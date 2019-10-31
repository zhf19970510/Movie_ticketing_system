package com.zhf.dao.impl;

import com.zhf.bean.Film;
import com.zhf.bean.User;
import com.zhf.dao.AdminDao;
import com.zhf.util.ConnectionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/10/23 0023.
 */
public class AdminDaoImpl implements AdminDao {
    @Override
    public List<User> queryAllPersonByType(int utype) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE utype=?";
        try {
            ConnectionUtil.DQL(sql, new Object[]{utype}, (rs) -> {
                try {
                    while (rs.next()) {
                        User user = new User();
                        user.setUid(rs.getInt(1));
                        user.setUserNo(rs.getString(2));
                        user.setuPass(rs.getString(3));
                        user.setuType(rs.getInt(4));
                        user.setMoney(rs.getDouble(5));
                        users.add(user);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User queryUserById(int uid) {
        User user = new User();
        String sql = "SELECT * FROM users WHERE uid=? AND utype=0";
        try {
            ConnectionUtil.DQL(sql, new Object[]{uid}, (rs) -> {
                try {
                    while (rs.next()) {
                        user.setUid(rs.getInt(1));
                        user.setUserNo(rs.getString(2));
                        user.setuPass(rs.getString(3));
                        user.setuType(rs.getInt(4));
                        user.setMoney(rs.getDouble(5));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean deleteUserInfo(int uid) {
        boolean flag = true;
        String sql1 = "DELETE FROM orders WHERE uid=?";
        String sql2 = "DELETE FROM comments WHERE uid=?";
        String sql3 = "DELETE FROM users WHERE uid=?";

        try {
            flag = ConnectionUtil.transaction(new String[]{sql1, sql2, sql3}, new Object[]{uid});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public int addCinemaManager(String cinManagerNo, String cinMangerPass) {
        int rows = 0;
        String sql = "INSERT INTO users(userNo,upass,utype,money) VALUES(?,?,1,100)";
        try {
            rows = ConnectionUtil.DML(sql, new Object[]{cinManagerNo, cinMangerPass});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public User queryUserByUserNo(String cinemaManagerNo) {
        User user = new User();
        String sql = "SELECT * FROM users WHERE userNo=? AND utype=1";
        try {
            ConnectionUtil.DQL(sql, new Object[]{cinemaManagerNo}, (rs) -> {
                try {
                    while (rs.next()) {
                        user.setUid(rs.getInt(1));
                        user.setUserNo(rs.getString(2));
                        user.setuPass(rs.getString(3));
                        user.setuType(rs.getInt(4));
                        user.setMoney(rs.getDouble(5));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean deleteCinemaManager(int uid) {
        //删除该影院管理员所有场次信息
        String sql1 = " DELETE FROM sessions WHERE sid IN (SELECT sid FROM (SELECT sid FROM cinema,room,sessions WHERE cinema.uid=? AND cinema.cid=room.cid AND room.rid=sessions.rid) a)";
        //删除该影院管理员所有影厅信息
        String sql2 = "DELETE FROM room WHERE rid IN (SELECT rid FROM (SELECT rid FROM cinema,room WHERE cinema.uid=? AND cinema.cid=room.cid) a)";
        //删除该影院管理员所有电影院信息
        String sql3 = "DELETE FROM cinema WHERE cid IN (SELECT cid FROM (SELECT cid FROM users,cinema WHERE users.uid=? AND users.uid=cinema.uid) AS a)";
        String sql4 = "DELETE FROM users WHERE uid=?";
        boolean flag = true;
        try {
            flag = ConnectionUtil.transaction(new String[]{sql1, sql2, sql3, sql4}, new Object[]{uid});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateCinemaManager(int uid, String cinemaManagerNo, String cinemaManagerPass) {
        String sql = "UPDATE users set userNo=?,upass=? WHERE uid=? AND utype=1";
        int rows = 0;
        try {
            rows = ConnectionUtil.DML(sql, new Object[]{cinemaManagerNo, cinemaManagerPass, uid});
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rows > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Film> findAllFilm() {
        List<Film> filmList = new ArrayList<>();
        String sql = "SELECT * FROM film";
        try {
            ConnectionUtil.DQL(sql, new Object[]{}, (rs) -> {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filmList;
    }

    @Override
    public int addFilm(String filmName, String ftype, String fintroduce) {
        int rows = 0;
        String sql = "INSERT INTO film(fname,ftype,fintroduce) VALUES(?,?,?)";
        try {
            rows = ConnectionUtil.DML(sql, new Object[]{filmName, ftype, fintroduce});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public Film queryFilmByName(String fname) {
        Film film = new Film();
        String sql = "SELECT * FROM film WHERE fname=?";
        try {
            ConnectionUtil.DQL(sql, new Object[]{fname}, (rs) -> {
                try {
                    while (rs.next()) {
                        film.setFid(rs.getInt(1));
                        film.setfName(rs.getString(2));
                        film.setfType(rs.getString(3));
                        film.setfIntroduce(rs.getString(4));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return film;
    }

    @Override
    public boolean deleteFilm(String fname, boolean isOnlineFilm) {
        //找到fname对应的fid
        Film film = queryFilmByName(fname);
        if (film.getFid() == 0) {
            return false;
        }
        int fid = film.getFid();
        String sql1 = "DELETE FROM sessions WHERE fid=?";
        String sql2 = "DELETE FROM film WHERE fid=?";
        boolean isDelete = true;
        try {
            if (isOnlineFilm) {
                isDelete = ConnectionUtil.transaction(new String[]{sql1, sql2}, new Object[]{fid});
            } else {
                isDelete = ConnectionUtil.transaction(new String[]{sql2}, new Object[]{fid});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDelete;
    }

    @Override
    public int updateFilmIntroduce(String fname, String fintroduce) {
        int rows = 0;
        String sql = "UPDATE film SET fintroduce=? where fname=?";
        try {
            rows = ConnectionUtil.DML(sql,new Object[]{fintroduce,fname});
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows;
    }
}
