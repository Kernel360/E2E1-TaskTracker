-- test_db.flyway_schema_history definition

CREATE TABLE `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- test_db.notification definition

CREATE TABLE `notification` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `receiver_id` bigint NOT NULL,
  `sender_id` bigint NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `visible` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- test_db.notification_entity definition

CREATE TABLE `notification_entity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `noti_type` varchar(255) DEFAULT NULL,
  `receiver_id` bigint NOT NULL,
  `sender_id` bigint NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- test_db.user_info definition
--
CREATE TABLE `user_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `admin_check` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `exit_date` datetime(6) DEFAULT NULL,
  `is_ban` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gnu0k8vv6ptioedbxbfsnan9g` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1007 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- test_db.board definition

CREATE TABLE `board` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `user_info_id` bigint NOT NULL,
  `is_finished` bit(1) NOT NULL,
  `finished` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDXmph7qe4yv41dlsoap3i3nojto` (`title`),
  KEY `IDXjk711ewcdq5de75sinadge90q` (`created_at`),
  KEY `IDXonho5rbblvelk09215m7tubis` (`created_by`),
  KEY `FK3le375nc8mp9fvpo16i7sq6md` (`user_info_id`),
  CONSTRAINT `FK3le375nc8mp9fvpo16i7sq6md` FOREIGN KEY (`user_info_id`) REFERENCES `user_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1034 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- test_db.board_members definition

CREATE TABLE `board_members` (
  `board_id` bigint NOT NULL,
  `members_id` bigint NOT NULL,
  UNIQUE KEY `UK_budwa60idxciii4rtchubnbg3` (`members_id`),
  KEY `FK2637hdcxhcb38r8us280l55go` (`board_id`),
  CONSTRAINT `FK2637hdcxhcb38r8us280l55go` FOREIGN KEY (`board_id`) REFERENCES `board` (`id`),
  CONSTRAINT `FKj3hs1qx9xlkfg50entvs054kq` FOREIGN KEY (`members_id`) REFERENCES `user_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- test_db.friend definition

--CREATE TABLE `friend` (
--  `id` bigint NOT NULL AUTO_INCREMENT,
--  `follower_id` bigint DEFAULT NULL,
--  `user_id` bigint DEFAULT NULL,
--  PRIMARY KEY (`id`),
--  KEY `FKt7qrapls6a0it0j8g88r9tpu7` (`follower_id`),
--  KEY `FKk6euulfnj7jm9x8h9ufbgvoem` (`user_id`),
--  CONSTRAINT `FKk6euulfnj7jm9x8h9ufbgvoem` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`),
--  CONSTRAINT `FKt7qrapls6a0it0j8g88r9tpu7` FOREIGN KEY (`follower_id`) REFERENCES `user_info` (`id`)
--) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- test_db.task definition

CREATE TABLE `task` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `is_finished` bit(1) NOT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `worker` varchar(255) DEFAULT NULL,
  `board_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrar3pm9ixqub1nilws0l8l22w` (`board_id`),
  CONSTRAINT `FKrar3pm9ixqub1nilws0l8l22w` FOREIGN KEY (`board_id`) REFERENCES `board` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;