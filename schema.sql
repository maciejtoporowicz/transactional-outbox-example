CREATE TABLE outbox_message (
    id SERIAL PRIMARY KEY,
    type varchar(100) NOT NULL,
    content varchar(512) NOT NULL,
);
﻿
CREATE TABLE account (
    id varchar(36) PRIMARY KEY,
    name varchar(50)
);