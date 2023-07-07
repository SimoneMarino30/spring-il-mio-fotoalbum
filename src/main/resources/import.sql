<!-- PHOTOS
INSERT INTO photos (title, description, url_pic, visible, created_at) VALUES('Margherita', 'An irresistible symphony of colors, shapes, and aromas harmoniously blend on the crispy round surface. A culinary masterpiece that captivates both palate and heart.', 'https://res.cloudinary.com/glovoapp/w_600,f_auto,q_auto:low/Products/dzkieoorh0hh2ktj8ob5', true, '2023-06-22 12:14');
INSERT INTO photos (title, description, url_pic, visible, created_at) VALUES('Trentino', 'Embark on a culinary journey to Trentino with this enticing pizza: Agerola fior di latte, smoky speck, creamy buffalo ricotta, and delicate zucchini blossoms. A delightful blend of flavors inspired by the enchanting region of Trentino!', 'https://res.cloudinary.com/glovoapp/w_600,f_auto,q_auto:low/Products/mqg2xcbdupwnuhiw7a5l', true, '2023-07-06 15:14');
<!-- CATEGORIES
INSERT INTO categories (name) VALUES('Pizze');
INSERT INTO categories (name) VALUES('Paesaggi');
<!-- TABELLA PONTE PHOTOS-CATEGORIES
INSERT INTO photo_category (photo_id, category_id) VALUES(1,1);
INSERT INTO photo_category (photo_id, category_id) VALUES(2,1);
INSERT INTO photo_category (photo_id, category_id) VALUES(2,2);
<!-- SECURITY
INSERT INTO roles (id, name) VALUES(1,'ADMIN');
INSERT INTO roles (id, name) VALUES(2,'USER');
INSERT INTO users (id, email, first_name, last_name, password) VALUES(1, 'simo@email.com', 'Simo', 'Simoni', '{noop}password');
INSERT INTO users (id, email, first_name, last_name, password) VALUES(2, 'marino@email.com', 'Marino', 'Marini', '{noop}marini');
INSERT INTO users_roles (roles_id, user_id) VALUES(1, 1);
INSERT INTO users_roles (roles_id, user_id) VALUES(2, 2);