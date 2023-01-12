package western.covid.project.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import western.covid.project.login.proxy.IUserDao;
import western.covid.project.login.proxy.UserDaoImpl;
import western.covid.project.login.proxy.UserDaoProxy;
import western.covid.project.utils.ResponseResult;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

/**
 * The entrance class for COVID-19 STATS System.
 * @author Tianci Du
 * @version 1.0
 */
public class LoginFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField nameField;
	private JPasswordField passwordField;
	private JButton loginBtn;
	private IUserDao userDao;

	/**
	 * Launch the application. Entry of the system.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		
		/**
		 *  in this case, we choose json file as out database.
		 *  in the future, if we choose other type of database ,what we need to do is to change implementation here.
		 */
		
		this.userDao=new UserDaoProxy(new UserDaoImpl());
	
		this.setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(100, 84, 80, 30);
		contentPane.add(lblNewLabel);
		
		nameField = new JTextField();
		nameField.setBounds(160, 84, 193, 30);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(84, 131, 80, 30);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(160, 131, 193, 30);
		contentPane.add(passwordField);
		
		loginBtn = new JButton("Login");
		loginBtn.setBounds(99, 197, 117, 29);
		loginBtn.addActionListener(this);
		contentPane.add(loginBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setBounds(236, 197, 117, 29);
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(cancelBtn);
	}

	/**
	 * Processes action events occurring on this button
	 * by dispatching them to any registered.
	 * This method is used handle login action when user clicked on the login button.
	 * {@code ActionListener} objects.
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==loginBtn) {
			ResponseResult result=this.userDao.login(nameField.getText(), new String(passwordField.getPassword()));
			if(result!=null&&result.getCode()==200) {
				this.setVisible(false);
				MainFrame frame = new MainFrame();
				frame.setVisible(true);
			}else {
				if(nameField==null||nameField.getText().equals("")||nameField.getText().length()<3
						||passwordField.getPassword()==null||passwordField.getText().equals("")) {
					new WarningDialog("<html>user name cannot be empty.<br/>password cannot be empty.</html>",LoginFrame.this);
					
				}
			}
			
		}
		
	}
}
