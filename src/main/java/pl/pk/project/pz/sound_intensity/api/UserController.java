package pl.pk.project.pz.sound_intensity.api;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.pk.project.pz.sound_intensity.dao.UserRepo;
import pl.pk.project.pz.sound_intensity.dao.entity.User;

@RestController
public class UserController
{

    private UserRepo userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepo userRepo,BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.userRepo=userRepo;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    //login ujety przez springa

    @PostMapping("/sign-up")
    public void singUp(@RequestBody User user)
    {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @DeleteMapping("users/all")
    public void deleteAllUsers()
    {
        userRepo.deleteAll();
    }

    @GetMapping("users/{id}")
    public User findUserById(@PathVariable long id)
    {
        return userRepo.findUserById(id);
    }



}
