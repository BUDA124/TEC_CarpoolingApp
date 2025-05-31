package org.tec.carpooling.ui;

import org.tec.carpooling.bl.dto.UI_BL.LogInDTO;

public final class UserSession {

    private static UserSession instance;
    private LogInDTO currentUser;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void loginUser(LogInDTO user) {
        this.currentUser = user;
    }

    public void logoutUser() {
        this.currentUser = null;
    }

    public LogInDTO getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}