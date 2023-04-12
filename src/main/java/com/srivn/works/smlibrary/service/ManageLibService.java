package com.srivn.works.smlibrary.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srivn.works.smlibrary.db.entity.books.AuthorInfoEn;
import com.srivn.works.smlibrary.db.entity.books.BookInfoEn;
import com.srivn.works.smlibrary.db.entity.common.ClsnEn;
import com.srivn.works.smlibrary.db.mapper.CustomBookInfoMapper;
import com.srivn.works.smlibrary.db.repo.AuthorInfoRepo;
import com.srivn.works.smlibrary.db.repo.BookInfoRepo;
import com.srivn.works.smlibrary.db.repo.common.ClsnRepo;
import com.srivn.works.smlibrary.db.repo.common.ClsnValueRepo;
import com.srivn.works.smlibrary.db.repo.common.DataCatRepo;
import com.srivn.works.smlibrary.db.repo.common.DataValueRepo;
import com.srivn.works.smlibrary.exception.SMException;
import com.srivn.works.smlibrary.exception.SMMessage;
import com.srivn.works.smlibrary.model.BookInfo;
import com.srivn.works.smlibrary.util.AppMsg;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManageLibService {

	private final BookInfoRepo bookRepo;
	private final AuthorInfoRepo authorInfoRepo;

	private final CustomBookInfoMapper customBookInfoMapper;

	private static final Logger logger = LoggerFactory.getLogger(ManageLibService.class);

	public SMMessage addNewBook(BookInfo bookInfo) {
		try {
			if (bookRepo.findByTitle(bookInfo.getTitle()) == null) {
				BookInfoEn bookInfoEn = customBookInfoMapper.DTOToEn(bookInfo);
				customBookInfoMapper.EnToDTO(bookRepo.save(bookInfoEn));
				return SMMessage.builder().code(AppMsg.Msg.MSG_ADD_001.getCode()).message(AppMsg.Msg.MSG_ADD_001.getMsg())
						.build();
			} else {
				throw new SMException(AppMsg.Err.ERR_DUP_002.getCode(), AppMsg.Err.ERR_DUP_002.getMsgWithParam("Book"));
			}
		} catch (SMException e) {
			throw e;
		} catch (Exception e) {
			throw new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgWithParam());
		}

	}

	public BookInfo getBookByTitle(String bookTitle) {
		try {
			BookInfoEn bookInfoEn = bookRepo.findByTitle(bookTitle);
			if (bookInfoEn != null) {
				return customBookInfoMapper.EnToDTO(bookInfoEn);
			} else {
				throw new SMException(AppMsg.Err.ERR__DNF_001.getCode(), AppMsg.Err.ERR__DNF_001.getMsgWithParam("Book"));
			}
		} catch (SMException e) {
			throw e;
		} catch (Exception e) {
			throw new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgWithParam());
		}
	}

	public List<BookInfo> getBookAll() {
		try {
			List<BookInfoEn> enList = bookRepo.findAll();
			if (!enList.isEmpty()) {
				return enList.stream().map(bookEn -> customBookInfoMapper.EnToDTO(bookEn)).collect(Collectors.toList());
			} else {
				throw new SMException(AppMsg.Err.ERR__DNF_001.getCode(), AppMsg.Err.ERR__DNF_001.getMsgWithParam("Books"));
			}
		} catch (SMException e) {
			throw e;
		} catch (Exception e) {
			throw new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgWithParam());
		}
	}

	public SMMessage addNewAuthor(String authorName) {
		try {
			if (authorInfoRepo.findByAuthorName(authorName) == null) {
				authorInfoRepo.save(AuthorInfoEn.builder().authorName(authorName).build());
				return SMMessage.builder().code(AppMsg.Msg.MSG_ADD_001.getCode()).message(AppMsg.Msg.MSG_ADD_001.getMsg())
						.build();
			} else {
				throw new SMException(AppMsg.Err.ERR_DUP_002.getCode(), AppMsg.Err.ERR_DUP_002.getMsgWithParam("Author"));
			}
		} catch (SMException e) {
			throw e;
		} catch (Exception e) {
			throw new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgWithParam());
		}
	}

	public String getAuthorByName(String authorName) {
		try {
			AuthorInfoEn authoeEn = authorInfoRepo.findByAuthorName(authorName);
			if (authoeEn != null) {
				return authoeEn.getAuthorName();
			} else {
				throw new SMException(AppMsg.Err.ERR__DNF_001.getCode(), AppMsg.Err.ERR__DNF_001.getMsgWithParam("Author"));
			}
		} catch (SMException e) {
			throw e;
		} catch (Exception e) {
			throw new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgWithParam());
		}
	}

	public List<String> getAuthorAll() {
		try {
			List<AuthorInfoEn> enList = authorInfoRepo.findAll();
			if (!enList.isEmpty()) {
				return StreamSupport.stream(enList.spliterator(), false)
						.map((en) -> en.getAuthorName()).collect(Collectors.toList());
			} else {
				throw new SMException(AppMsg.Err.ERR__DNF_001.getCode(), AppMsg.Err.ERR__DNF_001.getMsgWithParam("Authors"));
			}
		} catch (SMException e) {
			throw e;
		} catch (Exception e) {
			throw new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgWithParam());
		}
	}
}
