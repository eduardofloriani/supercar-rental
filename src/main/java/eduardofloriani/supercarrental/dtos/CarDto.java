package eduardofloriani.supercarrental.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import eduardofloriani.supercarrental.enums.BodyTypeEnum;
import eduardofloriani.supercarrental.enums.FuelTypeEnum;
import eduardofloriani.supercarrental.enums.TractionEnum;
import eduardofloriani.supercarrental.enums.TransmissionTypeEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDto {

    private UUID car_id;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotBlank
    private String model_year;

    @NotBlank
    private String color;

    @NotBlank
    private FuelTypeEnum fuel_type;

    @NotBlank
    private int number_of_doors;

    @NotBlank
    private int fuel_tank_capacity;

    @NotBlank
    private int horse_power;

    @NotBlank
    private TransmissionTypeEnum transmission_type;

    @NotBlank
    private TractionEnum traction;

    @NotBlank
    private int number_of_passengers;

    @NotBlank
    private BodyTypeEnum body_type;

    @NotBlank
    private String license_plate;

    @NotBlank
    private Double daily_price;

    private List<BookingDto> bookingDtos;

}
