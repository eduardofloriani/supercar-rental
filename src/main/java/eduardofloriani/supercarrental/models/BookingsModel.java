package eduardofloriani.supercarrental.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "bookings")
public class BookingsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID booking_id;
    @Column(nullable = false)
    private Date start_date;
    @Column(nullable = false)
    private Date end_date;
    @Column(nullable = false)
    private double price;

}
