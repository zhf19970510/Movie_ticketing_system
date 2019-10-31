create database filmDB character set utf8 collate utf8_unicode_ci;
use filmDB;

create table users(
uid int auto_increment primary key,
userNo varchar(30) unique not null,
upass varchar(30) default '1234' not null,
utype int default 0 not null,-- 0 普通用户  1影院管理员   2 管理员
money double default 100
);

-- 插入管理员
insert into users(userNo,utype) values('charles',2);

-- 插入影院管理员
insert into users(userNo,utype) values('jack',1);
insert into users(userNo,utype) values('rouse',1);
insert into users(userNo,utype) values('marry',1);
-- 插入普通用户

insert into users(userNo,utype) values('tom',0);
insert into users(userNo,utype) values('jam',0);
insert into users(userNo,utype) values('tina',0);
insert into users(userNo,utype) values('moto',0);

select * from users;

create table cinema(
cid int auto_increment primary key,
cname varchar(30) unique not null,
city varchar(30) default '上海' not null,
address varchar(50) not null,
uid int -- 主外键约束
);
insert into cinema(cname,address) values('万达影城','金山区龙皓路1088号万达广场四楼');
insert into cinema(cname,address) values('中宏影城','宝山区殷高西路555号西区3楼');
insert into cinema(cname,address) values('国泰电影院','黄浦区淮海中路870号');

select * from cinema;

create table room(
rid int auto_increment primary key,
rname varchar(30) unique not null,
rprice double default 88.88 not null,
rsize varchar(30) default '10,10' not null,   -- x,y  x:行数  y:列数
cid int 
);

select * from room;
insert into room(rname,cid) values('激光1号厅',1);
insert into room(rname,cid) values('激光2号厅',1);
insert into room(rname,cid) values('激光3号厅',1);


insert into room(rname,cid) values('DTS巨幕厅1号厅',2);
insert into room(rname,cid) values('DTS巨幕厅2号厅',2);
insert into room(rname,cid) values('DTS巨幕厅3号厅',2);


insert into room(rname,cid) values('1号厅',3);
insert into room(rname,cid) values('2号厅',3);
insert into room(rname,cid) values('3号厅',3);


select * from room;
create table sessions(
sid int  auto_increment primary key,
startTime varchar(30) not null,
endTime varchar(30) not null,
rid int,
fid int 
);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 12:00','2019-10-21 14:00',1,1);

insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 14:00','2019-10-21 16:00',1,1);

insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 16:00','2019-10-21 18:00',1,2);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 18:00','2019-10-21 20:00',1,2);



insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 12:00','2019-10-21 14:00',2,4);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 14:00','2019-10-21 16:00',2,4);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 16:00','2019-10-21 18:00',2,3);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 18:00','2019-10-21 20:00',2,3);


insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 12:00','2019-10-21 14:00',3,5);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 14:00','2019-10-21 16:00',3,5);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 16:00','2019-10-21 18:00',3,6);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 18:00','2019-10-21 20:00',3,6);


insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 12:00','2019-10-21 14:00',4,4);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 14:00','2019-10-21 16:00',4,4);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 16:00','2019-10-21 18:00',4,3);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 18:00','2019-10-21 20:00',4,3);


insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 12:00','2019-10-21 14:00',5,4);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 14:00','2019-10-21 16:00',5,4);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 16:00','2019-10-21 18:00',5,3);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 18:00','2019-10-21 20:00',5,3);


insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 12:00','2019-10-21 14:00',6,5);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 14:00','2019-10-21 16:00',6,5);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 16:00','2019-10-21 18:00',6,6);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 18:00','2019-10-21 20:00',6,6);


insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 12:00','2019-10-21 14:00',7,4);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 14:00','2019-10-21 16:00',7,4);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 16:00','2019-10-21 18:00',7,3);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 18:00','2019-10-21 20:00',7,3);


insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 12:00','2019-10-21 14:00',8,5);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 14:00','2019-10-21 16:00',8,5);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 16:00','2019-10-21 18:00',8,6);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 18:00','2019-10-21 20:00',8,6);

insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 12:00','2019-10-21 14:00',9,1);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 14:00','2019-10-21 16:00',9,1);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 16:00','2019-10-21 18:00',9,2);
insert into sessions(startTime,endTime,rid,fid)
values('2019-10-21 18:00','2019-10-21 20:00',9,2);
select * from sessions;


create table film(
fid int auto_increment primary key,
fname varchar(30) unique not null,
ftype varchar(10) not null,
fintroduce varchar(255) default '暂无简介'
);
insert into film(fname,ftype) values('双子杀手','动作');
insert into film(fname,ftype) values('沉睡的魔咒2','惊悚');
insert into film(fname,ftype) values('海贼王·狂热行动','动漫');
insert into film(fname,ftype) values('哪吒之魔童降世','动漫');
insert into film(fname,ftype) values('登山者','冒险');
insert into film(fname,ftype) values('喜剧之王','喜剧');
insert into film(fname,ftype) values('夏洛特烦恼','喜剧');
insert into film(fname,ftype) values('卧虎藏龙','武侠');
insert into film(fname,ftype) values('倚天屠龙记','武侠');
select * from film;
create table comments(
commentsId int auto_increment primary key,
uid int ,
fid int ,
fscore double default 6 not null,
content varchar(50)  
);

create table orders(
oid int auto_increment primary key,
sid int,
uid int,
seat varchar(10)  -- x,y  座位在第x行  第 y列);

select * from cinema;
select * from film;
select * from room;
select * from sessions;
select * from users;

-- 用户模块  查询 所有排了片的电影信息


create view cinemaView 
as
select
users.`uid`,users.`money`,users.`userNo`,
cinema.`cid`,cinema.`cname`,cinema.`city`,
room.`rid`,room.`rname`,room.`rsize`,room.`rprice`,
sessions.`sid`,sessions.`startTime`,sessions.`endTime`,
film.`fid`,film.`fname`,film.`ftype`,film.`fintroduce`
from users,cinema,room,sessions,film
where users.`uid`=cinema.`uid` and  cinema.`cid`=room.`cid`
and room.`rid`=sessions.`rid` and sessions.`fid`=film.`fid`;
-- 1. 所有排了片子的电影名字
select  distinct fname from cinemaView;

-- 2. 查看所有排了片子的电影详情
select * from film where fname in(select  distinct fname from cinemaView);

-- 选择想看的电影  输入 想看电影的编号  查询排了片的电影院信息

select distinct cname from cinemaView where fid=4;

-- 查看的是电影院的详情

select * from cinema where cname in(select distinct cname from cinemaView where fid=4);
 
-- 根据电影院的编号  cid  查询安排了此电影的场次
select sid from cinemaView where fid=4;

-- 
select sid,startTime,endTime,fname,cname from cinemaView where sid in (select sid from cinemaView where fid=4 and cid=2);

