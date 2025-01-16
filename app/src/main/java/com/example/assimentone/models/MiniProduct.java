package com.example.assimentone.models;

public class MiniProduct
{
    private String aType;
    private int amount;

    public MiniProduct(String aType, int amount) {
        this.aType = aType;
        this.amount = amount;
    }

    public String getaType() {
        return aType;
    }

    public void setaType(String aType) {
        this.aType = aType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
