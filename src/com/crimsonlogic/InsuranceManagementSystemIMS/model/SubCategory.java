package com.crimsonlogic.InsuranceManagementSystemIMS.model;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubCategory {
	
	private int subCategoryId;
	private String subCategoryName;
	private int categoryId;
	private String description;
	private Timestamp createdAt;
	private Timestamp updatedAt;
}
