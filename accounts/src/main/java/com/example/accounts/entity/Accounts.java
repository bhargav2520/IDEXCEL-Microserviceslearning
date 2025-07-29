package com.example.accounts.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Accounts extends BaseEntity{
    @Column(name="customer_Id")
    private Long customerId;
    @Id
    @Column(name="account_Number")
    private Long accountNumber;
    @Column(name="account_type")
    private String accountType;
    @Column(name="branch_address")
    private String branchAddress;

}
