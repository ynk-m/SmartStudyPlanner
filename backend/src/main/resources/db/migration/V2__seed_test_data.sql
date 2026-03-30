-- ═══════════════════════════════════════════════════════
-- Courses
-- ═══════════════════════════════════════════════════════
INSERT INTO course (id, name, description, min_session_minutes, max_session_minutes, break_minutes, preferred_start_time, preferred_end_time) VALUES
(1, 'Linear Algebra', 'Vectors, matrices, eigenvalues, linear transformations', 30, 90, 10, '09:00', '18:00'),
(2, 'Data Structures & Algorithms', 'Arrays, trees, graphs, sorting, dynamic programming', 25, 60, 10, '08:00', '20:00'),
(3, 'Operating Systems', 'Processes, threads, memory management, file systems', 30, 120, 15, '10:00', '19:00');

-- ═══════════════════════════════════════════════════════
-- Chapters
-- ═══════════════════════════════════════════════════════
-- Linear Algebra
INSERT INTO chapter (id, name, course_id) VALUES
(1, 'Vectors & Vector Spaces', 1),
(2, 'Matrices & Determinants', 1),
(3, 'Eigenvalues & Eigenvectors', 1);

-- DSA
INSERT INTO chapter (id, name, course_id) VALUES
(4, 'Arrays & Linked Lists', 2),
(5, 'Trees & Graphs', 2),
(6, 'Sorting & Searching', 2),
(7, 'Dynamic Programming', 2);

-- OS
INSERT INTO chapter (id, name, course_id) VALUES
(8, 'Process Management', 3),
(9, 'Memory Management', 3),
(10, 'File Systems & I/O', 3);

-- ═══════════════════════════════════════════════════════
-- Subchapters
-- ═══════════════════════════════════════════════════════
-- Linear Algebra - Ch1: Vectors & Vector Spaces
INSERT INTO subchapter (id, name, estimated_minutes, readiness_score, next_review_at, chapter_id) VALUES
(1, 'Vector addition & scalar multiplication', 30, 0.7, NOW() + INTERVAL '3 days', 1),
(2, 'Dot product & cross product', 45, 0.5, NOW() + INTERVAL '1 day', 1),
(3, 'Linear independence & span', 60, 0.2, NOW(), 1),
(4, 'Basis & dimension', 45, 0.0, NULL, 1);

-- Linear Algebra - Ch2: Matrices
INSERT INTO subchapter (id, name, estimated_minutes, readiness_score, next_review_at, chapter_id) VALUES
(5, 'Matrix operations', 30, 0.8, NOW() + INTERVAL '7 days', 2),
(6, 'Row reduction & echelon forms', 60, 0.4, NOW() + INTERVAL '1 day', 2),
(7, 'Determinants', 45, 0.1, NOW(), 2),
(8, 'Inverse matrices', 40, 0.0, NULL, 2);

-- Linear Algebra - Ch3: Eigenvalues
INSERT INTO subchapter (id, name, estimated_minutes, readiness_score, next_review_at, chapter_id) VALUES
(9, 'Characteristic equation', 50, 0.0, NULL, 3),
(10, 'Diagonalization', 60, 0.0, NULL, 3);

-- DSA - Ch4: Arrays & Linked Lists
INSERT INTO subchapter (id, name, estimated_minutes, readiness_score, next_review_at, chapter_id) VALUES
(11, 'Array operations & complexity', 25, 0.9, NOW() + INTERVAL '14 days', 4),
(12, 'Singly linked lists', 30, 0.6, NOW() + INTERVAL '2 days', 4),
(13, 'Doubly linked lists & stacks/queues', 35, 0.3, NOW(), 4);

-- DSA - Ch5: Trees & Graphs
INSERT INTO subchapter (id, name, estimated_minutes, readiness_score, next_review_at, chapter_id) VALUES
(14, 'Binary trees & BST', 45, 0.5, NOW() + INTERVAL '1 day', 5),
(15, 'AVL & Red-Black trees', 60, 0.1, NOW(), 5),
(16, 'Graph representations', 30, 0.3, NOW() + INTERVAL '1 day', 5),
(17, 'BFS & DFS', 40, 0.2, NOW(), 5);

-- DSA - Ch6: Sorting
INSERT INTO subchapter (id, name, estimated_minutes, readiness_score, next_review_at, chapter_id) VALUES
(18, 'Bubble, Selection, Insertion sort', 30, 0.8, NOW() + INTERVAL '7 days', 6),
(19, 'Merge sort & Quick sort', 45, 0.4, NOW() + INTERVAL '2 days', 6),
(20, 'Binary search', 25, 0.7, NOW() + INTERVAL '5 days', 6);

