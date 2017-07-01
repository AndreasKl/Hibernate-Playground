
    create table listing (
       id binary not null,
        article_id varchar(255),
        store_id varchar(255),
        version bigint not null,
        primary key (id)
    );

    create table stock (
       article_id varchar(255) not null,
        store_id varchar(255) not null,
        value varchar(255),
        primary key (article_id, store_id)
    );

