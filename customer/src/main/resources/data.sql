-- Rollen Tabelle beladen
INSERT INTO public.roles (role_id, name) VALUES (1, 'ADMIN');
INSERT INTO public.roles (role_id, name) VALUES (2, 'CUSTOMER');

-- Kategorie Tabelle beladen
INSERT INTO public.categories(category_id, is_activated, name) VALUES (1, true, 'Plant');
INSERT INTO public.categories(category_id, is_activated, name) VALUES (2, true, 'Fire');
INSERT INTO public.categories(category_id, is_activated, name) VALUES (3, true, 'Water');
INSERT INTO public.categories(category_id, is_activated, name) VALUES (4, true, 'Bug');
INSERT INTO public.categories(category_id, is_activated, name) VALUES (5, true, 'Flying');
INSERT INTO public.categories(category_id, is_activated, name) VALUES (6, true, 'Normal');

-- Produkte Tabelle beladen
INSERT INTO public.products (product_id, current_quantity, description, image, is_activated, name, price, star_rating, category_id)
VALUES (1, 13, 'This is a description', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png', true, 'Bulbasaur', 20.0, 3, 1);

INSERT INTO public.products (product_id, current_quantity, description, image, is_activated, name, price, star_rating, category_id)
VALUES (2, 30, 'This is a description', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png', true, 'Ivysaur', 10.0, 2, 1);

INSERT INTO public.products (product_id, current_quantity, description, image, is_activated, name, price, star_rating, category_id)
VALUES (3, 1, 'This is a description', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png', true, 'Venusaur', 8.0, 2, 1);

INSERT INTO public.products (product_id, current_quantity, description, image, is_activated, name, price, star_rating, category_id)
VALUES (4, 55, 'This is a description', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png', true, 'Charmander', 13.0, 3, 2);

-- Admin Tabelle beladen
INSERT INTO public.admins (admin_id, email, first_name, last_name, password, username)
VALUES (1, 'hans.mustermann@testMail.com', 'Hans', 'Mustermann', '1234', 'HansM');
INSERT INTO public.admins (admin_id, email, first_name, last_name, password, username)
VALUES (2, 'hannah.herzfrau@testMail.com', 'Hannah', 'Herzfau', '1234', 'HannahH');

-- Stadt Tabelle beladen
INSERT INTO public.cities (city_id, name) VALUES (1, 'Zurich');
INSERT INTO public.cities (city_id, name) VALUES (2, 'Bern');
INSERT INTO public.cities (city_id, name) VALUES (3, 'Geneva');
INSERT INTO public.cities (city_id, name) VALUES (4, 'London');

-- Kunden Tabelle beladen
INSERT INTO public.customers (customer_id, address, country, email, first_name, last_name, password, phone_number, username, city_id)
VALUES (1, 'Hausstrasse 2', 'CH', 'colleen.smith@testMail.com', 'Colleen', 'Smith', '1234', '+41 78 433 25 25', 'CollenS', 1);

INSERT INTO public.customers (customer_id, address, country, email, first_name, last_name, password, phone_number,username, city_id)
VALUES (2, 'Penn Street 20', 'GB', 'harold.payne@testMail.com', 'Harold', 'Payne', '1234', '+41 79 433 25 25', 'HaroldP', 4);

INSERT INTO public.customers (customer_id, address, country, email, first_name, last_name, password, phone_number, username, city_id)
VALUES (3, 'Hausweg 5', 'CH', 'cindy.evans@testMail.com', 'Cindy', 'Evans', '1234', '+41 76 433 25 25', 'CindyE', 2);