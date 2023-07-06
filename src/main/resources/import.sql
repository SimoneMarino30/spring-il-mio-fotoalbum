<!-- PHOTOS
INSERT INTO photos (title, description, url_pic, visible) VALUES('margherita', 'pizza margherita', 'https://res.cloudinary.com/glovoapp/w_600,f_auto,q_auto:low/Products/dzkieoorh0hh2ktj8ob5', true);
<!-- CATEGORIES
INSERT INTO categories (name) VALUES('Pizze');
<!-- TABELLA PONTE PHOTOS-CATEGORIES
INSERT INTO photo_category (photo_id, category_id) VALUES(1,1);