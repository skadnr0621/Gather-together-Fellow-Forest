package com.ssafy.modongmun.user;

import com.ssafy.modongmun.user.dto.SignupDto;
import com.ssafy.modongmun.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupDto signupDto) {
        UserDto savedUserDto = userService.signup(signupDto);
        return new ResponseEntity<>(savedUserDto, HttpStatus.OK);
    }

    @GetMapping("/user/users/{user_email}")
    public ResponseEntity<UserDto> getUser(@PathVariable("user_email") String userEmail) {
        UserDto userDto = userService.getUser(userEmail);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }

    @PatchMapping("/user/users/{user_id}")
    public ResponseEntity<UserDto> modifyUser(@PathVariable("user_id") Long userId, @RequestBody UserDto userDto) {
        UserDto modifiedUserDto = userService.modifyUser(userId, userDto);
        return new ResponseEntity<>(modifiedUserDto, HttpStatus.OK);
    }

}
