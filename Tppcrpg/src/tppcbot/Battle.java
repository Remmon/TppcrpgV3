/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tppcbot;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    int[] exp = {0,0,0,0}; // 0 = totalExp, 1= first poke exp
    DecimalFormat formatter;
    Party p;
    String battleid;
    long random;
    long estimatedTime;
    long expStartTime;
    long expEstimatedTime;
    
    private Battle(){
        p = Party.getInstance();
        formatter =(DecimalFormat) DecimalFormat.getInstance();
        DecimalFormatSymbols custom=new DecimalFormatSymbols();
        custom.setDecimalSeparator(' ');
        formatter.setDecimalFormatSymbols(custom);
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
        exp[0] = 0; exp[1] =0; exp[2] =0; exp[3] = 0; 
        battleid = id;
        controller.FightBreaker = false;
        long startTime = System.currentTimeMillis();
        expStartTime = System.currentTimeMillis();
        controller.startBigTime = System.currentTimeMillis();
        Misc mi = Misc.getInstance();
        driver.get("http://www.tppcrpg.net/battle_trainer.php");
        driver.findElement(By.id("Trainer")).sendKeys(battleid);
        driver.findElement(By.className("submit"));
        int calc = 0;
        
        while (true){
            try{
                if (controller.FightBreaker){   //UI break button
                    break;
                }
                if(driver.findElements(By.name("LoginID")).size() > 0){
                    mi.login(controller.accountField.getText(), controller.passwordField.getText());
                }
                controller.defSleep();
                estimatedTime  = System.currentTimeMillis() - startTime;
                if (estimatedTime > 10000){
                    driver.get("http://www.tppcrpg.net/battle_trainer.php");
                    driver.findElement(By.id("Trainer")).sendKeys(battleid);
                    driver.findElement(By.className("submit"));
                    startTime = System.currentTimeMillis();
                }
                expEstimatedTime = System.currentTimeMillis() - expStartTime;
                Platform.runLater(new Runnable() {
                    @Override public void run() {

                        controller.updateUI();
                        expEstimatedTime = System.currentTimeMillis() - expStartTime;
                        int tempLvl = ((Pokemon)p.getPartyList().get(0)).getLevel();
                        double tempExp = Math.pow(((double)tempLvl), 3.0);
                        tempExp = tempExp + (((double)exp[0]/(double)expEstimatedTime)*1000.0*60.0*60.0);
                        tempExp = Math.pow(tempExp, (1.0/3.0));
                        controller.battleExpLabel.setText("Total exp:    \t" + formatter.format(exp[0]) 
                                                      + "\nStarter exp:  \t" + formatter.format(exp[1]) 
                                                      + "\t\tStarter level in an hour: " + (int)tempExp
                                                      + "\nTeamPoints:   \t"+ formatter.format(exp[2])
                                                      + "\nMoney:        \t" + formatter.format(exp[3])
                                                      + "\nMoney per hour: \t" + formatter.format((int)(((double)exp[3]/(double)expEstimatedTime)*1000*60*60))
                                                      + "\nExp per hour: \t" + formatter.format((int)(((double)exp[0]/(double)expEstimatedTime)*1000*60*60))
                                                      + "\nTime spent:   \t" + (((expEstimatedTime/1000 - (  ((expEstimatedTime/1000 -(expEstimatedTime/1000)%60)/60)%60  ))/60/60)) + "h "  + (  ((expEstimatedTime/1000 -(expEstimatedTime/1000)%60)/60)%60  ) + "m " + ((expEstimatedTime/1000)%60) + "s"); // exp = level ^ 3 || level = exp^(1/3)
                                                      System.out.println(((Pokemon)p.getPartyList().get(0)).getLevel());
                    }
                });
                if (controller.breakEnabled.isSelected() && controller.breaker){
                    System.out.println("Stopping.");
                    controller.breaker = false;
                    break;
                }
                exp = checkExperience(exp);
                if (driver.findElements(By.id("Validate")).size() > 0 ){
                    startTime = System.currentTimeMillis();
                    CaptchaBreaker cb = CaptchaBreaker.getInstance();
                    cb.solveCaptcha("Congratulations!");
                    
                    driver.get("http://www.tppcrpg.net/battle_trainer.php");
                    driver.findElement(By.id("Trainer")).sendKeys(battleid);
                    driver.findElement(By.className("submit"));
                    
                }else if(driver.findElements(By.linkText("Restart Battle")).size() != 0){
                    controller.bigSleep();
                    startTime = System.currentTimeMillis();
                    driver.findElement(By.linkText("Restart Battle")).click();
                    calc++;

                    if(calc > battleAmount && battleAmount > 0){
                        break;
                    }

                } else if (driver.findElements(By.className("submit")).size() != 0 && driver.findElements(By.name("LoginID")).size() == 0){
                    startTime = System.currentTimeMillis();
                    driver.findElement(By.className("submit")).click();
                }
                checkBreak();
                mi.checkFaint();
                controller.defSleep();

            }catch (org.openqa.selenium.StaleElementReferenceException e){
                //e.printStackTrace();
                System.out.println("Stale element while fighting (nothing to worry)");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
    
    
    public String getSubstring (String line, String start, String end){
        int startIndex = line.indexOf(start)+start.length();
        int endIndex = line.indexOf(end, startIndex);
        return line.substring(startIndex,endIndex);
    }
    
    public int[] checkExperience(int[] exp){
        boolean firstCount = true;
        int tempExp;
        List<WebElement> trainer1 = driver.findElements(By.className("Trainer1"));
        if(trainer1.size() > 0){
            for(int i=0;i <trainer1.size();i++){
                tempExp = 0;
                String tempLine = trainer1.get(i).getText();
                if(tempLine.contains("experience!")){
                    tempExp = Integer.valueOf(getSubstring (tempLine, "gained ", " experience!"));
                    exp[0] = exp[0]+tempExp;
                    if(firstCount){
                        exp[1] = exp[1] + tempExp;
                        firstCount = false;
                    }
                } else if (tempLine.contains("team points for winning")){
                    tempExp= Integer.valueOf(getSubstring (tempLine, "gained ", " team points"));
                    exp[2] = exp[2] + tempExp;
                } else if (tempLine.contains("gained $")){
                    tempExp= Integer.valueOf(getSubstring (tempLine, "gained $", "!"));
                    exp[3] = exp[3] + tempExp;
                }
            }
        }
        return exp;
    }
    
    public void checkBreak(){
        
        
        ArrayList<Integer> pokeList = new ArrayList();
        List<WebElement> levelList = driver.findElements(By.cssSelector(".rosterContent li"));
        if(levelList.size() > 0 ){
            for(int i=0;i<6;i++){
                //int rlevel;
                String tempLevel;
                String tempNumber = "";

                String tempItem = "";
                if(levelList.get(i).getText().contains("(L:")){

                    tempNumber = getSubstring(levelList.get(i).getText(), "(L:", ")");//levelList.get(i).getText().substring(levelList.get(i).getText().indexOf("(L:")+3, levelList.get(i).getText().indexOf(")", levelList.get(i).getText().indexOf("(L:")+3));
                }
                if(i<3){
                    tempLevel = driver.findElement(By.xpath("/html/body/div[@id='right']/ul/li[@id='rs']/div[1]/img["+(i+1)+"]")).getAttribute("onclick");

                    tempItem = getSubstring(tempLevel, "item: '", "'");     //tempLevel.substring(tempLevel.indexOf("item: '")+7, tempLevel.indexOf("'", tempLevel.indexOf("item: '")+7));
                } else if (i <6){
                    tempLevel = driver.findElement(By.xpath("/html/body/div[@id='right']/ul/li[@id='rs']/div[2]/img["+(i-2)+"]")).getAttribute("onclick");
                    //tempNumber = tempLevel.substring(tempLevel.indexOf("level: '")+8, tempLevel.indexOf("'", tempLevel.indexOf("level: '")+8));
                    tempItem = getSubstring(tempLevel, "item: '", "'");     //tempLevel.substring(tempLevel.indexOf("item: '")+7, tempLevel.indexOf("'", tempLevel.indexOf("item: '")+7));
                }
                ((Pokemon)p.getPartyList().get(i)).setItem("tempItem");
                ((Pokemon)p.getPartyList().get(i)).setLevel(Integer.valueOf(tempNumber));

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


                driver.get("http://www.tppcrpg.net/battle_trainer.php");
                driver.findElement(By.id("Trainer")).sendKeys(battleid);
                driver.findElement(By.className("submit"));


            }

        }
    }

    
      
    
}
