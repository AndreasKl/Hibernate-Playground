
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

    create table versioned (
       id varchar(255) not null,
        created timestamp,
        name varchar(255),
        updated timestamp,
        version bigint not null,
        primary key (id)
    );

    create table versioned_entry (
       id varchar(255) not null,
        name varchar(255),
        versioned_id varchar(255),
        primary key (id)
    );

    alter table versioned_entry 
       add constraint FKp7nsjtf9e84cwcd24635gjt0d 
       foreign key (versioned_id) 
       references versioned;
