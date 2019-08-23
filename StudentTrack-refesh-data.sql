-- REFESH DATA
SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM courses;
DELETE FROM course_structure;
DELETE FROM classes;
DELETE FROM students;
DELETE FROM non_students;
DELETE FROM classes_has_students;
DELETE FROM lecturers;
DELETE FROM lecturers_has_classes;
DELETE FROM skills;
DELETE FROM lecturers_has_skills;

alter table classes auto_increment = 1;
alter table course_structure auto_increment = 1;
alter table courses auto_increment = 1;
alter table lecturers auto_increment = 1;
alter table students auto_increment = 1;
alter table non_students auto_increment = 1;
alter table classes_has_students auto_increment = 1;
alter table skills auto_increment = 1;

INSERT INTO courses(NAME_COURSES, DETAIL, DURATION )
VALUES ('JAVA', 'Buổi 1, Start, Cài đặt cấu hình biến môi trường Phương pháp lộ trình học Cách thức học hợp lý -
				 Buổi 2, FOP, Chương trình hướng cấu trúc FOP Hướng dẫn cách tạo thư viện riêng cho mỗi người -
				 Buổi 3, OPP theory, Tìm hiểu Class-Object Interface Abstract Class Boxing Unboxing Autoboxing -
                 Buổi 4, OPP PRO, OPP Anonymous class funtion Funtional Interface Default methoad
                 Buổi 5, Review, Kiểm tra trắc nghiệm Interview OPP lần I'
			  ,9),
		('PHP', 'Buổi 1, Làm việc với ngôn ngữ lập trình PHP -
				 Buổi 2, Làm việc với Form Data - 
                 Buổi 3, Làm việc với các câu lệnh điều khiển - 
                 Buổi 4, Làm việc với các kiểu dữ liệu - 
                 Buổi 5,  Làm việc với mảng (Array) - 
                 Buổi 6, Làm việc với Sessions và Cookies - 
                 Buổi 7, Làm việc với Functions - 
                 Buổi 8, Lập trình hướng đối tượng với PHP'
			  ,8),
		('.NET', 'Buổi 1,  Cơ bản về công nghệ .Net - 
				  Buổi 2, Giới thiệu về ngôn ngữ lập trình C# -
                  Buổi 3, Làm việc với Visual Studio 2015 -
                  Buổi 4, Lập trình với C# - 
                  Buổi 5, Lập trình hướng đối tượng với C#'
				,6),
		('Android', 'Buổi 1, Giới thiệu về Android -
					 Buổi 2, Làm việc với Android Studio -
                     Buổi 3, Phát triển và Test ứng dụng Android -
                     Buổi 4, Làm việc với layouts and widgets - 
                     Buổi 5, Làm việc với Event Handle - 
                     Buổi 6, Làm việc với themes and styles'
				  , 4),
		('Front End', 'Buổi 1, Các khái niệm cơ bản của một ứng dụng Web -
					   Buổi 2, Cấu trúc của một trang Web - 
                       Buổi 3, Sử dụng HTML và CSS để thiết kế một trang Web - 
                       Buổi 4, Các thành phần cơ bản của HTML và CSS - 
                       Buổi 5, Thiết kế trang web có khả năng Responsive'
					, 6);

INSERT INTO course_structure(CHAPTER_NAME, COURSES_ID)
VALUES('Làm quen với Java – OOP', 1),
      ('CSDL, mối quan hệ và truy vấn dữ liệu',1),
      ('Java Persistence with Hibernate',1),
      ('Quản lý tiến trình với Thread, Process',1),
      ('Design pattern, Regex, mô hình triển khai và thực thi',1),
      ('Thiết kế giao diện ứng dụng với Swing và JavaFx',1),
      ('Các components và xử lý sự kiện liên quan', 1),
      ('Hiệu ứng chuyển động với Graphics 2D', 1),
      ('Xây dựng projects cuối khóa học',1),
      ('Thiết kế Web (HTML5, CSS3, JavaScript, Bootstrap)',2),
      ('Lập trình web với PHP',2),
      ('Xây dựng ứng dụng web với PHP & MySQL',2),
      ('Làm việc với Laravel framework',2),
      ('Project – Xây dựng ứng dụng Web với Laravel framework',2),
      ('Kỹ năng tìm việc',2),
      ('Lập trình C#',3),
      ('Lập trình ứng dụng Windows Form với C#', 3),
      (' Phát triển ứng dụng Web trên ASP.NET với C# -Phần I', 3),
      ('Phát triển ứng dụng Web trên ASP.NET với C# -Phần II',3),
      ('Làm việc với .NET Core 2.0 và ASP.NET Core 2.0',3),
      ('Thiết kế phần mềm',3),
      ('Thực hiện dự án',3),
      ('Lập trình Java cho Android',4),
      ('Lập trình Android',4),
      ('Thực hiện dự án',4),
      ('Kỹ năng tìm việc',4),
      ('Thiết kế web với HTML5, CSS3 và Bootstrap',5),
      ('Lập trình JavaScript',5),
      ('Làm việc với jQuery',5),
      ('Làm việc với ReactJS',5),
      ('Thực hiện dự án',5),
      ('Kỹ năng mềm và tìm việc',5);
      
