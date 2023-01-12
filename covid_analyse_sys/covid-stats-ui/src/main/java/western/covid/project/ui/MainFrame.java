package western.covid.project.ui;
import com.covid.country.factory.KnownCountryFactory;
import western.covid.project.analysis.facade.DispatchJobFacade;
import western.covid.project.dispatch.observe.MessageObservable;
import western.covid.project.dispatch.observe.Observable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * This class constructs the dashboard of COVID-19 STATS SYSTEM.
 * @author Tianci Du
 * @version 1.0
 */

public class MainFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField addCountryTextField;
	private JTextField removeCountryTextField;
	private JTextField outputTextField;
	private JList countryList;
	private DefaultListModel listModel;
	private JButton addCountryBtn;
	private JButton removeCountryBtn;
	private JComboBox methodComboBox;
	private JButton recalcBtn;
	private ResultTextArea outputArea;
	private MapPanel mapPanel=null;
	private JPanel centerPanel=null;
	private Observable messageObservable=null;
	private DispatchJobFacade facade;



	/**
	 * Create the main window frame in the constructor.
	 */
	public MainFrame() {

		messageObservable=new MessageObservable();
		facade=new DispatchJobFacade(messageObservable);
		/**
		 * setting the size and position of main window
		 */
		this.setTitle("COVID-19 stats");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 680);
		int windowWidth = this.getWidth(); // get the width of current frame
        int windowHeight = this.getHeight();// get the height of current frame
        Toolkit kit = Toolkit.getDefaultToolkit(); // toolkit which can be used to retrieve the size of computer screen
        Dimension screenSize = kit.getScreenSize(); 
        int screenWidth = screenSize.width; // get screen width
        int screenHeight = screenSize.height; // get screen height
        // make the position of current frame in the center of screen
        this.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		/**
		 * setup north panel 
		 */
		JPanel northPanel = new JPanel();
		contentPane.add(northPanel, BorderLayout.NORTH);
		
		JPanel addPanel = new JPanel();
		northPanel.add(addPanel);
		addPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel countryLabel = new JLabel("Add a country:");
		addPanel.add(countryLabel);
		
		addCountryTextField = new JTextField();
		addPanel.add(addCountryTextField);
		addCountryTextField.setColumns(10);
		
		addCountryBtn = new JButton("add");
		addPanel.add(addCountryBtn);
		addCountryBtn.addActionListener(this);
		
		JPanel removePanel = new JPanel();
		northPanel.add(removePanel);
		
		JLabel removeCountryLabel = new JLabel("Remove a country:");
		removePanel.add(removeCountryLabel);
		
		removeCountryTextField = new JTextField();
		removePanel.add(removeCountryTextField);
		removeCountryTextField.setColumns(10);
		
		removeCountryBtn = new JButton("remove");
		removePanel.add(removeCountryBtn);
		removeCountryBtn.addActionListener(this);
		
		/**
		 * setup East panel configuration
		 */
		JPanel controlPanel = new JPanel();
		contentPane.add(controlPanel, BorderLayout.EAST);
		controlPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel topPanel = new JPanel();
		controlPanel.add(topPanel);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel listTitleLabel = new JLabel("List of selected country"); 
		topPanel.add(listTitleLabel,BorderLayout.NORTH);
		listTitleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		
		countryList = new JList();
		listModel = new DefaultListModel();
		countryList.setModel(listModel);
		topPanel.add(countryList,BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel();
		controlPanel.add(bottomPanel);
		bottomPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel operationPanel = new JPanel();
		bottomPanel.add(operationPanel,BorderLayout.NORTH);
		operationPanel.setLayout(new GridLayout(2, 1, 0, 0));
		/**
		 * facade design pattern
		 */
		recalcBtn = new JButton("Recaculate");
		operationPanel.add(recalcBtn);
		recalcBtn.addActionListener(this);
		
		JLabel outputLabel = new JLabel("Output:");
		outputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		operationPanel.add(outputLabel);
		
//		outputTextField = new JTextField();
//		bottomPanel.add(outputTextField,BorderLayout.CENTER);
//		outputTextField.setColumns(10);
		outputArea = new ResultTextArea();
		bottomPanel.add(outputArea,BorderLayout.CENTER);
		messageObservable.registerObserver(outputArea);
		
		/**
		 *  setup Center panel configuration
		 */
		centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		mapPanel=new MapPanel();
		centerPanel.add(mapPanel,BorderLayout.CENTER);
		/**
		 * register observer in this case map panel is able to receive update notification
		 */
		messageObservable.registerObserver(mapPanel);
		
		/**
		 * South panel configuration
		 */
		JPanel southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);
		
		JLabel chooseMethodLabel = new JLabel("Choose analysis method:");
		chooseMethodLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		southPanel.add(chooseMethodLabel);
		String[] cases={"ConfirmCase","CasePerCapita","ConfirmCaseFemale","ConfirmCaseMale",""};
		methodComboBox = new JComboBox(cases);
		southPanel.add(methodComboBox);
	}


	/**
	 * Handle the button clicked event including add action,remove action and recalculation action.
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==addCountryBtn) {
			if(checkTextField(addCountryTextField)) {
				new WarningDialog("<html>country field is empty.check again pls.</html>",this);
			}
			if(listModel.contains(addCountryTextField.getText())) {
				new WarningDialog("<html>the deserved country has been existed.check again pls.</html>",this);
			}else {
				List<String> knownList= KnownCountryFactory.getInstance();
				if (!knownList.contains(addCountryTextField.getText())){
					new WarningDialog("<html>the input country is not in database. pls check again.</html>",this);
					return;
				}
				listModel.addElement(addCountryTextField.getText());
			}
			
		}else if(e.getSource()==removeCountryBtn) {
			if(checkTextField(removeCountryTextField)) {
				new WarningDialog("<html>country field is empty.check again pls.</html>",this);
			}
			List<String> knownList= KnownCountryFactory.getInstance();
			if (!knownList.contains(removeCountryTextField.getText())){
				new WarningDialog("<html>the input country is not in database. pls check again.</html>",this);
				return;
			}
			if(listModel.getSize()>0&&listModel.contains(removeCountryTextField.getText())) {
				if (listModel.indexOf(removeCountryTextField.getText()) == 0){
					listModel.removeElement(removeCountryTextField.getText());
				}else {
					new WarningDialog("<html>The input country to be remove from the list is not in there in the first place.<br/> pls select another one.</html>",this);
				}

			}
			else if (listModel.contains(removeCountryTextField.getText()) == false){
				new WarningDialog("<html>the input country is not in the list of selected countries.<br/> pls check again.<html>",this);
			}
			
		}else if(e.getSource()==recalcBtn){


			Enumeration<String> data=this.listModel.elements();
			List<String> list=new ArrayList<>();
			while(data.hasMoreElements()){
				list.add(data.nextElement());
			}
			Map<String,Double> map=facade.perform(methodComboBox.getSelectedItem().toString(),list,mapPanel);
//			StringBuffer buffer=new StringBuffer();
//			for (Map.Entry<String, Double> entry: map.entrySet()){
//				String content=String.format("%s --> %f",entry.getKey(),entry.getValue());
//				content+="\n";
//				buffer.append(content);
//			}
//			outputArea.append(buffer.toString());
//			System.out.println("map:"+map);
		}
		
	}
	
	private boolean checkTextField(JTextField textField) {
		return addCountryTextField.getText()==null||addCountryTextField.getText().isEmpty();
	}

}
