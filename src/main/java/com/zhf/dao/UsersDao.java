package com.zhf.dao;

import com.zhf.bean.*;

import java.util.List;

/**
 * Created on 2019/10/23 0023.
 */
public interface UsersDao {

    double queryMoney(User user);

    void reCharge(User user, double amount, String tag);

    User queryUser(String userNo, String uPass);

    List<Film> queryOnlineFilm();

    List<Cinema> queryCinemaByFilmId(int filmId);

    List<Sessions> querySessionsByCid(int filmNo, int cinemaNo);

    Room queryRoomByRoomId(int roomId);

    String queryRoomSizeBySessionId(int sessionId);

    List<String> queryPurchasedSeats(int sessionId);

    double queryPriceBySessionId(int sessionId);

    int saveOrder(int sessionId, int uid, String seatInfo);

    List<Film> queryUserPurchasedFilm(User user);

    List<Comments> queryUserFilmAndComment(User user);

    List<Film> queryUserNotCommentFilm(User user);

    int insertCommentsByFid(User user, int filmId, double fscore, String content);

    int registerUser(String uNo, String uPass1);
}
