package com.zhf.view;

import com.zhf.bean.*;
import com.zhf.dao.UsersDao;
import com.zhf.dao.impl.UsersDaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created on 2019/10/23 0023.
 */
public class UsersView {
    private static UsersDao usersDao = new UsersDaoImpl();

    public static void UsersMainView(Scanner sc, User user) {
        while (true) {
            try {
                System.out.println("****************************");
                System.out.println("1.查询余额/充值余额");
                System.out.println("2.选购电影");
                System.out.println("3.评论");
                System.out.println("4.退出系统");
                String choose = sc.next();
                if (choose.equals("1")) {
                    System.out.print("您当前的余额为：");
                    double money = usersDao.queryMoney(user);
                    System.out.println(money);
                    System.out.println("\n您是否选择充值");
                    System.out.println("1.充值\t\t2.返回");
                    choose = sc.next();
                    if (choose.equals("1")) {
                        System.out.print("请输入您要充值的金额：");
                        double amount = sc.nextDouble();
                        usersDao.reCharge(user, amount, "+");

                    } else if ("2".equals(choose)) {
                        continue;
                    } else {
                        System.out.println("输入失误！");
                    }
                } else if (choose.equals("2")) {
                    System.out.println("当前所有排了片子的电影有：");
                    List<Film> films = queryOnlineFilm();
                    for (Film film : films) {
                        System.out.println(film);
                    }
                    System.out.print("请输入您想要观看的电影编号：");
                    int filmNo = Integer.parseInt(sc.next());
                    List<Cinema> cinemas = queryCinemaByFilmId(filmNo);
                    if (cinemas != null && cinemas.size() != 0) {
                        System.out.println("当前电影对应的电影院有：");
                        for (Cinema cinema : cinemas) {
                            System.out.println(cinema);
                        }
                    } else {
                        System.out.println("没有该电影编号指定的电影院!");
                        continue;
                    }
                    System.out.print("请输入您想去的电影院编号：");
                    int cinemaNo = Integer.parseInt(sc.next());
                    List<Sessions> sessions = querySessionsByCid(filmNo, cinemaNo);
                    if (sessions != null && sessions.size() != 0) {
                        System.out.println("该电影院关于该电影的场次信息如下：");
                        for (Sessions session : sessions) {
                            //根据sid和rid查询对应Room的具体信息
                            int roomId = session.getRoom().getRid();
                            Room room = queryRoomByRoomId(roomId);
                            if (room != null) {
                                session.getRoom().setName(room.getName());
                                session.getRoom().setRprice(room.getRprice());
                                session.getRoom().setrSize(room.getrSize());
                            }
                            System.out.println(session);
                        }
                    } else {
                        System.out.println("该电影院不存在或者该电影院没有该电影的场次信息");
                        continue;
                    }
                    System.out.print("请输入您想要选择的场次Id：");
                    int sessionId = Integer.parseInt(sc.next());
                    String roomSize = queryRoomSizeBySessionId(sessionId);
                    if (roomSize != null) {
                        System.out.println("该房间大小为：" + roomSize);
                        //查询该影厅已经购买过票的座位信息
                        List<String> seats = queryticketPurchasedSeat(sessionId);
                        if (seats != null && seats.size() != 0) {
                            System.out.println("当前已经选购的座位有：");
                            for (String seat : seats) {
                                System.out.println(String.format("%-10s", seat));
                            }
                        } else {
                            System.out.println("该影厅暂时还没有购票");
                        }
                        System.out.print("请输入您想要购买的座位：");
                        String seatInfo = sc.next();
                        //如果要订买电影票先要判断金额数够不够
                        //判断座位输入的是否合理
                        String[] xy = seatInfo.split(",");
                        String[] totalxy = roomSize.split(",");
                        if (seats.contains(seatInfo) || Integer.parseInt(xy[0]) <= 0 || Integer.parseInt(xy[0]) > Integer.parseInt(totalxy[0]) || Integer.parseInt(xy[1]) < 0 || Integer.parseInt(xy[1]) > Integer.parseInt(totalxy[1])) {
                            System.out.println("座位输入不合理");
                            continue;
                        }
                        double amount = queryPriceBySessionId(sessionId);
                        if (usersDao.queryMoney(user) >= amount) {
                            //更新订单表以及账上余额信息
                            usersDao.reCharge(user, amount, "-");
                            int rows = saveOrder(sessionId, user.getUid(), seatInfo);
                            if (rows > 0) {
                                System.out.println("保存订单成功！");
                            }
                        } else {
                            System.out.println("账号余额不足，请先进行充值");
                        }
                    }
                } else if (choose.equals("3")) {
                    //列出当前用户看过的片子+评论
                    List<Film> userFilms = queryUserPurchasedFilm(user);
                    if (userFilms != null && userFilms.size() != 0) {
                        System.out.println("当前用户看过的电影有：");
                        for (Film film : userFilms) {
                            System.out.println(film);

                        }
                        //当前用户已经评论过的电影，电影评论内容，评分
                        List<Comments> commentsList = queryUserFilmAndComment(user);
                        if (commentsList != null && commentsList.size() > 0) {
                            System.out.println("当前用户已经评论过的电影，电影评论评分，评论内容:");
                            for (Comments comments : commentsList) {
                                System.out.println(comments);
                            }
                        }

                        List<Film> filmList = queryUserNotCommentFilm(user);
                        if (filmList != null && filmList.size() != 0) {
                            System.out.println("当前用户看过但没有评论过的电影有：");
                            List<Integer> filmIdList = new ArrayList<>();
                            for (Film film : filmList) {
                                filmIdList.add(film.getFid());
                                System.out.println(film);
                            }
                            //如果当前用户还有没有评论过的电影，则提示用户是否进行评论
                            System.out.println("请选择：");
                            System.out.println("1.评论电影\t\t2.返回");
                            choose = sc.next();
                            if ("1".equals(choose)) {
                                System.out.print("请输入需要评论的电影Id：");
                                int filmId = Integer.parseInt(sc.next());
                                //判断该filmId是否在filmIdList中
                                if (filmIdList.contains(filmId)) {
                                    System.out.println("请输入需要评分的内容：");
                                    String content = sc.next();
                                    System.out.println("请给该电影评分(0.0-10.0)");
                                    double fscore = Double.parseDouble(sc.next());
                                    int rows = insertCommentsByFid(user, filmId, fscore, content);
                                    if (rows > 0) {
                                        System.out.println("插入评论成功！");
                                    }
                                } else {
                                    System.out.println("输入的电影id不在看过但没有评论过的列表范围内");
                                    continue;
                                }
                            } else if ("2".equals(choose)) {
                                continue;
                            } else {
                                System.out.println("输入错误！");
                            }
                        }


                    } else {
                        System.out.println("当前用户还没有买过电影票！");
                    }
                } else if (choose.equals("4")) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("操作有误，请重新操作！");
            }
        }

    }

