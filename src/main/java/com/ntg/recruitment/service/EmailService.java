package com.ntg.recruitment.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendExamEmail(String toEmail, String candidateName, String examLink) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("Technical Assessment Invitation - NTG Clarity");

            // نص الإيميل بتنسيق HTML احترافي
            String htmlContent =
                    "<div style='font-family: Arial, sans-serif; line-height: 1.6; color: #333;'>" +
                            "<h2>Dear Candidate ,</h2>" +
                            "<p>Congratulations! You have been selected to move forward in our recruitment process.</p>" +
                            "<p>As part of the next step, please complete the technical assessment via the link below:</p>" +
                            "<div style='margin: 20px 0;'>" +
                            "   <a href='" + examLink + "' style='background-color: #008080; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px;'>Start Assessment</a>" +
                            "</div>" +
                            "<p>If the button doesn't work, copy and paste this link: <br>" + examLink + "</p>" +
                            "<br><p>Best regards,<br><strong>NTG Recruitment Team</strong></p>" +
                            "</div>";

            helper.setText(htmlContent, true); // true تعني أن النص هو HTML
            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }
    }
}
