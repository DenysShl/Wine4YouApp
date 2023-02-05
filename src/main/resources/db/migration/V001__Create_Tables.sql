-- drop table if exists flyway_schema_history;
-- drop table if exists wines;
-- drop table if exists payments;
-- drop table if exists carts_items;
-- drop table if exists carts;
-- drop table if exists orders;
-- drop table if exists address;
-- drop table if exists events;
-- drop table if exists items;
-- drop table if exists meals;
-- drop table if exists roles;
-- drop table if exists styles;
-- drop table if exists tastes;
-- drop table if exists users;
-- drop table if exists users_roles;
-- drop table if exists wine_images;
-- drop table if exists orders_wines;
-- drop table if exists shopping_carts;
-- drop table if exists shopping_carts_products;

create table address (
                         id bigint not null,
                         apartment varchar(255),
                         city varchar(255),
                         street varchar(255),
                         primary key (id)
);

create table carts (
                       id  bigint not null,
                       buy_as_gift boolean,
                       created_date timestamp,
                       delivery_price float8,
                       discount int4,
                       is_called boolean,
                       payment_type varchar(255),
                       shipping varchar(255),
                       total_amount numeric(19, 2),
                       address_id bigint,
                       user_id bigint,
                       primary key (id)
);

create table carts_items (
                             cart_id bigint not null,
                             items_id bigint not null
);

create table events (
                        id bigint not null,
                        name_event varchar(255),
                        primary key (id)
);

create table items (
                       id bigint not null,
                       quantity int4,
                       total numeric(19, 2),
                       wine_id bigint,
                       primary key (id)
);

create table meals (
                       id bigint not null,
                       meal_name varchar(255),
                       primary key (id)
);

create table orders (
                        id bigint not null,
                        date_created timestamp,
                        order_status varchar(255),
                        order_tracking_number varchar(255),
                        cart_id int8,
                        payment_id int8,
                        user_id int8,
                        primary key (id)
);

create table payments (
                          id bigint not null,
                          card_name varchar(255),
                          card_number varchar(255),
                          payment_status varchar(255),
                          primary key (id)
);

create table roles (
                       id bigint not null,
                       role_name varchar(255),
                       primary key (id)
);

create table styles (
                        id bigint not null,
                        name_style varchar(255),
                        primary key (id)
);

create table tastes (
                        id bigint not null,
                        name_taste varchar(255),
                        primary key (id)
);

create table users (
                       id bigint not null,
                       birthday date not null,
                       email varchar(255) not null,
                       first_name varchar(255) not null,
                       last_name varchar(255) not null,
                       password varchar(255) not null,
                       phone varchar(255) not null,
                       registration_date timestamp,
                       address_id bigint,
                       cart_id bigint,
                       is_deleted boolean,
                       primary key (id)
);

create table users_roles (
                             user_id bigint not null,
                             role_id bigint not null,
                             primary key (user_id, role_id)
);

create table wine_images (
                             id bigint not null,
                             image bytea not null,
                             name varchar(255),
                             type varchar(255),
                             url_path varchar(255),
                             wine_id bigint not null,
                             primary key (id)
);

create table wines (
                       id bigint not null,
                       brand varchar(255),
                       made_in_country varchar(255),
                       in_stock boolean,
                       price numeric(19, 2),
                       title varchar(255),
                       capacity float8,
                       description varchar(255),
                       name varchar(255),
                       wine_type varchar(255),
                       event_id bigint,
                       meal_id bigint,
                       wine_style_id bigint,
                       wine_taste_id bigint,
                       primary key (id)
);
