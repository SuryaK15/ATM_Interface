package bank_work;

import java.util.*;


public class AccountService {
    private Map<String, Account> accounts;
    private List<String> transferTransactions;
    public AccountService() {
        accounts = new HashMap<>();
        accounts.put("user1", new Account("user1","1234","Surya",1000));
        accounts.put("user2", new Account("user2","5678","Spandana",2000));
    }
    public boolean isValid(String userId) {
        return accounts.containsKey(userId);
    }
    public Account getAccount(String userId){
        return accounts.get(userId);
    }
    
    public double checkBalance(String userId) {
    	
    	Account account = getAccount(userId);
    	return account.getBalance();
    	
    }
    public boolean transfer(String fromUserId, String toUserId, double amount){
    	if(fromUserId == toUserId) {
    		return false;
    	}
    	else if(isValid(fromUserId) && isValid(toUserId)){
            Account fromAccount = accounts.get(fromUserId);
            Account toAccount = accounts.get(toUserId);
            if(fromAccount.getBalance() >= amount){
                fromAccount.setBalance(fromAccount.getBalance()-amount);
                toAccount.setBalance(toAccount.getBalance()+amount);
                return true;
            }
        }
        return false;
    }
    public List<String> getTransferTransactions() {
        return transferTransactions;
    }
    
  public void withdraw(String userId, double amount) {
  Account account = getAccount(userId);
  if (account != null) {
      account.setBalance(account.getBalance() - amount);
      account.addTransaction("Withdraw", amount);
  }
}


public void deposit(String userId, double amount) {
  Account account = getAccount(userId);
  if (account != null) {
      account.setBalance(account.getBalance() + amount);
      account.addTransaction("Deposit", amount);
  }
}

public String getTransactions(String userId) {
  Account account = getAccount(userId);
  if (account != null) {
      return account.getTransactions();
  }
  return "No transactions found for this user";
}
public boolean isValid(String userId, String pin){
    if(accounts.containsKey(userId)){
        Account account = accounts.get(userId);
        if(account.getPin().equals(pin)){
            return true;
        }
    }
    return false;
}
}


