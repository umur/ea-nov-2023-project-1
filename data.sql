-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.34 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for alumni-project
CREATE DATABASE IF NOT EXISTS `alumni-project` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `alumni-project`;

-- Dumping structure for table alumni-project.achievement
CREATE TABLE IF NOT EXISTS `achievement` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `profile_id` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkior25kjveahn9x4tbpy4u1e8` (`profile_id`),
  CONSTRAINT `FKkior25kjveahn9x4tbpy4u1e8` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table alumni-project.achievement: ~0 rows (approximately)

-- Dumping structure for table alumni-project.course
CREATE TABLE IF NOT EXISTS `course` (
  `gpa` double DEFAULT NULL,
  `education_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7y5eatxbbb595eyeukwx7smaj` (`education_id`),
  CONSTRAINT `FK7y5eatxbbb595eyeukwx7smaj` FOREIGN KEY (`education_id`) REFERENCES `education` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table alumni-project.course: ~0 rows (approximately)

-- Dumping structure for table alumni-project.education
CREATE TABLE IF NOT EXISTS `education` (
  `graduation_year` int NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `profile_id` bigint DEFAULT NULL,
  `degree` varchar(255) DEFAULT NULL,
  `university` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKelocxwwcyf5acj85hgke1c0fl` (`profile_id`),
  CONSTRAINT `FKelocxwwcyf5acj85hgke1c0fl` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table alumni-project.education: ~2 rows (approximately)
INSERT INTO `education` (`graduation_year`, `id`, `profile_id`, `degree`, `university`) VALUES
	(2024, 1, 1, 'Master', 'MIU'),
	(2024, 2, 2, 'Master', 'MIU');

