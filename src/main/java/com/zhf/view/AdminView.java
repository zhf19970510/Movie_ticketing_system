package com.zhf.view;

import com.zhf.bean.Film;
import com.zhf.bean.User;
import com.zhf.dao.AdminDao;
import com.zhf.dao.impl.AdminDaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created on 2019/10/23 0023.
 */
public class AdminView {
    private static AdminDao adminDao = new AdminDaoImpl();

    public static void AdminMainView(Scanner sc, User user) {
        while (true) {
            try {
                System.out.println("****************************");
                System.out.println("1.普通用户管理");
                System.out.println("2.影院管理员管理");
                System.out.println("3.电影管理");
                System.out.println("4.退出系统");
                String choose = sc.next();
                if ("1".equals(choose)) {
                    boolean flag1 = queryPersonByType(0, "普通");
                    if (flag1 == false) {
                        continue;
                    }
                    System.out.println("请输入你要进行的操作：");
                    System.out.println("1.删除用户");
                    System.out.println("2.返回");
                    choose = sc.next();
                    if ("1".equals(choose)) {
                        // 删除不合理用户以及该用户的订单和评论信息
                        System.out.println("请输入你要删除的用户id:");
                        int uid = Integer.parseInt(sc.next());
                        User user1 = queryUserById(uid);
                        if (user1 != null && user1.getUid() > 0) {
                            //删除改用户以及该用户的订单和评论信息，这里涉及到事务操作
                            boolean flag = deleteUserInfo(uid);
                            if (flag) {
                                System.out.println("删除该用户以及该用户的订单和评论信息成功！");
                            } else {
                                System.out.println("删除该用户以及该用户的订单和评论信息失败！");
                            }
                        } else {
                            System.out.println("当前平台无用户，无法删除用户！");
                        }
                    } else if ("2".equals(choose)) {
                        continue;
                    } else {
                        System.out.println("输入错误");
                        continue;
                    }

                } else if ("2".equals(choose)) {
                    boolean flag1 = queryPersonByType(1, "影院管理员");
                    if (flag1 == false) {
                        continue;
                    }
                    System.out.println("请输入你要进行的操作：");
                    System.out.println("1.添加影院管理员");
                    System.out.println("2.删除影院管理员");
                    System.out.println("3.修改影院管理员");
                    System.out.println("4.返回");
                    choose = sc.next();
                    if ("1".equals(choose)) {
                        System.out.println("请输入需要添加的影院管理员账号：");
                        String cinManagerNo = sc.next();
                        System.out.println("请输入需要添加的影院管理员密码：");
                        String cinMangerPass = sc.next();
                        int rows = addCinemaManager(cinManagerNo, cinMangerPass);
                        if (rows > 0) {
                            System.out.println("添加影院管理员成功！");
                        } else {
                            System.out.println("添加影院管理员失败！");
                        }
                    } else if ("2".equals(choose)) {
                        deleteOrUpdateCinemaManagerInfo(sc, "删除");
                    } else if ("3".equals(choose)) {
                        deleteOrUpdateCinemaManagerInfo(sc, "修改");
                    } else if ("4".equals(choose)) {
                        continue;
                    } else {
                        System.out.println("输入选项错误!");
                        continue;
                    }
                } else if ("3".equals(choose)) {
                    List<Film> onlineFilmList = UsersView.queryOnlineFilm();
                    if (onlineFilmList != null && onlineFilmList.size() != 0) {
                        System.out.println("当前所有排了片子的电影有：");
                        for (Film film : onlineFilmList) {
                            System.out.println(film);
                        }
                    }
                    List<Film> allFilmList = findAllFilm();
                    //用这个集合存储未排场次的电影

                    List<Film> notOnLineFilms = new ArrayList<>();
                    for (Film afilm : allFilmList) {
                        boolean similar = false;
                        for (Film film : onlineFilmList) {
                            if (afilm.getFid() == film.getFid()) {
                                similar = true;
                                break;
                            }
                        }
                        if (!similar) {
                            notOnLineFilms.add(afilm);
                        }
                    }
                    if (notOnLineFilms.size() != 0) {
                        System.out.println("\n当前未排片子的电影有：");
                        for (Film film : notOnLineFilms) {
                            System.out.println(film);
                        }
                    }

                    System.out.println("\n请选择你要进行的操作：");
                    System.out.println("1.增加电影");
                    System.out.println("2.删除电影");
                    System.out.println("3.修改电影");
                    System.out.println("4.返回");
                    choose = sc.next();
                    if ("1".equals(choose)) {
                        System.out.println("请输入要增加的电影名称：");
                        String filmName = sc.next();
                        //确认电影是否存在
                        boolean exists = false;
                        for (Film film : allFilmList) {
                            if (film.getfName().equals(filmName)) {
                                System.out.println("要增加的电影已存在，不可再次增加");
                                exists = true;
                                break;
                            }
                        }
                        if (exists == true) {
                            continue;
                        }
                        System.out.println("请输入要增加电影的类型：");
                        String ftype = sc.next();
                        System.out.println("请输入要增加电影的简介：");
                        String fintroduce = sc.next();
                        int rows = addFilm(filmName, ftype, fintroduce);
                        if (rows > 0) {
                            System.out.println("增加电影成功！");
                        } else {
                            System.out.println("增加电影失败！");
                        }
                    } else if ("2".equals(choose)) {
                        System.out.println("请输入要删除的电影名称：");
                        String fname = sc.next();
                        //分为两种情况，一种是排了电影的，涉及到级联操作，一种是未排电影的，不需要级联操作，直接删除
                        //先检查在所有的电影中是否存在该电影名称，如果存在，则下一步操作；如果不存在，则直接再来
                        boolean exists = false;
                        for (Film film : allFilmList) {
                            if (film.getfName().equals(fname)) {
                                exists = true;
                                break;
                            }
                        }
                        if (exists == true) {
                            //需要判定属于哪种类型
                            boolean isOnlineFilm = false;
                            for (Film film : onlineFilmList) {
                                if (film.getfName().equals(fname)) {
                                    isOnlineFilm = true;
                                    break;
                                }
                            }
                            boolean isDelete = deleteFilm(fname, isOnlineFilm);
                            if(isDelete){
                                System.out.println("删除电影成功！");
                            }else {
                                System.out.println("删除电影失败！");
                            }
                        } else {
                            System.out.println("要删除的电影不存在！");
                            continue;
                        }

                    } else if ("3".equals(choose)) {
                        System.out.println("请输入要修改的电影名称：");
                        String fname = sc.next();
                        boolean exists = false;
                        for (Film film : allFilmList) {
                            if (film.getfName().equals(fname)) {
                                exists = true;
                                break;
                            }
                        }
                        if(exists){
                            System.out.println("请输入修改后的电影简介：");
                            String fintroduce = sc.next();
                            int rows = updateFilmIntroduce(fname,fintroduce);
                            if(rows>0){
                                System.out.println("更新电影信息成功！");
                            }else {
                                System.out.println("更新电影信息失败！");
                            }
                        }
                    } else if ("4".equals(choose)) {
                        continue;
                    } else {
                        System.out.println("输入选项错误");
                        continue;
                    }
                } else if (choose.equals("4")) {
                    break;
                } else {
                    System.out.println("输入选项错误");
                }
            } catch (Exception e) {
                System.out.println("操作有误，请重新操作！");
            }
        }
    }

