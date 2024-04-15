create table "user"(
    id uuid primary key,
    mail varchar(255) not null unique,
    username varchar(255) not null unique,
    password int not null,
    balance int not null,
    role varchar(20) not null,
    created_at timestamp with time zone default current_timestamp not null,
    modified_at timestamp with time zone default current_timestamp not null
);

create table "transaction"(
    id uuid primary key,
    user_id int references "user"(id) not null,
    value int not null,
    transaction_type varchar(50) not null,
    created_at timestamp with time zone default current_timestamp not null,
    modified_at timestamp with time zone default current_timestamp not null
);

create table jwt_tokens(
    id uuid primary key,
    user_id int references "user"(id) not null,
    token text not null,
    created_at timestamp with time zone default current_timestamp not null,
    modified_at timestamp with time zone default current_timestamp not null
)