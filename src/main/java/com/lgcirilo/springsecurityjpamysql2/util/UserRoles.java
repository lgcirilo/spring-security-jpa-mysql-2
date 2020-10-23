package com.lgcirilo.springsecurityjpamysql2.util;

import java.util.ArrayList;

public class UserRoles {
    long UserId;
    ArrayList<Integer> roleList;

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        UserId = userId;
    }

    public ArrayList<Integer> getRoleList() {
        return roleList;
    }

    public void setRoleList(ArrayList<Integer> roleList) {
        this.roleList = roleList;
    }
}
