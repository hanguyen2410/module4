package com.codegym.service.customer;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.model.Transfer;
import com.codegym.model.Withdraw;
import com.codegym.repository.CustomerRepository;
import com.codegym.repository.DepositRepository;
import com.codegym.repository.TransferRepository;
import com.codegym.repository.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private WithdrawRepository withdrawRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getById(Long id) {
        return null;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void remove(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public void removeById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Deposit deposit(Deposit deposit) {
        depositRepository.save(deposit);
        customerRepository.save(deposit.getCustomer());
        return deposit;
    }

    @Override
    public Withdraw withdraw(Withdraw withdraw) {
        withdrawRepository.save(withdraw);
        customerRepository.save(withdraw.getCustomer());
        return withdraw;
    }

    @Override
    public List<Customer> findAllByIdNot(Long id) {
        return customerRepository.findAllByIdNot(id);
    }

    @Override
    public Transfer transfer(Transfer transfer) {
        customerRepository.save(transfer.getSender());
        customerRepository.save(transfer.getRecipient());
        transferRepository.save(transfer);

        return transfer;
    }
}
