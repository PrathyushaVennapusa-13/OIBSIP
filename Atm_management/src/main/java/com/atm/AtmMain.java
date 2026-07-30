package com.atm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AtmMain {

	public void atmOperations(Scanner scanner,DatabaseConnection databaseConnection,AtmHiding atm ,String account_number) {
		while (true) {
			System.out.println("1.DEPOSIT\n2.WITHDRAW\n3.CHECKBALANCE\n4.TRANSFER AMOUNT\n5.TRANSACTION HISTORY \n6. EXIT");

			
			System.out.println("CHOOSE AN OPTION:  ");
			int choice = scanner.nextInt();
           
			switch (choice) {
			case 1:
				atm.depositMoney(account_number);
				break;

			case 2:
				atm.withdrawAmount( account_number);
				break;

			case 3:
				atm.checkBalance(account_number);
				break;
			case 4:
				atm.transferAmount(account_number);
				break;
			case 5:
				atm.transactionHistory(account_number);
				break;
			case 6:

				System.out.println("THANK YOU");
				System.exit(choice);
			default:
				try {
					throw new InvalidDataException("Enter valid option");
				} catch (InvalidDataException ie) {
					ie.printStackTrace();
				}

			}

		}
	}

	public static void main(String[] args) {
		System.out.println("==================================");
		System.out.println("      WELCOME TO ATM PROJECT");
		System.out.println("==================================");
		Scanner scanner = new Scanner(System.in);
		DatabaseConnection databaseConnection = new DatabaseConnection();
		 AtmHiding atm = new AtmImplementation();
		int attempts = 3;
		while (attempts > 0) {
			System.out.print("Enter Account Number: ");
			String account_number = scanner.next();

			System.out.print("Enter PIN: ");
			int pin = scanner.nextInt();
			String query = "SELECT * FROM account WHERE account_number = ? AND pin = ?";
			try (Connection connection = databaseConnection.createConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(query);) {

				preparedStatement.setString(1, account_number);
				preparedStatement.setInt(2, pin);
				ResultSet resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {
					System.out.println("\nLogin Successful!");
					System.out.println("Welcome " + account_number);
					AtmMain atmMain = new AtmMain();
					atmMain.atmOperations(scanner,databaseConnection,atm,account_number);
					break;
				} else {
					attempts--;

					if (attempts > 0) {
						System.out.println("Invalid Account Number or PIN");
						System.out.println("Attempts Remaining: " + attempts);
					} else {
						System.out.println("Too many failed attempts.");
						System.out.println("Application Closed.");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
