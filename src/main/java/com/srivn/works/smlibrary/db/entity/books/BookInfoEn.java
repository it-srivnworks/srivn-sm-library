package com.srivn.works.smlibrary.db.entity.books;

import com.srivn.works.smlibrary.db.entity.common.ClsnValueEn;
import com.srivn.works.smlibrary.db.entity.common.DataValueEn;

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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_book")
	@SequenceGenerator(name = "seq_book", sequenceName = "seq_book",initialValue = 100, allocationSize = 1)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "TITLE")
	private String title;

	@Column(name = "ISBN")
	private String isbn;
	
	@ManyToOne
	@JoinColumn(name = "authorId")
	private AuthorInfoEn author;

	@ManyToOne
	@JoinColumn(name = "DATA_VALUE_ID")
	private DataValueEn publlication;
	
	@ManyToOne
	@JoinColumn(name = "CLSN_VAL_ID")
	private ClsnValueEn category;

	@Column(name = "units")
	private int units;
}
