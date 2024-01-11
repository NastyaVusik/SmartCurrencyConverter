package by.example.smartcurrencyconverter.web.controller;

import by.example.smartcurrencyconverter.dto.userDTO.LoginUserDTO;
import by.example.smartcurrencyconverter.dto.userDTO.RegistrationUserDTO;
import by.example.smartcurrencyconverter.dto.userDTO.UpdateUserDTO;
import by.example.smartcurrencyconverter.entity.Role;
import by.example.smartcurrencyconverter.entity.User;
import by.example.smartcurrencyconverter.mapper.GeneralMapper;
import by.example.smartcurrencyconverter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class userController {

    private final UserService userService;
    private final GeneralMapper generalMapper;

    @PostMapping("/registration")
    public ResponseEntity<User> registration(@Validated @RequestBody RegistrationUserDTO registrationUserDTO) {

        User user = userService.save(generalMapper.mapToUser(registrationUserDTO));
        user.setJoinedDate(LocalDate.now());

        return ResponseEntity.ok(user);
    }


    @PostMapping("/login")
    public ResponseEntity<User> login(@Validated @RequestBody LoginUserDTO loginUserDTO) {

        //Code with sequrity will be here
        Optional<User> userByUsername = userService.findByUsername(loginUserDTO.getUsername());
        if (userByUsername.isPresent()) {
            if (loginUserDTO.getUsername().equals(userByUsername.get().getPassword())) {
                return ResponseEntity.ok(userByUsername.get());
            }
        }

        return ResponseEntity.badRequest().build();

    }


    @PutMapping("/{id}/update")
    public ResponseEntity<User> update(@Validated @RequestBody UpdateUserDTO updateUserDTO) {

        User user = userService.update(generalMapper.mapToUser(updateUserDTO));

        return ResponseEntity.ok(user);
    }



    @DeleteMapping("/{id}/delete")
    public ResponseEntity<User> deleteById(@PathVariable(name = "userId") Long id){
        if(userService.findById(id).isPresent()){
            userService.deleteById(id);
        }

        return ResponseEntity.ok().build();

    }


}
