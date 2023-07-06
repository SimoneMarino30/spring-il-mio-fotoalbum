<!-- PHOTOS
INSERT INTO photos (title, description, url_pic, visible) VALUES('Margherita', 'An irresistible symphony of colors, shapes, and aromas harmoniously blend on the crispy round surface. A culinary masterpiece that captivates both palate and heart.', 'https://res.cloudinary.com/glovoapp/w_600,f_auto,q_auto:low/Products/dzkieoorh0hh2ktj8ob5', true);
INSERT INTO photos (title, description, url_pic, visible) VALUES('Trentino', 'Embark on a culinary journey to Trentino with this enticing pizza: Agerola fior di latte, smoky speck, creamy buffalo ricotta, and delicate zucchini blossoms. A delightful blend of flavors inspired by the enchanting region of Trentino!', 'https://res.cloudinary.com/glovoapp/w_600,f_auto,q_auto:low/Products/mqg2xcbdupwnuhiw7a5l', true);
<!-- CATEGORIES
INSERT INTO categories (name) VALUES('Pizze');
<!-- TABELLA PONTE PHOTOS-CATEGORIES
INSERT INTO photo_category (photo_id, category_id) VALUES(1,1);
INSERT INTO photo_category (photo_id, category_id) VALUES(2,1);