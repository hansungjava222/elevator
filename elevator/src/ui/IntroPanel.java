package ui;

import javax.swing.*;
import java.awt.*;

public class IntroPanel extends JPanel {

    private static final Color BG     = new Color(0x0E1822);
    private static final Color ACCENT = new Color(0x54E5D4);

    private final Image background;

    public IntroPanel(String imagePath, String step, String title, String desc,
                      String buttonText, Runnable onNext) {

        java.net.URL url = getClass().getResource(imagePath);
        background = (url != null) ? new ImageIcon(url).getImage() : null;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(0, 72, 0, 0));   // 왼쪽 여백

        add(Box.createVerticalGlue());                              // 위쪽 빈 공간

        if (step != null) {
            JLabel lblStep = new JLabel(step);
            lblStep.setFont(new Font("SansSerif", Font.PLAIN, 15));
            lblStep.setForeground(ACCENT);
            lblStep.setAlignmentX(LEFT_ALIGNMENT);
            add(lblStep);
            add(Box.createVerticalStrut(24));
        }

        JLabel lblTitle = new JLabel(toHtml(title));
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 44));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(LEFT_ALIGNMENT);
        add(lblTitle);
        add(Box.createVerticalStrut(56));

        JLabel lblDesc = new JLabel(toHtml(desc));
        lblDesc.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblDesc.setForeground(new Color(200, 210, 220));
        lblDesc.setAlignmentX(LEFT_ALIGNMENT);
        add(lblDesc);
        add(Box.createVerticalStrut(90));

        JButton btnNext = new JButton(buttonText);
        btnNext.setFont(new Font("SansSerif", Font.BOLD, 15));
        btnNext.setForeground(BG);
        btnNext.setBackground(ACCENT);
        btnNext.setOpaque(true);
        btnNext.setBorderPainted(false);
        btnNext.setFocusPainted(false);
        btnNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNext.setMaximumSize(new Dimension(266, 56));
        btnNext.setPreferredSize(new Dimension(266, 56));
        btnNext.setAlignmentX(LEFT_ALIGNMENT);
        btnNext.addActionListener(e -> onNext.run());
        add(btnNext);

        add(Box.createVerticalGlue());                              // 아래쪽 빈 공간
    }

    // "첫 줄\n둘째 줄" → JLabel에서 줄바꿈되도록 HTML로 변환
    private static String toHtml(String text) {
        return "<html>" + text.replace("\n", "<br>") + "</html>";
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int w = getWidth(), h = getHeight();

        g2.setColor(BG);
        g2.fillRect(0, 0, w, h);

        // 배경 사진: 비율 유지하면서 화면을 꽉 채우기
        if (background != null) {
            int iw = background.getWidth(this), ih = background.getHeight(this);
            double scale = Math.max((double) w / iw, (double) h / ih);
            int dw = (int) (iw * scale), dh = (int) (ih * scale);
            g2.drawImage(background, (w - dw) / 2, (h - dh) / 2, dw, dh, this);
        }

        // 왼쪽은 진하게, 오른쪽은 옅게 어두운 막 씌우기 (글씨가 잘 보이도록)
        g2.setPaint(new GradientPaint(0, 0, new Color(14, 24, 34, 240),
                                      w, 0, new Color(14, 24, 34, 120)));
        g2.fillRect(0, 0, w, h);
    }
}