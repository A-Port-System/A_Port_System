package com.aport.user.domain;

public class Officer extends User{
    private String department;

    private Officer(Builder builder) {
        super(builder);
        this.department = builder.department;
    }
    
    public static Officer of(Builder builder) {
    	return new Officer(builder);
    }
}
