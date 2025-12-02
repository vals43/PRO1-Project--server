package PRO1.server.Controller;

import PRO1.server.Model.Users;
import PRO1.server.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/id/{id}")
    public Users getUserById(@PathVariable int id) {
        return userRepository.getReferenceById(id);
    }

    @PostMapping("/add")
    public Users addUser(@RequestBody Users user) {
        return userRepository.save(user);
    }


    @DeleteMapping("/del/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }


}
