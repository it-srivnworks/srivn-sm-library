package com.srivn.works.smlibrary.db.repo.common;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.srivn.works.smlibrary.db.entity.common.ClsnEn;
import com.srivn.works.smlibrary.db.entity.common.ClsnValueEn;

@Repository
public interface ClsnRepo extends CrudRepository<ClsnEn, Integer>{

	ClsnEn findByClsnDes(String clsnDes);
}
