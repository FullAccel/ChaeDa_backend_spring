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



INSERT INTO member (id, address, email, gender, name, phone_number, dtype, role)
VALUES (1, '서울시 광진구', 'chaeda@konkuk.ac.kr', 'MALE', '홍길동', '010-1234-5678', 'Student', 'STUDENT');

--
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
