package eduardofloriani.supercarrental.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class UserUtils {

    public int calculateAge(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth must be provided.");
        }

        return (int) ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now());
    }

}
