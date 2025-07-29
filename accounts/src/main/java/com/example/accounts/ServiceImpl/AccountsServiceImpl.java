package com.example.accounts.ServiceImpl;


import com.example.accounts.Dto.AccountsDto;
import com.example.accounts.Dto.CustomerDto;
import com.example.accounts.Mapper.AccountsMapper;
import com.example.accounts.exception.ResourceNotFoundException;
import com.example.accounts.repository.AccountRepository;
import com.example.accounts.repository.CustomerRepository;
import com.example.accounts.Mapper.CustomerMapper;
import com.example.accounts.constants.AccountsConstants;
import com.example.accounts.exception.CustomerAlreadyExistsException;
import com.example.accounts.entity.Accounts;
import com.example.accounts.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.accounts.service.iAccountService;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements iAccountService {
    private  AccountRepository accountRepository;
    private   CustomerRepository customerRepository;

    @Override
    public void create(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());
       Optional<Customer> optionalCustomer=  customerRepository.findByMobileNumber(customer.getMobileNumber());
       if(optionalCustomer.isPresent()){
           throw new CustomerAlreadyExistsException("customer already exists"+customerDto.getMobileNumber());
       }

       Customer savedcustomer= customerRepository.save(customer);

        accountRepository.save(createNewAccount(customer));

    }

    @Override
    public CustomerDto fetch(String mobileNumber) {
  Customer customer=customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()->new ResourceNotFoundException("customer","mobileNumber","mobileNumber"));
        Accounts account=accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(()->new ResourceNotFoundException("account","customer",customer.getCustomerId().toString()));
        CustomerDto customerDto=CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account,new AccountsDto()));
        return customerDto;
    }

    @Override
    public Boolean update(CustomerDto customerDto) {
        boolean isUpdated = Boolean.FALSE;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = Boolean.TRUE;
        }
        return  isUpdated;

    }
    @Override
    public Boolean delete(String mobileNumber){
        boolean isdeleted= Boolean.FALSE;


            Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", mobileNumber.toString())
            );
            customerRepository.deleteById(customer.getCustomerId());
            accountRepository.deleteByCustomerId(customer.getCustomerId());
            isdeleted = Boolean.TRUE;

        return  isdeleted;

    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);

        return newAccount;
    }





}
