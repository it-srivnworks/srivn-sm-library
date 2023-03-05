package com.srivn.works.smlibrary.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class BookInfo {

	private String title;
	private String author;
	private String publlication;
	private String isbn;
	private String category;
	private int units;
}
