CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE category
(
	id   UUID DEFAULT gen_random_uuid(),
	name VARCHAR(50) NOT NULL,
	CONSTRAINT pk_category_key PRIMARY KEY (id)
);

CREATE TABLE expense
(
	id          UUID        NOT NULL DEFAULT gen_random_uuid(),
	description VARCHAR(50) NOT NULL,
	created_at  TIMESTAMP   NOT NULL DEFAULT NOW(),
	category_id UUID        NOT NULL,
	amount      DECIMAL     NOT NULL,
	CONSTRAINT pk_expense_key PRIMARY KEY (id),
	CONSTRAINT fk_expense_key FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE
) PARTITION BY HASH (id);

CREATE TABLE expense_h0 PARTITION OF expense FOR VALUES WITH (modulus 3, remainder 0);
CREATE TABLE expense_h1 PARTITION OF expense FOR VALUES WITH (modulus 3, remainder 1);
CREATE TABLE expense_h2 PARTITION OF expense FOR VALUES WITH (modulus 3, remainder 2);

/*
 filling tables with values
 */

INSERT INTO category(id, name)
VALUES ('82ff2531-4fa7-4d62-a81b-505eed792b1a', 'food'),
			 ('a25b46e9-647d-4241-8159-f5bb6ccbee9f', 'education'),
			 ('9c65c8bb-6ea6-48da-ab36-070eec4e66e1', 'gifts'),
			 ('834f2d74-7a9d-44f3-8ae5-92ef460712f3', 'entertainment'),
			 ('b5352711-8c69-40c9-bd64-71c9b564d355', 'home'),
			 ('2c8f40f0-ee9c-4123-8fc0-a3fafc3690ab', 'medications'),
			 ('4e902cc9-4252-4df2-bbcf-8d35df003a7c', 'other');

INSERT INTO expense(description, created_at, category_id, amount)
VALUES ('null', '2021-11-11 19:27:26.860428', '2c8f40f0-ee9c-4123-8fc0-a3fafc3690ab', 0);

INSERT INTO expense(description, category_id, amount)
VALUES ('Some fruits..', '82ff2531-4fa7-4d62-a81b-505eed792b1a', 20.15),
			 ('monday launch', '82ff2531-4fa7-4d62-a81b-505eed792b1a', 7.25),
			 ('pizza delivery', '82ff2531-4fa7-4d62-a81b-505eed792b1a', 17.99),
			 ('Spring course on Udemy', 'a25b46e9-647d-4241-8159-f5bb6ccbee9f', 9.99),
			 ('book Hibernate in action', 'a25b46e9-647d-4241-8159-f5bb6ccbee9f', 24.99),
			 ('monthly english courses', 'a25b46e9-647d-4241-8159-f5bb6ccbee9f', 99.99),
			 ('apple for girlfriends birthday', '9c65c8bb-6ea6-48da-ab36-070eec4e66e1', 600),
			 ('flowers to mother', '9c65c8bb-6ea6-48da-ab36-070eec4e66e1', 7.99),
			 ('new year gifts for closes friends', '9c65c8bb-6ea6-48da-ab36-070eec4e66e1', 59.55),
			 ('cinema', '834f2d74-7a9d-44f3-8ae5-92ef460712f3', 10.50),
			 ('table in kitchen', 'b5352711-8c69-40c9-bd64-71c9b564d355', 99.99),
			 ('some furniture', 'b5352711-8c69-40c9-bd64-71c9b564d355', 150.10),
			 ('pfizer', '2c8f40f0-ee9c-4123-8fc0-a3fafc3690ab', 10.99),
			 ('sputnik-V', '2c8f40f0-ee9c-4123-8fc0-a3fafc3690ab', 50.99),
			 ('secret good', '2c8f40f0-ee9c-4123-8fc0-a3fafc3690ab', 54.10),
			 ('have yóu really been gót this sentence? ;)', '2c8f40f0-ee9c-4123-8fc0-a3fafc3690ab', 999999.99)



