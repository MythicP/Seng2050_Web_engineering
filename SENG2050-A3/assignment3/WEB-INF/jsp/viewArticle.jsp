<%@page language="java" import="myPackage.Issue" import="myPackage.KnowledgeBaseBean" import="myPackage.GeneralUser" import="myPackage.Person" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>View Article</title>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<%--
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma","no-cache"); 
		response.setHeader ("Expires", "0"); 
--%>
<jsp:useBean id="generalUser" scope="session" class="myPackage.GeneralUser"/>
<jsp:useBean id="staffUser" scope="session" class="myPackage.Staff"/>
<jsp:useBean id="issue" scope="session" class="myPackage.Issue"/>
</head>
<body>
	<div id ="total">
		<h1>View Article</h1>
		<h3 id = "viewTitle">Title: <c:out value="${issue.issueTitle}"/></h3>
		<br>
		<p>State: <c:out value="${issue.state}"/>
		<br>
		Category: <c:out value="${issue.category}"/>
		<br>
		Report Date: <c:out value="${issue.dateReported}"/>
		<br>
		Description: <c:out value="${issue.issueDescription}"/>
		<br>
		<%--Solution Shown here --%> 
		<br>
		Type Comments Here...</p>
		<form action="<%=request.getContextPath()%>/ViewArticle" method="post">
			<textarea id = "commentArea" name="commentInfo" rows="5" cols="100">
			</textarea>
			<br><br>
			<button class = "viewSubmit" name="newComment" type="submit" value="Submit">Submit</button>
			&nbsp
			<button type="reset" value="Reset">Reset</button>
		</form>
		
		<c:forEach var="comment" items="${issue.comments}">
			<p class='${comment.solution}' >
				<c:out value="${comment.username}"/><br>
				<c:out value="${comment.commentInfo}"/><br>
				<c:out value="${comment.commentDate}"/><br>
				<c:choose>
				<c:when test="${staffUser.username != null}">
				<form action="<%=request.getContextPath()%>/ViewArticle" method="post">
					<button id = "solutionButton" name="solution" type="submit" value="${comment.commentID}">Set Solution</button><br>
				</form>
				</c:when>    
				<c:otherwise>  </c:otherwise>
				</c:choose>
			</p>
		</c:forEach>
		
		<form action="<%=request.getContextPath()%>/Index" method="post">
			<button class = "returnButton" type="submit" name="returnValue" value="true" > Return </button>
		</form>
	</div>
	<div>
		<%-- Staff Menu --%>
		<c:choose>
			<c:when test="${staffUser.username != null}">
				<p> 
				<form action="<%=request.getContextPath()%>/ViewArticle" method="post">
				
				</form>				
				</p> 
			</c:when>    
			 <c:otherwise>  </c:otherwise>
		</c:choose>
	</div>
</body>
</html>