INSERT INTO Textbook (name, start_page_num, last_page_num, publisher, publish_year, subject, target_grade,
                      upload_member_id, textbook_thumbnail, textbook_src_url)
VALUES ('Textbook 1', 1, 100, 'Publisher 1', '2022', 'Calculus', 'HIGH_3', 1,
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_thumbnail/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%89%E1%85%AE1_2024.jpeg',
        'url1'),
       ('Textbook 2', 2, 200, 'Publisher 2', '2022', 'Calculus', 'HIGH_3', 2,
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_thumbnail/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%89%E1%85%AE1_2024.jpeg',
        'url2'),
       ('Textbook 3', 3, 300, 'Publisher 3', '2022', 'Calculus', 'HIGH_3', 3,
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_thumbnail/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%89%E1%85%AE1_2024.jpeg',
        'url3'),
       ('Textbook 4', 4, 400, 'Publisher 4', '2022', 'Calculus', 'HIGH_3', 4,
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_thumbnail/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%89%E1%85%AE1_2024.jpeg',
        'url4'),
       ('Textbook 5', 5, 500, 'Publisher 5', '2022', 'Calculus', 'HIGH_3', 5,
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_thumbnail/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%89%E1%85%AE1_2024.jpeg',
        'url5'),
       ('Textbook 6', 6, 600, 'Publisher 6', '2022', 'Calculus', 'HIGH_3', 6,
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_thumbnail/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%89%E1%85%AE1_2024.jpeg',
        'url6'),
       ('Textbook 7', 7, 700, 'Publisher 7', '2022', 'Calculus', 'HIGH_3', 7,
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_thumbnail/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%89%E1%85%AE1_2024.jpeg',
        'url7'),
       ('Textbook 8', 8, 800, 'Publisher 8', '2022', 'Calculus', 'HIGH_3', 8,
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_thumbnail/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%89%E1%85%AE1_2024.jpeg',
        'url8'),
       ('Textbook 9', 9, 900, 'Publisher 9', '2022', 'Calculus', 'HIGH_3', 9,
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_thumbnail/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%89%E1%85%AE1_2024.jpeg',
        'url9'),
       ('Textbook 10', 10, 1000, 'Publisher 10', '2022', 'Calculus', 'HIGH_3', 10,
        'https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_thumbnail/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%89%E1%85%AE1_2024.jpeg',
        'url10');

-- 수학 상
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_high', 'Polynomial', 'Operations_of_polynomials');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_high', 'Polynomial', 'Remainder_theorem_and_factorization');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_high', 'Equations', 'Complex_numbers');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_high', 'Equations', 'Quadratic_equations');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_high', 'Equations', 'Quadratic_equations_and_quadratic_functions');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_high', 'Equations', 'Various_types_of_equations');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_high', 'Inequalities', 'Linear_inequalities');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_high', 'Inequalities', 'Quadratic_inequalities');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_high', 'Equations_of_Shapes', 'Plane_coordinates');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_high', 'Equations_of_Shapes', 'Equations_of_lines');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_high', 'Equations_of_Shapes', 'Equations_of_circles');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_high', 'Equations_of_Shapes', 'Transformation_of_shapes');

-- 수학 하
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_low', 'Sets_and_Propositions', 'Meaning_and_representation_of_sets');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_low', 'Sets_and_Propositions', 'Operations_of_sets');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_low', 'Sets_and_Propositions', 'Propositions');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_low', 'Functions', 'Functions');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_low', 'Functions', 'Rational_expressions_and_rational_functions');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_low', 'Functions', 'Irrational_expressions_and_irrational_functions');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_low', 'Permutations_and_Combinations', 'Permutations_and_combinations');

-- 수학 1
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_1', 'Exponential_and_Logarithmic_Functions', 'Exponents_and_Logarithms');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_1', 'Exponential_and_Logarithmic_Functions', 'Exponential_and_Logarithmic_Functions');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_1', 'Trigonometric_Functions', 'Trigonometric_Functions');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_1', 'Trigonometric_Functions', 'Graphs_of_Trigonometric_Functions');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_1', 'Trigonometric_Functions', 'Applications_of_Trigonometric_Functions');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_1', 'Sequences', 'Arithmetic_and_Geometric_Sequences');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_1', 'Sequences', 'Sum_of_Sequences');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_1', 'Sequences', 'Mathematical_Induction');

-- 수학 2
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_2', 'Functions_Limits_and_Continuity', 'Functions_Limits');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_2', 'Functions_Limits_and_Continuity', 'Functions_Continuity');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_2', 'Differentiation', 'Differential_Coefficient');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_2', 'Differentiation', 'Derivative_Functions');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_2', 'Differentiation', 'Applications_of_Derivatives');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_2', 'Integration_in_Math_2', 'Indefinite_Integrals');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_2', 'Integration_in_Math_2', 'Definite_Integrals');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Math_2', 'Integration_in_Math_2', 'Applications_of_Definite_Integrals_in_Math_2');

