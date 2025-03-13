package git.MatheusOliveira04.models;

import git.MatheusOliveira04.models.enums.StatusSale;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "tb_sale")
public class Sale {

    @Id
    @GeneratedValue
    private UUID id;

    private String description;

    private String code;

    @Column(nullable = false)
    private StatusSale status = StatusSale.DUE;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "sale")
    private BillingDetails billingDetails;

    @Column(nullable = false)
    private LocalDate dateToPay;

    private LocalDate datePayed;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "sale")
    private List<Payment> payments = new ArrayList<>();
}
