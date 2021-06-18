/*ViewArticle.java
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
import javax.servlet.annotation.WebServlet;@WebServlet(urlPatterns = {"/ViewArticle"})
public class ViewArticle extends HttpServlet 
{
	//main method is called apon navigating to the page with no data
	//creates saveList and data to save the current running game to the savelist
	//then uses print method to display the ui
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	HttpSession session = request.getSession();

    	String value = request.getParameter("issue");
	 	String newComment = request.getParameter("commentInfo"); 
		String commentSolution = request.getParameter("solution");
		
		if(commentSolution != null)
		{
			CommentsBean beanCom = new CommentsBean();
			Comment comTemp = new Comment();
			
            List<Comment> comlist = new ArrayList<Comment>();
			List<Comment> comlist2 = new ArrayList<Comment>();
			comlist = beanCom.getComments();
			
			ListIterator<Comment> iterCom = comlist.listIterator();
			
			for(int i = 0; i < comlist.size(); i++)
			{
				comTemp = iterCom.next();
				
				if(comTemp.getCommentID().equals(commentSolution))
				{
					comTemp.setSolution(true);
				}
                else
                {
                    comTemp.setSolution(false);
                }
				comlist2.add(comTemp);
			}
			beanCom.setComments(comlist2);
			
			Issue issue = (Issue) session.getAttribute("issue"); //grab current issue
			
			ListIterator<Comment> iterCom2 = comlist2.listIterator();
			
			List<Comment> comlist3 = new ArrayList<Comment>();
			
			Comment tempComment = new Comment();
			
			for(int i = 0; i < comlist2.size(); i++)
			{
				tempComment = iterCom2.next();
				if(issue.getIssueID().equals(tempComment.getIssueID()))
				{
					comlist3.add(tempComment);
				}
			}
			issue.setComments(comlist3);
			session.setAttribute("issue", issue);
			
		}
        else if(newComment != null)	//if someone has entered a new comment
        {
            Issue issue = (Issue) session.getAttribute("issue"); //grab current issue
        	Comment temp = new Comment();
        	Date tempDate = new Date();
            String date = String.valueOf(tempDate);
        	GeneralUser person = (GeneralUser) session.getAttribute("generalUser"); //get current person(might need change compare current person to comments user)
        	Staff staff = (Staff) session.getAttribute("staffUser");
			
        	temp.setCommentInfo(newComment);		//set comment data// WHAT ABOUT STAFF
        	if(person.getUsername() != null)
        		temp.setUsername(person.getUsername());
        	else
            {
        		temp.setUsername(staff.getUsername());    //If Staff sets a comment change to in progress
                issue.setState("In Progress");
            }
        	temp.setCommentDate(date);

            //add generator
             String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";   /*Possible characters in security code*/
              String randomString = "";
              int length = 6;
              
              Random rand = new Random();
              
              char[] text = new char[length];
              
              for(int i = 0; i < length; i++) /*Add random characters to the char array*/
              {
                text[i] = characters.charAt(rand.nextInt(characters.length()));
              }
              
              for(int i = 0; i < text.length; i++)  /*add the chars to a string*/
              {
                randomString += text[i];
              }

              temp.setCommentID(randomString);

        	//get Issue was here
            temp.setIssueID(issue.getIssueID());

        	List<Comment> tempList = issue.getComments();	//add to list
        	if(tempList == null)
        		tempList = new ArrayList<Comment>();
        	tempList.add(temp);
            //
            System.out.println("Testing Thing");            //Comments are added Here
            CommentsBean bean = new CommentsBean();
            List<Comment> comlist = bean.getComments();
            comlist.add(temp);
            bean.setComments(comlist);
            //
        	issue.setComments(tempList);

        	session.setAttribute("issue", issue);
            overwriteIssue(issue, session);
        }
        else if(!value.equals("new"))
        { 
        	Issue issue = searchKbforIssue(session, request, value);
        	session.setAttribute("issue", issue);
        }       

    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/viewArticle.jsp");
		dispatcher.forward(request, response);
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp");
        dispatcher.forward(request, response);
    }

    private Issue searchKbforIssue(HttpSession session, HttpServletRequest request, String id)
    {
    	KnowledgeBaseBean kb = (KnowledgeBaseBean) session.getAttribute("kb");
    	List<Issue> list = kb.getIssues();
      //must look through both Issus and articles
    	ListIterator<Issue> listIter = list.listIterator();
    	Issue temp;
    	Issue returnValue = null;

    	while(listIter.hasNext())
    	{ 
    		temp = listIter.next();
    		if(temp.getIssueID().equals(id))
    		{
    			returnValue = temp; 
    		}
    	}
        if(returnValue == null)
        {
            List<Issue> list2 = kb.getArticles();
            ListIterator<Issue> listIter2 = list2.listIterator();
            while(listIter2.hasNext())
            { 
                temp = listIter2.next();
                if(temp.getIssueID().equals(id))
                {
                    returnValue = temp; 
                }
            }
        }
    	return returnValue;
    }

    private void overwriteIssue(Issue issue, HttpSession session) //added overwrite
    {
        KnowledgeBaseBean kb = (KnowledgeBaseBean) session.getAttribute("kb");
        List<Issue> list = kb.getIssues();
        ListIterator<Issue> listIter = list.listIterator();
        Issue temp;

        while(listIter.hasNext())
        { 
            temp = listIter.next();
            if(temp.getIssueID().equals(issue.getIssueID()))
            {
                listIter.remove();
                list.add(issue);
                break;
            }
        }
        kb.setIssues(list);
    }
}