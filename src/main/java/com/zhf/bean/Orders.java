package com.zhf.bean;

/**
 * Created on 2019/10/24 0024.
 */
public class Orders {
    private int oid;
    private Sessions sessions;
    private String seat;
    private User user;

    public Orders() {
    }

    public Orders(Sessions sessions, String seat, User user) {
        this.sessions = sessions;
        this.seat = seat;
        this.user = user;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public Sessions getSessions() {
        return sessions;
    }

    public void setSessions(Sessions sessions) {
        this.sessions = sessions;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oid=" + oid +
                ", sessions=" + sessions +
                ", seat='" + seat + '\'' +
                ", user=" + user +
                '}';
    }
}
