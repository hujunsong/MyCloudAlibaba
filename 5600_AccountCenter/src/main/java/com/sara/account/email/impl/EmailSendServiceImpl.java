package com.sara.account.email.impl;

import com.sara.account.email.EmailSendService;
import com.sara.account.model.EMailCmd;
import com.sara.account.model.RegisterRedisCacheDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.util.DateUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Locale;

/**
 * 发送邮件
 *
 * @author: hujunsong
 * @date: 2023/4/3 14:10
 */
@Component("emailSendService")
public class EmailSendServiceImpl implements EmailSendService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private TemplateEngine templateEngine;

    /**
     * 发送简单邮件
     *
     * @param eMailCmd
     * @return : void
     * @author: hujunsong
     * @date: 2023/4/3 15:37
     */
    public void sendEMail(EMailCmd eMailCmd) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("m18611715735@163.com"); //设置发送邮件账号
            simpleMailMessage.setTo(eMailCmd.getTos()); //设置接收邮件的人，可以多个
            simpleMailMessage.setSubject(eMailCmd.getSubject()); //设置发送邮件的主题
            simpleMailMessage.setText(eMailCmd.getText()); //设置发送邮件的内容
            mailSender.send(simpleMailMessage);
        } catch (MailException e) {
            logger.error("邮件发送失败!");
        }
    }

    /**
     * 发送用户注册验证码邮件
     *
     * @param registerRedisCacheDomain
     * @return : void
     * @author: hujunsong
     * @date: 2023/4/3 15:37
     */
    public void sendRegisterCodeEmail(RegisterRedisCacheDomain registerRedisCacheDomain) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper mineHelper = new MimeMessageHelper(message, true);
            mineHelper.setFrom("m18611715735@163.com"); //设置发送邮件账号
            mineHelper.setTo(registerRedisCacheDomain.getEmail()); //设置接收邮件的人，可以多个
            mineHelper.setSubject("Sara商城注册验证码"); //设置发送邮件的主题

            String text = this.getCaptchaTemplate(registerRedisCacheDomain.getEmail(), registerRedisCacheDomain.getCode(), 5);
            mineHelper.setText(text, true); //设置发送邮件的内容 第二个设置为true则可以发送带HTML的邮件
            mailSender.send(message);
        } catch (MessagingException e) {
            logger.error("邮件发送失败!");
        }
    }

    /**
     * 获得验证码模板
     *
     * @param email   用户邮箱
     * @param code    验证码
     * @param timeout 超时时间
     * @return
     */
    private String getCaptchaTemplate(String email, String code, int timeout) {
        Context context = new Context();
        //设置模板所需的参数
        context.setVariable("title", "验证码");
        context.setVariable("email", email);
        context.setVariable("code", code);
        context.setVariable("timeout", timeout);
        context.setVariable("date", DateUtils.format(new Date(),
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault()));
        //通过模板类将动态参数传入HTML模板,并返回模板内容 参数一:模板名字，参数二：动态参数Web文本
        String content = templateEngine.process("/MailCode", context);
        return content;
    }
}
