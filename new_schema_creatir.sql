drop database if exists aimsdb;
create database aimsdb;
use aimsdb;

create table if not exists user (
	id int not null auto_increment,
    first_name varchar(20) not null,
    last_name varchar(20) null,
    email varchar(50) unique not null,
    phone varchar(11) null,
    join_date date not null,
    
    primary key (id)
);

create table faculty (
	id int not null auto_increment,
    user_id int not null,
    primary key (id),
    foreign key (user_id) references user(id) on delete cascade
);

create table academicoffice (
	id int not null auto_increment,
    user_id int not null,
    primary key (id),
    foreign key (user_id) references user(id) on delete cascade
);

create table session (
	id int not null auto_increment,
    sem int not null,
    year int not null,
    status varchar(10) not null,
    primary key (id)
);


create table batch (
	id int not null auto_increment,
    start_session int not null,
    end_session int,
    duration int,
    primary key (id),
    foreign key (start_session) references session(id),
    foreign key (end_session) references session(id)
);
create table student (
	id int not null auto_increment,
    entryno varchar(100) not null unique,
    user_id int not null,
    batch_id int not null,
    primary key (id),
    foreign key (user_id) references user(id) on delete cascade,
    foreign key (batch_id) references batch(id) on delete cascade
);

create table auth (
	user_id int not null unique,
    password varchar(100) not null,
    foreign key (user_id) references user (id) on delete cascade
);

create table loginsession (
	id int not null auto_increment,
    user_id int not null,
    timestamp datetime not null,
    primary key (id),
    foreign key (user_id) references user (id) on delete cascade
);

create table course (
	id int not null auto_increment,
    code varchar(10) not null,
    title varchar(100) not null,
    description varchar(255),
    credit varchar(10),
    primary key (id)
);

create table prerequisite (
	course_id int not null,
    prereq_course_id int not null,
    foreign key (course_id) references course (id) on delete cascade,
    foreign key (prereq_course_id) references course (id) on delete cascade
);


create table courseoffer (
	id int not null auto_increment,
    course_id int not null,
    faculty_id int not null,
    session_id int not null,
    primary key (id),
    foreign key (course_id) references course (id) on delete cascade,
    foreign key (faculty_id) references faculty (id) on delete cascade,
    foreign key (session_id) references session (id) on delete cascade
);

create table offertype (
	batch_id int not null,
    course_id int not null,
    type varchar(10) not null,
    primary key (batch_id, course_id),
    foreign key (batch_id) references batch (id) on delete cascade,
    foreign key (course_id) references course (id) on delete cascade
);

create table courseregister (
	id int not null auto_increment,
    student_id int not null,
    offer_id int not null,
    grade int,
    creditsreceived int,
    status varchar(10) not null,
    primary key (id),
    foreign key (student_id) references student (id) on delete cascade,
    foreign key (offer_id) references courseoffer (id) on delete cascade
);

create table mtpinfo (
	id int not null auto_increment,
    student_id int null,
    faculty_id int not null,
    title varchar(100) not null,
    domains varchar(255),
    credits int,
    primary key (id),
    foreign key (student_id) references student (id) on delete cascade,
    foreign key (faculty_id) references faculty (id) on delete cascade
);


