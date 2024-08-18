create table if not exists orders (
  id_order bigint not null auto_increment,
  amount double not null,
  id_creation bigint,
  date_creation timestamp,
  id_update bigint,
  date_update timestamp,
  primary key (id_order)
);

create table if not exists orders_inventory (
  id_order_inventory bigint not null auto_increment,
  id_order bigint not null,
  id_inventory bigint not null,
  price double not null,
  id_creation bigint,
  date_creation timestamp,
  id_update bigint,
  date_update timestamp,
  primary key (id_order_inventory),
  foreign key (id_order) references orders (id_order),
  foreign key (id_inventory) references inventory (id_inventory)
);
