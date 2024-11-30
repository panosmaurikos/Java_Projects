package com.eap.plh24;

public class GoldCustomer extends Customer {

    public GoldCustomer(String n) {
        super(n);
    }
    // με μέγιστο επιτρεπτό όριο πονταρίσματος 1000
    @Override
    public int getMaxStake() {
        return 1000;
    }
}