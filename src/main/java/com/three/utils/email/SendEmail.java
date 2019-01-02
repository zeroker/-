package com.three.utils.email;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class SendEmail {

    // 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
    // PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）,
    //     对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
    private static Logger logger = LogManager.getLogger(SendEmail.class);

    public static String myEmailAccount = "15610300395@163.com";
    public static String myEmailPassword = "zeroker155288";

    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
    // 网易126邮箱的 SMTP 服务器地址为: smtp.126.com

    public static String myEmailSMTPHost = "smtp.163.com";



    public static  void send(String receiveMailAccount,String idcode){

            System.out.println("send开始了------------------------");
            // 1. 创建参数配置, 用于连接邮件服务器的参数配置
            Properties props = new Properties();                    // 参数配置
            props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
            props.setProperty("mail.smtp.auth", "true");            // 需要请求认证



            // 465 SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
            //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
//            //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)

        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);


            // 2. 根据配置创建会话对象, 用于和邮件服务器交互
            Session session = Session.getInstance(props);
            // 设置为debug模式, 可以查看详细的发送 log
            session.setDebug(true);

            // 3. 创建一封邮件


            try{
                MimeMessage message = createMimeMessage(session,myEmailAccount,receiveMailAccount,idcode);
                Transport transport = session.getTransport();
                transport.connect(myEmailAccount, myEmailPassword);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();


            }catch(Exception e){
                logger.info("send error  出了错误");
                e.printStackTrace();
            }





    }


    public static MimeMessage createMimeMessage(Session session, String sendMail,
                                                String receiveMail,String idcode) throws Exception {


        logger.info("填写邮箱开始了！！！ its start!");
        //  创建邮件
        MimeMessage message = new MimeMessage(session);


        // 发件人
        message.setFrom(new InternetAddress(sendMail, "zeroker", "UTF-8"));

        // 收件人（可以增加多个收件人、抄送、密送）

        message.setRecipient(MimeMessage.RecipientType.TO,
                new InternetAddress(receiveMail, receiveMail, "UTF-8"));


        //  邮件主题
        message.setSubject("验证码信息", "UTF-8");

//        message.setContent("【智慧校园】您的验证码是："+idcode+"请及时输入。","text/html;charset=gb2312");
        //  邮件正文（可以使用html标签）
        message.setContent("【网上书城】您的验证码是："+idcode+"请及时输入。", "text/html;charset=UTF-8");



        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置

        message.saveChanges();

        return message;
    }

}
