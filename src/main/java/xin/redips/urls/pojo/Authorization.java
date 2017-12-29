package xin.redips.urls.pojo;

import java.util.Date;

public class Authorization {
	//authcode status 0,1,2
	public static final int AUTHCODE_STATUS_OK = 0;
	public static final int AUTHCODE_STATUS_EXPIRED = 1;
	public static final int AUTHCODE_STATUS_INVALID = 2;
	//authcode type
	public static final int AUTHCODE_TYPE_RESETPASSWORD = 0;
	//authcode period in seconds
	public static final int AUTHCODE_PERIOD_RESETPASSWORD = 259200; //3 days in seconds
	
	public Authorization(String username,String authcode){
		this.username = username;
		this.authcode = authcode;
	}
	public Authorization(String username,String authcode,int type,int period){
		this.username = username;
		this.authcode = authcode;
		this.type = type;
		this.period=period;
	}
	public Authorization() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAuthcode() {
		return authcode;
	}
	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	private int id;
	private int type;
	private int period;
	private String reserved;
	private String username;
	private String authcode;
	private Date effectiveDate;

}
