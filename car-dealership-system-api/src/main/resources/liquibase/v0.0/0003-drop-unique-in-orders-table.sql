-- changeset author:prokopchuk

ALTER TABLE orders
    DROP CONSTRAINT IF EXISTS un_cars;