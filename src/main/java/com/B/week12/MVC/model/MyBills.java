package com.B.week12.MVC.model;

public class MyBills {

	private int userBillerId;
	private User fromUser;
	private User toUser;

	public int getUserBillerId() {
		return userBillerId;
	}

	public void setUserBillerId(int userBillerId) {
		this.userBillerId = userBillerId;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

}
