package kr.iiac.bugs.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
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
	
}
