package bankAccountGui;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class login extends JFrame{
	
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	File[] userFiles;
	File foundUserFile;
	File directory;
	
	FileReader fReader;
	BufferedReader fR;
	menu menu;
	createAccount cA;
	adminMenu aMenu;
	boolean userFileFound = false;
	
	public login(){
		
		super("Login");
		
		getContentPane().setLayout(null);
				
		directory = new File("C:\\Users\\Public\\Documents\\Koutha Bank");

		userFiles = directory.listFiles();
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblUsername.setBounds(67, 99, 116, 16);
		getContentPane().add(lblUsername);
		
		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		usernameTextField.setBounds(175, 98, 116, 22);
		getContentPane().add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblPassword.setBounds(67, 151, 108, 16);
		getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(175, 149, 116, 22);
		getContentPane().add(passwordField);
		
		JLabel lblWelcomeToKoutha = new JLabel("Welcome to Koutha Bank!");
		lblWelcomeToKoutha.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 29));
		lblWelcomeToKoutha.setBounds(47, 13, 343, 63);
		getContentPane().add(lblWelcomeToKoutha);
		
		JButton loginButton = new JButton("Login");
		
		loginButton.setBounds(96, 198, 97, 25);
		getContentPane().add(loginButton);
		
		JButton btnCreateAccount = new JButton("Create Account");
		
		btnCreateAccount.setBounds(216, 198, 131, 25);
		getContentPane().add(btnCreateAccount);
		
		loginButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				
				boolean userFileFound = false;
				
				char[] adminPassChars = passwordField.getPassword();
				String adminPass = "";
				
				for(char x : adminPassChars){
					
					adminPass += x;	
						
					}
				
				if(usernameTextField.getText().equals("vkouthaADMIN") && adminPass.equals("vkouthaADMIN")){
					
					aMenu = new adminMenu(userFiles);
					userFileFound = true;
					openAdminMenu();

				}
								
				for(File userFile : userFiles){
					
					if(userFile.getName().equals(usernameTextField.getText() + ".txt")){
						
						userFileFound = true;
						String currentLine = "";
						
						try{
							
							fReader = new FileReader(userFile);
							fR = new BufferedReader(fReader);
							
						for(int x = 0; x <= 3; x++){
							
							currentLine = fR.readLine();
							
						}
						
						char[] passChars = passwordField.getPassword();
						String password = "";
						
						for(char x : passChars){
							
						password += x;	
							
						}
						
						if(password.equals(currentLine.replace("Password: ", ""))){
							
							menu = new menu(userFile);
							openMenu();
							
						}else{
							
							JOptionPane.showMessageDialog(null, "The information you've entered does not match anything in our system. Please try again.");
							
						}
						
						}catch(Exception e1){
							
							e1.printStackTrace();
							
						}finally{
							
							try {
								
								fReader.close();
								fR.close();

							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						
					}
					
				}
				
				if(userFileFound == false){
					
					JOptionPane.showMessageDialog(null, "The information you've entered does not match anything in our system! Please try again!");
					
				}
				
				
				
			}
		});
		
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean userFileFound = false;
				
				char[] adminPassChars = passwordField.getPassword();
				String adminPass = "";
				
				for(char x : adminPassChars){
					
					adminPass += x;	
						
					}
				
				if(usernameTextField.getText().equals("vkouthaADMIN") && getEncrypted(adminPass).equals("xmqwvc\\<?HDI")){
					
					aMenu = new adminMenu(userFiles);
					userFileFound = true;
					openAdminMenu();
					
				}
				
				for(File userFile : userFiles){
					
					if(userFile.getName().equals(usernameTextField.getText() + ".txt")){
						
						userFileFound = true;
						String currentLine = "";
						
						try{
							
							fReader = new FileReader(userFile);
							fR = new BufferedReader(fReader);
							
						for(int x = 0; x <= 3; x++){
							
							currentLine = fR.readLine();
							
						}
						
						char[] passChars = passwordField.getPassword();
						String password = "";
						
						for(char x : passChars){
							
						password += x;	
							
						}
						
						if(password.equals(currentLine.replace("Password: ", ""))){
							
							menu = new menu(userFile);
							openMenu();
							
						}else{
							
							JOptionPane.showMessageDialog(null, "The information you've entered does not match anything in our system. Please try again.");
							
						}
						
						}catch(Exception e1){
							
							e1.printStackTrace();
							
						}finally{
							
							try {
								
								fReader.close();
								fR.close();

							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						
					}
					
				}
				
				if(userFileFound == false){
					
					JOptionPane.showMessageDialog(null, "The information you've entered does not match anything in our system! Please try again!");
					
				}
				
				
				
			
				
			}
		});
		
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cA = new createAccount(directory);
				openCreateAccount();
				
			}
		});
		
		
	}
	
	public String getEncrypted(String pass){
		
		String encryptedPass = "";
		
		
		for(int x = 0; x < pass.length(); x++){
						
			if(pass.charAt(x) < 105){
				
				encryptedPass += ((char)(pass.charAt(x) - 5));
				
			}else if (pass.charAt(x) < 150){
			
			encryptedPass += ((char)(pass.charAt(x) + 2));
			
			}else{
				
				encryptedPass += ((char)(pass.charAt(x) - 20));
				
			}

		}
		
		return encryptedPass;
		
	}
		
	
	
	public String getDate(){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		
		Date date = new Date();
		
		return dateFormat.format(date);
		
	}
	
	public void openMenu(){
		
		menu.setSize(500, 300);
		menu.setVisible(true);
		menu.setLocationRelativeTo(null);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		dispose();
		
		
	}
	
	public void openAdminMenu(){
		
		aMenu.setSize(500, 800);
		aMenu.setVisible(true);
		aMenu.setLocationRelativeTo(null);
		aMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		dispose();
		
	}
	
	public void openCreateAccount(){
		
		cA.setSize(420, 500);
		cA.setResizable(false);
		cA.setVisible(true);
		cA.setLocationRelativeTo(null);
		cA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		dispose();
		
	}
	
}
