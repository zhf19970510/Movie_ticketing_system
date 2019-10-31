package com.zhf.bean;

/**
 * Created on 2019/10/24 0024.
 */
public class Room {
    private int rid;
    private String name;
    private Cinema cinema;
    private String rSize;
    private double rprice;
    public Room() {
    }

    public Room(String name, Cinema cinema, String rSize, double rprice) {
        this.name = name;
        this.cinema = cinema;
        this.rSize = rSize;
        this.rprice = rprice;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public String getrSize() {
        return rSize;
    }

    public void setrSize(String rSize) {
        this.rSize = rSize;
    }

    public double getRprice() {
        return rprice;
    }

    public void setRprice(double rprice) {
        this.rprice = rprice;
    }

    @Override
    public String toString() {
        return String.format("%-20s%-10.2f%-12s",name,rprice,rSize);
    }
}
