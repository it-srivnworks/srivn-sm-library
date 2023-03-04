package com.srivn.works.smlibrary.db.entity.books;

import com.srivn.works.smlibrary.db.entity.common.ClassificationValueEn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "BOOK_DETAILS")
public class BookDetailsEn {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
	@SequenceGenerator(name = "book_seq", sequenceName = "book_seq", allocationSize = 1)
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
	private ClassificationValueEn category;

	@Column(name = "units")
	private int units;
}
