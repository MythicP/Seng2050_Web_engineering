/*  
 *  Names: 
 *  Student Numbers:
 *  Date: 
 *  Course: SENG2050, Assignment 3
 *  Bean: Database
*/

package myPackage;

import java.io.*;
import java.util.*;
import javax.sql.*;
import java.sql.*;

public class UserDatabase implements Serializable
{
	private List<GeneralUser> general; 	
	private List<Staff> staffMem; 
	
	public UserDatabase() // empty constructor 
	{
		
	}
	
	public List<GeneralUser> getGeneral()
	{
		general = new ArrayList<GeneralUser>();
		
		String query = "SELECT * FROM GeneralList";
		
		Connection connection = null;
		Statement statement = null;
		ResultSet dataTables = null;
		ResultSet result = null;
		
		try
		{
			connection = Config.getConnection(); //step 1
			statement = connection.createStatement(); //step 2
			
			DatabaseMetaData data = connection.getMetaData();
			dataTables = data.getTables(null, null, "GeneralList", null);
			
			if(!dataTables.next())
			{
				statement.execute("CREATE TABLE GeneralList (firstName VARCHAR(20), lastName VARCHAR(20), contactNum VARCHAR(30), email VARCHAR(30), password VARCHAR(20), username VARCHAR(20));");
			}
			
			result = statement.executeQuery(query); //step 3 and 4
			
			while(result.next())
			{ //step 5
				GeneralUser genUser = new GeneralUser();
				
				genUser.setFirstName(result.getString(1));
				genUser.setLastName(result.getString(2));
				genUser.setContactNum(result.getString(3));
				genUser.setEmail(result.getString(4));
				genUser.setPassword(result.getString(5));
				genUser.setUsername(result.getString(6));
				
				general.add(genUser);
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

		return general;
	}	
	
	public void setGeneral()
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet dataTables = null;

		try
		{
			connection = Config.getConnection(); 
			statement = connection.createStatement();
			
			DatabaseMetaData data = connection.getMetaData();
			dataTables = data.getTables(null, null, "GeneralList", null);
			
			if(!dataTables.next())
			{
				statement.execute("CREATE TABLE GeneralList (firstName VARCHAR(20), lastName VARCHAR(20), contactNum VARCHAR(30), email VARCHAR(30), password VARCHAR(20), username VARCHAR(20));");
			}

			statement.execute("DROP TABLE GeneralList;");
			statement.execute("CREATE TABLE GeneralList (firstName VARCHAR(20), lastName VARCHAR(20), contactNum VARCHAR(30), email VARCHAR(30), password VARCHAR(20), username VARCHAR(20));");
			String query = "INSERT INTO GeneralList VALUES";
			
			query += "(\'Peter\', \'Parker\', \'1234567890\', \'p.p@gmail.com\', \'user\', \'pete\'),";
			query += "(\'Harry\', \'Potter\', \'1234567890\', \'h.p@gmail.com\', \'user\', \'harry\'),";
			query += "(\'Jon\', \'Snow\', \'1234567890\', \'j.s@gmail.com\', \'user\', \'jon\');";
			
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
			try{ if(dataTables != null) dataTables.close();}catch(Exception e){};			
			try{ if(statement != null) statement.close();}catch(Exception e){};
			try{ if(connection != null) connection.close();}catch(Exception e){};
		}
	}
	
	public List<Staff> getStaff()
	{
		staffMem = new ArrayList<Staff>();
		
		String query = "SELECT * FROM StaffList";
		
		Connection connection = null;
		Statement statement = null;
		ResultSet dataTables = null;
		ResultSet result = null;
		
		try
		{
			connection = Config.getConnection(); //step 1
			statement = connection.createStatement(); //step 2
			
			DatabaseMetaData data = connection.getMetaData();
			dataTables = data.getTables(null, null, "StaffList", null);
			
			if(!dataTables.next())
			{
				statement.execute("CREATE TABLE StaffList (firstName VARCHAR(20), lastName VARCHAR(20), contactNum VARCHAR(30), email VARCHAR(30), password VARCHAR(20), username VARCHAR(20));");
			}
			
			result = statement.executeQuery(query); //step 3 and 4
			
			while(result.next())
			{ //step 5
				Staff staffUser = new Staff();
				
				staffUser.setFirstName(result.getString(1));
				staffUser.setLastName(result.getString(2));
				staffUser.setContactNum(result.getString(3));
				staffUser.setEmail(result.getString(4));
				staffUser.setPassword(result.getString(5));
				staffUser.setUsername(result.getString(6));
				
				staffMem.add(staffUser);
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

		return staffMem;
	}	
	
	public void setStaff()
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet dataTables = null;

		try
		{
			connection = Config.getConnection(); 
			statement = connection.createStatement();
			
			DatabaseMetaData data = connection.getMetaData();
			dataTables = data.getTables(null, null, "StaffList", null);
			
			if(!dataTables.next())
			{
				statement.execute("CREATE TABLE StaffList (firstName VARCHAR(20), lastName VARCHAR(20), contactNum VARCHAR(30), email VARCHAR(30), password VARCHAR(20), username VARCHAR(20));");
			}

			statement.execute("DROP TABLE StaffList;");
			statement.execute("CREATE TABLE StaffList (firstName VARCHAR(20), lastName VARCHAR(20), contactNum VARCHAR(30), email VARCHAR(30), password VARCHAR(20), username VARCHAR(20));");
			String query = "INSERT INTO StaffList VALUES";
			
			query += "(\'Michael\', \'Price\', \'1234567890\', \'m.p@gmail.com\', \'admin\', \'mike\'),";
			query += "(\'Thomas\', \'Miller\', \'1234567890\', \'t.m@gmail.com\', \'admin\', \'tom\'),";
			query += "(\'Jakeb\', \'Pont\', \'1234567890\', \'j.p@gmail.com\', \'admin\', \'jakeb\');";
			
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
			try{ if(dataTables != null) dataTables.close();}catch(Exception e){};
			try{ if(statement != null) statement.close();}catch(Exception e){};
			try{ if(connection != null) connection.close();}catch(Exception e){};
		}
	}	
}