/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/5/16 15:40:40                           */
/*==============================================================*/


drop table if exists data_dict;

drop table if exists employee;

drop table if exists play;

drop table if exists sale;

drop table if exists sale_item;

drop table if exists schedule;

drop table if exists seat;

drop table if exists studio;

drop table if exists ticket;

/*==============================================================*/
/* Table: data_dict                                             */
/*==============================================================*/
create table data_dict
(
   dict_id              int not null auto_increment,
   dict_parent_id       int,
   dict_index           int,
   dict_name            varchar(200),
   dict_value           varchar(100) not null,
   primary key (dict_id)
);

/*==============================================================*/
/* Table: employee                                              */
/*==============================================================*/
create table employee
(
   emp_id               int not null auto_increment,
   emp_no               char(20) not null,
   emp_name             varchar(100) not null,
   emp_tel_num          char(20),
   emp_addr             varchar(200),
   emp_email            varchar(100),
   primary key (emp_id)
);
--
-- 转存表中的数据 `data_dict`
--

INSERT INTO `data_dict` (`dict_id`, `dict_parent_id`, `dict_index`, `dict_name`, `dict_value`) VALUES
(1, NULL, 1, '数据字典', '根'),
(2, 1, 2, 'PLAYTYPE', '影片类型'),
(3, 1, 3, 'PLAYLANG', '影片语言'),
(4, 1, 4, '锁失效时间', '5'),
(5, 2, 1, 'costume drama', '古装剧'),
(6, 2, 2, 'Time_Travel', '穿越剧'),
(7, 2, 3, 'life Drama', '生活剧'),
(8, 3, 1, 'chinese', '汉语'),
(9, 3, 2, 'English', '英语'),
(10, 3, 3, 'Korea', '韩语');

/*==============================================================*/
/* Table: play                                                  */
/*==============================================================*/
create table play
(
   play_id              int not null auto_increment,
   play_type_id         int,
   play_lang_id         int,
   play_name            varchar(200),
   play_introduction    varchar(2000),
   play_image          varchar(100),
   play_length          int,
   play_ticket_price    numeric(10,2),
   play_status          smallint comment 'È¡Öµº¬Òå£º
            0£º´ý°²ÅÅÑÝ³ö
            1£ºÒÑ°²ÅÅÑÝ³ö
            -1£ºÏÂÏß',
   primary key (play_id)
);

/*==============================================================*/
/* Table: sale                                                  */
/*==============================================================*/
create table sale
(
   sale_ID              bigint not null auto_increment,
   emp_id               int,
   sale_time            datetime,
   sale_payment         decimal(10,2),
   sale_change          numeric(10,2),
   sale_type            smallint comment 'Àà±ðÈ¡Öµº¬Òå£º
            1£ºÏúÊÛµ¥
            -1£ºÍË¿îµ¥',
   sale_status          smallint comment 'ÏúÊÛµ¥×´Ì¬ÈçÏÂ£º
            0£º´ú¸¶¿î
            1£ºÒÑ¸¶¿î',
   primary key (sale_ID)
);

/*==============================================================*/
/* Table: sale_item                                             */
/*==============================================================*/
create table sale_item
(
   sale_item_id         bigint not null auto_increment,
   ticket_id            bigint,
   sale_ID              bigint,
   sale_item_price      numeric(10,2),
   primary key (sale_item_id)
);

/*==============================================================*/
/* Table: schedule                                              */
/*==============================================================*/
create table schedule
(
   sched_id             int not null auto_increment,
   studio_id            int,
   play_id              int,
   sched_time           datetime not null,
   sched_ticket_price   numeric(10,2),
   primary key (sched_id)
);

/*==============================================================*/
/* Table: seat                                                  */
/*==============================================================*/
create table seat
(
   seat_id              int not null auto_increment,
   studio_id            int,
   seat_row             int,
   seat_column          int,
   primary key (seat_id)
);

/*==============================================================*/
/* Table: studio                                                */
/*==============================================================*/
create table studio
(
   studio_id            int not null auto_increment,
   studio_name          varchar(100) not null,
   studio_row_count     int,
   studio_col_count     int,
   studio_introduction  varchar(2000),
   primary key (studio_id)
);

/*==============================================================*/
/* Table: ticket                                                */
/*==============================================================*/
create table ticket
(
   ticket_id            bigint not null auto_increment,
   seat_id              int,
   sched_id             int,
   ticket_price         numeric(10,2),
   ticket_status        smallint comment 'º¬ÒåÈçÏÂ£º
            0£º´ýÏúÊÛ
            1£ºËø¶¨
            9£ºÂô³ö',
   ticket_locked_time   datetime,
   primary key (ticket_id)
);

-------------------------------------------sign 是用户注册和登录修改密码
CREATE TABLE IF NOT EXISTS `sign` (
  `name` varchar(20) NOT NULL,
  `pass` varchar(20) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
------------------------------------------- end 是最后的保存票据的数据库

CREATE TABLE IF NOT EXISTS `end` (
 sale_id int primary key auto_increment,
  `play_name` varchar(50) NOT NULL,
  `studio_id` int(11) DEFAULT NULL,
  `sched_time` datetime DEFAULT NULL,
  `ticket_price` decimal(10,2) DEFAULT NULL,
  `set_row` int(11) DEFAULT NULL,
  `set_col` int(11) DEFAULT NULL,
  `play_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table data_dict add constraint FK_super_child_dict foreign key (dict_parent_id)
      references data_dict (dict_id) on delete restrict on update restrict;

alter table play add constraint FK_dict_lan_play foreign key (play_lang_id)
      references data_dict (dict_id) on delete restrict on update restrict;

alter table play add constraint FK_dict_type_play foreign key (play_type_id)
      references data_dict (dict_id) on delete restrict on update restrict;

alter table sale add constraint FK_employee_sale foreign key (emp_id)
      references employee (emp_id) on delete restrict on update restrict;

alter table sale_item add constraint FK_sale_sale_item foreign key (sale_ID)
      references sale (sale_ID) on delete restrict on update restrict;

alter table sale_item add constraint FK_ticket_sale_item foreign key (ticket_id)
      references ticket (ticket_id) on delete restrict on update restrict;

alter table schedule add constraint FK_play_sched foreign key (play_id)
      references play (play_id) on delete restrict on update restrict;

alter table schedule add constraint FK_studio_sched foreign key (studio_id)
      references studio (studio_id) on delete restrict on update restrict;

alter table seat add constraint FK_studio_seat foreign key (studio_id)
      references studio (studio_id) on delete restrict on update restrict;

alter table ticket add constraint FK_sched_ticket foreign key (sched_id)
      references schedule (sched_id) on delete restrict on update restrict;

alter table ticket add constraint FK_seat_ticket foreign key (seat_id)
      references seat (seat_id) on delete restrict on update restrict;

