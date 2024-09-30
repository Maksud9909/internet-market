package uz.ccrew.internetmarket.security.user;


import uz.ccrew.internetmarket.entity.User;
import uz.ccrew.internetmarket.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findByLogin(login.toLowerCase());
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new UserDetailsImpl(optional.get());
    }
}