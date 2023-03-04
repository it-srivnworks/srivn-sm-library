package com.srivn.works.smlibrary.db.repo.common;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.srivn.works.smlibrary.db.entity.common.ClassificationValueEn;

@Repository
public interface ClassificationValueRepo extends CrudRepository<ClassificationValueEn, Integer>{

}
