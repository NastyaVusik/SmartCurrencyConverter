package by.example.smartcurrencyconverter.web.controller;

import by.example.smartcurrencyconverter.configuration.security.JwtTokenProvider;
import by.example.smartcurrencyconverter.configuration.security.UserPrincipal;
import by.example.smartcurrencyconverter.dto.userDTO.GetUserDTO;
import by.example.smartcurrencyconverter.dto.userDTO.LoginUserDTO;
import by.example.smartcurrencyconverter.dto.userDTO.RegistrationUserDTO;
import by.example.smartcurrencyconverter.dto.userDTO.UpdateUserDTO;
import by.example.smartcurrencyconverter.entity.Role;
import by.example.smartcurrencyconverter.entity.User;
import by.example.smartcurrencyconverter.mapper.GeneralMapper;
import by.example.smartcurrencyconverter.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;



@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final GeneralMapper generalMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;
    //    private final static org.slf4j.Logger log = LoggerFactory.getLogger(ConverterController.class);


    @GetMapping("/registration")
    public ModelAndView registration(Model model) {
        model.addAttribute("registrationUserDTO", new RegistrationUserDTO());
//        return "user-registration";

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("user-registration.html");

        return modelAndView;
    }



    @PostMapping("/registration")
    public ModelAndView registration(@ModelAttribute("registrationUserDTO") RegistrationUserDTO registrationUserDTO) {

        User user = userService.save(generalMapper.mapToUser(registrationUserDTO));

        log.info("Save user with name: " + user.getName());

        user.setJoinedDate(LocalDate.now());

        log.info("User was saved in " + user.getJoinedDate());

        // Redirect to the login page
        return new ModelAndView("redirect:/user/login");
    }


    @GetMapping("/login")
    public ModelAndView login(Model model) {
        model.addAttribute("loginUserDTO", new LoginUserDTO());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user-login.html");
        return modelAndView;
    }


    @PostMapping("/signin")
    public ModelAndView login( @ModelAttribute("loginUserDTO") LoginUserDTO loginUserDTO, HttpServletResponse res) {

        log.info("Find user in database with username: " + loginUserDTO.getUsername());

        UserPrincipal userPrincipal = (UserPrincipal) userService.loadUserByUsername(loginUserDTO.getUsername());

        if(passwordEncoder.matches(loginUserDTO.getPassword(), userPrincipal.getPassword())){
            Set<Role> roles = (Set<Role>) userPrincipal.getAuthorities();
            generalMapper.mapToUser(loginUserDTO).setLastVisitDate(LocalDate.now());

            String token = jwtTokenProvider.generateToken(userPrincipal.getUsername(), userPrincipal.getPassword(), roles);

            log.info("Login was completed. User's name is " + userPrincipal.getName());
            Authentication token1 = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(token1);
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60 * 60 * 24 * 365);
            cookie.setPath("/");
            
            res.addCookie(cookie);
            return new ModelAndView("redirect:/");
        }

        log.info("Username " + loginUserDTO.getUsername() + " or password is wrong.");

        return new ModelAndView("redirect:/user/login");
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

    @GetMapping("/logout")
    public ModelAndView logout( HttpServletResponse res, HttpServletRequest req){

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null)
            {
                new SecurityContextLogoutHandler().logout(req, res, auth);
                Cookie cookie = new Cookie("token", null);
                cookie.setHttpOnly(true);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                res.addCookie(cookie);
            }
        return new ModelAndView("redirect:/");
    }
    
//    @GetMapping("/{id}/logout")
//    public String logout(@PathVariable(name = "userId") Long id, @Validated GetUserDTO getUserDTO){
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if(auth != null){
//            new SecurityContextLogoutHandler().;
//        }
//        return "redirect:home";
//    }



}
