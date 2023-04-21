package eduardofloriani.supercarrental.dtos;

import eduardofloriani.supercarrental.enums.BodyTypeEnum;
import eduardofloriani.supercarrental.enums.FuelTypeEnum;
import eduardofloriani.supercarrental.enums.TractionEnum;
import eduardofloriani.supercarrental.enums.TransmissionTypeEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CarFilterDto {

    private LocalDate startDate;
    private LocalDate endDate;
    private String brand;
    private String model;
    private BodyTypeEnum bodyType;
    private String minYear;
    private String maxYear;
    private int minHp;
    private int maxHp;
    private TransmissionTypeEnum transmission;
    private TractionEnum traction;
    private FuelTypeEnum fuelType;
    private double minDailyPrice;
    private double maxDailyPrice;

}