-- DATA INSERTION
insert into session (sem, year, status) values (1, 2022, "ACTIVE"),(1, 2021, "COMPLETED"),(2, 2021, "COMPLETED"),(1, 2020, "COMPLETED"),(2, 2020, "COMPLETED");
insert into user (first_name, last_name, email, phone, join_date) values ('Aditya' ,'Kumar', 'adi@iitrpr.ac.in', '1234567890', '2020-07-27');
insert into user (first_name, last_name, email, phone, join_date) values ('Ayush' ,'Shukla', 'ayush@iitrpr.ac.in', '1234567890', '2021-07-27');
insert into user (first_name, last_name, email, phone, join_date) values ('Sriya' ,'Sharma', 'shriya@iitrpr.ac.in', '1234567890', '2021-07-27');
insert into user (first_name, last_name, email, phone, join_date) values ('Krishna' ,'Kumar', 'krishna@iitrpr.ac.in', '1234567890', '2022-07-27');
insert into user (first_name, last_name, email, phone, join_date) values ('Raj' ,'Saha', 'raj@iitrpr.ac.in', '1234567890', '2010-07-27');
insert into user (first_name, last_name, email, phone, join_date) values ('Raunak' ,'Sinha', 'raunak@iitrpr.ac.in', '1234567890', '2015-07-27');
insert into user (first_name, last_name, email, phone, join_date) values ('Abhinav' ,'Jha', 'abhinav@iitrpr.ac.in', '1234567890', '2021-07-27');
insert into batch (start_session, end_session, duration) values (4, 3, 24);
insert into batch (start_session, end_session, duration) values (2, 1, 24);
insert into batch (start_session, end_session, duration) values (1, 1, 24);
insert into student (entryno, user_id, batch_id) values ('2020CSM1008', 1, 1);
insert into student (entryno, user_id, batch_id) values ('2021CSM1010', 2, 2);
insert into student (entryno, user_id, batch_id) values ('2021CSM1002', 3, 2);
insert into student (entryno, user_id, batch_id) values ('2022CSM1001', 4, 3);
insert into faculty (user_id) values (5);
insert into faculty (user_id) values (6);
insert into academicoffice (user_id) values (7);
insert into auth (user_id, password) values (1, 'aditya');
insert into auth (user_id, password) values (2, 'ayush');
insert into auth (user_id, password) values (3, 'shriya');
insert into auth (user_id, password) values (4, 'krishna');
insert into auth (user_id, password) values (5, 'raj');
insert into auth (user_id, password) values (6, 'raunak');
insert into auth (user_id, password) values (7, 'abhinav');
insert into course (code, title, description, credit) values ("CS506","Data structure and algorithm","Data structure and Algorithms","3-1-2-6-4");
insert into course (code, title, description, credit) values ("CS509","PG Software Lab","PG Software Lab","0-0-6-6-3");
insert into course (code, title, description, credit) values ("CS526","Mathematical Foundation of Computer Science","Mathematical Foundation of Computer Science","3-1-0-5-3");
insert into course (code, title, description, credit) values ("CS527","Computer Systems","Computer Systems","3-0-2-7-4");
insert into course (code, title, description, credit) values ("CS501","Data Science and Machine Learning","Data Science and Machine Learning","3-0-2-6-4");
insert into course (code, title, description, credit) values ("CS502","Reinforcement Learning","Reinforcement Learning","2-0-2-5-3");
insert into course (code, title, description, credit) values ("CS503","Internet of Things","Internet of Things","3-0-0-6-3");
insert into course (code, title, description, credit) values ("CS504","Digital Image Processing","Digital image processing","2-1-2-4-3");
insert into course (code, title, description, credit) values ("CS505","Spatial Computing","Spatial computing","3-0-0-6-3");
insert into prerequisite (course_id, prereq_course_id) values (5, 1), (5, 3), (6, 3), (7, 4), (8, 1), (8, 3), (9, 2);
insert into offertype (batch_id, course_id, type) values (1, 1, "CORE"), (1, 2, "CORE"), (1, 3, "CORE"), (1, 4, "CORE"),(1, 5, "ELECTIVE"),(1, 6, "ELECTIVE"),(1, 7, "ELECTIVE"),(1, 8, "ELECTIVE"),(1, 9, "ELECTIVE");
insert into offertype (batch_id, course_id, type) values (2, 1, "CORE"), (2, 2, "ELECTIVE"), (2, 3, "CORE"), (2, 4, "CORE"),(2, 5, "ELECTIVE"),(2, 6, "ELECTIVE"),(2, 7, "ELECTIVE"),(2, 8, "ELECTIVE"),(2, 9, "ELECTIVE");
insert into offertype (batch_id, course_id, type) values (3, 1, "CORE"), (3, 2, "CORE"), (3, 3, "CORE"), (3, 4, "ELECTIVE"),(3, 5, "ELECTIVE"),(3, 6, "ELECTIVE"),(3, 7, "ELECTIVE"),(3, 8, "ELECTIVE"),(3, 9, "ELECTIVE");

insert into courseoffer (course_id, faculty_id, session_id) values (1, 1, 4), (2, 1, 4), (3, 2, 4), (4, 2, 4);
insert into courseregister (student_id, offer_id, grade, creditsreceived, status) values (1, 1, 8, 4, 'COMPLETED'), (1, 2, 0, 0, 'DROPPED'), (1, 3, 8, 3, 'COMPLETED'), (1, 4, 9, 4, 'COMPLETED');
insert into courseoffer (course_id, faculty_id, session_id) values (5, 1, 5), (6, 1, 5), (7, 2, 5), (8, 2, 5);
insert into courseregister (student_id, offer_id, grade, creditsreceived, status) values (1, 5, 7, 4, 'COMPLETED'), (1, 6, 9, 3, 'COMPLETED'), (1, 7, 8, 3, 'COMPLETED'), (1, 8, 7, 3, 'COMPLETED');

insert into courseoffer (course_id, faculty_id, session_id) values (1, 1, 2), (2, 1, 2), (3, 2, 2), (4, 2, 2);
insert into courseregister (student_id, offer_id, grade, creditsreceived, status) values (2, 9, 7, 4, 'COMPLETED'), (2, 10, 9, 3, 'COMPLETED'), (2, 11, 8, 3, 'FAILED'), (2, 12, 7, 3, 'COMPLETED'), (1, 10, 9, 3, 'COMPLETED');
insert into courseregister (student_id, offer_id, grade, creditsreceived, status) values (3, 9, 7, 4, 'COMPLETED'), (3, 10, 9, 3, 'COMPLETED'), (3, 11, 8, 3, 'COMPLETED'), (3, 12, 7, 3, 'COMPLETED');
insert into courseoffer (course_id, faculty_id, session_id) values (5, 1, 3), (6, 1, 3), (7, 2, 3), (8, 2, 3), (9, 1, 3);
insert into courseregister (student_id, offer_id, grade, creditsreceived, status) values (1, 17, 8, 3, 'COMPLETED'), (2, 15, 8, 3, 'COMPLETED'), (2, 17, 7, 3, 'COMPLETED');
insert into courseregister (student_id, offer_id, grade, creditsreceived, status) values (3, 13, 8, 4, 'COMPLETED'), (3, 14, 9, 3, 'COMPLETED'), (3, 15, 8, 3, 'COMPLETED'), (3, 16, 9, 3, 'COMPLETED');

insert into courseoffer (course_id, faculty_id, session_id) values (1, 1, 1), (2, 1, 1), (3, 2, 1), (4, 2, 1);
insert into courseregister (student_id, offer_id, grade, creditsreceived, status) values (4, 18, 0, 0, 'ENROLLED'), (4, 19, 0, 0, 'ENROLLED'), (4, 20, 0, 0, 'ENROLLED'), (2, 20, 0, 0, 'ENROLLED');-- , (4, 21, 0, 0, 'ENROLLED');