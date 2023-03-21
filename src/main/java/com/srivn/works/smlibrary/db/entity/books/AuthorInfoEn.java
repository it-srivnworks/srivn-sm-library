package com.srivn.works.smlibrary.db.entity.books;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AUTHOR_INFO")
public class AuthorInfoEn {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_author")
	@SequenceGenerator(name = "seq_author", sequenceName = "seq_author",initialValue = 100, allocationSize = 1)
	@Column(name = "authorId")
	private int authorId;
	
	@Column(name = "authorName")
	private String authorName;

}
