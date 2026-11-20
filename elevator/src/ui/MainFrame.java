package ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class MainFrame extends JFrame {

	 private final CardLayout cardLayout = new CardLayout();
	    private final JPanel root = new JPanel(cardLayout);

	    private JPanel panelHeader, panelSettings, panelShaft, panelResult;
	    private JLabel lblTitle, lblStep;

	    public MainFrame() {
	        setTitle("엘리베이터 시뮬레이션");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        root.add(new IntroPanel("/resources/intro1.png",
	                "01  /  OUR CAMPUS",
	                "이동이 시작되는 곳,\n한성대학교 상상관.",
	                "매일 수많은 학생이 오가는 공간.\n강의실로 향하는 일상에도 수많은 이동이 존재합니다.",
	                "다음 장면  →",
	                () -> cardLayout.show(root, "intro2")), "intro1");

	        root.add(new IntroPanel("/resources/intro2.png",
	                "02  /  THE WAIT",
	                "서로 다른 목적지,\n겹쳐지는 호출.",
	                "누군가는 2층에서 7층으로, 또 누군가는 9층에서 1층으로.\n같은 순간에도 승강기를 향한 요청은 달라집니다.",
	                "다음 장면  →",
	                () -> cardLayout.show(root, "intro3")), "intro2");

	        root.add(new IntroPanel("/resources/intro3.png",
	                null,
	                "한 번의 배차가,\n기다림을 바꿉니다.",
	                "호출 순서와 이동 경로를 계산하고,\n배차 알고리즘에 따라 달라지는 결과를 직접 비교하세요.",
	                "시뮬레이션 시작하기  →",
	                () -> cardLayout.show(root, "main")), "intro3");

	        root.add(createMainPanel(), "main");

	        root.setPreferredSize(new Dimension(1200, 800));   // Figma 프레임 크기
	        add(root);
	        pack();                                            // 내용이 정확히 1200×800이 되게
	        setLocationRelativeTo(null);
	        setVisible(true);
	    }

	    private JPanel createMainPanel() {
	        JPanel main = new JPanel(new BorderLayout());

	        panelHeader = createHeader();

	        panelSettings = new JPanel();
	        panelSettings.setPreferredSize(new Dimension(320, 0));
	        panelSettings.setBackground(Color.LIGHT_GRAY);

	        panelShaft = new JPanel();
	        panelShaft.setBackground(Color.WHITE);

	        panelResult = new JPanel();
	        panelResult.setPreferredSize(new Dimension(280, 0));
	        panelResult.setBackground(Color.CYAN);

	        main.add(panelHeader, BorderLayout.NORTH);
	        main.add(panelSettings, BorderLayout.WEST);
	        main.add(panelShaft, BorderLayout.CENTER);
	        main.add(panelResult, BorderLayout.EAST);
	        return main;
	    }

    private JPanel createHeader() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(0, 80));
        panel.setBackground(Color.WHITE);

        // 아래쪽 파란 선(4px) + 안쪽 여백(위 0, 좌 32, 아래 0, 우 32)
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(0x4A7FD9)),
                BorderFactory.createEmptyBorder(0, 32, 0, 32)));

        lblTitle = new JLabel("엘리베이터 배차 시뮬레이터");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 26));

        lblStep = new JLabel("01 / 04  호출 등록");
        lblStep.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblStep.setForeground(Color.GRAY);

        panel.add(lblTitle, BorderLayout.WEST);
        panel.add(lblStep, BorderLayout.EAST);
        return panel;
    }
}