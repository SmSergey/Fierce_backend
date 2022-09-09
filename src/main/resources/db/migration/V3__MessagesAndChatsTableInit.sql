CREATE TABLE chats
(
    id UUID NOT NULL,
    CONSTRAINT pk_chats PRIMARY KEY (id)
);

CREATE TABLE chats_users
(
    chat_id  UUID   NOT NULL,
    users_id BIGINT NOT NULL
);

CREATE TABLE messages
(
    id      UUID NOT NULL,
    user_id BIGINT,
    data    VARCHAR(255),
    chat_id UUID,
    CONSTRAINT pk_messages PRIMARY KEY (id)
);

ALTER TABLE app_user
    ADD CONSTRAINT uc_app_user_email UNIQUE (email);

ALTER TABLE chats_users
    ADD CONSTRAINT uc_chats_users_users UNIQUE (users_id);

ALTER TABLE messages
    ADD CONSTRAINT FK_MESSAGES_ON_CHAT FOREIGN KEY (chat_id) REFERENCES chats (id);

ALTER TABLE messages
    ADD CONSTRAINT FK_MESSAGES_ON_USER FOREIGN KEY (user_id) REFERENCES app_user (id);

ALTER TABLE chats_users
    ADD CONSTRAINT fk_chause_on_chat FOREIGN KEY (chat_id) REFERENCES chats (id);

ALTER TABLE chats_users
    ADD CONSTRAINT fk_chause_on_user FOREIGN KEY (users_id) REFERENCES app_user (id);