package model;
// Generated Mar 24, 2024, 12:07:53 AM by Hibernate Tools 5.4.33.Final

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * OrderLevel generated by hbm2java
 */
public class OrderLevel implements java.io.Serializable {

	private Integer idOrderMemberLevel;
	@JsonIgnore
	private MemberLevel memberLevel;
	private User user;
	private Date dateOrder;
	private Date timeOrder;
	private Date dateExp;
	private Date timeExp;
	private String status;
	private String pictureName;
	private String note;

	public OrderLevel() {
	}

	public OrderLevel(MemberLevel memberLevel, User user, Date dateOrder, Date timeOrder, Date dateExp, Date timeExp,
			String status, String pictureName, String note) {
		this.memberLevel = memberLevel;
		this.user = user;
		this.dateOrder = dateOrder;
		this.timeOrder = timeOrder;
		this.dateExp = dateExp;
		this.timeExp = timeExp;
		this.status = status;
		this.pictureName = pictureName;
		this.note = note;
	}

	public Integer getIdOrderMemberLevel() {
		return this.idOrderMemberLevel;
	}

	public void setIdOrderMemberLevel(Integer idOrderMemberLevel) {
		this.idOrderMemberLevel = idOrderMemberLevel;
	}

	public MemberLevel getMemberLevel() {
		return this.memberLevel;
	}

	public void setMemberLevel(MemberLevel memberLevel) {
		this.memberLevel = memberLevel;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDateOrder() {
		return this.dateOrder;
	}

	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	public Date getTimeOrder() {
		return this.timeOrder;
	}

	public void setTimeOrder(Date timeOrder) {
		this.timeOrder = timeOrder;
	}

	public Date getDateExp() {
		return this.dateExp;
	}

	public void setDateExp(Date dateExp) {
		this.dateExp = dateExp;
	}

	public Date getTimeExp() {
		return this.timeExp;
	}

	public void setTimeExp(Date timeExp) {
		this.timeExp = timeExp;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPictureName() {
		return this.pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
