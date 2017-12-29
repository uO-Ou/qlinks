package xin.redips.urls.pojo;

public class Account {
	private int id;
	private String name;
	private String password;
	private String mail;
	private int capacity;
	public Account(){}
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
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", password=" + password + ", mail=" + mail + ", capacity="
				+ capacity + "]";
	}
	public Account(String name, String password, String mail) {
		super();
		this.name = name;
		this.password = password;
		this.mail = mail;
	}
	public Account(String name,String password){
		super();
		this.name = name;
		this.password = password;
	}
	
}
