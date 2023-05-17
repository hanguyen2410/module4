package com.codegym.service.customer;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.model.Transfer;
import com.codegym.model.Withdraw;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface ICustomerService extends IGeneralService<Customer> {
        Deposit deposit(Deposit deposit);
        Withdraw withdraw(Withdraw withdraw);

        List<Customer> findAllByIdNot(Long id);

        Transfer transfer(Transfer transfer);
}
