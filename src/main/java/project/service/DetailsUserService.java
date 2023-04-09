package project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.repository.KeyRepository;

@Service
@RequiredArgsConstructor
public class DetailsUserService implements UserDetailsService {
    private final KeyRepository keyRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return keyRepository.findByLogin(login);
    }
}
