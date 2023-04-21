package eduardofloriani.supercarrental.controllers;

import eduardofloriani.supercarrental.dtos.CarDto;
import eduardofloriani.supercarrental.dtos.CarFilterDto;
import eduardofloriani.supercarrental.models.CarModel;
import eduardofloriani.supercarrental.services.CarService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CarModel>> findAll() {
        List<CarModel> cars = carService.findAllCars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CarModel> findById(@PathVariable UUID id) {
        CarModel car = carService.findCarById(id);
        return ResponseEntity.ok(car);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<CarModel>> findByFilters(@RequestBody CarFilterDto filters) {
        List<CarModel> cars = carService.findCarsByFilters(filters);
        return ResponseEntity.ok(cars);
    }

    @PostMapping("/add")
    public ResponseEntity<CarModel> add(@RequestBody @Valid CarDto carDto) {
        CarModel carModel = carService.addCar(carDto);
        return ResponseEntity.ok(carModel);
    }

    @PutMapping("/update")
    public ResponseEntity<CarModel> update(@RequestBody @Valid CarDto carDto) {
        CarModel carModel = carService.updateCar(carDto);
        return ResponseEntity.ok(carModel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

}
