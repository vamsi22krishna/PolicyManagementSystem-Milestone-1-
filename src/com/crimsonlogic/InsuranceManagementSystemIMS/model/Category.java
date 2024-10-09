package com.crimsonlogic.InsuranceManagementSystemIMS.model;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Category {
    private int categoryId;
    private String categoryName;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
