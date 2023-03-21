package com.srivn.works.smlibrary.db.repo.common;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.srivn.works.smlibrary.db.entity.common.DataCategoryEn;

@Repository
public interface DataCatRepo extends CrudRepository<DataCategoryEn, Integer>{

	DataCategoryEn findByCatValue(String catValue);
}
