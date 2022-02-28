package com.atm.user;

import java.sql.ResultSet;
import java.util.Scanner;

import com.atm.dao.ATMUserDBOperation;
import com.atm.entities.AtmUser;

public class App 
{
    public static void main( String[] args )
    {
    	  
    	        System.out.println("***********************************************************************************************************");
    	    	System.out.println("                                             Welcome to ABC Bank                                           ");
    	    	System.out.println("***********************************************************************************************************");
    	    	System.out.println("                                         PLEASE INSERT YOUR DEBIT CARD                                     ");
    	    	
    	    	boolean status=true;
    	    	do
    	    	{
    	       try
    	       {
    	    	Scanner scan=new Scanner(System.in);
    	    	System.out.println("\t                                     Enter your Account Type                                         ");
    	    	System.out.println("\t                                    Press 1 -> Saving Account                                      ");
    	    	System.out.println("\t                                    Press 2 -> Current Account                                     ");
    	    	int userType=scan.nextInt();
    	    	
    	    	ATMUserDBOperation bo=new ATMUserDBOperation();
				if(userType==1||userType==2)
    	    	{
    	    		System.out.println("\t Enter Correct User pin:");
    	    		long UserPin=scan.nextLong();
    	    		
    	    		boolean validUser=bo.login(UserPin);
    	    		
    	    		if(validUser)
    	    		{
    	    			System.out.println("***********************************************************************************************************");
    	    			System.out.println("Login Successfull!!");
    	    			System.out.println("***********************************************************************************************************");
    	    			
    	    	       	System.out.println("\t"+ "Press-1.Open Account\r\n"
      	       			                       + "Press-2.Deposit\r\n"
       	       			                       + "Press-3.Withdraw\r\n"
       	       			                       + "Press-4.Balance Check\r\n"
       	       			                       + "Press-5.Pin Change\r\n"
       	       			                       + "Press-6.Exit");
    	    	       	
    	    	       	int operation=scan.nextInt();
    	    	    	if(operation==1)
    	    	       	{
    	    	    		System.out.println("***********************************************************************************************************");
    		       			System.out.println("Account Created Successfully!!!!");                                                                             
    		       			System.out.println("***********************************************************************************************************");
    		       		
    	    	            System.out.println("Enter account number:");
    	    	       		long accno=scan.nextLong();
    	    	       		System.out.println("Enter account pin:");
    	    	       		long UserPin1=scan.nextLong();
    	    	       		System.out.println("Enter initial balance:");
    	    	       		double accBalance=scan.nextDouble();
    	    	       		System.out.println("Enter account holder name: :");
    	    	       		String Username=scan.next();
    	    	       		System.out.println("Enter account type: :");
    	    	       		String accType=scan.next();
    	    	       		
    	    	       		
    	    	       		AtmUser u=new AtmUser(accno, UserPin1, accBalance,Username, accType);
    	    	       				
    	    	       		if(bo.openAccount(u))
    	    	       		{
    	    	       			System.out.println("***********************************************************************************************************");
    	    	       			System.out.println("Account created!!");
    	    	       			System.out.println("***********************************************************************************************************");
    	    	       		}
    	    	       		else
    	    	       		{
    	    	       			System.out.println("Problem in account creation!! ");
    	    	       		}
    	    	       		
    	    	       	}
    	    	       	if(operation==2) //Deposit 
    	    	       	{
    	    	       		System.out.println("Enter the Deposit Amount:");
    	    	       		double depositAmount=scan.nextDouble();
    	    	       		System.out.println("***********************************************************************************************************");
    	    	       		bo.deposit(depositAmount, UserPin);
    	    	       		System.out.println("***********************************************************************************************************");

    	    	       	}
    	    	    	else if(operation==3)//withdraw
    			       	{
    			       		System.out.println("Enter the Withdrawal Amount?:");
    			       		double withdrawAmount=scan.nextDouble();
    			       		System.out.println("**********************************************************************************************************");
    			       		bo.withdraw(withdrawAmount, UserPin);
    			       		System.out.println("**********************************************************************************************************");

    			       	}
    	    	     	else if(operation==4)//balance check
    	    	       	{
    	    	     		System.out.println("***********************************************************************************************************");
    	       			 ResultSet rs=bo.balanceCheck(UserPin);
    	       			 while(rs.next())
    	       			 {
    	       				 System.out.println("Available Balance is :"+rs.getDouble(3));	 
    	       				 System.out.println("Thanking You!!!");
    	       			 }
    	       			    System.out.println("***********************************************************************************************************");

    	    	       	}
    	    	    	else if(operation==5)//Change Pin
    	    	       	{
    	    	    		System.out.println("Enter new password:");
    	    	    		String newPassword=scan.next();
    	    	       		if(bo.changePassword(UserPin, newPassword))
    	    	       		{
    	    	       			System.out.println("***********************************************************************************************************");
    	    	    			System.out.println("Pin Changed Successfully!!");
    	    	    			System.out.println("Thank you for banking with us!!!!");
    	    	    			System.out.println("***********************************************************************************************************");

    	    	       		}
                         }
    	    	    	else if(operation==6)
    	    	       	{
    	    	       		bo.logout();
    	    	       		status=false;
    	    	       		System.out.println("***********************************************************************************************************");
    	        			System.out.println("Exits Successfully!!!!");
    	        			System.out.println("***********************************************************************************************************");

    	    	       	}
    	    	    	else 
    	    	       	{
    	    	    		System.out.println("***********************************************************************************************************");
    	        			System.out.println("Wrong Input!!");
    	        			System.out.println("***********************************************************************************************************");

    	    	       	}
    	    		   }
    	    	      else
    	        		{
    	    	    		System.out.println("***********************************************************************************************************");
    	        			System.out.println("Incorrect Userpin !!!!!");
    	        			System.out.println("***********************************************************************************************************");

    	        		}
    	        		
    	    		}
    	    	else
    	    	{
    	    		System.out.println("\t Please enter a correct Userpin!!");
    	    	}
    	        }
    	    	 catch(Exception e)
    	         {
    	      	   System.out.println(e.getMessage());
    	      	   System.out.println("\t Wrong Input!!");
    	      	   System.out.println("\t Provide Number input!!");
    	      		System.out.println("***********************************************************************************************************");

    	        }
    	      	}
    	      	while(status);
    	       }
    	       }

