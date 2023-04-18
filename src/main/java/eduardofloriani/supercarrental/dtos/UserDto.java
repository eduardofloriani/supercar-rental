package eduardofloriani.supercarrental.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
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

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date_of_birth;

}
