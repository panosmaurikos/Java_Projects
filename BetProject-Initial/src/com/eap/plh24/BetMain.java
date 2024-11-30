package com.eap.plh24;

public class BetMain {

	public static void main(String[] args) {
		BetOrganization bo = BetOrganization.getInstance();
		//Κατά την προσομοίωση ΔΕΝ ζητούνται δεδομένα εισόδου στο πρόγραμμα
		//Όλα τα δεδομένα είναι hardcoded μέσα στον κώδικά σας
		//1.Δημιουργία διαθέσιμων στοιχημάτων για το ποδόσφαιρο

		//2.Δημιουργία διαθέσιμων στοιχημάτων για το μπάσκετ
		FootballBet fb1 = new FootballBet("Olympiakos-Panathinaikos", 1.5);
		FootballBet fb2 = new FootballBet("AEK-PAOK", 2.0);
		FootballBet fb3 = new FootballBet("ofi-lamia", 2);
		BasketballBet bb1 = new BasketballBet("USA-germania", 1.8);
		BasketballBet bb2 = new BasketballBet("ethnikiellados-UK", 1.7);

		bo.addBet(fb1);
		bo.addBet(fb2);
		bo.addBet(fb3);
		bo.addBet(bb1);
		bo.addBet(bb2);

		//3.Δημιουργία παιχτών
		Customer customer1 = new Customer("Tony di Naples");
		GoldCustomer customer2 = new GoldCustomer("takis o magkas");
		PlatinumCustomer customer3 = new PlatinumCustomer("Babis Sougias");
		//Customer customer4 = new Customer("giannis");
		//GoldCustomer customer5 = new GoldCustomer("makis");

		//4.Δημιουργία στοιχημάτων των παιχτών
		CustomerBet bet1 = new CustomerBet("Olympiakos-Panathinaikos", "Football", 100, "X");
		CustomerBet bet2 = new CustomerBet("AEK-PAOK", "Football", 500, "2");
		CustomerBet bet3 = new CustomerBet("USA-germania", "Basketball", 2000, "1");
		CustomerBet bet4 = new CustomerBet("ethnikiellados-UK", "Basketball", 134, "2");
		CustomerBet bet5 = new CustomerBet("ofi-lamia", "Football", 200, "1");

		customer1.addCustomerBet(bet1);
		customer2.addCustomerBet(bet2);
		customer3.addCustomerBet(bet3);
		customer3.addCustomerBet(bet1);
		customer3.addCustomerBet(bet4);
		customer1.addCustomerBet(bet4);
		customer2.addCustomerBet(bet4);
		customer3.addCustomerBet(bet5);
		//System.out.println(customer1.getCustomerBetList());
		//System.out.println(customer2.getCustomerBetList());
		//System.out.println(customer3.getCustomerBetList());

		// 5. Προσθήκη παικτών στο σύστημα
		bo.addCustomer(customer1);
		bo.addCustomer(customer2);
		bo.addCustomer(customer3);

		// 6. Προσομοίωση αγώνων
		GameEmulator gameEmulator = GameEmulator.getInstance();
		gameEmulator.doGameEmulation();

		// 7. Προβολή αποτελεσμάτων
		bo.showCustomersResults();

		// 8. file print
		bo.printCustomersResultsToTextFile();
	}
}
