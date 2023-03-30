CREATE TABLE `group_expenses` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `group_name` varchar(255) NOT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `users` (
                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `user_name` varchar(255) NOT NULL,
                         UNIQUE KEY `uk_k8d0f2n7n88w1a16yhua64onx` (`user_name`),
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `balances` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `active` tinyint(1) DEFAULT NULL,
                            `description` varchar(255) DEFAULT NULL,
                            `group_id` bigint(20) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            CONSTRAINT `fkkaow0a2clmjqfp8wbcx2en1vj` FOREIGN KEY (`group_id`) REFERENCES `group_expenses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `expenses` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `currency` varchar(255) NOT NULL,
                            `description` varchar(255) NOT NULL,
                            `expense_date` timestamp(6) NOT NULL,
                            `price` decimal(38,2) NOT NULL,
                            `balance_id` bigint(20) NOT NULL,
                            `group_id` bigint(20) NOT NULL,
                            `user_id` bigint(20) NOT NULL,
                            PRIMARY KEY (`id`),
                            CONSTRAINT `fk3m46q9jnvs9alweejcte894vx` FOREIGN KEY (`balance_id`) REFERENCES `balances` (`id`),
                            CONSTRAINT `fkhpk0n2cbnfiuu5nrgl0ika3hq` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
                            CONSTRAINT `fkne4jfgy6h5e1gv78elj8bypb5` FOREIGN KEY (`group_id`) REFERENCES `group_expenses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user_group` (
                              `user_id` bigint(20) NOT NULL,
                              `group_id` bigint(20) NOT NULL,
                              CONSTRAINT `fk7k9ade3lqbo483u9vuryxmm34` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
                              CONSTRAINT `fkbegtgnl3oq004958pisko4fu4` FOREIGN KEY (`group_id`) REFERENCES `group_expenses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
