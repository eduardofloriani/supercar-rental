package eduardofloriani.supercarrental.services;

import eduardofloriani.supercarrental.dtos.RentDto;
import eduardofloriani.supercarrental.enums.RentStatusEnum;
import eduardofloriani.supercarrental.exceptions.RentNotFoundException;
import eduardofloriani.supercarrental.models.RentModel;
import eduardofloriani.supercarrental.models.CarModel;
import eduardofloriani.supercarrental.repositories.RentRepository;
import eduardofloriani.supercarrental.utils.RentCalculator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RentService {

    private final RentRepository rentRepository;
    private final CarService carService;

    private final RentCalculator rentCalculator;
    private static final ModelMapper modelMapper = new ModelMapper();

    public RentService(RentRepository rentRepository, CarService carService, RentCalculator rentCalculator) {
        this.rentRepository = rentRepository;
        this.carService = carService;
        this.rentCalculator = rentCalculator;
    }

    public List<RentModel> findAllRents() {
        return rentRepository.findAll();
    }

    public RentModel findRentById(UUID id) {
        return rentRepository.findById(id)
                .orElseThrow(() -> new RentNotFoundException(id));
    }

    public RentModel addRent(RentDto rentDto) {
        RentModel rentModel = new RentModel();
        modelMapper.map(rentDto, rentModel);

        int rentalDays = rentCalculator.calculateRentalDays(rentDto.getStart_date(), rentDto.getEnd_date());
        CarModel carModel = carService.findCarById(rentDto.getCar_id());
        rentModel.setPrice(rentCalculator.calculateRentAmount(carModel.getDaily_price(), rentalDays));
        rentModel.setCar(carModel);
        rentModel.setStatus(RentStatusEnum.valueOf("ACTIVE"));

        return rentRepository.save(rentModel);
    }

    public RentModel updateRent(RentDto rentDto) {
        RentModel rentModel = findRentById(rentDto.getId());
        modelMapper.map(rentDto, rentModel);

        return rentRepository.save(rentModel);
    }

    public void deleteRent(UUID id) {
        RentModel rentModel = findRentById(id);
        rentRepository.delete(rentModel);
    }

}
