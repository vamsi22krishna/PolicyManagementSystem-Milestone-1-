package com.crimsonlogic.InsuranceManagementSystemIMS.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Policy {
	private int policyId;
	private String policyName;
	private int subCategoryId;
	private int categoryId;
	private BigDecimal amount;
	private int minAge;
	private int maxAge;
	private Timestamp createdAt;
	private Timestamp updatedAt;
}

