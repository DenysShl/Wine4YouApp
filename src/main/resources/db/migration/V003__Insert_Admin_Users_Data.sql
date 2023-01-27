INSERT INTO public.users (address_id,birthday,email,first_name,last_name,"password",phone,registration_date,cart_id)
VALUES (NULL,'2022-12-29','7860@gmail.com','Den','Shl','$2a$10$eEjs.dMKVNtNSgsmCHaIn..e2kddxnjU4o5xGjnObNnAWYFJBtfZ2','+30501321212','2022-12-29 20:18:57.038646',NULL),
       (NULL,'2022-12-29','123@gmail.com','Dmt','Lem','$2a$10$8imFxnRXlXS7x/IKmBFZKONGdWcAprwbdppm7Mss8T5CsMGMJA41u','+30501595959','2022-12-29 20:18:57.16901',NULL);
INSERT INTO public.users_roles (user_id,role_id)
VALUES (1,1),
       (2,2);