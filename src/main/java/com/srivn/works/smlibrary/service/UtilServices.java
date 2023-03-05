package com.srivn.works.smlibrary.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srivn.works.smlibrary.db.entity.common.ClsnEn;
import com.srivn.works.smlibrary.db.entity.common.ClsnValueEn;
import com.srivn.works.smlibrary.db.repo.common.ClsnRepo;
import com.srivn.works.smlibrary.db.repo.common.ClsnValueRepo;

@Service
public class UtilServices {

	@Autowired
	ClsnRepo clsnRepo;

	@Autowired
	ClsnValueRepo clsnValueRepo;

	public ClsnEn addClsn(String clsnDesc) {
		return clsnRepo.save(ClsnEn.builder().clsnDes(clsnDesc).build());
	}

	public ClsnValueEn addClsnValue(String clsnDes, String clsnValue) {
		ClsnEn clsnEn = clsnRepo.findByClsnDes(clsnDes);
		return clsnValueRepo.save(ClsnValueEn.builder().clsn(clsnEn).clsnVal(clsnValue).build());
	}

	public ClsnEn getClsnByDes(String clsnDesc) {
		return clsnRepo.findByClsnDes(clsnDesc);
	}

	public ClsnValueEn getClsnValByValue(String clsnVal) {
		return clsnValueRepo.findByClsnVal(clsnVal);
	}

	public List<ClsnEn> getClsnAll() {
		return StreamSupport.stream(clsnRepo.findAll().spliterator(), false).collect(Collectors.toList());
	}
	
	public List<ClsnValueEn> getClsnValAll() {
		return StreamSupport.stream(clsnValueRepo.findAll().spliterator(), false).collect(Collectors.toList());
	}
}
