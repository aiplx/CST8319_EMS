CREATE SCHEMA IF NOT EXISTS `ems_schema` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `ems_schema`;

-- Table `role`
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
                                      `role_id` INT NOT NULL AUTO_INCREMENT,
                                      `role_name` VARCHAR(45) NOT NULL,
                                      `role_desc` VARCHAR(100) NOT NULL,
                                      PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- Table `employee`
DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
                                          `employee_id` INT NOT NULL AUTO_INCREMENT,
                                          `role_id` INT NOT NULL,
                                          `first_name` VARCHAR(45) NOT NULL,
                                          `last_name` VARCHAR(45) NOT NULL,
                                          `city` VARCHAR(45) NOT NULL,
                                          `province` VARCHAR(45) NOT NULL,
                                          `phone_number` VARCHAR(45) NOT NULL, /* added email field by Mohamed 2024-07-29*/
                                          `email` VARCHAR(45) NOT NULL,/* added phone number field by Mohamed 2024-07-29*/
                                          `password` VARCHAR(45) NOT NULL, /* added password field by Ping 2024-07-28*/
                                          `username` VARCHAR(45) NOT NULL, /* added password field by Ping 2024-07-28*/
                                          PRIMARY KEY (`employee_id`),
                                          FOREIGN KEY (`role_id`) REFERENCES `role`(`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table `availability`
DROP TABLE IF EXISTS `availability`;
CREATE TABLE IF NOT EXISTS `availability` (
                                              `availability_id` INT NOT NULL AUTO_INCREMENT,
                                              `employee_id` INT NOT NULL,
                                              `weekday` ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday') NOT NULL,
                                              `start_time` TIME NOT NULL,
                                              `end_time` TIME NOT NULL,
                                              PRIMARY KEY (`availability_id`),
                                              FOREIGN KEY (`employee_id`) REFERENCES `employee`(`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table `payhistory`
DROP TABLE IF EXISTS `payhistory`;
CREATE TABLE IF NOT EXISTS `payhistory` (
                                            `payhistory_id` INT NOT NULL AUTO_INCREMENT,
                                            `employee_id` INT NOT NULL,
                                            `period_start` DATE NOT NULL,
                                            `period_end` DATE NOT NULL,
                                            `total_hours` INT NOT NULL,
                                            `total_pay` INT NOT NULL,
                                            PRIMARY KEY (`payhistory_id`),
                                            FOREIGN KEY (`employee_id`) REFERENCES `employee`(`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table `request_type`
DROP TABLE IF EXISTS `request_type`;
CREATE TABLE IF NOT EXISTS `request_type` (
                                              `request_type_id` INT NOT NULL AUTO_INCREMENT,
                                              `request_desc` VARCHAR(255) NOT NULL,
                                              PRIMARY KEY (`request_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table `requests`
DROP TABLE IF EXISTS `user_requests`;
CREATE TABLE IF NOT EXISTS `user_requests` (
                                               `request_id` INT NOT NULL AUTO_INCREMENT,
                                               `request_type_id` INT NOT NULL,
                                               `employee_id` INT NOT NULL,
                                               `request_status` ENUM('approved', 'denied', 'not_accessed') NOT NULL,
                                               PRIMARY KEY (`request_id`),
                                               FOREIGN KEY (`request_type_id`) REFERENCES `request_type`(`request_type_id`),
                                               FOREIGN KEY (`employee_id`) REFERENCES `employee`(`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table `schedule`
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE IF NOT EXISTS `schedule` (
                                          `schedule_id` INT NOT NULL AUTO_INCREMENT,
                                          `employee_id` INT NOT NULL,
                                          `date` DATE NOT NULL,
                                          `start_time` TIME NOT NULL,
                                          `end_time` TIME NOT NULL,
                                          PRIMARY KEY (`schedule_id`),
                                          FOREIGN KEY (`employee_id`) REFERENCES `employee`(`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table `vacation`
DROP TABLE IF EXISTS `vacation`;
CREATE TABLE IF NOT EXISTS `vacation` (
                                          `vacation_id` INT NOT NULL AUTO_INCREMENT,
                                          `start_date` DATE NOT NULL,
                                          `end_date` DATE NOT NULL,
                                          `employee_id` INT NOT NULL,
                                          PRIMARY KEY (`vacation_id`),
                                          FOREIGN KEY (`employee_id`) REFERENCES `employee`(`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table `account_info`
-- Delete by Ping 2024-07-28
# DROP TABLE IF EXISTS `account_info`;
# CREATE TABLE IF NOT EXISTS `account_info` (
#                                               `user_password_id` INT NOT NULL AUTO_INCREMENT,
#                                               `employee_id` INT NOT NULL,
#                                               `username` VARCHAR(45) NOT NULL,
#                                               `password` VARCHAR(45) NOT NULL,
#                                               PRIMARY KEY (`user_password_id`),
#                                               FOREIGN KEY (`employee_id`) REFERENCES `employee`(`employee_id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- End of deletion for account_info

-- Table `message` (new table for communications)
DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
                                         `message_id` INT NOT NULL AUTO_INCREMENT,
                                         `sender_id` INT NOT NULL,
                                         `receiver_id` INT,
                                         `title` VARCHAR(200) NOT NULL, /* Added title field for messages - Mohamed 7/29/24*/
                                         `message_content` TEXT NOT NULL,
                                         `is_read` BOOLEAN DEFAULT FALSE, /* Added boolean is_read field for messages - Mohamed 07/29/2024 */
                                         `timestamp` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                         PRIMARY KEY (`message_id`),
                                         FOREIGN KEY (`sender_id`) REFERENCES `employee`(`employee_id`),
                                         FOREIGN KEY (`receiver_id`) REFERENCES `employee`(`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table `time_tracking` (new table for detailed time tracking)
DROP TABLE IF EXISTS `time_tracking`;
CREATE TABLE IF NOT EXISTS `time_tracking` (
                                               `time_tracking_id` INT NOT NULL AUTO_INCREMENT,
                                               `employee_id` INT NOT NULL,
                                               `clock_in` TIMESTAMP NOT NULL,
                                               `clock_out` TIMESTAMP,
                                               PRIMARY KEY (`time_tracking_id`),
                                               FOREIGN KEY (`employee_id`) REFERENCES `employee`(`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
