-- changeset author:prokopchuk

CREATE OR REPLACE VIEW vw_orders AS
    SELECT
        o.*, u.name, u.surname, u.email, u.phone_number
    FROM
        orders o
    INNER JOIN users u
        ON u.id = o.user_id;