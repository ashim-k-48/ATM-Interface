import java.util.*;
class User{
	String name;
	int pin;
	public User(String name,int pin){
		this.name = name;
		this.pin = pin;
	}
}
class ATM{
	float balance;
	int maxAttempt = 5;
	int attemptCount = 0;
	final int withdrawlAmount = 25000;
	List<User> users = new ArrayList<>();
	public ATM(){
		users.add(new User("Ashim Khan", 5577));
		users.add(new User("Ahmad Fahad", 1212));
		users.add(new User("Adil Husain", 5555));
		users.add(new User("Mohd. Mubasshir", 6666));
	}
	private User validatePin(int pin){
		for(User user : users){
			if(user.pin == pin){
				return user;
			}
		}
		return null;
	}
	public void checkPin(){
		while (attemptCount < maxAttempt){
			System.out.println("Enter your Pin: ");
			Scanner sc=new Scanner(System.in);
			int enteredPin = sc.nextInt();
			attemptCount++;
			User currentUser = validatePin(enteredPin);
			if (currentUser != null){
				System.out.println("Hello "+currentUser.name+"!");
				menu(currentUser);
			}
			else{
				if(attemptCount < maxAttempt ){
					System.out.println("Enter Correct Pin.");
				}
				else{
					System.out.println("Too Many Incorrect attempts, Access Denied.");
				}
			}
		}
	}
	public void menu(User currentUser){
		System.out.println("Enter Your Choice.");
		System.out.println("1. Balance Enquiry.");
		System.out.println("2. Money Withdraw.");
		System.out.println("3. Deposit Money.");
		System.out.println("4. Exit.");
		Scanner sc = new Scanner(System.in);
		int opt= sc.nextInt();
		if(opt == 1){
			balanceEnquiry(currentUser);
		}
		else if(opt == 2){
			moneyWithdraw(currentUser);
		}
		else if(opt == 3){
			depositMoney(currentUser);
		}
		else if(opt == 4){
			System.out.println("Thanks for using our service "+currentUser.name+". Goodby!");
			checkPin();
		}
		else{
			System.out.println("Enter a valid choice.");
		}
		menu(currentUser);
	}
	public void balanceEnquiry(User currentUser){
		System.out.println("Balance: "+balance);
		menu(currentUser);
	}
	public void moneyWithdraw(User currentUser){
		System.out.println("Enter Amount: ");
		Scanner sc = new Scanner(System.in);
		float amount = sc.nextFloat();
		if(amount > withdrawlAmount){
			System.out.println("Maximum withdrawl amount is: "+withdrawlAmount);
			moneyWithdraw(currentUser);
		}
		else if(amount <=0){
			System.out.println("Invalid amount");
			moneyWithdraw(currentUser);
		}
		else if(amount > balance){
                        System.out.println("Insufficient Balance.");
                }

		else if(isValidAmount(amount)){
			balance = balance - amount;
			System.out.println("INR "+amount+" Withdrawl Successful.");
		}
		else{
			System.out.println("Please enter an amount that is a multiple of 100, 200, or 500.");
			moneyWithdraw(currentUser);
		}
		menu(currentUser);
	}
	private boolean isValidAmount(float amount){
		return (amount % 100 == 0 || amount % 200 == 0 || amount % 500 == 0);
	}
	public void depositMoney(User currentUser){
		System.out.println("Enter Amount: ");
		Scanner sc = new Scanner(System.in);
		float amount = sc.nextFloat();
		balance = balance + amount;
		System.out.println(" INR "+amount+" Deposit Successful.");
		menu(currentUser);
	}
}
public class Main{
	public static void main(String[] args){
		System.out.println("                NBI Welcome's You           ");
		System.out.println("                                            ");
		ATM atm = new ATM();
		atm.checkPin();
	}
}
