package org.tec.carpooling.ui;

public final class UserSession {

    private static UserSession instance;
    private String currentUser;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setLogInUser(String user) {
        this.currentUser = user;
    }

    public void logoutUser() {
        this.currentUser = null;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}