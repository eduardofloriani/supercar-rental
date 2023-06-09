package eduardofloriani.supercarrental.models;

import eduardofloriani.supercarrental.enums.RentStatusEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "rents")
public class RentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "car_id", unique = true)
    private CarModel car;

    @Column(nullable = false)
    private LocalDate start_date;

    @Column(nullable = false)
    private LocalDate end_date;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RentStatusEnum status;

}