-- 미적분
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Calculus', 'Limits_of_Sequences', 'Limits_of_Sequences');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Calculus', 'Limits_of_Sequences', 'Series');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Calculus', 'Differential_Calculus', 'Differentiation_of_Various_Functions');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Calculus', 'Differential_Calculus', 'Various_Differentiation_Methods');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Calculus', 'Differential_Calculus', 'Applications_of_Derivative_Functions');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Calculus', 'Integration_in_calculus', 'Various_Integration_Methods');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Calculus', 'Integration_in_calculus', 'Applications_of_Definite_Integrals_in_Calculus');

-- 확률과 통계
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Probability_and_Statistics', 'Counting_Methods', 'Permutations_and_Combinations');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Probability_and_Statistics', 'Counting_Methods', 'Binomial_Theorem');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Probability_and_Statistics', 'Probability', 'Probability');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Probability_and_Statistics', 'Probability', 'Conditional_Probability');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Probability_and_Statistics', 'Statistics', 'Probability_Distributions');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Probability_and_Statistics', 'Statistics', 'Statistical_Estimation');

-- 기하
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Geometry', 'Conic_Sections', 'Conic_Sections');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Geometry', 'Conic_Sections', 'Conic_Sections_and_Lines');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Geometry', 'Plane_Vectors', 'Vector_Operations');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Geometry', 'Plane_Vectors', 'Components_and_Dot_Product_of_Plane_Vectors');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Geometry', 'Spatial_Shapes_and_Coordinates', 'Spatial_Shapes');
INSERT INTO math_problem_type (subject, chapter, sub_concept)
VALUES ('Geometry', 'Spatial_Shapes_and_Coordinates', 'Spatial_Coordinates');



INSERT INTO member (id, login_id, password, address, email, gender, name, phone_number, dtype, role)
VALUES (1, 'chaeda@konkuk.ac.kr', '$2a$10$ebqrHe.RCMF/eeVLCwjhqeC3ihDblknXX4qJ94RIsqozZAiohi0U.', '서울시 광진구',
        'chaeda@konkuk.ac.kr', 'MALE', '홍길동', '010-1234-5678', 'Student',
        'STUDENT');


INSERT INTO student (id, home_phone_num, parent_phone_num, school_name, notes, grade)
VALUES (1, '02-5318-6577', '010-5318-6577', '건국고등학교', null, 'HIGH_1')
;

INSERT INTO math_problem
(id, problem_number, page_number, solved_students_count, incorrect_students_count, easy_num,
 medium_difficulty_perceived_count, high_difficulty_perceived_count, textbook_id)
VALUES (1, 101, 10, 30, 5, 10, 15, 5, 1),
       (2, 102, 10, 25, 10, 8, 12, 5, 1),
       (3, 103, 10, 20, 15, 5, 10, 5, 1),
       (4, 104, 10, 22, 8, 7, 11, 4, 1),
       (5, 105, 10, 27, 13, 9, 13, 5, 1),
       (6, 106, 11, 30, 10, 12, 15, 3, 1),
       (7, 107, 11, 24, 9, 8, 11, 5, 1),
       (8, 108, 11, 28, 7, 11, 14, 3, 1),
       (9, 109, 11, 26, 12, 6, 10, 6, 1),
       (10, 110, 11, 29, 11, 10, 13, 4, 1),
       (11, 111, 12, 31, 14, 7, 12, 3, 1),
       (12, 112, 12, 23, 9, 5, 10, 8, 1),
       (13, 113, 12, 27, 13, 11, 13, 2, 1),
       (14, 114, 12, 25, 8, 9, 11, 5, 1),
       (15, 115, 12, 32, 6, 10, 14, 2, 1),
       (16, 116, 13, 21, 12, 4, 9, 8, 1),
       (17, 117, 13, 33, 11, 13, 15, 2, 1),
       (18, 118, 13, 20, 8, 6, 10, 7, 1),
       (19, 119, 13, 28, 10, 9, 12, 4, 1),
       (20, 120, 13, 26, 15, 7, 11, 5, 1);

-- ProblemTypeMapping 테이블에 30개의 레코드를 삽입하는 SQL 문
INSERT INTO problem_type_mapping (math_problem_id, math_problem_type_id)
VALUES (1, 36),
       (2, 37),
       (3, 38),
       (4, 39),
       (5, 40),
       (6, 41),
       (7, 42),
       (8, 36),
       (9, 37),
       (10, 38),
       (11, 39),
       (12, 40),
       (13, 41),
       (14, 42),
       (15, 36),
       (16, 37),
       (17, 38),
       (18, 39),
       (19, 40),
       (20, 41),
       (1, 42),
       (2, 36),
       (3, 37),
       (4, 38),
       (5, 39),
       (6, 40),
       (7, 41),
       (8, 42),
       (9, 36),
       (10, 37);

