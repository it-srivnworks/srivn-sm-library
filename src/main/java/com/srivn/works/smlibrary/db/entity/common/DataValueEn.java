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
@Table(name = "DATA_VALUE")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DataValueEn {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_data_value")
	@SequenceGenerator(name = "seq_data_value", sequenceName = "seq_data_value", initialValue = 1000,allocationSize = 1)
	@Column(name = "DATA_VALUE_ID")
	private int dataValId;
	
	@Column(name = "DATA_VALUE")
	private String dataValue;

	@ManyToOne
	@JoinColumn(name = "CAT_ID")
	DataCategoryEn dataCat;
}
