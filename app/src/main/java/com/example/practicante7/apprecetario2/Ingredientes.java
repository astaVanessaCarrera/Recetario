package com.example.practicante7.apprecetario2;

public class Ingredientes {

    private int mImage; // Variable declaration

//The constructor is created to be able to initialize the image variable
    public Ingredientes(int imagen) {
        this.mImage = imagen;
    }

//The getter method that returns the image in the activity is created
    public int getImage() {
        return mImage;
    }
}
