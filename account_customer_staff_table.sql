-- use electricsystem;
INSERT INTO staff (`id`,`address`,`email`,`gender`,`name`,`phone_number`) VALUES (1,'Trần Phú, Mộ Lao,Hà Đông, Hà Nội','ngochoai@gmai.com','1','Trần Thị Ngọc Hoài','0866994382');
INSERT INTO staff (`id`,`address`,`email`,`gender`,`name`,`phone_number`) VALUES (2,'Khương Điình, Thanh Xuân, Hà Nội','daobichdiep@gmail.com','1','Đào Bích Diệp','0673491211');
INSERT INTO staff (`id`,`address`,`email`,`gender`,`name`,`phone_number`) VALUES (3,'Lê Lai, Quang Trung, Hà Đông, Hà Nội','hoangminhduc@gmail.com','0','Hoàng Minh Đức','0392486754');
INSERT INTO staff (`id`,`address`,`email`,`gender`,`name`,`phone_number`) VALUES (4,'Hà Đông, Hà Nội','admin@gmail.com','1','Admin','0971261221');

INSERT INTO customer (`id`,`address`,`email`,`gender`,`name`,`phone_number`,`username`) VALUES (1,'10 Trần Phú, Mộ Lao, Hà Đông','hoangvananh7201@gmail.com',1,'Hoàng Vân Anh','0961082342','HD11300001');
INSERT INTO customer (`id`,`address`,`email`,`gender`,`name`,`phone_number`,`username`) VALUES (2,'12 Nguyễn Văn Lộc, Mộ Lao, Hà Đông','hoangthuhien@gmail.com',1,'Hoàng Thu Hiền','0679823541','HD11300002');
INSERT INTO customer (`id`,`address`,`email`,`gender`,`name`,`phone_number`,`username`) VALUES (3,'82 Lê Lai, Hà Cầu, Hà Đông','nguyenvanquang@gmail.com',0,'Nguyễn Văn Quân','0967453276','HD11300003');
INSERT INTO customer (`id`,`address`,`email`,`gender`,`name`,`phone_number`,`username`) VALUES (4,'17 Thanh Bình, Vạn Phúc, Hà Đông','doanvanbach@gmail.com',0,'Đoàn Văn Bách','0367854312','HD11300004');
INSERT INTO customer (`id`,`address`,`email`,`gender`,`name`,`phone_number`,`username`) VALUES (5,'27 Lê Trọng Tấn, Quang Trung, Hà Đông','nguyenngocanh@gmail.com',1,'Nguyễn Ngọc Ánh','0946719823','HD11300005');
INSERT INTO customer (`id`,`address`,`email`,`gender`,`name`,`phone_number`,`username`) VALUES (6,'368 Quang Trung, Quang Trung, Hà Đông','tranngocanh@gmail.com',1,'Trần Thị Ngọc Anh','0786820123','HD11300006');
INSERT INTO customer (`id`,`address`,`email`,`gender`,`name`,`phone_number`,`username`) VALUES (7,'68 La Khê, Văn Khê, Hà Đông','phamducbinh@gmail.com',0,'Phạm Đức Bình','0986723198','HD11300007');

INSERT INTO account (`id`,`password`,`role`,`username`,`customer_id`,`staff_id`) VALUES (1,'$2a$12$lqvL7eCoNwE.Kwb47qaLJO0Y6mWzw9KOt8IxDxMib3vUpmnp39pJy',1,'HD11300001',1,NULL);
INSERT INTO account (`id`,`password`,`role`,`username`,`customer_id`,`staff_id`) VALUES (2,'$2a$10$EtvJKcbeBsBDUMg49uR4sekm/NBhQt0j2X0NrZAywEbgMlDgafkHa',1,'HD11300002',2,NULL);
INSERT INTO account (`id`,`password`,`role`,`username`,`customer_id`,`staff_id`) VALUES (3,'$2a$10$.nFpCL/meachdui3xpC8RevANsqHmhTVk7HTaqaqpEI4HdmO9Yy6i',1,'HD11300003',3,NULL);
INSERT INTO account (`id`,`password`,`role`,`username`,`customer_id`,`staff_id`) VALUES (4,'$2a$10$FF30hABXxHCNu5QgX89xe.KSpIo5BgSIlUcEGeV0R4aXsGjrfFIy.',1,'HD11300004',4,NULL);
INSERT INTO account (`id`,`password`,`role`,`username`,`customer_id`,`staff_id`) VALUES (5,'$2a$10$xSDTOVE/etThMSaFEu4QCeIX/Gmb1.cKvudLGPGX8TLFYEI2Vvfvq',1,'HD11300005',5,NULL);
INSERT INTO account (`id`,`password`,`role`,`username`,`customer_id`,`staff_id`) VALUES (6,'$2a$10$p3JQ6YJBFtbt9b/tdHHmIu5u0A26R6TYkfj04DdtNehAVVYPWl7XG',1,'HD11300006',6,NULL);
INSERT INTO account (`id`,`password`,`role`,`username`,`customer_id`,`staff_id`) VALUES (7,'$2a$10$iApRjSmhBl1cFnNz78P.Le.FmyAQeAzJ1Q/oBhZT8836Ar0VtKlPG',1,'HD11300007',7,NULL);
INSERT INTO account (`id`,`password`,`role`,`username`,`customer_id`,`staff_id`) VALUES (8,'$2a$12$AFyXU7uxa5feUn113n2L1eKNfNMeIy5kyqrlFVI6NbifL9KQ/n/LS',0,'admin123',NULL,4);
INSERT INTO account (`id`,`password`,`role`,`username`,`customer_id`,`staff_id`) VALUES (9,'$2a$12$votbiPSD/bYxPjwavVXnlOv9koHWvt0it3qG7S2al0HcngmlvRxki',0,'ngochoai',NULL,1);
INSERT INTO account (`id`,`password`,`role`,`username`,`customer_id`,`staff_id`) VALUES (10,'$2a$12$LcfIguLhX5JarVYL47oruuex2baT99zC0OFQ87l7TE3NZAd8bPkbu',0,'bichdiep',NULL,2);
