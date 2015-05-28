package com.boyko.codemefast;

public class User {
    private String userName;
    private int id;
    private int uixp;
    private int dbxp;
    private int serverxp;
    private int backendXp;
    private int avatar;
    private int level;

    public User(String userName, int id, int uixp, int dbxp, int serverxp, int backendXp, int avatar, int level) {
        super();
        this.userName = userName;
        this.id = id;
        this.uixp = uixp;
        this.dbxp = dbxp;
        this.serverxp = serverxp;
        this.backendXp = backendXp;
        this.avatar = avatar;
        this.level = level;
    }

}
