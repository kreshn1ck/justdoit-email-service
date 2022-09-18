package com.ubt.email.service;

import com.ubt.email.utils.Mail;
import com.ubt.email.utils.MailHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EmailService {

    private final EmailSenderService emailSenderService;
    private final MailHelper mailHelper;

    public boolean sendConfirmUserEmail(String email, String confirmToken, String username) throws Exception {
        try {
            Mail mail = mailHelper.prepareConfirmUserEmail(email, confirmToken, username);
            emailSenderService.sendEmail(mail);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void sendForgotPasswordEmail(String email, String resetToken, String username) throws Exception {
        Mail mail = mailHelper.prepareResetPasswordEmail(email, resetToken, username);
        emailSenderService.sendEmail(mail);
    }
}