    private static int updateFilmIntroduce(String fname, String fintroduce) {
        int rows = 0;
        try {
            rows = adminDao.updateFilmIntroduce(fname,fintroduce);
        }catch (Exception e){
            System.out.println("更新电影简介");
        }
        return rows;
    }

    private static boolean deleteFilm(String fname, boolean isOnlineFilm) {
        boolean isDelete = true;
        try {
            isDelete = adminDao.deleteFilm(fname, isOnlineFilm);
        } catch (Exception e) {
            System.out.println("级联删除电影失败！");
        }
        return isDelete;
    }

    private static int addFilm(String filmName, String ftype, String fintroduce) {
        int rows = 0;
        try {
            rows = adminDao.addFilm(filmName, ftype, fintroduce);
        } catch (Exception e) {
            System.out.println("添加电影失败!");
        }
        return rows;
    }

    private static List<Film> findAllFilm() {
        List<Film> filmList = null;
        try {
            filmList = adminDao.findAllFilm();
        } catch (Exception e) {
            System.out.println("查找所有电影失败！");
        }
        return filmList;
    }

    private static boolean deleteCinemaManager(int uid) {
        boolean flag = true;
        try {
            flag = adminDao.deleteCinemaManager(uid);
        } catch (Exception e) {
            System.out.println("删除该用户管理员相关信息出错！");
        }
        return flag;
    }