-- Dumping structure for table alumni-project.experience
CREATE TABLE IF NOT EXISTS `experience` (
  `delete_yn` bit(1) DEFAULT NULL,
  `show_yn` bit(1) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `job_id` bigint DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_a404pcl6cct5njb7njamtb4a3` (`job_id`),
  CONSTRAINT `FKt76y8ooog2ickwjm29pv92657` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table alumni-project.experience: ~0 rows (approximately)

-- Dumping structure for table alumni-project.job
CREATE TABLE IF NOT EXISTS `job` (
  `delete_yn` bit(1) DEFAULT NULL,
  `show_yn` bit(1) DEFAULT NULL,
  `assigner_id` bigint DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `location_id` bigint DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `poster_id` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `organization` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4fujawc6aybbj5gsi9c0c3wrc` (`assigner_id`),
  UNIQUE KEY `UK_48qe9xq9hdahsyi2asoathxbx` (`location_id`),
  UNIQUE KEY `UK_gyhoyoxcd7pwxi7qpsu0vucvf` (`poster_id`),
  CONSTRAINT `FK4d9rme1ojqlfdif4jjmmaptp1` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`),
  CONSTRAINT `FK8nm1biutxe5u2m4lj7uqwcnuc` FOREIGN KEY (`poster_id`) REFERENCES `profile` (`id`),
  CONSTRAINT `FKef21ij4vfivmdi1tbbawp5kc6` FOREIGN KEY (`assigner_id`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table alumni-project.job: ~2 rows (approximately)
INSERT INTO `job` (`delete_yn`, `show_yn`, `assigner_id`, `created_date`, `id`, `location_id`, `modified_date`, `poster_id`, `description`, `organization`, `title`) VALUES
	(b'0', b'1', 1, '2023-11-09 15:00:46.000000', 1, 1, '2023-11-09 15:00:52.000000', 1, 'Fullstack', 'Any', 'Java developer'),
	(b'0', b'1', 2, '2023-11-09 15:05:13.000000', 2, 2, '2023-11-09 15:05:19.000000', 2, 'Front-End', 'MKIT', 'React developer');

-- Dumping structure for table alumni-project.location
CREATE TABLE IF NOT EXISTS `location` (
  `zip` int NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table alumni-project.location: ~2 rows (approximately)
INSERT INTO `location` (`zip`, `id`, `city`, `country`, `state`, `street`) VALUES
	(52557, 1, 'Fairfield', 'US', 'Iowa', '1000 N 4th Street'),
	(13100, 2, 'Ulaanbaatar', 'MGL', 'Ulaanbaatar', 'Baga toiruu');

-- Dumping structure for table alumni-project.log
CREATE TABLE IF NOT EXISTS `log` (
  `roles` tinyint DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `method` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table alumni-project.log: ~8 rows (approximately)
INSERT INTO `log` (`roles`, `date`, `id`, `method`, `name`, `url`) VALUES
	(2, '2023-11-09 15:09:06.734208', 1, 'getJobs', 'Saikhanbat', 'http://localhost:8080/api/jobs'),
	(2, '2023-11-09 15:09:11.242737', 2, 'getJobs', 'Saikhanbat', 'http://localhost:8080/api/jobs'),
	(2, '2023-11-09 15:09:14.936849', 3, 'getJobs', 'Saikhanbat', 'http://localhost:8080/api/jobs'),
	(2, '2023-11-09 15:09:17.733405', 4, 'getJobs', 'Saikhanbat', 'http://localhost:8080/api/jobs'),
	(2, '2023-11-09 15:12:26.017608', 5, 'getJobs', 'Saikhanbat', 'http://localhost:8080/api/jobs'),
	(2, '2023-11-09 15:13:10.528125', 6, 'getJobs', 'Saikhanbat', 'http://localhost:8080/api/jobs'),
	(2, '2023-11-09 15:13:13.711232', 7, 'getUser', 'Saikhanbat', 'http://localhost:8080/api/users/1'),
	(2, '2023-11-09 15:13:16.900330', 8, 'getUsers', 'Saikhanbat', 'http://localhost:8080/api/users');

-- Dumping structure for table alumni-project.profile
CREATE TABLE IF NOT EXISTS `profile` (
  `delete_yn` bit(1) DEFAULT NULL,
  `show_yn` bit(1) DEFAULT NULL,
  `zip` int NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modified_date` datetime(6) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `profile_image` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table alumni-project.profile: ~2 rows (approximately)
INSERT INTO `profile` (`delete_yn`, `show_yn`, `zip`, `created_date`, `id`, `modified_date`, `city`, `country`, `major`, `phone_number`, `profile_image`, `state`, `street`) VALUES
	(b'0', b'1', 52556, '2023-11-09 14:59:42.258000', 1, '2023-11-09 14:59:42.258000', 'Fairfield', 'USA', 'ComPro', '94641960', 'Link to the image', 'Iowa', 'S D street'),
	(b'0', b'1', 52556, '2023-11-09 15:06:52.678000', 2, '2023-11-09 15:06:52.678000', 'Fairfield', 'USA', 'ComPro', '6415570321', 'Link to the image', 'Iowa', 'S D street');

-- Dumping structure for table alumni-project.user
CREATE TABLE IF NOT EXISTS `user` (
  `account_locked` bit(1) DEFAULT NULL,
  `delete_yn` bit(1) DEFAULT NULL,
  `failed_login_attempts` int NOT NULL,
  `role` tinyint DEFAULT NULL,
  `show_yn` bit(1) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `last_failed_login_timestamp` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `profile_id` bigint DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1mcjtpxmwom9h9bf2q0k412e0` (`profile_id`),
  CONSTRAINT `FKof44u64o1d7scaukghm9veo23` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table alumni-project.user: ~2 rows (approximately)
INSERT INTO `user` (`account_locked`, `delete_yn`, `failed_login_attempts`, `role`, `show_yn`, `created_date`, `id`, `last_failed_login_timestamp`, `modified_date`, `profile_id`, `email`, `first_name`, `last_name`, `password`, `token`) VALUES
	(b'0', b'0', 0, 2, b'1', '2023-11-09 14:59:42.254000', 1, NULL, '2023-11-09 14:59:42.254000', 1, 'test@gmail.com', 'Saikhanbat', 'Bayarsaikhan', '123456789', NULL),
	(b'0', b'0', 0, 2, b'1', '2023-11-09 15:06:52.678000', 2, NULL, '2023-11-09 15:06:52.678000', 2, '123@gmail.com', 'Tsogtbayar', 'Sukhbaatar', '123456789', NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
