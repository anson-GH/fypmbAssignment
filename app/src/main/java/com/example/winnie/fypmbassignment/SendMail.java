package com.example.winnie.fypmbassignment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail extends AsyncTask<String, String, String> {

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    //Declaring Variables
    private Context context;
    private Session session;
    //Information to send email
    private String email;
    private String subject;
    private String message;
    //Progressdialog to show while sending email
    private ProgressDialog progressDialog;

    //Class Constructor
    public SendMail(Context context, String email, String subject, String message) {
        //Initializing variables
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Showing progress dialog while sending email
        progressDialog = ProgressDialog.show(context, "Sending message", "Please wait...", false, false);
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
        //Dismissing the progress dialog
        progressDialog.dismiss();


        Toast.makeText(context, "Message Sent", Toast.LENGTH_LONG).show();


    }

    @Override
    protected String doInBackground(String... params) {

        //Creating properties
        Properties props = new Properties();

        //Configuring properties for gmail
        //If you are not using gmail you may need to change the values
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        //Creating a new session
        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    //Authenticating the password
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("sampletaruc@gmail.com", "tarucollege");
                    }
                });

        try {
            //Creating MimeMessage object
            MimeMessage mm = new MimeMessage(session);

            //Setting sender address
            mm.setFrom(new InternetAddress("sampletaruc@gmail.com"));
            //Adding receiver
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            //Adding subject
            mm.setSubject(subject);

            //Adding message
            mm.setContent("<span style=\"color:#0078D7;font-size:40px\">Password recovery</span><br/><br/>Hello " + message + ",<br/><br/>" +
                    "We have received a request to reset your old password at socialtainment.com." +
                    " <br/> <br/>In order to change your password, click the link below:" +
                    " <br/> <br/>Reset My Password (<a href=\"http://192.168.0.7/recovery.aspx?email=" + email + " \" style=\"color:#0078D7\">http://192.168.0.7/recovery.aspx</a>)" +
                    " <br/> <br/>If you have not made any password reset request, it is likely that another user entered your email address by mistake and you can simply disregard this email." +
                    " <br/> <br/>Thanks, <br/>Tunku Abdul Rahman University College", "text/html");

            //Sending email
            Transport.send(mm);

        } catch (MessagingException e) {
            e.printStackTrace();
        }


        return null;
    }


}