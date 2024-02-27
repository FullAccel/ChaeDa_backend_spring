INSERT INTO textbook (textbook_id, name, image_url, last_page_num, target_grade)
VALUES (1, '쎈_고등수학상_2024',
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_profile/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%89%E1%85%AE%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%A1%E1%86%BC_2024.jpeg',
        224, '고1'),
       (2, '쎈_고등수학하_2024',
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_profile/%E1%84%8A%E1%85%A6%E1%86%AB_%EC%88%98%ED%95%99%ED%95%98_2024.jpeg',
        160, '고1'),
       (3, '쎈_수1_2024',
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_profile/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%89%E1%85%AE1_2024.jpeg',
        184, '고2'),
       (4, '쎈_수2_2024',
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_profile/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%89%E1%85%AE2_2024.jpeg',
        168, '고2'),
       (5, '쎈_미적분_2024',
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_profile/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%86%E1%85%B5%E1%84%8C%E1%85%A5%E1%86%A8%E1%84%87%E1%85%AE%E1%86%AB_2024.jpeg',
        200, '고3'),
       (6, '쎈_기하_2024',
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_profile/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%80%E1%85%B5%E1%84%92%E1%85%A1_2024.jpeg',
        128, '고3'),
       (7, '쎈_확률과통계_2024',
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_profile/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%92%E1%85%AA%E1%86%A8%E1%84%85%E1%85%B2%E1%86%AF%E1%84%80%E1%85%AA%E1%84%90%E1%85%A9%E1%86%BC%E1%84%80%E1%85%A8_2024.jpeg',
        120, '고3')
;

INSERT INTO member (member_id, address, email, gender, name, phone_number, profile_url, dtype)
VALUES (1, '서울시 광진구', 'parksejoon313@gmail.com', '남', '홍길동', '010-5318-6577', 'https://www.OO.com/data/file/~.webp',
        'Teacher'),
       (2, '서울시 광진구', 'parksejoon313@gmail.com', '남', '홍길동', '010-5318-6577', 'https://www.OO.com/data/file/~.webp',
        'Teacher'),
       (3, '서울시 광진구', 'parksejoon313@gmail.com', '남', '홍길동', '010-5318-6577', 'https://www.OO.com/data/file/~.webp',
        'Teacher'),
       (4, '서울시 광진구', 'parksejoon313@gmail.com', '남', '홍길동', '010-5318-6577', 'https://www.OO.com/data/file/~.webp',
        'Student'),
       (5, '서울시 광진구', 'parksejoon313@gmail.com', '남', '홍길동', '010-5318-6577', 'https://www.OO.com/data/file/~.webp',
        'Student'),
       (6, '서울시 광진구', 'parksejoon313@gmail.com', '남', '홍길동', '010-5318-6577', 'https://www.OO.com/data/file/~.webp',
        'Student')
;

INSERT INTO teacher (member_id, subject, notes)
VALUES (1, '수학', null),
       (2, '수학', null),
       (3, '수학', null)
;

INSERT INTO student (member_id, home_phone_num, parent_phone_num, school_name, subject, notes)
VALUES (4, '02-5318-6577', '010-5318-6577', '건국고등학교', '수학', null),
       (5, '02-5318-6577', '010-5318-6577', '건국고등학교', '수학', null),
       (6, '02-5318-6577', '010-5318-6577', '건국고등학교', '수학', null)
;

INSERT INTO class_group (class_group_id, name, teacher_member_id, grade, lesson_days, profile_url)
VALUES (1, '고등 월수금반 3시', 1, '고3', 'MWF', 'https://www.OO.com/data/file/~.webp'),
       (2, '고등 월수금반 3시', 1, '고3', 'MWF', 'https://www.OO.com/data/file/~.webp'),
       (3, '고등 월수금반 3시', 1, '고3', 'MWF', 'https://www.OO.com/data/file/~.webp'),
       (4, '고등 월수금반 3시', 1, '고3', 'MWF', 'https://www.OO.com/data/file/~.webp'),
       (5, '고등 월수금반 3시', 1, '고3', 'MWF', 'https://www.OO.com/data/file/~.webp'),
       (6, '고등 월수금반 3시', 1, '고3', 'MWF', 'https://www.OO.com/data/file/~.webp')
;

INSERT INTO course (course_id, class_group_class_group_id, student_member_id)
VALUES (1, 1, 4),
       (2, 1, 5),
       (3, 1, 6)
;