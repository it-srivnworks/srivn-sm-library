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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CLSN")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ClsnEn {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_clsn")
	@SequenceGenerator(name = "seq_clsn", sequenceName = "seq_clsn", initialValue = 100, allocationSize = 1)
	@Column(name = "CLSN_ID")
	private int clsnId;

	@Column(name = "CLSN_DS")
	private String clsnDes;

}