INSERT INTO solved_num_for_day (solved_num, today_date, student_id) VALUES
                                                                        (15, CURRENT_DATE - INTERVAL '0 DAY', 1),
                                                                        (23, CURRENT_DATE - INTERVAL '1 DAY', 1),
                                                                        (37, CURRENT_DATE - INTERVAL '2 DAY', 1),
                                                                        (45, CURRENT_DATE - INTERVAL '3 DAY', 1),
                                                                        (33, CURRENT_DATE - INTERVAL '4 DAY', 1),
                                                                        (29, CURRENT_DATE - INTERVAL '5 DAY', 1),
                                                                        (12, CURRENT_DATE - INTERVAL '6 DAY', 1),
                                                                        (48, CURRENT_DATE - INTERVAL '7 DAY', 1),
                                                                        (21, CURRENT_DATE - INTERVAL '8 DAY', 1),
                                                                        (35, CURRENT_DATE - INTERVAL '9 DAY', 1),
                                                                        (40, CURRENT_DATE - INTERVAL '10 DAY', 1),
                                                                        (26, CURRENT_DATE - INTERVAL '11 DAY', 1),
                                                                        (14, CURRENT_DATE - INTERVAL '12 DAY', 1),
                                                                        (32, CURRENT_DATE - INTERVAL '13 DAY', 1),
                                                                        (17, CURRENT_DATE - INTERVAL '14 DAY', 1),
                                                                        (39, CURRENT_DATE - INTERVAL '15 DAY', 1),
                                                                        (44, CURRENT_DATE - INTERVAL '16 DAY', 1),
                                                                        (30, CURRENT_DATE - INTERVAL '17 DAY', 1),
                                                                        (28, CURRENT_DATE - INTERVAL '18 DAY', 1),
                                                                        (22, CURRENT_DATE - INTERVAL '19 DAY', 1),
                                                                        (41, CURRENT_DATE - INTERVAL '20 DAY', 1),
                                                                        (20, CURRENT_DATE - INTERVAL '21 DAY', 1),
                                                                        (25, CURRENT_DATE - INTERVAL '22 DAY', 1),
                                                                        (49, CURRENT_DATE - INTERVAL '23 DAY', 1),
                                                                        (36, CURRENT_DATE - INTERVAL '24 DAY', 1),
                                                                        (19, CURRENT_DATE - INTERVAL '25 DAY', 1),
                                                                        (50, CURRENT_DATE - INTERVAL '26 DAY', 1),
                                                                        (13, CURRENT_DATE - INTERVAL '27 DAY', 1),
                                                                        (18, CURRENT_DATE - INTERVAL '28 DAY', 1),
                                                                        (42, CURRENT_DATE - INTERVAL '29 DAY', 1),
                                                                        (27, CURRENT_DATE - INTERVAL '30 DAY', 1),
                                                                        (31, CURRENT_DATE - INTERVAL '31 DAY', 1),
                                                                        (24, CURRENT_DATE - INTERVAL '32 DAY', 1),
                                                                        (11, CURRENT_DATE - INTERVAL '33 DAY', 1),
                                                                        (38, CURRENT_DATE - INTERVAL '34 DAY', 1),
                                                                        (47, CURRENT_DATE - INTERVAL '35 DAY', 1),
                                                                        (34, CURRENT_DATE - INTERVAL '36 DAY', 1),
                                                                        (16, CURRENT_DATE - INTERVAL '37 DAY', 1),
                                                                        (43, CURRENT_DATE - INTERVAL '38 DAY', 1),
                                                                        (46, CURRENT_DATE - INTERVAL '39 DAY', 1),
                                                                        (22, CURRENT_DATE - INTERVAL '40 DAY', 1),
                                                                        (30, CURRENT_DATE - INTERVAL '41 DAY', 1),
                                                                        (35, CURRENT_DATE - INTERVAL '42 DAY', 1),
                                                                        (40, CURRENT_DATE - INTERVAL '43 DAY', 1),
                                                                        (12, CURRENT_DATE - INTERVAL '44 DAY', 1),
                                                                        (48, CURRENT_DATE - INTERVAL '45 DAY', 1),
                                                                        (21, CURRENT_DATE - INTERVAL '46 DAY', 1),
                                                                        (37, CURRENT_DATE - INTERVAL '47 DAY', 1),
                                                                        (15, CURRENT_DATE - INTERVAL '48 DAY', 1),
                                                                        (45, CURRENT_DATE - INTERVAL '49 DAY', 1),
                                                                        (28, CURRENT_DATE - INTERVAL '50 DAY', 1),
                                                                        (33, CURRENT_DATE - INTERVAL '51 DAY', 1),
                                                                        (26, CURRENT_DATE - INTERVAL '52 DAY', 1),
                                                                        (39, CURRENT_DATE - INTERVAL '53 DAY', 1),
                                                                        (20, CURRENT_DATE - INTERVAL '54 DAY', 1),
                                                                        (50, CURRENT_DATE - INTERVAL '55 DAY', 1),
                                                                        (14, CURRENT_DATE - INTERVAL '56 DAY', 1),
                                                                        (29, CURRENT_DATE - INTERVAL '57 DAY', 1),
                                                                        (19, CURRENT_DATE - INTERVAL '58 DAY', 1),
                                                                        (11, CURRENT_DATE - INTERVAL '59 DAY', 1);

