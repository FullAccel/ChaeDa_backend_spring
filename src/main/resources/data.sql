INSERT INTO textbook (textbook_id, name, image_url, last_page_num, target_grade)
VALUES (1, '쎈_고등수학상_2024',
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_thumbnail/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%89%E1%85%AE%E1%84%92%E1%85%A1%E1%86%A8(%E1%84%89%E1%85%A1%E1%86%BC)_2024.jpeg',
        224, '고1'),
       (2, '쎈_고등수학하_2024',
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_thumbnail/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%80%E1%85%A9%E1%84%83%E1%85%B3%E1%86%BC%E1%84%89%E1%85%AE%E1%84%92%E1%85%A1%E1%86%A8(%E1%84%92%E1%85%A1)_2024.jpeg',
        160, '고1'),
       (3, '쎈_수1_2024',
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_thumbnail/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%89%E1%85%AE1_2024.jpeg',
        184, '고2'),
       (4, '쎈_수2_2024',
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_thumbnail/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%89%E1%85%AE2_2024.jpeg',
        168, '고2'),
       (5, '쎈_미적분_2024',
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_thumbnail/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%86%E1%85%B5%E1%84%8C%E1%85%A5%E1%86%A8%E1%84%87%E1%85%AE%E1%86%AB_2024.jpeg',
        200, '고3'),
       (6, '쎈_기하_2024',
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_thumbnail/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%80%E1%85%B5%E1%84%92%E1%85%A1_2024.jpeg',
        128, '고3'),
       (7, '쎈_확률과통계_2024',
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_thumbnail/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%92%E1%85%AA%E1%86%A8%E1%84%85%E1%85%B2%E1%86%AF%E1%84%80%E1%85%AA%E1%84%90%E1%85%A9%E1%86%BC%E1%84%80%E1%85%A8_2024.jpeg',
        120, '고3')
;

INSERT INTO member (member_id, address, email, gender, name, phone_number, dtype)
VALUES (1, '서울시 광진구', 'parksejoon313@gmail.com', '남', '홍길동', '010-5318-6577', 'Teacher'),
       (2, '서울시 광진구', 'parksejoon313@gmail.com', '남', '홍길동', '010-5318-6577', 'Teacher'),
       (3, '서울시 광진구', 'parksejoon313@gmail.com', '남', '홍길동', '010-5318-6577', 'Teacher'),
       (4, '서울시 광진구', 'parksejoon313@gmail.com', '남', '홍길동', '010-5318-6577', 'Student'),
       (5, '서울시 광진구', 'parksejoon313@gmail.com', '남', '홍길동', '010-5318-6577', 'Student'),
       (6, '서울시 광진구', 'parksejoon313@gmail.com', '남', '홍길동', '010-5318-6577', 'Student')
;

INSERT INTO teacher (member_id, subject, notes)
VALUES (1, '수학', null),
       (2, '수학', null),
       (3, '수학', null)
;

INSERT INTO student (member_id, home_phone_num, parent_phone_num, school_name, subject, notes, grade)
VALUES (4, '02-5318-6577', '010-5318-6577', '건국고등학교', '수학', null, 'HIGH_1'),
       (5, '02-5318-6577', '010-5318-6577', '건국고등학교', '수학', null, 'HIGH_2'),
       (6, '02-5318-6577', '010-5318-6577', '건국고등학교', '수학', null, 'HIGH_3')
;



INSERT INTO class_group (class_group_id, name, teacher_member_id, grade, lesson_days)
VALUES (1, '고등 월수금반 3시', 1, 'HIGH_3', 'MWF'),
       (2, '고등 월수금반 3시', 1, 'HIGH_2', 'MWF'),
       (3, '고등 월수금반 3시', 1, 'HIGH_1', 'MWF'),
       (4, '고등 월수금반 3시', 1, 'N_SU', 'MWF'),
       (5, '고등 월수금반 3시', 1, 'HIGH_3', 'MWF'),
       (6, '고등 월수금반 3시', 1, 'HIGH_2', 'MWF')
;

