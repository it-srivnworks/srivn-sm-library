package com.srivn.works.smlibrary.db.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CLSN")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ClassificationEn {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLSN_SEQ")
	@SequenceGenerator(name = "CLSN_SEQ", sequenceName = "CLSN_SEQ", allocationSize = 1)
	@Column(name = "CLSN_ID")
	private int clsnId;
	
	@Column(name = "CLSN_DS")
	private int clsnDes;
	
	
}
