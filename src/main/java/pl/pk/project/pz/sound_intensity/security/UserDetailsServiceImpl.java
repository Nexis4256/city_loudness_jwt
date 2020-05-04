package pl.pk.project.pz.sound_intensity.security;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.pk.project.pz.sound_intensity.dao.UserRepo;
//import pl.pk.project.pz.sound_intensity.dao.entity.User;

import static java.util.Collections.emptyList;


@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private UserRepo userRepo;

    public UserDetailsServiceImpl(UserRepo userRepository)
    {
        this.userRepo = userRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        pl.pk.project.pz.sound_intensity.dao.entity.User user  = userRepo.findByEmail(email);
        if (user == null)
        {
            throw new UsernameNotFoundException(email);
        }
        return new User(user.getEmail(),user.getPassword(),emptyList());
    }


}
