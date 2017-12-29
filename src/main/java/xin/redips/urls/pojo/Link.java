package xin.redips.urls.pojo;

import java.util.Date;

public class Link {
     private int id;
     private int userId;
     private int hitCnt;
     private String url;
     private String hint;
     private String tag;
     private Date birth;
     private String helpInfo;
     
     public Date getBirth() {
		return birth;
	 }
	 public void setBirth(Date birth) {
		this.birth = birth;
	 }
	 public int getId() {
		return id;
	 }
	 public void setId(int id) {
		this.id = id;
	 }
	 public int getUserId() {
		return userId;
	 }
	 public void setUserId(int userId) {
		this.userId = userId;
	 }
	 public String getUrl() {
		return url;
	 }
	 public void setUrl(String url) {
		 this.url = url;
	 }
	 public String getHint() {
		 return hint;
	 }
	 public void setHint(String hint) {
		 this.hint = hint;
	 }
	 public Link(){
		 this.hitCnt = 1;
		 this.birth = new Date();
		 this.helpInfo = new String("");
	 }
	 public Link(int userId,String url,String hint,String tag){
		 this.userId = userId;
		 this.url = url;
		 this.hint = hint;
		 this.tag = tag;
		 this.hitCnt = 1;
		 this.birth = new Date();
		 this.helpInfo = new String("");
	 }
	 public String getTag() {
		return tag;
	 }
	 public void setTag(String tag) {
		this.tag = tag;
	 }
	 public int getHitCnt() {
		return hitCnt;
	 }
	 public void setHitCnt(int hitCnt) {
		this.hitCnt = hitCnt;
	 }
	 
	 public String getHelpInfo() {
		return helpInfo;
	 }
	 public void setHelpInfo(String helpInfo) {
		this.helpInfo = helpInfo;
	 }
	 @Override
	 public String toString() {
		return "Link [id=" + id + ", userId=" + userId + ", hitCnt=" + hitCnt + ", url=" + url + ", hint=" + hint
				+ ", tag=" + tag + ", birth=" + birth + ", helpInfo=" + helpInfo + "]";
	 }
	
}
