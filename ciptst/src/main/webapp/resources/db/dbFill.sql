INSERT INTO person (id, name, email,  vk) VALUES
  ((SELECT nextval('global_seq')), 'Kolya',    'kolya@admin.com',          '175685'),
  ((SELECT nextval('global_seq')), 'Dima',     'dimon@manager.com',        '26129350'),
  ((SELECT nextval('global_seq')), 'Igor',     'igor@senior.ru',           '34179400'),
  ((SELECT nextval('global_seq')), 'Chingiz',  'chingishan@middle.ru',     ''),
  ((SELECT nextval('global_seq')), 'Radzhesh', 'radik@junior.ru',          '1777574');


INSERT INTO phone (phone, personid) VALUES
  ('121',   1),
  ('1233',  2),
  ('2323',  3),
  ('23425', 4),
  ('23344', 5),
  ('2345', 5);