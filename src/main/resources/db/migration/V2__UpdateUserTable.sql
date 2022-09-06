ALTER TABLE app_user
    ADD email VARCHAR(255);

ALTER TABLE app_user
    ADD is_activated BOOLEAN;

ALTER TABLE app_user
    ADD verification_code VARCHAR(255);