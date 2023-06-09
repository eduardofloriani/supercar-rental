package eduardofloriani.supercarrental.utils;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class RentUtils {

    public int calculateRentalDays(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start and end dates must be provided.");
        }

        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }

    public double calculateRentAmount(double dailyPrice, int rentalDays) {
        return dailyPrice * rentalDays;
    }

    public boolean isOverLapping(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        return !end1.isBefore(start2) && !end2.isBefore(start1);
    }

}
