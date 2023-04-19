package eduardofloriani.supercarrental.controllers;

import eduardofloriani.supercarrental.dtos.RentDto;
import eduardofloriani.supercarrental.models.RentModel;
import eduardofloriani.supercarrental.services.RentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rent")
public class RentController {

    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RentModel>> findAll() {
        List<RentModel> bookings = rentService.findAllRents();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<RentModel> findById(@PathVariable UUID id) {
        RentModel rentModel = rentService.findRentById(id);
        return ResponseEntity.ok(rentModel);
    }

    @PostMapping("/add")
    public ResponseEntity<RentModel> add(@RequestBody @Valid RentDto rentDto) {
        RentModel rentModel = rentService.addRent(rentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(rentModel);
    }

    @PutMapping("/update")
    public ResponseEntity<RentModel> update(@RequestBody @Valid RentDto rentDto) {
        RentModel rentModel = rentService.updateRent(rentDto);
        return ResponseEntity.ok(rentModel);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Void> cancel(@PathVariable UUID id) {
        rentService.cancelRent(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        rentService.deleteRent(id);
        return ResponseEntity.noContent().build();
    }
}
