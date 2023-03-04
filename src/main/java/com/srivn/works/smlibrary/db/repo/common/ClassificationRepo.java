package com.srivn.works.smlibrary.db.repo.common;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.srivn.works.smlibrary.db.entity.common.ClassificationEn;

@Repository
public interface ClassificationRepo extends CrudRepository<ClassificationEn, Integer>{

}
