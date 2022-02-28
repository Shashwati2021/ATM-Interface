package com.atm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import com.atm.entities.AtmUser;


public class ATMUserDBOperation {
	DBConncetion  ob=new DBConncetion ();
	 Connection conn=ob.connection();
	 //Account Creation Method
	 synchronized public boolean openAccount( AtmUser e) throws SQLException
		{
			PreparedStatement stmt=conn.prepareStatement("insert into SBIAtmUser values(?,?,?,?,?)");
			stmt.setLong(1, e.getAccno());
			stmt.setLong(2,e.getUserPin());
			stmt.setDouble(3,e.getAccBalance());
			stmt.setString(4,e.getUsername());
			stmt.setString(5,e.getAccType());
			
			int affectedRows=stmt.executeUpdate();
			if(affectedRows>0)
			{
				return true;
			}
			else
			{
				return false;
			}
			
			
		}
	//Login Method
	 	public boolean login(long UserPin) throws SQLException
		{
				
			PreparedStatement stmt=conn.prepareStatement("select * from SBIAtmUser where UserPin=? ");
			stmt.setLong(1, UserPin);
			
			
			ResultSet result=stmt.executeQuery();
			if(result.next()) //true or false
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	 	
	 	//Deposit Method
	 	synchronized public void deposit(double depositAmount,long UserPin)//deposit
	 	{
	 		try
	 		{
	 		PreparedStatement stmt=conn.prepareStatement("select * from SBIAtmUser where UserPin=? ");
			stmt.setLong(1, UserPin);	
			ResultSet result=stmt.executeQuery();
			double availableBalance=0.0;
			  while(result.next())
			  {
				  availableBalance=result.getDouble(3); 
			  }
			  
			  availableBalance=availableBalance + depositAmount;
			  
			  PreparedStatement stmt1=conn.prepareStatement("update SBIAtmUser set accBalance=? where UserPin=?");
			  stmt1.setDouble(1, availableBalance);
			  stmt1.setLong(2,UserPin);
			  int affectedRows=stmt1.executeUpdate();
			  if(affectedRows>0)
			  {
				  System.out.println("Amount Credited Successfully!!!!");
				  System.out.println("Your Available Balance is now:"+availableBalance);
				  System.out.println("Thank You For Banking With Us!!!");
			  }
			  else
			  {
				System.out.println("Problem in Credit Amount!!");  
			  }
				 			
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("Something went wrong !!");
	 			System.out.println(" Please Try Again !!");
	 		}
	 		}
		synchronized public void withdraw(double withdrawalAmount,long UserPin)//withdraw
	 	{
	 		try
	 		{
	 		PreparedStatement stmt=conn.prepareStatement("select * from SBIAtmUser where UserPin=? ");
			stmt.setLong(1, UserPin);	
			ResultSet result=stmt.executeQuery();
			double availableBalance=0.0;
			  while(result.next())
			  {
				  availableBalance=result.getDouble(3); 
			  } 
			  if(withdrawalAmount<=availableBalance)
			  {
			  availableBalance=availableBalance - withdrawalAmount;
			  PreparedStatement stmt1=conn.prepareStatement("update SBIAtmUser set accBalance=? where UserPin=?");
			  stmt1.setDouble(1, availableBalance);
			  stmt1.setLong(2,UserPin);
			  int affectedRows=stmt1.executeUpdate();
				  if(affectedRows>0)
				  {
					  System.out.println("Amount Debited Successfully!!");
					  System.out.println("Please Collect Your Amount!!!!");
					  System.out.println("Your Remaining Balance is now:"+availableBalance);
					  System.out.println("Thank You For Banking With Us!!!");
				  }
				  else
				  {
					System.out.println("Problem in Debit Amount!!");  
				  }
			  }
			  else
			  {
				  System.out.println("Sufficient balance not available!!");
			  }	 			
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("Something went wrong!!");
	 			System.out.println(" Please Try Again !!");
	 		}
	 		}
		public ResultSet balanceCheck(long accno)
	 	{
	 		ResultSet rs=null;
	 		try
	 		{
	 		PreparedStatement pr=conn.prepareStatement("select * from SBIAtmUser where UserPin=?");
	 		pr.setLong(1, accno);
	 		rs=pr.executeQuery();
	 		
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("Somethinng went wrong!!");
	 			System.out.println(" Please Try Again !!");
	 		}
	 		return rs;
	 	}
			
		public boolean changePassword(long accno,String newPassword) throws SQLException
		{
			PreparedStatement stmt=conn.prepareStatement("update SBIAtmUser set UserPin=? where UserPin=? ");
			stmt.setString(1,newPassword);
			stmt.setLong(2, accno);
			int affectedRows=stmt.executeUpdate();
			if(affectedRows>0)
			{
				return true;
			}
			else
			{
				return false;
			}
			
			
		}
	
		public void logout() throws SQLException
		{
			conn.close();
			
		}
	
		}

	 	


