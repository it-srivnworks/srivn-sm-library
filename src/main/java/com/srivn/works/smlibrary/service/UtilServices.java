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
import com.srivn.works.smlibrary.exception.SMMessage;
import com.srivn.works.smlibrary.util.AppMsg;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UtilServices {

	private final ClsnRepo clsnRepo;
	private final ClsnValueRepo clsnValueRepo;
	private final DataCatRepo dataCatRepo;
	private final DataValueRepo dataValueRepo;

	public SMMessage addClsn(String clsnDesc) {
		try {
			if (clsnRepo.findByClsnDes(clsnDesc) == null) {
				clsnRepo.save(ClsnEn.builder().clsnDes(clsnDesc).build());
				return SMMessage.getSMMessage(AppMsg.Msg.MSG_ADD_001);
			} else {
				throw SMException.getSMException(AppMsg.Err.ERR_DUP_002, "ClsnDesc");
			}
		} catch (SMException e) {
			throw e;
		} catch (Exception e) {
			throw SMException.getSMException(AppMsg.Err.ERR_UKN_000, null);
		}
	}

	public SMMessage addClsnValue(String clsnDes, String clsnValue) {
		try {
			ClsnEn clsnEn = getByClsnDes(clsnDes);
			ClsnValueEn clsnValueEn = getByClsnValue(clsnValue);
			return SMMessage.getSMMessage(AppMsg.Msg.MSG_ADD_001);
		} catch (SMException e) {
			throw e;
		} catch (Exception e) {
			throw SMException.getSMException(AppMsg.Err.ERR_UKN_000, null);
		}
	}

	public ClsnEn getByClsnDes(String clsnDesc) {
		try {
			ClsnEn en = clsnRepo.findByClsnDes(clsnDesc);
			if (en != null) {
				return en;
			} else {
				throw SMException.getSMException(AppMsg.Err.ERR__DNF_001, clsnDesc);
			}
		} catch (Exception e) {
			throw SMException.getSMException(AppMsg.Err.ERR_UKN_000, null);
		}
	}

	public ClsnValueEn getByClsnValue(String clsnVal) {
		try {
			ClsnValueEn en = clsnValueRepo.findByClsnValue(clsnVal);
			if (en != null) {
				return en;
			} else {
				throw SMException.getSMException(AppMsg.Err.ERR__DNF_001, clsnVal);
			}
		} catch (Exception e) {
			throw SMException.getSMException(AppMsg.Err.ERR_UKN_000, null);
		}
	}

	public List<ClsnEn> getClsnAll() {
		try {
			List<ClsnEn> enList = StreamSupport.stream(clsnRepo.findAll().spliterator(), false)
					.collect(Collectors.toList());
			if (!enList.isEmpty()) {
				return enList;
			} else {
				throw SMException.getSMException(AppMsg.Err.ERR__DNF_001, "CLSNList");
			}
		} catch (Exception e) {
			throw SMException.getSMException(AppMsg.Err.ERR_UKN_000, null);
		}
	}

	public List<ClsnValueEn> getClsnValAll() {
		try {
			List<ClsnValueEn> enList = StreamSupport.stream(clsnValueRepo.findAll().spliterator(), false)
					.collect(Collectors.toList());
			if (!enList.isEmpty()) {
				return enList;
			} else {
				throw SMException.getSMException(AppMsg.Err.ERR__DNF_001, "ClsnValueList");
			}
		} catch (Exception e) {
			throw SMException.getSMException(AppMsg.Err.ERR_UKN_000, null);
		}
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * ***********************************/
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
