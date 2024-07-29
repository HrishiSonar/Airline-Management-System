create database airline1;

use airline1;

create table flight_dtls(
    id int primary key auto_increment,
    name varchar(50),
    class varchar(50),
    destination varchar(50),
    source varchar(50),
    departure datetime,
    arrival datetime
);

insert into flight_details (id, name, flight_class, destination, source, departure, arrival, fare_price) values
(default, 'Air Asia', 'Economy', 'BOM', 'PNQ', '2023-12-12 12:12:00', '2023-12-12 15:12:00', 5670.5),
(default, 'Akasa', 'Economy', 'PNQ', 'BOM', '2023-12-12 12:12:00', '2023-12-12 15:12:00', 5670.5),
(default, 'Indigo', 'Economy', 'HYD', 'BOM', '2023-12-12 12:12:00', '2023-12-12 15:12:00', 5670.5),
(default, 'Air Asia', 'Economy', 'PNQ', 'IDR', '2023-12-12 12:12:00', '2023-12-12 15:12:00', 5670.5),
(default, 'Indigo', 'Economy', 'IDR', 'PNQ', '2023-12-12 12:12:00', '2023-12-12 15:12:00', 5670.5),
(default, 'Akasa', 'Economy', 'BOM', 'IND', '2023-12-12 12:12:00', '2023-12-12 15:12:00', 5670.5);

insert into user_details (id,cpass,email,name,role) values (default,'Prachi123','pg@gmail.com','Prachi','USER');

insert into address_details(pincode, city, state) values (482001,'Jabalpur', 'Madhya Pradesh'),
(456006,'Ujjain', 'Madhya Pradesh'), 
(411057,'Pune', 'Maharastra'), 
(400099, 'Mumbai', 'Maharashtra'),
(440001, 'Nagpur', 'Maharashtra'),
(400601, 'Thane', 'Maharashtra'),
(422001, 'Nashik', 'Maharashtra');
insert into  general_details (customer_id,aadhar, address,dob,gender,mobile_number,pincode_pincode) values(1,'AA1','MP','2000-02-26','MALE','9999955555', 482001);

insert into payment_details(status, total_amount, customer_id) values('Successful', 5000.5, 1);

insert into booking_details(id,duration, fare_price, customer_id, flight_id, payment_id) values(default, '01:00:00', 1000.0,1 , 1, 1);

insert into passenger_details(id,name,bookingid_id,user_details_id) values(default, 'Sagar',1,1); 

