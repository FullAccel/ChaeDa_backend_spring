INSERT INTO textbook (textbook_id, name, image_url, last_page_num, target_grade)
VALUES
    (1, '쎈 고등 수학 1-1"', 'https://www.OO.com/data/file/~.webp', 256, '고1'),
    (2, '쎈 고등 수학 1-2"', 'https://www.OO.com/data/file/~.webp', 300, '고1'),
    (3, '쎈 고등 수학 2-1"', 'https://www.OO.com/data/file/~.webp', 256, '고2'),
    (4, '쎈 고등 수학 2-2"', 'https://www.OO.com/data/file/~.webp', 300, '고2'),
    (5, '쎈 고등 수학 3-1"', 'https://www.OO.com/data/file/~.webp', 256, '고3'),
    (6, '쎈 고등 수학 3-2"', 'https://www.OO.com/data/file/~.webp', 300, '고3')
;

INSERT INTO member (member_id, address, email, gender, name, phone_number, profile_url, dtype)
VALUES
    (1, '서울시 광진구', 'parksejoon313@gmail.com', '남', '홍길동', '010-5318-6577', 'https://www.OO.com/data/file/~.webp', 'Teacher'),
    (2, '서울시 광진구', 'parksejoon313@gmail.com', '남', '홍길동', '010-5318-6577', 'https://www.OO.com/data/file/~.webp', 'Teacher'),
    (3, '서울시 광진구', 'parksejoon313@gmail.com', '남', '홍길동', '010-5318-6577', 'https://www.OO.com/data/file/~.webp', 'Teacher'),
    (4, '서울시 광진구', 'parksejoon313@gmail.com', '남', '홍길동', '010-5318-6577', 'https://www.OO.com/data/file/~.webp', 'Student'),
    (5, '서울시 광진구', 'parksejoon313@gmail.com', '남', '홍길동', '010-5318-6577', 'https://www.OO.com/data/file/~.webp', 'Student'),
    (6, '서울시 광진구', 'parksejoon313@gmail.com', '남', '홍길동', '010-5318-6577', 'https://www.OO.com/data/file/~.webp', 'Student')
;

INSERT INTO teacher (member_id, subject, notes)
VALUES
    (1, '수학', null),
    (2, '수학', null),
    (3, '수학', null)
;

INSERT INTO student (member_id, home_phone_num, parent_phone_num, school_name, subject, notes)
VALUES
    (4, '02-5318-6577', '010-5318-6577', '건국고등학교', '수학', null),
    (5, '02-5318-6577', '010-5318-6577', '건국고등학교', '수학', null),
    (6, '02-5318-6577', '010-5318-6577', '건국고등학교', '수학', null)
;

INSERT INTO class_group (class_group_id, name, teacher_member_id, grade, lesson_days, profile_url)
VALUES
    (1, '고등 월수금반 3시', 1, '고3', 'MWF', 'https://www.OO.com/data/file/~.webp'),
    (2, '고등 월수금반 3시', 1, '고3', 'MWF', 'https://www.OO.com/data/file/~.webp'),
    (3, '고등 월수금반 3시', 1, '고3', 'MWF', 'https://www.OO.com/data/file/~.webp'),
    (4, '고등 월수금반 3시', 1, '고3', 'MWF', 'https://www.OO.com/data/file/~.webp'),
    (5, '고등 월수금반 3시', 1, '고3', 'MWF', 'https://www.OO.com/data/file/~.webp'),
    (6, '고등 월수금반 3시', 1, '고3', 'MWF', 'https://www.OO.com/data/file/~.webp')
;

INSERT INTO course (course_id, class_group_class_group_id, student_member_id)
VALUES
    (1, 1, 4),
    (2, 1, 5),
    (3, 1, 6)
;