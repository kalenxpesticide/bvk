create table if not exists inventory (
  id_inventory bigint not null auto_increment,
  name varchar(200) not null,
  price double not null default 0,
  quantity int not null,
  id_creation bigint,
  date_creation timestamp,
  id_update bigint,
  date_update timestamp,
  primary key (id_inventory)
);