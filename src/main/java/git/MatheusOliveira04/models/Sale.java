package git.MatheusOliveira04.models;

import git.MatheusOliveira04.models.enums.StatusSale;
import git.MatheusOliveira04.utils.ListUtils;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
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
    @Setter
    @GeneratedValue
    private UUID id;

    @Column(length = 100)
    private String description;

    private String code;

    @Setter
    @Column(nullable = false)
    private StatusSale status;

    @Setter
    @OneToOne(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private BillingDetails billingDetails;

    @FutureOrPresent
    @Column(nullable = false)
    private LocalDate dateToPay;

    @Setter
    @PastOrPresent
    private LocalDate datePayed;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "sale")
    @Setter
    private List<Payment> payments = new ArrayList<>();

    public Boolean checkDatePayedIsNotNull() {
        return this.getDatePayed() != null;
    }

    public Boolean checkPaymentsIsNullOrEmpty() {
        return ListUtils.isNullOrEmpty(this.getPayments());
    }
}
