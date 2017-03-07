/*
Feb 28, 2017
trainerManager.java, trainerManager, Joni Sikiö <joni.sikio@student.lut.fi> 
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
import java.util.ArrayList;
import java.util.logging.Level;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Joni Sikiö <joni.sikio@student.lut.fi>
 */
public class trainerManager {
    static trainerManager tm = null;
    ArrayList<trainer> trainerList = new ArrayList();
    WebDriver driver;
    
    private trainerManager(){
        loadLogFromFile();
        //System.out.println(trainerList.get(0));
        if (trainerList.isEmpty()){
            trainerList.add(null);
            trainerList.add(new trainer("2502909", 4));
            trainerList.add(new trainer("3397006", 10));
            trainerList.add(new trainer("3397013", 20));
            trainerList.add(new trainer("3269882", 75));
            trainerList.add(new trainer("2044868", 101));
            trainerList.add(new trainer("1113640", 150));
            trainerList.add(new trainer("1325349", 200));
            trainerList.add(new trainer("924714", 250));
            trainerList.add(new trainer("913641", 350));
            trainerList.add(new trainer("3033909", 400));
            trainerList.add(new trainer("3384456", 500));
            trainerList.add(new trainer("2493188", 600));
            trainerList.add(new trainer("2646064", 650));
            trainerList.add(new trainer("2656958", 815));
            trainerList.add(new trainer("3384457", 1000));
            trainerList.add(new trainer("3394572", 1200));
            trainerList.add(new trainer("498042", 1575));
            trainerList.add(new trainer("2536240", 1760));
            trainerList.add(new trainer("3384459", 2000));
            trainerList.add(new trainer("482301", 2500));
            trainerList.add(new trainer("3439851", 3000));
            trainerList.add(new trainer("3421893", 3500));
            trainerList.add(new trainer("995268", 4000));
            trainerList.add(new trainer("3328818", 4500));
            trainerList.add(new trainer("3101818", 5000));
            trainerList.add(new trainer("3395546", 5500));
            trainerList.add(new trainer("3387834", 6500));
            trainerList.add(new trainer("3395547", 7500));
            trainerList.add(new trainer("2719620", 13000));
        }
    }
    
    public WebDriver getDriver(){
        return driver;
    }
    public void setDriver(WebDriver driver){
        this.driver = driver;
    }
    
    static public trainerManager getInstance(){
        if (tm == null){
            tm = new trainerManager();
        }
        
        return tm;
    }
    
    public void loadLogFromFile(){  //Loads previous log objects from a file.
        FileInputStream fiStream = null;
        File f = new File("D:\\tppcJavafiles\\trainer.tmp");
        if(f.exists()){
            try {
                fiStream = new FileInputStream("D:\\tppcJavafiles\\trainer.tmp");
                ObjectInputStream oiStream = new ObjectInputStream(fiStream);
                trainerList = (ArrayList<trainer>) oiStream.readObject();
                oiStream.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Can't find file.");
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(trainerManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(trainerManager.class.getName()).log(Level.SEVERE, null, ex);
            }finally {
                try {
                    fiStream.close();
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(trainerManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void saveLogToFile(){ //Saves all log objects to file.
        FileOutputStream foStream = null;
        try {
            foStream = new FileOutputStream("D:\\tppcJavafiles\\trainer.tmp");
            ObjectOutputStream ooStream = new ObjectOutputStream(foStream);
            ooStream.writeObject(trainerList);
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

    ArrayList<trainer> getList() {
        ArrayList<trainer> templist= new ArrayList();
        for (int i=1; i<trainerList.size();i++){
            templist.add(trainerList.get(i));
        }
        
        
        return templist;
    }

    void setSelected(trainer newValue) {
        trainerList.set(0, newValue);
    }
    
    public trainer getSelected() {
        return trainerList.get(0);
    }
    
}
