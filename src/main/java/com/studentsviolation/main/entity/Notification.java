package com.studentsviolation.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Notification {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name="is_notif")
	private int isNotif;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getIsNotif() {
		return isNotif;
	}
	public void setIsNotif(int isNotif) {
		this.isNotif = isNotif;
	}
	public int getCrinNotif() {
		return crinNotif;
	}
	public void setCrinNotif(int crinNotif) {
		this.crinNotif = crinNotif;
	}
	public int getEducNotif() {
		return educNotif;
	}
	public void setEducNotif(int educNotif) {
		this.educNotif = educNotif;
	}
	public int getOaNotif() {
		return oaNotif;
	}
	public void setOaNotif(int oaNotif) {
		this.oaNotif = oaNotif;
	}
	public int getNotif() {
		return notif;
	}
	public void setNotif(int notif) {
		this.notif = notif;
	}
	@Column(name="crim_notif")
	private int crinNotif;
	@Column(name="educ_notif")
	private int educNotif;
	@Column(name="oa_notif")
	private int oaNotif;
	@Column(name="notif")
	private int notif;
	@Column(name="disc_notif")
	private int discNotif;
	@Column(name="guide_notif")
	private int guideNotif;
	public int getDiscNotif() {
		return discNotif;
	}
	public void setDiscNotif(int discNotif) {
		this.discNotif = discNotif;
	}
	public int getGuideNotif() {
		return guideNotif;
	}
	public void setGuideNotif(int guideNotif) {
		this.guideNotif = guideNotif;
	}
}
