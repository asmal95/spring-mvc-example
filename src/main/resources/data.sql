INSERT INTO ROLE (ID, NAME , AUTHORITY) VALUES
  (1, 'Users', 'ROLE_USER'),
  (2, 'Administrators', 'ROLE_ADMIN');
INSERT INTO USERS (ID, USERNAME, PASSWORD) VALUES
  (1, 'admin', '$2a$10$9eOIaFtzFIHJ69WslgrjieXUyPQz6F6T6SEp5vR0ZdkUHhw0mFoHK');

INSERT INTO ROLE_ASSIGNMENTS (USER_ID, ROLE_ID) VALUES
  (1, 1),
  (1, 2);
