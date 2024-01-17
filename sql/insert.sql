INSERT INTO `news-website-2024`.`user` (`password`, `username`, `email`, `first_name`, `last_name`)
VALUES ('$2a$10$W2c.HFG4gvkJxexB4yQU..M/1IeEJv/Y1PV.Y.Tw6U.RXRH/.RY.a', 'user', 'test@test.com', 'Nguyễn', 'Văn Toàn');
INSERT INTO `news-website-2024`.`user` (`password`, `username`, `email`, `first_name`, `last_name`)
VALUES ('$2a$10$W2c.HFG4gvkJxexB4yQU..M/1IeEJv/Y1PV.Y.Tw6U.RXRH/.RY.a', 'user02', 'admin@test.com', 'Nguyễn',
        'Văn Toàn');

INSERT INTO `news-website-2024`.`role` (`authority`, `description`)
VALUES ('USER', 'Người dùng');
INSERT INTO `news-website-2024`.`role` (`authority`, `description`)
VALUES ('WRITER', 'Tác giả');
INSERT INTO `news-website-2024`.`role` (`authority`, `description`)
VALUES ('ADMIN', 'Quản trị viên');
