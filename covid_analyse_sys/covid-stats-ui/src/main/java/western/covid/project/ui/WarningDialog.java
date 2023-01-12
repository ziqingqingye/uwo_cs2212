package western.covid.project.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * A customized class to display dialog.
 * @author Tianci Du
 * @version 1.0
 */
public class WarningDialog extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel msgLabel;
	private JButton okBtn;
	
	public WarningDialog(String content,JFrame parent) {
		
		/**
		 *  improvements:
		 *  1. 
		 */
		this.setTitle("Warning Message");
		this.setSize(280, 180);
		this.setVisible(true);
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setLocation(parent.getWidth()/2-this.getWidth()/2, parent.getHeight()/2-this.getHeight()/2);
		msgLabel=new JLabel(content);
		msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(msgLabel,BorderLayout.CENTER);
		okBtn=new JButton();
		okBtn.setText("ok");
		this.add(okBtn,BorderLayout.SOUTH);
		okBtn.addActionListener(this);
		
		
	}

	/**
	 * Click on ok button to close dialog.
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==okBtn) {
			this.dispose();
		}
		
	}

}
