package xin.redips.urls.pojo;

import java.util.Date;

public class Logger {
	public Logger(String name,int op){
		this.username = name;
		this.op_type = op;
	}
	public Logger() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getOp_type() {
		return op_type;
	}
	public void setOp_type(int op_type) {
		this.op_type = op_type;
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	private int id;
	private String username;
	private int op_type;
	private String reserved;
	private Date time;
}
