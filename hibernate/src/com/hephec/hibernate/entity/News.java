package com.hephec.hibernate.entity;

import java.sql.Date;

public class News {
	private Integer id;
	private String title;
	private String author;
	private Date date;
	public News() {
		// TODO Auto-generated constructor stub
	}
	public News(String title, String author, Date date) {
		
		this.title = title;
		this.author = author;
		this.date = date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", author=" + author
				+ ", date=" + date + "]";
	}
	
}
