
package elevator;
import javax.swing.SwingUtilities;
import ui.MainFrame;
/**
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(() -> new MainFrame()); // EDT 이벤트 큐에 작업 등록
	}

}
