package com.eap.plh24;

import java.util.List;
import java.util.Arrays;

public class BasketballBet extends Bet {

    private static final List<String> choices = Arrays.asList("1", "2");

    public BasketballBet(String game, double betAmount) {
        super(game, betAmount);
    }

    // Επιστρέφει τη λίστα των διαθέσιμων επιλογών για το μπασκετ
    public static List<String> getChoices() {
        return choices;
    }
}
