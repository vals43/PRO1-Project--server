package PRO1.server.Controller;

import PRO1.server.DTO.LoginRequest;
import PRO1.server.DTO.RegisterRequest;
import PRO1.server.Model.User;
import PRO1.server.Repository.UserRepository;
import PRO1.server.Service.UserService;
import PRO1.server.web.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserRepository userRepository; // Instance injectée

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return userRepository.findByEmail(request.getEmail())
                .map(user -> {
                    if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                        String token = jwtUtils.generateToken(user.getEmail());

                        Map<String, String> response = new HashMap<>();
                        response.put("token", token);
                        response.put("name", user.getName());
                        response.put("id" , String.valueOf(user.getUser_id()));

                        return ResponseEntity.ok(response);
                    }
                    return ResponseEntity.status(401).body("Identifiants invalides");
                })
                .orElse(ResponseEntity.status(404).body("Utilisateur non trouvé"));
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            User savedUser = userService.registerNewUser(request);

            Map<String, Object> response = new HashMap<>();
            response.put("user", savedUser);
            response.put("message", "Compte créé avec succès");

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            // Erreur -> renvoie message d'erreur en JSON
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

}