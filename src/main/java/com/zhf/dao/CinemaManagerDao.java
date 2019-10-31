package com.zhf.dao;

import com.zhf.bean.*;

import java.util.List;

/**
 * Created on 2019/10/23 0023.
 */
public interface CinemaManagerDao {
    List<Cinema> queryAllCinema(User user);

    int addCinema(User user, String cname, String city, String address);

    int deleteCinemaByCid(User user, int cid);

    boolean checkCinemaExist(String cname);

    List<Room> queryAllRoom(User user);

    List<Sessions> queryAllSessions(User user);

    Cinema queryCinemaByfname(User user, String cname);

    int addRoom(User user, int cId, String roomName, double rprice, String rsize);

    int deleteRoomByrId(int cid, int rId);

    Room queryRoomById(int rId, int cId);

    int addSession(String startTime, String endTime, int rid, String filmName);

    Film queryFilmByName(String filmName);

    int deleteSessionById(int sId, Room room);

    int updateSessionById(int sId, Room room, String fname);

    int updateRoomByrId(int cid, int rId, double price);

}
