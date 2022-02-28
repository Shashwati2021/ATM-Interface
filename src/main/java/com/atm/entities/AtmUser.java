package com.atm.entities;

public class AtmUser {
long accno;
long UserPin;
double accBalance;
String username;
String accType;

public AtmUser() {
	super();
	// TODO Auto-generated constructor stub
}
public long getAccno() {
	return accno;
}
public void setAccno(long accno) {
	this.accno = accno;
}
public long getUserPin() {
	return UserPin;
}
public void setUserPin(long userPin) {
	UserPin = userPin;
}
public double getAccBalance() {
	return accBalance;
}
public void setAccBalance(double accBalance) {
	this.accBalance = accBalance;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getAccType() {
	return accType;
}
public void setAccType(String accType) {
	this.accType = accType;
}
public AtmUser(long accno, long userPin, double accBalance, String username, String accType) {
	super();
	this.accno = accno;
	UserPin = userPin;
	this.accBalance = accBalance;
	this.username = username;
	this.accType = accType;
}

}
