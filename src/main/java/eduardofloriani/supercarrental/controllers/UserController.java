package eduardofloriani.supercarrental.controllers;

import eduardofloriani.supercarrental.dtos.UserDto;
import eduardofloriani.supercarrental.models.UserModel;
import eduardofloriani.supercarrental.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserModel>> findAll() {
        List<UserModel> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserModel> findById(@PathVariable UUID id) {
        UserModel user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/add")
    public ResponseEntity<UserModel> add(@RequestBody @Valid UserDto userDto) {
        UserModel user = userService.addUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/update")
    public ResponseEntity<UserModel> update(@RequestBody @Valid UserDto userDto) {
        UserModel user = userService.updateUser(userDto);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
