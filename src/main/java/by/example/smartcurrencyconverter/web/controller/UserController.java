package by.example.smartcurrencyconverter.web.controller;

import by.example.smartcurrencyconverter.dto.userDTO.LoginUserDTO;
import by.example.smartcurrencyconverter.dto.userDTO.RegistrationUserDTO;
import by.example.smartcurrencyconverter.dto.userDTO.UpdateUserDTO;
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
public class UserController {

    private final UserService userService;
    private final GeneralMapper generalMapper;
    //    private final static org.slf4j.Logger log = LoggerFactory.getLogger(ConverterController.class);


    @PostMapping("/registration")
    public ResponseEntity<User> registration(@Validated @RequestBody RegistrationUserDTO registrationUserDTO) {

        User user = userService.save(generalMapper.mapToUser(registrationUserDTO));

        log.info("Save user with name: " + user.getName());

        user.setJoinedDate(LocalDate.now());

        log.info("User was saved in " + user.getJoinedDate());

        return ResponseEntity.ok(user);
    }


    @PostMapping("/login")
    public ResponseEntity<User> login(@Validated @RequestBody LoginUserDTO loginUserDTO) {

        log.info("Find user in database with username: " + loginUserDTO.getUsername());

        //Code with sequrity will be here
        Optional<User> userByUsername = userService.findByUsername(loginUserDTO.getUsername());
        if (userByUsername.isPresent()) {

            log.info("User with username " + userByUsername.get().getUsername() + " was found.");
            log.info("Check user password.");

            if (loginUserDTO.getUsername().equals(userByUsername.get().getPassword())) {

               log.info("Login was completed. User id = " + userByUsername.get().getId());

                return ResponseEntity.ok(userByUsername.get());
            }
        }

        log.info("Username " + loginUserDTO.getUsername() + " or password is wrong.");

        return ResponseEntity.badRequest().build();

    }


    @PutMapping("/{id}/update")
    public ResponseEntity<User> updateById(@Validated @RequestBody UpdateUserDTO updateUserDTO,
                                       @PathVariable(name = "userId") Long id) {

        log.info("Update user with id = " + id);

        User user = userService.update(generalMapper.mapToUser(updateUserDTO));

        return ResponseEntity.ok(user);
    }



    @DeleteMapping("/{id}/delete")
    public ResponseEntity<User> deleteById(@PathVariable(name = "userId") Long id){
        if(userService.findById(id).isPresent()){

            log.info("Delete user, because id = " + id + " was found.");

            userService.deleteById(id);
        }

        return ResponseEntity.ok().build();

    }


}
