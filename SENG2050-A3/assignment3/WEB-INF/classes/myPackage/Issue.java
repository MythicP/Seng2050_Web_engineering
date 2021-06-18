package myPackage;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.LinkedList;
import java.io.Serializable;

public class Issue implements Serializable
{
	private String state;
	private String username;
	private List<Staff> staff;
	private String category;
	private String subcategory;
	private String dateReported;
	private String dateResolved;
	private String issueTitle;
	private String issueDescription;
	private boolean resolved;
	private List<Comment> comments;
	private String issueID;
	
	public Issue()
	{
		
	}

	public String getState()
	{
		return state;
	}

	public void setState(String new_state)
	{
		state = new_state;
	}

	public String getUserName()
	{
		return username;
	}

	public void setUserName(String new_user)
	{
		username = new_user;
	}

	public List<Staff> getStaff()
	{
		return staff;
	}

	public void setStaff(List<Staff> new_staff)
	{
		staff = new_staff;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String new_category)
	{
		category = new_category;
	}

	public String getSubcategory()
	{
		return subcategory;
	}

	public void setSubcategory(String new_subcategory)
	{
		subcategory = new_subcategory;
	}

	public String getDateReported()
	{
		return dateReported;
	}

	public void setDateReported(String new_dateReported)
	{
		dateReported = new_dateReported;
	}

	public String getDateResolved()
	{
		return dateResolved;
	}

	public void setDateResolved(String new_dateResolved)
	{
		dateResolved = new_dateResolved;
	}

	public String getIssueDescription()
	{
		return issueDescription;
	}

	public void setIssueDescription(String new_issueDescription)
	{
		issueDescription = new_issueDescription;
	}

	public String getIssueTitle()
	{
		return issueTitle;
	}

	public void setIssueTitle(String new_issueTitle)
	{
		issueTitle = new_issueTitle;
	}

	public boolean isResolved()
	{
		return resolved;
	}

	public void setResolved(boolean new_resolved)
	{
		resolved = new_resolved;
	}

	public List<Comment> getComments()
	{
		return comments;
	}

	public void setComments(List<Comment> new_comments)
	{
		comments = new_comments;
	}

	public String getIssueID()
	{
		return issueID;
	}

	public void setIssueID(String new_issueID)
	{
		issueID = new_issueID;
	}
}