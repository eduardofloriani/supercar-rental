package eduardofloriani.supercarrental.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RentDto {

    private UUID id;

    @NotNull
    private UUID user_id;

    @NotNull
    private UUID car_id;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate start_date;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate end_date;

    private double price;

}
