package eduardofloriani.supercarrental.services;

import eduardofloriani.supercarrental.dtos.UserDto;
import eduardofloriani.supercarrental.exceptions.UserNotFoundException;
import eduardofloriani.supercarrental.models.UserModel;
import eduardofloriani.supercarrental.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private static final ModelMapper modelMapper = new ModelMapper();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> findAllUsers() {
        return userRepository.findAll();
    }

    public UserModel findUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public UserModel addUser(UserDto userDto) {
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
