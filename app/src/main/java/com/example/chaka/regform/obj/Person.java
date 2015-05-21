package com.example.chaka.regform.obj;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chaka on 20/05/2015.
 */
public class Person implements Parcelable{

    String name;
    String ni;
    String passport;
    String gender;
    Bitmap image;

    public Person() {
    }

    public Person(String name, String ni, String passport, String gender) {
        this.name = name;
        this.ni = ni;
        this.passport = passport;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNi() {
        return ni;
    }

    public void setNi(String ni) {
        this.ni = ni;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(ni);
        dest.writeString(passport);
        dest.writeString(gender);
        //dest.writeString(image);
    }

    /*
     * Retrieving Person data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object CREATOR
     */
    private Person(Parcel in) {
        this.name = in.readString();
        this.ni = in.readString();
        this.passport = in.readString();
        this.gender = in.readString();
        //this.image = in.readString();
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {

        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
