/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tppcbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author Jontsa
 */
public class Misc {
    static Misc m = null;
    FXMLDocumentController controller;
    WebDriver driver;
    int value;
    ArrayList<Account> accList;
    
    private Misc(){
    
    }
    
    public void setDriverAndController(FXMLDocumentController dc, WebDriver d){
        controller = dc;
        driver = d;
    }
        

    
    public static Misc getInstance(){
        if(m == null){
            m = new Misc();
        }
        
        return m;
    }
    
    
    public void login(String user, String pass){
        driver.get("http://www.tppcrpg.net/login.php");
        driver.findElement(By.name("LoginID")).sendKeys(user);
        driver.findElement(By.name("NewPass")).sendKeys(pass);
        
        if(driver.findElements(By.name("Validate")).size() > 0){
                CaptchaBreaker cb = CaptchaBreaker.getInstance();
                cb.solveCaptcha("Validation Image");
        }
        if(driver.findElements(By.className("submit")).size() > 0){
                driver.findElement(By.className("submit")).click();
        }
        
    }
    
    void logout() {
        driver.get("http://www.tppcrpg.net/logout.php");
    }
    
    public int startDonate(int tokens){
        
        int errorcalc = 0;
        driver.get("http://www.tppcrpg.net/donation_center.php");
        while(true){
            try{
                while(true){
                    if(driver.findElements(By.id("donatePoke")).size() == 0 && errorcalc<10){
                        errorcalc++;
                        driver.get("http://www.tppcrpg.net/donation_center.php");
                    } else {
                        break;
                    }
                    if(controller.FightBreaker){
                        break;
                    }
                    controller.defSleep();
                }

                if(controller.FightBreaker){
                    break;
                }
                if (driver.findElements(By.id("donatePoke")).size() > 0 ){
                    tokens++;
                    Select select = new Select(driver.findElement(By.id("donatePoke")));
                    select.selectByIndex(1);
                    driver.findElement(By.className("submit")).click();
                    driver.switchTo().alert().accept();
                } else {
                    break;
                }
                
            } catch(Exception e){
                e.printStackTrace();
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Exception, enter for continue.");
                try {
                    String s = br.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(Misc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            controller.defSleep();
        }
        return tokens;
    }
    
    public void battleMap(){
        driver.findElement(By.linkText("Lets Battle!")).click();
        while(true){
            try{
                if(controller.FightBreaker){
                    break;
                }    

                if (driver.findElements(By.id("Validate")).size() != 0 ){
                    CaptchaBreaker cb = CaptchaBreaker.getInstance();
                    cb.solveCaptcha("Congratulations!");
                }

                if (driver.findElements(By.className("hpBar")).size() > 0) {

                    if (driver.findElement(By.className("hpBar")).getAttribute("title").equals("0% HP Remaining")){
                        driver.findElement(By.linkText("Leave Battle")).click();
                        break;
                    }
                }
                controller.defSleep();
                if (driver.findElements(By.className("submit")).size() > 0){
                    driver.findElement(By.className("submit")).click();

                } else {
                    break;
                }
            } catch(Exception e){
                e.printStackTrace();
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Exception, enter for continue.");
                try {
                    String s = br.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(Misc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }    
        }
    }

    public int SwapToken() {  
        controller.FightBreaker = false;
        int mapid = 1;
        int tokens = 0;
        tokens = startDonate(tokens);
        driver.get("http://www.tppcrpg.net/map.php?Map="+mapid);
        int caught =0;
        while(true){
            try{
                if(controller.FightBreaker){
                    break;
                }
                driver.findElement(By.name("Find")).click();
                if (driver.findElements(By.name("Catch")).size() > 0 ){
                    caught++;
                    driver.findElement(By.name("Catch")).click();
                    if (driver.findElements(By.className("success")).size() > 0){
                    } else {
                        System.out.println("Poke Escaped");
                        tokens = startDonate(tokens);
                        driver.get("http://www.tppcrpg.net/map.php?Map="+mapid);

                        caught = 0;
                    }
                    driver.findElement(By.partialLinkText("Return To")).click();

                } else if (driver.findElements(By.linkText("Lets Battle!")).size() > 0 ){
                    battleMap();
                    driver.get("http://www.tppcrpg.net/map.php?Map="+mapid);
                }

                if (caught > (10*mapid)){
                        mapid++;
                    if (mapid > 14){
                        mapid = 1;
                    }

                }
                if(caught > 139){
                    caught = 0;
                    tokens = startDonate(tokens);
                    driver.get("http://www.tppcrpg.net/map.php?Map="+mapid);
                }
                
            } catch(Exception e){
                e.printStackTrace();
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Exception, enter for continue.");
                try {
                    String s = br.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(Misc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            controller.defSleep();
        }
        
        return tokens;

    }

    void ssAnne() {
        long startTime = System.currentTimeMillis();
        long estimatedTime;
        controller.startBigTime = System.currentTimeMillis();
        value = 0;
        boolean hazebool = true;
        controller.FightBreaker = false;
        
        while(true){
            try{
                if(controller.FightBreaker){
                    controller.FightBreaker = false;
                    break;
                }
                if(driver.findElements(By.className("submit")).size() >0){
                    System.out.println("Pressin submit");
                    driver.findElement(By.className("submit")).click();
                    startTime = System.currentTimeMillis();
                }
                if(driver.findElements(By.id("battleText")).size()>0){
                    System.out.println("Checking battleText"+driver.findElements(By.id("battleText")).size());
                    System.out.println(driver.findElements(By.id("battleText")).get(0).getText());
                    if(driver.findElements(By.id("battleText")).get(0).getText().contains("This number has been recorded as your")){
                        driver.get("http://www.tppcrpg.net");
                        break;
                    }
                }
                List<WebElement> alwe = driver.findElements(By.className("Trainer1"));
                if(alwe.size()>0){
                    System.out.println("Check");
                    for(int i=0;i<alwe.size();i++){
                        System.out.println("Check2");
                        if(alwe.get(i).getText().contains("You have defeated")){
                            String work = alwe.get(i).getText().substring(alwe.get(i).getText().indexOf("You have defeated ")+ "You have defeated ".length(), alwe.get(i).getText().indexOf(" Pokemon!"));     //"You have defeated 1 Pokemon!"
                            work = work.replaceAll(" ", "");
                            value = Integer.valueOf(work);
                            Platform.runLater(new Runnable() {
                                @Override public void run() {
                                   controller.ssAnneLabel.setText("Wins: " + value);

                                }
                            });
                            System.out.println(value);
                            break;
                        }
                    }
                }

                if(value > Integer.valueOf(controller.ssAnneWinCountLabel.getText()) && hazebool){
                    Select select = new Select(driver.findElement(By.id("MyMove")));
                    select.selectByVisibleText("Haze");
                    hazebool = false;

                    // Here you need to lose
                }
                List<WebElement> party = new ArrayList();
                if(driver.findElement(By.xpath("/html/body/div[@id='body']/div[@id='inner']/div[@id='battleWindow']/div[@id='ActiveBoxes']/div[@id='Trainer1_Active']/div[@class='innerContent']/fieldset/div[@class='hpBar']")).getAttribute("title").equals("0% HP Remaining")){
                    driver.findElement(By.className("rosterContent")).findElement(By.partialLinkText("")).click();
                }


                estimatedTime = System.currentTimeMillis() - startTime;
                if (estimatedTime > 10000) {
                    System.out.println("Timed out!");
                    driver.get("http://www.tppcrpg.net/resume_battle.php");
                    driver.findElements(By.linkText("S.S. Anne")).get(driver.findElements(By.linkText("S.S. Anne")).size()-1).click();
                    startTime = System.currentTimeMillis();
                }



                Platform.runLater(new Runnable() {
                    @Override public void run() {
                       controller.updateUI();

                    }
                });

                controller.defSleep();
                //controller.bigSleep();
            } catch(org.openqa.selenium.StaleElementReferenceException ex){
                System.out.println("Stale element error (nothing to worry about)");
            } catch(Exception e){
                e.printStackTrace();
                /*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Exception, enter for continue.");
                try {
                    String s = br.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(Misc.class.getName()).log(Level.SEVERE, null, ex);
                }*/
            }
        }


    }

    void makeAccounts(){
        String accountName ="";
        int calc = 0;
        for(int i=0; i<Integer.valueOf(controller.accountNumberField.getText());i++){
            System.out.println("Creatin account #" + i);
            driver.get("http://www.tppcrpg.net/signup.php");

            if(driver.findElements(By.id("NickName")).size() > 0){
                accountName =controller.accountPrefixField.getText();
                driver.findElement(By.id("NickName")).sendKeys(accountName);
                driver.findElement(By.id("Password")).sendKeys(controller.accountPasswordField.getText());
                driver.findElement(By.id("ConfirmPassword")).sendKeys(controller.accountPasswordField.getText());
                driver.findElement(By.id("Email")).sendKeys(controller.accountGmailField.getText()+"+"+controller.accountPrefixField.getText()+"@gmail.com");


                List<WebElement> starters = driver.findElements(By.id("Starter"));
                for(int u =0; u<starters.size();u++){
                    if(starters.get(u).getAttribute("value").equals(controller.starterSelection)){
                        starters.get(u).click();
                    }
                }
                Select tselect = new Select(driver.findElement(By.id("Team")));
                tselect.selectByIndex(controller.accountTeamCombo.getSelectionModel().getSelectedIndex()+1);
                CaptchaBreaker cb = CaptchaBreaker.getInstance();
                
                String accountNameAdd = "";
                int newCalc=0;
                while(true){
                    try{
                        if(driver.findElement(By.id("taken")).getText().equals("Name Taken!")){
                            if(newCalc > 0){
                                driver.findElement(By.id("NickName")).sendKeys(Keys.BACK_SPACE);
                            } newCalc++;
                            driver.findElement(By.id("NickName")).sendKeys(""+(++calc));
                            accountNameAdd = "" +calc; 
                            driver.findElement(By.id("Password")).sendKeys("");
                        } else if (driver.findElement(By.id("taken")).getText().equals("Name Available!")){
                            break;
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    controller.defSleep();
                }
                accountName = accountName + accountNameAdd;
                cb.solveCaptcha("Validation Image");
                accList = Account.getAccList();
                controller.accountCombo.getItems().setAll(accList.subList(1, accList.size()));
                controller.accountMainCombo.getItems().setAll(accList.subList(1, accList.size()));
                
                Account.getAccList().add(new Account(accountName, controller.accountPasswordField.getText(), controller.accountGmailField.getText()+"+"+controller.accountPrefixField.getText()+"@gmail.com", controller.accountTeamCombo.getSelectionModel().getSelectedItem()));
            }
            controller.multiProgress = (float)i / (float)(Integer.valueOf(controller.accountNumberField.getText()) -1);
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    controller.accListNumberLabel.setText((accList.size()-1)+"");
                    controller.multiProgressBar.setProgress(controller.multiProgress);
                }
            });

        }

    }

    public void accountsTrade(String acco){


        driver.get("http://www.tppcrpg.net/create_trade.php");
        driver.findElement(By.name("id")).sendKeys(acco);
        driver.findElement(By.className("submit")).click();

        Select select = new Select(driver.findElement(By.id("CP")));
        for(int u=0;u<select.getOptions().size();u++){
            select.selectByIndex(u);
        }

        driver.findElement(By.className("submit")).click();

    }

    void verifyAccounts() {
        
        CheckingMails cm = new CheckingMails(controller.accountGmailField.getText()+"@gmail.com",controller.accountGmailPassField.getText());
        ArrayList<String> urls = cm.check();
        for(int i=0;i<urls.size();i++){
            driver.get(urls.get(i));
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            controller.multiProgress = (float)(i+1) / (float)(urls.size());
            Platform.runLater(new Runnable() {
                @Override public void run() {
                   controller.multiProgressBar.setProgress(controller.multiProgress);
                }
            });
        }
    }
    
     void loadAccounts() {
        
        CheckingMails cm = new CheckingMails(controller.accountGmailField.getText()+"@gmail.com",controller.accountGmailPassField.getText());
        ArrayList<String> accountString = cm.checkAll();
        for(int i=0;i<accountString.size();i++){
            driver.get(accountString.get(i));
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            controller.multiProgress = (float)(i+1) / (float)(accountString.size());
            Platform.runLater(new Runnable() {
                @Override public void run() {
                   controller.multiProgressBar.setProgress(controller.multiProgress);
                }
            });
        }
    }

    void accountsPromo() {
        accList = Account.getAccList();
        driver.get("http://www.tppcrpg.net/team.php");
        ArrayList<WebElement> rewardList = (ArrayList<WebElement>) driver.findElements(By.name("Reward"));
        rewardList.get(0).click();
        //System.out.println(rewardList.get(0).getAttribute("value"));
        driver.findElement(By.name("getReward")).click();

    }

    void accountsManual() {
        accList = Account.getAccList();
        boolean excludedCheck = false;
        for(int i=1;i<accList.size();i++){
            excludedCheck = false;
            for(int e=0;e<controller.accountExcludedCombo.getItems().size();e++){
                if(controller.accountExcludedCombo.getItems().get(e).equals(accList.get(i))){
                    excludedCheck = true;
                }
            }
            if(excludedCheck){
                continue;
            }
            driver.get("http://www.tppcrpg.net/login.php");
            driver.findElement(By.name("LoginID")).sendKeys(accList.get(i).account);
            driver.findElement(By.name("NewPass")).sendKeys(accList.get(i).password);
            if(driver.findElements(By.name("Validate")).size() > 0){
                CaptchaBreaker cb = CaptchaBreaker.getInstance();
                cb.solveCaptcha("Validation Image");
            }
            if(driver.findElements(By.className("submit")).size() > 0){
                driver.findElement(By.className("submit")).click();
            }

            controller.manual.visibleProperty().set(false);
            while(true){
                if(controller.manualBoolean){
                    controller.manualBoolean = false;
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            controller.multiProgress = (float)(i+1) / (float)(accList.size());
            Platform.runLater(new Runnable() {
                @Override public void run() {
                   controller.multiProgressBar.setProgress(controller.multiProgress);
                }
            });
            driver.get("http://www.tppcrpg.net/logout.php"); 
        }


    }

    void accountsPreFight() {
        Map m = Map.getInstance();
        Battle b = Battle.getInstance();
        long random = 0;
        long estimatedTime = 0;
        
        m.startMap("poke", "Heracross", 1); //Catch a heracross
        driver.get("http://www.tppcrpg.net/change_roster.php");
        driver.get("http://www.tppcrpg.net/change_roster.php?c=&pn=214&o=");
        driver.findElement(By.xpath("/html/body/div[@id='body']/div[@id='inner']/div[@id='rightR']/ul/li/div[@class='sl']/ul/li[1]/a")).click();
        changeMove("Close Combat");
        b.fight("2502909", 3);
        changeItem("Team Boost");
        b.fight("2502909", 6);
        //accList.get(i).prePromoDone = true;

    }

    void accountsCatch(int amount) {
        Map m = Map.getInstance();
        //accList = Account.getAccList();
        m.startMap("map", ""+(int)(Math.random()*10 + 2), amount);
        System.out.println(""+(int)(Math.random()*10 + 2));

    }
    
    public void changeItem(String item){ //Change to be applicable to all pokes in roster
        Select movesel;
        driver.get("http://www.tppcrpg.net/team.php");
        driver.findElement(By.xpath("/html/body/div[@id='body']/div[@id='inner']/form/table[@class='ranks facrew']/tbody/tr[@class='r0'][1]/td[4]/input")).click();
        driver.findElement(By.className("submit")).click();

        driver.get("http://www.tppcrpg.net/configure_roster.php");
        driver.findElement(By.xpath("/html/body/div[@id='body']/div[@id='inner']/form/ul[@id='configure']/li[1]/p[1]/input")).click();

        movesel = new Select(driver.findElement(By.id("ItemA")));

        for(int n=0;n<movesel.getOptions().size();n++){
            if(!movesel.getOptions().get(n).getAttribute("class").equals("disabled") && movesel.getOptions().get(n).getText().contains(item)){
                movesel.selectByIndex(n);
                break;
            }
        }

        for(int y=0;y<driver.findElements(By.className("submit")).size();y++){
            if (driver.findElements(By.className("submit")).get(y).getAttribute("value").equals("Set Item!")){
                driver.findElements(By.className("submit")).get(y).click();
            }
        }
        controller.defSleep();
        for(int y=0;y<driver.findElements(By.className("submit")).size();y++){
            if (driver.findElements(By.className("submit")).get(y).getAttribute("value").equals("Update Moves and Items")){
                driver.findElements(By.className("submit")).get(y).click();
            }
        }
    
    }

    private void changeMove(String move) { // Change to be applicable to all pokes in roster

        driver.get("http://www.tppcrpg.net/configure_roster.php");
        driver.findElement(By.className("tMove1")).click();
        Select movesel = new Select(driver.findElement(By.id("MoveA")));
        //movesel.selectByValue(move);
        movesel.selectByVisibleText(move);
        controller.defSleep();
        for(int y=0;y<driver.findElements(By.className("submit")).size();y++){
            if (driver.findElements(By.className("submit")).get(y).getAttribute("value").equals("Set Move!")){
                driver.findElements(By.className("submit")).get(y).click();
            }
        }
        controller.defSleep();

        for(int y=0;y<driver.findElements(By.className("submit")).size();y++){
            if (driver.findElements(By.className("submit")).get(y).getAttribute("value").equals("Update Moves and Items")){
                driver.findElements(By.className("submit")).get(y).click();
            }
        }

    }
    
    public void checkFaint(){
        try{
            if(driver.findElements(By.className("hpBar")).size() > 0){
                if(driver.findElement(By.xpath("/html/body/div[@id='body']/div[@id='inner']/div[@id='battleWindow']/div[@id='ActiveBoxes']/div[@id='Trainer1_Active']/div[@class='innerContent']/fieldset/div[@class='hpBar']")).getAttribute("title").equals("0% HP Remaining")){
                    if(driver.findElement(By.className("rosterContent")).findElements(By.partialLinkText("")).size() > 0){
                        driver.findElement(By.className("rosterContent")).findElement(By.partialLinkText("")).click();
                    }
                }
            } 
        } catch(StaleElementReferenceException e){
            //System.out.println("Stale element!");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public void multiAcc(){
        try{
            Battle b = Battle.getInstance();
            Map m = Map.getInstance();
            accList = Account.getAccList();
            boolean excludedCheck = false;
            //if(!accList.get(i).prePromoDone){
            for(int i=1;i<accList.size();i++){
                
                if(controller.FightBreaker){
                    break;
                }
                excludedCheck = false;
                if((!accList.get(i).prePromoDone && controller.listToCatchCheck.isSelected()) || (!controller.listToCatchCheck.isSelected()) ){
                    controller.defSleep();
                    for(int e=0;e<controller.accountExcludedCombo.getItems().size();e++){
                        if(controller.accountExcludedCombo.getItems().get(e).equals(accList.get(i))){
                            excludedCheck = true;
                        }
                    }
                    if(excludedCheck){
                        continue;
                    }
                    driver.get("http://www.tppcrpg.net/login.php");
                    driver.findElement(By.name("LoginID")).sendKeys(accList.get(i).account);
                    driver.findElement(By.name("NewPass")).sendKeys(accList.get(i).password);
                    if(driver.findElements(By.name("Validate")).size() > 0){
                        CaptchaBreaker cb = CaptchaBreaker.getInstance();
                        cb.solveCaptcha("Validation Image");
                    }
                    if(driver.findElements(By.className("submit")).size() > 0){
                        driver.findElement(By.className("submit")).click();
                    }

                    /************       Actions here     ************/
                    for(int a=0;a< controller.multiAccListView.getItems().size();a++){
                        controller.defSleep();
                        String choice = controller.multiAccListView.getItems().get(a);
                        String[] opt = choice.split(",");
                        if(opt[0].equals("catch")){
                            if(controller.listToRandomCheck.isSelected()){
                                int randam = (int) (Float.valueOf(controller.listToRandom.getText())*Math.random());
                                randam = randam + (Integer.valueOf(Integer.valueOf(opt[1]))- randam/2);
                                System.out.println("Catching "+randam + " pokes");
                                accountsCatch(randam);
                            } else {
                                accountsCatch(Integer.valueOf(opt[1]));
                            }
                            
                        }else if(opt[0].equals("prepromo")){
                            accountsPreFight();
                            accList.get(i).prePromoDone = true;
                        }else if(opt[0].equals("promo")){
                            accountsPromo();
                        }else if(opt[0].equals("trade")){
                            accountsTrade(opt[1]);
                        }else if(opt[0].equals("fight")){
                            if(controller.listToRandomCheck.isSelected()){
                                int randam = (int) (Float.valueOf(controller.listToRandom.getText())*Math.random());
                                randam = randam + (Integer.valueOf(Integer.valueOf(opt[1]))- randam/2);
                                System.out.println("Battling "+randam + " times");
                                b.fight(opt[1], randam);
                            } else {
                                b.fight(opt[1], Integer.valueOf(opt[2]));
                            }
                        }
                    }
                    /************************************************/
                    driver.get("http://www.tppcrpg.net/logout.php");
                }
                controller.multiProgress = (float)(i+1) / (float)(accList.size());;
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                       controller.multiProgressBar.setProgress(controller.multiProgress);
                    }
                });
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        
    }



    
 
    
}
