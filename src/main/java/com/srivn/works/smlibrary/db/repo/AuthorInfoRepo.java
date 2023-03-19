package com.srivn.works.smlibrary.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.srivn.works.smlibrary.db.entity.books.AuthorInfoEn;

public interface AuthorInfoRepo extends JpaRepository<AuthorInfoEn, Integer>{

	AuthorInfoEn findByAuthorName(String authorName);
}
