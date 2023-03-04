package com.srivn.works.smlibrary.db.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CLSN_VAL")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ClassificationValueEn {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLSN_VAL_SEQ")
	@SequenceGenerator(name = "CLSN_VAL_SEQ", sequenceName = "CLSN_VAL_SEQ", allocationSize = 1)
	@Column(name = "CLSN_VAL_ID")
	private int clsnValId;
	
	@Column(name = "CLSN_VALUE")
	private int clsnVal;

	@ManyToOne
	@JoinColumn(name = "CLSN_ID")
	ClassificationEn clsn;
}
