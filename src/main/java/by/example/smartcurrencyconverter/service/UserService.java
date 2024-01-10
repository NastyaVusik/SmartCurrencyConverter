package by.example.smartcurrencyconverter.service;

import by.example.smartcurrencyconverter.entity.Currency;
import by.example.smartcurrencyconverter.entity.User;
import by.example.smartcurrencyconverter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
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
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "username"));     //?????????????????????????????????
    }


    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username){

        return Optional.of(userRepository.findByUsername(username).orElseThrow());
    }


    public User update(User user){
        user = userRepository.findById(user.getId()).orElseThrow();

        return userRepository.save(user);
    }


    public Map<Long, Currency> getLovelyCurrencies(User user, Currency currency) {
        Map<Long, Currency> lovelyCorencies = userRepository.findById(user.getId()).get().getLovelyCurrencies();
        int size = 6;

        if (lovelyCorencies.size() < size) {
            lovelyCorencies.put(user.getId(), currency);
        }

        return lovelyCorencies;
    }

}
