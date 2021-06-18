package myPackage;

import java.util.List;
import java.util.ListIterator;
import java.util.LinkedList;
import java.io.Serializable;

public abstract class Person implements Serializable
{
	private String firstName;
	private String lastName;
	private String contactNum;
	private String email;
	private String password;
	private String username;

	public Person()
	{

	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String new_firstName)
	{
		firstName = new_firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String new_lastName)
	{
		lastName = new_lastName;
	}

	public String getContactNum()
	{
		return contactNum;
	}

	public void setContactNum(String new_contactNum)
	{
		contactNum = new_contactNum;
	}

	public String getEmail()
	{
		return email;
	} 

	public void setEmail(String new_email)
	{
		email = new_email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String new_password)
	{
		password = new_password;
	}
	
	public String getUsername()
	{
		return username;
	}

	public void setUsername(String new_username)
	{
		username = new_username;
	}
}