/*
Mar 12, 2017
Pokemon.java, Pokemon, Joni Sikiö <joni.sikio@student.lut.fi> 
Kuvaus sisällöstä: 
Kehitysympäristö: NetBeans
Muutoshistoria:
Lisenssi: default

 */
package tppcbot;

/**
 *
 * @author Joni Sikiö <joni.sikio@student.lut.fi>
 */
public class Pokemon {
    String name;
    int level;
    String item;
    
    Pokemon(){
        name = "";
        level = 0;
        item ="";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
    
    
}
