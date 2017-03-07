/*
Feb 25, 2017
CaptchaBreaker.java, CaptchaBreaker, Joni Sikiö <joni.sikio@student.lut.fi> 
Kuvaus sisällöstä: 
Kehitysympäristö: NetBeans
Muutoshistoria:
Lisenssi: default

 */
package tppcbot;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Joni Sikiö <joni.sikio@student.lut.fi>
 */
public class CaptchaBreaker {
    public static CaptchaBreaker cb = null;
    Map<String, ArrayList<String>> map;
    String[] Letters = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
    FXMLDocumentController controller;
    WebDriver driver;
    
    private CaptchaBreaker(){
        map = new HashMap<>();
        
        for(int i=0; i<Letters.length;i++){
            map.put(Letters[i], new ArrayList());
            File file = new File("D:\\tppcJavafiles\\"+Letters[i]+".txt");
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                   // process the line.
                   map.get(Letters[i]).add(line);
                }
            }catch (IOException ex) {
                Logger.getLogger(CaptchaBreaker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        
    }
    
    public void setDriverAndController(FXMLDocumentController dc, WebDriver d){
        controller = dc;
        driver = d;
    }
    
    public String solveCaptcha(String attrib){


        String output = "";
        ArrayList<WebElement> allImages = (ArrayList<WebElement>) driver.findElements(By.tagName("img"));
        for (int i=0; i<allImages.size(); i++){
            if(allImages.get(i).getAttribute("alt").equals(attrib/*"Congratulations!"*/)){                        
                output = cb.crush(driver, allImages.get(i));
                if (!output.equals("")){
                    if(driver.findElements(By.id("Validate")).size() > 0){
                        driver.findElement(By.id("Validate")).sendKeys(output);
                    } else if (driver.findElements(By.name("Validate")).size() > 0){
                        driver.findElement(By.name("Validate")).sendKeys(output);
                    }

                    driver.findElement(By.className("submit")).click();
                }
                break;
            }
        }
        controller.defSleep();
        return output;
    } 
  
    
    
    public static CaptchaBreaker getInstance(){
        if(cb == null){
            cb = new CaptchaBreaker();
        }
        
        return cb;
    }
    
    
    public String crush(WebDriver driver, WebElement element) {
        String soo = "";
        BufferedImage originalImage;
        try {
            originalImage = shootWebElement(driver, element);
            //parseImage(originalImage);
            soo = parseImage(originalImage);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CaptchaBreaker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CaptchaBreaker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return soo;

    }
   
    
    

public BufferedImage shootWebElement(WebDriver driver, WebElement element) throws IOException {

    File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    
    
    
    Point p = element.getLocation();

    int width = element.getSize().getWidth();
    int height = element.getSize().getHeight();

    BufferedImage img = ImageIO.read(screen);

    BufferedImage dest = img.getSubimage(p.getX()+1, p.getY()+1, width, height);
    ImageIO.write(dest, "png", screen);
    FileUtils.copyFile(screen, new File("D:\\tppcJavafiles\\screenshot.png"));
    return dest;
}


public String parseImage(BufferedImage img){
    ArrayList<String> LetterList = new ArrayList();
    boolean empty = true;
    boolean empty2 = true;

    int counter = 0;
    
    int startX = 0;
    int endX = 0;
    
    int startY = 0;
    int endY = 0;
    
    
    
    for(int x=0; x<img.getWidth();x++){
        for(int y=0;y<img.getHeight();y++){
            Color mycolor = new Color(img.getRGB(x, y));
            //System.out.println(" R" + mycolor.getRed() + " G" + mycolor.getGreen() + " B" + mycolor.getBlue());
            if((mycolor.getRed() == 255 && mycolor.getBlue() == 255 && mycolor.getGreen() == 255) || (mycolor.getRed() == 220 && mycolor.getGreen() == 210 && mycolor.getBlue() == 60)){
                //System.out.print("1");
                empty = false;
                if(counter == 0){
                    startX = x;
                }
                counter++;
            } else {
                //System.out.print("0");
            }
        }

        if (empty && counter > 0){
            counter = 0;
            endX = x;
            int counter2 = 0;
            for(int y=0; y<img.getHeight();y++){
                for (int xx=startX;xx<endX;xx++){
                    Color mycolor = new Color(img.getRGB(xx, y));
                    if( (mycolor.getRed() == 255 && mycolor.getGreen() == 255 && mycolor.getBlue() == 255) || (mycolor.getRed() == 220 && mycolor.getGreen() == 210 && mycolor.getBlue() == 60)){
                        //System.out.print("0");
                        empty2 = false;
                        if(counter2 == 0){
                            startY = y;
                        }
                        counter2++;
                    } else {
                        //System.out.print("1");

                    }
                    
                }
                if (empty2 && counter2 > 0){ 
                    endY = y;
                    counter2 = 0;
                    LetterList.add("");
                    for(int y2=startY; y2<endY; y2++){
                        for(int x2=startX; x2<endX; x2++){
                            Color mycolor2 = new Color(img.getRGB(x2, y2));
                            if( (mycolor2.getRed() == 255 && mycolor2.getGreen() == 255 && mycolor2.getBlue() == 255) || (mycolor2.getRed() == 220 && mycolor2.getGreen() == 210 && mycolor2.getBlue() == 60)){
                                LetterList.set(LetterList.size()-1, LetterList.get(LetterList.size()-1)+"1");
                            } else {
                                LetterList.set(LetterList.size()-1, LetterList.get(LetterList.size()-1)+"0");
                            }
                            
                        }
                    }
                    //System.out.println(LetterList.get(LetterList.size()-1));
                    
                    
                }
                empty2 = true;
                
            }
            
            
        }
        empty=true;
        //counter = 0;
        
        //System.out.println("");
    }
    
    String cap = compareLetters(LetterList);
    /*
        try {
            ImageIO.write(img, "png", new File("D:\\tppcJavafiles\\captchadata\\"+cap+".png"));
            //FileUtils.copyFile(screen, new File("D:\\tppcJavafiles\\screenshot.png"));
        } catch (IOException ex) {
            Logger.getLogger(CaptchaBreaker.class.getName()).log(Level.SEVERE, null, ex);
        }
    */
    
    return cap;
}
    
public String compareLetters(ArrayList<String> LetterList){
    boolean breaker = false;
    
    boolean found = false;
    
    String Captcha = "";
    for(int u=0; u<LetterList.size();u++){
        System.out.println(LetterList.get(u));
        found = false;
        for(int i=0; i<Letters.length;i++){

            if (map.get(Letters[i]) == null){
                System.out.println("Letters[i]=" + Letters[i]);
                System.out.println("WHat the FuCK");
                System.out.println(map.containsKey(Letters[i]));
            }
            int CHECK = map.get(Letters[i]).size();
            //int p = 0;
            //while(map.get(Letters[i]).get(p) != null){
            
            for(int p=0; p< CHECK;p++){

                if (LetterList.get(u).equals(map.get(Letters[i]).get(p))){
                    System.out.println(Letters[i]);
                    Captcha = Captcha + Letters[i];
                    found = true;
                    breaker=true;
                    break;
                }   
                p++;
            }
            
            if(breaker){
                breaker = false;
                break;
            }
        }
        if (found = false){
            System.out.println("Couldn't find letter for:");
            System.out.println(LetterList.get(u));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(CaptchaBreaker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /*try{
            PrintWriter writer = new PrintWriter("D:\\tppcJavafiles\\captchadata\\"+Captcha+".txt", "UTF-8");
            for(int i = 0; i < LetterList.size();i++){
                writer.println(LetterList.get(i));
            }

            //writer.println("The first line");
            writer.close();
        } catch (IOException e) {
           // do something
        }*/
    }
    
    return Captcha;
}


}
