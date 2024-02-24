-- Inserting Rights
INSERT INTO rights (id, name) VALUES (1, 'CREATE'), (2, 'READ'), (3, 'UPDATE'), (4, 'DELETE')

-- Inserting Roles
    INSERT INTO roles (id, name) VALUES (1, 'USER'), (2, 'ADMIN')

-- Inserting Role Rights
-- Assuming that 'CREATE', 'READ', 'UPDATE', 'DELETE' rights have IDs 1, 2, 3, 4 respectively
-- USER role has 'READ' right, and ADMIN role has all rights
INSERT INTO role_rights (role_id, right_id) VALUES (1, 1), (1, 2), (1, 3), (1, 4)
INSERT INTO role_rights (role_id, right_id) VALUES (2, 1), (2, 2), (2, 3), (2, 4)