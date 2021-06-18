
<%@page language="java" import="myPackage.Issue" import="myPackage.KnowledgeBaseBean" import="myPackage.GeneralUser" import="myPackage.Person" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Index</title>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<%--
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma","no-cache"); 
		response.setHeader ("Expires", "0"); 
--%>
<jsp:useBean id="kb" scope="session" class="myPackage.KnowledgeBaseBean"/>
<jsp:useBean id="generalUser" scope="session" class="myPackage.GeneralUser"/>
<jsp:useBean id="staffUser" scope="session" class="myPackage.Staff"/>
</head>

<body>
	<div id="total">
		<h1>Index</h1> 
	
		<div id="Articles">
			<h2>My Issues</h2>
			<c:choose>
			<c:when test="${staffUser.username != null}">
				<p>Welcome user: ${staffUser.username}</p> 
			</c:when>    
			<c:when test="${generalUser.username != null}">
				<p>Welcome user: ${generalUser.username}</p> 
			</c:when>
			 <c:otherwise> <p>Welcome user: ${generalUser.username}</p> </c:otherwise>
		</c:choose>

		<div id = "filter">
			<form id="filterForm" method = "post" action="<%=request.getContextPath()%>/Index">
				<b>Filter</b><br><br>
				Issue Category:<br><br>
				<input type="radio" name="category" value="All" checked="checked"> All Categories
	  			<input type="radio" name="category" value="Network"> Network
	  			<input type="radio" name="category" value="Software"> Software
	  			<input type="radio" name="category" value="Hardware"> Hardware
	  			<input type="radio" name="category" value="Email"> Email
	  			<input type="radio" name="category" value="Account"> Account
	  			<br><br>
				Sort by Date:<br><br>
	  			<input type="radio" name="date" value="oldToNew" checked="checked"> Oldest to Newest
	  			<input type="radio" name="date" value="newToOld"> Newest to Oldest
	  			<br><br>
	  			<button type="submit" value="Submit">Filter</button>
	  			<br><br>
	  		</form>
	  	</div>

			<table>
				<tr>
					<th>Title</th>
					<th>Category</th>
					<th>Date</th>
				</tr>

				<%String temp = (String) session.getAttribute("sorted");

				if(temp == null)
				{temp = "";}

				if(!temp.equals("true"))
				{%>
					<form class = "issueTable" method = "post" action = "<%=request.getContextPath()%>/ViewArticle">
						<c:forEach var="item" items="${kb.issues}">
							<tr>
								<c:choose>
								<c:when test ="${item.userName == generalUser.username}">
									<td width="20%"><button class = "titleButton" name = "issue" type = "submit" value = "${item.issueID}"><c:out value="${item.issueTitle}"/></button></td>
									<td width = "20%"><c:out value="${item.category}"/></td>
									<td width = "20%"><c:out value="${item.dateReported}"/></td>
								</c:when>
								<c:when test ="${staffUser.username != null}">
									<td><button class = "titleButton" name = "issue" type = "submit" value = "${item.issueID}"><c:out value="${item.issueTitle}"/></button></td>
									<td><c:out value="${item.category}"/></td>
									<td><c:out value="${item.dateReported}"/></td>
								</c:when>
								</c:choose>
							</tr>
						</c:forEach>
					</form>
				<%}
				else
				{%>
					<form class = "issueTable" method = "post" action = "<%=request.getContextPath()%>/ViewArticle">
						<c:forEach var="item" items="${sortedList}">
							<tr>
								<c:choose>
								<c:when test ="${item.userName == generalUser.username}">
									<td><c:out value="${item.dateReported}"/></td>
									<td><c:out value="${item.category}"/></td>
									<td><button name = "issue" type = "submit" value = "${item.issueID}"><c:out value="${item.issueTitle}"/></button></td>
								</c:when>
								<c:when test ="${staffUser.username != null}">
									<td><c:out value="${item.dateReported}"/></td>
									<td><c:out value="${item.category}"/></td>
									<td><button name = "issue" type = "submit" value = "${item.issueID}"><c:out value="${item.issueTitle}"/></button></td>
								</c:when>
								</c:choose>
							</tr>
						</c:forEach>
					</form>
				<%}%>

			</table>
			<br>
		</div>
		<div id="SideMenu">
			<h2>Side Menu</h2>
			<p>Side Menu here</p>
			<form action = "<%=request.getContextPath()%>/NewArticle" method = "post">
				<button name="new" type="submit" value="first">New Article</button>
			</form>
			<form action = "<%=request.getContextPath()%>/KnowledgeBase" method = "post">
				<button type="submit" name="returnValue" value="knowledge"> View Knowledge Base </button>
			</form>
			<form action = "<%=request.getContextPath()%>/LoginPage" method = "post">
				<button type="submit" name="returnValue" value="logout"> Log Out </button>
			</form>
			<form action = "<%=request.getContextPath()%>/Index" method = "post">
				<button type="submit" name="deleteSql" value="hello"> Delete SQL databases </button>
			</form>
			<br>
		</div>
		<br>
		<br>
	</div>
</body>
</html>