package by.factory_accounting.service;

import by.factory_accounting.entity.User;
import by.factory_accounting.entity.UserPrincipal;
import by.factory_accounting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        User user = userRepository.findByMail(mail);
        if(user == null){
            throw  new UsernameNotFoundException(mail);
        }
        return new UserPrincipal(user);

    }
}
