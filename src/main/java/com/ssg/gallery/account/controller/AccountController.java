package com.ssg.gallery.account.controller;

import com.ssg.gallery.account.dto.AccountJoinRequest;
import com.ssg.gallery.account.dto.AccountLoginRequest;
import com.ssg.gallery.account.helper.AccountHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AccountController {
    private final AccountHelper accountHelper;

    @PostMapping("/api/account/join")
    public ResponseEntity<?> join(@RequestBody AccountJoinRequest request) {
        if (!StringUtils.hasLength(request.getName()) ||
                !StringUtils.hasLength(request.getLoginId()) ||
                !StringUtils.hasLength(request.getLoginPw())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        accountHelper.join(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/account/login")
    public ResponseEntity<?> login(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestBody AccountLoginRequest loginRequest) {
        if (!StringUtils.hasLength(loginRequest.getLoginId()) ||
                !StringUtils.hasLength(loginRequest.getLoginPw())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String output = accountHelper.login(loginRequest,request,response);
        if (output == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/account/logout")
    public ResponseEntity<?> logout(HttpServletRequest request,
                                    HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        accountHelper.logout(request, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/account/check")
    public ResponseEntity<?> check(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        boolean isLoggedIn = accountHelper.isLoggedIn(request);
        if (!isLoggedIn){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
