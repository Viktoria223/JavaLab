create table teacher
(
    id serial primary key,
    first_name varchar(25) not null,
    last_name varchar(25) not null,
    work_experience integer not null
);

create table course
(
    id serial primary key,
    name varchar(25) not null,
    date varchar(25) not null,
    teacher_id integer,
    foreign key (teacher_id) references teacher(id)
);

create table lesson
(
    id serial primary key,
    name varchar(25) not null,
    date varchar(25) not null,
    course_id integer not null,
    foreign key (course_id) references course(id)
);



create table student
(
    id serial primary key,
    first_name varchar(25) not null,
    last_name varchar(25) not null,
    group_number varchar (25) not null
);

create table student_course
(
    course_id  integer,
    student_id integer,
    foreign key (course_id) references course (id),
    foreign key (student_id) references student (id)
);

create table teacher_course
(
    course_id  integer,
    teacher_id integer,
    foreign key (course_id) references course (id),
    foreign key (teacher_id) references teacher (id)
);

