package myPackage;

import javax.sql.*;
import java.sql.*;
import java.util.*;
import java.io.*;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;

public class CommentsBean implements Serializable
{
	private List<Comment> comments;

	public List<Comment> getComments()
	{
		comments = new ArrayList<Comment>();

		String query = "SELECT * FROM Comments";
		
		Connection connection = null;
		Statement statement = null;
		ResultSet dataTables = null;
		ResultSet result = null;

		try
		{
			connection = Config.getConnection(); //step 1
			statement = connection.createStatement(); //step 2

			DatabaseMetaData data = connection.getMetaData();
			dataTables = data.getTables(null, null, "Comments", null);
			if(!dataTables.next())
			{
				statement.execute("CREATE TABLE Comments (issueID VARCHAR(100),commentInfo VARCHAR(100), username VARCHAR(20), commentDate VARCHAR(100), solution BOOLEAN, commentID VARCHAR(20));");
			}

			result = statement.executeQuery(query); //step 3 and 4

			while(result.next())
			{ //step 5
				Comment comment = new Comment();
				// you should be validating the following,
				// this is just an example to get you started
				comment.setIssueID(result.getString(1));
				comment.setCommentInfo(result.getString(2));
				comment.setUsername(result.getString(3));
				comment.setCommentDate(result.getString(4));
				comment.setSolution(result.getBoolean(5));
				comment.setCommentID(result.getString(6));

				comments.add(comment);
			}
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
			try{ if(dataTables != null) dataTables.close();}catch(Exception e){};
			try{ if(statement != null) statement.close();}catch(Exception e){};
			try{ if(result != null) result.close();}catch(Exception e){};
			try{ if(connection != null) connection.close();}catch(Exception e){};
		}

		return comments;
	}

	public void setComments(List<Comment> new_comments)
	{
		Connection connection = null;
		Statement statement = null;

		try
		{
			connection = Config.getConnection(); 
			statement = connection.createStatement();
			statement.execute("DROP TABLE Comments;");
			statement.execute("CREATE TABLE Comments (issueID VARCHAR(100), commentInfo VARCHAR(100), username VARCHAR(20), commentDate VARCHAR(100), solution BOOLEAN, commentID VARCHAR(20));");

			String query = "INSERT INTO Comments VALUES";

			ListIterator<Comment> iter = new_comments.listIterator();
			Comment comment = new Comment();
			while(iter.hasNext())
			{
				comment = iter.next();

				String solution = String.valueOf(comment.isSolution());
				solution = solution.toUpperCase();

			 	query +=  "(\'";
			 	query += comment.getIssueID() + "\', \'";
				query += comment.getCommentInfo() + "\', \'";
				query += comment.getUsername() + "\', \'";
				query += comment.getCommentDate() + "\', ";
				query += solution + ", \'";
				if(iter.hasNext())
					query += comment.getCommentID() + "\'),";
				else
					query += comment.getCommentID() + "\');";
			}
			statement.execute(query);
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
		comments = new_comments;
	}
}