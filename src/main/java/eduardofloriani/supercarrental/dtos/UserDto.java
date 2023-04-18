package eduardofloriani.supercarrental.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private UUID id;

    @NotBlank
    private String name;

    @CPF
    @NotBlank
    private String cpf;

    @NotBlank
    @Size(min = 7, max = 9)
    private String identity_card;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private Date date_of_birth;

    private List<BookingDto> bookingDtos;

}
