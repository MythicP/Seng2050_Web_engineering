/*HtmlGen.java
   *Author: Michael Price
   *Student No: 3233787
   *Date last Modified: 07/05/2019
   *Description:
   Is called by HomePage and BookingPage to generate html code based on passed infomation
*/
package myPackage;

import java.util.List;
import java.util.ListIterator;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

public class HtmlGen
{
	public static String doctype()
	{
		return "<!DOCTYPE html>\n<html lang=\"en\">";
	}

	public static String head(String title)
	{
		String lines = "<meta charset=\"UTF-8\">\n";
		lines += "<head><title>" + title + "</title></head>";
		lines += "<link rel=\"stylesheet\" type=\"text/css\" href=\"css\\style.css\">\n";
		return lines;
	}

	public static String br()
	{
		return "<br>";
	}

	public static String h(String heading, int h_Count)
	{
		return "<h" + h_Count + ">" + heading + "</h" + h_Count + ">";
	}

	public static String p(String text)
	{
		return "<p>" + text + "</p>";
	}

	public static String divStart(String id)
	{
		return "<div id=\"" + id + "\">";
	}

	public static String divEnd()
	{
		return "</div>";
	}

	public static String bodyStart()
	{
		return "<body>";
		
	}

	public static String bodyEnd()
	{
		return "</body>";
	}

	//pre-condition: pased variable can't be null and must be lenth 12
	//post-condition: makes a table of case values and decides Id for each table elemnt wether said value has been opend
	public static String valueTable(boolean[] taken, Double[] realValues, Double[] caseValues)
	{
		Double temp = 0.0;
		Double temp2 = 0.0;
		int tempInt = 0;
		String table = "<table>";
		if(taken != null && realValues != null && caseValues != null)
		{
			for(int i = 0; i < 12; i++)
			{
				temp = caseValues[i];
				for(int j = 0; j < 12; j++)
				{
					temp2 = realValues[j];
					if(Double.compare(temp, temp2) == 0)
					{
						tempInt = j;
						break;
					}
				}
				if(taken[tempInt])
					table += "<tr><td id=\"taken\">" + caseValues[i] + "</td></tr>";
				else
					table += "<tr><td>" + caseValues[i] + "</td></tr>";
			}
		}
		table += "</table>";
		return table;
	}

	//pre-condition: offer is some amount and not null
	//post-condition: returns html form with buttons for deal and no-deal
	public static String offer(double offer)
	{
		String lines = "";
		lines += "<form id=\"cases\" action=\"GamePage.jsp\" method=\"POST\" onsubmit = \"return true\">";
		lines += "<p>Press Deal to take the Banks offer of: " + offer + "<br>Or press no-deal to stick with your case</p>";
		lines += "<button name=\"Bdeal\" id=\"Bdeal\" type=\"submit\" value=\"deal\">Deal</button>";
		lines += "<button name=\"Bdeal\" id=\"Bdeal\" type=\"submit\" value=\"nodeal\">No-Deal</button>";
		lines += "</form><br>";
		return lines;
	}

	//pre-condition: opend array must be length 12
	//post-condition: returns the case "table"(not an actual table) of buttons representing each case
	//where each case is given an id based on the cases current state either opend, closed or picked as the users case
	public static String form(boolean[] opened, int picked)
	{
		String ca = ""; 
		String lines = "";
		lines += "<form id=\"cases\" action=\"GamePage.jsp\" method=\"POST\" onsubmit = \"return true\">";
		for(int i = 0; i < 12; i++)
		{
			ca = "C" + (i + 1);
			if(picked == i)
				lines += "<button name=\"case\" id=\"picked\" type=\"submit\" value=\"picked\">Case " + (i + 1) + "</button>";
			else if(opened[i] == true)
				lines += "<button name=\"case\" id=\"opened\" type=\"submit\" value=\"" + ca + "\">Case " + (i + 1) + "</button>";
			else
				lines += "<button name=\"case\" id=\"closed\" type=\"submit\" value=\"" + ca + "\">Case " + (i + 1) + "</button>";
			if(i != 0)
			{
				if( ((i + 1) % 3) == 0)
					lines += "<br><br>";
				else
				lines += "&nbsp;&nbsp;";
			}
			else
				lines += "&nbsp;&nbsp;";
		}
		lines += "</form>";
		lines += "<br>";
		return lines;
	}

	//pre-condition: 
	//post-condition: returns html form with buttons for new and load game 
	public static String menuForm()
	{
		String lines = "\n<form action=\"HomePage\" method= \"GET\">";
		lines += "\n<button name=\"home\" id=\"home\" type=\"submit\" value=\"New\">New Game</button>";
		lines += "\n&nbsp;&nbsp;";
		lines += "\n<button name=\"home\" id=\"home\" type=\"submit\" value=\"Load\">Load Game</button>";
		lines += "\n</form>";
		lines += "\n<br>";
		return lines;
	}

	//pre-condition: value should be "New" or "Load"
	//post-condition: returns html form that allows a user to enter their user id and start the game 
	public static String loadForm(String value)
	{
		String lines = "<script src=\"js/validate.js\"></script>";
		lines += "\n<form action=\"GamePage.jsp\" method= \"POST\" onsubmit=\"return validate()\">";
		lines += "\n<label for=\"userId\">User ID*</label>";
		lines += "\n<input type=\"text\" name=\"userId\" id=\"userId\"/><br><br>";
		lines += "\n<button name=\"home\" id=\"home\" type=\"submit\" value=\"" + value + "\">" + value + " Game</button>";
		lines += "\n</form>";
		lines += "\n<form action=\"HomePage\" method= \"GET\">";
		lines += "\n<button id=\"back\" type=\"submit\">Back To Menu</button>";
		lines += "\n</form>";
		lines += "\n<br>";
		return lines;
	}
}
