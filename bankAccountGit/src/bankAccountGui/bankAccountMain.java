package bankAccountGui;

import java.io.File;

import javax.swing.JFrame;

public class bankAccountMain{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File bankDirectory = new File("C:\\Users\\Public\\Documents\\Koutha Bank");
		
		if(bankDirectory.exists() == false){
			
			try{
				
				bankDirectory.mkdir();
				
			}catch(Exception e){
				
				e.printStackTrace();
				
			}
			
		}else{
			
			
		}

		login login = new login();
		
		login.setSize(450, 300);
		login.setResizable(false);
		login.setVisible(true);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setLocationRelativeTo(null);
		
	}

}
