package bankAccountGui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class createAccount extends JFrame{
	
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField userNameTextField;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldConfirm;
	
	File directory;
	File user;
	FileWriter fWriter;
	BufferedWriter fW;
	login login;
	
	public createAccount(File bankDirectory){
		
		super("Create Account");
		getContentPane().setLayout(null);
		
		directory = bankDirectory;
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblFirstName.setBounds(59, 106, 90, 16);
		getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblLastName.setBounds(64, 158, 75, 16);
		getContentPane().add(lblLastName);
		
		firstNameTextField = new JTextField();
		firstNameTextField.setBounds(161, 104, 116, 22);
		getContentPane().add(firstNameTextField);
		firstNameTextField.setColumns(10);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setBounds(161, 156, 116, 22);
		getContentPane().add(lastNameTextField);
		lastNameTextField.setColumns(10);
		
		JLabel lblCreateAccount = new JLabel("Create an Account");
		lblCreateAccount.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		lblCreateAccount.setBounds(101, 27, 240, 42);
		getContentPane().add(lblCreateAccount);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblUsername.setBounds(59, 208, 96, 16);
		getContentPane().add(lblUsername);
		
		userNameTextField = new JTextField();
		userNameTextField.setBounds(161, 206, 116, 22);
		getContentPane().add(userNameTextField);
		userNameTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPassword.setBounds(90, 254, 84, 16);
		getContentPane().add(lblPassword);
		
		JLabel lblConfirmYourPassword = new JLabel("Confirm password:");
		lblConfirmYourPassword.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblConfirmYourPassword.setBounds(240, 254, 163, 16);
		getContentPane().add(lblConfirmYourPassword);
		
		JButton btnCreateAccount = new JButton("Create Account");

		btnCreateAccount.setBounds(59, 343, 128, 25);
		getContentPane().add(btnCreateAccount);
		
		JButton btnReturnToLogin = new JButton("Return to Login");
		
		btnReturnToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				openLogin();
				
			}
		});
		btnReturnToLogin.setBounds(213, 343, 153, 25);
		getContentPane().add(btnReturnToLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(64, 283, 110, 22);
		getContentPane().add(passwordField);
		
		passwordFieldConfirm = new JPasswordField();
		passwordFieldConfirm.setBounds(240, 283, 116, 22);
		getContentPane().add(passwordFieldConfirm);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 82, 432, 9);
		getContentPane().add(separator);
		
		passwordFieldConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				user = new File("C:\\Users\\Public\\Documents\\Koutha Bank\\" + userNameTextField.getText() + ".txt");
				String firstName = firstNameTextField.getText();
				String lastName = lastNameTextField.getText();
				String username = userNameTextField.getText();
				char[] passChars = passwordField.getPassword();
				char[] passConfirms = passwordFieldConfirm.getPassword();
				String password = "";
				String passwordConfirm = "";
				
				for(char i: passChars){
					
					password += i;
					
				}
				
				for(char h: passConfirms){
					
					passwordConfirm += h;
					
				}
				
				boolean goodToCreateAccount = true;
				
				try{
					
					if(firstNameTextField.getText().trim().equals("") || lastNameTextField.getText().trim().equals("") || userNameTextField.getText().trim().equals("")|| 
							password.trim().equals("") || passwordConfirm.trim().equals("") ){
						
						JOptionPane.showMessageDialog(null, "Please make sure all boxes are filled out!");
						goodToCreateAccount = false;
						
					}else if(!password.equals(passwordConfirm)){
						
						JOptionPane.showMessageDialog(null, "Your passwords did not match! Please try again");
						goodToCreateAccount = false;
						
					}
					
					if(username.equals("vkouthaADMIN") && password.equals("vkouthaADMIN")){
						
						JOptionPane.showMessageDialog(null, "That username has already been taken!", "Username taken!", JOptionPane.ERROR_MESSAGE);
						goodToCreateAccount = false;
						
					}
					
					
					
					if(user.exists() == false && goodToCreateAccount == true){
						
						user.createNewFile();
						
						try{
							
							fWriter = new FileWriter(user);
							fW = new BufferedWriter(fWriter);
							
							fWriter.write("First Name: " + firstName + System.lineSeparator() + "Last Name: " + lastName + System.lineSeparator() + "Username: " + username + System.lineSeparator() + "Password: " + password + 
									System.lineSeparator() + "Balance: 0.00" + System.lineSeparator() + "Account created on: " + getDate() + 
									System.lineSeparator() + "Last login: " + null);
							
							fWriter.flush();
		
							JOptionPane.showMessageDialog(null, "Your account has been created!");
														
							openLogin();
							
							
							
						}catch(Exception e1){
							
							e1.printStackTrace();
							
						}finally{
							
							try {
								
								fWriter.close();
								fW.close();

							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						
					}else if (user.exists() == true && goodToCreateAccount == true){
						
						JOptionPane.showMessageDialog(null, "That username already exists! Please create a new one!");
						
					}
					
				}catch(Exception e1){
					
					e1.printStackTrace();
					
				}
				
			}
		});
		
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				user = new File("C:\\Users\\Public\\Documents\\Koutha Bank\\" + userNameTextField.getText() + ".txt");
				String firstName = firstNameTextField.getText();
				String lastName = lastNameTextField.getText();
				String fullName = firstName + " " + lastName;
				String username = userNameTextField.getText();
				char[] passChars = passwordField.getPassword();
				char[] passConfirms = passwordFieldConfirm.getPassword();
				String password = "";
				String passwordConfirm = "";
				
				for(char i: passChars){
					
					password += i;
					
				}
				
				for(char h: passConfirms){
					
					passwordConfirm += h;
					
				}
				
				boolean goodToCreateAccount = true;
				
				try{
					
					if(firstNameTextField.getText().trim().equals("") || lastNameTextField.getText().trim().equals("") || userNameTextField.getText().trim().equals("")|| 
							password.trim().equals("") || passwordConfirm.trim().equals("") ){
						
						JOptionPane.showMessageDialog(null, "Please make sure all boxes are filled out!");
						goodToCreateAccount = false;
						
					}else if(!password.equals(passwordConfirm)){
						
						JOptionPane.showMessageDialog(null, "Your passwords did not match! Please try again");
						goodToCreateAccount = false;
						
					}
					
					
					
					if(user.exists() == false && goodToCreateAccount == true){
						
						user.createNewFile();
						
						try{
							
							fWriter = new FileWriter(user);
							fW = new BufferedWriter(fWriter);
							
							fWriter.write("First Name: " + firstName + System.lineSeparator() + "Last Name: " + lastName + System.lineSeparator() + "Username: " + username + System.lineSeparator() + "Password: " + password + 
									System.lineSeparator() + "Balance: 0.00" + System.lineSeparator() + "Account created on: " + getDate() + 
									System.lineSeparator() + "Last login: " + null);
							
							fWriter.flush();

							JOptionPane.showMessageDialog(null, "Your account has been created!");
														
							openLogin();
							
							
							
						}catch(Exception e1){
							
							e1.printStackTrace();
							
						}finally{
							
							try {
								
								fWriter.close();
								fW.close();

							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						
					}else if (user.exists() == true && goodToCreateAccount == true){
						
						JOptionPane.showMessageDialog(null, "That username already exists! Please create a new one!");
						
					}
					
				}catch(Exception e1){
					
					e1.printStackTrace();
					
				}
			}
		});
		
		
		
	}
	
	public String getDate(){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		
		Date date = new Date();
		
		return dateFormat.format(date);
		
	}
	
	
	public void openLogin(){
		
		login = new login();
		
		login.setSize(450, 300);
		login.setResizable(false);
		login.setVisible(true);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setLocationRelativeTo(null);
		
		dispose();
		
	}
	
}
