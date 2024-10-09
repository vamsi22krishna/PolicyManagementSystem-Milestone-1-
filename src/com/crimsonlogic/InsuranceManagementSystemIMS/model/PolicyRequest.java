package com.crimsonlogic.InsuranceManagementSystemIMS.model;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class PolicyRequest {
	private int requestId;
    private int userId;
    private int policyId;
    private String status;
    private Timestamp requestDate;
}
