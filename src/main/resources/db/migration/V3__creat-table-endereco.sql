create table endereco(

    id bigint not null auto_increment,
    tipo varchar(100) not null,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,
    pessoa_id bigint not null,

    primary key(id),
    constraint fk_endereco_pessoa_id foreign key(pessoa_id) references pessoa(id)
);