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
import lombok.Builder;
import lombok.Data;

@Builder
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
	@JoinColumn(name = "AUTHOR_ID")
	private AuthorInfoEn author;

	@ManyToOne
	@JoinColumn(name = "DV_ID_PUBLICATION")
	private DataValueEn publlication;
	
	@ManyToOne
	@JoinColumn(name = "CV_ID_CATEGORY")
	private ClsnValueEn category;

	@Column(name = "units")
	private int units;
}
