package PRO1.server.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {

    @GetMapping("/")
    public String HelloWorld(){
        return "Hello ";
    }

}
