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
import com.srivn.works.smlibrary.util.AppConstants;

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
				bookInfo = customBookInfoMapper.EnToDTO(bookRepo.save(bookInfoEn));
				return SMMessage.builder().code(AppConstants.AppMessages.MSG_0001.getCode())
						.message(AppConstants.AppMessages.MSG_0001.getMsg()).build();
			} else {
				throw new SMException(AppConstants.ErrorMessage.ERROR_0002.getCode(),
						AppConstants.ErrorMessage.ERROR_0002.getMsgWithParam("Book"));
			}
		} catch (SMException e) {
			throw e;
		} catch (Exception e) {
			throw new SMException(AppConstants.ErrorMessage.ERROR_0000.getCode(),
					AppConstants.ErrorMessage.ERROR_0000.getMsgWithParam());
		}

	}

	public BookInfo getBookByTitle(String bookTitle) {
		BookInfoEn bookInfoEn = bookRepo.findByTitle(bookTitle);
		if (bookInfoEn != null) {
			return customBookInfoMapper.EnToDTO(bookInfoEn);
		} else {
			throw new SMException(AppConstants.ErrorMessage.ERROR_0001.getCode(),
					AppConstants.ErrorMessage.ERROR_0001.getMsgWithParam("Book"));
		}
	}

	public List<BookInfo> getBookAll() {
		return bookRepo.findAll().stream().map(bookEn -> customBookInfoMapper.EnToDTO(bookEn))
				.collect(Collectors.toList());
	}

	public AuthorInfoEn addNewAuthor(String authorName) {
		return authorInfoRepo.save(AuthorInfoEn.builder().authorName(authorName).build());
	}

	public AuthorInfoEn getAuthorByName(String authorName) {
		return authorInfoRepo.findByAuthorName(authorName);
	}

	public List<AuthorInfoEn> getAuthorAll() {
		return StreamSupport.stream(authorInfoRepo.findAll().spliterator(), false).collect(Collectors.toList());
	}
}
