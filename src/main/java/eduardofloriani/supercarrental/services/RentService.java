package eduardofloriani.supercarrental.services;

import eduardofloriani.supercarrental.dtos.RentDto;
import eduardofloriani.supercarrental.enums.RentStatusEnum;
import eduardofloriani.supercarrental.exceptions.CarAlreadyAssociatedException;
import eduardofloriani.supercarrental.exceptions.RentNotFoundException;
import eduardofloriani.supercarrental.models.RentModel;
import eduardofloriani.supercarrental.models.CarModel;
import eduardofloriani.supercarrental.models.UserModel;
import eduardofloriani.supercarrental.repositories.RentRepository;
import eduardofloriani.supercarrental.utils.RentUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RentService {

    private final RentRepository rentRepository;
    private final CarService carService;
    private final UserService userService;
    private final RentUtils rentUtils;
    private static final ModelMapper modelMapper = new ModelMapper();

    public RentService(RentRepository rentRepository, CarService carService, UserService userService, RentUtils rentUtils) {
        this.rentRepository = rentRepository;
        this.carService = carService;
        this.userService = userService;
        this.rentUtils = rentUtils;
    }

    public List<RentModel> findAllRents() {
        return rentRepository.findAll();
    }

    public RentModel findRentById(UUID id) {
        return rentRepository.findById(id)
                .orElseThrow(() -> new RentNotFoundException(id));
    }

    public RentModel addRent(RentDto rentDto) {
        UUID carId = rentDto.getCar_id();
        List<RentModel> rents = rentRepository.findByCarId(carId);
        if (!rents.isEmpty()) {
            throw new CarAlreadyAssociatedException(carId);
        }

        RentModel rentModel = new RentModel();
        modelMapper.map(rentDto, rentModel);

        int rentalDays = rentUtils.calculateRentalDays(rentDto.getStart_date(), rentDto.getEnd_date());
        CarModel carModel = carService.findCarById(carId);
        rentModel.setCar(carModel);
        rentModel.setPrice(rentUtils.calculateRentAmount(carModel.getDaily_price(), rentalDays));
        UserModel userModel = userService.findUserById(rentDto.getUser_id());
        rentModel.setUser(userModel);
        rentModel.setStatus(RentStatusEnum.ACTIVE);

        return rentRepository.save(rentModel);
    }

    public RentModel updateRent(RentDto rentDto) {
        RentModel rentModel = findRentById(rentDto.getId());
        modelMapper.map(rentDto, rentModel);

        return rentRepository.save(rentModel);
    }

    public void cancelRent(UUID id) {
        RentModel rentModel = findRentById(id);
        rentModel.setStatus(RentStatusEnum.CANCELED);

        rentRepository.save(rentModel);
    }

    public void finishRent(UUID id) {
        RentModel rentModel = findRentById(id);
        rentModel.setStatus(RentStatusEnum.FINISHED);

        rentRepository.save(rentModel);
    }

    public void deleteRent(UUID id) {
        RentModel rentModel = findRentById(id);
        rentRepository.delete(rentModel);
    }

}
