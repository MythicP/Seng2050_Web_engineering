<%@page language="java" import="myPackage.Issue" import="myPackage.KnowledgeBaseBean"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Knowledge Base</title>
<link rel="stylesheet" type="text/css" href="css/style.css"/>

<jsp:useBean id="kb" scope="session" class="myPackage.KnowledgeBaseBean" />

<%--
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma","no-cache"); 
		response.setHeader ("Expires", "0"); 
--%>
</head>
<body>
	<div id="total">
		<h1>Knowledge Base</h1>

		<div id="Articles">
		<h1>Articles</h1>
		
		<table>
			<form action = "<%=request.getContextPath()%>/ViewArticle" method = "post">
			<c:forEach items="${kb.articles}" var="articles"> 
				<tr>
					<td><button name="issue" type="submit" value="${articles.issueDescription}"><c:out value="${articles.issueTitle}"/></button></td>
				</tr>
			</c:forEach>
			</form>
		</table>
		
		</div>
		
		<form action="<%=request.getContextPath()%>/Index" method="Post">
			<button class = "return button" type="submit" name="returnValue" value="true" > Return </button>
		</form>
		
</body>
</html>