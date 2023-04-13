package eduardofloriani.supercarrental.models;

import eduardofloriani.supercarrental.enums.BodyTypeEnum;
import eduardofloriani.supercarrental.enums.FuelTypeEnum;
import eduardofloriani.supercarrental.enums.TractionEnum;
import eduardofloriani.supercarrental.enums.TransmissionTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "cars")
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID car_id;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String model_year;
    @Column(nullable = false)
    private String color;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuelTypeEnum fuel_type;
    @Column(nullable = false)
    private int number_of_doors;
    @Column(nullable = false)
    private int fuel_tank_capacity;
    @Column(nullable = false)
    private int horse_power;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransmissionTypeEnum transmission_type;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TractionEnum traction;
    @Column(nullable = false)
    private int number_of_passengers;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BodyTypeEnum body_type;
    @Column(nullable = false, unique = true)
    private String license_plate;
    @Column(nullable = false)
    private double daily_price;

}
