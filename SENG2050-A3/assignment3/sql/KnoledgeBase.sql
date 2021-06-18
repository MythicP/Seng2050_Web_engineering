CREATE TABLE KnowledgeBase (
	state VARCHAR(20),
	username VARCHAR(20),
	category VARCHAR(20),
	subcategory VARCHAR(20),
	dateReported DATETIME,
	dateResolved DATETIME,
	issueTitle VARCHAR(30),
	issueDescription VARCHAR(100),
	resolved BOOLEAN,
	issueID VARCHAR(6),
	);

INSERT INTO KnowledgeBase VALUES ('new', 'pete', 'Network', 'can\'t connect', '1992-05-12 13:28:04', '', 'Time', 'Help', FALSE, 'DGHR43');
