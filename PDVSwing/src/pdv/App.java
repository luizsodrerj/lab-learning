package pdv;

import java.awt.EventQueue;

import framework.presentation.swing.Window;

public class App {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Window.setSystemLookAndFeel();
					
					MainFrame frame = new MainFrame();
					Window.centralizeWindow(frame);
					frame.init();
					frame.setVisible(true);
					
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
	}

}
