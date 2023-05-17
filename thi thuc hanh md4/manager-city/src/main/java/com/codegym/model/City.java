package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;



@Entity
@Table(name = "citys")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class City extends BaseEntity{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        @Column(name = "city_name", nullable = false)
        @NotEmpty(message = "Tên thành phố không được trống")
        private String nameCity;

        @NotEmpty(message = "Tên quốc gia không được trống")
        private String nation;

        @DecimalMin(value = "100", message = "Diện tích phải là 100km trở lên")
        @DecimalMax(value = "1000000", message = "Diện tích phải là 1.000.000km trở xuống")
        @NotNull(message = "Diện tích không được để trống!!")
        private BigDecimal area;

        @DecimalMin(value = "100", message = "dân số phải là 100 người trở lên")
        @DecimalMax(value = "2000000000", message = "dân số phải là 2.000.000.000 người trở xuống")
        @NotNull(message = "Dân Số không được để trống!!")
        private BigDecimal population;

        @DecimalMin(value = "100000", message = "GDP phải là 100.000 VNĐ trở lên")
        @DecimalMax(value = "1000000000", message = "GDP phải là 1.000.000.000 VNĐ trở xuống")
        @NotNull(message = "GDP không được để trống!!")
        private BigDecimal GDP;

        private String description;




}
