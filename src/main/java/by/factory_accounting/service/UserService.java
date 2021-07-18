package by.factory_accounting.service;

import by.factory_accounting.entity.User;
import by.factory_accounting.entity.UserPrincipal;
import by.factory_accounting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("UserService")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean saveUser(User user){
        Optional<User> sUser = Optional.ofNullable(user);
        if(sUser.isPresent()){
            userRepository.save(sUser.get());
            return false;
        }
        throw new RuntimeException("User is invalid");

    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        User user  = userRepository.findByMail(mail).orElseThrow(()->
                new UsernameNotFoundException("User doesn't exists"));
        return  UserPrincipal.fromUser(user);
    }
}
