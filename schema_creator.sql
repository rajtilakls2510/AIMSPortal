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
    entryno varchar(10) not null unique,
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
