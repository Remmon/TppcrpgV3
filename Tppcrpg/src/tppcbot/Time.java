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
    int fightSleepMean;
    int fightSleepDeviation;
    
    int meanBetweenRest;
    int deviationBetweenRest;
    int restMean;
    int restDeviation;
        
    private Time(){
    
    }
    

    
    static public Time getInstance(){
        if (tm==null){
            tm = new Time();
        }
        return tm;
    }
    

    void setFightSleepMean(Integer fightSleepMean) {
        tm.fightSleepMean = fightSleepMean;
    }

    void setFightSleepDeviation(Integer fightSleepDeviation) {
        tm.fightSleepDeviation = fightSleepDeviation;
    }

    public void setMeanBetweenRest(int meanBetweenRest) {
        tm.meanBetweenRest = meanBetweenRest;
    }

    public void setDeviationBetweenRest(int deviationBetweenRest) {
        tm.deviationBetweenRest = deviationBetweenRest;
    }

    public void setRestMean(int restMean) {
        tm.restMean = restMean;
    }

    public void setRestDeviation(int restDeviation) {
        tm.restDeviation = restDeviation;
    }
    
    public int getFightSleepMean() {
        return tm.fightSleepMean;
    }

    public int getFightSleepDeviation() {
        return tm.fightSleepDeviation;
    }

    public int getMeanBetweenRest() {
        return tm.meanBetweenRest;
    }

    public int getDeviationBetweenRest() {
        return tm.deviationBetweenRest;
    }

    public int getRestMean() {
        return tm.restMean;
    }

    public int getRestDeviation() {
        return tm.restDeviation;
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