INSERT INTO solved_num_for_month (solved_num, month_date, student_id) VALUES
                                                                          (15, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '0 MONTH'), 1),
                                                                          (23, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 MONTH'), 1),
                                                                          (37, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '2 MONTH'), 1),
                                                                          (45, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '3 MONTH'), 1),
                                                                          (33, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '4 MONTH'), 1),
                                                                          (29, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '5 MONTH'), 1),
                                                                          (12, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '6 MONTH'), 1),
                                                                          (48, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '7 MONTH'), 1),
                                                                          (21, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '8 MONTH'), 1),
                                                                          (35, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '9 MONTH'), 1),
                                                                          (40, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '10 MONTH'), 1),
                                                                          (26, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '11 MONTH'), 1);

INSERT INTO solved_num_for_week (solved_num, start_of_week, student_id) VALUES
                                                                            (15, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '0 WEEK'), 1),
                                                                            (23, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '1 WEEK'), 1),
                                                                            (37, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '2 WEEK'), 1),
                                                                            (45, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '3 WEEK'), 1),
                                                                            (33, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '4 WEEK'), 1),
                                                                            (29, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '5 WEEK'), 1),
                                                                            (12, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '6 WEEK'), 1),
                                                                            (48, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '7 WEEK'), 1),
                                                                            (21, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '8 WEEK'), 1),
                                                                            (35, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '9 WEEK'), 1),
                                                                            (40, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '10 WEEK'), 1),
                                                                            (26, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '11 WEEK'), 1);

-- Week 0
INSERT INTO subconcept_statistics_for_week (type_id, student_id, start_of_week, solved_num, wrong_num, easy_num, middle_num, hard_num) VALUES
                                                                                                                                           (1, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '0 WEEK'), 20, 5, 10, 7, 3),
                                                                                                                                           (3, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '0 WEEK'), 22, 4, 12, 6, 4),
                                                                                                                                           (5, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '0 WEEK'), 18, 3, 9, 8, 1),
                                                                                                                                           (7, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '0 WEEK'), 24, 6, 11, 9, 4),
                                                                                                                                           (9, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '0 WEEK'), 19, 2, 8, 7, 4),
                                                                                                                                           (11, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '0 WEEK'), 21, 5, 10, 8, 3),
                                                                                                                                           (13, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '0 WEEK'), 23, 4, 11, 7, 4),
                                                                                                                                           (15, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '0 WEEK'), 17, 3, 8, 6, 3),
                                                                                                                                           (17, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '0 WEEK'), 25, 6, 12, 9, 4),
                                                                                                                                           (19, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '0 WEEK'), 20, 2, 9, 8, 3),
                                                                                                                                           (21, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '0 WEEK'), 22, 5, 11, 8, 3),
                                                                                                                                           (23, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '0 WEEK'), 24, 4, 12, 7, 4),
                                                                                                                                           (25, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '0 WEEK'), 18, 3, 8, 6, 3),
                                                                                                                                           (27, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '0 WEEK'), 26, 6, 13, 9, 4),
                                                                                                                                           (29, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '0 WEEK'), 21, 2, 10, 8, 3),
                                                                                                                                           (31, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '0 WEEK'), 23, 5, 12, 8, 3),
                                                                                                                                           (33, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '0 WEEK'), 25, 4, 13, 7, 4),
                                                                                                                                           (35, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '0 WEEK'), 19, 3, 9, 6, 3),
                                                                                                                                           (37, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '0 WEEK'), 27, 6, 14, 9, 4),
                                                                                                                                           (39, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '0 WEEK'), 22, 2, 11, 8, 3);

