package git.MatheusOliveira04.models;

import git.MatheusOliveira04.models.enums.StatusSale;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "tb_sale")
public class Sale {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 100)
    private String description;

    private String code;

    @Column(nullable = false)
    private StatusSale status;

    @OneToOne(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private BillingDetails billingDetails;

    @FutureOrPresent
    @Column(nullable = false)
    private LocalDate dateToPay;

    @Setter
    @PastOrPresent
    private LocalDate datePayed;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "sale")
    private List<Payment> payments = new ArrayList<>();
}