-- ALTER TABLE CLASSES ADD FOREIGN KEY (COURSE_ID) REFERENCES COURSES(ID);
      
INSERT INTO classes(NAME_CLASS, TIMETABLE, FEE, DATE_BEGIN, DATE_END, MIN_QTY_STUDENTS, MAX_QTY_STUDENTS, COURSE_ID)
VALUES('JAVA-01', 'Thứ 2 - 19h_21h, Thứ 4 - 19h_21h', 4200000,'2019/07/08','2020/04/08',20,40,1),
	  ('JAVA-02', 'Thứ 3 - 19h_21h, Thứ 5 - 19h_21h', 4200000,'2019/07/08','2020/04/08',20,40,1),
      ('JAVA-03', 'Thứ 2 - 7h_9h, Thứ 4 - 7h_9h', 4200000,'2019/07/08','2020/04/08',20,40,1),
      ('JAVA-04', 'Thứ 3 - 7h_9h, Thứ 5 - 7h_9h', 4200000,'2019/07/08','2020/04/08',20,40,1),
      ('JAVA-05', 'Thứ 7 - 7h_9h, CN - 7h_9h', 4200000,'2019/07/08','2020/04/08',20,40,1),
      ('PHP-01', 'Thứ 2 - 9h_11h, Thứ 5 - 9h_11h', 4200000,'2019/07/08','2020/04/08',20,40,2),
	  ('PHP-02', 'Thứ 3 - 9h_11h, Thứ 5 - 9h_11h', 4200000,'2019/07/08','2020/04/08',20,40,2),
	  ('.NET-01', 'Thứ 7 - 7h_9h, CN - 7h_9h', 4200000,'2019/07/08','2020/04/08',20,40,3),
	  ('.NET-02', 'Thứ 2 - 20h_22h, Thứ 4 - 20h_22h', 4200000,'2019/07/08','2020/04/08',20,40,3),
	  ('Android-01', 'Thứ 7 - 9h_10h, CN - 9h_10h', 4200000,'2019/07/08','2020/04/08',20,40,4),
	  ('Android-02', 'Thứ 3 - 7h_9h, Thứ 5 - 7h_9h', 4200000,'2019/07/08','2020/04/08',20,40,4),
      ('Front End-01', 'Thứ 3 - 7h_9h, Thứ 5 - 7h_9h', 4200000,'2019/07/08','2020/04/08',20,40,1),
      ('Front End-02', 'Thứ 2 - 9h_11h, Thứ 5 - 9h_11h', 4200000,'2019/07/08','2020/04/08',20,40,2),
	  ('Front End-03', 'Thứ 3 - 9h_11h, Thứ 5 - 9h_11h', 4200000,'2019/07/08','2020/04/08',20,40,2),
	  ('Front End-04', 'Thứ 7 - 7h_9h, CN - 7h_9h', 4200000,'2019/07/08','2020/04/08',20,40,3);

