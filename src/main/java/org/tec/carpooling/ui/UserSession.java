package org.tec.carpooling.ui;

import org.tec.carpooling.bl.dto.BL_DA.UserDTO;

public final class UserSession {

    private static UserSession instance;
    private UserDTO currentUser;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void loginUser(UserDTO user) {
        this.currentUser = user;
    }

    public void logoutUser() {
        this.currentUser = null;
    }

    public UserDTO getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public Long getCurrentIdPerson() {
        return isLoggedIn() ? currentUser.getId() : null;
    }
}