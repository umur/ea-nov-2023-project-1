INSERT INTO `profile` (`id`, `created_date`, `delete_yn`, `modified_date`, `show_yn`, `city`, `country`, `major`, `phone_number`, `profile_image`, `state`, `street`, `zip`) VALUES
    (1, '2023-11-14 13:38:48.829000', b'0', '2023-11-14 13:38:48.829000', b'1', 'Fairfield', 'USA', 'ComPro', '94641960', 'Link to the image', 'Iowa', 'S D street', 52556);

INSERT INTO `user` (`id`, `created_date`, `delete_yn`, `modified_date`, `show_yn`, `account_locked`, `email`, `failed_login_attempts`, `first_name`, `last_failed_login_timestamp`, `last_name`, `password`, `role`, `token`, `profile_id`) VALUES
    (1, '2023-11-14 13:38:48.824000', b'0', '2023-11-14 13:38:48.824000', b'1', b'0', 'test@gmail.com', 0, 'Saikhanbat', NULL, 'Bayarsaikhan', '123456789', 'ADMIN', NULL, 1);
