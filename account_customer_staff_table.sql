-- use electricsystem;
INSERT INTO staff (`id`,`address`,`email`,`gender`,`name`,`phone_number`) VALUES (1,'Trần Phú, Mộ Lao,Hà Đông, Hà Nội','ngochoai@gmai.com','1','Trần Thị Ngọc Hoài','0866994382');
INSERT INTO staff (`id`,`address`,`email`,`gender`,`name`,`phone_number`) VALUES (2,'Khương Điình, Thanh Xuân, Hà Nội','daobichdiep@gmail.com','1','Đào Bích Diệp','0673491211');
INSERT INTO staff (`id`,`address`,`email`,`gender`,`name`,`phone_number`) VALUES (3,'Lê Lai, Quang Trung, Hà Đông, Hà Nội','hoangminhduc@gmail.com','0','Hoàng Minh Đức','0392486754');
INSERT INTO staff (`id`,`address`,`email`,`gender`,`name`,`phone_number`) VALUES (4,'Hà Đông, Hà Nội','admin@gmail.com','1','Admin','0971261221');

INSERT INTO customer (`id`,`address`,`email`,`gender`,`name`,`phone_number`,`username`) VALUES (1,'10 Trần Phú, Mộ Lao, Hà Đông','hoangvananh7201@gmail.com',1,'Hoàng Vân Anh','0961082342','ESHNHD001');
INSERT INTO customer (`id`,`address`,`email`,`gender`,`name`,`phone_number`,`username`) VALUES (2,'12 Nguyễn Văn Lộc, Mộ Lao, Hà Đông','hoangthuhien@gmail.com',1,'Hoàng Thu Hiền','0679823541','ESHNHD002');
INSERT INTO customer (`id`,`address`,`email`,`gender`,`name`,`phone_number`,`username`) VALUES (3,'82 Lê Lai, Hà Cầu, Hà Đông','nguyenvanquang@gmail.com',0,'Nguyễn Văn Quân','0967453276','ESHNHD003');
INSERT INTO customer (`id`,`address`,`email`,`gender`,`name`,`phone_number`,`username`) VALUES (4,'17 Thanh Bình, Vạn Phúc, Hà Đông','doanvanbach@gmail.com',0,'Đoàn Văn Bách','0367854312','ESHNHD004');
INSERT INTO customer (`id`,`address`,`email`,`gender`,`name`,`phone_number`,`username`) VALUES (5,'27 Lê Trọng Tấn, Quang Trung, Hà Đông','nguyenngocanh@gmail.com',1,'Nguyễn Ngọc Ánh','0946719823','ESHNHD005');
INSERT INTO customer (`id`,`address`,`email`,`gender`,`name`,`phone_number`,`username`) VALUES (6,'368 Quang Trung, Quang Trung, Hà Đông','tranngocanh@gmail.com',1,'Trần Thị Ngọc Anh','0786820123','ESHNHD006');
INSERT INTO customer (`id`,`address`,`email`,`gender`,`name`,`phone_number`,`username`) VALUES (7,'68 La Khê, Văn Khê, Hà Đông','phamducbinh@gmail.com',0,'Phạm Đức Bình','0986723198','ESHNHD007');

INSERT INTO account (`id`,`customer_id`,`password`,`role`,`staff_id`,`username`) VALUES (1,1,'$2a$10$QRiEpuCHjvhujEvouPNbeesm7GaYA5ALksIlswmISXRIP6L9ojmK.',1,null,'ESHNHD001');
INSERT INTO account (`id`,`customer_id`,`password`,`role`,`staff_id`,`username`) VALUES (2,2,'$2a$10$EtvJKcbeBsBDUMg49uR4sekm/NBhQt0j2X0NrZAywEbgMlDgafkHa',1,null,'ESHNHD002');
INSERT INTO account (`id`,`customer_id`,`password`,`role`,`staff_id`,`username`) VALUES (3,3,'$2a$10$.nFpCL/meachdui3xpC8RevANsqHmhTVk7HTaqaqpEI4HdmO9Yy6i',1,null,'ESHNHD003');
INSERT INTO account (`id`,`customer_id`,`password`,`role`,`staff_id`,`username`) VALUES (4,4,'$2a$10$FF30hABXxHCNu5QgX89xe.KSpIo5BgSIlUcEGeV0R4aXsGjrfFIy.',1,null,'ESHNHD004');
INSERT INTO account (`id`,`customer_id`,`password`,`role`,`staff_id`,`username`) VALUES (5,5,'$2a$10$xSDTOVE/etThMSaFEu4QCeIX/Gmb1.cKvudLGPGX8TLFYEI2Vvfvq',1,null,'ESHNHD005');
INSERT INTO account (`id`,`customer_id`,`password`,`role`,`staff_id`,`username`) VALUES (6,6,'$2a$10$p3JQ6YJBFtbt9b/tdHHmIu5u0A26R6TYkfj04DdtNehAVVYPWl7XG',1,null,'ESHNHD006');
INSERT INTO account (`id`,`customer_id`,`password`,`role`,`staff_id`,`username`) VALUES (7,7,'$2a$10$iApRjSmhBl1cFnNz78P.Le.FmyAQeAzJ1Q/oBhZT8836Ar0VtKlPG',1,null,'ESHNHD007');
INSERT INTO account (`id`,`customer_id`,`password`,`role`,`staff_id`,`username`) VALUES (8,null,'$2a$10$ojR5YC7W0lY0MAL8M0R5FuHCtJSlkzYELW8VCk4vxEvK1htinWHtO',0,4,'admin');
INSERT INTO account (`id`,`customer_id`,`password`,`role`,`staff_id`,`username`) VALUES (9,null,'$2a$10$5A1j4kTG6NaYK7wBs1ttAu7jhtBX4nkxcr8LZeNAYXEFU41O.BZTS',0,1,'ngochoai');
INSERT INTO account (`id`,`customer_id`,`password`,`role`,`staff_id`,`username`) VALUES (10,null,'$2a$10$pI8vArOR1tJhfIVGLTQg/eC8Z7EGotFq53enww46ipmnsGl36CByy',0,2,'bichdiep');
