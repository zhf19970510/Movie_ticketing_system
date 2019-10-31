package com.zhf.view;

import com.zhf.bean.User;

import java.util.Scanner;

/**
 * Created on 2019/10/23 0023.
 */
public class MainView {
    static Scanner sc = new Scanner(System.in);

    static UsersView usersView = new UsersView();

    static CinemaManagerView cinemaManagerView = new CinemaManagerView();

    static AdminView adminView = new AdminView();

    public static void main(String[] args) {
        mainView();
    }

    public static void mainView() {
        User user = null;
        while (true) {
            System.out.println("**********欢迎进入电影院购票系统**********");
            System.out.println("请选择您要进行的操作：");
            System.out.println("1.登录\t\t2.注册\t\t3.退出当前系统");
            String choose = sc.next();
            switch (choose) {
                case "1":
                    System.out.print("请输入用户名：");
                    String userNo = sc.next();
                    System.out.print("请输入密码：");
                    String uPass = sc.next();
                    user = queryUser(userNo, uPass);
                    if (user != null && user.getUid() != 0) {
                        //对该用户进行一系列操作，包括普通用户，影院管理员，系统管理员
                        if (user.getuType() == 0) {
                            //普通用户
                            UsersView.UsersMainView(sc, user);
                        } else if (user.getuType() == 1) {
                            //影院管理员
                            CinemaManagerView.CinemaManagerMainView(sc, user);
                        } else if (user.getuType() == 2) {
                            //系统管理员
                            AdminView.AdminMainView(sc, user);
                        } else {
                            System.out.println("没有该类型用户！");
                        }
                    } else {
                        System.out.println("输入的用户名或密码错误！");
                    }
                    break;
                case "2":
                    System.out.println("请输入你要注册的账号：");
                    String uNo = sc.next();
                    System.out.println("请输入你要注册的密码：");
                    String uPass1 = sc.next();
                    System.out.println("请再次输入你注册的密码：");
                    String uPass2 = sc.next();
                    if (uPass1.equals(uPass2)) {
                        int rows = registerUser(uNo, uPass1);
                        if (rows > 0) {
                            System.out.println("注册成功！");
                        } else {
                            System.out.println("注册失败！");
                        }
                    } else {
                        System.out.println("两次输入的密码不一致！");
                        continue;
                    }
                    break;
                case "3":
                    System.out.println("退出系统");
                    System.exit(0);
                default:
                    System.out.println("输入错误，请重新输入!");
                    break;
            }
        }
    }

    private static int registerUser(String uNo, String uPass1) {
        int rows = 0;
        try {
            rows = usersView.registerUser(uNo, uPass1);
        } catch (Exception e) {
            System.out.println("注册用户出错");
        }
        return rows;
    }

    private static User queryUser(String userNo, String uPass) {
        User user = usersView.queryUser(userNo, uPass);
        return user;
    }
}
