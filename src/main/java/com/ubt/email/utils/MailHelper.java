package com.ubt.email.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Component
public class MailHelper {

    private static final String DEFAULT_ENCODING = "UTF-8";

    @Value("${todo.confirm.user.url}")
    private String confirmUserURL;
    @Value("${todo.reset.password.url}")
    private String resetPasswordURL;
    @Value("${spring.mail.sender}")
    private String senderEmail;

    public Mail prepareConfirmUserEmail(String email, String confirmToken, String username) throws UnsupportedEncodingException {
        String constructedConfirmUrl = confirmUserURL + URLEncoder.encode(confirmToken, DEFAULT_ENCODING);
        Map<String, Object> properties = new HashMap<>();
        properties.put("username", username);
        properties.put("confirmUserUrl", constructedConfirmUrl);

        return Mail.builder()
                .from(senderEmail)
                .to(email)
                .htmlTemplate(new Mail.HtmlTemplate("User_Confirmation", properties))
                .subject("User confirmation for " + username)
                .build();
    }

    public Mail prepareResetPasswordEmail(String email, String username, String resetToken) throws UnsupportedEncodingException {
        String constructedPasswordResetLink = resetPasswordURL + URLEncoder.encode(resetToken, DEFAULT_ENCODING);
        Map<String, Object> properties = new HashMap<>();
        properties.put("username", username);
        properties.put("passwordResetLink", constructedPasswordResetLink);

        return Mail.builder()
                .from(senderEmail)
                .to(email)
                .htmlTemplate(new Mail.HtmlTemplate("Reset_Password", properties))
                .subject("Reset password for " + username)
                .build();
    }
}
