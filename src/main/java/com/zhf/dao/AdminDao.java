package com.zhf.dao;

import com.zhf.bean.Film;
import com.zhf.bean.User;

import java.util.List;

/**
 * Created on 2019/10/23 0023.
 */
public interface AdminDao {
    List<User> queryAllPersonByType(int utype);

    User queryUserById(int uid);

    boolean deleteUserInfo(int uid);

    int addCinemaManager(String cinManagerNo, String cinMangerPass);

    User queryUserByUserNo(String cinemaManagerNo);

    boolean deleteCinemaManager(int uid);

    boolean updateCinemaManager(int uid, String cinemaManagerNo, String cinemaManagerPass);

    List<Film> findAllFilm();

    int addFilm(String filmName, String ftype, String fintroduce);

    Film queryFilmByName(String fname);

    boolean deleteFilm(String fname, boolean isOnlineFilm);

    int updateFilmIntroduce(String fname, String fintroduce);
}
