package com.codegym.controller;


import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.model.Transfer;
import com.codegym.model.Withdraw;
import com.codegym.service.customer.ICustomerService;
import com.codegym.service.deposit.IDepositService;
import com.codegym.service.transfer.ITransferService;
import com.codegym.service.withdraw.IWithdrawService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IDepositService depositService;

    @Autowired
    private IWithdrawService withdrawService;

    @Autowired
    private ITransferService transferService;
    @GetMapping
    public ModelAndView showListPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/list");
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreatePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @GetMapping("/update/{customerId}")
    public ModelAndView showUpdatePage(@PathVariable Long customerId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/update");
        Optional<Customer> optionalCustomer = customerService.findById(customerId);
        modelAndView.addObject("customer", optionalCustomer.get());
        return modelAndView;
    }


    @GetMapping("/delete/{customerId}")
    public ModelAndView showDeletePage(@PathVariable Long customerId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/delete");
        Optional<Customer> optionalCustomer = customerService.findById(customerId);
        modelAndView.addObject("customer", optionalCustomer.get());
        return modelAndView;
    }

    @GetMapping("/deposit/{customerId}")
    public ModelAndView showDepositPage(@PathVariable Long customerId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/deposit");
        Optional<Customer> optionalCustomer = customerService.findById(customerId);
        if (!optionalCustomer.isPresent()) {
            modelAndView.setViewName("redirect:" + "404");
        }
        modelAndView.addObject("customer", optionalCustomer.get());
        Deposit deposit = new Deposit();
        deposit.setCustomer(optionalCustomer.get());
        modelAndView.addObject("deposit", deposit);
        return modelAndView;
    }

    @GetMapping("/withdraw/{customerId}")
    public ModelAndView showWithdrawPage(@PathVariable Long customerId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/withdraw");
        Optional<Customer> optionalCustomer = customerService.findById(customerId);
        if (!optionalCustomer.isPresent()) {
            modelAndView.setViewName("redirect:" + "404");
        }
        modelAndView.addObject("customer", optionalCustomer.get());
        Withdraw withdraw = new Withdraw();
        withdraw.setCustomer(optionalCustomer.get());
        modelAndView.addObject("withdraw", withdraw);
        return modelAndView;
    }
    @GetMapping("/transfer/{senderId}")
    public ModelAndView showTransferPage(@PathVariable Long senderId){
        ModelAndView modelAndView = new ModelAndView();
        Optional<Customer> customerOptional = customerService.findById(senderId);
        modelAndView.setViewName("customer/transfer");

        if (!customerOptional.isPresent()) {
            modelAndView.setViewName("redirect:" + "404");
        }

        Customer sender = customerOptional.get();

        List<Customer> recipients = customerService.findAllByIdNot(senderId);

        Transfer transfer = new Transfer();
        transfer.setSender(sender);
        modelAndView.addObject("sender",sender);
        modelAndView.addObject("recipients", recipients);
        modelAndView.addObject("transfer", transfer);

        return modelAndView;
    }

    @GetMapping("/information")
    public ModelAndView showListTransferPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/listTransfer");
        List<Transfer> transfers = transferService.findAll();
        modelAndView.addObject("transfers", transfers);
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView Create(@Validated @ModelAttribute Customer customer, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/create");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("errors", true);
            modelAndView.addObject("customer", customer);
        } else {
            customer.setId(0L);
            customer.setBalance(new BigDecimal(0L));
            customerService.save(customer);
            modelAndView.addObject("customer", new Customer());
            modelAndView.addObject("success", true);
        }
        return modelAndView;
    }


    @PostMapping("/update/{customerId}")
    public ModelAndView update(@PathVariable Long customerId, @Validated @ModelAttribute Customer customer, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/update");

        Optional<Customer> customerOptional = customerService.findById(customerId);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", "ID khách hàng không hợp lệ");
            return modelAndView;
        }

        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("errors", true);
            modelAndView.addObject("customer", customer);
            return modelAndView;
        }

        customer.setBalance(customerOptional.get().getBalance());
        customer.setId(customerId);
        customerService.save(customer);
        modelAndView.addObject("success", true);

        modelAndView.addObject("customer", customer);

        return modelAndView;
    }

    @PostMapping("/delete/{customerId}")
    public ModelAndView delete(@PathVariable Long customerId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/delete");
        Optional<Customer> customerOptional = customerService.findById(customerId);
        if (!customerOptional.isPresent()) {
            modelAndView.setViewName("404");
            return modelAndView;
        }
        Customer customer = customerOptional.get();
        customerService.remove(customer);
        modelAndView.addObject("customer", customer);
        modelAndView.setViewName("redirect:" + "/customers");
        return modelAndView;
    }

    @PostMapping("/deposit/{customerId}")
    public ModelAndView deposit(@PathVariable Long customerId,@Validated @ModelAttribute Deposit deposit, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/deposit");
        Optional<Customer> customerOptional = customerService.findById(customerId);
        if (!customerOptional.isPresent()) {
            modelAndView.setViewName("404");
            return modelAndView;
        }
        if(bindingResult.hasErrors()){
            modelAndView.addObject("errors", true);
            modelAndView.addObject("deposit", deposit);
            return modelAndView;
        }

        Customer customer = customerOptional.get();

        BigDecimal currentBalance = customer.getBalance();

        BigDecimal transactionAmount = deposit.getTransactionAmount();

        BigDecimal newBalance = currentBalance.add(transactionAmount);

        customer.setBalance(newBalance);

        deposit.setCustomer(customer);

        customerService.deposit(deposit);

        deposit.setTransactionAmount(BigDecimal.ZERO);

        modelAndView.addObject("deposit", deposit);
        modelAndView.addObject("success", true);

        return modelAndView;
    }

    @PostMapping("/withdraw/{customerId}")
    public ModelAndView withdraw(@PathVariable Long customerId, @Validated @ModelAttribute Withdraw withdraw,BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/withdraw");
        Optional<Customer> customerOptional = customerService.findById(customerId);
        if (!customerOptional.isPresent()) {
            modelAndView.setViewName("404");
            return modelAndView;
        }
        if(bindingResult.hasErrors()){
            modelAndView.addObject("errors", true);
            modelAndView.addObject("withdraw", withdraw);
            return modelAndView;
        }

        Customer customer = customerOptional.get();
        BigDecimal currentBalance = customer.getBalance();

        BigDecimal transactionAmount = withdraw.getTransactionAmount();

        if (currentBalance.compareTo(transactionAmount) < 0) {
            modelAndView.addObject("error", true);
            modelAndView.addObject("message","Số tiền rút quá số tiền gửi , vui lòng nạp thêm để rút!!");
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("withdraw", withdraw);
            return modelAndView;
        }

        BigDecimal newBalance = currentBalance.subtract(transactionAmount);

        customer.setBalance(newBalance);

        withdraw.setCustomer(customer);

        customerService.withdraw(withdraw);

        withdraw.setTransactionAmount(BigDecimal.ZERO);

        modelAndView.addObject("withdraw", withdraw);
        modelAndView.addObject("success", true);

        return modelAndView;
    }

    @PostMapping("/transfer/{senderId}")
        public ModelAndView transfer(@PathVariable Long senderId, @Validated @ModelAttribute Transfer transfer, BindingResult bindingResult){
                ModelAndView modelAndView = new ModelAndView();
                Optional<Customer> senderOptional = customerService.findById(senderId);
                modelAndView.setViewName("customer/transfer");
        Customer sender = senderOptional.get();

        List<Customer> recipients = customerService.findAllByIdNot(senderId);
        transfer.setSender(sender);

        if (!senderOptional.isPresent()) {
            modelAndView.setViewName("404");
            return modelAndView;
        }
        Optional<Customer> recipientOptional = customerService.findById(transfer.getRecipient().getId());
        if (!recipientOptional.isPresent()) {
            modelAndView.setViewName("404");
            return modelAndView;
        }
        if (bindingResult.hasErrors()){
            modelAndView.addObject("errors",true);
            modelAndView.addObject("sender",sender);
            modelAndView.addObject("recipients", recipients);
            modelAndView.addObject("transfer", transfer);
            return modelAndView;
        }
        modelAndView.addObject("recipients", recipients);
        BigDecimal senderBalance = sender.getBalance();

        Customer recipient = recipientOptional.get();
        BigDecimal recipientBalance = recipient.getBalance();

        BigDecimal transferAmount = transfer.getTransferAmount();
        long fees = 10L;
        BigDecimal feesAmount = transferAmount.multiply(BigDecimal.valueOf(fees)).divide(BigDecimal.valueOf(100L));
        BigDecimal transactionAmount = transferAmount.add(feesAmount);

        BigDecimal minAmount = BigDecimal.valueOf(1000L);
        BigDecimal maxAmount = BigDecimal.valueOf(1000000000L);

        if (transferAmount.compareTo(minAmount) < 0) {
            modelAndView.addObject("error", true);
            modelAndView.addObject("message", "Số tiền chuyển khoản tối thiểu là 1.000 VNĐ");
        }
        else if (transferAmount.compareTo(maxAmount) > 0) {
            modelAndView.addObject("error", true);
            modelAndView.addObject("message", "Số tiền chuyển khoản tối đa là 1.000.000.000 VNĐ");
        }
        else {
            if (senderBalance.compareTo(transactionAmount) < 0) {
                modelAndView.addObject("error", true);
                modelAndView.addObject("message", "Số dư người gửi không đủ để thực hiện giao dịch chuyển khoản, Vui lòng thử lại !!");

                return modelAndView;
            }

            if (sender.getId().equals(recipient.getId())) {
                modelAndView.addObject("error", true);
                modelAndView.addObject("message", "Tài khoản người nhận không hợp lệ, vui lòng thử lại !!");

                return modelAndView;
            }

            BigDecimal newSenderBalance =  senderBalance.subtract(transactionAmount);
            sender.setBalance(newSenderBalance);

            BigDecimal newRecipientBalance = recipientBalance.add(transferAmount);
            recipient.setBalance(newRecipientBalance);

            transfer.setSender(sender);
            transfer.setRecipient(recipient);
            transfer.setFees(fees);
            transfer.setFeesAmount(feesAmount);
            transfer.setTransactionAmount(transactionAmount);

            customerService.transfer(transfer);

            modelAndView.addObject("success", true);
            modelAndView.addObject("message", "Giao dịch chuyển khoản thành công !!");
        }
        return modelAndView;
        }
}

