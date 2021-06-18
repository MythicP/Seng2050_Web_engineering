/*KnowledgeBase.java
   *Author: Michael Price
   *Student No: 3233787
   *Date last Modified: 07/05/2019
   *Description: 
   This is the main program that creates a servlet to run the LoginPage
*/
package myPackage;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;@WebServlet(urlPatterns = {"/KnowledgeBase"})
public class KnowledgeBase extends HttpServlet 
{
	//main method is called apon navigating to the page with no data
	//creates saveList and data to save the current running game to the savelist
	//then uses print method to display the ui
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	String returnValue = request.getParameter("returnValue");
		if(returnValue == null)
			returnValue = "error";
		
		if(returnValue.equals("knowledge"))
		{
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/knowledgeBase.jsp");
			dispatcher.forward(request, response);
		}
    }
}