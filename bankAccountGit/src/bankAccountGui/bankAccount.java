package bankAccountGui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class bankAccount extends JFrame{
	
	private JTextField textField;
	private JPasswordField passwordField;

	public bankAccount(){
		
		super("Bank account");
		
		getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblUsername.setBounds(67, 99, 116, 16);
		getContentPane().add(lblUsername);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		textField.setBounds(175, 98, 116, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		
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
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "is not ready yut");
				
			}
		});
		
		loginButton.setBounds(96, 198, 97, 25);
		getContentPane().add(loginButton);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		btnCreateAccount.setBounds(216, 198, 131, 25);
		getContentPane().add(btnCreateAccount);
		
	}
}
