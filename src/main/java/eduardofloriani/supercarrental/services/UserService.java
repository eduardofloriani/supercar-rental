package eduardofloriani.supercarrental.services;

import eduardofloriani.supercarrental.dtos.UserDto;
import eduardofloriani.supercarrental.exceptions.MinimumAgeNotReachedException;
import eduardofloriani.supercarrental.exceptions.UserNotFoundException;
import eduardofloriani.supercarrental.models.UserModel;
import eduardofloriani.supercarrental.repositories.UserRepository;
import eduardofloriani.supercarrental.utils.UserUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserUtils userUtils;
    private static final ModelMapper modelMapper = new ModelMapper();

    public UserService(UserRepository userRepository, UserUtils userUtils) {
        this.userRepository = userRepository;
        this.userUtils = userUtils;
    }

    public List<UserModel> findAllUsers() {
        return userRepository.findAll();
    }

    public UserModel findUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public UserModel addUser(UserDto userDto) {
        int age = userUtils.calculateAge(userDto.getDate_of_birth());
        if (age < 21) {
            throw new MinimumAgeNotReachedException();
        }

        UserModel userModel = new UserModel();
        modelMapper.map(userDto, userModel);

        return userRepository.save(userModel);
    }

    public UserModel updateUser(UserDto userDto) {
        UserModel userModel = findUserById(userDto.getId());
        modelMapper.map(userDto, userModel);

        return userRepository.save(userModel);
    }

    public void deleteUser(UUID id) {
        UserModel userModel = findUserById(id);
        userRepository.delete(userModel);
    }
}
