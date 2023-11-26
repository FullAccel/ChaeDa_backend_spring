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
    (1, '서울시 광진구', 'parksejoon313@gmail.com', '남', '홍길동', '010-5318-6577', 'https://www.OO.com/data/file/~.webp', 'Teacher')
;

INSERT INTO teacher (member_id, subject, notes)
VALUES
    (1, '수학', null)
;

INSERT INTO class_group (class_group_id, name, teacher_member_id, grade, lesson_days, profile_url)
VALUES
    (1, '고등 월수금반 3시', 1, '고3', 'MWF', 'https://www.OO.com/data/file/~.webp')
    (2, '고등 월수금반 3시', 1, '고3', 'MWF', 'https://www.OO.com/data/file/~.webp'),
    (3, '고등 월수금반 3시', 1, '고3', 'MWF', 'https://www.OO.com/data/file/~.webp'),
    (4, '고등 월수금반 3시', 1, '고3', 'MWF', 'https://www.OO.com/data/file/~.webp'),
    (5, '고등 월수금반 3시', 1, '고3', 'MWF', 'https://www.OO.com/data/file/~.webp'),
    (6, '고등 월수금반 3시', 1, '고3', 'MWF', 'https://www.OO.com/data/file/~.webp')
;