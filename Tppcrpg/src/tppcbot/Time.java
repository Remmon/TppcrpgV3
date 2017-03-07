/*
Feb 28, 2017
Time.java, Time, Joni Sikiö <joni.sikio@student.lut.fi> 
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

/**
 *
 * @author Joni Sikiö <joni.sikio@student.lut.fi>
 */
public class Time implements Serializable{
    
    static Time tm = null;
    int fightSleepTime;
    int fightSleepRandom;
    
    int timeBetweenRest;
    int timeBetweenRestRandom;
    int timeToRest;
    int timeToRestRandom;
        
    private Time(){
    
    }
    

    
    static public Time getInstance(){
        if (tm==null){
            tm = new Time();
        }
        return tm;
    }
    


    public void setFightSleepTime(int fightSleepTime) {
        tm.fightSleepTime = fightSleepTime;
    }

    public void setFightSleepRandom(int fightSleepRandomMultiplier) {
        tm.fightSleepRandom = fightSleepRandomMultiplier;
    }

    public void setTimeBetweenRest(int timeBetweenRest) {
        tm.timeBetweenRest = timeBetweenRest;
    }

    public void setTimeBetweenRestRandom(int timeBetweenRestRandomMultiplier) {
        tm.timeBetweenRestRandom = timeBetweenRestRandomMultiplier;
    }

    public void setTimeToRest(int timeToRest) {
        tm.timeToRest = timeToRest;
    }

    public void setTimeToRestRandom(int timeToRestRandomMultiplier) {
        tm.timeToRestRandom = timeToRestRandomMultiplier;
    }

    public int getFightSleepTime() {
        return tm.fightSleepTime;
    }

    public int getFightSleepRandom() {
        return tm.fightSleepRandom;
    }

    public int getTimeBetweenRest() {
        return tm.timeBetweenRest;
    }

    public int getTimeBetweenRestRandom() {
        return tm.timeBetweenRestRandom;
    }

    public int getTimeToRest() {
        return tm.timeToRest;
    }

    public int getTimeToRestRandom() {
        return tm.timeToRestRandom;
    }
    
    public void loadLogFromFile(){  //Loads previous log objects from a file.
        FileInputStream fiStream = null;
        File f = new File("D:\\tppcJavafiles\\time.tmp");
        if(f.exists()){
            try {
                fiStream = new FileInputStream("D:\\tppcJavafiles\\time.tmp");
                ObjectInputStream oiStream = new ObjectInputStream(fiStream);
                tm = (Time) oiStream.readObject();
                oiStream.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Can't find file.");
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(Time.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(Time.class.getName()).log(Level.SEVERE, null, ex);
            }finally {
                try {
                    fiStream.close();
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(Time.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void saveLogToFile(){ //Saves all log objects to file.
        FileOutputStream foStream = null;
        try {
            foStream = new FileOutputStream("D:\\tppcJavafiles\\time.tmp");
            ObjectOutputStream ooStream = new ObjectOutputStream(foStream);
            ooStream.writeObject(tm);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(trainerManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(trainerManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                foStream.close();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(trainerManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    
}
