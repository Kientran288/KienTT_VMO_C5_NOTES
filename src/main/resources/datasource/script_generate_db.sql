-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th3 02, 2022 lúc 02:39 PM
-- Phiên bản máy phục vụ: 10.4.22-MariaDB
-- Phiên bản PHP: 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `demo`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roles`
--

CREATE TABLE `roles` (
                         `id` int(11) NOT NULL,
                         `name` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
                                       (1, 'ROLE_USER'),
                                       (2, 'ROLE_MODERA'),
                                       (3, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
                         `id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
                         `username` varchar(100) NOT NULL,
                         `email` varchar(100) NOT NULL,
                         `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_roles`
--

CREATE TABLE `user_roles` (
                              `id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
                              `user_id` int(11) NOT NULL,
                              `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `roles`
--
ALTER TABLE `roles`
    ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
    ADD UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
    ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- Chỉ mục cho bảng `user_roles`
--
ALTER TABLE `user_roles`
    ADD KEY `role_id` (`role_id`),
  ADD KEY `FK_user` (`user_id`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `user_roles`
--
ALTER TABLE `user_roles`
    ADD CONSTRAINT `FK_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);
COMMIT;


CREATE TABLE `product` (
                           `id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
                           `name` varchar(1000) NULL,
                           `description` varchar(10000) NULL,
                           `quantity` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `demo`.`note` (
                               `id` INT NOT NULL ,
                               `title` VARCHAR(1000) NOT NULL ,
                               `note` LONGTEXT NOT NULL ,
                               `check_box_ids` MEDIUMTEXT NOT NULL ,
                               `image_link` MEDIUMTEXT NOT NULL,
                               `status` INT NOT NULL,
                               `type` INT NOT NULL,
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `demo`.`checkbox`
(`id` INT NOT NULL ,
 `checked` INT NOT NULL ,
 `content` VARCHAR(100) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `roles` (`id`, `name`) VALUES
                                       (1, 'ROLE_USER'),
                                       (2, 'ROLE_MODERA'),
                                       (3, 'ROLE_ADMIN');

INSERT INTO `users` (`id`, `username`, `email`, `password`) VALUES
    (1, 'kent', 'kent@vmo', '$2a$10$wjC/..tbcBuy1P/kABdKD.Q8sTl67Cyu40TbMe013fb2pVXgyl7Hy');

INSERT INTO `user_roles` (`id`, `user_id`, `role_id`) VALUES
    (1, 1, 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
