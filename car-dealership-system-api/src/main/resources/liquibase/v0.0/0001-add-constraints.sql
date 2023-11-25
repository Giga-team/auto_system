-- changeset author:prokopchuk

ALTER TABLE orders
    ADD CONSTRAINT fk_orders_users FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE orders
    ADD CONSTRAINT fk_orders_cars FOREIGN KEY (car_id) REFERENCES cars (id),
    ADD CONSTRAINT un_cars UNIQUE (car_id);