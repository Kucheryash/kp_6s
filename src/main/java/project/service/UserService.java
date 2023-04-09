package project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.entity.Key;
import project.repository.KeyRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final KeyRepository keyRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean register(Key key){
        String login = key.getLogin();
        if (keyRepository.findByLogin(login)!=null)
            return false;
        key.setPassword(passwordEncoder.encode(key.getPassword()));
        log.info("Регистрация нового пользователя: '"+ login+"'.");
        keyRepository.save(key);
        return true;
    }
}
