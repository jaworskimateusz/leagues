package pl.jaworskimateuszm.myleagues.controller;

import org.springframework.web.bind.annotation.*;
import pl.jaworskimateuszm.myleagues.mapper.UserMapper;
import pl.jaworskimateuszm.myleagues.model.User;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<User> getAll() {
        return userMapper.findAll();
    }

    @GetMapping("/username/{username}")
    public User getOne(@PathVariable String username) {
        return userMapper.findByUsername(username);
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        userMapper.insert(user);
    }
}
