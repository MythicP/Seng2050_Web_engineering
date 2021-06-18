package myPackage;

import javax.sql.*;
import java.sql.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Config 
{
	public static Connection getConnection() throws NamingException, SQLException 
	{
		try 
		{
			DataSource datasource = (DataSource)new InitialContext().lookup("java:/comp/env/assignment3");
			Connection connection = datasource.getConnection();
			return connection;
		}
		catch (NamingException ne) 
		{
			System.err.println(ne.getMessage());
			System.err.println(ne.getStackTrace());
			throw ne;
		}
		catch (SQLException sqle) 
		{
			System.err.println(sqle.getMessage());
			System.err.println(sqle.getStackTrace());
			throw sqle;
		}
	}
}