-- Week 1
INSERT INTO subconcept_statistics_for_week (type_id, student_id, start_of_week, solved_num, wrong_num, easy_num, middle_num, hard_num) VALUES
                                                                                                                                           (2, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '1 WEEK'), 19, 5, 10, 7, 3),
                                                                                                                                           (4, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '1 WEEK'), 21, 4, 11, 6, 4),
                                                                                                                                           (6, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '1 WEEK'), 17, 3, 8, 8, 1),
                                                                                                                                           (8, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '1 WEEK'), 23, 6, 12, 9, 4),
                                                                                                                                           (10, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '1 WEEK'), 18, 2, 8, 7, 4),
                                                                                                                                           (12, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '1 WEEK'), 20, 5, 10, 8, 3),
                                                                                                                                           (14, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '1 WEEK'), 22, 4, 11, 7, 4),
                                                                                                                                           (16, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '1 WEEK'), 16, 3, 8, 6, 3),
                                                                                                                                           (18, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '1 WEEK'), 24, 6, 12, 9, 4),
                                                                                                                                           (20, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '1 WEEK'), 19, 2, 9, 8, 3),
                                                                                                                                           (22, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '1 WEEK'), 21, 5, 11, 8, 3),
                                                                                                                                           (24, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '1 WEEK'), 23, 4, 12, 7, 4),
                                                                                                                                           (26, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '1 WEEK'), 17, 3, 8, 6, 3),
                                                                                                                                           (28, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '1 WEEK'), 25, 6, 13, 9, 4),
                                                                                                                                           (30, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '1 WEEK'), 20, 2, 10, 8, 3),
                                                                                                                                           (32, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '1 WEEK'), 22, 5, 12, 8, 3),
                                                                                                                                           (34, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '1 WEEK'), 24, 4, 13, 7, 4),
                                                                                                                                           (36, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '1 WEEK'), 18, 3, 9, 6, 3),
                                                                                                                                           (38, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '1 WEEK'), 26, 6, 14, 9, 4),
                                                                                                                                           (40, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '1 WEEK'), 21, 2, 11, 8, 3);

-- Week 2
INSERT INTO subconcept_statistics_for_week (type_id, student_id, start_of_week, solved_num, wrong_num, easy_num, middle_num, hard_num) VALUES
                                                                                                                                           (1, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '2 WEEK'), 19, 5, 10, 7, 3),
                                                                                                                                           (3, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '2 WEEK'), 21, 4, 11, 6, 4),
                                                                                                                                           (5, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '2 WEEK'), 17, 3, 8, 8, 1),
                                                                                                                                           (7, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '2 WEEK'), 23, 6, 12, 9, 4),
                                                                                                                                           (9, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '2 WEEK'), 18, 2, 8, 7, 4),
                                                                                                                                           (11, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '2 WEEK'), 20, 5, 10, 8, 3),
                                                                                                                                           (13, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '2 WEEK'), 22, 4, 11, 7, 4),
                                                                                                                                           (15, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '2 WEEK'), 16, 3, 8, 6, 3),
                                                                                                                                           (17, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '2 WEEK'), 24, 6, 12, 9, 4),
                                                                                                                                           (19, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '2 WEEK'), 19, 2, 9, 8, 3),
                                                                                                                                           (21, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '2 WEEK'), 21, 5, 11, 8, 3),
                                                                                                                                           (23, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '2 WEEK'), 23, 4, 12, 7, 4),
                                                                                                                                           (25, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '2 WEEK'), 17, 3, 8, 6, 3),
                                                                                                                                           (27, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '2 WEEK'), 25, 6, 13, 9, 4),
                                                                                                                                           (29, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '2 WEEK'), 20, 2, 10, 8, 3),
                                                                                                                                           (31, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '2 WEEK'), 22, 5, 12, 8, 3),
                                                                                                                                           (33, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '2 WEEK'), 24, 4, 13, 7, 4),
                                                                                                                                           (35, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '2 WEEK'), 18, 3, 9, 6, 3),
                                                                                                                                           (37, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '2 WEEK'), 26, 6, 14, 9, 4),
                                                                                                                                           (39, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '2 WEEK'), 21, 2, 11, 8, 3);