    private static int insertCommentsByFid(User user, int filmId, double fscore, String content) {
        int rows = 0;
        try {
            rows = usersDao.insertCommentsByFid(user, filmId, fscore, content);
        } catch (Exception e) {
            System.out.println("插入评论失败");
        }
        return rows;
    }

    private static List<Film> queryUserNotCommentFilm(User user) {
        List<Film> filmList = null;
        try {
            filmList = usersDao.queryUserNotCommentFilm(user);
        } catch (Exception e) {
            System.out.println("没有查询到该用户所有看过但是没有评论过的电影");
        }
        return filmList;
    }

    private static List<Comments> queryUserFilmAndComment(User user) {
        List<Comments> commentsList = null;
        try {
            commentsList = usersDao.queryUserFilmAndComment(user);
        } catch (Exception e) {
            System.out.println("查询该用户所有电影评论信息失败");
        }
        return commentsList;
    }

    private static List<Film> queryUserPurchasedFilm(User user) {
        List<Film> filmList = null;
        try {
            filmList = usersDao.queryUserPurchasedFilm(user);
        } catch (Exception e) {
            System.out.println("查找该用户已购买过的电影失败");
        }
        return filmList;
    }

    private static int saveOrder(int sessionId, int uid, String seatInfo) {
        int rows = 0;
        try {
            rows = usersDao.saveOrder(sessionId, uid, seatInfo);
        } catch (Exception e) {
            System.out.println("保存订单失败！");
        }
        return rows;
    }

    private static double queryPriceBySessionId(int sessionId) {
        double price = 0;
        try {
            price = usersDao.queryPriceBySessionId(sessionId);
        } catch (Exception e) {
            System.out.println("没有找到该场次对应的价格信息！");
        }
        return price;
    }

    private static List<String> queryticketPurchasedSeat(int sessionId) {
        List<String> seats = null;
        try {
            seats = usersDao.queryPurchasedSeats(sessionId);
        } catch (Exception e) {
            System.out.println("查找该场次对应的已购票座位信息失败");
        }
        return seats;
    }

    private static String queryRoomSizeBySessionId(int sessionId) {
        String str = null;
        try {
            str = usersDao.queryRoomSizeBySessionId(sessionId);
        } catch (Exception e) {
            System.out.println("查询该场次对应影厅大小信息失败");
        }
        return str;
    }

    private static Room queryRoomByRoomId(int roomId) {
        Room room = null;
        try {
            room = usersDao.queryRoomByRoomId(roomId);
        } catch (Exception e) {
            System.out.println("没有对应影厅");
        }
        return room;
    }

    private static List<Sessions> querySessionsByCid(int filmNo, int cinemaNo) {
        List<Sessions> sessions = null;
        try {
            sessions = usersDao.querySessionsByCid(filmNo, cinemaNo);
        } catch (Exception e) {
            System.out.println("查找失败！");
        }
        return sessions;
    }

    public User queryUser(String userNo, String uPass) {
        User user = null;
        try {
            user = usersDao.queryUser(userNo, uPass);
        } catch (Exception e) {
            System.out.println("查找失败！");
        }
        return user;
    }

    public static List<Film> queryOnlineFilm() {
        List<Film> films = null;
        try {
            films = usersDao.queryOnlineFilm();
        } catch (Exception e) {
            System.out.println("查找失败！");
        }
        return films;
    }

    public static List<Cinema> queryCinemaByFilmId(int filmId) {
        List<Cinema> cinemas = null;
        try {
            cinemas = usersDao.queryCinemaByFilmId(filmId);
        } catch (Exception e) {
            System.out.println("没有该电影编号指定的电影院!");
        }
        return cinemas;
    }

    public int registerUser(String uNo, String uPass1) {
        int rows = 0;
        try {
            rows = usersDao.registerUser(uNo, uPass1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }
}
