package git.MatheusOliveira04.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "tb_billing_details")
public class BillingDetails {

    @Id
    @GeneratedValue
    private UUID id;

    @DecimalMin(value = "0.01", message = "This field must be greater than zero")
    @Column(nullable = false)
    private BigDecimal totalAmountToPay;

    @Column(nullable = false)
    @DecimalMin(value = "0.00", message = "This field cannot be less than zero")
    private BigDecimal totalPaid;

    @Column(nullable = false)
    @DecimalMin(value = "0.00", message = "This field cannot be less than zero")
    private BigDecimal cashBack;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sale_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Sale sale;
}
