INSERT INTO `news-website-2024`.`user` (`password`, `username`, `email`, `first_name`, `last_name`)
VALUES ('$2a$10$W2c.HFG4gvkJxexB4yQU..M/1IeEJv/Y1PV.Y.Tw6U.RXRH/.RY.a', 'user', 'test@test.com', 'Nguyễn', 'Văn Toàn');
INSERT INTO `news-website-2024`.`user` (`password`, `username`, `email`, `first_name`, `last_name`)
VALUES ('$2a$10$W2c.HFG4gvkJxexB4yQU..M/1IeEJv/Y1PV.Y.Tw6U.RXRH/.RY.a', 'writer', 'writer@writer.com', 'Nguyễn',
        'Văn Toàn');
INSERT INTO `news-website-2024`.`user` (`password`, `username`, `email`, `first_name`, `last_name`)
VALUES ('$2a$10$W2c.HFG4gvkJxexB4yQU..M/1IeEJv/Y1PV.Y.Tw6U.RXRH/.RY.a', 'admin', 'admin@admin.com', 'Nguyễn',
        'Văn Toàn');

INSERT INTO `news-website-2024`.`role` (`authority`, `description`)
VALUES ('USER', 'Người dùng');
INSERT INTO `news-website-2024`.`role` (`authority`, `description`)
VALUES ('WRITER', 'Tác giả');
INSERT INTO `news-website-2024`.`role` (`authority`, `description`)
VALUES ('ADMIN', 'Quản trị viên');

INSERT INTO `user_role` (`user_id`, `role_id`)
VALUES ('1', '1');
INSERT INTO `user_role` (`user_id`, `role_id`)
VALUES ('2', '1');
INSERT INTO `user_role` (`user_id`, `role_id`)
VALUES ('2', '2');
INSERT INTO `user_role` (`user_id`, `role_id`)
VALUES ('3', '1');
INSERT INTO `user_role` (`user_id`, `role_id`)
VALUES ('3', '3');

INSERT INTO `news-website-2024`.`category` (`alias`, `title`)
VALUES ('the-thao', 'Thể thao');

INSERT INTO `news-website-2024`.`state` (`state_code`, `state_name`)
VALUES ('DRAFT', 'Nháp');
INSERT INTO `news-website-2024`.`state` (`state_code`, `state_name`)
VALUES ('PENDING', 'Đang chờ duyệt');
INSERT INTO `news-website-2024`.`state` (`state_code`, `state_name`)
VALUES ('SUBMITTED', 'Đã gửi duyệt');
INSERT INTO `news-website-2024`.`state` (`state_code`, `state_name`)
VALUES ('APPROVED', 'Đã được chấp nhận');
INSERT INTO `news-website-2024`.`state` (`state_code`, `state_name`)
VALUES ('PUBLISHED', 'Đã đăng tải');
INSERT INTO `news-website-2024`.`state` (`state_code`, `state_name`)
VALUES ('UNPUBLISHED', 'Tạm ngừng đăng tải');
INSERT INTO `news-website-2024`.`state` (`state_code`, `state_name`)
VALUES ('TRASH', 'Đã xóa');
