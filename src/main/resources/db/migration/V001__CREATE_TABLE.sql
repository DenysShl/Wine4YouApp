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

create table events (
                        id bigint not null auto_increment,
                        name_event varchar(255),
                        primary key (id)
);

create table orders (
                        id bigint not null auto_increment,
                        order_time datetime(6),
                        user_id bigint,
                        primary key (id)
);

create table orders_wines (
                              order_id bigint not null,
                              wine_id bigint not null
);
create table roles (
                       id bigint not null auto_increment,
                       role_name varchar(255),
                       primary key (id)
);
create table shopping_carts (
                                id bigint not null,
                                primary key (id)
);
create table shopping_carts_products (
                                         shopping_cart_id bigint not null,
                                         product_id bigint not null
);
create table styles (
                        id bigint not null auto_increment,
                        name_style varchar(255),
                        primary key (id)
);
create table tastes (
                        id bigint not null auto_increment,
                        name_taste varchar(255),
                        primary key (id)
);
create table users (
                       id bigint not null auto_increment,
                       address varchar(255) not null,
                       birthday date not null,
                       city varchar(255) not null,
                       email varchar(255) not null,
                       first_name varchar(255) not null,
                       last_name varchar(255) not null,
                       password varchar(255) not null,
                       phone varchar(255) not null,
                       registration_date datetime(6),
                       shopping_cart_id bigint,
                       primary key (id)
);
create table users_roles (
                             user_id bigint not null,
                             role_id bigint not null,
                             primary key (user_id, role_id)
);
create table wine_images (
                             id bigint not null auto_increment,
                             image longblob,
                             name varchar(255),
                             type varchar(255),
                             wine_id bigint not null,
                             primary key (id)
);
create table wines (
                       id bigint not null auto_increment,
                       brand varchar(255),
                       made_in_country varchar(255),
                       in_stock bit,
                       price decimal(19,2),
                       title varchar(255),
                       capacity double precision,
                       description varchar(255),
                       name varchar(255),
                       wine_type varchar(255),
                       event_id bigint,
                       wine_style_id bigint,
                       wine_taste_id bigint,
                       primary key (id)
);

alter table orders_wines
    add constraint UK_9e99ew6dhol8r1xkxd3k0hncp unique (wine_id);

alter table roles
    add constraint UK_716hgxp60ym1lifrdgp67xt5k unique (role_name);

alter table shopping_carts_products
    add constraint UK_9m3ymtfvxtxcb1ukw8lse9t5h unique (product_id);

alter table users
    add constraint UK1drr8bhslhiv9m1s9inur8vff unique (phone, email);

alter table orders
    add constraint FK32ql8ubntj5uh44ph9659tiih
        foreign key (user_id)
            references users (id);

alter table orders_wines
    add constraint FKfb3gfhxg6g4ydjr80y2foqrsa
        foreign key (wine_id)
            references wines (id);

alter table orders_wines
    add constraint FKlkjjmij0q9nurt1a1rrcm9yf5
        foreign key (order_id)
            references orders (id);

alter table shopping_carts
    add constraint FKc1fbrvff059ke4p8ce3hu38oa
        foreign key (id)
            references users (id);

alter table shopping_carts_products
    add constraint FKlqquvf61c1r7bme4e558a0q9
        foreign key (product_id)
            references orders (id);

alter table shopping_carts_products
    add constraint FK8ofq6ppk9gndq09rgw5ukp618
        foreign key (shopping_cart_id)
            references shopping_carts (id);

alter table users
    add constraint FKpit3woesw8x1062syim3kei69
        foreign key (shopping_cart_id)
            references shopping_carts (id);

alter table users_roles
    add constraint FKj6m8fwv7oqv74fcehir1a9ffy
        foreign key (role_id)
            references roles (id);

alter table users_roles
    add constraint FK2o0jvgh89lemvvo17cbqvdxaa
        foreign key (user_id)
            references users (id);

alter table wine_images
    add constraint FKdpapwy563s2cf4td49ttyn3lg
        foreign key (wine_id)
            references wines (id);

alter table wines
    add constraint FK95ndewxh6j4t20rcoa2xcpwyy
        foreign key (event_id)
            references events (id);

alter table wines
    add constraint FK3x5ee3me3hblcpeeuofiss2py
        foreign key (wine_style_id)
            references styles (id);

alter table wines
    add constraint FKkhyec7v6xw334awt2ged8nf27
        foreign key (wine_taste_id)
            references tastes (id);