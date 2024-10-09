package com.crimsonlogic.InsuranceManagementSystemIMS.model;

import java.sql.Date;
import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserPolicy {
//	private int userPolicyId;
	private int userId;
    private int policyId;
    private String policyName;
    private String categoryName;
    private String subCategoryName;
	private Date startDate;
	private Date endDate;
	private String status;
	
	private String resons;
	
	
	
	
	
	
	
	

public boolean isExpired() {
    LocalDate now = LocalDate.now();
    LocalDate expiration = this.endDate.toLocalDate();
    return expiration.isBefore(now);
}

// Method to check if the policy is expiring soon
public boolean isExpiringSoon() {
    LocalDate now = LocalDate.now();
    LocalDate expiration = this.endDate.toLocalDate();
    return !isExpired() && expiration.isBefore(now.plusDays(30));
}
}