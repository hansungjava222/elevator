package ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class MainFrame extends JFrame {

	 private final CardLayout cardLayout = new CardLayout(); //화면 전환을 관리하는 객체 생성
	    private final JPanel root = new JPanel(cardLayout); //root 참조변수에 객체 생성후 대입

	    private JPanel panelHeader, panelSettings, panelShaft, panelResult; // 패널 4개 만들기
	    private JLabel lblTitle, lblStep;

	    public MainFrame() {
	        setTitle("엘리베이터 시뮬레이션");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        root.add(new IntroPanel("/resources/intro1.png", // 1번째 인자
	                "01  /  OUR CAMPUS", //2번째 인자
	                "이동이 시작되는 곳,\n한성대학교 상상관.", // 3번째 인자
	                "매일 수많은 학생이 오가는 공간.\n강의실로 향하는 일상에도 수많은 이동이 존재합니다.", //4번째 인자
	                "다음 장면  →", // 5번째 인자, 아래 6번째 인자
	                () -> cardLayout.show(root, "intro2")), "intro1"); // 새로운 IntroPanel 객체를 생성해서 root 패널에 추가하고, 그 화면의 이름을 intro1로 지정한다.

	        root.add(new IntroPanel("/resources/intro2.png",
	                "02  /  THE WAIT",
	                "서로 다른 목적지,\n겹쳐지는 호출.",
	                "누군가는 2층에서 7층으로, 또 누군가는 9층에서 1층으로.\n같은 순간에도 승강기를 향한 요청은 달라집니다.",
	                "다음 장면  →",
	                () -> cardLayout.show(root, "intro3")), "intro2"); //새로운 IntroPanel 객체를 생성해서 root 패널에 추가하고, 그 화면의 이름을 intro2로 지정한다.

	        root.add(new IntroPanel("/resources/intro3.png",
	                null,
	                "한 번의 배차가,\n기다림을 바꿉니다.",
	                "호출 순서와 이동 경로를 계산하고,\n배차 알고리즘에 따라 달라지는 결과를 직접 비교하세요.",
	                "시뮬레이션 시작하기  →",
	                () -> cardLayout.show(root, "main")), "intro3"); //새로운 IntroPanel 객체를 생성해서 root 패널에 추가하고, 그 화면의 이름을 intro3로 지정한다.

	        root.add(createMainPanel(), "main"); //메인 화면 등록

	        root.setPreferredSize(new Dimension(1200, 800));   // Figma 프레임 크기
	        add(root); 
	        pack();                                            // 내용이 정확히 1200×800이 되게
	        setLocationRelativeTo(null);
	        setVisible(true);
	    }

	    private JPanel createMainPanel() {

	        // BorderLayout을 사용하는 메인 패널 생성
	        JPanel main = new JPanel(new BorderLayout());

	        // 헤더 패널 생성 후 참조값 대입
	        panelHeader = createHeader();

	        // 설정 패널 생성 및 크기, 배경색 설정
	        panelSettings = new JPanel();
	        panelSettings.setPreferredSize(new Dimension(320, 0));
	        panelSettings.setBackground(Color.LIGHT_GRAY);

	        // 엘리베이터 표시 패널 생성
	        panelShaft = new JPanel();
	        panelShaft.setBackground(Color.WHITE);

	        // 결과 패널 생성 및 크기, 배경색 설정
	        panelResult = new JPanel();
	        panelResult.setPreferredSize(new Dimension(280, 0));
	        panelResult.setBackground(Color.CYAN);

	        // 메인 패널에 각 영역 배치
	        main.add(panelHeader, BorderLayout.NORTH);
	        main.add(panelSettings, BorderLayout.WEST);
	        main.add(panelShaft, BorderLayout.CENTER);
	        main.add(panelResult, BorderLayout.EAST);

	        // 완성된 메인 패널 반환
	        return main;
	    }

	    private JPanel createHeader() {

	        // BorderLayout을 사용하는 헤더 패널 생성
	        JPanel panel = new JPanel(new BorderLayout());

	        // 헤더 높이 80px, 배경색 흰색 설정
	        panel.setPreferredSize(new Dimension(0, 80));
	        panel.setBackground(Color.WHITE);

	        // 아래쪽 파란 테두리 4px + 좌우 여백 32px
	        panel.setBorder(BorderFactory.createCompoundBorder(
	                BorderFactory.createMatteBorder(0, 0, 4, 0,
	                        new Color(0x4A7FD9)),
	                BorderFactory.createEmptyBorder(0, 32, 0, 32)));

	        // 제목 라벨 생성 및 글꼴 설정
	        lblTitle = new JLabel("엘리베이터 배차 시뮬레이터");
	        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 26));

	        // 진행 단계 라벨 생성 및 글꼴, 색상 설정
	        lblStep = new JLabel("01 / 04  호출 등록");
	        lblStep.setFont(new Font("SansSerif", Font.PLAIN, 16));
	        lblStep.setForeground(Color.GRAY);

	        // 제목은 왼쪽, 진행 단계는 오른쪽에 배치
	        panel.add(lblTitle, BorderLayout.WEST);
	        panel.add(lblStep, BorderLayout.EAST);

	        // 완성된 헤더 패널 반환
	        return panel;
	    }
}