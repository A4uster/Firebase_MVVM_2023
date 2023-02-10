package com.example.firebase_mvvm_2023;

public class Pizza {
    private String mIngredients,mNombre, mPrecio,mUid;
    public Pizza(String nom, String ingredients, String precio, String uid){
        mNombre = nom;
        mIngredients = ingredients;
        mPrecio = precio;
        mUid = uid;
    }
    public String toString(){
        return mNombre + " - " + mIngredients + " - " + mPrecio;
    }

    public void setmUid(String mUid) {this.mUid = mUid;}
    public void setmPrecio(String mPrecio) {this.mPrecio = mPrecio;}
    public void setmIngredients(String mIngredients) {this.mIngredients = mIngredients;}
    public void setmNombre(String mNombre) {this.mNombre = mNombre;}

    public String getmUid() {return mUid;}
    public String getmPrecio() {return mPrecio;}
    public String getmIngredients() {return mIngredients;}
    public String getmNombre() {return mNombre;}
}
