CREATE TABLE outbox_message (
    id SERIAL,
    type varchar(100) NOT NULL,
    json_content varchar(512) NOT NULL,
    primary key (id)
);
﻿
CREATE TABLE account (
    id varchar(36),
    name varchar(50),
	email varchar(100),
    primary key (id)
);