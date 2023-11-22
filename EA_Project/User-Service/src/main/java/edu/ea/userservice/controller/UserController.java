package edu.ea.userservice.controller;


import edu.ea.userservice.config.RabbitMQConfig;
import edu.ea.userservice.dto.AuthRequest;
import edu.ea.userservice.dto.UserDto;
import edu.ea.userservice.model.User;
import edu.ea.userservice.service.UserService;
import edu.ea.userservice.service.impl.JwtService;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.PUT;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    @Value("${alumni.rabbitmq.exchange}")
    private  String exchange;

    @Value("${student.routingkey}")
    private String studentRoutingkey;

    @Value("${job.routingkey}")
    private String jobRoutingkey;
    private final UserService userService;
    private final JwtService jwtService;
    private final  AuthenticationManager authenticationManager;

    private final RabbitTemplate rabbitTemplate;

    @GetMapping("/listAll")
    List<UserDto> findAllUsers()
    {
        System.out.println("All user api ");
        return  userService.getAllUsers();
    }
    @PostMapping("/activate/{id}")
    public void activateUser(@PathVariable Long id) throws Exception {
        userService.activateUser(id);
    }

    @PostMapping("/deactivate/{id}")
    public void deactivateUser(@PathVariable Long id) throws Exception {
        userService.deactivateUser(id);
    }

    @PostMapping("/reset/{id}")
    public void deactivateUser(@PathVariable Long id  , @RequestBody AuthRequest authRequest) throws Exception {
        userService.restPassword(id,authRequest);
    }

    @DeleteMapping("/remove/{id}")
    public void deleteUser(@PathVariable Long id) throws Exception {
        userService.deleteUser(id);
        rabbitTemplate.convertAndSend(exchange, studentRoutingkey, id);
        rabbitTemplate.convertAndSend(exchange, jobRoutingkey, id);
        System.out.println("delete user sent to queue" + id );
    }


    @PostMapping("/register")
    public UserDto addNewUser(@RequestBody UserDto user) {
        return userService.addUser(user);
    }
    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) throws Exception {
        System.out.println("Trying login " + authRequest.getUsername());
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            UserDto userByemail = userService.getEmail(authRequest.getUsername());
            String   role="";
            if(userByemail.getRole()!=null)
                role =userByemail.getRole().getName();
            return jwtService.generateToken(userByemail.getId(),authRequest.getUsername(), role);
        } else {
            throw new Exception("invalid access");
        }
    }




    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        jwtService.validateToken(token);
        return "Token is valid";
    }
}


