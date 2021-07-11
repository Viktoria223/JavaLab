insert into teacher(first_name, last_name, work_experience) values ('Стас', 'Логинов', 15);
insert into teacher(first_name, last_name, work_experience) values ('Владимир', 'Кислов', 30);
insert into teacher(first_name, last_name, work_experience) values ('Светлана', 'Быстрова', 5);
insert into teacher(first_name, last_name, work_experience) values ('Елена', 'Светлова', 17);
insert into teacher(first_name, last_name, work_experience) values ('Николай', 'Копнов', 38);

insert into student(first_name, last_name, group_number) values ('Кристина', 'Фокина', 01.086);
insert into student(first_name, last_name, group_number) values ('Евгений', 'Нужнов', 77.135);
insert into student(first_name, last_name, group_number) values ('Алина', 'Акишина', 15.221);
insert into student(first_name, last_name, group_number) values ('Дмитрий', 'Легков', 11.001);
insert into student(first_name, last_name, group_number) values ('Марина', 'Сафронова', 21.200);

insert into course(id, name, date, teacher_id) values (01, 'История', '01.02.2020/22.02.2020', 3);
insert into course(id, name, date, teacher_id) values (02, 'Биология', '21.03.2019/15.07.2020', 4);
insert into course(id, name, date, teacher_id) values (03, 'Обществозание', '17.10.2018/23.05.2019', 2);
insert into course(id, name, date, teacher_id) values (04, 'Математика', '09.01.2020/30.02.2021', 1);
insert into course(id, name, date, teacher_id) values (05, 'Информатика', '04.11.2019/28.12.2019', 5);

insert into lesson(id, name, date, course_id) values (21, 'Математика', '10.01.2020', 04);
insert into lesson(id, name, date, course_id) values (22, 'История', '02.02.2020', 01);
insert into lesson(id, name, date, course_id) values (23, 'Информатика', '05.11.2019', 05);
insert into lesson(id, name, date, course_id) values (24, 'Обществознание', '18.10.2018', 03);
insert into lesson(id, name, date, course_id) values (25, 'Биология', '22.03.2019', 02);

insert into student_course(course_id, student_id) values (01, 1);
insert into student_course(course_id, student_id) values (05, 3);
insert into student_course(course_id, student_id) values (03, 4);
insert into student_course(course_id, student_id) values (02, 2);
insert into student_course(course_id, student_id) values (04, 5);

insert into teacher_course(course_id, teacher_id) values (03, 1);
insert into teacher_course(course_id, teacher_id) values (05, 3);
insert into teacher_course(course_id, teacher_id) values (01, 5);
insert into teacher_course(course_id, teacher_id) values (02, 2);
insert into teacher_course(course_id, teacher_id) values (04, 4);