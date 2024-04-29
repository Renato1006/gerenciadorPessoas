create table pessoa(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    data_nascimento date not null,

    primary key(id)

);