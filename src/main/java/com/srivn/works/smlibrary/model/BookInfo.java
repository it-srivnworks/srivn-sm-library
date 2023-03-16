package com.srivn.works.smlibrary.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
public class BookInfo {

	private String title;
	private String author;
	private String publlication;
	private String isbn;
	private String category;
	private int units;
}
