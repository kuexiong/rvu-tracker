delete from amountbilled;
delete from patient;
delete from user;
INSERT INTO user VALUES (1,'Kue','Xiong','kxiong1@madisoncollege.edu','kxiong1'),
                        (2, 'Spongebob', 'Square Pants', 'sponge@underthesea.com','sponge');
INSERT INTO patient VALUES (1,'Peppa','Pig','2023-01-12','Anxiety','In Progress',1),
                           (2,'George','Pig','2022-12-05','ADHD','In Progress',1),
                           (3,'Grampy','Rabbit','2023-01-08','Memory complaints','Signed',2),
                           (4,'Zuzu','Zebra','2023-02-10','TBI','Final Review',1);
INSERT INTO amountbilled VALUES (1, 1, '2023-01-15 13:15:01', 1, 1),
                                 (2, 1, '2023-01-15 13:15:01', 1, 2),
                                 (3, 1, '2023-01-20 10:30:00', 2, 3);