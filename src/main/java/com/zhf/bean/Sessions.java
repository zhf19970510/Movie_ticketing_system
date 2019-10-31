package com.zhf.bean;

/**
 * Created on 2019/10/24 0024.
 */
public class Sessions {
    private int sid;
    private String startTime;
    private String endTime;
    private Room room;
    private Film film;

    public Sessions() {
    }

    public Sessions(String startTime, String endTime, Room room, Film film) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
        this.film = film;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public String toString() {
        return String.format("%-10d%-20s%-20s", sid, startTime, endTime) + this.room.toString();
    }
}
