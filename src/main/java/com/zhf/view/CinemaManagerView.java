package com.zhf.view;

import com.zhf.bean.Cinema;
import com.zhf.bean.Room;
import com.zhf.bean.Sessions;
import com.zhf.bean.User;
import com.zhf.dao.CinemaManagerDao;
import com.zhf.dao.impl.CinemaManagerDaoImpl;

import java.util.List;
import java.util.Scanner;

/**
 * Created on 2019/10/23 0023.
 */
public class CinemaManagerView {
    private static CinemaManagerDao cinemaManagerDao = new CinemaManagerDaoImpl();

    public static void CinemaManagerMainView(Scanner sc, User user) {
        while (true) {
            try {
                System.out.println("****************************");
                System.out.println("1.影院管理");
                System.out.println("2.影厅管理");
                System.out.println("3.场次管理");
                System.out.println("4.退出系统");
                String choose = sc.next();
                if ("1".equals(choose)) {
                    List<Cinema> cinemaList = queryAllCinema(user);
                    if (cinemaList != null && cinemaList.size() != 0) {
                        System.out.println("当前影院管理员所管理的电影院有：");
                        for (Cinema cinema : cinemaList) {
                            System.out.println(cinema);
                        }
                    }

                    System.out.println("1.增加影院");
                    if (cinemaList != null && cinemaList.size() != 0) {
                        System.out.println("2.删除影院");
                    }

                    System.out.println("请选择你要进行的操作：");
                    choose = sc.next();
                    if (choose.equals("1")) {
                        System.out.println("请输入要增加影院的名称：");
                        String cname = sc.next();
                        //判断电影院名称是否已经存在
                        boolean exists = checkCinemaExist(cname);
                        if (exists) {
                            System.out.println("影院已存在！");
                            continue;
                        }
                        System.out.println("请输入要增加影院的城市：");
                        String city = sc.next();
                        System.out.println("请输入要增加影院的地址：");
                        String address = sc.next();
                        int rows = addCinema(user, cname, city, address);
                        if (rows > 0) {
                            System.out.println("增加电影院成功！");
                        } else {
                            System.out.println("增加电影院失败！");
                        }
                    } else if (cinemaList != null && cinemaList.size() != 0 && choose.equals("2")) {
                        System.out.print("请输入要删除的影院Id:");
                        int cid = Integer.parseInt(sc.next());
                        int rows = deleteCinemaByCid(user, cid);
                        System.out.println(rows);
                        if (rows > 0) {
                            System.out.println("删除影院成功！");
                        } else {
                            System.out.println("删除影院失败");
                        }
                    } else {
                        System.out.println("输入操作错误！");
                    }

                } else if ("2".equals(choose)) {
                    List<Room> roomList = queryAllRoom(user);
                    if (roomList != null && roomList.size() != 0) {
                        System.out.println("该影院管理员所管理的影厅有：");
                        for (Room room : roomList) {
                            System.out.print(String.format("%-10d", room.getRid()));
                            System.out.print(room);
                            System.out.println(room.getCinema().getcName());
                        }
                    }
                    System.out.println("1.增加影厅");
                    if (roomList != null && roomList.size() != 0) {
                        System.out.println("2.删除影厅");
                        System.out.println("3.修改影厅");
                    }
                    System.out.println("请选择你要进行的操作：");
                    choose = sc.next();
                    if (choose.equals("1")) {
                        System.out.println("请输入要增加影厅对应的影院名称：");
                        String cname = sc.next();
                        //查询有无该影院管理员对应的影院
                        Cinema cinema = queryCinemaByfname(user, cname);
                        if (cinema != null && cinema.getCid() != 0) {
                            System.out.println("请输入你要添加的影厅名称：");
                            String rname = sc.next();
                            System.out.println("请输入你要添加的影厅价格：");
                            double rprice = Double.parseDouble(sc.next());
                            System.out.println("请输入你要添加的影厅大小：");
                            String rsize = sc.next();
                            int cid = cinema.getCid();
                            int rows = addRoom(user, cid, rname, rprice, rsize);
                            if (rows > 0) {
                                System.out.println("添加影厅成功！");
                            } else {
                                System.out.println("添加影厅失败");
                            }
                        } else {
                            System.out.println("输入的电影院不在当前影院管理员范围内");
                        }
                    } else if (roomList != null && roomList.size() != 0 && choose.equals("2")) {
                        System.out.println("请输入需要删除影厅对应的电影院:");
                        String cname = sc.next();
                        //查询有无该影院管理员对应的影院
                        Cinema cinema = queryCinemaByfname(user, cname);
                        if (cinema != null && cinema.getCid() != 0) {
                            System.out.println("请输入要删除的影厅id:");
                            int rId = Integer.parseInt(sc.next());
                            int rows = deleteRoomByrId(cinema.getCid(), rId);
                            if (rows > 0) {
                                System.out.println("删除影厅成功!");
                            } else {
                                System.out.println("删除影厅失败！");
                            }
                        }
                    } else if (roomList != null && roomList.size() != 0 && choose.equals("3")) {
                        System.out.println("请输入需要修改影厅对应的电影院:");
                        String cname = sc.next();
                        //查询有无该影院管理员对应的影院
                        Cinema cinema = queryCinemaByfname(user, cname);
                        if (cinema != null && cinema.getCid() != 0) {
                            System.out.println("请输入要修改的影厅id:");
                            int rId = Integer.parseInt(sc.next());
                            System.out.println("请输入要后的影厅价格:");
                            double price = Double.parseDouble(sc.next());
                            int rows = updateRoomByrId(cinema.getCid(), rId, price);
                            if (rows > 0) {
                                System.out.println("修改影厅价格成功!");
                            } else {
                                System.out.println("修改影厅价格失败!");
                            }
                        }
                    } else {
                        System.out.println("输入操作出错！");
                    }
                } else if ("3".equals(choose)) {
                    List<Sessions> sessionList = queryAllSessions(user);
                    if (sessionList != null && sessionList.size() != 0) {
                        System.out.println("当前影院管理员所管理的场次有：");
                        for (Sessions sessions : sessionList) {
                            System.out.print(sessions);
                            System.out.println(sessions.getFilm().getfName());
                        }
                    }
                    // 增添场次
                    System.out.println("1.增加场次");
                    if (sessionList != null && sessionList.size() != 0) {
                        System.out.println("2.删除场次");
                        System.out.println("3.修改场次");
                    }
                    System.out.println("请选择你要进行的操作：");
                    choose = sc.next();
                    if (choose.equals("1")) {
                        Cinema cinema = inputAndQueryCinema(sc, user, "增加");
                        if (cinema != null && cinema.getCid() != 0) {
                            Room room = inputAndQueryRoom(sc, cinema, "增加");
                            if (room != null && room.getRid() != 0) {
                                System.out.println("请输入要增加的场次开始时间：");
                                String startTime = sc.next();
                                String[] startTimestrs = startTime.split("[|]");
                                startTime = startTimestrs[0] + " " + startTimestrs[1];
                                System.out.println("请输入要增加的场次结束时间：");
                                String endTime = sc.next();
                                String[] endTimestrs = endTime.split("[|]");
                                endTime = endTimestrs[0] + " " + endTimestrs[1];
                                if (startTime.compareTo(endTime) >= 0) {
                                    System.out.println("输入开始时间大于结束时间！输入错误");
                                    continue;
                                }
                                /**有四种情况，会导致排的场次发生冲突：
                                 * 1.取出starttime或者endtime在其他开始和结束时间范围内就不行
                                 * 2.starttime和其他时间一样不行
                                 * 3.endtime和其他时间一样不行
                                 * 4.其他时间的开始时间大于starttime并且其他时间的结束时间小于endtime
                                 * */
                                //取出所有其他场次信息
                                boolean isSimilar = false;
                                for (Sessions sessions : sessionList) {
                                    String otherSessionStartTime = sessions.getStartTime();
                                    String otherSessionEndTime = sessions.getEndTime();
                                    int startCompar = startTime.compareTo(otherSessionStartTime);
                                    int endCompar = endTime.compareTo(otherSessionEndTime);
                                    int startToOhterEndCompar = startTime.compareTo(otherSessionEndTime);
                                    int endToOhterStartCompar = endTime.compareTo(otherSessionStartTime);
                                    if (room.getName().equals(sessions.getRoom().getName()) && (startCompar == 0 || endCompar == 0 || (startCompar > 0 && startToOhterEndCompar < 0) || (endToOhterStartCompar > 0 && endToOhterStartCompar < 0) || (startCompar < 0 && endCompar > 0))) {
                                        System.out.println("要增加的场次信息和其他场次信息时间段冲突，添加场次失败");
                                        isSimilar = true;
                                        break;
                                    }
                                }
                                if (isSimilar) {
                                    continue;
                                }
                                System.out.println("请输入要增加场次对应的电影名称：");
                                String filmName = sc.next();

                                int rows = addSession(startTime, endTime, room.getRid(), filmName);
                                if (rows > 0) {
                                    System.out.println("添加场次成功！");
                                } else {
                                    System.out.println("添加场次失败！");
                                }
                            } else {
                                System.out.println("输入的影厅不在当前影院管理员范围内");

                            }
                        } else {
                            System.out.println("输入的电影院不在当前影院管理员范围内");
                        }
                    } else if (sessionList != null && sessionList.size() != 0 && choose.equals("2")) {
                        Cinema cinema = inputAndQueryCinema(sc, user, "删除");
                        if (cinema != null && cinema.getCid() != 0) {
                            Room room = inputAndQueryRoom(sc, cinema, "删除");
                            if (room != null && room.getRid() != 0) {
                                System.out.println("请输入要删除的场次Id:");
                                int sId = Integer.parseInt(sc.next());
                                int rows = deleteSessionById(sId, room);
                                if (rows > 0) {
                                    System.out.println("删除场次信息成功！");
                                } else {
                                    System.out.println("删除场次信息失败！");
                                }
                            } else {
                                System.out.println("输入的影厅不在当前影院管理员范围内");
                            }
                        } else {
                            System.out.println("输入的电影院不在当前影院管理员范围内");
                        }
                    } else if (sessionList != null && sessionList.size() != 0 && choose.equals("3")) {
                        Cinema cinema = inputAndQueryCinema(sc, user, "修改");
                        if (cinema != null && cinema.getCid() != 0) {
                            Room room = inputAndQueryRoom(sc, cinema, "修改");
                            if (room != null && room.getRid() != 0) {
                                System.out.println("请输入要修改的场次Id:");
                                int sId = Integer.parseInt(sc.next());
                                System.out.println("请输入要修改后的电影名称：");
                                String fname = sc.next();
                                int rows = updateSessionById(sId, room, fname);
                                if (rows > 0) {
                                    System.out.println("修改场次信息成功！");
                                } else {
                                    System.out.println("修改场次信息失败！");
                                }
                            } else {
                                System.out.println("输入的影厅不在当前影院管理员范围内");
                            }
                        }
                    } else {
                        System.out.println("输入选项错误!");
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

    private static int updateRoomByrId(int cid, int rId, double price) {
        int rows = 0;
        try {
            rows = cinemaManagerDao.updateRoomByrId(cid, rId, price);
        } catch (Exception e) {
            System.out.println("更新影院价格出错！");
        }
        return rows;
    }

    private static int updateSessionById(int sId, Room room, String fname) {
        int rows = 0;
        try {
            rows = cinemaManagerDao.updateSessionById(sId, room, fname);
        } catch (Exception e) {
            System.out.println("更新场次信息出错！");
        }
        return rows;
    }

    private static int deleteSessionById(int sId, Room room) {
        int rows = 0;
        try {
            rows = cinemaManagerDao.deleteSessionById(sId, room);
        } catch (Exception e) {
            System.out.println("删除场次信息失败！");
        }
        return rows;
    }

    private static int addSession(String startTime, String endTime, int rid, String filmName) {
        int rows = 0;
        try {
            rows = cinemaManagerDao.addSession(startTime, endTime, rid, filmName);
        } catch (Exception e) {
            System.out.println("添加场次信息出错");
        }
        return rows;
    }

    private static Room queryRoomById(int rId, int cId) {
        Room room = null;
        try {
            room = cinemaManagerDao.queryRoomById(rId, cId);
        } catch (Exception e) {
            System.out.println("查找影厅失败！");
        }
        return room;
    }

    private static List<Sessions> queryAllSessions(User user) {
        List<Sessions> sessionsList = null;
        try {
            sessionsList = cinemaManagerDao.queryAllSessions(user);
        } catch (Exception e) {
            System.out.println("查询该影院管理员安排场次信息失败");
        }
        return sessionsList;
    }

    private static int deleteRoomByrId(int cid, int rId) {
        int rows = 0;
        try {
            rows = cinemaManagerDao.deleteRoomByrId(cid, rId);
        } catch (Exception e) {
            System.out.println("删除影厅出错!");
        }
        return rows;
    }

    private static int addRoom(User user, int cId, String rname, double rprice, String rsize) {
        int rows = 0;
        try {
            rows = cinemaManagerDao.addRoom(user, cId, rname, rprice, rsize);
        } catch (Exception e) {
            System.out.println("添加影厅出错");
        }
        return rows;
    }

    private static Cinema queryCinemaByfname(User user, String cname) {
        Cinema cinema = null;
        try {
            cinema = cinemaManagerDao.queryCinemaByfname(user, cname);
        } catch (Exception e) {
            System.out.println("查找该影院管理员对应" + cname + "影院信息失败");
        }
        return cinema;
    }

    private static List<Room> queryAllRoom(User user) {
        List<Room> roomList = null;
        try {
            roomList = cinemaManagerDao.queryAllRoom(user);
        } catch (Exception e) {
            System.out.println("查询该管理员场次信息失败");
        }
        return roomList;
    }

    private static boolean checkCinemaExist(String cname) {
        boolean exists = true;
        try {
            exists = cinemaManagerDao.checkCinemaExist(cname);
        } catch (Exception e) {
            System.out.println("查询该城市的电影院失败");
        }
        return exists;
    }

    private static int deleteCinemaByCid(User user, int cid) {
        int rows = 0;
        try {
            rows = cinemaManagerDao.deleteCinemaByCid(user, cid);
        } catch (Exception e) {
            System.out.println("删除电影院失败！");
        }
        return rows;
    }

    private static int addCinema(User user, String cname, String city, String address) {
        int rows = 0;
        try {
            rows = cinemaManagerDao.addCinema(user, cname, city, address);
        } catch (Exception e) {
            System.out.println("新增电影院失败！");
        }
        return rows;
    }

    private static List<Cinema> queryAllCinema(User user) {
        List<Cinema> cinemaList = null;
        try {
            cinemaList = cinemaManagerDao.queryAllCinema(user);
        } catch (Exception e) {
            System.out.println("查找该管理员所有管理电影院信息失败");
        }
        return cinemaList;
    }

    //封装方法
    public static Cinema inputAndQueryCinema(Scanner sc, User user, String operation) {
        System.out.println("请输入要" + operation + "场次对应的影院名称：");
        String cname = sc.next();
        //查询有无该影院管理员对应的影院
        Cinema cinema = queryCinemaByfname(user, cname);
        return cinema;
    }

    public static Room inputAndQueryRoom(Scanner sc, Cinema cinema, String operation) {
        System.out.println("请输入你要" + operation + "场次对应的影厅Id：");
        int rId = Integer.parseInt(sc.next());
        Room room = queryRoomById(rId, cinema.getCid());
        return room;
    }
}
