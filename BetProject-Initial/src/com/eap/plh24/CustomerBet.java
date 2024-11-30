package com.eap.plh24;

import java.util.List;

public class CustomerBet {
    //Λίστα που περιέχει τις δύο διαθέσιμες επιλογές αγώνα, ποδοσφαίρου και μπάσκετ αντίστοιχα
    private final List<String> availableBetTypes = List.of("Football", "Basketball");
    //Το μοναδικό όνομα του αγώνα
    //Κατά τη δημιουργία αντικειμένων της εν λόγω κλάσης, δεν ελέγχουμε αν το όνομα αγώνα που έδωσε το παίχτης είναι σωστό.
    //Αυτό θα ελεγχθεί αργότερα από την κλάση BetOrganization
    private String betName;
    //Το ποσό του πονταρίσματος σε ευρώ (χωρίς δεκαδικά)
    private int stake;
    //Η επιλογή πονταρίσματος του παίχτη. Όπως αναφέρθηκε και παραπάνω πρέπει να ελεγχθεί, ανάλογα με τον τύπο του αγώνα.
    //Οι διαθέσιμες εκβάσεις ενός αγώνα μπάσκετ είναι, 1:Νικήτρια η πρώτη ομάδα, 2:Νικήτρια η δεύτερη ομάδα
    //Οι διαθέσιμες εκβάσεις ενός αγώνα ποδοσφαίρου είναι, 1:Νικήτρια η πρώτη ομάδα, Χ:Ισοπαλία, 2:Νικήτρια η δεύτερη ομάδα
    private String choice;
    private String betType;
    //Η παράμετρος "betType" είναι ο τύπος του αγώνα. Μπορεί να πάρει μόνο μια εκ των 2 τιμών: "Football" ή "Basketball"
    //Η δοθείσα τιμή του String betType που δίνεται κατά την προσομοίωση θα ελέγχεται στον constructor της κλάσης CustomerBet
    //και παράλληλα θα ελέγχεται αν η επιλογή, "choice", αφορά στις διαθέσιμες επιλογές του εκάστοτε τύπου αγώνα.
    public CustomerBet(String betName, String betType, int stake, String choice) {
        // check for bet type and choice

        if (!availableBetTypes.contains(betType)) {
            throw new IllegalArgumentException("Invalid bet type");
        }

        if (betType.equals("Football")) {
            if (!choice.equals("1") && !choice.equals("X") && !choice.equals("2")) {
                throw new IllegalArgumentException("Invalid choice for football");
            }
        } else if (betType.equals("Basketball")) {
            if (!choice.equals("1") && !choice.equals("2")) {
                throw new IllegalArgumentException("Invalid choice for basketball");
            }
        }

        this.betName = betName;
        this.stake = stake;
        this.choice = choice;
        this.betType = betType;

    }
    // Getters
    public String getBetName() {
        return betName;
    }
    public int getStake() {
        return stake;
    }
    public String getChoice() {
        return choice;
    }
    public String getBetType() {
        return betType;
    }


    public String toString() {
        return "CustomerBet{" +
                "betName='" + betName + '\'' +
                ", stake=" + stake +
                ", choice='" + choice + '\'' +
                ", betType='" + betType + '\'' +
                '}';
    }
}
