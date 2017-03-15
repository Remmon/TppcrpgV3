/*
Mar 12, 2017
Party.java, Party, Joni Sikiö <joni.sikio@student.lut.fi> 
Kuvaus sisällöstä: 
Kehitysympäristö: NetBeans
Muutoshistoria:
Lisenssi: default

 */
package tppcbot;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Joni Sikiö <joni.sikio@student.lut.fi>
 */
public class Party {
    static Party p = null;
    ArrayList<Pokemon> partyList;
    FXMLDocumentController controller;
    WebDriver driver;
    
    private Party(){
        partyList = new ArrayList<Pokemon>();
        for(int i=0;i<6;i++){
            partyList.add(new Pokemon());
        }
    }
    
    public void setController(FXMLDocumentController controller, WebDriver driver){
        this.controller = controller;
        this.driver = driver;
    }
    
    public static Party getInstance(){
        if(p == null){
            p = new Party();
        }
        return p;
    }
    
    public ArrayList getPartyList(){
        return partyList;
    }

    
    public String getSubstring (String line, String start, String end){
        int startIndex = line.indexOf(start)+start.length();
        int endIndex = line.indexOf(end, startIndex);
        return line.substring(startIndex,endIndex);
    }
    
    public void updatePartyList(){
        for(int i=0;i<6;i++){
            //int rlevel;
            String tempLevel;
            String tempNumber = "";

            String tempItem = "";
            if(driver.findElements(By.xpath("/html/body/div[@id='right']/ul/li[@id='rs']/div[1]/img["+(i+1)+"]")).size() > 0 ){
                if(i<3){
                    tempLevel = driver.findElement(By.xpath("/html/body/div[@id='right']/ul/li[@id='rs']/div[1]/img["+(i+1)+"]")).getAttribute("onclick");
                    tempNumber = getSubstring(tempLevel, "level: '", "'");
                    tempItem = getSubstring(tempLevel, "item: '", "'"); //tempLevel.substring(tempLevel.indexOf("item: '")+7, tempLevel.indexOf("'", tempLevel.indexOf("item: '")+7));
                    /*pokeLevelLabel.get(i).setText("Lv: "+tempNumber);
                    pokeItemLabel.get(i).setText(tempItem);
                    */
                } else if (i <6){
                    tempLevel = driver.findElement(By.xpath("/html/body/div[@id='right']/ul/li[@id='rs']/div[2]/img["+(i-2)+"]")).getAttribute("onclick");
                    tempNumber = getSubstring(tempLevel, "level: '", "'");//tempLevel.substring(tempLevel.indexOf("level: '")+8, tempLevel.indexOf("'", tempLevel.indexOf("level: '")+8));
                    tempItem = getSubstring(tempLevel, "item: '", "'");
                    /*
                    pokeLevelLabel.get(i).setText("Lv: "+tempNumber);
                    pokeItemLabel.get(i).setText(tempItem);
                    */
                }
                partyList.get(i).setItem(tempItem);
                partyList.get(i).setLevel(Integer.valueOf(tempNumber));
            }
        }
    }
    
    
}
