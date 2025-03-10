package git.MatheusOliveira04.models;

import git.MatheusOliveira04.models.enums.PaymentMethod;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.UUID;

@Validated
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "tb_payment")
public class Payment {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull @Positive
    private BigDecimal amountReceived;
}
