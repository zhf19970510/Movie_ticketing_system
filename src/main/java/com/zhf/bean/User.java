package com.zhf.bean;

/**
 * Created on 2019/10/24 0024.
 */
public class User {
    private int uid;
    private String userNo;
    private String uPass;
    private double money;
    private int uType;

    public User() {
    }

    public User(String userNo, String uPass, double money, int uType) {
        this.userNo = userNo;
        this.uPass = uPass;
        this.money = money;
        this.uType = uType;
    }

    public User(int uid, String userNo, String uPass, double money, int uType) {
        this.uid = uid;
        this.userNo = userNo;
        this.uPass = uPass;
        this.money = money;
        this.uType = uType;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getuPass() {
        return uPass;
    }

    public void setuPass(String uPass) {
        this.uPass = uPass;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getuType() {
        return uType;
    }

    public void setuType(int uType) {
        this.uType = uType;
    }

    @Override
    public String toString() {
        return String.format("%-6d%-20s%-20s%-10.2f%d",uid,userNo,uPass,money,uType);
    }
}
