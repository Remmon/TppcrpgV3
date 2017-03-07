/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tppcbot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 *
 * @author Jontsa
 */
public class Map {
    static Map m;
    FXMLDocumentController controller;
    WebDriver driver;
    HashMap<String, ArrayList<String>> tppcMaps;
    HashMap<String, String> tppcItems;
    ArrayList<String> mapList;
    private Map(){
        tppcMaps = new HashMap();      
        tppcItems = new HashMap();
        mapList = new ArrayList(Arrays.asList("Dark Islands","Treepath","Hidden Cove","Cold Cavern", "Meteor Hills","City Limits", "Ancient Temple", "Dark Cave","Memorial Caves","Empty Cliffs","Untouched Growth","Victory Path","Frozen Ruins","Safari Zone","Caverns of Alph"));
        tppcMaps.put("Ancient Temple", new ArrayList(Arrays.asList("Aerodactyl","Cranidos","Gligar","Hippopotas","Kabuto","Larvitar","Lunatone","Maractus","Omanyte","Relicanth","Sandshrew","Shieldon","Solrock","Tirtouga","Wooper")));
        tppcMaps.put("Caverns of Alph", new ArrayList(Arrays.asList("Unown")));
        tppcMaps.put("City Limits", new ArrayList(Arrays.asList("Buneary","Clefairy","Combee","Glameow","Kricketot","Ledyba","Meowth","Miltank","Minccino","Patrat","Purrloin","Skitty","Spinarak","Starly","Surskit","Togepi","Yanma")));
        tppcMaps.put("Cold Cavern", new ArrayList(Arrays.asList("Barboach","Basculin","Delibird","Finneon","Horsea","Jynx","Luvdisc","Magikarp","Mantine","Remoraid","Seel","Shellder","Slowpoke","Snorunt","Swinub")));
        tppcMaps.put("Dark Cave", new ArrayList(Arrays.asList("Aron","Cubone","Diglett","Geodude","Gligar","Golbat","Nosepass","Onix","Phanpy","Poochyena","Rhyhorn","Shuppet","Skorupi","Zubat")));
        tppcMaps.put("Dark Islands", new ArrayList(Arrays.asList("Carvanha","Chinchou","Clamperl","Corphish","Corsola","Ducklett","Goldeen","Lotad","Magikarp","Marill","Poliwag","Psyduck","Spheal","Staryu","Tentacool")));
        tppcMaps.put("Empty Cliffs", new ArrayList(Arrays.asList("Bellsprout","Budew","Cacnea","Doduo","Eevee","Exeggcute","Farfetch'd","Hoppip","Mankey","Meditite","Natu","Oddish","Pidgey","Pidove","Sunkern","Tropius")));
        tppcMaps.put("Frozen Ruins", new ArrayList(Arrays.asList("Alomomola","Barboach","Clamperl","Finneon","Horsea","Jynx","Luvdisc","Magikarp","Marill","Remoraid","Sneasel","Swinub","Tentacool","Wailmer")));
        tppcMaps.put("Hidden Cove", new ArrayList(Arrays.asList("Aipom","Bidoof","Doduo","Farfetch'd","Heracross","Nincada","Oddish","Paras","Sewaddle","Spearow","Spinarak","Stantler","Stunfisk","Taillow","Volbeat","Wurmple")));
        tppcMaps.put("Memorial Caves", new ArrayList(Arrays.asList("Castform","Drifloon","Duskull","Gastly","Girafarig","Kecleon","Lickitung","Misdreavus","Slakoth","Spinda","Spiritomb","Stantler","Teddiursa","Zigzagoon")));
        tppcMaps.put("Meteor Hills", new ArrayList(Arrays.asList("Clefairy","Grimer","Growlithe","Gulpin","Houndour","Magmar","Nidoran♀","Nidoran♂","Seviper","Slugma","Sneasel","Stunky","Trapinch","Vulpix","Whismur")));
        tppcMaps.put("Safari Zone", new ArrayList(Arrays.asList("Bouffalant","Chansey","Doduo","Durant","Exeggcute","Kangaskhan","Lotad","Oddish","Paras","Pinsir","Rhyhorn","Scyther","Seedot","Sunkern","Tauros","Venonat")));
        tppcMaps.put("Treepath", new ArrayList(Arrays.asList("Chatot","Hoothoot","Kecleon","Murkrow","Pidgey","Pineco","Rattata","Sentret","Snubbull","Spinda","Sunkern","Vullaby","Weedle","Wurmple","Yanma")));
        tppcMaps.put("Untouched Growth", new ArrayList(Arrays.asList("Abra","Clefairy","Ditto","Drowzee","Eevee","Grimer","Growlithe","Machop","Meowth","Pikachu","Ponyta","Rattata","Voltorb","Vulpix")));
        tppcMaps.put("Victory Path", new ArrayList(Arrays.asList("Chimecho","Chinchou","Magnemite","Mareep","Minun","Mr","Mime","Pikachu","Plusle","Porygon","Ralts","Shinx","Smeargle","Spoink","Wobbuffet")));
        
        tppcItems.put("Blackbelt", "Meditite");
        tppcItems.put("Charcoal", "Vulpix");
        tppcItems.put("Darkglasses", "Poochyena");
        tppcItems.put("Dragon Scale", "Horsea");
        tppcItems.put("Exp Share", "Wobbuffet");
        tppcItems.put("Fairy Wings", "Clefairy");
        tppcItems.put("Hard Stone", "Aron");
        tppcItems.put("Kings Rock", "Teddiursa");
        tppcItems.put("Leftovers", "Spheal");
        tppcItems.put("Magnet", "Pikachu");
        tppcItems.put("Metal Coat", "Magnemite");
        tppcItems.put("Miracle Seed", "Oddish");
        tppcItems.put("NeverMeltice", "Jynx");
        tppcItems.put("Pink Bow", "Phanpy");
        tppcItems.put("Polkadot Bow", "Whismur");
        tppcItems.put("Quick Claw", "Meowth");
        tppcItems.put("Sharp Beak", "Doduo");
        tppcItems.put("Silver Powder", "Heracross");
        tppcItems.put("Soft Sand", "Trapinch");
        tppcItems.put("Spell Tag", "Shuppet");
        tppcItems.put("Twisted Spoon", "Abra");
        tppcItems.put("Upgrade", "Porygon");
        tppcItems.put("X Accuracy", "Kecleon");
        
    }
    
