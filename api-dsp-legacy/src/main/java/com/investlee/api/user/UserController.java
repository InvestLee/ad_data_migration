package com.investlee.api.user;

import com.investlee.core.user.UserCreateCommand;
import com.investlee.core.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/v1")
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public UserResponse create(@RequestBody UserCreateRequest request) {
        return UserResponse.from(
                userService.create(
                        new UserCreateCommand(request.name())
                )
        );
    }

    @GetMapping("/{id}")
    public UserResponse find(@PathVariable("id") Long id) {
        return UserResponse.from(userService.find(id));
    }

    @PatchMapping("/name")
    public UserResponse updateName(@RequestBody UserUpdateNameRequest request) {
        return UserResponse.from(userService.updateName(
                request.id(),
                request.name()));
    }

    @DeleteMapping("/{id}")
    public UserResponse delete(@PathVariable("id") Long id) {
        return UserResponse.from(userService.delete(id));
    }
}
