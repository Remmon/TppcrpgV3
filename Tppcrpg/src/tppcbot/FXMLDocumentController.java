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
import java.util.Random;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    public TextField usernameField;
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
    //public ComboBox<?> pokemonHuntComboBox;
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
    @FXML
    private ComboBox<String> pokemonHuntMapComboBox;
    @FXML
    private ComboBox<String> pokemonHuntPokeComboBox;
    @FXML
    private ComboBox<String> pokemonHuntItemComboBox;
    String mapChoice = "map";
    @FXML
    private TextField startHunterAmount;
    @FXML
    private RadioButton radioMap;
    @FXML
    private RadioButton radioPoke;
    @FXML
    private RadioButton radioItem;
    @FXML
    public Label accListNumberLabel;
    @FXML
    public CheckBox listToCatchCheck;
    @FXML
    private TextField fightSleepMeanField;
    @FXML
    private TextField fightSleepDeviationField;
    @FXML
    private TextField deviationBetweenRest;
    @FXML
    private TextField restMean;
    @FXML
    private TextField restDeviation;
    @FXML
    private TextField meanBetweenRest;
    Random ranGen;
    double ran;
    @FXML
    private Label restAverageLabel;
    @FXML
    private TextField startSafariZonePoints;
    @FXML
    private AnchorPane startSilphCo;
    @FXML
    private TextField legendaryAmount;
    @FXML
    private ComboBox<?> legendaryCombo;
    @FXML
    public ComboBox<Account> accountMainCombo;
    @FXML
    public TextField accountField;
    @FXML
    public ListView<Account> accountExcludedCombo;
    @FXML
    public Label battleExpLabel;
    @FXML
    public ListView<String> multiAccListView;
    @FXML
    private TextField listToBattleAccount;
    @FXML
    private TextField listToBattleAmount;
    @FXML
    public CheckBox listToRandomCheck;
    @FXML
    public TextField listToRandom;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = this;
        System.setProperty("webdriver.chrome.driver", "D:\\tppcJavafiles\\chromedriver.exe");
        driver = new ChromeDriver();
        ranGen = new Random();
        
        //System.setProperty("phantomjs.binary.path", "D:\\tppcJavafiles\\phantomjs.exe");
        //driver = new  PhantomJSDriver();
        //driver.manage().window().setSize(new Dimension(1820,980));
        //System.setProperty("webdriver.phantomjs.driver","D:\\tppcJavafiles\\\\phantomjs.exe");
        
        
        CaptchaBreaker cb = CaptchaBreaker.getInstance();
        cb.setDriverAndController(controller, driver);
        Battle b =Battle.getInstance();
        b.setDriverAndController(controller, driver);
        Map m = Map.getInstance();
        m.setDriverAndController(controller, driver);
        Misc mi = Misc.getInstance();
        mi.setDriverAndController(controller, driver);
        Party pa = Party.getInstance();
        pa.setController(controller, driver);
        
        pokemonHuntMapComboBox.getItems().setAll(m.getMaps());
        pokemonHuntMapComboBox.getSelectionModel().selectFirst();
        pokemonHuntPokeComboBox.getItems().setAll(m.getPokes());
        pokemonHuntPokeComboBox.getSelectionModel().selectFirst();
        pokemonHuntItemComboBox.getItems().setAll(m.getItems());
        pokemonHuntItemComboBox.getSelectionModel().selectFirst();

        imageCalc = 0;
        multiProgress = 0;
        multiProgressBar.setProgress(multiProgress);
        listToCatchAmount.setText("5");
        Account.loadLogFromFile();
        listToTradeAccount.setText("3461715");
        accList = Account.getAccList();
        if(!accList.isEmpty()){
            accountCombo.getItems().setAll(accList.subList(1, accList.size()));
            accountCombo.getSelectionModel().selectFirst();
            accountMainCombo.getItems().setAll(accList.subList(1, accList.size()));
            accountMainCombo.getSelectionModel().select(accList.get(0));
        } else {
            Account.getAccList().add(new Account("Placeholder", "", "", ""));
            accountCombo.getItems().setAll(accList.subList(1, accList.size()));
            accountCombo.getSelectionModel().selectFirst();
            accountMainCombo.getItems().setAll(accList.subList(1, accList.size()));
        }
        accListNumberLabel.setText((accList.size()-1)+"");
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
        
        meanBetweenRest.setText(""+t.getMeanBetweenRest());
        deviationBetweenRest.setText(""+t.getDeviationBetweenRest());
        restMean.setText(""+t.getRestMean());
        restDeviation.setText(""+t.getRestDeviation());
        
        fightSleepMeanField.setText(""+t.getFightSleepMean());
        fightSleepDeviationField.setText(""+t.getFightSleepDeviation());
        Account.loadLogFromFile2();
        accountExcludedCombo.getItems().setAll(Account.getExcludeList());
        
        accountTeamCombo.getItems().add("Team TPPC");
        accountTeamCombo.getItems().add("Team Rocket");
        accountTeamCombo.getItems().add("Team Magma");
        accountTeamCombo.getItems().add("Team Aqua");
        accountTeamCombo.getItems().add("Team Galactic");
        accountTeamCombo.getSelectionModel().select(0);
        
        accountGmailField.setText("officetheguy");
        ran = Math.abs(ranGen.nextGaussian()*Integer.valueOf(deviationBetweenRest.getText()) + Integer.valueOf(meanBetweenRest.getText()));

        tm = trainerManager.getInstance();
        tm.setDriver(driver);
        FightBreaker = false;
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
            mi.login(accountMainCombo.getSelectionModel().getSelectedItem().getAccount(), accountMainCombo.getSelectionModel().getSelectedItem().getPassword());

            Platform.runLater(new Runnable() {
                @Override public void run() {
                    String playerLevel = driver.findElement(By.xpath("/html/body/div[@id='right']/ul/li[@class='lvlitem']")).getText();
                    playerLevel = (playerLevel.substring(7, 14)).replaceAll(" ", "");
                    startLevel = Integer.valueOf(playerLevel);

                    String imageString = driver.findElement(By.xpath("/html/body/div[@id='right']/ul/li[@class='pb']")).getAttribute("style");
                    System.out.println(imageString);
                    
                    imageString = imageString.substring(imageString.indexOf("http:"), imageString.indexOf("\")", imageString.indexOf("http:")));
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
        double timeElapsed = (double)((currBigTime - startBigTime)/ 1000); //seconds
        
        if ( timeElapsed>  ((ran)*60)){ //converted to seconds
            System.out.println("Time elapsed: " + ((timeElapsed/60)-(timeElapsed%60)) + " minutes and " + timeElapsed%60 + " seconds");
            
            try {
                ran = Math.abs(ranGen.nextGaussian()*Integer.valueOf(restDeviation.getText()) + Integer.valueOf(restMean.getText()));
                long sleepv = (long) (ran*60*1000); //millisecond

                System.out.println("Sleeping for: " + (sleepv)/1000/60 + " minutes");
                Thread.sleep(sleepv);
            } catch (InterruptedException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ran = Math.abs(ranGen.nextGaussian()*Integer.valueOf(deviationBetweenRest.getText()) + Integer.valueOf(meanBetweenRest.getText()));
            System.out.println("Next sleep after: " + ran + " minutes");
            startBigTime = System.currentTimeMillis();
        }
        
}
    
    public void defSleep(){
        try {

            long sleepv = (long)(Math.abs(ranGen.nextGaussian()*Integer.valueOf(fightSleepDeviationField.getText())+Integer.valueOf(fightSleepMeanField.getText())));
            //System.out.println("Random sleep: " + sleepv + " ms");
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
        t.setMeanBetweenRest(Integer.valueOf(meanBetweenRest.getText()));
        t.setDeviationBetweenRest(Integer.valueOf(deviationBetweenRest.getText()));
        t.setRestMean(Integer.valueOf(restMean.getText()));
        t.setRestDeviation(Integer.valueOf(restDeviation.getText()));
        
        t.setFightSleepMean(Integer.valueOf(fightSleepMeanField.getText()));
        t.setFightSleepDeviation(Integer.valueOf(fightSleepDeviationField.getText()));
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
        
        Task task = new Task<Void>() {
            @Override public Void call() {
   
                int safariPoints = 0;
                boolean safariBreaker = false;
                FightBreaker = false;
                driver.get("http://www.tppcrpg.net/safari_zone.php");
                while(true){
                    if(FightBreaker){
                        break;
                    }
                    ArrayList<WebElement> imageList = (ArrayList<WebElement>) driver.findElements(By.tagName("img"));

                    if(imageList.size() > 0 ){
                        for(int e=0;e<imageList.size();e++){
                            if(imageList.get(e).getAttribute("alt").equals("Safari Zone")){
                                imageList.get(e).click();
                                break;
                            }
                        }
                    } 
                    if(driver.findElements(By.name("Find")).size() > 0){
                        driver.findElement(By.name("Find")).click();
                    }

                    controller.defSleep();
                    if (driver.findElements(By.linkText("Submit To Safari Zone Catching Contest!")).size() > 0){
                        driver.findElement(By.linkText("Submit To Safari Zone Catching Contest!")).click();
                        for(int b=0;b<5;b++){
                            if(driver.findElements(By.xpath("/html/body/div[@id='body']/div[@id='inner']/blockquote[@class='success']/strong")).size() > 0){

                                safariPoints = Integer.valueOf(driver.findElement(By.xpath("/html/body/div[@id='body']/div[@id='inner']/blockquote[@class='success']/strong")).getText().replaceAll(",", "").replaceAll(" ", ""));
                                System.out.println(safariPoints);
                                if(Integer.valueOf(controller.startSafariZonePoints.getText()) < safariPoints){
                                    safariBreaker = true;
                                }
                                break;
                            }

                        }
                    }
                    if (driver.findElements(By.linkText("Lets Battle!")).size() > 0 ){
                        driver.get("http://www.tppcrpg.net/safari_zone.php");
                    } else if (driver.findElements(By.partialLinkText("Return To")).size() > 0){
                        driver.findElement(By.partialLinkText("Return To")).click();
                    }
                    if(safariBreaker){
                        break;
                    }
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
        multiAccListView.getItems().add("trade,"+listToTradeAccount.getText()+"");
    }

    @FXML
    private void listToCatchAction(ActionEvent event) {        
        multiAccListView.getItems().add("catch,"+listToCatchAmount.getText()+"");

        
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
            multiAccListView.getItems().add("promo");
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

            multiAccListView.getItems().add("prepromo");
    
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

    private void startSafariZonePoints(ActionEvent event) {
    }


    
    @FXML
    private void startHunterBotAction(ActionEvent event) {
        Task task = new Task<Void>() {
            @Override public Void call() {
                Map m = Map.getInstance();
                if(mapChoice.equals("map")){
                    m.startMap("map", pokemonHuntMapComboBox.getSelectionModel().getSelectedItem(), Integer.valueOf(startHunterAmount.getText()));
                } else if(mapChoice.equals("poke")){
                    m.startMap("poke", pokemonHuntPokeComboBox.getSelectionModel().getSelectedItem(), Integer.valueOf(startHunterAmount.getText()));
                } else if(mapChoice.equals("item")){
                    m.startMap("item", pokemonHuntItemComboBox.getSelectionModel().getSelectedItem(), Integer.valueOf(startHunterAmount.getText()));
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

    @FXML
    private void radioMapAction(ActionEvent event) {
        mapChoice = "map";
        pokemonHuntMapComboBox.visibleProperty().set(true);
        pokemonHuntPokeComboBox.visibleProperty().set(false);
        pokemonHuntItemComboBox.visibleProperty().set(false);
        
        radioPoke.selectedProperty().set(false);
        radioItem.selectedProperty().set(false);
    }

    @FXML
    private void radioPokeAction(ActionEvent event) {
        mapChoice = "poke";
        pokemonHuntMapComboBox.visibleProperty().set(false);
        pokemonHuntPokeComboBox.visibleProperty().set(true);
        pokemonHuntItemComboBox.visibleProperty().set(false);
        
        radioMap.selectedProperty().set(false);
        radioItem.selectedProperty().set(false);
    }

    @FXML
    private void radioItemAction(ActionEvent event) {
        mapChoice = "item";
        pokemonHuntMapComboBox.visibleProperty().set(false);
        pokemonHuntPokeComboBox.visibleProperty().set(false);
        pokemonHuntItemComboBox.visibleProperty().set(true);
        
        radioPoke.selectedProperty().set(false);
        radioMap.selectedProperty().set(false);
    }

    @FXML
    private void standardRandomAction(ActionEvent event) { // fix dis
        ArrayList<Double> randomNumber1 = new ArrayList();
        ArrayList<Double> randomNumber2 = new ArrayList();
        ArrayList<Double> randomNumber3 = new ArrayList();

        
        double number = 10.0;
        for(int i=0; i< number;i++){
            ran = Math.abs(ranGen.nextGaussian()*Integer.valueOf(deviationBetweenRest.getText()) + Integer.valueOf(meanBetweenRest.getText()));
            ran = Math.ceil(ran);
            randomNumber1.add(ran);
        } 
        for(int i=0; i< number;i++){
            ran = Math.abs(ranGen.nextGaussian()*Integer.valueOf(restDeviation.getText()) + Integer.valueOf(restMean.getText()));
            ran = Math.ceil(ran);
            randomNumber2.add(ran);
        }
        for(int i=0; i< number;i++){
            ran = Math.abs(ranGen.nextGaussian()*Integer.valueOf(fightSleepDeviationField.getText()) + Integer.valueOf(fightSleepMeanField.getText()));
            ran = Math.ceil(ran);
            randomNumber3.add(ran);
        } 
        restAverageLabel.setText("Random between rest: " + randomNumber1+"\n"+"Random rest: " + randomNumber2+"\n"+"Random between actions: " + randomNumber3+"\n");
    }

    @FXML
    private void trainingChallengeAction(ActionEvent event) {
        //Add "was unaffected!" and ""
        
        Task task = new Task<Void>() {
        @Override public Void call() {
            FightBreaker = false;
            long startTime = System.currentTimeMillis();
            long startTrainingTime =System.currentTimeMillis();
            startBigTime = System.currentTimeMillis();
            driver.get("http://www.tppcrpg.net/training_challenge.php");
            if(driver.findElements(By.linkText("Battle Now!")).size() > 0){
                driver.findElement(By.linkText("Battle Now!")).click();
            } else if (driver.findElements(By.partialLinkText("Your Starter")).size() > 0 ){
                driver.findElement(By.partialLinkText("Your Starter")).click();
                driver.get("http://www.tppcrpg.net/training_challenge.php");
                driver.findElement(By.linkText("Battle Now!")).click();
            }
            while(true){
                try{
                    System.out.println((System.currentTimeMillis() - startTrainingTime)/60/1000+" Minutes ran.");
                    if((System.currentTimeMillis() - startTrainingTime) > 4*60*60*1000){
                        Misc mi = Misc.getInstance();
                        mi.logout();
                        Thread.sleep(2*60*60*1000 + 10*60*1000);
                        mi.login(usernameField.getText(), passwordField.getText());
                        driver.get("http://www.tppcrpg.net/training_challenge.php");
                        if(driver.findElements(By.linkText("Battle Now!")).size() > 0){
                            driver.findElement(By.linkText("Battle Now!")).click();
                        } else if (driver.findElements(By.partialLinkText("Your Starter")).size() > 0 ){
                            driver.findElement(By.partialLinkText("Your Starter")).click();
                            driver.get("http://www.tppcrpg.net/training_challenge.php");
                            driver.findElement(By.linkText("Battle Now!")).click();
                        }
                        startTrainingTime = System.currentTimeMillis();
                        startBigTime = System.currentTimeMillis();
                        startTime = System.currentTimeMillis();
                    }
                    if (FightBreaker){   //UI break button
                        break;
                    }
                    estimatedTime  = System.currentTimeMillis() - startTime;
                    if (estimatedTime > 10000){
                        driver.get("http://www.tppcrpg.net/training_challenge.php");
                        if(driver.findElements(By.linkText("Battle Now!")).size() > 0){
                            driver.findElement(By.linkText("Battle Now!")).click();
                        } else if (driver.findElements(By.partialLinkText("Your Starter")).size() > 0 ){
                            driver.findElement(By.partialLinkText("Your Starter")).click();
                            driver.get("http://www.tppcrpg.net/training_challenge.php");
                            driver.findElement(By.linkText("Battle Now!")).click();
                        }
                        startTime = System.currentTimeMillis();
                    }
                    if (driver.findElements(By.id("Validate")).size() > 0 ){
                        startTime = System.currentTimeMillis();
                        CaptchaBreaker cb = CaptchaBreaker.getInstance();
                        cb.solveCaptcha("Congratulations!");
                        driver.get("http://www.tppcrpg.net/training_challenge.php");
                        if(driver.findElements(By.linkText("Battle Now!")).size() > 0){
                            driver.findElement(By.linkText("Battle Now!")).click();
                        } else if (driver.findElements(By.partialLinkText("Your Starter")).size() > 0 ){
                            driver.findElement(By.partialLinkText("Your Starter")).click();
                            driver.get("http://www.tppcrpg.net/training_challenge.php");
                            driver.findElement(By.linkText("Battle Now!")).click();
                        }
                        startTime = System.currentTimeMillis();
                    }else if(driver.findElements(By.linkText("Restart Battle")).size() != 0){
                        startTime = System.currentTimeMillis();
                        driver.findElement(By.linkText("Restart Battle")).click();

                    } else if (driver.findElements(By.className("submit")).size() != 0){
                        startTime = System.currentTimeMillis();
                        driver.findElement(By.className("submit")).click();
                    }
                    /*Platform.runLater(new Runnable() {
                    @Override public void run() {
                            controller.updateUI();
                         }
                     });*/
                    controller.defSleep();
                    if(driver.findElements(By.className("Trainer1")).size() > 0 || driver.findElements(By.className("Trainer2")).size() > 0){
                        List<WebElement> trainer1 = driver.findElements(By.className("Trainer1"));
                        List<WebElement> trainer2 = driver.findElements(By.className("Trainer2"));
                        for(int i=0;i<trainer1.size();i++){
                            System.out.println(trainer1.get(i).getText());
                            if(trainer1.get(i).getText().contains("wasn't very effective!")){
                                try{
                                    Select select = new Select(driver.findElement(By.name("MyMove")));
                                    select.selectByIndex(1);
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }
                        for(int i=0;i<trainer2.size();i++){
                            System.out.println(trainer2.get(i).getText());
                            if(trainer2.get(i).getText().contains("was unaffected!")){
                                try{
                                    Select select = new Select(driver.findElement(By.name("MyMove")));
                                    select.selectByIndex(1);
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }
                    }
                    
                    
                    
                    
                    
                } catch (Exception e){
                    e.printStackTrace();
                }
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

    @FXML
    private void legendaryBotAction(ActionEvent event) {
    }

    @FXML
    private void accountAddAction(ActionEvent event) {
        Account.accList.add(new Account(accountField.getText(), passwordField.getText(), "none", "none"));
    }

    @FXML
    private void accountMainAction(ActionEvent event) {
        List<Account> acc = new ArrayList();
        Account.accList.set(0, accountMainCombo.getValue());
        accountMainCombo.getSelectionModel().select(accList.get(0));

    }

    @FXML
    private void accountExcludeAction(ActionEvent event) {
        Account.excludeList.add(accountCombo.getSelectionModel().getSelectedItem());
        accountExcludedCombo.getItems().setAll(Account.getExcludeList());
        
        
    }

    @FXML
    private void accountIncludeAction(ActionEvent event) {
        Account.excludeList.remove(accountExcludedCombo.getSelectionModel().getSelectedItem());
        accountExcludedCombo.getItems().setAll(Account.getExcludeList());
    
    }

    @FXML
    private void loadAccountsFromGmailAction(ActionEvent event) {

        Task task = new Task<Void>() {
            @Override public Void call() {

                Misc mi = Misc.getInstance();
                mi.loadAccounts();
                accountCombo.getItems().setAll(accList.subList(1, accList.size()));
                accountMainCombo.getItems().setAll(accList.subList(1, accList.size()));

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
    private void startMultiAccBot(ActionEvent event) {
        Task task = new Task<Void>() {
           @Override public Void call() {
                Misc mi = Misc.getInstance();
                mi.multiAcc();
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
    private void emptyMultiAccBot(ActionEvent event) {
        multiAccListView.getItems().remove(multiAccListView.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void listToBattleAction(ActionEvent event) {

            multiAccListView.getItems().add("fight,"+listToBattleAccount.getText()+","+Integer.valueOf(listToBattleAmount.getText()));

    }



}
