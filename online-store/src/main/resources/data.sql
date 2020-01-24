INSERT INTO Authorities (authority, description)
	VALUES ('ROLE_ADMIN', 'Admin authority');
    
INSERT INTO Authorities (authority, description)
	VALUES ('ROLE_USER', 'User authority');

INSERT INTO Users (username, password, first_name, last_name, email)
	VALUES ('admin', 'password', 'John', 'Smith', 'adminmail@gmail.com');
    
INSERT INTO Users (username, password, first_name, last_name, email)
	VALUES ('user', 'password', 'test user', 'bob', 'usermail@gmail.com');
    
INSERT INTO User_has_authorities (users_id, authorities_id)
	VALUES ((SELECT id FROM Users WHERE username = 'admin'), (SELECT id FROM Authorities WHERE authority = 'ROLE_ADMIN'));
INSERT INTO User_has_authorities (users_id, authorities_id)
	VALUES ((SELECT id FROM Users WHERE username = 'admin'), (SELECT id FROM Authorities WHERE authority = 'ROLE_USER'));
    
INSERT INTO User_has_authorities (users_id, authorities_id)
	VALUES ((SELECT id FROM Users WHERE username = 'user'), (SELECT id FROM Authorities WHERE authority = 'ROLE_USER'));
    
