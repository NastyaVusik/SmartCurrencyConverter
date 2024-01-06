package by.example.smartcurrencyconverter.service;

import by.example.smartcurrencyconverter.entity.User;
import by.example.smartcurrencyconverter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;


    public User save(User user){
        user.setJoinedDate(LocalDate.now());

        return userRepository.save(user);

    }


    public void deleteById(Long id){
        userRepository.findById(id).orElseThrow(RuntimeException::new);     //????????????????????????????????

        userRepository.deleteById(id);

    }


    public User delete(User user){
     userRepository.delete(user);

        return user;

    }


    @Transactional(readOnly = true)
    public Optional<User> findById(Long id){
        Optional<User> userById = Optional.of(userRepository.findById(id).orElseThrow());

        return userById;

    }


    @Transactional(readOnly = true)
    public List<User> findAll(){
        return userRepository.findAll(Sort.by(Sort.Direction.ASC));     //?????????????????????????????????
    }


    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username){

        return Optional.of(userRepository.findByUsername(username).orElseThrow());
    }


    public User update(User user){
        user = userRepository.findById(user.getId()).orElseThrow();

        return userRepository.save(user);
    }



}