INSERT INTO STUDENTS(FULLNAME, PHONE, ADDRESS, EMAIL, DATE_OF_BIRTH, FBLINK, YEARTH,SCHOOL_NAME, CURRENT_WORKING)
VALUES('Nguyễn Văn Hải', 0973741903, 'Quảng Nam','zzvanhai81@gmail.com', '1997/06/20','https://www.facebook.com/van.hai.54943', 3, 'ĐH Sư Phạm',null),
	  ('Nguyễn Hoàng', 01685426854, 'Hà Tĩnh','nguyenhoang@gmail.com', '1997/08/11','https://www.facebook.com/nguyenhoang4875', 3, 'ĐH Bách Khoa',null),
      ('Hậu Bùi', 0988554795, 'Quảng Nam','haubui@gmail.com', '1997/11/26','https://www.facebook.com/vanhau1997', 4, 'ĐH Bách Khoa',null),
      ('Nhi Nguyễn', 0163451455, 'Huế','nhinguyen@gmail.com', '1997/08/20','https://www.facebook.com/profile.php?id=100009475637703', 4, 'ĐH Bách Khoa',null),
      ('Lê Thị Quỳnh Nhi', 0973741903, 'Huế','nhile@gmail.com', '1997/07/12','https://www.facebook.com/profile.php?id=100007538958538', 3, 'ĐH Bách Khoa',null),
      ('Đỗ Thị Minh Thúy', 0988554795, 'Đà Nẵng','thuydo@gmail.com', '1997/02/20','https://www.facebook.com/dothiminhthuy.dn', 3, 'ĐH Bách Khoa',null),
      ('Dương Huy Vũ', 0973741903, 'Quảng Trị','hoangvu@gmail.com', '1997/06/20','https://www.facebook.com/vu.felius', 3, 'ĐH Bách Khoa',null),
      ('Nguyên Nguyễn', 0988554795, 'Huế','nguyennguyen@gmail.com', '1997/06/20','https://www.facebook.com/profile.php?id=100004326316145', 4, 'ĐH Bách Khoa',null),
      ('Huỳnh Văn Mỹ', 0988554795, 'Quảng Nam','vanmy@gmail.com', '1997/11/26','https://www.facebook.com/huynh.van.my028', 3, 'ĐH Sư Phạm',null),
      ('Trần Thị Thanh Tiền', 0988554795, 'Quảng Nam','thanhtien1@gmail.com', '1997/06/20','https://www.facebook.com/profile.php?id=100008124666115', 3, 'ĐH Sư Phạm',null);
      
INSERT INTO NON_STUDENTS(FULLNAME, PHONE, ADDRESS, EMAIL, DATE_OF_BIRTH, FBLINK, CURRENT_WORKING)
VALUES('Nguyễn Văn Hòa', 0973741903, 'Quảng Nam','hoanguyen81@gmail.com', '1997/06/20','https://www.facebook.com/van.hai.54943', 'developer'),
	  ('Duy Nguyễn', 01685426854, 'Hà Tĩnh','duynguyen@gmail.com', '1997/08/11','https://www.facebook.com/nguyenhoang4875', 'developer'),
      ('Khanh Bùi', 0988554795, 'Quảng Nam','haubui@gmail.com', '1997/11/26','https://www.facebook.com/vanhau1997', 'developer'),
      ('Thông Nguyễn', 0163451455, 'Huế','nhinguyen@gmail.com', '1997/08/20','https://www.facebook.com/profile.php?id=100009475637703', 'developer'),
      ('Lê Thị Hòa', 0973741903, 'Huế','nhile@gmail.com', '1997/07/12','https://www.facebook.com/profile.php?id=100007538958538', 'tester'),
      ('Đỗ Thị Thúy', 0988554795, 'Đà Nẵng','thuydo@gmail.com', '1997/02/20','https://www.facebook.com/dothiminhthuy.dn', 'tester'),
      ('Trọng Thức', 0973741903, 'Quảng Trị','thucpt@gmail.com', '1997/06/20','https://www.facebook.com/vu.felius', 'tester'),
      ('An Trần', 0988554795, 'Huế','nguyennguyen@gmail.com', '1997/06/20','https://www.facebook.com/profile.php?id=100004326316145', 'tester'),
      ('Mỹ Mỹ', 0988554795, 'Quảng Nam','vanmy@gmail.com', '1997/11/26','https://www.facebook.com/huynh.van.my028', 'tester'),
      ('Dương Dương', 0988554795, 'Quảng Nam','thanhtien1@gmail.com', '1997/06/20','https://www.facebook.com/profile.php?id=100008124666115', 'tester');
      