-- Week 3
INSERT INTO subconcept_statistics_for_week (type_id, student_id, start_of_week, solved_num, wrong_num, easy_num, middle_num, hard_num) VALUES
                                                                                                                                           (2, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '3 WEEK'), 19, 5, 10, 7, 3),
                                                                                                                                           (4, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '3 WEEK'), 21, 4, 11, 6, 4),
                                                                                                                                           (6, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '3 WEEK'), 17, 3, 8, 8, 1),
                                                                                                                                           (8, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '3 WEEK'), 23, 6, 12, 9, 4),
                                                                                                                                           (10, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '3 WEEK'), 18, 2, 8, 7, 4),
                                                                                                                                           (12, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '3 WEEK'), 20, 5, 10, 8, 3),
                                                                                                                                           (14, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '3 WEEK'), 22, 4, 11, 7, 4),
                                                                                                                                           (16, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '3 WEEK'), 16, 3, 8, 6, 3),
                                                                                                                                           (18, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '3 WEEK'), 24, 6, 12, 9, 4),
                                                                                                                                           (20, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '3 WEEK'), 19, 2, 9, 8, 3),
                                                                                                                                           (22, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '3 WEEK'), 21, 5, 11, 8, 3),
                                                                                                                                           (24, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '3 WEEK'), 23, 4, 12, 7, 4),
                                                                                                                                           (26, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '3 WEEK'), 17, 3, 8, 6, 3),
                                                                                                                                           (28, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '3 WEEK'), 25, 6, 13, 9, 4),
                                                                                                                                           (30, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '3 WEEK'), 20, 2, 10, 8, 3),
                                                                                                                                           (32, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '3 WEEK'), 22, 5, 12, 8, 3),
                                                                                                                                           (34, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '3 WEEK'), 24, 4, 13, 7, 4),
                                                                                                                                           (36, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '3 WEEK'), 18, 3, 9, 6, 3),
                                                                                                                                           (38, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '3 WEEK'), 26, 6, 14, 9, 4),
                                                                                                                                           (40, 1, DATE_TRUNC('week', CURRENT_DATE - INTERVAL '3 WEEK'), 21, 2, 11, 8, 3);

-- Month 0
INSERT INTO subconcept_statistics_for_month (type_id, student_id, target_month, solved_num, wrong_num, easy_num, middle_num, hard_num) VALUES
                                                                                                                                           (1, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '0 MONTH'), 20, 5, 10, 7, 3),
                                                                                                                                           (3, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '0 MONTH'), 22, 4, 12, 6, 4),
                                                                                                                                           (5, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '0 MONTH'), 18, 3, 9, 8, 1),
                                                                                                                                           (7, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '0 MONTH'), 24, 6, 11, 9, 4),
                                                                                                                                           (9, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '0 MONTH'), 19, 2, 8, 7, 4),
                                                                                                                                           (11, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '0 MONTH'), 21, 5, 10, 8, 3),
                                                                                                                                           (13, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '0 MONTH'), 23, 4, 11, 7, 4),
                                                                                                                                           (15, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '0 MONTH'), 17, 3, 8, 6, 3),
                                                                                                                                           (17, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '0 MONTH'), 25, 6, 12, 9, 4),
                                                                                                                                           (19, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '0 MONTH'), 20, 2, 9, 8, 3),
                                                                                                                                           (21, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '0 MONTH'), 22, 5, 11, 8, 3),
                                                                                                                                           (23, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '0 MONTH'), 24, 4, 12, 7, 4),
                                                                                                                                           (25, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '0 MONTH'), 18, 3, 8, 6, 3),
                                                                                                                                           (27, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '0 MONTH'), 26, 6, 13, 9, 4),
                                                                                                                                           (29, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '0 MONTH'), 21, 2, 10, 8, 3),
                                                                                                                                           (31, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '0 MONTH'), 23, 5, 12, 8, 3),
                                                                                                                                           (33, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '0 MONTH'), 25, 4, 13, 7, 4),
                                                                                                                                           (35, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '0 MONTH'), 19, 3, 9, 6, 3),
                                                                                                                                           (37, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '0 MONTH'), 27, 6, 14, 9, 4),
                                                                                                                                           (39, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '0 MONTH'), 22, 2, 11, 8, 3);

