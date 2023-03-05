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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CLSN_VAL")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ClsnValueEn {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLSN_VAL_SEQ")
	@SequenceGenerator(name = "CLSN_VAL_SEQ", sequenceName = "CLSN_VAL_SEQ", initialValue = 1000,allocationSize = 1)
	@Column(name = "CLSN_VAL_ID")
	private int clsnValId;
	
	@Column(name = "CLSN_VALUE")
	private String clsnVal;

	@ManyToOne
	@JoinColumn(name = "CLSN_ID")
	ClsnEn clsn;
}
