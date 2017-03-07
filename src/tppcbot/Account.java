/*
Mar 2, 2017
Account.java, Account, Joni Sikiö <joni.sikio@student.lut.fi> 
Kuvaus sisällöstä: 
Kehitysympäristö: NetBeans
Muutoshistoria:
Lisenssi: default

 */
package tppcbot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import static tppcbot.Time.tm;

/**
 *
 * @author Joni Sikiö <joni.sikio@student.lut.fi>
 */
public class Account implements Serializable{
    static ArrayList<Account> accList = new ArrayList();

    static ArrayList getAccList() {
        /*if(accList == null){
            accList = new ArrayList();
        }*/
        
        return accList;
    }
    
    String account;
    String password;
    String email;
    String team;
    boolean prePromoDone;
    
    @Override
    public String toString(){
        return account + " : " + password;
    }
    
    
    Account(String account, String password, String email, String team) {
        this.prePromoDone = false;
        this.account = account;
        this.password = password;
        this.email = email;
        this.team = team;
        System.out.println(this.account + ":" + this.password + " (" + this.email + " " + this.team+")");
        
    }


     public static void loadLogFromFile(){  //Loads previous log objects from a file.
        FileInputStream fiStream = null;
        File f = new File("D:\\tppcJavafiles\\accounts.tmp");
        if(f.exists()){
            try {
                fiStream = new FileInputStream("D:\\tppcJavafiles\\accounts.tmp");
                ObjectInputStream oiStream = new ObjectInputStream(fiStream);
                accList = (ArrayList<Account>) oiStream.readObject();
                System.out.println(accList);
                oiStream.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Can't find file.");
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
            }finally {
                try {
                    fiStream.close();
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void saveLogToFile(){ //Saves all log objects to file.
        FileOutputStream foStream = null;
        try {
            foStream = new FileOutputStream("D:\\tppcJavafiles\\accounts.tmp");
            ObjectOutputStream ooStream = new ObjectOutputStream(foStream);
            ooStream.writeObject(accList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                foStream.close();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    } 
    
}
