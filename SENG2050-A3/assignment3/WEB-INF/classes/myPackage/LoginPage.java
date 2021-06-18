/*LoginPage.java
   *Author: Michael Price
   *Student No: 3233787
   *Date last Modified: 07/05/2019
   *Description: 
   This is the main program that creates a servlet to run the LoginPage
*/
package myPackage;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;@WebServlet(urlPatterns = {"/LoginPage"})
public class LoginPage extends HttpServlet 
{
	//main method is called apon navigating to the page with no data
	//creates saveList and data to save the current running game to the savelist
	//then uses print method to display the ui
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		doPost(request, response);
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {	
		UserDatabase data = new UserDatabase();
		List<GeneralUser> general_list = data.getGeneral();
		List<Staff> staff_list = data.getStaff();
		
		if(general_list.size() == 0 || staff_list.size() == 0)
		{
			data.setGeneral();
			data.setStaff();
		}
		
		String returnValue = request.getParameter("returnValue");
		
		if(returnValue == null)
			returnValue = "error";
		
		if(returnValue.equals("logout"))
		{
			HttpSession session = request.getSession();
			session.invalidate();
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
    }
}