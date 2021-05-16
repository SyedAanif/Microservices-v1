package com.learn.microservices.currencyexchangeservice.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class ExchangeValue {

    @NonNull
    @Id
    private Long id;
    @NonNull
    @Column(name = "currency_from")
    private String from;
    @NonNull
    @Column(name = "currency_to")
    private String to;
    @NonNull
    private BigDecimal conversionMultiple;
    @Transient
    private int port;
}
