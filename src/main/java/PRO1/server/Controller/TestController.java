package PRO1.server.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController  {

    @GetMapping("/hello")
    public String hello() {
        return "Serveur dynamique OK !";
    }

    @GetMapping({"/message:{name}" , "/message"})
    public String message(@PathVariable(required = false) String name) {
        if (name == null) {
            return "Hello World!";
        }else {
            return "Hello " + name;
        }
    }

}
