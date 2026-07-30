package com.atm;

public interface AtmHiding {

	public void depositMoney(String account_number);
	public void withdrawAmount(String account_number);
	public void checkBalance(String account_number);
	public void transferAmount(String account_number);
	public void transactionHistory(String account_number);
	
}
