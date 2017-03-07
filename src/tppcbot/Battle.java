/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tppcbot;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import static tppcbot.TppcSub.driver;

/**
 *
 * @author Jontsa
 */
public class Battle {
    static Battle b = null;
    FXMLDocumentController controller;
    WebDriver driver;
    
    String battleid;
    long random;
    long estimatedTime;
    
    private Battle(){
    
    }
    
    public static Battle getInstance(){
        if(b == null){
            b = new Battle();
        }
        
        return b;
    }
    public void setDriverAndController(FXMLDocumentController c, WebDriver d) {
        this.controller = c;
        driver = d;
        
        
    }
    
    public void fight(String id, int battleAmount){
        battleid = id;
        controller.FightBreaker = false;
        long startTime = System.currentTimeMillis();
        controller.startBigTime = System.currentTimeMillis();
        
        driver.get("http://www.tppcrpg.net/battle.php?Battle=Trainer&Trainer="+battleid);
        int calc = 0;
                
        while (true){
            controller.bigSleep();
            if (controller.FightBreaker){   //UI break button
                break;
            }
            estimatedTime  = System.currentTimeMillis() - startTime;
            if (estimatedTime > 10000){
                driver.get("http://www.tppcrpg.net/battle.php?Battle=Trainer&Trainer="+battleid);
                startTime = System.currentTimeMillis();
            }
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    System.out.println("UpdateUI");
                   controller.updateUI();
                }
            });
            checkBreak();
            if (controller.breakEnabled.isSelected() && controller.breaker){
                System.out.println("Stopping.");
                controller.breaker = false;
                break;
            }

            if (driver.findElements(By.id("Validate")).size() > 0 ){
                startTime = System.currentTimeMillis();
                CaptchaBreaker cb = CaptchaBreaker.getInstance();
                cb.solveCaptcha("Congratulations!");
                
                driver.get("http://www.tppcrpg.net/battle.php?Battle=Trainer&Trainer="+battleid);
                startTime = System.currentTimeMillis();
            }else if(driver.findElements(By.linkText("Restart Battle")).size() != 0){
                startTime = System.currentTimeMillis();
                driver.findElement(By.linkText("Restart Battle")).click();
                calc++;
                
                if(calc > battleAmount && battleAmount > 0){
                    break;
                }
                
            } else if (driver.findElements(By.className("submit")).size() != 0){
                startTime = System.currentTimeMillis();
                driver.findElement(By.className("submit")).click();
            }
            Platform.runLater(new Runnable() {
                @Override public void run() {
                   controller.updateUI();
                }
            });
            controller.defSleep();
            
        }
        
        
        
    }
    
    
    
    public void checkBreak(){
        
        
        ArrayList<Integer> pokeList = new ArrayList();
        System.out.println("Checking Break");
        for(int i=0;i<6;i++){
            //int rlevel;
            String tempLevel;
            String tempNumber = "";

            String tempItem = "";
            if(i<3){
                tempLevel = driver.findElement(By.xpath("/html/body/div[@id='right']/ul/li[@id='rs']/div[1]/img["+(i+1)+"]")).getAttribute("onclick");
                tempNumber = tempLevel.substring(tempLevel.indexOf("level: '")+8, tempLevel.indexOf("'", tempLevel.indexOf("level: '")+8));
                tempItem = tempLevel.substring(tempLevel.indexOf("item: '")+7, tempLevel.indexOf("'", tempLevel.indexOf("item: '")+7));
            } else if (i <6){
                tempLevel = driver.findElement(By.xpath("/html/body/div[@id='right']/ul/li[@id='rs']/div[2]/img["+(i-2)+"]")).getAttribute("onclick");
                tempNumber = tempLevel.substring(tempLevel.indexOf("level: '")+8, tempLevel.indexOf("'", tempLevel.indexOf("level: '")+8));
                tempItem = tempLevel.substring(tempLevel.indexOf("item: '")+7, tempLevel.indexOf("'", tempLevel.indexOf("item: '")+7));
            }

            if(!controller.pokeBreakLabel.get(i).getText().isEmpty() && i < 6 && i != 0 && tempItem.equals("Exp Share") && controller.breakEnabled.isSelected()){
                if (Integer.valueOf(controller.pokeBreakLabel.get(i).getText()) <= Integer.valueOf(tempNumber)){
                    pokeList.add(i+1);
                }
            } else if (!controller.pokeBreakLabel.get(0).getText().isEmpty() && controller.breakEnabled.isSelected()){
                if(Integer.valueOf(controller.pokeBreakLabel.get(i).getText()) <= Integer.valueOf(tempNumber)){
                    System.out.println("Breaker enabled");
                    controller.breaker = true;
                }
            }
        }
        
         if (pokeList.size() > 0){
            System.out.println("Pokelist size: " + pokeList.size());
            driver.get("http://www.tppcrpg.net/configure_roster.php");
            for (int i=0;i<pokeList.size();i++){
                System.out.println("Doing The Thing");
                if((pokeList.get(i))%2 == 0){
                    driver.findElement(By.xpath("/html/body/div[@id='body']/div[@id='inner']/form/ul[@id='configure']/li[@class='right']["+(pokeList.get(i)/2)+"]/p[1]/input")).click();
                    Select select = new Select(driver.findElement(By.xpath("/html/body/div[@id='cBox']/p[@class='center'][1]/select[@id='ItemA']")));
                    select.selectByIndex(0);
                    //driver.findElement(By.xpath("/html/body/div[@id='cBox']/p[@class='center'][2]/input[@class='submit']")).submit();
                    driver.findElement(By.xpath("/html/body/div[@id='cBox']/p[@class='center'][2]/input")).click();


                } else {
                    System.out.println(pokeList.get(i));
                    if(pokeList.get(i) == 1){
                        driver.findElement(By.xpath("/html/body/div[@id='body']/div[@id='inner']/form/ul[@id='configure']/li["+(pokeList.get(i))+"]/p[1]/input")).click();
                    } else if (pokeList.get(i) == 3){
                        //System.out.println("/html/body/div[@id='body']/div[@id='inner']/form/ul[@id='configure']/li["+(pokeList.get(i)+1)+"]/p[1]/input");
                        driver.findElement(By.xpath("/html/body/div[@id='body']/div[@id='inner']/form/ul[@id='configure']/li["+(pokeList.get(i)+1)+"]/p[1]/input")).click();
                    } else if (pokeList.get(i) == 5){
                        driver.findElement(By.xpath("/html/body/div[@id='body']/div[@id='inner']/form/ul[@id='configure']/li["+(pokeList.get(i)+2)+"]/p[1]/input")).click();
                    }
                    Select select = new Select(driver.findElement(By.xpath("/html/body/div[@id='cBox']/p[@class='center'][1]/select[@id='ItemA']")));
                    select.selectByIndex(0);
                    //driver.findElement(By.xpath("/html/body/div[@id='cBox']/p[@class='center'][2]/input[@class='submit']")).submit();
                    driver.findElement(By.xpath("/html/body/div[@id='cBox']/p[@class='center'][2]/input")).click();
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println("Time to continue");
            driver.findElement(By.xpath("/html/body/div[@id='body']/div[@id='inner']/form/p[@class='center f']/input[@class='submit']")).click();
            driver.get("http://www.tppcrpg.net/battle.php?Battle=Trainer&Trainer="+controller.trainingAccountField.getText());


        }

    
    }
    
      
    
}
