package com.aloharoombackend.controller;

import com.aloharoombackend.auth.PrincipalDetails;
import com.aloharoombackend.dto.MyPageEditDto;
import com.aloharoombackend.dto.SignUpDto;
import com.aloharoombackend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //회원 가입
    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody SignUpDto signUpDto){
        return ResponseEntity.ok(userService.signUp(signUpDto));
    }

    //해시태그, 가전제품(like) 조회
    @GetMapping("/home")
    public ResponseEntity getUserInfo(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUser().getId();
        return ResponseEntity.ok(userService.findOneInfo(userId));
    }

    //username 중복 체크
    @GetMapping("/{username}")
    public ResponseEntity checkUsernameDuplicate(@PathVariable String username) {
        boolean check = userService.checkUsernameDuplicate(username);
        if(check)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        else
            return ResponseEntity.ok("");
    }

    //nickname 중복 체크
    @GetMapping("/{nickname}")
    public ResponseEntity checkNicknameDuplicate(@PathVariable String nickname) {
        boolean check = userService.checkNicknameDuplicate(nickname);
        if(check)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        else
            return ResponseEntity.ok("");
    }

    //회원 조회
    @GetMapping("/myPage")
    public ResponseEntity myPage(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUser().getId();
        return ResponseEntity.ok(userService.findUser(userId));
    }

    //회원 수정 초기 화면
    @GetMapping("/myPage/edit")
    public ResponseEntity myPageEditForm(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUser().getId();
        return ResponseEntity.ok(userService.findUserEdit(userId));
    }

    //회원 수정
    @PatchMapping(path = {"/myPage/edit"}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity myPageEdit(@RequestPart MyPageEditDto myPageEditDto,
                                    @RequestPart MultipartFile profileImg,
                                    @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUser().getId();
        return ResponseEntity.ok(userService.update(userId, myPageEditDto, profileImg));
    }

    //회원 탈퇴
    @DeleteMapping("/myPage")
    public ResponseEntity deleteUser(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUser().getId();
        return ResponseEntity.ok(userService.delete(userId));
    }

    /* 로그인 사용자 식별자 조회 */
    @GetMapping("/userId")
    public ResponseEntity findUserId(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Map<String, Long> map = new HashMap<>();
        map.put("loginUserId", principalDetails.getUser().getId());
        return ResponseEntity.ok(map);
    }
}















