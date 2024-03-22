package com.athenauem;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserInfo {

    /* user info */
    private StringProperty username, password, site;

    public UserInfo(String username, String password, String site){
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.site = new SimpleStringProperty(site);
    }

    /* JavaFX getter methods */
    public StringProperty usernameProperty(){
        return username;
    }

    public StringProperty passwordProperty(){
        return password;
    }

    public StringProperty siteProperty(){
        return site;
    }

    /* Standard getter methods */
    public String getUsername() {
        return username.get();
    }
    public String getPassword() {
        return password.get();
    }
    public String getSite() {
        return site.get();
    }
}
