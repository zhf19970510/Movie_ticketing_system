package com.zhf.dao.impl;

import com.zhf.bean.*;
import com.zhf.dao.UsersDao;
import com.zhf.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/10/23 0023.
 */
public class UsersDaoImpl implements UsersDao {
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @Override
    public double queryMoney(User user) {
        String sql = "SELECT money FROM users where uid=?";
        int uid = user.getUid();
        List<Double> moneys = new ArrayList<>();
        ConnectionUtil.DQL(sql, new Object[]{uid}, (rs) -> {
            try {
                while (rs.next()) {
                    double amount = rs.getDouble(1);
                    moneys.add(amount);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        if (moneys.size() == 1) {
            return moneys.get(0);
        }
        return -1;
    }

    @Override
    public void reCharge(User user, double amount, String tag) {
        String osql = "SELECT money FROM users where uid=?";
        try {
            conn = ConnectionUtil.getConnection();
            pst = conn.prepareStatement(osql);
            pst.setInt(1, user.getUid());
            rs = pst.executeQuery();
            double oamount = 0;
            while (rs.next()) {
                oamount = rs.getDouble(1);
            }
            String newSql = "UPDATE users SET money=? where uid=?";
            pst = conn.prepareStatement(newSql);
            if ("+".equals(tag)) {
                pst.setDouble(1, (oamount + amount));
            } else if ("-".equals(tag)) {
                pst.setDouble(1, (oamount - amount));
            } else {
                System.out.println("符号出错");
            }
            pst.setInt(2, user.getUid());
            int rows = pst.executeUpdate();
            if (rows < 1) {
                System.out.println("更改金额失败！");
            } else {
                System.out.println("更改金额成功！");
            }
        } catch (SQLException e) {
            System.out.println("更改金额出错！");
        }

    }

    @Override
    public User queryUser(String userNo, String uPass) {
        User user = new User();
        try {
            String sql = "select * from users where userNo=? and upass=?";

            conn = ConnectionUtil.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, userNo.trim());
            pst.setString(2, uPass.trim());
            rs = pst.executeQuery();
            while (rs.next()) {
                user.setUid(rs.getInt(1));
                user.setUserNo(rs.getString(2));
                user.setuPass(rs.getString(3));
                user.setuType(rs.getInt(4));
                user.setMoney(rs.getDouble(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<Film> queryOnlineFilm() {
        String sql = "select * from film where fname in(select  distinct fname from cinemaView)";
        List<Film> films = new ArrayList<>();
        ConnectionUtil.DQL(sql, new Object[]{}, (rs) -> {
            try {
                while (rs.next()) {
                    Film film = new Film();
                    int fid = rs.getInt(1);
                    String fname = rs.getString(2);
                    String ftype = rs.getString(3);
                    String fintroduce = rs.getString(4);
                    film.setFid(fid);
                    film.setfName(fname);
                    film.setfType(ftype);
                    film.setfIntroduce(fintroduce);
                    films.add(film);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return films;
    }

    @Override
    public List<Cinema> queryCinemaByFilmId(int filmId) {
        String sql = "select * from cinema where cid in (select distinct cid from cinemaView where fid=?)";
        List<Cinema> cinemas = new ArrayList<>();
        ConnectionUtil.DQL(sql, new Object[]{filmId}, (rs) -> {
            try {
                while (rs.next()) {
                    Cinema cinema = CinemaManagerDaoImpl.generateNewCinema(rs);
                    cinemas.add(cinema);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return cinemas;
    }

    @Override
    public List<Sessions> querySessionsByCid(int filmNo, int cinemaNo) {
        String sql = "select * from sessions where sid in (select distinct sid from cinemaView where fid=? and cid=?)";
        List<Sessions> sessions = new ArrayList<>();
        ConnectionUtil.DQL(sql, new Object[]{filmNo, cinemaNo}, (rs) -> {
            try {
                while (rs.next()) {
                    Sessions session = new Sessions();
                    session.setSid(rs.getInt(1));
                    session.setStartTime(rs.getString(2));
                    session.setEndTime(rs.getString(3));
                    Room room = new Room();
                    room.setRid(rs.getInt(4));
                    session.setRoom(room);
                    Film film = new Film();
                    film.setFid(rs.getInt(5));
                    session.setFilm(film);
                    sessions.add(session);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return sessions;
    }

    @Override
    public Room queryRoomByRoomId(int roomId) {
        String sql = "SELECT rname,rprice,rsize FROM room WHERE rid=?";
        List<Room> rooms = new ArrayList<>();
        ConnectionUtil.DQL(sql, new Object[]{roomId}, (rs) -> {
            try {
                while (rs.next()) {
                    Room room = new Room();
                    room.setName(rs.getString(1));
                    room.setRprice(rs.getDouble(2));
                    room.setrSize(rs.getString(3));
                    rooms.add(room);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        if (rooms.size() == 1) {
            Room room = rooms.get(0);
            return room;
        }
        return null;
    }

    @Override
    public String queryRoomSizeBySessionId(int sessionId) {
        String sql = "SELECT rsize FROM sessions,room WHERE sid=? and sessions.rid=room.rid";
        List<String> list = queryInfo(sessionId, sql);
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<String> queryPurchasedSeats(int sessionId) {
        String sql = "SELECT seat FROM orders WHERE sid=?";
        List<String> list = queryInfo(sessionId, sql);
        return list;
    }

    private List<String> queryInfo(int sessionId, String sql) {
        List<String> list = new ArrayList<>();
        ConnectionUtil.DQL(sql, new Object[]{sessionId}, (rs) -> {
            try {
                while (rs.next()) {
                    String seat = rs.getString(1);
                    list.add(seat);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return list;
    }

    @Override
    public double queryPriceBySessionId(int sessionId) {
        String sql = "SELECT rprice FROM sessions,room WHERE sid=? and sessions.rid=room.rid";
        List<Double> prices = new ArrayList<>();
        ConnectionUtil.DQL(sql, new Object[]{sessionId}, (rs) -> {
            try {
                while (rs.next()) {
                    double price = rs.getDouble(1);
                    prices.add(price);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        if (prices.size() == 1) {
            return prices.get(0);
        }
        return 0;
    }

    @Override
    public int saveOrder(int sessionId, int uid, String seatInfo) {
        int rows = 0;
        String sql = "INSERT INTO orders(sid,uid,seat) VALUES(?,?,?)";
        try {
            rows = ConnectionUtil.DML(sql, new Object[]{sessionId, uid, seatInfo});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public List<Film> queryUserPurchasedFilm(User user) {
        String sql = "SELECT film.fid,film.fname,film.ftype,film.fintroduce FROM orders,sessions,film WHERE orders.uid=? AND orders.sid=sessions.sid AND sessions.fid=film.fid";
        List<Film> filmList = new ArrayList<>();
        int uId = user.getUid();
        return getFilms(sql, uId, filmList);
    }

    @Override
    public List<Comments> queryUserFilmAndComment(User user) {
        List<Comments> commentsList = new ArrayList<>();
        String sql = "SELECT film.fname,comments.fscore,comments.content FROM film,comments WHERE comments.uid=? and comments.fid=film.fid";
        int uId = user.getUid();
        ConnectionUtil.DQL(sql, new Object[]{uId}, (rs) -> {
            try {
                while (rs.next()) {
                    Comments comments = new Comments();
                    Film film = new Film();
                    film.setfName(rs.getString(1));
                    comments.setFilm(film);
                    comments.setScore(rs.getDouble(2));
                    comments.setContent(rs.getString(3));
                    commentsList.add(comments);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return commentsList;
    }

    @Override
    public List<Film> queryUserNotCommentFilm(User user) {
        String sql = "SELECT film.fid,film.fname,film.ftype,film.fintroduce FROM orders,sessions,film WHERE orders.uid=? AND orders.sid=sessions.sid AND sessions.fid=film.fid and film.fname not in (SELECT DISTINCT film.fname FROM film,comments WHERE comments.uid=? and comments.fid=film.fid)";
        int uId = user.getUid();
        List<Film> filmList = new ArrayList<>();
        return getFilms(sql, uId, filmList);
    }

    private List<Film> getFilms(String sql, int uId, List<Film> filmList) {
        int num = getQuestionMarkNum(sql);
        Object[] objs = null;
        if (num == 1) {
            objs = new Object[]{uId};
        } else {
            objs = new Object[]{uId, uId};
        }
        ConnectionUtil.DQL(sql, objs, (rs) -> {
            try {
                while (rs.next()) {
                    Film film = new Film();
                    film.setFid(rs.getInt(1));
                    film.setfName(rs.getString(2));
                    film.setfType(rs.getString(3));
                    film.setfIntroduce(rs.getString(4));
                    filmList.add(film);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return filmList;
    }

    @Override
    public int insertCommentsByFid(User user, int filmId, double fscore, String content) {
        int rows = 0;
        int uId = user.getUid();
        String sql = "INSERT INTO comments(uid,fid,fscore,content) VALUES(?,?,?,?)";
        try {
            rows = ConnectionUtil.DML(sql, new Object[]{uId, filmId, fscore, content});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    public int getQuestionMarkNum(String sql) {
        char[] chs = sql.toCharArray();
        int count = 0;
        for (char ch : chs) {
            if (ch == '?') {
                count++;
            }
        }
        return count;
    }

    @Override
    public int registerUser(String uNo, String uPass1) {
        int rows = 0;
        String sql = "INSERT INTO users(userNo,upass,utype,money) VALUES(?,?,0,0)";
        try {
            rows = ConnectionUtil.DML(sql, new Object[]{uNo, uPass1});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }
}
