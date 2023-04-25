package eduardofloriani.supercarrental.controllers;

import eduardofloriani.supercarrental.dtos.RentDto;
import eduardofloriani.supercarrental.enums.RentTypeEnum;
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

    @GetMapping("/car/{id}")
    public ResponseEntity<List<RentModel>> findAllByCarId(@PathVariable UUID id) {
        List<RentModel> rents = rentService.findAllRentsByRentType(id, RentTypeEnum.CAR);
        return ResponseEntity.ok(rents);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<List<RentModel>> findAllByUserId(@PathVariable UUID id) {
        List<RentModel> rents = rentService.findAllRentsByRentType(id, RentTypeEnum.USER);
        return ResponseEntity.ok(rents);
    }

    @GetMapping("car/{id}/active")
    public ResponseEntity<List<RentModel>> findActiveRentsByCarId(@PathVariable UUID id) {
        List<RentModel> rents = rentService.findActiveRentsByRentType(id, RentTypeEnum.CAR);
        return ResponseEntity.ok(rents);
    }

    @GetMapping("user/{id}/active")
    public ResponseEntity<List<RentModel>> findActiveRentsByUserId(@PathVariable UUID id) {
        List<RentModel> rents = rentService.findActiveRentsByRentType(id, RentTypeEnum.USER);
        return ResponseEntity.ok(rents);
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

    @PutMapping("/finish/{id}")
    public ResponseEntity<Void> finish(@PathVariable UUID id) {
        rentService.finishRent(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        rentService.deleteRent(id);
        return ResponseEntity.noContent().build();
    }
}
