package com.example.msauserapitest.auth.controller;

import com.example.msauserapitest.auth.annotation.ApiFor;
import com.example.msauserapitest.auth.dto.LoginRequest;
import com.example.msauserapitest.auth.dto.TokenIssueResponse;
import com.example.msauserapitest.auth.service.LoginService;
import com.example.msauserapitest.user.enums.RoleType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {
    private final LoginService loginService;

    @ApiFor(roles = RoleType.ALL)
    @PostMapping("/login")
    public ResponseEntity<TokenIssueResponse> createUserToken(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(loginService.login(request));
    }

    @ApiFor(roles = RoleType.ALL)
    @PostMapping("/not-user-token")
    public ResponseEntity<TokenIssueResponse> createNotUserToken() {
        return ResponseEntity.ok(loginService.notUserToken());
    }

    @GetMapping("")
    public void welcome() {
        System.out.println("Ddddddddddddddddddd");
        System.out.println("Ddddddddddddddddddd");
        System.out.println("Ddddddddddddddddddd");
        System.out.println("Ddddddddddddddddddd");
        System.out.println("Ddddddddddddddddddd");
        System.out.println("Ddddddddddddddddddd");
        System.out.println("Ddddddddddddddddddd");
        System.out.println("Ddddddddddddddddddd");
        System.out.println("Ddddddddddddddddddd");
        System.out.println("Ddddddddddddddddddd");
    }
}
