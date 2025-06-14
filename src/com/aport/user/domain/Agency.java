package com.aport.user.domain;

public class Agency extends User {
    private String agencyName;

    private Agency(Builder builder) {
        super(builder);
        this.agencyName = builder.agencyName;
    }
    
    public static Agency of(Builder builder) {
    	return new Agency(builder);
    }
}