-- Month 1
INSERT INTO subconcept_statistics_for_month (type_id, student_id, target_month, solved_num, wrong_num, easy_num, middle_num, hard_num) VALUES
                                                                                                                                           (2, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 MONTH'), 19, 5, 10, 7, 3),
                                                                                                                                           (4, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 MONTH'), 21, 4, 11, 6, 4),
                                                                                                                                           (6, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 MONTH'), 17, 3, 8, 8, 1),
                                                                                                                                           (8, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 MONTH'), 23, 6, 12, 9, 4),
                                                                                                                                           (10, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 MONTH'), 18, 2, 8, 7, 4),
                                                                                                                                           (12, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 MONTH'), 20, 5, 10, 8, 3),
                                                                                                                                           (14, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 MONTH'), 22, 4, 11, 7, 4),
                                                                                                                                           (16, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 MONTH'), 16, 3, 8, 6, 3),
                                                                                                                                           (18, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 MONTH'), 24, 6, 12, 9, 4),
                                                                                                                                           (20, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 MONTH'), 19, 2, 9, 8, 3),
                                                                                                                                           (22, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 MONTH'), 21, 5, 11, 8, 3),
                                                                                                                                           (24, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 MONTH'), 23, 4, 12, 7, 4),
                                                                                                                                           (26, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 MONTH'), 17, 3, 8, 6, 3),
                                                                                                                                           (28, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 MONTH'), 25, 6, 13, 9, 4),
                                                                                                                                           (30, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 MONTH'), 20, 2, 10, 8, 3),
                                                                                                                                           (32, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 MONTH'), 22, 5, 12, 8, 3),
                                                                                                                                           (34, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 MONTH'), 24, 4, 13, 7, 4),
                                                                                                                                           (36, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 MONTH'), 18, 3, 9, 6, 3),
                                                                                                                                           (38, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 MONTH'), 26, 6, 14, 9, 4),
                                                                                                                                           (40, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 MONTH'), 21, 2, 11, 8, 3);

-- Month 2
INSERT INTO subconcept_statistics_for_month (type_id, student_id, target_month, solved_num, wrong_num, easy_num, middle_num, hard_num) VALUES
                                                                                                                                           (1, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '2 MONTH'), 19, 5, 10, 7, 3),
                                                                                                                                           (3, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '2 MONTH'), 21, 4, 11, 6, 4),
                                                                                                                                           (5, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '2 MONTH'), 17, 3, 8, 8, 1),
                                                                                                                                           (7, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '2 MONTH'), 23, 6, 12, 9, 4),
                                                                                                                                           (9, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '2 MONTH'), 18, 2, 8, 7, 4),
                                                                                                                                           (11, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '2 MONTH'), 20, 5, 10, 8, 3),
                                                                                                                                           (13, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '2 MONTH'), 22, 4, 11, 7, 4),
                                                                                                                                           (15, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '2 MONTH'), 16, 3, 8, 6, 3),
                                                                                                                                           (17, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '2 MONTH'), 24, 6, 12, 9, 4),
                                                                                                                                           (19, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '2 MONTH'), 19, 2, 9, 8, 3),
                                                                                                                                           (21, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '2 MONTH'), 21, 5, 11, 8, 3),
                                                                                                                                           (23, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '2 MONTH'), 23, 4, 12, 7, 4),
                                                                                                                                           (25, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '2 MONTH'), 17, 3, 8, 6, 3),
                                                                                                                                           (27, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '2 MONTH'), 25, 6, 13, 9, 4),
                                                                                                                                           (29, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '2 MONTH'), 20, 2, 10, 8, 3),
                                                                                                                                           (31, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '2 MONTH'), 22, 5, 12, 8, 3),
                                                                                                                                           (33, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '2 MONTH'), 24, 4, 13, 7, 4),
                                                                                                                                           (35, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '2 MONTH'), 18, 3, 9, 6, 3),
                                                                                                                                           (37, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '2 MONTH'), 26, 6, 14, 9, 4),
                                                                                                                                           (39, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '2 MONTH'), 21, 2, 11, 8, 3);

-- Month 3
INSERT INTO subconcept_statistics_for_month (type_id, student_id, target_month, solved_num, wrong_num, easy_num, middle_num, hard_num) VALUES
                                                                                                                                           (2, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '3 MONTH'), 19, 5, 10, 7, 3),
                                                                                                                                           (4, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '3 MONTH'), 21, 4, 11, 6, 4),
                                                                                                                                           (6, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '3 MONTH'), 17, 3, 8, 8, 1),
                                                                                                                                           (8, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '3 MONTH'), 23, 6, 12, 9, 4),
                                                                                                                                           (10, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '3 MONTH'), 18, 2, 8, 7, 4),
                                                                                                                                           (12, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '3 MONTH'), 20, 5, 10, 8, 3),
                                                                                                                                           (14, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '3 MONTH'), 22, 4, 11, 7, 4),
                                                                                                                                           (16, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '3 MONTH'), 16, 3, 8, 6, 3),
                                                                                                                                           (18, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '3 MONTH'), 24, 6, 12, 9, 4),
                                                                                                                                           (20, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '3 MONTH'), 19, 2, 9, 8, 3),
                                                                                                                                           (22, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '3 MONTH'), 21, 5, 11, 8, 3),
                                                                                                                                           (24, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '3 MONTH'), 23, 4, 12, 7, 4),
                                                                                                                                           (26, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '3 MONTH'), 17, 3, 8, 6, 3),
                                                                                                                                           (28, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '3 MONTH'), 25, 6, 13, 9, 4),
                                                                                                                                           (30, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '3 MONTH'), 20, 2, 10, 8, 3),
                                                                                                                                           (32, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '3 MONTH'), 22, 5, 12, 8, 3),
                                                                                                                                           (34, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '3 MONTH'), 24, 4, 13, 7, 4),
                                                                                                                                           (36, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '3 MONTH'), 18, 3, 9, 6, 3),
                                                                                                                                           (38, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '3 MONTH'), 26, 6, 14, 9, 4),
                                                                                                                                           (40, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '3 MONTH'), 21, 2, 11, 8, 3);

