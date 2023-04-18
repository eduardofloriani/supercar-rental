package eduardofloriani.supercarrental.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import eduardofloriani.supercarrental.enums.BodyTypeEnum;
import eduardofloriani.supercarrental.enums.FuelTypeEnum;
import eduardofloriani.supercarrental.enums.TractionEnum;
import eduardofloriani.supercarrental.enums.TransmissionTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDto {

    private UUID id;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotBlank
    private String model_year;

    @NotBlank
    private String color;

    @NotNull
    private FuelTypeEnum fuel_type;

    @NotNull
    private int number_of_doors;

    @NotNull
    private int fuel_tank_capacity;

    @NotNull
    private int horse_power;

    @NotNull
    private TransmissionTypeEnum transmission_type;

    @NotNull
    private TractionEnum traction;

    @NotNull
    private int number_of_passengers;

    @NotNull
    private BodyTypeEnum body_type;

    @NotBlank
    private String license_plate;

    @NotNull
    private double daily_price;

    private List<RentDto> rentDtos;

}
