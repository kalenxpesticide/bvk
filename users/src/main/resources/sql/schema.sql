create table if not exists oauth_client_details (
  client_id varchar(255) not null,
  client_secret varchar(255) not null,
  web_server_redirect_uri varchar(2048),
  scope varchar(255),
  access_token_validity int,
  refresh_token_validity int,
  resource_ids varchar(1024),
  authorized_grant_types varchar(1024),
  authorities varchar(1024),
  additional_information varchar(4096),
  autoapprove varchar(255),
  primary key (client_id)
);

create table if not exists permission (
  id int not null auto_increment,
  name varchar(512),
  primary key (id),
  unique (name)
);

create table if not exists role (
  id int not null auto_increment,
  name varchar(255),
  primary key (id),
  unique (name)
);

create table if not exists user (
  id int not null auto_increment,
  username varchar(100) not null,
  password varchar(1024) not null,
  email varchar(1024) not null,
  enabled boolean not null,
  accountNonExpired boolean not null,
  credentialsNonExpired boolean not null,
  accountNonLocked boolean not null,
  primary key (id),
  unique (username)
);

create table if not exists permission_role (
  permission_id int,
  role_id int,
  foreign key (permission_id) references permission (id),
  foreign key (role_id) references role (id)
);

create table if not exists role_user (
  role_id int,
  user_id int,
  foreign key (role_id) references role (id),
  foreign key (user_id) references user (id)
);

create table if not exists oauth_client_token (
  token_id varchar(256),
  token blob,
  authentication_id varchar(256) primary key,
  user_name varchar(256),
  client_id varchar(256)
);

create table if not exists oauth_access_token (
  token_id varchar(256),
  token blob,
  authentication_id varchar(256) primary key,
  user_name varchar(256),
  client_id varchar(256),
  authentication blob,
  refresh_token varchar(256)
);

create table if not exists oauth_refresh_token (
  token_id varchar(256),
  token blob,
  authentication blob
);

create table if not exists oauth_code (
  code varchar(256),
  authentication blob
);

create table if not exists oauth_approvals (
  userId varchar(256),
  clientId varchar(256),
  scope varchar(256),
  status varchar(10),
  expiresAt timestamp
);
