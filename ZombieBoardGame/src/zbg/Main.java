package zbg;

import java.io.File;

import javax.swing.JOptionPane;

public class Main {

	private static Object[] options = new Object[] { "New Game", "Load Game", "Quit" };

	public static void main(String[] args) {
		// Get Filepath

		boolean b = true;
		do {
			int game = JOptionPane.showOptionDialog(null, "Which game would you like to play?", "Game", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
			if (game == 0) {
				String filePath = JOptionPane.showInputDialog("Type in the filepath of the board you would like to use", "Boards/Board0.board");
				if (filePath == null) {
					filePath = "Boards/Board0.board";
				}
				
				File f = new File(filePath);
				String extension = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
				if (!extension.equalsIgnoreCase("board")) {
					JOptionPane.showMessageDialog(null, "Thats not a .board file!");
				}else{
					if (f.exists()) {
						CreateBoard board = new CreateBoard(filePath);
						b = false;
					} else {
						JOptionPane.showMessageDialog(null, "File does not exist.");
					}
				}
				
			} else if(game == 1){
				String filePath = JOptionPane.showInputDialog("Type in the filepath of the board you would like to use", "Saves/Save0.rydge");
				if (filePath == null) {
					filePath = "Saves/Save0.rydge";
				}
				File f = new File(filePath);
				String extension = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
				if (!extension.equalsIgnoreCase("rydge")) {
					JOptionPane.showMessageDialog(null, "Thats not a .rydge file!");
				} else {
					if (f.exists()) {
						CreateBoard board = new CreateBoard(filePath, 0);
						b = false;
					} else {
						JOptionPane.showMessageDialog(null, "File does not exist.");
					}
				}
			} else{
				System.exit(0);
			}
		} while (b == true);

	}

}
