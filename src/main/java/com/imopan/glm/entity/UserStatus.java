package com.imopan.glm.entity;

public enum UserStatus {
    
	NORMAL ("normal"), FORBID("forbid");
    
    private String value;
    
    private UserStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public String value(){
        return this.value;
    }
    
    
    public static UserStatus value(String value){
    	for (UserStatus userStatus : UserStatus.values()) {
    		if(userStatus.getValue().equals(value)){
    			return userStatus;
    		}
		}
    	return null;
    }
}
