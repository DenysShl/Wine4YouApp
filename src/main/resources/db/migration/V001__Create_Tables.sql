create table address
(
    id serial not null,
    apartment varchar(255),
    city      varchar(255),
    street    varchar(255),
    primary key (id)
);

create table carts
(
    id serial not null,
    buy_as_gift    boolean,
    created_date   timestamp,
    delivery_price float8,
    discount       int4,
    is_called      boolean,
    payment_type   varchar(255),
    shipping       varchar(255),
    total_amount   numeric(19, 2),
    address_id     int8,
    user_id        int8,
    primary key (id)
);

create table carts_items
(
    cart_id  int8 not null,
    items_id int8 not null
);

create table events
(
    id serial not null,
    name_event varchar(255),
    primary key (id)
);

create table items
(
    id serial not null,
    quantity int4,
    total    numeric(19, 2),
    wine_id  int8,
    primary key (id)
);

create table meals
(
    id serial not null,
    meal_name varchar(255),
    primary key (id)
);

create table orders
(
    id serial not null,
    date_created          timestamp,
    order_status          varchar(255),
    order_tracking_number varchar(255),
    cart_id               int8,
    payment_id            int8,
    user_id               int8,
    primary key (id)
);

create table payments
(
    id serial not null,
    card_name      varchar(255),
    card_number    varchar(255),
    payment_status varchar(255),
    primary key (id)
);

create table roles
(
    id serial not null,
    role_name varchar(255),
    primary key (id)
);

create table styles
(
    id serial not null,
    name_style varchar(255),
    primary key (id)
);

create table tastes
(
    id serial not null,
    name_taste varchar(255),
    primary key (id)
);

create table users
(
    id serial not null,
    birthday          date         not null,
    email             varchar(255) not null,
    first_name        varchar(255) not null,
    last_name         varchar(255) not null,
    password          varchar(255) not null,
    phone             varchar(255) not null,
    registration_date timestamp,
    address_id        int8,
    cart_id           int8,
    is_deleted        boolean,
    primary key (id)
);

create table users_roles
(
    user_id int8 not null,
    role_id int8 not null,
    primary key (user_id, role_id)
);

create table wine_images
(
    id serial not null,
    image bytea not null,
    name     varchar(255),
    type     varchar(255),
    url_path varchar(255),
    wine_id  int8 not null,
    primary key (id)
);

create table wines
(
    id serial not null,
    brand           varchar(255),
    made_in_country varchar(255),
    in_stock        boolean,
    price           numeric(19, 2),
    title           varchar(255),
    capacity        float8,
    description     varchar(255),
    name            varchar(255),
    wine_type       varchar(255),
    event_id        int8,
    meal_id         int8,
    wine_style_id   int8,
    wine_taste_id   int8,
    primary key (id)
);
