INSERT INTO `user_info` (`id`, `created_at`, `created_by`, `modified_at`, `modified_by`, `admin_check`, `exit_date`, `is_ban`, `name`)
VALUES (1, NOW(), 'system', NOW(), 'system', 1, NULL, 0, '김철수');

INSERT INTO `user_info` (`id`, `created_at`, `created_by`, `modified_at`, `modified_by`, `admin_check`, `exit_date`, `is_ban`, `name`)
VALUES (2, NOW(), 'system', NOW(), 'system', 1, NULL, 0, '이영희');

INSERT INTO `user_info` (`id`, `created_at`, `created_by`, `modified_at`, `modified_by`, `admin_check`, `exit_date`, `is_ban`, `name`)
VALUES (3, NOW(), 'system', NOW(), 'system', 1, NULL, 0, '박민수');

INSERT INTO `user_info` (`id`, `created_at`, `created_by`, `modified_at`, `modified_by`, `admin_check`, `exit_date`, `is_ban`, `name`)
VALUES (4, NOW(), 'system', NOW(), 'system', 1, NULL, 0, '최유리');

INSERT INTO `user_info` (`id`, `created_at`, `created_by`, `modified_at`, `modified_by`, `admin_check`, `exit_date`, `is_ban`, `name`)
VALUES (5, NOW(), 'system', NOW(), 'system', 1, NULL, 0, '강민지');

INSERT INTO `user_info` (`id`, `created_at`, `created_by`, `modified_at`, `modified_by`, `admin_check`, `exit_date`, `is_ban`, `name`)
VALUES (6, NOW(), 'system', NOW(), 'system', 1, NULL, 0, '윤지우');

INSERT INTO `user_info` (`id`, `created_at`, `created_by`, `modified_at`, `modified_by`, `admin_check`, `exit_date`, `is_ban`, `name`)
VALUES (7, NOW(), 'system', NOW(), 'system', 1, NULL, 0, '한지수');

INSERT INTO `user_info` (`id`, `created_at`, `created_by`, `modified_at`, `modified_by`, `admin_check`, `exit_date`, `is_ban`, `name`)
VALUES (8, NOW(), 'system', NOW(), 'system', 1, NULL, 0, '서윤호');

INSERT INTO `user_info` (`id`, `created_at`, `created_by`, `modified_at`, `modified_by`, `admin_check`, `exit_date`, `is_ban`, `name`)
VALUES (9, NOW(), 'system', NOW(), 'system', 1, NULL, 0, '오수진');

INSERT INTO `user_info` (`id`, `created_at`, `created_by`, `modified_at`, `modified_by`, `admin_check`, `exit_date`, `is_ban`, `name`)
VALUES (10, NOW(), 'system', NOW(), 'system', 1, NULL, 0, '김나연');


INSERT INTO board (id, created_at, created_by, modified_at, modified_by, content, title, user_info_id)
VALUES
    (1, NOW(), '홍길동', NOW(), '김유신', '안녕하세요', '제목 1', 1),
    (2, NOW(), '이순신', NOW(), '강감찬', '반갑습니다', '제목 2', 2),
    (3, NOW(), '유관순', NOW(), '윤봉길', '잘 지내세요', '제목 3', 3),
    (4, NOW(), '김구', NOW(), '안중근', '어서오세요', '제목 4', 4),
    (5, NOW(), '윤동주', NOW(), '정지용', '감사합니다', '제목 5', 5),
    (6, NOW(), '박두진', NOW(), '이병주', '환영합니다', '제목 6', 6),
    (7, NOW(), '김춘수', NOW(), '박목월', '축하합니다', '제목 7', 7),
    (8, NOW(), '이육사', NOW(), '김소월', '위로합니다', '제목 8', 8),
    (9, NOW(), '윤동주', NOW(), '정지용', '격려합니다', '제목 9', 9),
    (10, NOW(), '박두진', NOW(), '이병주', '응원합니다', '제목 10', 10);