package com.unimagdalena.onlineProducts.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
        CASH(0),
        CREDIT_CARD(1),
        PAYPAL(2),
        NEQUI(3),
        DAVIPLATA(4),
        PSE(5);

        private final int value;

        MethodPay(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }
}
