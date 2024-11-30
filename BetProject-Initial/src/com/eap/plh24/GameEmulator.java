package com.eap.plh24;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

//Η συγκεκριμένη κλάση χρησιμοποιείται κατά την εκτέλεση του προγράμματος, για την τυχαία παραγωγή των αποτελεσμάτων των αγώνων.
public class GameEmulator {
    private static GameEmulator instance;

    //Αντικείμενο που μπορεί να χρησιμοποιηθεί για την παραγωγή τυχαίων αριθμών
    Random r = new Random();
	//Η συγκεκριμένη δομή HashMap θα μας βοηθήσει να αντιστοιχήσουμε κάθε αγώνα με ένα αποτέλεσμα.
	//Συγκεκριμένα, για κάθε μοναδικό όνομα αγώνα (πρώτη παράμετρος String),
	//θα αντιστοιχούμε ένα μονάδικο αποτέλεσμα (1-Χ-2) ή (1-2), ανάλογα με το είδος του αγώνα (ποδόσφαιρο ή μπάσκετ)
    private final Map<String,String> emulatedGamesResults = new HashMap<>();
    //Διαθέσιμες επιλογές για ποδόσφαιρο
    private final String[] footballChoices = FootballBet.getChoices().toArray(new String[0]);
    //Διαθέσιμες επιλογές για μπάσκετ
    private final String[] basketballChoices = BasketballBet.getChoices().toArray(new String[0]);
	//Η μέθοδος αυτή παράγει τα δεδομένα του HashMap "emulatedGamesResults"
	//π.χ. ότι στον αγώνα ποδοσφαίρου "Βραζιλία-Ν.Κορέα" αντιστοιχεί το αποτέλεσμα "1"
    // Private constructor για το singleton pattern
    private GameEmulator() {}
    // Μέθοδος singleton pattern
    public static GameEmulator getInstance() {
        if (instance == null) {
            synchronized (GameEmulator.class) {
                if (instance == null) {
                    instance = new GameEmulator();
                }
            }
        }
        return instance;
    }

    public void doGameEmulation() {
        // παίρνω τη λίστα με τα διαθέσιμα στοιχήματα από την BetOrganization
        BetOrganization betOrganization = BetOrganization.getInstance();
        List<Bet> bets = betOrganization.getBetList();
        // Διαγράφω τα προηγούμενα αποτελέσματα εάν υπάρχουν
        emulatedGamesResults.clear();
        // Δημιουργία τυχαίων αποτελεσμάτων για κάθε παιχνίδι
        for (Bet bet : bets) {
            String gameName = bet.getGame();
            // Παράλειψη εάν έχουμε ήδη αποτέλεσμα για αυτό το παιχνίδι
            if (emulatedGamesResults.containsKey(gameName)) {
                continue;
            }
            // Δημιουργούμε το αποτέλεσμα με βάση τον τύπο στοιχήματος
            String result;
            if (bet instanceof FootballBet) {
                result = footballChoices[r.nextInt(footballChoices.length)];
            } else if (bet instanceof BasketballBet) {
                result = basketballChoices[r.nextInt(basketballChoices.length)];
            } else {
                continue;
            }
            // Αποθήκευση των αποτελεσμάτων
            emulatedGamesResults.put(gameName, result);
        }
    }


    public Map<String, String> getEmulatedGamesResults() {
        return emulatedGamesResults;
    }
}
