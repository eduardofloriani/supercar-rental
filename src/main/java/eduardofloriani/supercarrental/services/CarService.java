package eduardofloriani.supercarrental.services;

import eduardofloriani.supercarrental.dtos.CarDto;
import eduardofloriani.supercarrental.exceptions.CarNotFoundException;
import eduardofloriani.supercarrental.models.CarModel;
import eduardofloriani.supercarrental.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.beans.ConstructorProperties;
import java.util.List;
import java.util.UUID;

@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarModel> findAllCars() {
        return carRepository.findAll();
    }

    public CarModel findCarById(UUID id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
    }

    public CarModel saveCar(CarModel carModel) {
        return carRepository.save(carModel);
    }

    public void deleteCar(UUID id) {
        CarModel carModel = findCarById(id);
        carRepository.delete(carModel);
    }
}
