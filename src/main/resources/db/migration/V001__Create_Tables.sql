drop table if exists address;
drop table if exists events;
drop table if exists orders;
drop table if exists orders_wines;
drop table if exists roles;
drop table if exists shopping_carts;
drop table if exists shopping_carts_products;
drop table if exists styles;
drop table if exists tastes;
drop table if exists users;
drop table if exists users_roles;
drop table if exists wine_images;
drop table if exists wines;

create table address (
                         id serial not null,
                         apartment varchar(255),
                         city varchar(255),
                         street varchar(255),
                         primary key (id)
);

create table carts (
                       id serial not null,
                       buy_as_gift boolean,
                       created_date timestamp,
                       delivery_price float8,
                       discount int4,
                       is_called boolean,
                       payment varchar(255),
                       shipping varchar(255),
                       total_amount numeric(19, 2),
                       address_id int8,
                       user_id int8,
                       primary key (id)
);

create table carts_items (
                             cart_id int8 not null,
                             items_id int8 not null
);

create table events (
                        id serial4 not null,
                        name_event varchar(255),
                        primary key (id)
);

create table items (
                       id serial4 not null,
                       quantity int4,
                       total numeric(19, 2),
                       cart_id int8,
                       order_id int8,
                       wine_id int8,
                       primary key (id)
);

create table meals (
                       id serial4 not null,
                       meal_name varchar(255),
                       primary key (id)
);

create table orders (
                        id serial4 not null,
                        created_date timestamp,
                        delivery_price float8,
                        discount int4,
                        total_amount numeric(19, 2),
                        primary key (id)
);

create table orders_items (
                              order_id serial4 not null,
                              item_id serial4 not null
);

create table roles (
                       id  serial4 not null,
                       role_name varchar(255),
                       primary key (id)
);

create table styles (
                        id  serial4 not null,
                        name_style varchar(255),
                        primary key (id)
);

create table tastes (
                        id serial4 not null,
                        name_taste varchar(255),
                        primary key (id)
);

create table users (
                       id serial4 not null,
                       birthday date not null,
                       email varchar(255) not null,
                       first_name varchar(255) not null,
                       last_name varchar(255) not null,
                       password varchar(255) not null,
                       phone varchar(255) not null,
                       registration_date timestamp,
                       address_id int8,
                       cart_id int8,
                       primary key (id)
);

create table users_roles (
                             user_id int8 not null,
                             role_id int8 not null,
                             primary key (user_id, role_id)
);

create table wine_images (
                             id serial4 not null,
                             image bytea not null,
                             name varchar(255),
                             type varchar(255),
                             url_path varchar(255),
                             wine_id int8 not null,
                             primary key (id)
);

create table wines (
                       id serial4 not null,
                       brand varchar(255),
                       made_in_country varchar(255),
                       in_stock boolean,
                       price numeric(19, 2),
                       title varchar(255),
                       capacity float8,
                       description varchar(255),
                       name varchar(255),
                       wine_type varchar(255),
                       event_id int8,
                       meal_id int8,
                       wine_style_id int8,
                       wine_taste_id int8,
                       primary key (id)
);
