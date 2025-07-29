package com.example.accounts.service;

import com.example.accounts.Dto.CustomerDetailsDto;

public interface ICustomerservice {
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
