package com.crimsonlogic.InsuranceManagementSystemIMS.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private int userId;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private long phoneNumber;
    private String role;

  

}