INSERT INTO course (course_id, class_group_class_group_id, student_member_id)
VALUES (1, 1, 4),
       (2, 1, 5),
       (3, 1, 6)
;

INSERT INTO announcement (announcement_id, title, content, class_group_class_group_id, dtype)
VALUES (1, '숙제공지 제목', '이번 숙제는 잘해와라', 1, 'hw_announcement'),
       (2, '숙제공지 제목', '이번 숙제는 잘해와라', 1, 'hw_announcement'),
       (3, '숙제공지 제목', '이번 숙제는 잘해와라', 1, 'hw_announcement'),
       (4, '숙제공지 제목', '이번 숙제는 잘해와라', 1, 'hw_announcement'),
       (5, '숙제공지 제목', '이번 숙제는 잘해와라', 1, 'hw_announcement'),
       (6, '숙제공지 제목', '이번 숙제는 잘해와라', 1, 'hw_announcement')
;

INSERT INTO hw_announcement(announcement_id, dead_line_date, dead_line_date_time, end_page, start_page, submission_num,
                            teacher_member_id, textbook_textbook_id)
VALUES (1, '2024-04-26', '2024-04-26 00:00:00.000', 50, 56, 0, 1, 1),
       (2, '2024-04-27', '2024-04-27 00:00:00.000', 50, 56, 0, 1, 1),
       (3, '2024-04-28', '2024-04-28 00:00:00.000', 50, 56, 0, 1, 1),
       (4, '2024-04-29', '2024-04-29 00:00:00.000', 50, 56, 0, 2, 2),
       (5, '2024-04-30', '2024-04-30 00:00:00.000', 50, 56, 0, 2, 2),
       (6, '2024-05-01', '2024-05-01 00:00:00.000', 50, 56, 0, 2, 2)
;

INSERT INTO submission(submission_id, complete_status, hw_announcement_id, student_member_id)
VALUES (1, true, 1, 4),
       (2, true, 1, 5),
       (3, true, 1, 6),
       (4, true, 2, 4),
       (5, true, 2, 5),
       (6, true, 2, 6),
       (7, true, 3, 4),
       (8, true, 3, 5),
       (9, true, 3, 6),
       (10, true, 4, 4),
       (11, true, 4, 5),
       (12, true, 4, 6),
       (13, true, 5, 4),
       (14, true, 5, 5),
       (15, true, 5, 6),
       (16, true, 6, 4),
       (17, true, 6, 5),
       (18, true, 6, 6)
;

INSERT INTO image(image_id, member_id, submission_submission_id, image_key, image_file_extension, image_type)
VALUES (1, 4, 1, 'be97fa56-b20a-459b-ae13-ec7615546e8e', 'PNG', 'HOMEWORK_SUBMISSION'),
       (2, 5, 2, 'be97fa56-b20a-459b-ae13-ec7615546e8e', 'PNG', 'HOMEWORK_SUBMISSION'),
       (3, 6, 3, 'be97fa56-b20a-459b-ae13-ec7615546e8e', 'PNG', 'HOMEWORK_SUBMISSION'),
       (4, 4, 4, 'be97fa56-b20a-459b-ae13-ec7615546e8e', 'PNG', 'HOMEWORK_SUBMISSION'),
       (5, 5, 5, 'be97fa56-b20a-459b-ae13-ec7615546e8e', 'PNG', 'HOMEWORK_SUBMISSION'),
       (6, 6, 6, 'be97fa56-b20a-459b-ae13-ec7615546e8e', 'PNG', 'HOMEWORK_SUBMISSION'),
       (7, 4, 7, 'be97fa56-b20a-459b-ae13-ec7615546e8e', 'PNG', 'HOMEWORK_SUBMISSION'),
       (8, 5, 8, 'be97fa56-b20a-459b-ae13-ec7615546e8e', 'PNG', 'HOMEWORK_SUBMISSION'),
       (9, 6, 9, 'be97fa56-b20a-459b-ae13-ec7615546e8e', 'PNG', 'HOMEWORK_SUBMISSION'),
       (10, 4, 10, 'be97fa56-b20a-459b-ae13-ec7615546e8e', 'PNG', 'HOMEWORK_SUBMISSION'),
       (11, 5, 11, 'be97fa56-b20a-459b-ae13-ec7615546e8e', 'PNG', 'HOMEWORK_SUBMISSION'),
       (12, 6, 12, 'be97fa56-b20a-459b-ae13-ec7615546e8e', 'PNG', 'HOMEWORK_SUBMISSION'),
       (13, 4, 13, 'be97fa56-b20a-459b-ae13-ec7615546e8e', 'PNG', 'HOMEWORK_SUBMISSION'),
       (14, 5, 14, 'be97fa56-b20a-459b-ae13-ec7615546e8e', 'PNG', 'HOMEWORK_SUBMISSION'),
       (15, 6, 15, 'be97fa56-b20a-459b-ae13-ec7615546e8e', 'PNG', 'HOMEWORK_SUBMISSION'),
       (16, 4, 16, 'be97fa56-b20a-459b-ae13-ec7615546e8e', 'PNG', 'HOMEWORK_SUBMISSION'),
       (17, 5, 17, 'be97fa56-b20a-459b-ae13-ec7615546e8e', 'PNG', 'HOMEWORK_SUBMISSION'),
       (18, 6, 18, 'be97fa56-b20a-459b-ae13-ec7615546e8e', 'PNG', 'HOMEWORK_SUBMISSION')
