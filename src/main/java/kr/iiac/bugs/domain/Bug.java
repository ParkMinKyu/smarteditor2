package kr.iiac.bugs.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Bug {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable = false)
	private long id;

	@Column(nullable = false)
	private long parentId;
	
	@Column(nullable = false,length=300)
	private String menuName;
	
	@Column(length=2000)
	private String content;
	
	@Column(length=100)
	private String writer;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date regDate = new Date();
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date modifyDate = new Date();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
