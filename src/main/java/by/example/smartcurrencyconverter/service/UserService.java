package by.example.smartcurrencyconverter.service;

import by.example.smartcurrencyconverter.configuration.security.UserPrincipal;
import by.example.smartcurrencyconverter.entity.Currency;
import by.example.smartcurrencyconverter.entity.Role;
import by.example.smartcurrencyconverter.entity.User;
import by.example.smartcurrencyconverter.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.apache.logging.log4j.core.net.Priority;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRoles()==null)
        {
            user.setRoles(new HashSet<>());
        }
        user.getRoles().add(Role.USER);
        user.setJoinedDate(LocalDate.now());

        return userRepository.save(user);

    }


    public void deleteById(Long id){
        userRepository.findById(id).orElseThrow(RuntimeException::new);

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
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "username"));
    }


    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username){

        return Optional.of(userRepository.findByUsername(username).orElseThrow());
    }


    public User update(User user){
        user = userRepository.findById(user.getId()).orElseThrow();

        return userRepository.save(user);
    }


//    public Map<Long, Currency> getLovelyCurrencies(User user, Currency currency) {
//        Map<Long, Currency> lovelyCorencies = userRepository.findById(user.getId()).get().getLovelyCurrencies();
//        int size = 6;
//
//        if (lovelyCorencies.size() < size) {
//            lovelyCorencies.put(user.getId(), currency);
//        }
//
//        return lovelyCorencies;
//    }


    public Set<Currency> addToLovelyCurrencies(User user, Currency fromCurrency, Currency toCurrency) {
        Set<Currency> lovelyCurrencies = userRepository.findById(user.getId()).get().getLovelyCurrencies();
        int limit = 6;

        if(lovelyCurrencies.size() <= (limit - 2)){
            lovelyCurrencies.add(fromCurrency);
            lovelyCurrencies.add(toCurrency);
        }

         if (lovelyCurrencies.size() == limit) {
             lovelyCurrencies.remove(lovelyCurrencies.stream().iterator().next());
             lovelyCurrencies.remove(lovelyCurrencies.stream().iterator().next());
             lovelyCurrencies.add(fromCurrency);
             lovelyCurrencies.add(toCurrency);
         }

        return lovelyCurrencies;
    }


    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow();

        UserPrincipal userPrincipal = UserPrincipal.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .birthdayDate(user.getBirthdayDate())
                .country(user.getCountry())
                .roles(user.getRoles())
                .build();

        return userPrincipal;
    }


    public void assignRoleToUser(User user, Role role){
        user.getRoles().add(role);
    }

}
