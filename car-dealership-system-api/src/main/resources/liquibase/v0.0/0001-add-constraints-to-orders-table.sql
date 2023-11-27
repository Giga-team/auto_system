-- changeset author:prokopchuk

ALTER TABLE orders
    DROP CONSTRAINT IF EXISTS fk_orders_users,
    ADD CONSTRAINT fk_orders_users FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE orders
    DROP CONSTRAINT IF EXISTS fk_orders_cars,
    ADD CONSTRAINT fk_orders_cars FOREIGN KEY (car_id) REFERENCES cars (id),
    DROP CONSTRAINT IF EXISTS un_cars,
    ADD CONSTRAINT un_cars UNIQUE (car_id);