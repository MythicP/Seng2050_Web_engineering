<%-- 
GamePage.jsp
   *Author: Michael Price
   *Student No: 3233787
   *Date last Modified: 07/05/2019
   *Description: 
   This is the main program that runs the Game
--%>

<%@page import="java.io.IOException"%>
<%@page import="java.lang.NumberFormatException"%>
<%@page import="myPackage.HtmlGen"%>
<%@page import="myPackage.GameSave"%>
<%@page import="myPackage.SaveData"%>
<%@page import="java.util.List"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Collections"%>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Deal or No-Deal</title>
		<link rel="stylesheet" type="text/css" href="css\style.css">
		<jsp:useBean id="data" class="myPackage.GameSave" scope="session"/>
		<jsp:useBean id="saveList" class="myPackage.SaveData" scope="session"/>
	</head>
	<%! 
		String userId = "";
		String home = "";
		String value = "";
		String deal = "";
		
		boolean dealorNo = false;
		boolean fin = false;
		boolean[] opened;
		boolean error = false;

		int picked = -1;
		
		Double[] caseValues;
		
		HtmlGen hgen = new HtmlGen();
	%>
	<%
		value = request.getParameter("case");
		home = request.getParameter("home");
		deal = request.getParameter("Bdeal");
		userId = request.getParameter("userId");

		Double[] tempValues = {0.5, 1.0, 10.0, 100.0, 200.0, 500.0, 1000.0, 2000.0, 5000.0, 10000.0, 20000.0, 50000.0};
		caseValues = tempValues;

		if(value == null)
			value = "";
		if(home == null)
			home = "";
		if(deal == null)
			deal = "";

		try
		{
			if(home.equals("New")) 		//if the page is loaded from homepage with new the data is reset to the defalt
			{
				saveList.setRemove(userId);
				data.setUserName(userId);
				data.setWinnings(0.0);
				data.setDealOrNo(false);
				boolean[] opened1 = {false, false, false, false, false, false, false, false, false, false, false, false};
				data.setOpenedCases(opened1);
				data.setRound(1);
				data.setCount(4);
				data.setPicked(-1);
				data.setCaseValues(caseValues);
				dealorNo = false;
				deal = "";
			}
			else if(home.equals("Load"))		//if the page is loaded with a selected user that users game is loaded
			{
				GameSave temp = saveList.getSave(userId);
				if(temp != null)
				{
					data.setUserName(temp.getUserName());
					data.setOpenedCases(temp.getOpenedCases());
					data.setRound(temp.getRound());
					data.setCount(temp.getCount());
					data.setPicked(temp.getPicked());
					data.setCaseValues(temp.getCaseValues());
					data.setWinnings(temp.getWinnings());
					data.setDealOrNo(temp.getDealOrNo());

					saveList.setRemove(userId);

					if(data.getCount() <= 0)
					{
						dealorNo = true;
						data.setDealOrNo(true);
					}
				}
				else
				{
					data.setWinnings(1.0);		//if the user dosn't exsist an error message is displayed
					error = true;
				}	
			}
			
			if(deal.equals("nodeal"))		//switch is used to maintain/ set noumber of cases to open after a new round starts
			{
				dealorNo = false;
				data.setDealOrNo(false);
				data.setRound(data.getRound() + 1);
				switch(data.getRound())
				{
					case 1:
						break;
					case 2:
						data.setCount(3);
						break;
					case 3:
						data.setCount(2);
						break;
					case 4:
						data.setCount(1);
						break;
					case 5:
						data.setCount(1);
						fin = true;
						break;
				}	
			}

			opened = data.getOpenedCases();
			picked = data.getPicked();

			int sub = Integer.parseInt(value.substring(1)) - 1;

			if(picked == -1)	//if the user hasn't selected a case the first case pased is selected
			{
				data.setPicked(sub);
				picked = sub;
			}
			else if(opened[sub] == false) //if case is opened already no change, ie refresh won't break game
			{
				Double[] list = data.getCaseValues();
				opened[sub] = true;
				if(data.getRound() > 5)
				{
					data.setRound(1);
					data.setCount(4);
				}
				data.setCount(data.getCount() - 1);
				if(data.getCount() <= 0)
				{
					dealorNo = true;
					data.setDealOrNo(true);
				}
			}
		}
		catch(Exception e)
		{
		}
	%>
	<body>
		<div id = "total">
			<h1>Deal or No-Deal </h1>
			<div id ="left-c">
				<p>Round: <jsp:getProperty name = "data" property = "round"/>
				<br>
				Cases Left to Open:	<jsp:getProperty name = "data" property = "count"/>
				<br>
				Your Selected Case: <% out.print(picked + 1);%>
				<br>
				<%	if(data.getPicked() == -1)
						out.print("<br>Please Select Your Case");
				%>
				</p>
			</div>
			<div id = "mid-c">
				<% 
					if(error == true) //if loaded user dosn't exsist 
					{
						error = false;
						out.println("<p>There was an error loading this page</p>");
					}	
					else if(deal.equals("deal")) //if the user had picked deal
					{
						data.setWinnings(data.getOffer());
						out.print("<form id=\"cases\"><p>A winner is you!<br>Here are your winnings: " + data.getOffer() + "</p></form>");
						deal = "";
						dealorNo = false;
						data.setDealOrNo(false);
					}
					else if(fin) // if the user reaches and picks no-deal at the end of the game
					{
						tempValues = data.getCaseValues();
						Double valuething = tempValues[data.getPicked()];
						data.setWinnings(valuething);
						out.print("<form id=\"cases\"><p>A winner is you!<br>Here are your winnings: " + valuething + "</p></form>");
						fin = false;
					}
					else if(data.getDealOrNo()) //triggered after the selected no of cases for a round are opend
						out.print(hgen.offer(data.getOffer()));
					else
						out.print(hgen.form(data.getOpenedCases(), picked));
				%>
			</div>
			<div id = "right-c">
				<p>Case Values</p>
				<%
					out.print(hgen.valueTable(data.getOpenedCases(), data.getCaseValues(), caseValues));
				%>
				<br>
			</div>
			<form action = "HomePage" method="POST">
				<button name="save" type="submit" value="true">Save & Exit</button>
			</form>
			<br>
		</div>
	</body>
</html>