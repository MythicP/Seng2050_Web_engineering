/*Index.java
   *Author: Michael Price
   *Student No: 3233787
   *Date last Modified: 07/05/2019
   *Description: 
   This is the main program that creates a servlet to run the LoginPage
*/
package myPackage;

import javax.sql.*;
import java.sql.*;
import java.util.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;@WebServlet(urlPatterns = {"/Index"})
public class Index extends HttpServlet 
{

	private UserDatabase data = new UserDatabase();
	private List<Issue> tempList = new ArrayList<Issue>();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp");
        dispatcher.forward(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
		
        String name = request.getParameter("userId");
        String password = request.getParameter("password");
		
		List<GeneralUser> general_list = data.getGeneral();
		List<Staff> staff_list = data.getStaff();
		
		boolean isStaff = false;
		int flag = 0;
		
		if(name != null && password != null)
		{
			ListIterator<GeneralUser> listIter_gen = general_list.listIterator();
			ListIterator<Staff> listIter_staff = staff_list.listIterator();
			
			GeneralUser temp_gen;
			Staff temp_staff;
			
			while(listIter_gen.hasNext())
			{ 
				temp_gen = listIter_gen.next();
				if(temp_gen.getUsername().equals(name) && temp_gen.getPassword().equals(password))
				{
					isStaff = false; 
				}
				else
				{
					flag++;
				}
			}
			
			while(listIter_staff.hasNext())
			{ 
				temp_staff = listIter_staff.next();
				if(temp_staff.getUsername().equals(name) && temp_staff.getPassword().equals(password))
				{
					isStaff = true; 
				}
				else
				{
					flag++;
				}
			}
			
			if(flag >= 6)
			{
				session.invalidate();
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp");
				dispatcher.forward(request, response);
			}
			
			if(isStaff)
			{
				Staff staffMem = new Staff();
				
				//staffMem.setFirstName("null");
				//staffMem.setLastName("null");
				//staffMem.setContactNum("null");
				//staffMem.setEmail("null");
				staffMem.setPassword(password);
				staffMem.setUsername(name);
				
				session.setAttribute("staffUser", staffMem);
			}
			else
			{
				GeneralUser genMem = new GeneralUser();
				
				//genMem.setFirstName("null");
				//genMem.setLastName("null");
				//genMem.setContactNum("null");
				//genMem.setEmail("null");
				genMem.setPassword(password);
				genMem.setUsername(name);
				
				session.setAttribute("generalUser", genMem);
			}
		}

        KnowledgeBaseBean kb = (KnowledgeBaseBean) session.getAttribute("kb");
	    kb = new KnowledgeBaseBean();
	    session.setAttribute("kb", kb);

        session.setAttribute("sorted", "");

        String paramCategory = request.getParameter("category");


	    if(paramCategory == null)
	    {
	    	paramCategory = "";
	    }
	    

        if(!paramCategory.equals(""))
        {
        	Issue tempIssue = new Issue();

        	List<Issue> issues = new ArrayList<Issue>();
        	issues = kb.getIssues();

        	ListIterator<Issue> iterIssues = issues.listIterator();

        	for(int i = 0; i < issues.size(); i++)
        	{
        		tempIssue = iterIssues.next();

        		if(tempIssue.getCategory().equals(paramCategory) || paramCategory.equals("All"))
        		{
        			tempList.add(tempIssue); //tempList should reset but it don't
        		}
        	}
        	session.setAttribute("sortedList", tempList);
        	session.setAttribute("sorted", "true");
        }


        String paramDate = request.getParameter("date");

        if(paramDate == null)
        {
        	paramDate = "";
        }
        else
        {
        	List<Issue> issues = new ArrayList<Issue>();
        	issues = tempList;

        	Issue[] arrIssue = new Issue[issues.size()];
        	arrIssue = issues.toArray(arrIssue);

        	SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
        	Date date1 = new Date();
        	Date date2 = new Date();

        	for(int i = 0; i < issues.size(); i++)
        	{
        		try 
        		{
		        	date1 = formatter.parse(arrIssue[i].getDateReported());
		        } 
		        catch (ParseException e)
		        {
		            e.printStackTrace();
		        }

		        for(int j = i + 1; j < (issues.size()); j++)
		        {
		        	try 
	        		{
			        	date2 = formatter.parse(arrIssue[j].getDateReported());
			        } 
			        catch (ParseException e)
			        {
			            e.printStackTrace();
			        }

			        int result = date1.compareTo(date2);
			        
			        if(result > 0 && paramDate.equals("oldToNew"))
			        {
			        	swap(i, j, arrIssue);
			        }
			        else if(result < 0 && paramDate.equals("newToOld"))
			        {
			        	swap(i, j, arrIssue);
			        }

		        }
		    }

		    tempList = Arrays.asList(arrIssue);

		    session.setAttribute("sortedList", tempList);
        	session.setAttribute("sorted", "true");
        }

        tempList = new ArrayList<Issue>();

        //////////////////////////////////////////////////////////////////////////////////////////\
        if(request.getParameter("deleteSql") != null)
        {
        	Connection connection = null;
			Statement statement = null;

			try
			{
				connection = Config.getConnection(); 
				statement = connection.createStatement();

				statement.execute("DROP TABLE IssuesBase;");
				statement.execute("DROP TABLE Comments;");
	        }
	        catch(SQLException e)
			{
				System.err.println(e.getMessage());
				System.err.println(e.getStackTrace());
				e.printStackTrace();
			}
			catch(Exception e)
			{
				System.err.println(e.getStackTrace());
				e.printStackTrace();
			}
			finally
			{	
				try{ if(statement != null) statement.close();}catch(Exception e){};
				try{ if(connection != null) connection.close();}catch(Exception e){};
			}
		}
        /////////////////////////////////////////////////////////////////////////////////////////

    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		dispatcher.forward(request, response);
	}

	private Issue[] swap(int date1, int date2, Issue[] arrIssue)
	{
		Issue tempIssue = new Issue();

		tempIssue = arrIssue[date1];
		arrIssue[date1] = arrIssue[date2];
		arrIssue[date2] = tempIssue;

		return arrIssue;
	}
}