package com.zhf.bean;

/**
 * Created on 2019/10/24 0024.
 */
public class Cinema {
    private int cid;
    private String cName;
    private String city;
    private String address;
    private User user;

    public Cinema() {
    }

    public Cinema(String cName, String city, String address, User user) {
        this.cName = cName;
        this.city = city;
        this.address = address;
        this.user = user;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("%-10d%-25s\t%-16s%s",cid,cName,city,address);
    }
}
