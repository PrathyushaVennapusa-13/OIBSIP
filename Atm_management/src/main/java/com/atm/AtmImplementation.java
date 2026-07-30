package com.atm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AtmImplementation implements AtmHiding
{
	
	
	Scanner scanner = new Scanner(System.in);
	DatabaseConnection db = new DatabaseConnection();
	public void createAccount()
	{
		System.out.print("Enter Account Number: ");
		String accountNumber = scanner.next();

		scanner.nextLine(); // Consume leftover newline

		System.out.print("Enter Account Holder Name: ");
		String accountName = scanner.nextLine();

		System.out.print("Enter Phone Number: ");
		long phoneNumber = scanner.nextLong();

		if (String.valueOf(phoneNumber).length() != 10) {
		    System.out.println("Phone number must contain exactly 10 digits.");
		    return;
		}

		scanner.nextLine(); // Consume leftover newline

		System.out.print("Enter Bank Name: ");
		String bankName = scanner.nextLine();

		System.out.print("Enter Email: ");
		String email = scanner.next();

		if (!email.contains("@") || !email.contains(".")) {
		    System.out.println("Invalid email address.");
		    return;
		}

		System.out.print("Set 4-digit PIN: ");
		int pin = scanner.nextInt();

		if (pin < 1000 || pin > 9999) {
		    System.out.println("PIN must be exactly 4 digits.");
		    return;
		}

		String query ="Insert into account values(?,?,?,?,?,?,?)";
		try(Connection connection =db.createConnection();
		PreparedStatement insertPreparedStatement =connection.prepareStatement(query);) 
		{
			
			insertPreparedStatement.setString(1, accountName);
			insertPreparedStatement.setString(2,accountNumber);
			insertPreparedStatement.setLong(3, phoneNumber);
			insertPreparedStatement.setString(4,bankName);
			insertPreparedStatement.setString(5,email);
			insertPreparedStatement.setInt(6, pin);
			insertPreparedStatement.setDouble(7, 0.0);
			
			int rows =insertPreparedStatement.executeUpdate();
			if(rows!=0)
			{
				System.out.println("congratulations your account created in "+bankName+" and your account number is :"+accountNumber);
			}
			else
			{
				System.out.println("Sorry...Account not created please Try again");
			}
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		
		
		
	}

	@Override
	public void depositMoney() {
		System.out.println("Enter account number");
		String account_number =scanner.next();
		System.out.println("Enter pin");
		int pin_number =scanner.nextInt();
		System.out.println("enter the deposit amount");
		double deposit_money =scanner.nextDouble();
		String query ="update account set  balance = balance+? where account_number=? and pin =?";
		try(Connection connection =db.createConnection();
				PreparedStatement updatePreparedStatement =connection.prepareStatement(query);)
		{
			updatePreparedStatement.setDouble(1,deposit_money);
			updatePreparedStatement.setString(2, account_number);
			updatePreparedStatement.setInt(3, pin_number);
			
			int rows =updatePreparedStatement.executeUpdate();
			if(rows!=0)
			{
				System.out.println("amount deposited successfully ");
			}
			else
			{
				System.out.println("Enter your account number and pin correctly");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void checkBalance() {
		
		System.out.println("Enter account number");
		String account_number =scanner.next();
		System.out.println("Enter pin");
		int pin_number =scanner.nextInt();
		
		String query ="select * from account where account_number =? and pin =? ";
		
		try(Connection connection =db.createConnection();
		PreparedStatement fetchPreparedStatement =connection.prepareStatement(query);) 
		{
			fetchPreparedStatement .setString(1, account_number);
			fetchPreparedStatement .setInt(2, pin_number);
			ResultSet rs1 =fetchPreparedStatement.executeQuery();
				if(rs1.next())
				{
				    System.out.println("Balance : " + rs1.getDouble("balance"));
				} 
				else 
				{
				    System.out.println("Invalid account number or PIN");
				}
				
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();	
		}
	}

	@Override
	public void withdrawAmount() {
		System.out.println("Enter account number");
		String account_number =scanner.next();
		System.out.println("Enter pin");
		int pin_number =scanner.nextInt();
		System.out.println("Enter the amount you want to with draw");
		double withdraw =scanner.nextDouble();
		if (withdraw <= 0) {
	        System.out.println("Enter a valid withdrawal amount.");
	        return;
	    }

		String query ="select * from account where account_number =? and pin=?";
		String query1 ="update account set balance=balance-? where  account_number=? and pin =? ";
		try (Connection connection =db.createConnection();
		PreparedStatement fetchPreparedStatement =connection.prepareStatement(query);
				  PreparedStatement withdrawPreparedStatement =connection.prepareStatement(query1);)
		{
			fetchPreparedStatement.setString(1, account_number);
			fetchPreparedStatement.setInt(2, pin_number);

			try(ResultSet rs = fetchPreparedStatement.executeQuery() ;)
			{
			if(rs.next())
			{
				double balance =rs.getDouble("balance");
			  
		       if(balance>=withdraw)
			   {
			      withdrawPreparedStatement.setDouble(1, withdraw);
		          withdrawPreparedStatement.setString(2, account_number);
			      withdrawPreparedStatement.setInt(3, pin_number);
			      int rows =withdrawPreparedStatement.executeUpdate();
			      if(rows!=0)
			         {
				         System.out.println("Amount withdrawn successfully.");
			         }
			      else
			         {
			       	     System.out.println("Amount withdrawn Failed");
			         }
			   }
			   else
			   {
				   System.out.println("There is no sufficient balance to withdraw");
			   }
			}
			
			else
			{
				System.out.println("Enter proper details");
			}
		}
	} 
	catch (SQLException e)
	{
			
			e.printStackTrace();
	}
		
}
	

	@Override
	public void updateAccount() {
	
		System.out.println("Enter account number");
		String account_number =scanner.next();
		
		System.out.println("what you want to update");
		System.out.println("1.EMAIL\n2.PHONE NUMBER\n3.PIN\n4.BRANCH\n5.ACCOUNT NAME");
		int choice =scanner.nextInt();
		
		switch(choice)
		{
		case 1:
			System.out.println("Enter the new Email");
			String newEmail =scanner.next();
			String query ="update account set email=? where account_number =?";
			try(Connection connection =db.createConnection();
					PreparedStatement updatePreparedstatement =connection.prepareStatement(query);) 
			{
				updatePreparedstatement.setString(1, newEmail);
				updatePreparedstatement.setString(2, account_number);
				int rows =updatePreparedstatement.executeUpdate();
				if(rows!=0)
				{
					System.out.println("email updated successfully");
				}
				else
				{
					System.out.println("enter Account number properly");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			break;
			
		case 2:
			System.out.println("Enter the new Phone Number");
			Long newPhoneNumber =scanner.nextLong();
			String query1 ="update account set phone_number=? where account_number =?";
			try(Connection connection =db.createConnection();
					PreparedStatement updatePreparedstatement =connection.prepareStatement(query1);) 
			{
				updatePreparedstatement.setLong(1, newPhoneNumber);
				updatePreparedstatement.setString(2, account_number);
				int rows =updatePreparedstatement.executeUpdate();
				if(rows!=0)
				{
					System.out.println("Phone Number updated successfully");
				}
				else
				{
					System.out.println("enter Account number properly");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			break;

		case 3:
			System.out.println("Enter the new pin");
			int pin =scanner.nextInt();
			String query2 ="update account set pin=? where account_number =?";
			try(Connection connection =db.createConnection();
					PreparedStatement updatePreparedstatement =connection.prepareStatement(query2);) 
			{
				updatePreparedstatement.setInt(1, pin);
				updatePreparedstatement.setString(2, account_number);
				int rows =updatePreparedstatement.executeUpdate();
				if(rows!=0)
				{
					System.out.println("Pin updated successfully");
				}
				else
				{
					System.out.println("enter Account number properly");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			break;
		case 4:
			System.out.println("Enter the new bank Name");
			scanner.nextLine();
			String branchName =scanner.nextLine();
			
			String query3 ="update account set bank_name=? where account_number =?";
			try(Connection connection =db.createConnection();
					PreparedStatement updatePreparedstatement =connection.prepareStatement(query3);) 
			{
				updatePreparedstatement.setString(1,  branchName);
				updatePreparedstatement.setString(2, account_number);
				int rows =updatePreparedstatement.executeUpdate();
				if(rows!=0)
				{
					System.out.println("Branch Name updated successfully");
				}
				else
				{
					System.out.println("enter Account number properly");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			break;

		case 5:
			 
					System.out.println("Enter the new Account Name");
					scanner.nextLine();
					String accountName =scanner.nextLine();
					
					String query4 ="update account set account_name=? where account_number =?";
					try(Connection connection =db.createConnection();
							PreparedStatement updatePreparedstatement =connection.prepareStatement(query4);) 
					{
						updatePreparedstatement.setString(1,  accountName);
						updatePreparedstatement.setString(2, account_number);
						int rows =updatePreparedstatement.executeUpdate();
						if(rows!=0)
						{
							System.out.println("Account Name updated successfully");
						}
						else
						{
							System.out.println("enter Account number properly");
						}
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
			break;
			default:
				try {
				throw new InvalidDataException("Enter valid option");
				}
				catch(InvalidDataException ie)
				{
					ie.printStackTrace();
				}
		}
		
		}

	@Override
	public void displayDetails() {
		System.out.println("enter Account number");
		String account_number =scanner.next();
		String query ="select * from account where account_number=? ";
		try(Connection connection =db.createConnection();
				PreparedStatement fetchPreparedStatement =connection.prepareStatement(query);)
		{
			fetchPreparedStatement.setString(1,account_number );
			try(ResultSet resultset =fetchPreparedStatement.executeQuery();)
			{
				if(resultset.next())
				{
					System.out.println("Account Name :"+resultset.getString("account_name"));
					System.out.println("Bank Name: "+resultset.getString("bank_name"));
					System.out.println("Pin: " +resultset.getInt("pin"));
					System.out.println("email: "+resultset.getString("email"));
					System.out.println("phone Number: "+resultset.getLong("phone_number"));
					System.out.println("Balance: "+resultset.getDouble("balance"));
	
				}
				else
				{
					System.out.println("Enter proper Account Number");
				}
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	

	
		@Override
		public void transferAmount() {

		    System.out.println("Enter your account number");
		    String senderAccount = scanner.next();

		    System.out.println("Enter PIN");
		    int pin = scanner.nextInt();

		    System.out.println("Enter amount");
		    double amount = scanner.nextDouble();

		    System.out.println("Enter receiver account number");
		    String receiverAccount = scanner.next();

		    System.out.println("Enter receiver phone number");
		    long receiverPhone = scanner.nextLong();

		    String debitQuery = "UPDATE account SET balance = balance - ? " +
		                        "WHERE account_number = ? AND pin = ? AND balance >= ?";

		    String creditQuery = "UPDATE account SET balance = balance + ? " +
		                         "WHERE account_number = ? AND phone_number = ?";

		    String historyQuery = "INSERT INTO transaction_history " +
		                          "(sender_account, receiver_account, amount, status) " +
		                          "VALUES (?, ?, ?, ?)";

		    try (Connection connection = db.createConnection();
		         PreparedStatement debitStmt = connection.prepareStatement(debitQuery);
		         PreparedStatement creditStmt = connection.prepareStatement(creditQuery);
		         PreparedStatement historyStmt = connection.prepareStatement(historyQuery)) {

		        connection.setAutoCommit(false);

		        // Debit Sender
		        debitStmt.setDouble(1, amount);
		        debitStmt.setString(2, senderAccount);
		        debitStmt.setInt(3, pin);
		        debitStmt.setDouble(4, amount);

		        int debit = debitStmt.executeUpdate();

		        // Credit Receiver
		        creditStmt.setDouble(1, amount);
		        creditStmt.setString(2, receiverAccount);
		        creditStmt.setLong(3, receiverPhone);

		        int credit = creditStmt.executeUpdate();

		        if (debit > 0 && credit > 0) {

		            // Save transaction history
		            historyStmt.setString(1, senderAccount);
		            historyStmt.setString(2, receiverAccount);
		            historyStmt.setDouble(3, amount);
		            historyStmt.setString(4, "SUCCESS");

		            historyStmt.executeUpdate();

		            connection.commit();
		            System.out.println("Amount transferred successfully.");

		        } else {

		            connection.rollback();
		            System.out.println("Transaction failed.");

		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
		


		@Override
		public void transactionHistory() {

		    System.out.println("Enter your account number");
		    String accountNumber = scanner.next();

		    String query = "SELECT * FROM transaction_history " +
		                   "WHERE sender_account = ? OR receiver_account = ? " +
		                   "ORDER BY transaction_time DESC";

		    try (Connection connection = db.createConnection();
		         PreparedStatement pstmt = connection.prepareStatement(query)) {

		        pstmt.setString(1, accountNumber);
		        pstmt.setString(2, accountNumber);

		        ResultSet rs = pstmt.executeQuery();

		        System.out.println("---------------------------------------------------------------");
		        System.out.printf("%-5s %-15s %-15s %-10s %-20s %-10s%n",
		                "ID", "Sender", "Receiver", "Amount", "Time", "Status");
		        System.out.println("---------------------------------------------------------------");

		        boolean found = false;

		        while (rs.next()) {
		            found = true;

		            System.out.printf("%-5d %-15s %-15s %-10.2f %-20s %-10s%n",
		                    rs.getInt("transaction_id"),
		                    rs.getString("sender_account"),
		                    rs.getString("receiver_account"),
		                    rs.getDouble("amount"),
		                    rs.getTimestamp("transaction_time"),
		                    rs.getString("status"));
		        }

		        if (!found) {
		            System.out.println("No transaction history found.");
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	

}
