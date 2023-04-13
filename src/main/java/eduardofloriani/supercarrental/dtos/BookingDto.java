package eduardofloriani.supercarrental.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDto {

    private UUID booking_id;

    private UUID user_id;

    private UUID car_id;

    @NotBlank
    private Date start_date;

    @NotBlank
    private Date end_date;

    @NotBlank
    private double price;

}
