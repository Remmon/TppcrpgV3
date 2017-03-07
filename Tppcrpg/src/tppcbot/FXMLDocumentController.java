/*
Feb 25, 2017
FXMLDocumentController.java, FXMLDocumentController, Joni Sikiö <joni.sikio@student.lut.fi> 
Kuvaus sisällöstä: 
Kehitysympäristö: NetBeans
Muutoshistoria:
Lisenssi: default

 */
package tppcbot;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author Joni Sikiö <joni.sikio@student.lut.fi>
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    public Label label;
    @FXML
    public Button button;
    WebDriver driver;
    @FXML
    public ImageView imageView;
    @FXML
    public TextField usernameField;
    @FXML
    public TextField passwordField;
    String trainerid;
    String acc;
    String pass;
    WebElement element;
    boolean FightBreaker;
    @FXML
    public TextField trainingAccountField;
    @FXML
    public Label tokenLabel;
    @FXML
    public Label captchaLabel;
    @FXML
    public ListView<trainer> trainingListView;
    trainerManager tm;
    @FXML
    public Label levelLabel;
    public Label accountInfoLabel;
    @FXML
    public ImageView pokeImageView;
    @FXML
    public ProgressBar LevelProgress;
    @FXML
    public Label playerLevelLabel;
    int startLevel;
    int currLevel;
    @FXML
    public TextField timeToRest;
    @FXML
    public TextField timeToRestRandom;
    @FXML
    public TextField timeBetweenRest;
    @FXML
    public TextField fightSleepTimeField;
    @FXML
    public TextField fightSleepRandomField;
    @FXML
    public TextField timeBetweenRestRandom;
    long startBigTime;
    long currBigTime;
    long estimatedTime;
    long random;
    @FXML
    public Label pokeLabel1;
    @FXML
    public ImageView pokeImage1;
    @FXML
    public ImageView pokeImage2;
    @FXML
    public Label pokeLabel2;
    @FXML
    public ImageView pokeImage3;
    @FXML
    public Label pokeLabel3;
    @FXML
    public ImageView pokeImage4;
    @FXML
    public Label pokeLabel4;
    @FXML
    public ImageView pokeImage5;
    @FXML
    public Label pokeLabel5;
    @FXML
    public ImageView pokeImage6;
    @FXML
    public Label pokeLabel6;
    @FXML
    public Label pokeLevelLabel1;
    @FXML
    public Label pokeLevelLabel2;
    @FXML
    public Label pokeLevelLabel3;
    @FXML
    public Label pokeLevelLabel4;
    @FXML
    public Label pokeLevelLabel5;
    @FXML
    public Label pokeLevelLabel6;
    ArrayList<Label> pokeLabel;
    ArrayList<ImageView> pokeImage;
    ArrayList<Label> pokeLevelLabel;
    ArrayList<TextField> pokeBreakLabel;
    ArrayList<Label> pokeItemLabel;
    int updateCalc;
    @FXML
    public TextField breakField3;
    @FXML
    public TextField breakField1;
    @FXML
    public TextField breakField4;
    @FXML
    public TextField breakField6;
    @FXML
    public TextField breakField5;
    @FXML
    public TextField breakField2;
    @FXML
    public CheckBox breakEnabled;
    boolean breaker;
    @FXML
    public Label pokeItemLabel1;
    @FXML
    public Label pokeItemLabel2;
    @FXML
    public Label pokeItemLabel3;
    @FXML
    public Label pokeItemLabel4;
    @FXML
    public Label pokeItemLabel5;
    @FXML
    public Label pokeItemLabel6;
    @FXML
    public ComboBox<?> pokemonHuntComboBox;
    @FXML
    public TextField accountNumberField;
    @FXML
    public TextField accountGmailField;
    @FXML
    public TextField accountPrefixField;
    @FXML
    public TextField accountPasswordField;
    @FXML
    public ComboBox<String> accountTeamCombo;
    @FXML
    public RadioButton fPokeRadio;
    @FXML
    public RadioButton sPokeRadio;
    @FXML
    public RadioButton tPokeRadio;
    String starterSelection;
    ArrayList<Account> accList;
    @FXML
    public ComboBox<Account> accountCombo;
    @FXML
    public TextField listToTradeAccount;
    @FXML
    public TextField accountGmailPassField;
    @FXML
    public TextField listToCatchAmount;
    boolean manualBoolean;
    @FXML
    public Button continueManual;
    @FXML
    public Button manual;
    @FXML
    public ProgressBar multiProgressBar;
    float multiProgress;
    int imageCalc;
    
    FXMLDocumentController controller;
    @FXML
    public TextField ssAnneWinCountLabel;
    @FXML
    public Label ssAnneLabel;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = this;
        System.setProperty("webdriver.chrome.driver", "D:\\tppcJavafiles\\chromedriver.exe");
        driver = new ChromeDriver();
        
        
        //System.setProperty("phantomjs.binary.path", "D:\\tppcJavafiles\\phantomjs.exe");
        //driver = new  PhantomJSDriver();
        //driver.manage().window().setSize(new Dimension(1820,980));
        //System.setProperty("webdriver.phantomjs.driver","D:\\tppcJavafiles\\\\phantomjs.exe");
        
        
        //CaptchaBreaker cb = CaptchaBreaker.getInstance();
        //cb.setDriverAndController(controller, driver);
        Battle b =Battle.getInstance();
        b.setDriverAndController(controller, driver);
        Map m = Map.getInstance();
        m.setDriverAndController(controller, driver);
        Misc mi = Misc.getInstance();
        mi.setDriverAndController(controller, driver);
        
        imageCalc = 0;
        multiProgress = 0;
        multiProgressBar.setProgress(multiProgress);
        listToCatchAmount.setText("5");
        Account.loadLogFromFile();
        listToTradeAccount.setText("3461433");
        accList = Account.getAccList();
        accountCombo.getItems().setAll(accList);
        accountCombo.getSelectionModel().select(0);
        updateCalc = 0;
        //LevelProgress.setBlen
        pokeLabel = new ArrayList(Arrays.asList(pokeLabel1,pokeLabel2,pokeLabel3,pokeLabel4,pokeLabel5,pokeLabel6));
        pokeImage = new ArrayList(Arrays.asList(pokeImage1,pokeImage2,pokeImage3,pokeImage4,pokeImage5,pokeImage6));
        pokeLevelLabel = new ArrayList(Arrays.asList(pokeLevelLabel1,pokeLevelLabel2,pokeLevelLabel3,pokeLevelLabel4,pokeLevelLabel5,pokeLevelLabel6));
        pokeBreakLabel = new ArrayList(Arrays.asList(breakField1,breakField2,breakField3,breakField4,breakField5,breakField6));
        pokeItemLabel = new ArrayList (Arrays.asList(pokeItemLabel1,pokeItemLabel2,pokeItemLabel3,pokeItemLabel4,pokeItemLabel5,pokeItemLabel6));
        
        startBigTime = System.currentTimeMillis();
        Time t = Time.getInstance(); 
        t.loadLogFromFile();
        for (int i=0; i<6;i++){
            pokeLabel.get(i).setText("");
            pokeLevelLabel.get(i).setText("");
        }
        
        timeBetweenRest.setText(""+t.getFightSleepRandom());
        timeBetweenRestRandom.setText(""+t.getFightSleepTime());
        timeToRest.setText(""+t.getTimeBetweenRest());
        timeToRestRandom.setText(""+t.getTimeBetweenRestRandom());
        
        fightSleepTimeField.setText(""+t.getTimeToRest());
        fightSleepRandomField.setText(""+t.getTimeToRestRandom());
        accountTeamCombo.getItems().add("Team TPPC");
        accountTeamCombo.getItems().add("Team Rocket");
        accountTeamCombo.getItems().add("Team Magma");
        accountTeamCombo.getItems().add("Team Aqua");
        accountTeamCombo.getItems().add("Team Galactic");
        accountTeamCombo.getSelectionModel().select(0);
        
        accountGmailField.setText("zeroincidentssince");
        
        

        tm = trainerManager.getInstance();
        tm.setDriver(driver);
        FightBreaker = false;
        usernameField.setText("Reddit");
        passwordField.setText("RedditRedditqwer");
        trainingListView.getItems().setAll(tm.getList());
        trainingListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<trainer>() {
        @Override
            public void changed(ObservableValue<? extends trainer> observable, trainer oldValue, trainer newValue) {
                // Your action here
                trainingAccountField.setText(newValue.getAccountNumber());
                tm.setSelected(newValue);
                levelLabel.setText("Level: "+tm.getSelected().getLevel());
            }
        });
        
        trainingListView.getSelectionModel().select(tm.getSelected());
        
        
    }    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        Task task = new Task<Void>() {
            @Override public Void call() {
                Battle b = Battle.getInstance();
                b.fight(trainingAccountField.getText(), 0);
                
            return null;
        }
    };
        task.exceptionProperty().addListener((observable, oldValue, newValue) ->  {
            if(newValue != null) {
              Exception ex = (Exception) newValue;
              ex.printStackTrace();
            }
        });
        new Thread(task).start();
    }  


    @FXML
    private void StopBotAction(ActionEvent event) {
        FightBreaker = true;
    }

    @FXML
    private void loginButtonAction(ActionEvent event) {
        
        Task task = new Task<Void>() {
            @Override public Void call() {
            Misc mi = Misc.getInstance();
            mi.login(usernameField.getText(), passwordField.getText());

            Platform.runLater(new Runnable() {
                @Override public void run() {
                    String playerLevel = driver.findElement(By.xpath("/html/body/div[@id='right']/ul/li[@class='lvlitem']")).getText();
                    playerLevel = (playerLevel.substring(7, 14)).replaceAll(" ", "");
                    startLevel = Integer.valueOf(playerLevel);

                    String imageString = driver.findElement(By.xpath("/html/body/div[@id='right']/ul/li[@class='pb']")).getAttribute("style");
                    System.out.println(imageString);
                    
                    imageString = imageString.substring(imageString.indexOf("http:"), imageString.indexOf(")", imageString.indexOf("http:")));
                    System.out.println(imageString);
                    pokeImageView.setImage(new Image(imageString));

                    updateUI();

                }
            });



            return null;
        }
    };
    task.exceptionProperty().addListener((observable, oldValue, newValue) ->  {
            if(newValue != null) {
              Exception ex = (Exception) newValue;
              ex.printStackTrace();
            }
        });
    new Thread(task).start();
    }

    public void updateUI(){
        updateCalc = 0;
        if(imageCalc < 1){
            System.out.println("Updating images");
        }
        while(updateCalc++ < 3){
            try {
                if (driver.findElements(By.xpath("/html/body/div[@id='right']/ul/li[@class='hpSide']/img")).size() > 0){// && updateCalc > 6){
                    System.out.println("Updating!");
                    updateCalc = 0;
                    //String imageString = driver.findElement(By.xpath("/html/body/div[@id='right']/ul/li[@class='pb']")).getAttribute("style");
                    //imageString = imageString.substring(23, imageString.length()-3);
                    //pokeImageView.setImage(new Image(imageString));
                    String progress = driver.findElement(By.xpath("/html/body/div[@id='right']/ul/li[@class='hpSide']/img")).getAttribute("title");
                    progress = progress.substring(26, progress.length()-1);
                    double hello = Double.valueOf(progress)/100;
                    LevelProgress.setProgress(hello);
                    String playerLevel = driver.findElement(By.xpath("/html/body/div[@id='right']/ul/li[@class='lvlitem']")).getText();
                    playerLevel = (playerLevel.substring(7, 14)).replaceAll(" ", "");
                    currLevel = Integer.valueOf(playerLevel);
                    //playerLevelLabel.setText(playerLevel.substring(0, playerLevel.length()));
                    int levelDiff = currLevel - startLevel;
                    playerLevelLabel.setText("Level: "+currLevel+" (" + levelDiff+")");

                    /**/
                   for(int i=0;i<6;i++){
                        //int rlevel;
                        String tempLevel;
                        String tempNumber = "";

                        String tempItem = "";
                        if(i<3){
                            tempLevel = driver.findElement(By.xpath("/html/body/div[@id='right']/ul/li[@id='rs']/div[1]/img["+(i+1)+"]")).getAttribute("onclick");
                            tempNumber = tempLevel.substring(tempLevel.indexOf("level: '")+8, tempLevel.indexOf("'", tempLevel.indexOf("level: '")+8));
                            tempItem = tempLevel.substring(tempLevel.indexOf("item: '")+7, tempLevel.indexOf("'", tempLevel.indexOf("item: '")+7));
                            if(imageCalc < 1){
                                pokeImage.get(i).setImage(new Image(driver.findElement(By.xpath("/html/body/div[@id='right']/ul/li[@id='rs']/div[1]/img["+(i+1)+"]")).getAttribute("src")));
                                pokeLabel.get(i).setText(driver.findElement(By.xpath("/html/body/div[@id='right']/ul/li[@id='rs']/div[1]/img["+(i+1)+"]")).getAttribute("title").replaceAll("Shiny", "S.").replaceAll("Dark", "D.").replaceAll("Golden", "G."));
                            }
                            pokeLevelLabel.get(i).setText("Lv: "+tempNumber);
                            pokeItemLabel.get(i).setText(tempItem);

                        } else if (i <6){
                            tempLevel = driver.findElement(By.xpath("/html/body/div[@id='right']/ul/li[@id='rs']/div[2]/img["+(i-2)+"]")).getAttribute("onclick");
                            tempNumber = tempLevel.substring(tempLevel.indexOf("level: '")+8, tempLevel.indexOf("'", tempLevel.indexOf("level: '")+8));
                            tempItem = tempLevel.substring(tempLevel.indexOf("item: '")+7, tempLevel.indexOf("'", tempLevel.indexOf("item: '")+7));
                            if(imageCalc < 1){
                                pokeImage.get(i).setImage(new Image(driver.findElement(By.xpath("/html/body/div[@id='right']/ul/li[@id='rs']/div[2]/img["+(i-2)+"]")).getAttribute("src")));
                                pokeLabel.get(i).setText(driver.findElement(By.xpath("/html/body/div[@id='right']/ul/li[@id='rs']/div[2]/img["+(i-2)+"]")).getAttribute("title").replaceAll("Shiny", "S.").replaceAll("Dark", "D.").replaceAll("Golden", "G."));
                            }
                            pokeLevelLabel.get(i).setText("Lv: "+tempNumber);
                            pokeItemLabel.get(i).setText(tempItem);
                        }
                    }
                     /***/
                    
                    if(imageCalc++ > 50){
                            imageCalc = 0;
                    }
                    
                    //pokeList
                    
                }
                break;
            } catch(NoSuchElementException | org.openqa.selenium.StaleElementReferenceException e) {
                System.out.println("Stale element exception " + updateCalc);
            }
        }
    }
               
    
    public void bigSleep(){
        currBigTime = System.currentTimeMillis();
        long timeElapsed = (long)((currBigTime - startBigTime)/ 1000); //seconds

        if ( timeElapsed>  ((random + Integer.valueOf(timeBetweenRest.getText()))*60)){ //converted to seconds
            System.out.println("Time elapsed: " + timeElapsed);
            
            try {
                double random = Integer.valueOf(timeToRestRandom.getText())*Math.random(); //minutes
                long sleepv = (long) (Double.valueOf(timeToRest.getText())+Math.ceil(random))*60*1000; //millisecond

                System.out.println("Sleeping for: " + Math.ceil(sleepv/1000) + " seconds");
                Thread.sleep(sleepv);
            } catch (InterruptedException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            random = (long) Math.ceil(Math.random()*Integer.valueOf(timeBetweenRestRandom.getText())); //minutes
            startBigTime = System.currentTimeMillis();
        }
        
}
    
    public void defSleep(){
        try {
            long sleepv = (long) (Integer.valueOf(fightSleepTimeField.getText()) + Math.ceil(Integer.valueOf(fightSleepRandomField.getText())*Math.random()));
            //System.out.println(sleepv);
            Thread.sleep(sleepv);
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void startSwapTokensAction(ActionEvent event) {
        Task task = new Task<Void>() {
            @Override public Void call() {
                Misc mi = Misc.getInstance();
                updateMessage(""+mi.SwapToken());

                return null;
            }

        };
        task.exceptionProperty().addListener((observable, oldValue, newValue) ->  {
            if(newValue != null) {
              Exception ex = (Exception) newValue;
              ex.printStackTrace();
            }
        });
        tokenLabel.textProperty().bind(task.messageProperty());
        new Thread(task).start();

}


    



    @FXML
    private void logoutButtonAction(ActionEvent event) {
        Misc mi = Misc.getInstance();
        mi.logout();
    }

    @FXML
    private void saveSettingsAction(ActionEvent event) {
        Time t = Time.getInstance();
        t.setFightSleepRandom(Integer.valueOf(timeBetweenRest.getText()));
        t.setFightSleepTime(Integer.valueOf(timeBetweenRestRandom.getText()));
        t.setTimeBetweenRest(Integer.valueOf(timeToRest.getText()));
        t.setTimeBetweenRestRandom(Integer.valueOf(timeToRestRandom.getText()));
        
        t.setTimeToRest(Integer.valueOf(fightSleepTimeField.getText()));
        t.setTimeToRestRandom(Integer.valueOf(fightSleepRandomField.getText()));
        t.saveLogToFile();
        
    }

    @FXML
    private void pokeButtonAction1(ActionEvent event) {
        
    }

    @FXML
    private void pokeButtonAction2(ActionEvent event) {
    }

    @FXML
    private void pokeButtonAction3(ActionEvent event) {
    }

    @FXML
    private void pokeButtonAction4(ActionEvent event) {
    }

    @FXML
    private void pokeButtonAction5(ActionEvent event) {
    }

    @FXML
    private void pokeButtonAction6(ActionEvent event) {
    }

    @FXML
    private void startSafariZoneAction(ActionEvent event) {

        
    }

    @FXML
    private void startSSAnneAction(ActionEvent event) {
        
        Task task = new Task<Void>() {
            @Override public Void call() {
                long startTime = System.currentTimeMillis();
                //driver.get("http://www.tppcrpg.net/battle.php?Battle=SSAnne");
                Misc mi = Misc.getInstance();
                mi.ssAnne();
                return null;
            }

        };
        task.exceptionProperty().addListener((observable, oldValue, newValue) ->  {
            if(newValue != null) {
              Exception ex = (Exception) newValue;
              ex.printStackTrace();
            }
        });
        new Thread(task).start();
    }

    @FXML
    private void startHunterBotAction(ActionEvent event) {
    }

    @FXML
    private void pokemonHuntComboBoxAction(ActionEvent event) {
    }

    @FXML
    private void accountCreateAction(ActionEvent event) {
        Task task = new Task<Void>() {
            @Override public Void call() {
                
                Misc mi = Misc.getInstance();
                mi.makeAccounts();
                
                return null;
            }
    };
    task.exceptionProperty().addListener((observable, oldValue, newValue) ->  {
            if(newValue != null) {
              Exception ex = (Exception) newValue;
              ex.printStackTrace();
            }
        });
    new Thread(task).start();
        

        
        
        
    }

    @FXML
    private void tPokeRadioAction(ActionEvent event) {
        fPokeRadio.setSelected(false);
        sPokeRadio.setSelected(false);
        starterSelection = "3";
    }

    @FXML
    private void fPokeRadioAction(ActionEvent event) {
        tPokeRadio.setSelected(false);
        sPokeRadio.setSelected(false);
        starterSelection = "1";
    }

    @FXML
    private void sPokeRadioAction(ActionEvent event) {
        fPokeRadio.setSelected(false);
        tPokeRadio.setSelected(false);
        starterSelection = "2";
    }

    @FXML
    private void listToTradeAction(ActionEvent event) {
        Task task = new Task<Void>() {
        @Override public Void call() {
            Misc mi = Misc.getInstance();
            mi.accountsTrade();
        System.out.println("Ending trading making.");
        
        return null;
            }
    };
    task.exceptionProperty().addListener((observable, oldValue, newValue) ->  {
            if(newValue != null) {
              Exception ex = (Exception) newValue;
              ex.printStackTrace();
            }
        });
    new Thread(task).start();
        
    }

    @FXML
    private void listToCatchAction(ActionEvent event) {

        Task task = new Task<Void>() {
            @Override public Void call() {
                Misc mi = Misc.getInstance();
                mi.accountsTrade();

                return null;
        }
    };
    task.exceptionProperty().addListener((observable, oldValue, newValue) ->  {
            if(newValue != null) {
              Exception ex = (Exception) newValue;
              ex.printStackTrace();
            }
        });
    new Thread(task).start();
        
    }

    @FXML
    private void verifyAccountsAction(ActionEvent event) {
        Task task = new Task<Void>() {
        @Override public Void call() {
        
            Misc mi = Misc.getInstance();
            mi.verifyAccounts();

            return null;
        }
    };
    task.exceptionProperty().addListener((observable, oldValue, newValue) ->  {
            if(newValue != null) {
              Exception ex = (Exception) newValue;
              ex.printStackTrace();
            }
        }); 
    new Thread(task).start();
        
    }

    @FXML
    private void getTeamPromoAction(ActionEvent event) {
        Task task = new Task<Void>() {
            @Override public Void call() {
            Misc mi = Misc.getInstance();
            mi.accountsPromo();
            System.out.println("Ending team promo hunting.");

            return null;
            }   
        };
    task.exceptionProperty().addListener((observable, oldValue, newValue) ->  {
            if(newValue != null) {
              Exception ex = (Exception) newValue;
              ex.printStackTrace();
            }
        });
    new Thread(task).start();
    }

    @FXML
    private void continueManualAction(ActionEvent event) {
        manualBoolean = true;
        manual.visibleProperty().set(true);
        
    }

    @FXML
    private void manualAction(ActionEvent event) {
        Task task = new Task<Void>() {
            @Override public Void call() {
                Misc mi = Misc.getInstance();
                mi.accountsManual();

                return null;
            }
        };
    task.exceptionProperty().addListener((observable, oldValue, newValue) ->  {
            if(newValue != null) {
              Exception ex = (Exception) newValue;
              ex.printStackTrace();
            }
        });
    new Thread(task).start();
        
    }

    @FXML
    private void startPreFighterAction(ActionEvent event) {
        
        Task task = new Task<Void>() {
        @Override public Void call() {
            Misc mi = Misc.getInstance();
            mi.accountsPreFight();

            return null;
            }
        };
    task.exceptionProperty().addListener((observable, oldValue, newValue) ->  {
            if(newValue != null) {
              Exception ex = (Exception) newValue;
              ex.printStackTrace();
            }
        });
    new Thread(task).start();
    }

    
    
    public void catchMap(String choice, String vari){
        if(choice.equals("map")){ //Map by map,  vari = map number
        
        } else if (choice.equals("poke")){ //Map by poke, vari = poke name
                
        } else if (choice.equals("item")){ //Map by item, vari = item name
                
        }
    
    }

    @FXML
    private void completeAllAltTradesAction(ActionEvent event) {
        Task task = new Task<Void>() {
        @Override public Void call() {
            

        driver.get("http://www.tppcrpg.net/complete_trade.php");
        ArrayList<WebElement> trades = (ArrayList<WebElement>) driver.findElements(By.partialLinkText("Trade #"));
        ArrayList<String> tradesUrl = new ArrayList();
        for(int i=0; i<trades.size();i++){
            tradesUrl.add(trades.get(i).getAttribute("href"));
        }
        
        for(int i=0; i<tradesUrl.size();i++){
            System.out.println(tradesUrl.get(i));
            driver.get(tradesUrl.get(i));
            if(driver.findElements(By.id("FP")).size() > 0){
                Select myPoke = new Select(driver.findElement(By.id("FP")));
                System.out.println("My pokes in trade: " + myPoke.getOptions().size());

                if(myPoke.getOptions().size() == 0 && driver.findElements(By.className("submit")).size() > 0){
                    driver.findElement(By.className("submit")).click();
                }
            }
            defSleep();
        }
        return null;
        }
        
    };
        task.exceptionProperty().addListener((observable, oldValue, newValue) ->  {
            if(newValue != null) {
              Exception ex = (Exception) newValue;
              ex.printStackTrace();
            }
        });
    new Thread(task).start();
    }
    
}
