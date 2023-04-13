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
    private UUID id;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String modelYear;
    @Column(nullable = false)
    private String color;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuelTypeEnum fuelType;
    @Column(nullable = false)
    private int doorsNumber;
    @Column(nullable = false)
    private int fuelTankCapacity;
    @Column(nullable = false)
    private int horsePower;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransmissionTypeEnum transmissionType;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TractionEnum traction;
    @Column(nullable = false)
    private int passengersNumber;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BodyTypeEnum bodyType;
    @Column(nullable = false, unique = true)
    private String licensePlate;
    @Column(nullable = false)
    private double dailyPrice;
}
