package onTheHill;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MenuRunner implements Runnable{
	
	public JFrame frame;
	public MenuContentPanel mcp;
	public MenuContentPanel options;
	public MainMenuListener mml;
	public Thread menuThread;
	
	public boolean mm, opt, cred;
	
	public boolean looping;
	
	public static Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();

	public MenuRunner(){
		initFrame();
		initPanelAndButtons();
		displayFrame();
		mm = true;
		looping = true;
		Thread th = new Thread(this);
		th.start();
	}
	
	public void initFrame(){
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setSize(JFrame.MAXIMIZED_HORIZ, JFrame.MAXIMIZED_VERT);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initPanelAndButtons(){
		mcp = new MenuContentPanel(resolution);
		mcp.setBackground(Color.BLACK);
		mcp.setSize(resolution);
		mcp.setPreferredSize(resolution);
		
		options = new MenuContentPanel(resolution);
		options.setBackground(Color.BLACK);
		options.setSize(resolution);
		options.setPreferredSize(resolution);
		
		mcp.addButton("New Game", "ng");
		mcp.addButton("Options", "op");
		mcp.addButton("Credits", "ct");
		mcp.addButton("Quit", "qt");
		
		options.addButton("Music", "mc");
		options.addButton("Back", "bk");
		
		mml = new MainMenuListener(this);
		mcp.addMouseListener(mml);
		mcp.addMouseMotionListener(mml);
		options.addMouseListener(mml);
		options.addMouseMotionListener(mml);
		
		options.setFocusable(true);
		mcp.setFocusable(true);
		
		frame.setContentPane(mcp);
	}
	
	public void displayFrame(){
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		mcp.repaint();
	}

	public void run() {
		while(looping){
			if(mm){
				mcp.menuEvents();
				mcp.repaint();
				mcp.requestFocus();
			}else if(opt){
				options.menuEvents();
				options.repaint();
				options.requestFocus();
			}else if(cred){
				
			}
		}
		new Betrayal(frame, resolution);
	}
	
}