-- DSA - Ch7: Dynamic Programming
INSERT INTO subchapter (id, name, estimated_minutes, readiness_score, next_review_at, chapter_id) VALUES
(21, 'Memoization vs tabulation', 40, 0.0, NULL, 7),
(22, 'Classic DP problems (knapsack, LCS)', 60, 0.0, NULL, 7);

-- OS - Ch8: Process Management
INSERT INTO subchapter (id, name, estimated_minutes, readiness_score, next_review_at, chapter_id) VALUES
(23, 'Process lifecycle & states', 30, 0.6, NOW() + INTERVAL '3 days', 8),
(24, 'Threads & concurrency', 45, 0.3, NOW(), 8),
(25, 'Scheduling algorithms', 50, 0.1, NOW(), 8),
(26, 'Deadlocks', 40, 0.0, NULL, 8);

-- OS - Ch9: Memory Management
INSERT INTO subchapter (id, name, estimated_minutes, readiness_score, next_review_at, chapter_id) VALUES
(27, 'Paging & segmentation', 50, 0.2, NOW(), 9),
(28, 'Virtual memory', 45, 0.0, NULL, 9),
(29, 'Page replacement algorithms', 40, 0.0, NULL, 9);

-- OS - Ch10: File Systems
INSERT INTO subchapter (id, name, estimated_minutes, readiness_score, next_review_at, chapter_id) VALUES
(30, 'File system structure', 30, 0.0, NULL, 10),
(31, 'Disk scheduling', 35, 0.0, NULL, 10);

-- ═══════════════════════════════════════════════════════
-- Exams
-- ═══════════════════════════════════════════════════════
INSERT INTO exam (id, title, exam_date, notes, course_id) VALUES
(1, 'Linear Algebra Midterm', NOW() + INTERVAL '18 days', 'Covers chapters 1-2, focus on proofs', 1),
(2, 'DSA Final Exam', NOW() + INTERVAL '25 days', 'Comprehensive, includes coding questions', 2),
(3, 'OS Midterm', NOW() + INTERVAL '10 days', 'Process management & memory only', 3);

-- ═══════════════════════════════════════════════════════
-- Study Sessions (mix of completed, scheduled, skipped)
-- ═══════════════════════════════════════════════════════
-- Past completed sessions
INSERT INTO study_session (start_time, end_time, status, feedback, subchapter_id, exam_id) VALUES
(NOW() - INTERVAL '5 days' + INTERVAL '9 hours', NOW() - INTERVAL '5 days' + INTERVAL '9 hours 30 minutes', 'COMPLETED', 'GOOD', 1, 1),
(NOW() - INTERVAL '5 days' + INTERVAL '10 hours', NOW() - INTERVAL '5 days' + INTERVAL '10 hours 45 minutes', 'COMPLETED', 'MID', 2, 1),
(NOW() - INTERVAL '4 days' + INTERVAL '9 hours', NOW() - INTERVAL '4 days' + INTERVAL '9 hours 30 minutes', 'COMPLETED', 'GOOD', 5, 1),
(NOW() - INTERVAL '4 days' + INTERVAL '10 hours', NOW() - INTERVAL '4 days' + INTERVAL '11 hours', 'COMPLETED', 'BAD', 3, 1),
(NOW() - INTERVAL '3 days' + INTERVAL '8 hours', NOW() - INTERVAL '3 days' + INTERVAL '8 hours 25 minutes', 'COMPLETED', 'GOOD', 11, 2),
(NOW() - INTERVAL '3 days' + INTERVAL '9 hours', NOW() - INTERVAL '3 days' + INTERVAL '9 hours 30 minutes', 'COMPLETED', 'MID', 12, 2),
(NOW() - INTERVAL '2 days' + INTERVAL '10 hours', NOW() - INTERVAL '2 days' + INTERVAL '10 hours 45 minutes', 'COMPLETED', 'GOOD', 18, 2),
(NOW() - INTERVAL '2 days' + INTERVAL '11 hours', NOW() - INTERVAL '2 days' + INTERVAL '11 hours 30 minutes', 'COMPLETED', 'MID', 23, 3),
(NOW() - INTERVAL '1 day' + INTERVAL '9 hours', NOW() - INTERVAL '1 day' + INTERVAL '9 hours 50 minutes', 'COMPLETED', 'BAD', 25, 3),
(NOW() - INTERVAL '1 day' + INTERVAL '10 hours', NOW() - INTERVAL '1 day' + INTERVAL '10 hours 30 minutes', 'SKIPPED', NULL, 7, 1);

