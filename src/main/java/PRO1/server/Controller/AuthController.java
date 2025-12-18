package PRO1.server.Controller;

import PRO1.server.DTO.LoginRequest;
import PRO1.server.DTO.RegisterRequest;
import PRO1.server.Model.User;
import PRO1.server.Repository.UserRepository;
import PRO1.server.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // 1. Chercher l'utilisateur par email
        return userRepository.findByEmail(request.getEmail())
                .map(user -> {
                    // 2. Vérifier si le mot de passe correspond
                    if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                        // Ici, on retourne l'utilisateur (ou un token JWT plus tard)
                        return ResponseEntity.ok(user);
                    } else {
                        return ResponseEntity.status(401).body("Mot de passe incorrect");
                    }
                })
                .orElse(ResponseEntity.status(404).body("Utilisateur non trouvé"));
    }
        public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
            try {
                User savedUser = userService.registerNewUser(request);
                return ResponseEntity.ok("Compte créé avec succès pour " + savedUser.getEmail());
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
}