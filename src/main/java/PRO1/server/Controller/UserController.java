package PRO1.server.Controller;

import PRO1.server.DTO.RegisterRequest;
import PRO1.server.Model.User;
import PRO1.server.Repository.UserRepository;
import PRO1.server.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable int id) {
        return userRepository.getReferenceById(id);
    }

    @PostMapping("/add")
    public User addUser(@RequestBody RegisterRequest request) {
        return userService.registerNewUser(request);
    }


    @DeleteMapping("/del/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

}
