package com.BankingManagementSystem.frameDesign;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

import com.BankingManagementSystem.FileHandling.CustomerDetailsFile;
import com.BankingManagementSystem.FileHandling.TransactionDetailsFile;
import com.BankingManagementSystem.Pojo.CustomerDetails;
import com.BankingManagementSystem.Pojo.TransactionSummary;
import com.BankingManagementSystem.ValidationChecking.EmailValid;

public class WithdrawalFrame {
	
	ArrayList<CustomerDetails> userlist = CustomerDetailsFile.readDataFromFile();
	CustomerDetails r;
	private JTextField tdel;
	private JPanel contentPane;
	private JLabel labelName, labelAccNo, labelAmount, lblMoneyWithdrawal, lblAccountNumber,lblName ;
	private JButton bmanager;
	private
	
	int accNo;
	 public WithdrawalFrame(int index) {
		 JFrame frame = new JFrame("WITHDRAWAL");
		 frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ManagerLoginPage.class.getResource("/resources/withdraw-of-a-safe-box.png")));
	        accNo = index;
	        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	        
            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    int result = JOptionPane.showConfirmDialog(
                            frame, "Are you sure?","Exit",JOptionPane.INFORMATION_MESSAGE);
                    if( result==JOptionPane.OK_OPTION){
                        // NOW we change it to dispose on close..
                        frame.setDefaultCloseOperation(
                                JFrame.DISPOSE_ON_CLOSE);
                        frame.setVisible(false);
                        frame.dispose();
                        new TransactionFrame(null);
                    }
                }
            });
            contentPane = new JPanel();
	        contentPane.setOpaque(true);
	        contentPane.setBackground(new Color(255, 255, 255));
	        contentPane.setLayout(null);
	        
	        
	        CustomerDetails customerDetails = new CustomerDetails();
	        labelName = new JLabel(userlist.get(index).getCname(), JLabel.CENTER);
	        labelName.setToolTipText("Name of the Customer");
	        Font f1=new Font("comic sans ms",Font.BOLD,48);
	        labelName.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
	        labelName.setForeground(new Color(47, 79, 79));
	        labelName.setSize(312,37);;
	        labelName.setLocation(250,306);
	        contentPane.add(labelName);
	        
	       labelAccNo = new JLabel(userlist.get(index).getAccountNo(), JLabel.CENTER);
	        labelAccNo.setToolTipText("Account Number");
	        Font f2=new Font("comic sans ms",Font.BOLD,48);
	        labelAccNo.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
	        labelAccNo.setForeground(new Color(47, 79, 79));
	        labelAccNo.setSize(312,42);
	        labelAccNo.setLocation(250,221);
	        contentPane.add(labelAccNo);
	        
	        labelAmount = new JLabel("Amount :", JLabel.CENTER);
	        Font f3=new Font("comic sans ms",Font.BOLD,48);
	        labelAmount.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
	        labelAmount.setForeground(Color.RED);
	        labelAmount.setSize(200,50);
	        labelAmount.setLocation(50,378);
	        contentPane.add(labelAmount);
	        
	        
	       tdel = new JTextField();
	        tdel.setToolTipText("Enter amount to be withdraw");
	        tdel.setForeground(new Color(47, 79, 79));
	        tdel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
	        Font f5=new Font("Comic Sans MS", Font.BOLD, 22);
	        tdel.setFont(f5);
	        tdel.setSize(312,42);
	        tdel.setLocation(250,387);
	        contentPane.add(tdel);
	        
	       bmanager = new JButton("Confirm");
	        bmanager.setToolTipText("Confirmation");
	        Font f4=new Font("comic sans ms",Font.BOLD,22);
	        bmanager.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
	        bmanager.setForeground(new Color(34, 139, 34));
	        bmanager.setSize(200,72);
	        bmanager.setLocation(337,479);
	        bmanager.setFocusable(false);
	        contentPane.add(bmanager);
	        bmanager.addActionListener(new ActionListener() 
	        {
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					try{
					int amt=Integer.parseInt(tdel.getText().trim());
					if(amt != 0)
					{
					if(amt < userlist.get(accNo).getBalance())
					{
						int result = JOptionPane.showConfirmDialog(
                            frame, "Are you sure?","Confirmation",JOptionPane.INFORMATION_MESSAGE);
						if( result==JOptionPane.OK_OPTION)
						{
							withdrawMoney();
							//EmailValid obj=new EmailValid();
							//obj.Email(userlist.get(accNo).getAccountNo());
							
							frame.setVisible(false);
							TransactionFrame ob = new TransactionFrame(null);
							ob.setVisible(true);
					
						}
					}
					else if(amt >= userlist.get(accNo).getBalance())
						JOptionPane.showMessageDialog(null,"Insufficient balance");
					}
					
					else 
						JOptionPane.showMessageDialog(null,"Enter a valid amount");
					
					}catch (Exception z) {
						JOptionPane.showMessageDialog(null,"Enter a valid amount");
					}
				}
			   
				
	        }
				
	        );
			
			
	        frame.setContentPane(contentPane);
	       lblMoneyWithdrawal = new JLabel("MONEY WITHDRAWAL", SwingConstants.CENTER);
	        lblMoneyWithdrawal.setForeground(new Color(30, 144, 255));
	        lblMoneyWithdrawal.setFont(new Font("Comic Sans MS", Font.BOLD, 48));
	        lblMoneyWithdrawal.setBounds(10, 11, 564, 90);
	        contentPane.add(lblMoneyWithdrawal);
	        
	        lblAccountNumber = new JLabel("Account No :", SwingConstants.CENTER);
	        lblAccountNumber.setForeground(Color.RED);
	        lblAccountNumber.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
	        lblAccountNumber.setBounds(10, 217, 234, 50);
	        contentPane.add(lblAccountNumber);
	        
	        lblName = new JLabel("Name :", SwingConstants.CENTER);
	        lblName.setForeground(Color.RED);
	        lblName.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
	        lblName.setBounds(108, 299, 142, 50);
	        contentPane.add(lblName);
	        frame.setSize(600,700);
	        frame.setLocationByPlatform(false);
	        frame.setVisible(true);
	        frame.setResizable(false);
	    }

	 public void withdrawMoney() {
		 if(accNo >= 0)
         {
        	 
        	 userlist.get(accNo).setBalance(userlist.get(accNo).getBalance() - Double.parseDouble(tdel.getText().trim()) );
        	 
        	 TransactionSummary ts = new TransactionSummary();
           	 ts.setAccNo(userlist.get(accNo).getAccountNo());
           	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        	 LocalDateTime now = LocalDateTime.now();
           	 ts.setDateAndTime(dtf.format(now));
           	 ts.setWithdrawal(Double.parseDouble(tdel.getText().trim()));
           	 ts.setDeposite(0.0);
           	ts.setBalance(userlist.get(accNo).getBalance());
           	 ArrayList<TransactionSummary> trans = new ArrayList<TransactionSummary>();
           	 
           	 trans =  TransactionDetailsFile.readDataFromFile();
           	 trans.add(ts);
           	 
           	 TransactionDetailsFile.writeDatatoFile(trans);
           	 
        	 
        	 CustomerDetailsFile.writeDatatoFile(userlist);
        	 String message = "Thank you for using Bank India International, \n"+tdel.getText().trim()+" Rupees is debited from your account \n";
			 
        	 message = message+userlist.get(accNo).getAccountNo() + " Your current balance is "+userlist.get(accNo).getBalance()+"Rupees";
        	 
        	 EmailValid obj=new EmailValid();
				obj.Email(message,userlist.get(accNo).getAccountNo());
        	 
        	 JOptionPane.showMessageDialog(tdel, "withdrawal complete");
         }
         else
         {
            JOptionPane.showMessageDialog(tdel, "Invalid Account number");
         }
			
		}

	   /* public static void main(String... args)
	    {
	        SwingUtilities.invokeLater(new Runnable()
	        {
	            public void run()
	            {
	                new WithdrawalFrame();
	            }
	        });
	    }*/

}
