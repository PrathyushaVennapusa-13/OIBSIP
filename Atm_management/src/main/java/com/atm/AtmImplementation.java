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
	
	

	@Override
	public void depositMoney(String account_number) {
	
		System.out.println("enter the deposit amount");
		double deposit_money =scanner.nextDouble();
		if (deposit_money <= 0) {
		    System.out.println("Enter a valid deposit amount.");
		    return;
		}
		String query ="update account set  balance = balance+? where account_number=?";
		try(Connection connection =db.createConnection();
				PreparedStatement updatePreparedStatement =connection.prepareStatement(query);)
		{
			updatePreparedStatement.setDouble(1,deposit_money);
			updatePreparedStatement.setString(2, account_number);
			int rows = updatePreparedStatement.executeUpdate();

			if(rows > 0)
			{
			    System.out.println("Amount deposited successfully.");
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void checkBalance(String account_number) {
		String query ="select balance from account where account_number =? ";
		
		try(Connection connection =db.createConnection();
		PreparedStatement fetchPreparedStatement =connection.prepareStatement(query);) 
		{
			fetchPreparedStatement .setString(1, account_number);
			ResultSet rs1 =fetchPreparedStatement.executeQuery();

			if(rs1.next())
			{
			    System.out.println("Balance : " + rs1.getDouble("balance"));
			}
				
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();	
		}
	}

	@Override
	public void withdrawAmount(String account_number) {
		
		System.out.println("Enter the amount you want to with draw");
		double withdraw =scanner.nextDouble();
		if (withdraw <= 0) {
	        System.out.println("Enter a valid withdrawal amount.");
	        return;
	    }

		String query = "SELECT balance FROM account WHERE account_number = ?";
		String query1 ="update account set balance=balance-? where  account_number=? ";
		try (Connection connection =db.createConnection();
		PreparedStatement fetchPreparedStatement =connection.prepareStatement(query);
				  PreparedStatement withdrawPreparedStatement =connection.prepareStatement(query1);)
		{
			fetchPreparedStatement.setString(1, account_number);

			try(ResultSet rs = fetchPreparedStatement.executeQuery() ;)
			{
			if(rs.next())
			{
				double balance =rs.getDouble("balance");
			  
		       if(balance>=withdraw)
			   {
			      withdrawPreparedStatement.setDouble(1, withdraw);
		          withdrawPreparedStatement.setString(2, account_number);
			      int rows =withdrawPreparedStatement.executeUpdate();
			      if(rows!=0)
			         {
				         System.out.println("Amount withdrawn successfully.");
			         }
			   
			   }
			   else
			   {
				   System.out.println("There is no sufficient balance to withdraw");
			   }
			}
			
			
		}
	} 
	catch (SQLException e)
	{
			
			e.printStackTrace();
	}
		
}
	

	

	
	

	
		@Override
		public void transferAmount(String account_number) {

		    System.out.println("Enter amount");
		    double amount = scanner.nextDouble();

		    System.out.println("Enter receiver account number");
		    String receiverAccount = scanner.next();

		    System.out.println("Enter receiver phone number");
		    long receiverPhone = scanner.nextLong();

		    String debitQuery = "UPDATE account SET balance = balance - ? " +
		                        "WHERE account_number = ?  AND balance >= ?";

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
		        debitStmt.setString(2, account_number);
		        debitStmt.setDouble(3, amount);

		        int debit = debitStmt.executeUpdate();

		        // Credit Receiver
		        creditStmt.setDouble(1, amount);
		        creditStmt.setString(2, receiverAccount);
		        creditStmt.setLong(3, receiverPhone);

		        int credit = creditStmt.executeUpdate();

		        if (debit > 0 && credit > 0) {

		            // Save transaction history
		            historyStmt.setString(1, account_number);
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
		public void transactionHistory(String account_number) {


		    String query = "SELECT * FROM transaction_history " +
		                   "WHERE sender_account = ? OR receiver_account = ? " +
		                   "ORDER BY transaction_time DESC";

		    try (Connection connection = db.createConnection();
		         PreparedStatement pstmt = connection.prepareStatement(query)) {

		        pstmt.setString(1, account_number);
		        pstmt.setString(2, account_number);

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
