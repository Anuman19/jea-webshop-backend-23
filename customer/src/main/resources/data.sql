-- Rollen Tabelle beladen
INSERT INTO public.roles (role_id, name)
VALUES (1, 'ADMIN') ON CONFLICT DO NOTHING;
INSERT INTO public.roles (role_id, name)
VALUES (2, 'CUSTOMER') ON CONFLICT DO NOTHING;

-- Kategorie Tabelle beladen
INSERT INTO public.categories(category_id, is_activated, name)
VALUES (1, true, 'Plant') ON CONFLICT DO NOTHING;
INSERT INTO public.categories(category_id, is_activated, name)
VALUES (2, true, 'Fire') ON CONFLICT DO NOTHING;
INSERT INTO public.categories(category_id, is_activated, name)
VALUES (3, true, 'Water') ON CONFLICT DO NOTHING;
INSERT INTO public.categories(category_id, is_activated, name)
VALUES (4, true, 'Bug') ON CONFLICT DO NOTHING;
INSERT INTO public.categories(category_id, is_activated, name)
VALUES (5, true, 'Flying') ON CONFLICT DO NOTHING;
INSERT INTO public.categories(category_id, is_activated, name)
VALUES (6, true, 'Normal') ON CONFLICT DO NOTHING;

-- Produkte Tabelle beladen
INSERT INTO public.products (product_id, current_quantity, description, image, is_activated, name, price, star_rating,
                             category_id)
VALUES (1, 13, 'This is a description',
        'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png', true,
        'Bulbasaur', 20.0, 3, 1) ON CONFLICT DO NOTHING;

INSERT INTO public.products (product_id, current_quantity, description, image, is_activated, name, price, star_rating,
                             category_id)
VALUES (2, 30, 'This is a description',
        'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png', true,
        'Ivysaur', 10.0, 2, 1) ON CONFLICT DO NOTHING;

INSERT INTO public.products (product_id, current_quantity, description, image, is_activated, name, price, star_rating,
                             category_id)
VALUES (3, 1, 'This is a description',
        'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png', true,
        'Venusaur', 8.0, 2, 1) ON CONFLICT DO NOTHING;

INSERT INTO public.products (product_id, current_quantity, description, image, is_activated, name, price, star_rating,
                             category_id)
VALUES (4, 55, 'This is a description',
        'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png', true,
        'Charmander', 13.0, 3, 2) ON CONFLICT DO NOTHING;

-- Admin Tabelle beladen
INSERT INTO public.admins (email, first_name, last_name, password, username)
VALUES ('hans.mustermann@testMail.com', 'Hans', 'Mustermann', '1234', 'HansM') ON CONFLICT DO NOTHING;
INSERT INTO public.admins (email, first_name, last_name, password, username)
VALUES ('hannah.herzfrau@testMail.com', 'Hannah', 'Herzfau', '1234', 'HannahH') ON CONFLICT DO NOTHING;

-- Stadt Tabelle beladen
INSERT INTO public.cities (city_id, name)
VALUES (1, 'Zurich') ON CONFLICT DO NOTHING;
INSERT INTO public.cities (city_id, name)
VALUES (2, 'Bern') ON CONFLICT DO NOTHING;
INSERT INTO public.cities (city_id, name)
VALUES (3, 'Geneva') ON CONFLICT DO NOTHING;
INSERT INTO public.cities (city_id, name)
VALUES (4, 'London') ON CONFLICT DO NOTHING;

-- Kunden Tabelle beladen
INSERT INTO public.customers (customer_id, address, country, email, first_name, last_name, password, phone_number,
                              username, city_id)
VALUES (1, 'Hausstrasse 2', 'CH', 'colleen.smith@testMail.com', 'Colleen', 'Smith', '1234', '+41 78 433 25 25',
        'CollenS', 1) ON CONFLICT DO NOTHING;

INSERT INTO public.customers (customer_id, address, country, email, first_name, last_name, password, phone_number,
                              username, city_id)
VALUES (2, 'Penn Street 20', 'GB', 'harold.payne@testMail.com', 'Harold', 'Payne', '1234', '+41 79 433 25 25',
        'HaroldP', 4) ON CONFLICT DO NOTHING;

INSERT INTO public.customers (customer_id, address, country, email, first_name, last_name, password, phone_number,
                              username, city_id)
VALUES (3, 'Hausweg 5', 'CH', 'cindy.evans@testMail.com', 'Cindy', 'Evans', '1234', '+41 76 433 25 25', 'CindyE',
        2) ON CONFLICT DO NOTHING;