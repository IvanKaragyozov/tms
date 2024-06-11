CREATE TABLE rights
(
    id   BIGSERIAL,
    name VARCHAR(255) NOT NULL UNIQUE,

    CONSTRAINT PK_rights_id PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id   BIGSERIAL,
    name VARCHAR(255) NOT NULL UNIQUE,

    CONSTRAINT PK_roles_id PRIMARY KEY (id)
);

CREATE TABLE role_rights
(
    role_id  BIGSERIAL,
    right_id BIGSERIAL,
    CONSTRAINT PK_role_rights PRIMARY KEY (role_id, right_id),
    CONSTRAINT FK_role_rights_role_id FOREIGN KEY (role_id)
        REFERENCES roles (id),
    CONSTRAINT FK_role_rights_right_id FOREIGN KEY (right_id)
        REFERENCES rights (id)
);

CREATE TABLE users
(
    id                    BIGSERIAL,
    date_created_at       DATE         NOT NULL,
    date_last_modified_at DATE,
    is_active             BOOLEAN,
    email                 VARCHAR(128) NOT NULL,
    first_name            VARCHAR(128),
    last_name             VARCHAR(128),
    password              VARCHAR(255) NOT NULL,
    phone_number          VARCHAR(15),
    username              VARCHAR(64)  NOT NULL,
    CONSTRAINT PK_users_id PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    user_id BIGSERIAL,
    role_id BIGSERIAL,
    CONSTRAINT PK_user_roles PRIMARY KEY (user_id, role_id),
    CONSTRAINT FK_user_roles_user_id FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT FK_user_roles_role_id FOREIGN KEY (role_id)
        REFERENCES roles (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE tasks
(
    id             BIGSERIAL,
    description    TEXT, -- Possible definition of limit?
    priority_level VARCHAR(36),
    status         VARCHAR(36),
    title          VARCHAR(128) NOT NULL,
    owner_id       BIGSERIAL,
    CONSTRAINT PK_tasks_id PRIMARY KEY (id),
    CONSTRAINT PK_tasks_owner_id FOREIGN KEY (owner_id)
        REFERENCES users (id)
);

CREATE TABLE user_tasks
(
    user_id BIGSERIAL,
    task_id BIGSERIAL,
    CONSTRAINT FK_user_tasks_user_id FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT FK_user_tasks_task_id FOREIGN KEY (task_id)
        REFERENCES tasks (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
