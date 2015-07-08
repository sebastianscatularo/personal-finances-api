insert into profiles(id, first_name) values (1,"User");
insert into profiles(id, first_name) values (2,"Admin");
insert into profiles(id, first_name) values (3,"Guest");

insert into users(id, profile_id, login, password) values (1, 1,'sebastianscatularo@gmail.com','spring');
insert into users(id, profile_id, login, password) values (2, 2,'sebastianscatularo@gmail.com','spring');
insert into users(id, profile_id, login, password) values (3, 3,'sebastianscatularo@gmail.com','spring');

insert into roles(id, name) values (1,'ROLE_USER');
insert into roles(id, name) values (2,'ROLE_ADMIN');
insert into roles(id, name) values (3,'ROLE_GUEST');

insert into user_role(user_id, role_id) values (1,1);
insert into user_role(user_id, role_id) values (1,2);
insert into user_role(user_id, role_id) values (2,1);
insert into user_role(user_id, role_id) values (3,1);