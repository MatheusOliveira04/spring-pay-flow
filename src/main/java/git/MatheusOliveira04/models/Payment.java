package git.MatheusOliveira04.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import git.MatheusOliveira04.models.enums.PaymentMethod;
import jakarta.persistence.*;
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

    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @Positive(message = "Field must be greater than zero")
    @Column(nullable = false)
    private BigDecimal amountReceived;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sale_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Sale sale;
}
