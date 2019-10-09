create table items
(
    item_id int auto_increment
        primary key,
    name    varchar(255)  not null,
    price   decimal(6, 2) not null
);

create table roles
(
    role_id   int auto_increment
        primary key,
    role_name varchar(45) not null
);

create table users
(
    user_id  int auto_increment
        primary key,
    name     varchar(45)   null,
    surname  varchar(45)   null,
    login    varchar(45)   not null,
    password varchar(1000) not null,
    token    varchar(45)   null,
    salt     blob          not null
);

create table buckets
(
    bucket_id int auto_increment
        primary key,
    user_id   int not null,
    constraint buckets_users_fk
        foreign key (user_id) references users (user_id)
);

create index buckets_users_fk_idx
    on buckets (user_id);

create table buckets_items
(
    buckets_items_id int auto_increment
        primary key,
    item_id          int not null,
    bucket_id        int not null,
    constraint buckets_items_buckets_fk
        foreign key (bucket_id) references buckets (bucket_id),
    constraint buckets_items_items_fk
        foreign key (item_id) references items (item_id)
);

create index buckets_items_buckets_fk_idx
    on buckets_items (bucket_id);

create index buckets_items_items_fk_idx
    on buckets_items (item_id);

create table orders
(
    order_id int auto_increment
        primary key,
    user_id  int not null,
    constraint orders_users_fk
        foreign key (user_id) references users (user_id)
);

create index orders_users_fk_idx
    on orders (user_id);

create table orders_items
(
    orders_items_id int auto_increment
        primary key,
    order_id        int not null,
    item_id         int not null,
    constraint orders_items_items_fk
        foreign key (item_id) references items (item_id),
    constraint orders_items_orders_fk
        foreign key (order_id) references orders (order_id)
);

create index orders_items_items_fk_idx
    on orders_items (item_id);

create index orders_items_orders_fk_idx
    on orders_items (order_id);

create table roles_users
(
    roles_users_id int auto_increment
        primary key,
    role_id        int not null,
    user_id        int not null,
    constraint roles_users_roles_fk
        foreign key (role_id) references roles (role_id),
    constraint roles_users_users_fk
        foreign key (user_id) references users (user_id)
);

create index roles_users_roles_fk_idx
    on roles_users (role_id);

create index roles_users_users_fk_idx
    on roles_users (user_id);

