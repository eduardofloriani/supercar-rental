package eduardofloriani.supercarrental.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue
    private UUID user_id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    @CPF
    private String cpf;
    @Column(nullable = false, unique = true)
    @Size(min = 7, max = 9)
    private String identity_card;
    @Column(nullable = false)
    @Email
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private Date date_of_birth;

}
