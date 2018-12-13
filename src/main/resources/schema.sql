create table if not exists Url (
  urlId  varchar(100) null primary key ,
  longUrl varchar(100) not null,
  uniqueKey varchar(100) not null
);
