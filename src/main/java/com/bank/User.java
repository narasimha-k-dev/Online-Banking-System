package com.bank;

class User {
	    private int id;
	    private String name;
	    private String password;
	    private double balance;

	    public User(int id, String name, String password, double balance) {
	        this.id = id;
	        this.name = name;
	        this.password = password;
	        this.balance = balance;
	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public double getBalance() {
	        return balance;
	    }

	    public void setBalance(double balance) {
	        this.balance = balance;
	    }

	    @Override
	    public String toString() {
	        return "User{" +
	                "id=" + id +
	                ", name='" + name + '\'' +
	                ", balance=" + balance +
	                '}';
	    }
	}

