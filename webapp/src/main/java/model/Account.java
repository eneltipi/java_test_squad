package model;

public class Account{
    String username;
    String email;
    String password;
    String fullname;
    String address;
    String phonenumber;// vien
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	String role;
    String dateCreated;
    
    //for Ajax cell update
    String columnName;
    String newValue;
    
    public Account() {
//        System.out.println("Creating new instance..............");
    }
    
    public Account(String email, String password) {
    	this.email = email;
    	this.password = password;
//      System.out.println("Creating new instance..............");
  }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getFullname() {
        return fullname;
    }

    public String getAddress() {
        return address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getRole() {
        return role;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public Account(String username, String email, String password, String fullname, String address, String phonenumber, String role, String dateCreated, String columnName, String newValue) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.phonenumber = phonenumber;
        this.role = role;
        this.dateCreated = dateCreated;
        this.columnName = columnName;
        this.newValue = newValue;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
