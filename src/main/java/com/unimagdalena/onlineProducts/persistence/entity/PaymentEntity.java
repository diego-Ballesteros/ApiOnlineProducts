package com.unimagdalena.onlineProducts.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {
    @Id
    @Column(name = "id_payment", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;
    @Column(name = "payment_total")
    private Double paymentTotal;
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    @Column(name = "payment_method")
    private MethodPay paymentMethod;

    // Relations ---------------
    @OneToOne(mappedBy = "payment")
    private OrderEntity order;

    public enum MethodPay {
        CASH(1),
        CREDIT_CARD(2),
        PAYPAL(3),
        NEQUI(4),
        DAVIPLATA(5),
        PSE(6);

        private final int value;

        MethodPay(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }
}
