/*NewArticle.java
   *Author: Michael Price
   *Student No: 3233787
   *Date last Modified: 07/05/2019
   *Description: 
   This is the main program that creates a servlet to run the LoginPage
*/
package myPackage;

import java.time.*;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Date;
import java.util.Random;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.annotation.WebServlet;@WebServlet(urlPatterns = {"/NewArticle"})

public class NewArticle extends HttpServlet 
{
	//main method is called apon navigating to the page with no data
	//creates saveList and data to save the current running game to the savelist
	//then uses print method to display the ui
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
      RequestDispatcher dispatcher;

      String first = request.getParameter("new");

      if(first == null)
      {
        first = "error";
      }

      if(first.equals("first"))
      {
        HttpSession session = request.getSession();

        GeneralUser person = (GeneralUser) session.getAttribute("generalUser");
        Issue issue = new Issue();
        issue.setUserName(person.getUsername());
        issue.setState("New");
       
        issue.setResolved(false);

        Date tempDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
        String date = formatter.format(tempDate);
        issue.setDateReported(date);

        session.setAttribute("issue", issue);

        dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/details.jsp");
      }
      else
      {
        String[] strCategory = {"Network", "Software", "Hardware", "Email", "Account"};
        String[] strSubcategory = {"Cannot connect", "Speed", "Constant dropouts", "Other", "Slow to load", "Will not load at all", "Other", "Computer will not turn on", "Computer blue screens", 
                                    "Disk drive", "Peripherals", "Other", "Cannot send", "Cannot recieve", "Spam or Phishing", "Other", "Password reset", "Wrong details", "Other"};

        HttpSession session = request.getSession();
        Issue issue = (Issue) session.getAttribute("issue");

        if(request.getParameter("issueTitle") != null)
        {
          String tempTitle = request.getParameter("issueTitle");
          issue.setIssueTitle(tempTitle);

          String tempDescription = request.getParameter("issueDescription");
          issue.setIssueDescription(tempDescription);

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

          issue.setIssueID(randomString);

          session.setAttribute("issue", issue);

          KnowledgeBaseBean kb = (KnowledgeBaseBean) session.getAttribute("kb");
          List<Issue> tempList = kb.getIssues();
          tempList.add(issue);
          kb.setIssues(tempList);
          session.setAttribute("kb", kb);

          dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/viewArticle.jsp");
        }
        else
        {
          String strIssue = request.getParameter("numIssue");
          int numIssue = Integer.valueOf(strIssue);
          issue.setSubcategory(strSubcategory[numIssue]);

          if(numIssue <= 3)
          {
            issue.setCategory(strCategory[0]);
          }
          else if(numIssue > 3 && numIssue <= 6)
          {
            issue.setCategory(strCategory[1]);
          }
          else if(numIssue > 6 && numIssue <= 11)
          {
            issue.setCategory(strCategory[2]);
          }
          else if(numIssue > 11 && numIssue <= 15)
          {
            issue.setCategory(strCategory[3]);
          }
          else if(numIssue > 15 && numIssue <= 18)
          {
            issue.setCategory(strCategory[4]);
          }

          KnowledgeBaseBean kb = (KnowledgeBaseBean) session.getAttribute("kb");
          kb = new KnowledgeBaseBean();
          session.setAttribute("kb", kb);

          session.setAttribute("issue", issue);

          Issue tempIssue = new Issue();
          List<Issue> tempList = new ArrayList<Issue>();

          List<Issue> issues = new ArrayList<Issue>();
          issues = kb.getIssues();

          ListIterator<Issue> iterIssues = issues.listIterator();

          for(int i = 0; i < issues.size(); i++)
          {
            tempIssue = iterIssues.next();

            if(tempIssue.getSubcategory().equals(strSubcategory[numIssue]))
            {
              tempList.add(tempIssue);
            }
          }
          session.setAttribute("suggestList", tempList);

          dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/newArticle.jsp");
        }
      }

	    dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp");
        dispatcher.forward(request, response);
    }
}