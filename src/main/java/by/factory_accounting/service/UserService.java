package by.factory_accounting.service;

import by.factory_accounting.entity.user.User;
import by.factory_accounting.entity.user.UserSecurityDetails;
import by.factory_accounting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("userService")
public class UserService implements UserDetailsService {

    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository =userRepository;
    }

    @Override
    //метод для авторизации
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User doesn't exist "));
        return UserSecurityDetails.fromUser(user);
    }

    public User saveUser(User user){
        String newPassword = passwordEncoder().encode(user.getPassword());
        user.setPassword(newPassword);
        return userRepository.save(user);
    }

    public List<User> allUsers(){
        return userRepository.findAll();
    }

    public User findUserById(long id){
        return userRepository.getById(id);
    }

    public Optional<User> findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

}
