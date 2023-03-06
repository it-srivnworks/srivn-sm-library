package com.srivn.works.smlibrary.db.repo.common;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.srivn.works.smlibrary.db.entity.common.ClsnValueEn;

@Repository
public interface ClsnValueRepo extends CrudRepository<ClsnValueEn, Integer>{

	ClsnValueEn findByClsnValue(String clsnValue);
}
