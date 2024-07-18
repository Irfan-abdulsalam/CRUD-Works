package com.My_projects.CRUD_works_application.service;

import com.My_projects.CRUD_works_application.entity.CrudEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${notification.email.recipient}")
    private String notificationEmailRecipient;

    @Value("${spring.mail.username}")
    private String notificationEmailSender;

    public void sendCrudOperationNotification(String subject, CrudEntity crudEntity) {
        try {
            String emailContent = generateCrudOperationContent(crudEntity);
            sendEmail(notificationEmailRecipient, subject, emailContent);
        } catch (Exception e) {
            logger.error("Failed to send CRUD operation notification: {}", e.getMessage(), e);
        }
    }

    private String generateCrudOperationContent(CrudEntity crudEntity) {
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("CRUD Operation Details:<br><br>");
        emailContent.append("ID: ").append(crudEntity.getId()).append("<br>");
        emailContent.append("Name: ").append(crudEntity.getName()).append("<br>");
        emailContent.append("Email: ").append(crudEntity.getEmail()).append("<br>");
        emailContent.append("Mobile: ").append(crudEntity.getMobile()).append("<br>");
        emailContent.append("Address: ").append(crudEntity.getAddress()).append("<br>");
        emailContent.append("Operation Type: ").append(crudEntity.getOpType()).append("<br><br>");
        emailContent.append("Date & Time: ").append(new java.util.Date()).append("<br><br>");
        emailContent.append("Regards,<br>CRUD Works Application Team.");
        return emailContent.toString();
    }

    private void sendEmail(String recipient, String subject, String emailContent) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(notificationEmailSender, "CRUD Works Application");
            helper.setTo(recipient);
            helper.setSubject(subject);
            helper.setText(emailContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            logger.error("Failed to send email: {}", e.getMessage(), e);
        }
    }
}
