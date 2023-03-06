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
@Table(name = "DATA_CAT")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DataCategoryEn {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_catId")
	@SequenceGenerator(name = "seq_catId", sequenceName = "seq_catId", initialValue = 100, allocationSize = 1)
	@Column(name = "CAT_ID")
	private int catId;

	@Column(name = "CAT_VALUE")
	private String catValue;

}
