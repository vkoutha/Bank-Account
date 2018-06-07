package bankAccountGui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class adminMenu extends JFrame{
	
	
	JList usersJList;
	JTextField searchTextField;
	
	JTextPane fNamePane;
	JScrollPane fNameScroll;
	
	JTextPane lNamePane;
	JScrollPane lNameScroll;
	
	JTextPane passwordPane;
	JScrollPane passwordScroll;
	
	JTextPane usernamePane;
	JScrollPane usernameScroll;
	
	JTextPane balancePane;
	JScrollPane balanceScroll;
	
	JTextPane loginPane;
	
	JTextPane createdOnPane;
	
	JTabbedPane options;
	JPanel overviewPanel;
	JPanel findUsersPanel;
	
	JLabel lblWelcomeLordAdmin;
	
	ArrayList<String> fileNames = new ArrayList<String>();
	ArrayList<String> matchedUsers = new ArrayList<String>();

	File[] directory;
	
	FileReader fReader;
	BufferedReader fR;
	
	FileWriter fWriter;
	BufferedWriter fW;
	
	boolean fromSearch = false;
	boolean allowKeyRelease = true;
	
	private JButton btnDeleteUser;
	
	public adminMenu(File[] userFiles){
		
		super("Admin menu");
		
		directory = userFiles;
		
		for (File x : directory){
			
			fileNames.add(x.getName().replace(".txt", ""));
			
		}
		
		options = new JTabbedPane();
		overviewPanel = new JPanel(null);
		findUsersPanel = new JPanel(null);
		
		options.add("Overview", overviewPanel);
		
		lblWelcomeLordAdmin = new JLabel("Welcome Lord Admin!");
		lblWelcomeLordAdmin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 40));
		lblWelcomeLordAdmin.setBounds(37, 77, 488, 138);
		overviewPanel.add(lblWelcomeLordAdmin);
		
		JButton btnReturnToLogin = new JButton("Return to Login");
		
		btnReturnToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				openLogin();
				
			}
		});
		
		btnReturnToLogin.setBounds(78, 228, 142, 25);
		overviewPanel.add(btnReturnToLogin);
		
		JButton btnCreateAccount = new JButton("Create Account");
		
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				openCreateAccount();
				
			}
		});
		
		btnCreateAccount.setBounds(263, 228, 142, 25);
		overviewPanel.add(btnCreateAccount);
		options.add("Users", findUsersPanel);
		
		usersJList = new JList(fileNames.toArray());
		usersJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane listScroll = new JScrollPane(usersJList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		findUsersPanel.add(listScroll);
		
		listScroll.add(usersJList);
		listScroll.setBounds(162, 450, 150, 210);
		listScroll.setViewportView(usersJList);
      
		searchTextField = new JTextField();
		
	searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
					
				matchedUsers.clear();
				
				if(allowKeyRelease == true){
				
				for(String users : fileNames){
					
					if(users.toLowerCase().contains(searchTextField.getText().toLowerCase())){
													
						matchedUsers.add(users);
						usersJList.setListData(matchedUsers.toArray());
						
					}else{
						
						usersJList.setListData(matchedUsers.toArray());
						
					}
										
				}
								
				}
							
				allowKeyRelease = true;
				
			}
		});
		
		searchTextField.setBounds(178, 386, 116, 22);
		findUsersPanel.add(searchTextField);
		searchTextField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(12, 103, 86, 16);
		findUsersPanel.add(lblUsername);
		
		JLabel lblFirstName = new JLabel("First name: ");
		lblFirstName.setBounds(12, 33, 107, 16);
		findUsersPanel.add(lblFirstName);
		
		
		JLabel lblLastName = new JLabel("Last name: ");
		lblLastName.setBounds(240, 33, 97, 16);
		findUsersPanel.add(lblLastName);
		
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(236, 103, 76, 16);
		findUsersPanel.add(lblPassword);
		
		
		JLabel lblLastLogin = new JLabel("Last login: ");
		lblLastLogin.setBounds(52, 188, 86, 16);
		findUsersPanel.add(lblLastLogin);
		
		
		JLabel lblAccountCreated = new JLabel("Created on:");
		lblAccountCreated.setBounds(356, 188, 134, 16);
		findUsersPanel.add(lblAccountCreated);
		
		fNamePane = new JTextPane();
		fNameScroll = new JScrollPane(fNamePane, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		fNameScroll.setBounds(85, 33, 127, 22);
		fNameScroll.setViewportView(fNamePane);
		fNamePane.setEditable(false);
		
		findUsersPanel.add(fNameScroll);
		
		
		lNamePane = new JTextPane();
		lNameScroll = new JScrollPane(lNamePane, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		lNameScroll.setBounds(318, 33, 122, 22);
		lNameScroll.setViewportView(lNamePane);
		lNamePane.setEditable(false);
		
		findUsersPanel.add(lNameScroll);
		
		usernamePane = new JTextPane();
		usernameScroll = new JScrollPane(usernamePane, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		usernameScroll.setBounds(85, 101, 127, 22);
		usernameScroll.setViewportView(usernamePane);
		usernamePane.setEditable(false);
		
		findUsersPanel.add(usernameScroll);
		
		
		
		passwordPane = new JTextPane();
		passwordScroll = new JScrollPane(passwordPane, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		passwordScroll.setBounds(318, 100, 122, 22);
		passwordScroll.setViewportView(passwordPane);
		passwordPane.setEditable(false);
		
		findUsersPanel.add(passwordScroll);
		
		getContentPane().add(options);

		balancePane = new JTextPane();
		balanceScroll = new JScrollPane(balancePane, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		balanceScroll.setBounds(199, 161, 97, 22);
		balanceScroll.setViewportView(balancePane);
		balancePane.setEditable(false);
		findUsersPanel.add(balanceScroll);		
		
		loginPane = new JTextPane();
		loginPane.setEditable(false);
		loginPane.setBounds(22, 217, 134, 22);
		findUsersPanel.add(loginPane);
		
		
		createdOnPane = new JTextPane();
		createdOnPane.setBounds(331, 217, 134, 22);
		createdOnPane.setEditable(false);
		findUsersPanel.add(createdOnPane);
		
		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setBounds(138, 163, 56, 16);
		findUsersPanel.add(lblBalance);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(-12, 286, 489, 16);
		findUsersPanel.add(separator);
		
		JLabel lblQuickSearch = new JLabel("Quick Search");
		lblQuickSearch.setBounds(193, 346, 90, 16);
		findUsersPanel.add(lblQuickSearch);
		
		JButton btnLoginAsUser = new JButton("Login as User");
		btnLoginAsUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(usersJList.getSelectedValue()!= null){
					
					openMenu();
					
				}else{
					
					JOptionPane.showMessageDialog(null, "No user to login as is selected");
					
				}
				
			}
		});
		btnLoginAsUser.setBounds(190, 248, 116, 25);
		findUsersPanel.add(btnLoginAsUser);
		
		btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(usersJList.getSelectedValue()!= null){
					
				deleteUser();
				
				}else{
					
					JOptionPane.showMessageDialog(null, "No user to delete is selected!");
					
				}
				
			}
		});
		btnDeleteUser.setBounds(190, 205, 116, 25);
		findUsersPanel.add(btnDeleteUser);
				
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String userSearched = searchTextField.getText();
				boolean searchSuccess = false;
				fromSearch = false;
				
				for(File x: directory){
					
					if(x.getName().equals(userSearched + ".txt")){
						
						searchSuccess = true;
						fromSearch = true;
						
						allowKeyRelease = false;
						usersJList.setSelectedValue(userSearched, true);
						
						
					}
					
					

				}
				
				if(searchSuccess == false){
					
					JOptionPane.showMessageDialog(null, userSearched + " was not found in the directory", "Error", JOptionPane.ERROR_MESSAGE);
					
				}
								
			}
		});
		
		usersJList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				if (!e.getValueIsAdjusting()){
					
					for(File x: directory){
						
						if(x.getName().equals(usersJList.getSelectedValue() + ".txt")){
							
							if(fromSearch = false){
								
							searchTextField.setText(null);
							
							}
							
							fNamePane.setText(getFirstName(x));
							lNamePane.setText(getLastName(x));
							usernamePane.setText(getUsername(x));
							passwordPane.setText(getPassword(x));
							loginPane.setText(getLastLogin(x));
							createdOnPane.setText(getAccountCreated(x));
							balancePane.setText(getBalance(x));
							
						}
							
						
					}
					
					if(fNamePane.getText().length() > 19){
						
						fNameScroll.setBounds(85, 33, 127, 40);

					}else{
						
						fNameScroll.setBounds(85, 33, 127, 20);
						
					}
					
					if(usernamePane.getText().length()>19){
						
						usernameScroll.setBounds(85, 101, 130, 40);
						
					}else{
						
						usernameScroll.setBounds(85, 101, 130, 20);
						
					}
					
					if(lNamePane.getText().length()>19){
						
						lNameScroll.setBounds(318, 33, 122, 40);			
						
					}else{
						
						lNameScroll.setBounds(318, 33, 122, 20);
						
					}
					
					if(passwordPane.getText().length()>19){
						
						passwordScroll.setBounds(318, 100, 122, 40);
						
					}else{
						
						passwordScroll.setBounds(318, 100, 122, 20);
						
					}
					
					if(balancePane.getText().length()>13){
						
						balanceScroll.setBounds(199, 161, 97, 40);
						
					}else{
						
						balanceScroll.setBounds(199, 161, 97, 20);
						
					}
					
					
					
					
					
				}
				
			}
		});
		
		
	}
	
	public String getFirstName(File userFile){
		
		String firstName = "";
			
			try{ 
				
				fReader = new FileReader(userFile);
				fR = new BufferedReader(fReader);

				firstName = fR.readLine().replace("First Name: ", "");
				
			}catch(Exception e){

				e.printStackTrace();
				firstName = null;
				
			}finally{
				
				try {
					
					fReader.close();
					fR.close();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		
		return firstName;
		
	}
	
	public String getLastName(File userFile){
		
		String lastName = "";
			
			try{ 
			
				
				fReader = new FileReader(userFile);
				fR = new BufferedReader(fReader);

				fR.readLine();
				lastName = fR.readLine().replace("Last Name: ", "");
				
			}catch(Exception e){

				lastName = null;
				
			}finally{
				
				try {
					
					fReader.close();
					fR.close();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		
		return lastName;
		
	}
	
	public String getUsername(File userFile){
		
		String username = "";
			
			try{ 
			
				
				fReader = new FileReader(userFile);
				fR = new BufferedReader(fReader);

				fR.readLine();
				fR.readLine();
				
				username = fR.readLine().replace("Username: ", "");
				
			}catch(Exception e){

				username = null;
				
			}finally{
				
				try {
					
					fReader.close();
					fR.close();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		
		return username;
		
	}
	
	public String getPassword(File userFile){
		
		String password = "";
		
		
			
			try{ 
			
				
				fReader = new FileReader(userFile);
				fR = new BufferedReader(fReader);

				fR.readLine();
				fR.readLine();
				fR.readLine();
				
				
				
				password = fR.readLine().replace("Password: ", "");
				
				
			}catch(Exception e){

				e.printStackTrace();
				password = null;
				
			}finally{
				
				try {
					
					fReader.close();
					fR.close();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		
		return password;
		
	}
	
	public String getBalance(File userFile){
		
		DecimalFormat dF = new DecimalFormat("#0.00");
		
		String balance = "";
			
			try{ 
			
				
				fReader = new FileReader(userFile);
				fR = new BufferedReader(fReader);

				fR.readLine();
				fR.readLine();
				fR.readLine();
				fR.readLine();
				
				balance = fR.readLine().replace("Balance: ", "");
				
			}catch(Exception e){

				balance = null;
				
			}finally{
				
				try {
					
					fReader.close();
					fR.close();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
				
		return "$" + balance;
		
	}
	
	public String getLastLogin(File userFile){
		
		String loginDate = "";
			
			try{ 
			
				
				fReader = new FileReader(userFile);
				fR = new BufferedReader(fReader);

				fR.readLine();
				fR.readLine();
				fR.readLine();
				fR.readLine();
				fR.readLine();
				fR.readLine();

				loginDate = fR.readLine().replace("Last login: ", "");
				
				if(loginDate.equals("null")){
					
					loginDate = "Never logged in";
					
				}
				
			}catch(Exception e){

				e.printStackTrace();
				loginDate = null;
				
			}finally{
				
				try {
					
					fReader.close();
					fR.close();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
					
		return loginDate;
		
		
	}
	
	public String getAccountCreated(File userFile){
		
		String accountCreatedDate = "";
			
			try{ 
			
				
				fReader = new FileReader(userFile);
				fR = new BufferedReader(fReader);

				fR.readLine();
				fR.readLine();
				fR.readLine();
				fR.readLine();
				fR.readLine();

				accountCreatedDate = fR.readLine().replace("Account created on: ", "");
				
			}catch(Exception e){

				accountCreatedDate = null;
				
			}finally{
				
				try {
					
					fReader.close();
					fR.close();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		
		return accountCreatedDate;
		
	}
	
	public void openLogin(){
		
		login login = new login();
		
		login.setSize(450, 300);
		login.setResizable(false);
		login.setVisible(true);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setLocationRelativeTo(null);
		
		dispose();
		
	}
	
	public void openCreateAccount(){
		
		File bankDirectory = new File("C:\\Users\\Public\\Documents\\Koutha Bank");

		createAccount cA = new createAccount(bankDirectory);
		cA.setSize(420, 500);
		cA.setResizable(false);
		cA.setVisible(true);
		cA.setLocationRelativeTo(null);
		cA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		dispose();
		
	}
	
	public void openMenu(){
		
		File menuUserFile = new File("C:\\Users\\Public\\Documents\\Koutha Bank\\" + usersJList.getSelectedValue() + ".txt");
		menu menu = new menu(menuUserFile);
		
		menu.setSize(500, 300);
		menu.setVisible(true);
		menu.setLocationRelativeTo(null);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		dispose();
		
	}
	
	public void deleteUser(){
		
		File fileToDelete = null;
		
		for(File x : directory){
			
			if((usersJList.getSelectedValue() + ".txt").equals(x.getName())){
				
				fileToDelete = x;				
				
			}
			
		}
		
		try{
			
			fReader = new FileReader(fileToDelete);
			fR = new BufferedReader(fReader);
			
			fWriter = new FileWriter(fileToDelete);
			fW = new BufferedWriter(fWriter);
			
			System.gc();
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}finally{
			
			
			try{
				
			fReader.close();
			fR.close();
			
			fWriter.close();
			fW.close();
			
			}catch(Exception e){
				
				e.printStackTrace();
				
			}
			
			System.gc();
			
		}
						
		fileToDelete.delete();

		JOptionPane.showMessageDialog(null, "User has been deleted!");
		
		
		fileNames.remove(usersJList.getSelectedValue());
		usersJList.setListData(fileNames.toArray());
		
		fNamePane.setText(null);
		lNamePane.setText(null);
		usernamePane.setText(null);
		passwordPane.setText(null);
		loginPane.setText(null);
		createdOnPane.setText(null);
		balancePane.setText(null);
						

	}
	
}