-- Today's sessions
INSERT INTO study_session (start_time, end_time, status, feedback, subchapter_id, exam_id) VALUES
(NOW()::date + INTERVAL '9 hours', NOW()::date + INTERVAL '10 hours', 'SCHEDULED', NULL, 3, 1),
(NOW()::date + INTERVAL '10 hours 15 minutes', NOW()::date + INTERVAL '11 hours', 'SCHEDULED', NULL, 6, 1),
(NOW()::date + INTERVAL '14 hours', NOW()::date + INTERVAL '14 hours 45 minutes', 'SCHEDULED', NULL, 24, 3),
(NOW()::date + INTERVAL '15 hours', NOW()::date + INTERVAL '15 hours 40 minutes', 'SCHEDULED', NULL, 17, 2);

-- Future scheduled sessions
INSERT INTO study_session (start_time, end_time, status, feedback, subchapter_id, exam_id) VALUES
(NOW()::date + INTERVAL '1 day' + INTERVAL '9 hours', NOW()::date + INTERVAL '1 day' + INTERVAL '9 hours 45 minutes', 'SCHEDULED', NULL, 7, 1),
(NOW()::date + INTERVAL '1 day' + INTERVAL '10 hours', NOW()::date + INTERVAL '1 day' + INTERVAL '10 hours 30 minutes', 'SCHEDULED', NULL, 2, 1),
(NOW()::date + INTERVAL '1 day' + INTERVAL '14 hours', NOW()::date + INTERVAL '1 day' + INTERVAL '15 hours', 'SCHEDULED', NULL, 15, 2),
(NOW()::date + INTERVAL '2 days' + INTERVAL '9 hours', NOW()::date + INTERVAL '2 days' + INTERVAL '10 hours', 'SCHEDULED', NULL, 4, 1),
(NOW()::date + INTERVAL '2 days' + INTERVAL '10 hours 15 minutes', NOW()::date + INTERVAL '2 days' + INTERVAL '11 hours', 'SCHEDULED', NULL, 27, 3),
(NOW()::date + INTERVAL '3 days' + INTERVAL '9 hours', NOW()::date + INTERVAL '3 days' + INTERVAL '9 hours 40 minutes', 'SCHEDULED', NULL, 13, 2),
(NOW()::date + INTERVAL '3 days' + INTERVAL '10 hours', NOW()::date + INTERVAL '3 days' + INTERVAL '10 hours 50 minutes', 'SCHEDULED', NULL, 25, 3);

-- ═══════════════════════════════════════════════════════
-- Calendar Events (simulated Google Calendar sync)
-- ═══════════════════════════════════════════════════════
INSERT INTO calendar_event (google_event_id, title, start_time, end_time, last_synced_at) VALUES
('gcal_001', 'Linear Algebra Lecture', NOW()::date + INTERVAL '11 hours 30 minutes', NOW()::date + INTERVAL '13 hours', NOW()),
('gcal_002', 'DSA Lab', NOW()::date + INTERVAL '13 hours 30 minutes', NOW()::date + INTERVAL '15 hours 30 minutes', NOW()),
('gcal_003', 'OS Tutorial', NOW()::date + INTERVAL '1 day' + INTERVAL '11 hours', NOW()::date + INTERVAL '1 day' + INTERVAL '12 hours 30 minutes', NOW()),
('gcal_004', 'Study Group - Algebra', NOW()::date + INTERVAL '2 days' + INTERVAL '16 hours', NOW()::date + INTERVAL '2 days' + INTERVAL '18 hours', NOW()),
('gcal_005', 'Gym', NOW()::date + INTERVAL '18 hours', NOW()::date + INTERVAL '19 hours 30 minutes', NOW()),
('gcal_006', 'Dentist Appointment', NOW()::date + INTERVAL '3 days' + INTERVAL '14 hours', NOW()::date + INTERVAL '3 days' + INTERVAL '15 hours', NOW());

-- Reset sequences to avoid conflicts with future inserts
SELECT setval('course_id_seq', (SELECT MAX(id) FROM course));
SELECT setval('chapter_id_seq', (SELECT MAX(id) FROM chapter));
SELECT setval('subchapter_id_seq', (SELECT MAX(id) FROM subchapter));
SELECT setval('exam_id_seq', (SELECT MAX(id) FROM exam));
SELECT setval('study_session_id_seq', (SELECT MAX(id) FROM study_session));
SELECT setval('calendar_event_id_seq', (SELECT MAX(id) FROM calendar_event));