INSERT INTO classes_has_students(STUDENT_ID, CLASSES_ID, TYPE_STUDENT, STATUS_STUDENT)
VALUES(1, 5, 1, 'Đang Học'),
      (2, 5, 1, 'Đang Học'),
	  (3, 5, 1, 'Đang Học'),
      (4, 5, 1, 'Đang Học'),
      (5, 5, 1, 'Đang Học'),
      (6, 5, 1, 'Đang Học'),
      (7, 5, 1, 'Đang Học'),
	  (8, 2, 1, 'Tạm Dừng'),
	  (9, 2, 1, 'Đã Nghĩ'),
	  (10, 5, 1, 'Tạm Dừng'),
      (1, 6, 2, 'Đang Học'),
      (2, 6, 2, 'Đang Học'),
	  (3, 7, 2, 'Đang Học'),
      (4, 8, 2, 'Đang Học'),
      (5, 8, 2, 'Đang Học'),
      (6, 12, 2, 'Đang Học'),
      (7, 12, 2, 'Đang Học'),
	  (8, 12, 2, 'Tạm Dừng'),
	  (9, 12, 2, 'Đã Nghĩ'),
	  (10, 15, 2, 'Tạm Dừng');
      
INSERT INTO LECTURERS(NAME_LECTURERS, JOB_DESCRIPTION, Email, WAGE, ADDRESS, DATE_OF_BIRTH, STATUS_LECTURERS)
VALUES('Trương Văn Khương', 'Phụ trách dạy lớp JAVA-01, JAVA-02', 'KhươngTrương@gmail.com', 10000000,'Quảng Nam', '1988/02/20', 'Đang dạy'),
	  ('Nguyễn Văn Thành', 'Phụ trách dạy lớp JAVA-03, JAVA-04', 'vanthanh@gmail.com', 10000000,'Đà nẵng', '1988/02/20', 'Đang dạy'),
      ('Võ Vang', 'Phụ trách dạy lớp JAVA05, PHP-01, PHP-02', 'vovang@gmail.com', 10000000,'Hà Nội', '1988/02/20', 'Đang dạy'),
      ('Hồ Anh Điền', 'Phụ trách dạy lớp .NET-01, ,NET-02', 'anhdien@gmail.com', 10000000,'Quảng Nam', '1988/02/20', 'Tạm Dừng'),
      ('Nguyễn Văn Linh', 'Phụ trách dạy lớp Android-01, Android-01', 'vanlinh@gmail.com', 10000000,'Quảng Trị', '1988/02/20', 'Đã Nghĩ'),
      ('Hồ Công Thắng', 'Phụ trách dạy lớp JAVA-05, Front End-01', 'KhươngTrương@gmail.com', 10000000,'Quảng Nam', '1988/02/20', 'Đang dạy'),
	  ('Hồ Thị Cúc', 'Phụ trách dạy Front End-02, Front End-03', 'vanthanh@gmail.com', 10000000,'Đà nẵng', '1988/02/20', 'Đang dạy'),
      ('Lê Thị Ánh tuyết', 'Phụ trách dạy lớp Front End-01, Front End-04', 'vovang@gmail.com', 10000000,'Hà Nội', '1988/02/20', 'Đang dạy'),
      ('An Trường', 'Phụ trách dạy lớp .NET-01, ,NET-02', 'anhdien@gmail.com', 10000000,'Quảng Nam', '1988/02/20', 'Tạm Dừng'),
      ('Công Danh', 'Phụ trách dạy lớp Android-01, Android-01', 'vanlinh@gmail.com', 10000000,'Quảng Trị', '1988/02/20', 'Đã Nghĩ');
      
INSERT INTO SKILLS(TECH)
VALUES('JAVA'),
	  ('php'),
      ('.net'),
      ('ANDROID'),
      ('FRONT-END');
      
INSERT INTO LECTURERS_HAS_SKILLS(LECTURERS_ID, SKILLS_ID, EXPY)
VALUES(1, 1, 3),
      (2, 1, 2),
      (3, 1, 3),
      (3, 2, 4),
      (4, 3, 4),
      (5, 5, 3),
      (6, 1, 2),
      (6, 5, 4),
      (7, 5, 8),
      (8, 5, 2),
      (9, 3, 6),
      (10, 4, 4);
      
INSERT INTO lecturers_has_classes(LECTURERS_ID, CLASSES_ID)
VALUES(1,1),
	  (1,2),
      (2,3),
      (2,4),
      (3,5),
      (3,6),
      (3,7),
      (4,8),
      (4,9),
      (5,10),
      (5,11),
      (6,5),
      (6,12),
      (7,13),
      (7,14),
      (8,12),
      (8,15),
      (9,8),
      (9,9),
      (10,10),
      (10,11);

SET FOREIGN_KEY_CHECKS = 1;