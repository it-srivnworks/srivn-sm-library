package com.srivn.works.smlibrary.db.entity.books;

import com.srivn.works.smlibrary.db.entity.common.ClsnValueEn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "BOOK_INFO")
public class BookInfoEn {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
	@SequenceGenerator(name = "book_seq", sequenceName = "book_seq",initialValue = 100, allocationSize = 1)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "AUTHOR")
	private String author;

	@Column(name = "PUBLICATION")
	private String publlication;
	
	@Column(name = "ISBN")
	private String isbn;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY")
	private ClsnValueEn category;

	@Column(name = "units")
	private int units;
}
