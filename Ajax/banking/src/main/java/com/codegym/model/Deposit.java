package com.codegym.model;


import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "deposits")
public class Deposit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    @Column(name = "transaction_amount", precision = 12, scale = 0, nullable = false)
    @DecimalMin(value = "1000" , message = "Số tiền nạp tối thiểu là 1000 VNĐ")
    @DecimalMax(value = "1000000000", message = "Số tiền nạp tối đa là 1.000.000.000 VNĐ")
    @NotNull(message = "Số tiền nạp không được để trống!!")
    private BigDecimal transactionAmount;
    public Deposit() {
    }

    public Deposit(Long id, Customer customer, BigDecimal transactionAmount) {
        this.id = id;
        this.transactionAmount = transactionAmount;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
