/*
Mar 2, 2017
CheckingMails.java, CheckingMails, Joni Sikiö <joni.sikio@student.lut.fi> 
Kuvaus sisällöstä: 
Kehitysympäristö: NetBeans
Muutoshistoria:
Lisenssi: default

 */
//package com.tutorialspoint;
package tppcbot;

import java.util.ArrayList;
import java.util.Properties;
import javax.mail.FetchProfile;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

public class CheckingMails {
    ArrayList<String> urlList;
    String host;
    String mailStoreType;
    String username;
    String password;
      
    CheckingMails(String email, String pass) {
        urlList = new ArrayList();
        host = "pop.gmail.com";// change accordingly
        mailStoreType = "pop3";
        username = email;// change accordingly
        password = pass;// change accordingly
        System.out.println(email + " : " + pass);
   }
    
    
   public ArrayList check(){
    try {
mailStoreType = "pop3";
    //create properties field
    Properties properties = new Properties();

    properties.put("mail.pop3.host", host);
    properties.put("mail.pop3.port", "995");
    properties.put("mail.pop3.starttls.enable", "true");
    Session emailSession = Session.getDefaultInstance(properties);

    
    
    //create the POP3 store object and connect with the pop server
    Store store = emailSession.getStore("pop3s");

    store.connect(host, username, password);
    
    //create the folder object and open it
    Folder emailFolder = store.getFolder("INBOX");
    emailFolder.open(Folder.READ_ONLY);
    // retrieve the messages from the folder in an array and print it
    Message[] messages = emailFolder.getMessages();
    System.out.println("Messages: " + messages.length);

    for (int i = 0, n = messages.length; i < n; i++) {
        //System.out.println("Messages: " + messages.length);
        Message message = messages[i];
        //System.out.println("---------------------------------");
        //System.out.println("Email Number " + (i + 1));
        //System.out.println("Subject: " + message.getSubject());
        //System.out.println("'"+message.getSubject()+"'");
        if(message.getSubject().equals("Welcome To The TPPC Online RPG (http://tppcrpg.net)")){
            
            String mess = message.getContent().toString();
            //System.out.println(mess);
            if(mess.contains("http://www.tppcrpg.net/activate.php?id=")){
                urlList.add(mess.substring(mess.indexOf("http://www.tppcrpg.net/activate.php?id="),mess.indexOf("\n", mess.indexOf("http://www.tppcrpg.net/activate.php?id="))));
                System.out.println(urlList.get(urlList.size()-1));
            }
        }

      }

      //close the store and folder objects
      emailFolder.close(false);
      store.close();

      } catch (NoSuchProviderException e) {
         e.printStackTrace();
      } catch (MessagingException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
       System.out.println("Ending message reading.");
    return urlList;
   }

    public ArrayList checkAll(){
        try {
            mailStoreType = "imap";
        //create properties field
        Properties properties = new Properties();

        properties.put("mail.imap.host", "imap.gmail.com");
        properties.put("mail.imap.port", "993");
        properties.put("mail.imap.starttls.enable", "true");
        Session emailSession = Session.getDefaultInstance(properties);



        //create the POP3 store object and connect with the pop server
         
        Store store = emailSession.getStore("imaps");

        store.connect("imap.gmail.com", username, password);

        //create the folder object and open it
        Folder emailFolder = store.getFolder("INBOX");
        emailFolder.open(Folder.READ_ONLY);
        // retrieve the messages from the folder in an array and print it
        Message[] messages = emailFolder.getMessages();
        System.out.println("Messages: " + messages.length);

        for (int i = 0, n = messages.length; i < n; i++) {
            //System.out.println("Messages: " + messages.length);
            Message message = messages[i];
            //System.out.println("---------------------------------");
            //System.out.println("Email Number " + (i + 1));
            //System.out.println("Subject: " + message.getSubject());
            //System.out.println("'"+message.getSubject()+"'");
            if(message.getSubject().equals("Welcome To The TPPC Online RPG (http://tppcrpg.net)")){

                String mess = message.getContent().toString();
                //System.out.println(mess);
                if(mess.contains("http://www.tppcrpg.net/activate.php?id=")){
                    String acc = mess.substring(mess.indexOf("Welcome To The TPPC Online RPG, ")+"Welcome To The TPPC Online RPG, ".length(),mess.indexOf("!", mess.indexOf("Welcome To The TPPC Online RPG, ")));
                    System.out.println(acc);
                    String pass = mess.substring(mess.indexOf("Password: ")+"Password: ".length(),mess.indexOf("\n", mess.indexOf("Password: ")));
                    System.out.println(pass);
                    Account.accList.add(new Account(acc, pass, "NaN", "NaN"));
                }
            }

          }
          emailFolder.close(false);
          store.close();

          } catch (NoSuchProviderException e) {
             e.printStackTrace();
          } catch (MessagingException e) {
             e.printStackTrace();
          } catch (Exception e) {
             e.printStackTrace();
          }
           System.out.println("Ending message reading.");
        return urlList;
       }

}