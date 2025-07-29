package com.example.accounts.service;

import com.example.accounts.Dto.CustomerDto;

public interface iAccountService {

    void create(CustomerDto customerDto);

    CustomerDto fetch(String mobileNumber);

    Boolean update(CustomerDto customerDto);

    Boolean delete(String  MobileNumber);
}