;


INSERT INTO image(image_id, member_id, image_key, image_file_extension, image_type)
VALUES (19, 1, 'test', 'PNG', 'CLASS_GROUP_PROFILE'),
       (20, 1, 'test', 'PNG', 'CLASS_GROUP_PROFILE'),
       (21, 1, 'test', 'PNG', 'CLASS_GROUP_PROFILE'),
       (22, 1, 'test', 'PNG', 'CLASS_GROUP_PROFILE'),
       (23, 1, 'test', 'PNG', 'CLASS_GROUP_PROFILE'),
       (24, 1, 'test', 'PNG', 'CLASS_GROUP_PROFILE'),
       (25, 1, 'member1', 'PNG', 'MEMBER_PROFILE'),
       (26, 2, 'member2', 'PNG', 'MEMBER_PROFILE'),
       (27, 3, 'member3', 'PNG', 'MEMBER_PROFILE'),
       (28, 4, 'member4', 'PNG', 'MEMBER_PROFILE'),
       (29, 5, 'member5', 'PNG', 'MEMBER_PROFILE'),
       (30, 6, 'member6', 'PNG', 'MEMBER_PROFILE')
;

UPDATE class_group
SET image_id = 19
WHERE class_group_id = 1;
UPDATE class_group
SET image_id = 20
WHERE class_group_id = 2;
UPDATE class_group
SET image_id = 21
WHERE class_group_id = 3;
UPDATE class_group
SET image_id = 22
WHERE class_group_id = 4;
UPDATE class_group
SET image_id = 23
WHERE class_group_id = 5;
UPDATE class_group
SET image_id = 24
WHERE class_group_id = 6;

UPDATE member
SET image_id = 25
WHERE member_id = 1;
UPDATE member
SET image_id = 26
WHERE member_id = 2;
UPDATE member
SET image_id = 27
WHERE member_id = 3;
UPDATE member
SET image_id = 28
WHERE member_id = 4;
UPDATE member
SET image_id = 29
WHERE member_id = 5;
UPDATE member
SET image_id = 30
WHERE member_id = 6;

SELECT SETVAL('class_group_class_group_id_seq', (SELECT MAX(class_group_id) FROM class_group));
SELECT SETVAL('image_image_id_seq', (SELECT MAX(image_id) FROM image));
SELECT SETVAL('course_course_id_seq', (SELECT MAX(course_id) FROM course));
SELECT SETVAL('submission_submission_id_seq', (SELECT MAX(submission_id) FROM submission));
SELECT SETVAL('member_member_id_seq', (SELECT MAX(member_id) FROM member));
SELECT SETVAL('announcement_announcement_id_seq', (SELECT MAX(announcement_id) FROM announcement));