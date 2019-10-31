package com.zhf.dao.impl;

import com.zhf.bean.*;
import com.zhf.dao.CinemaManagerDao;
import com.zhf.util.ConnectionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/10/23 0023.
 */
public class CinemaManagerDaoImpl implements CinemaManagerDao {

    @Override
    public List<Cinema> queryAllCinema(User user) {
        List<Cinema> cinemaList = new ArrayList<>();
        int uId = user.getUid();
        String sql = "SELECT cid,cname,city,address FROM users,cinema WHERE users.uid=? AND users.uid=cinema.uid";
        ConnectionUtil.DQL(sql, new Object[]{uId}, (rs) -> {
            try {
                while (rs.next()) {
                    Cinema cinema = generateNewCinema(rs);
                    cinemaList.add(cinema);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return cinemaList;
    }

    static Cinema generateNewCinema(ResultSet rs) throws SQLException {
        Cinema cinema = new Cinema();
        cinema.setCid(rs.getInt(1));
        cinema.setcName(rs.getString(2));
        cinema.setCity(rs.getString(3));
        cinema.setAddress(rs.getString(4));
        return cinema;
    }

    @SuppressWarnings(value = "Duplicates")
    @Override
    public int addCinema(User user, String cname, String city, String address) {
        int rows = 0;
        String sql = "INSERT INTO cinema(cname,city,address,uid) VALUES(?,?,?,?)";
        int uId = user.getUid();
        try {
            rows = ConnectionUtil.DML(sql, new Object[]{cname, city, address, uId});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int deleteCinemaByCid(User user, int cid) {
        int rows = 0;
        int uId = user.getUid();
        String sql = "DELETE FROM cinema WHERE cid=? AND cinema.uid=?";
        try {
            rows = ConnectionUtil.DML(sql, new Object[]{cid, uId});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public boolean checkCinemaExist(String cname) {
        String sql = "SELECT city FROM cinema where cname=?";
        List<String> cityList = new ArrayList<>();
        ConnectionUtil.DQL(sql, new Object[]{cname}, (rs) -> {
            try {
                while (rs.next()) {
                    String qcity = rs.getString(1);
                    cityList.add(qcity);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        if (cityList.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<Room> queryAllRoom(User user) {
        String sql = "SELECT rid,rname,rprice,rsize,cinema.cid,cname FROM cinema,room WHERE cinema.uid=? AND cinema.cid=room.cid";
        List<Room> roomList = new ArrayList<>();
        int uId = user.getUid();
        ConnectionUtil.DQL(sql, new Object[]{uId}, (rs) -> {
            try {
                while (rs.next()) {
                    Room room = new Room();
                    room.setRid(rs.getInt(1));
                    room.setName(rs.getString(2));
                    room.setRprice(rs.getDouble(3));
                    room.setrSize(rs.getString(4));
                    Cinema cinema = new Cinema();
                    cinema.setCid(rs.getInt(5));
                    cinema.setcName(rs.getString(6));
                    room.setCinema(cinema);
                    roomList.add(room);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return roomList;
    }

    @Override
    public List<Sessions> queryAllSessions(User user) {
        String sql = "SELECT sessions.sid,startTime,endTime,rname,rprice,rsize,fname FROM cinema,room,sessions,film WHERE cinema.uid=? AND cinema.cid=room.cid AND room.rid=sessions.rid AND sessions.fid=film.fid";
        int uId = user.getUid();
        List<Sessions> sessionsList = new ArrayList<>();
        ConnectionUtil.DQL(sql, new Object[]{uId}, (rs) -> {
            try {
                while (rs.next()) {
                    Sessions session = new Sessions();
                    Room room = new Room();
                    Film film = new Film();
                    session.setSid(rs.getInt(1));
                    session.setStartTime(rs.getString(2));
                    session.setEndTime(rs.getString(3));
                    room.setName(rs.getString(4));
                    room.setRprice(rs.getDouble(5));
                    room.setrSize(rs.getString(6));
                    film.setfName(rs.getString(7));
                    session.setRoom(room);
                    session.setFilm(film);
                    sessionsList.add(session);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return sessionsList;
    }

    @Override
    public Cinema queryCinemaByfname(User user, String cname) {
        Cinema cinema = new Cinema();
        int uId = user.getUid();
        String sql = "SELECT * FROM cinema WHERE uid=? AND cname=?";
        ConnectionUtil.DQL(sql, new Object[]{uId, cname}, (rs) -> {
            try {
                while (rs.next()) {
                    cinema.setCid(rs.getInt(1));
                    cinema.setcName(rs.getString(2));
                    cinema.setCity(rs.getString(3));
                    cinema.setAddress(rs.getString(4));
                    cinema.setUser(user);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return cinema;
    }

    @Override
    public int addRoom(User user, int cId, String rname, double rprice, String rsize) {
        int uId = user.getUid();
        String sql = "INSERT INTO room(rname,rprice,rsize,cid) VALUES(?,?,?,?)";
        int rows = 0;
        rows = ConnectionUtil.DML(sql, new Object[]{rname, rprice, rsize, cId});
        return rows;
    }

    @Override
    public int deleteRoomByrId(int cid, int rId) {
        int rows = 0;
        String sql = "DELETE FROM room WHERE rid=? AND cid=?";
        try {
            rows = ConnectionUtil.DML(sql, new Object[]{rId, cid});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public Room queryRoomById(int rId, int cId) {
        String sql = "SELECT * FROM room WHERE rid=? AND cid=?";
        Room room = new Room();
        ConnectionUtil.DQL(sql, new Object[]{rId, cId}, (rs) -> {
            try {
                while (rs.next()) {
                    room.setRid(rs.getInt(1));
                    room.setName(rs.getString(2));
                    room.setRprice(rs.getDouble(3));
                    room.setrSize(rs.getString(4));
                    Cinema cinema = new Cinema();
                    cinema.setCid(rs.getInt(5));
                    room.setCinema(cinema);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return room;
    }

    @Override
    public Film queryFilmByName(String filmName) {
        Film film = new Film();
        String sql = "SELECT fid,fname FROM film WHERE fname=?";
        ConnectionUtil.DQL(sql, new Object[]{filmName}, (rs) -> {
            try {
                while (rs.next()) {
                    film.setFid(rs.getInt(1));
                    film.setfName(rs.getString(2));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return film;
    }

    @Override
    public int addSession(String startTime, String endTime, int rid, String filmName) {
        //找到电影名称对应的Film对象
        Film film = queryFilmByName(filmName);
        if (film.getFid() < 1) {
            System.out.println("输入的电影不存在！");
            return 0;
        }
        int fId = film.getFid();
        String sql = "INSERT INTO sessions(startTime,endTime,rid,fid) VALUES(?,?,?,?)";
        int rows = 0;
        try {
            rows = ConnectionUtil.DML(sql, new Object[]{startTime, endTime, rid, fId});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int deleteSessionById(int sId, Room room) {
        String sql = "DELETE FROM sessions WHERE sid=? AND rid=?";
        int rows = 0;
        try {
            rows = ConnectionUtil.DML(sql, new Object[]{sId, room.getRid()});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int updateSessionById(int sId, Room room, String fname) {
        int rows = 0;
        //首先把对应要修改的电影找到来
        //找到电影名称对应的Film对象
        Film film = queryFilmByName(fname);
        if (film.getFid() < 1) {
            System.out.println("输入的电影不存在！");
            return 0;
        }
        String sql = "UPDATE sessions SET fid=? WHERE sid=? AND rid=?";
        try {
            rows = ConnectionUtil.DML(sql, new Object[]{film.getFid(), sId, room.getRid()});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int updateRoomByrId(int cid, int rId, double price) {
        int rows = 0;
        String sql = "UPDATE room SET rprice=? WHERE rid=? AND cid=?";
        try {
            rows = ConnectionUtil.DML(sql, new Object[]{price, rId, cid});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }


}
