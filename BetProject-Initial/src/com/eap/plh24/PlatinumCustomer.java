package com.eap.plh24;

public class PlatinumCustomer extends Customer {

    public PlatinumCustomer(String n) {
        super(n);
    }
   // μέγιστο επιτρεπτό όριο πονταρίσματος 2000
    @Override
    public int getMaxStake() {
        return 2000;
    }
}