package eduardofloriani.supercarrental.services;

import eduardofloriani.supercarrental.dtos.CarDto;
import eduardofloriani.supercarrental.exceptions.CarNotFoundException;
import eduardofloriani.supercarrental.models.CarModel;
import eduardofloriani.supercarrental.repositories.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarService {

    private final CarRepository carRepository;

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

    public CarModel addCar(CarDto carDto) {
        CarModel carModel = new CarModel();
        BeanUtils.copyProperties(carDto, carModel);
        return carRepository.save(carModel);
    }

    public CarModel updateCar(CarDto carDto) {
        CarModel carModel = new CarModel();
        BeanUtils.copyProperties(carDto, carModel);
        CarModel car = findCarById(carModel.getCar_id());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(carModel, car);
        return carRepository.save(car);
    }

    public void deleteCar(UUID id) {
        CarModel carModel = findCarById(id);
        carRepository.delete(carModel);
    }
}
