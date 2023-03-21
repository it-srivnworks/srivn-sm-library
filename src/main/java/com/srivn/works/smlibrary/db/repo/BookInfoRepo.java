package com.srivn.works.smlibrary.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.srivn.works.smlibrary.db.entity.books.BookInfoEn;

@Repository
public interface BookInfoRepo extends JpaRepository<BookInfoEn, Integer>{

	BookInfoEn findByTitle(String title);
	
	BookInfoEn findByIsbn(String isbn);
	
}
