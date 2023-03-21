package com.srivn.works.smlibrary.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;
import com.srivn.works.smlibrary.db.entity.common.ClsnEn;
import com.srivn.works.smlibrary.db.entity.common.ClsnValueEn;
import com.srivn.works.smlibrary.db.entity.common.DataCategoryEn;
import com.srivn.works.smlibrary.db.entity.common.DataValueEn;
import com.srivn.works.smlibrary.db.repo.common.ClsnRepo;
import com.srivn.works.smlibrary.db.repo.common.ClsnValueRepo;
import com.srivn.works.smlibrary.db.repo.common.DataCatRepo;
import com.srivn.works.smlibrary.db.repo.common.DataValueRepo;
import com.srivn.works.smlibrary.exception.SMException;
import com.srivn.works.smlibrary.util.AppMsg;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UtilServices {

	private final ClsnRepo clsnRepo;
	private final ClsnValueRepo clsnValueRepo;
	private final DataCatRepo dataCatRepo;
	private final DataValueRepo dataValueRepo;

	public ClsnEn addClsn(String clsnDesc) {
			return clsnRepo.save(ClsnEn.builder().clsnDes(clsnDesc).build());
	}

	public ClsnValueEn addClsnValue(String clsnDes, String clsnValue) {
		ClsnEn clsnEn = clsnRepo.findByClsnDes(clsnDes);
		return clsnValueRepo.save(ClsnValueEn.builder().clsn(clsnEn).clsnValue(clsnValue).build());
	}

	public ClsnEn getByClsnDes(String clsnDesc) {
		try {
			ClsnEn clsnEn = clsnRepo.findByClsnDes(clsnDesc);
			if (clsnEn != null) {
				return clsnEn;
			} else {
				throw new SMException(AppMsg.Err.ERR_001.getCode(), AppMsg.Err.ERR_001.getMsgWithParam(clsnDesc));
			}
		} catch (Exception e) {
			throw new SMException(AppMsg.Err.ERR_000.getCode(), AppMsg.Err.ERR_000.getMsgWithParam());
		}
	}

	public ClsnValueEn getByClsnValue(String clsnVal) {
		return clsnValueRepo.findByClsnValue(clsnVal);
	}

	public List<ClsnEn> getClsnAll() {
		return StreamSupport.stream(clsnRepo.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public List<ClsnValueEn> getClsnValAll() {
		return StreamSupport.stream(clsnValueRepo.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public DataCategoryEn addDataCat(String dataCategory) {
		return dataCatRepo.save(DataCategoryEn.builder().catValue(dataCategory).build());
	}

	public DataValueEn addDataValue(String dataCategory, String dataValue) {
		DataCategoryEn dataCategoryEn = dataCatRepo.findByCatValue(dataCategory);
		return dataValueRepo.save(DataValueEn.builder().dataCat(dataCategoryEn).dataValue(dataValue).build());
	}

	public DataCategoryEn getByCatValue(String catValue) {
		return dataCatRepo.findByCatValue(catValue);
	}

	public DataValueEn getByDataValue(String dataValue) {
		return dataValueRepo.findByDataValue(dataValue);
	}

	public List<DataCategoryEn> getDataCategoryAll() {
		return StreamSupport.stream(dataCatRepo.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public List<DataValueEn> getDataValueAll() {
		return StreamSupport.stream(dataValueRepo.findAll().spliterator(), false).collect(Collectors.toList());
	}
}
