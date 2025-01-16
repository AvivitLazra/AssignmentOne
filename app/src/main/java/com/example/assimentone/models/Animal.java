package com.example.assimentone.models;

public class Animal {

    private String aType;
    private int aImage;
    private String aDesc;


    public Animal(String aType, int aImage, String aDesc) {
        this.aType = aType;
        this.aImage = aImage;
        this.aDesc = aDesc;
    }



    public String getaType() {
        return aType;
    }

    public void setaType(String aType) {
        this.aType = aType;
    }

    public int getaImage() {
        return aImage;
    }

    public void setaImage(int aImage) {
        this.aImage = aImage;
    }

    public String getaDesc() {
        return aDesc;
    }

    public void setaDesc(String aDesc) {
        this.aDesc = aDesc;
    }
}
