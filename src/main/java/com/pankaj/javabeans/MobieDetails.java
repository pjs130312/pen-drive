package com.pankaj.javabeans;

public class MobieDetails implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int mobId;
	public int getMobId() {
		return mobId;
	}
	public void setMobId(int mobId) {
		this.mobId = mobId;
	}
	public String getMobName() {
		return mobName;
	}
	public void setMobName(String mobName) {
		this.mobName = mobName;
	}
	public String getMobLanguage() {
		return mobLanguage;
	}
	public void setMobLanguage(String mobLanguage) {
		this.mobLanguage = mobLanguage;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	private String mobName;
	private String mobLanguage;
	private int price;
}