-- Accumulated statistics for different subconcepts
INSERT INTO accumulated_statistics_for_subconcept (student_id, solved_num, wrong_num, easy_num, middle_num, hard_num, type_id) VALUES
                                                                                                                                   (1, 20, 5, 10, 7, 3, 1),
                                                                                                                                   (1, 18, 4, 8, 6, 4, 3),
                                                                                                                                   (1, 25, 6, 12, 9, 4, 5),
                                                                                                                                   (1, 22, 5, 11, 8, 3, 7),
                                                                                                                                   (1, 19, 4, 9, 7, 3, 9),
                                                                                                                                   (1, 21, 5, 10, 8, 3, 11),
                                                                                                                                   (1, 23, 4, 11, 7, 4, 13),
                                                                                                                                   (1, 17, 3, 8, 6, 3, 15),
                                                                                                                                   (1, 24, 6, 12, 9, 4, 17),
                                                                                                                                   (1, 20, 5, 10, 7, 3, 19),
                                                                                                                                   (1, 18, 4, 8, 6, 4, 21),
                                                                                                                                   (1, 25, 6, 12, 9, 4, 23),
                                                                                                                                   (1, 22, 5, 11, 8, 3, 25),
                                                                                                                                   (1, 19, 4, 9, 7, 3, 27),
                                                                                                                                   (1, 21, 5, 10, 8, 3, 29),
                                                                                                                                   (1, 23, 4, 11, 7, 4, 31),
                                                                                                                                   (1, 17, 3, 8, 6, 3, 33),
                                                                                                                                   (1, 24, 6, 12, 9, 4, 35),
                                                                                                                                   (1, 20, 5, 10, 7, 3, 37),
                                                                                                                                   (1, 18, 4, 8, 6, 4, 39),
                                                                                                                                   (1, 25, 6, 12, 9, 4, 41),
                                                                                                                                   (1, 22, 5, 11, 8, 3, 43),
                                                                                                                                   (1, 19, 4, 9, 7, 3, 45),
                                                                                                                                   (1, 21, 5, 10, 8, 3, 47),
                                                                                                                                   (1, 23, 4, 11, 7, 4, 49),
                                                                                                                                   (1, 17, 3, 8, 6, 3, 51),
                                                                                                                                   (1, 24, 6, 12, 9, 4, 53),
                                                                                                                                   (1, 20, 5, 10, 7, 3, 2),
                                                                                                                                   (1, 18, 4, 8, 6, 4, 4),
                                                                                                                                   (1, 25, 6, 12, 9, 4, 6),
                                                                                                                                   (1, 22, 5, 11, 8, 3, 8),
                                                                                                                                   (1, 19, 4, 9, 7, 3, 10),
                                                                                                                                   (1, 21, 5, 10, 8, 3, 12),
                                                                                                                                   (1, 23, 4, 11, 7, 4, 14),
                                                                                                                                   (1, 17, 3, 8, 6, 3, 16),
                                                                                                                                   (1, 24, 6, 12, 9, 4, 18),
                                                                                                                                   (1, 20, 5, 10, 7, 3, 20),
                                                                                                                                   (1, 18, 4, 8, 6, 4, 22),
                                                                                                                                   (1, 25, 6, 12, 9, 4, 24),
                                                                                                                                   (1, 22, 5, 11, 8, 3, 26),
                                                                                                                                   (1, 19, 4, 9, 7, 3, 28),
                                                                                                                                   (1, 21, 5, 10, 8, 3, 30),
                                                                                                                                   (1, 23, 4, 11, 7, 4, 32),
                                                                                                                                   (1, 17, 3, 8, 6, 3, 34),
                                                                                                                                   (1, 24, 6, 12, 9, 4, 36),
                                                                                                                                   (1, 20, 5, 10, 7, 3, 38),
                                                                                                                                   (1, 18, 4, 8, 6, 4, 40),
                                                                                                                                   (1, 25, 6, 12, 9, 4, 42),
                                                                                                                                   (1, 22, 5, 11, 8, 3, 44),
                                                                                                                                   (1, 19, 4, 9, 7, 3, 46),
                                                                                                                                   (1, 21, 5, 10, 8, 3, 48),
                                                                                                                                   (1, 23, 4, 11, 7, 4, 50),
                                                                                                                                   (1, 17, 3, 8, 6, 3, 52),
                                                                                                                                   (1, 24, 6, 12, 9, 4, 54);
