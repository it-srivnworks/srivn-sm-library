package com.srivn.works.smlibrary.db.repo.common;

import org.springframework.data.repository.CrudRepository;
import com.srivn.works.smlibrary.db.entity.common.DataValueEn;

public interface DataValueRepo  extends CrudRepository<DataValueEn, Integer>{

	DataValueEn findByDataValue(String dataValue);
}