    public void setDriverAndController(FXMLDocumentController dc, WebDriver d){
        controller = dc;
        driver = d;
    }
    
    public static Map getInstance(){
        if(m == null){
            m = new Map();
        }
        
        return m;
    }
    
    //Mode = "Map","Poke","Item"
    //vari = list of map/poke/item names
    public void startMap(String mode, String vari, int amountToCatch){
        String[] variList = vari.split(",");
        int calc = 0;
        for(int i=0;i<variList.length;i++){
            if(mode.equals("map")){
                driver.get("http://www.tppcrpg.net/map.php?Map="+variList[i]);
                while(true){
                    if(driver.findElements(By.name("Find")).size() > 0){
                        driver.findElement(By.name("Find")).click();
                        controller.defSleep();
                        if(driver.findElements(By.xpath("/html/body/div[@id='body']/div[@id='inner']/form[1]/blockquote[@class='success']/strong[1]")).size() > 0 ){
                            System.out.println(driver.findElement(By.xpath("/html/body/div[@id='body']/div[@id='inner']/form[1]/blockquote[@class='success']/strong[1]")).getText());
                            if(driver.findElements(By.className("submit")).size() > 0){
                                driver.findElement(By.className("submit")).click();
                                calc++;
                                if(calc >= amountToCatch){
                                    break;
                                }
                            }
                        }

                    } else if (driver.findElements(By.linkText("Lets Battle!")).size() > 0 ){
                        driver.get("http://www.tppcrpg.net/map.php?Map="+variList[i]);
                    } else if (driver.findElements(By.partialLinkText("Return To")).size() > 0){
                        driver.findElement(By.partialLinkText("Return To")).click();
                    }
                }
            } else if(mode.equals("poke")){
                String mapid;
                
                
                boolean doubleBreak = false;
                for(int u=1;u<tppcMaps.size()+1;u++){
                    for(int y=0;y<tppcMaps.get(mapList.get(u-1)).size();y++){
                        if(tppcMaps.get(mapList.get(u-1)).get(y).equals(variList[i])){
                            mapid = ""+u;
                            doubleBreak = true;
                            break;
                        }
                    }
                    if(doubleBreak){
                        break;
                    }
                }
                
                
                driver.get("http://www.tppcrpg.net/map.php?Map="+variList[i]);
                while(true){
                    if(driver.findElements(By.name("Find")).size() > 0){
                        driver.findElement(By.name("Find")).click();
                        controller.defSleep();
                        if(driver.findElements(By.xpath("/html/body/div[@id='body']/div[@id='inner']/form[1]/blockquote[@class='success']/strong[1]")).size() > 0 ){
                            System.out.println(driver.findElement(By.xpath("/html/body/div[@id='body']/div[@id='inner']/form[1]/blockquote[@class='success']/strong[1]")).getText());
                            if (driver.findElement(By.xpath("/html/body/div[@id='body']/div[@id='inner']/form[1]/blockquote[@class='success']/strong[1]")).getText().equals(variList[i])){
                                controller.defSleep();
                                driver.findElement(By.className("submit")).click();
                                calc++;
                                if(calc >= amountToCatch){
                                    break;
                                }
                            }
                        }

                    } else if (driver.findElements(By.linkText("Lets Battle!")).size() > 0 ){
                        driver.get("http://www.tppcrpg.net/map.php?Map="+variList[i]);
                    } else if (driver.findElements(By.partialLinkText("Return To")).size() > 0){
                        driver.findElement(By.partialLinkText("Return To")).click();
                    }
                }
            } else if(mode.equals("item")){

            }
        }


        
        
        
    }
    
    
}
