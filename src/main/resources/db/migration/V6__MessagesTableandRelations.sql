CREATE TABLE chats_messages
(
    chat_id     UUID NOT NULL,
    messages_id UUID NOT NULL
);

ALTER TABLE chats_messages
    ADD CONSTRAINT uc_chats_messages_messages UNIQUE (messages_id);

ALTER TABLE chats_messages
    ADD CONSTRAINT fk_chames_on_chat FOREIGN KEY (chat_id) REFERENCES chats (id);

ALTER TABLE chats_messages
    ADD CONSTRAINT fk_chames_on_message FOREIGN KEY (messages_id) REFERENCES messages (id);