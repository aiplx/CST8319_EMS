USE ems_schema;

-- Insert value to Table `role` added by Ping 2024-07-28
INSERT INTO `role` (`role_name`, `role_desc`)
VALUES ('Manager', 'Manages team'),
       ('Employee', 'Handles tasks and reports to manager');
-- End of insertion for role table.

-- Insert test values into the 'Employee' table added by Mohamed 2024-07-18
INSERT INTO `ems_schema`.`employee`
(`role_id`, `first_name`, `last_name`, `city`, `province`, `password`, `username`,`email`, `phone_number`)
VALUES
    (1, 'Johnathon', 'Donut', 'Ottawa', 'Ontario', 'password123', 'johndoe', 'sendthathisway@gmail.com', '+14034642639'),
    (1, 'Samuel', 'Pie', 'Oshawa', 'Ontario', '123password', 'sampie', 'someotherfake@email.com', '+15999999999'),
    (2, 'Jane', 'Smith', 'Edmonton', 'Alberta', 'manager456', 'janesmith', 'dimbo615@gmail.com', '+12345678910');
-- End of test values insertion.