package com.ubt.email.controller;

import com.ubt.email.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping
@Slf4j
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/confirm")
    public void sendConfirmUserEmail(@RequestParam String email,
                               @RequestParam String confirmToken,
                               @RequestParam String username) throws Exception {
        log.info("Inside sendConfirmUserEmail method of EmailController");
        emailService.sendConfirmUserEmail(email, confirmToken, username);
    }

    @PostMapping("/forgot-password")
    public void sendForgotPasswordEmail(@RequestParam String email,
                                   @RequestParam String resetToken,
                                   @RequestParam String username) throws Exception {
        log.info("Inside sendForgotPasswordEmail method of EmailController");
        emailService.sendForgotPasswordEmail(email, resetToken, username);
    }
}
