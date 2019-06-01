package com.mLabsCloseServer.LetsTalk.models;

public class UserInfo {

    private  String organization_name;
    private  String user_id;
    private UserNew userNew;

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public UserNew getUserNew() {
        return userNew;
    }

    public void setUserNew(UserNew userNew) {
        this.userNew = userNew;
    }
}
