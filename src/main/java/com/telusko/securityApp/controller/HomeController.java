package com.telusko.securityApp.controller;

import com.telusko.securityApp.entity.User;
import com.telusko.securityApp.repository.UserRepository;
import com.telusko.securityApp.service.MyUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
public class HomeController {
    Logger lo = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private MyUserDetailsService userService;

    @PostMapping("/admin/addUser")
    public String addUser(@RequestBody User user) {
//        if (user.getUsername().equalsIgnoreCase(userRepo.findByUsername(user.getUsername()).toString())){
//            return "jishfisufc";
//        }
        User userss = userRepo.findByUsername(user.getUsername());
        if(userss !=null){
            return "Person already exists";
        }
        User users= userService.saveDetails(user);
        if (users.getId()>0){
            return "User is added succesfully";
        }

        return "ashdksjah";
//        return user.toString();
    }

    @PostMapping("/admin/authenticateUser")
    public String authenticateUser(@RequestBody User user){
         return userService.authenticateUser(user);
//        return user.toString();
    }
//    @PostMapping("/user/authenticateUser")
//    public String authenticatedUser(@RequestBody User user){
//         return userService.authenticatedUser(user);
//        return user.toString();
//    }

    @GetMapping("/admin/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable("id") String userid) {
        userService.deleteUser(userid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @RequestMapping("home")
    public String Home() {
        lo.info("inside home method");
//        userRepo.setId(101);


        if (userRepo != null && userRepo.toString() != null){
            return userRepo.toString();
        }
        return "home.jsp";

    }

    @GetMapping("hi")
    public String hi() {
        lo.info("inside hi method");
        return "home.jsp";
    }

 @GetMapping("his")
    public String his() {
        lo.info("inside hi method");

        return "home.jsp";
    }

//    @PutMapping("put")
//    public String encoder(@PathVariable int ids, @PathVariable String username, @PathVariable String password){
    @GetMapping("put")
    public String encoder(){
        Base64.Encoder encoder = Base64.getEncoder();
        String originalString = "String_valiadte";
//
        String encodedString = encoder.encodeToString(originalString.getBytes());
        System.out.println("Encrypted Value :: "+ encodedString);
        lo.info("inside encode method:" );
//        userRepo.setPassword(encodedString);
//        userRepo.setUsername("qwertyui");
//        return encodedString;
//        userRepository.save(userRepo);
        return "userRepo";
    }


}
