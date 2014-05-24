package ua.ros.taxiapp.domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    Integer userId;
    String password;
    String mobile;
    Boolean taxist;
    Date createdTime;
    String username;
    
    public User() {
    }
    
    public User(String mobile, String pass) {
        this.mobile = mobile;
        this.password = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getTaxist() {
        return taxist;
    }

    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer id) {
        this.userId = id;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public Date getCreatedTime() {
        return createdTime;
    }
    
    public void setCreatedTime(Date time) {
        this.createdTime = time;
    }
    
    public void setTaxist(Boolean taxist) {
        this.taxist = taxist;
    }
    
    public String toString() {
    	return this.username;
    }
}
