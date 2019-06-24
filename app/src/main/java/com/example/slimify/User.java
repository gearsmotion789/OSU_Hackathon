package com.example.slimify;

public class User {
    private String mname;
    private String mweight;

    public User(String name, String weight) {
        mname = name;
        mweight = weight;
    }

    public String getName() {
        return mname;
    }

    public String getWeight() {
        return mweight;
    }
}
