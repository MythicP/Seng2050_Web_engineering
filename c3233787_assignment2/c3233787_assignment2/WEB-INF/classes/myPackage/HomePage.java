/*HomePage.java
   *Author: Michael Price
   *Student No: 3233787
   *Date last Modified: 07/05/2019
   *Description: 
   This is the main program that creates a servlet to run the homepage
*/
package myPackage;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/HomePage"})
public class HomePage extends HttpServlet 
{
	//main method is called apon navigating to the page with no data
	//creates saveList and data to save the current running game to the savelist
	//then uses print method to display the ui
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	HttpSession session = request.getSession();

    	SaveData saveList = (SaveData) session.getAttribute("saveList");
    	if(saveList == null)
    	{
    		saveList = new SaveData();
    		session.setAttribute("saveList", saveList);
    	}

    	GameSave data = (GameSave) session.getAttribute("data");
    	if(data == null)
    	{
    		data = new GameSave();
    		session.setAttribute("data", data);
    	}

    	try
    	{
    		print(request, response);
    	}
    	catch(Exception e)
    	{

    	}
	}

	//Is called from the GamePage after a user uses the Save&Exit button, thus it saves said game to the SaveList
	//If the pased Save from the session has "winnings" i.e. the game has finnished it won't save the game as it is no longer needed
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	HttpSession session = request.getSession();

    	SaveData saveList = (SaveData) session.getAttribute("saveList");
    	if(saveList == null)
    	{
    		saveList = new SaveData();
    		session.setAttribute("saveList", saveList);
    	}

    	GameSave data = (GameSave) session.getAttribute("data");
    	if(data == null)
    	{
    		data = new GameSave();
    		session.setAttribute("data", data);
    	}

    	String save = request.getParameter("save");
    	if(save == null)
    		save = "";

    	if(save.equals("true"))
    	{
    		GameSave temp = new GameSave();
    		if(data.getWinnings() == 0.0)
    		{
	    		temp.setUserName(data.getUserName());
				temp.setOpenedCases(data.getOpenedCases());
				temp.setRound(data.getRound());
				temp.setCount(data.getCount());
				temp.setPicked(data.getPicked());
				temp.setCaseValues(data.getCaseValues());
				temp.setWinnings(data.getWinnings());
	    		saveList.setSave(temp);
	    	}
    	}

    	try
    	{
    		print(request, response);
    	}
    	catch(Exception e)
    	{

    	}
    }

    //The main method used by both get/ post to display the ui
    public void print(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	PrintWriter out = response.getWriter();
    	HtmlGen hg = new HtmlGen();
    	String test = request.getParameter("home");
    	if(test == null)
    		test = "";

    	out.println(hg.doctype());
		out.println(hg.head("Deal or No-Deal"));
		out.println(hg.bodyStart());
		out.println(hg.divStart("total"));
		out.println(hg.h("Deal or No-Deal", 1));
		if(test == "")
		{
			out.println(hg.menuForm());
		}
		else
		{
			out.println(hg.loadForm(test));
		}
		out.println(hg.br());
		out.println(hg.divEnd());
		out.println(hg.bodyEnd());
    }
}