CREATE TABLE app_user_chats
(
    user_id  BIGINT NOT NULL,
    chats_id UUID   NOT NULL
);

ALTER TABLE app_user_chats
    ADD CONSTRAINT uc_app_user_chats_chats UNIQUE (chats_id);

ALTER TABLE app_user_chats
    ADD CONSTRAINT fk_appusecha_on_chat FOREIGN KEY (chats_id) REFERENCES chats (id);

ALTER TABLE app_user_chats
    ADD CONSTRAINT fk_appusecha_on_user FOREIGN KEY (user_id) REFERENCES app_user (id);