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

create table batch (
	id int not null auto_increment,
    start_month date not null,
    end_month date null,
    duration int,
    primary key (id)
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

create table session (
	id int not null auto_increment,
    sem int not null,
    year int not null,
    status varchar(10) not null,
    primary key (id)
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
insert into user (first_name, last_name, email, phone, join_date) values ('Aditya' ,'Kumar', 'adi@iitrpr.ac.in', '1234567890', '2020-07-27');
insert into user (first_name, last_name, email, phone, join_date) values ('Ayush' ,'Shukla', 'ayush@iitrpr.ac.in', '1234567890', '2021-07-27');
insert into user (first_name, last_name, email, phone, join_date) values ('Sriya' ,'Sharma', 'shriya@iitrpr.ac.in', '1234567890', '2021-07-27');
insert into user (first_name, last_name, email, phone, join_date) values ('Krishna' ,'Kumar', 'krishna@iitrpr.ac.in', '1234567890', '2022-07-27');
insert into user (first_name, last_name, email, phone, join_date) values ('Raj' ,'Saha', 'raj@iitrpr.ac.in', '1234567890', '2010-07-27');
insert into user (first_name, last_name, email, phone, join_date) values ('Raunak' ,'Sinha', 'raunak@iitrpr.ac.in', '1234567890', '2015-07-27');
insert into user (first_name, last_name, email, phone, join_date) values ('Abhinav' ,'Jha', 'abhinav@iitrpr.ac.in', '1234567890', '2021-07-27');
insert into batch (start_month, end_month, duration) values ('2020-07-27', '2022-06-01', 24);
insert into batch (start_month, end_month, duration) values ('2021-07-27', '2023-06-01', 24);
insert into batch (start_month, end_month, duration) values ('2022-07-27', '2024-06-01', 24);
insert into student (entryno, user_id, batch_id) values ('2020CSM1008', 1, 1);
insert into student (entryno, user_id, batch_id) values ('2021CSM1010', 2, 2);
insert into student (entryno, user_id, batch_id) values ('2021CSM1002', 3, 2);
insert into student (entryno, user_id, batch_id) values ('2022CSM1001', 4, 3);
insert into faculty (user_id) values (5);
insert into faculty (user_id) values (6);
insert into academicoffice (user_id) values (7);
insert into auth (user_id, password) values (1, 'aditya');
insert into auth (user_id, password) values (2, 'ayush');
insert into auth (user_id, password) values (3, 'sriya');
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
insert into prerequisite (course_id, prereq_course_id) values (2, 1);
insert into prerequisite (course_id, prereq_course_id) values (4, 1);
insert into prerequisite (course_id, prereq_course_id) values (5, 1);
insert into prerequisite (course_id, prereq_course_id) values (5, 2);
insert into prerequisite (course_id, prereq_course_id) values (5, 3);
insert into prerequisite (course_id, prereq_course_id) values (6, 5);
insert into prerequisite (course_id, prereq_course_id) values (6, 3);
insert into prerequisite (course_id, prereq_course_id) values (7, 4);
insert into prerequisite (course_id, prereq_course_id) values (8, 3);
insert into prerequisite (course_id, prereq_course_id) values (8, 5);
insert into prerequisite (course_id, prereq_course_id) values (9, 3);
insert into prerequisite (course_id, prereq_course_id) values (9, 4);
insert into prerequisite (course_id, prereq_course_id) values (9, 5);
insert into prerequisite (course_id, prereq_course_id) values (9, 2);
insert into courseoffer (course_id, faculty_id, session_id) select course.id, faculty.id, session.id from course join faculty join session where course.id = faculty.id+1;
insert into session (sem, year, status) values (1, 2022, "ACTIVE"),(1, 2021, "ACTIVE"),(2, 2021, "COMPLETED"),(1, 2020, "COMPLETED"),(2, 2020, "COMPLETED");