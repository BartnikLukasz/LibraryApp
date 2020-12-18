package library.system.client;

import library.system.client.models.User;
import library.system.client.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="client")
public class Controller {

    @Autowired
    UserRepository userRepository;

    @PostMapping(path="/register")
    public @ResponseBody void register(@RequestParam String name, @RequestParam String username,
                                       @RequestParam String email, @RequestParam String password){
        User tempUser = new User();
        tempUser.setName(name);
        tempUser.setUsername(username);
        tempUser.setEmail(email);
        tempUser.setUserType(0);
        tempUser.setPassword(User.generateHash(password));

        userRepository.save(tempUser);
    }

    @PostMapping(path="/login")
    public @ResponseBody Boolean login(@RequestParam String username, @RequestParam String password){

        Boolean authenticated = false;

        String hashedPassword = User.generateHash(password);
        User matchedUser = userRepository.findByUsername(username);
        if(matchedUser!=null){
            if(hashedPassword.equals(matchedUser.getPassword())){
                authenticated = true;
            }
        }
        return authenticated;
    }

}
