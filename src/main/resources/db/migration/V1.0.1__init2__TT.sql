CREATE TABLE `friend` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `follower_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt7qrapls6a0it0j8g88r9tpu7` (`follower_id`),
  KEY `FKk6euulfnj7jm9x8h9ufbgvoem` (`user_id`),
  CONSTRAINT `FKk6euulfnj7jm9x8h9ufbgvoem` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`),
  CONSTRAINT `FKt7qrapls6a0it0j8g88r9tpu7` FOREIGN KEY (`follower_id`) REFERENCES `user_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;