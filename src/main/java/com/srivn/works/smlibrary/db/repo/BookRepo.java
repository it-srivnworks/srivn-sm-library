package com.srivn.works.smlibrary.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srivn.works.smlibrary.db.entity.books.BookDetailsEn;

@Repository
public interface BookRepo extends JpaRepository<BookDetailsEn, Integer>{

}
