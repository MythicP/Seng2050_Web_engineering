CREATE TABLE Accounts (
	username VARCHAR(20),
	firstName VARCHAR(20),
	lastName VARCHAR(20),
	password VARCHAR(20),
	contactNum VARCHAR(20),
	email VARCHAR(40),
	isSatff BOOLEAN,
	);

INSERT INTO Accounts VALUES ('mike', 'Michael', 'Price', 'password', '0347259873', 'me@gmail', TRUE),
							('tom', 'Thomas', 'Miller', 'password1', '4859285483', 'edgyman@hotmail', TRUE),
							('jakeb', 'Jakeb', 'Pont', 'qwe', '8484848484', 'noobmaster69@yahoo', TRUE),
							('pete', 'Peter', 'Parker', 'pass1', '48484848', 'spiderDude@gmail', FALSE),
							('harry', 'Harry', 'Potter', 'pass2', '00000001', 'wizARdDude@hogworts', FALSE),
							('john', 'John', 'Snow', 'pass3', '33849825', 'youaremyqueen@got', FALSE);