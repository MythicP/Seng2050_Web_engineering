CREATE TABLE Comments (
	commentInfo VARCHAR(50),
	username VARCHAR(20),
	commentDate DATETIME,
	solution BOOLEAN,
	);

INSERT INTO Comments VALUES ('This is a comment', 'pete', '1992-05-12 13:28:04', FALSE);