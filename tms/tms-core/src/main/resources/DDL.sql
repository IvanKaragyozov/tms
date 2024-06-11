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
    password              VARCHAR(255),
    phone_number          VARCHAR(15),
    username              VARCHAR(64),
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

CREATE TABLE projects
(
    id           BIGSERIAL,
    date_created DATE         NOT NULL,
    time_due     DATE,
    description  TEXT, -- Possible definition of limit?
    priority     VARCHAr(36),
    title        VARCHAR(128) NOT NULL,
    CONSTRAINT PK_projects_id PRIMARY KEY (id)
);

CREATE TABLE user_projects
(
    user_id    BIGSERIAL,
    project_id BIGSERIAL,
    CONSTRAINT PK_user_projects PRIMARY KEY (user_id, project_id),
    CONSTRAINT FK_user_projects_user_id FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT FK_user_projects_project_id FOREIGN KEY (project_id)
        REFERENCES projects (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE tasks
(
    id             BIGSERIAL,
    project_id     BIGINT,
    description    TEXT, -- Possible definition of limit?
    priority_level VARCHAR(36),
    status         VARCHAR(36),
    title          VARCHAR(128) NOT NULL,
    owner_id       BIGSERIAL,
    CONSTRAINT PK_tasks_id PRIMARY KEY (id),
    CONSTRAINT PK_tasks_owner_id FOREIGN KEY (owner_id)
        REFERENCES users (id),
    CONSTRAINT FK_tasks_project_id FOREIGN KEY (project_id)
        REFERENCES projects (id)
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

CREATE TABLE comments
(
    id          BIGSERIAL,
    task_id     BIGINT,
    user_id     BIGINT,
    time_posted TIMESTAMP(6) WITHOUT TIME ZONE,
    text        VARCHAR(255),
    CONSTRAINT PK_comments_id PRIMARY KEY (id),
    CONSTRAINT FK_comments_user_id FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT FK_comments_task_id FOREIGN KEY (task_id)
        REFERENCES tasks (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);