    private static User queryUserByUserNo(String cinemaManagerNo) {
        User user = null;
        try {
            user = adminDao.queryUserByUserNo(cinemaManagerNo);
        } catch (Exception e) {
            System.out.println("查询该影院管理员信息失败！");
        }
        return user;
    }

    private static int addCinemaManager(String cinManagerNo, String cinMangerPass) {
        int rows = 0;
        try {
            rows = adminDao.addCinemaManager(cinManagerNo, cinMangerPass);
        } catch (Exception e) {
            System.out.println("添加影院管理员出错！");
        }
        return rows;
    }

    private static boolean deleteUserInfo(int uid) {
        boolean flag = true;
        try {
            flag = adminDao.deleteUserInfo(uid);
        } catch (Exception e) {
            System.out.println("删除用户信息出错！");
        }
        return flag;
    }

    private static User queryUserById(int uid) {
        User user = null;
        try {
            user = adminDao.queryUserById(uid);
        } catch (Exception e) {
            System.out.println("查找用户信息失败");
        }
        return user;
    }

    private static List<User> queryAllPersonByType(int utype) {
        List<User> users = new ArrayList<>();
        try {
            users = adminDao.queryAllPersonByType(utype);
        } catch (Exception e) {
            System.out.println("查询所有普通用户出错！");
        }
        return users;
    }

    public static boolean queryPersonByType(int utype, String role) {
        List<User> ordinaryPersonList = queryAllPersonByType(utype);
        boolean flag = true;
        if (ordinaryPersonList != null && ordinaryPersonList.size() != 0) {
            System.out.println("该平台所有的" + role + "用户有：");
            for (User user0 : ordinaryPersonList) {
                System.out.println(user0);
            }
        } else {
            System.out.println("查找该平台的" + role + "用户失败！");
            flag = false;
        }
        return flag;
    }

    public static void deleteOrUpdateCinemaManagerInfo(Scanner sc, String operation) {
        System.out.println("请输入你要" + operation + "的管理员账号：");
        String cinemaManagerNo = sc.next();
        User user1 = queryUserByUserNo(cinemaManagerNo);
        if (user1 != null && user1.getUid() > 0) {
            boolean flag = true;
            if (operation.equals("删除")) {
                flag = deleteCinemaManager(user1.getUid());
            } else if (operation.equals("修改")) {
                flag = updateCinemaManager(sc, user1.getUid());
            }
            if (flag == true) {
                System.out.println(operation + "该影院管理员相关信息成功！");
            } else {
                System.out.println(operation + "该影院管理员相关信息失败！");
            }
        }
    }

    private static boolean updateCinemaManager(Scanner sc, int uid) {
        boolean flag = true;
        try {
            System.out.println("请输入要修改后的影院管理员账号：");
            String cinemaManagerNo = sc.next();
            System.out.println("请输入要修改后的影院管理员密码：");
            String cinemaManagerPass = sc.next();
            flag = adminDao.updateCinemaManager(uid, cinemaManagerNo, cinemaManagerPass);
        } catch (Exception e) {
            System.out.println("删除该用户管理员相关信息出错！");
        }
        return flag;
    }
}
