package com.eap.plh24;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BetOrganization {
	//Λίστα με τους διαθέσιμους παίχτες
	private final List<Customer> cList = new ArrayList<>();
	//Λίστα με τα διαθέσιμα στοιχήματα για αγώνες ποδοσφαίρου και μπάσκετ
	private final List<Bet> betList = new ArrayList<>();
	// getInstance
	private static BetOrganization instance;

	public static BetOrganization getInstance() {
		if (instance == null) {        // for thread-safe use double-checked locking
			synchronized (BetOrganization.class) {
				if (instance == null) {
					instance = new BetOrganization();
				}
			}
		}
		return instance;
	}

	public boolean checkCustomerBetInList(CustomerBet customerBet) {
		return betList.stream()
				.anyMatch(bet -> bet.getGame().equals(customerBet.getBetName()));
	}

	public List<Bet> getBetList() {
		return betList;
	}

	public void addCustomer(Customer customer) {
		cList.add(customer);
	}

	public void addBet(Bet bet) {
		betList.add(bet);
	}

	//Η μέθοδος υπολογίζει τα κέρδη του παίχτη που δίδεται ως παράμετρός της.
	//Πιο συγκεκριμένα, η παράμετρος αφορά στην λίστα στοιχημάτων του εκάστοτε παίχτη
	//Για κάθε ένα στοίχημα που έχει κάνει ο παίχτης
	//Ψάχνουμε να το αντιστοιχήσουμε με τη λίστα των στοιχημάτων του BetOrganization
	//Στη συνέχεια, εφόσον το βρούμε, κοιτάζουμε αν έχει κερδίσει η επιλογή του παίχτη
	//και αν ναι, προσθέτουμε το ποσό στα κέρδη (επιστρεφόμενη τιμή της μεθόδου)

	private double calculateGainsPerCustomer(IGiveBetList customer) {
		double totalGains = 0.0;
		List<CustomerBet> customerBets = customer.getCustomerBetList();
		Map<String, String> gameResults = GameEmulator.getInstance().getEmulatedGamesResults();
		//System.out.println("Calculating gains for customer: ");

		for (CustomerBet customerBet : customerBets) {
			//System.out.println("Checking customer bet: " + customerBet.getBetName());

			for (Bet bet : betList) {
				//System.out.println("Found match in bet list for: " + bet.getGame());

				if (bet.getGame().equals(customerBet.getBetName())) {
					String actualResult = gameResults.get(customerBet.getBetName());
					//System.out.println("Actual result: " + actualResult);
					//System.out.println("Customer choice: " + customerBet.getChoice());
					if (actualResult != null && actualResult.equals(customerBet.getChoice())) {
						double betAmount = customerBet.getStake();
						double odds = bet.getOdd();
						totalGains += betAmount * odds;
						//System.out.println("Bet won! Stake: " + betAmount + ", Odds: " + odds );
					}else{
						//System.out.println("Bet lost.");
					}
					break;
				}
			}
		}
		//System.out.println("Total gains for customer: " + totalGains);

		return totalGains;
	}


	//print
	public void showCustomersResults() {
		System.out.println("------------------Results-------------------");
		Map<String, String> gameResults = GameEmulator.getInstance().getEmulatedGamesResults();
//		// ολα τα αποτελέσματα κατά άθλημα
//		System.out.println("Football Results:");
//		for (Bet bet : betList) {
//			if (bet instanceof FootballBet) {
//				String result = gameResults.get(bet.getGame());
//				System.out.println("Game: " + bet.getGame() + ", Result: " + result);
//			}
//		}
//		System.out.println("Basketball Results:");
//		for (Bet bet : betList) {
//			if (bet instanceof BasketballBet) {
//				String result = gameResults.get(bet.getGame());
//				System.out.println("Game: " + bet.getGame() + ", Result: " + result);
//			}
//		}
//		System.out.println("--------------------------------------------");
		//System.out.println("\nCustomer Results:");
		for (Customer customer : cList) {
			System.out.println("####################################");
			System.out.println("Customer name: " + customer.getName());
			System.out.println("Money played: " + customer.getMoneyPlayed());
			System.out.println("Total gains: " + calculateGainsPerCustomer(customer));

		}
		System.out.println("--------------End-of-Results----------------");
	}
	//Εγγραφή των αποτελεσμάτων των παιχτών στο αρχείο κειμένου "bet-results.txt"
	//Το αρχείο αυτό θα αντικαθίσταται από νέο αρχείο, κάθε φορά που εκτελείται το πρόγραμμα (δεν κρατάμε τα προηγούμενα δεδομένα)
	//Το format του αρχείου να είναι ίδιο με την εκτύπωση των αποτελεσμάτων (showCustomersResults)
	public void printCustomersResultsToTextFile() {
		try (FileWriter writer = new FileWriter("bet-results.txt")) {
			writer.write("------------------Results-------------------\n");
			for (Customer customer : cList) {
				writer.write("####################################\n");
				writer.write("Customer name: " + customer.getName() + "\n");
				writer.write("Customer money paid: " + customer.getMoneyPlayed() + "\n");
				writer.write("Customer gains: " + calculateGainsPerCustomer(customer) + "\n");
			}
			writer.write("--------------End-of-Results----------------\n");
		} catch (IOException e) {
			System.err.println("Error writing to file: " + e.getMessage());
		}
	}
}