package com.boyko.codemefast;

public class UserData {
    private static String currentUser = "";

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(String currentUser) {
        UserData.currentUser = currentUser;
    }
}
