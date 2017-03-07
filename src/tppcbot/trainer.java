/*
Feb 28, 2017
trainer.java, trainer, Joni Sikiö <joni.sikio@student.lut.fi> 
Kuvaus sisällöstä: 
Kehitysympäristö: NetBeans
Muutoshistoria:
Lisenssi: default

 */
package tppcbot;

import java.io.Serializable;

/**
 *
 * @author Joni Sikiö <joni.sikio@student.lut.fi>
 */
public class trainer implements Serializable{
    String accountNumber;
    int level;
    
    trainer(String accountNumber, int level){
        this.accountNumber = accountNumber;
        this.level = level;
    }
    public String getAccountNumber(){
        return accountNumber;
    }
    
    @Override
    public String toString(){
        String temp="Level: " + level;
        if(temp.length() <= 8){
            temp += "      \t#" + accountNumber;
        } else if (temp.length() <= 9){
            temp += "     \t#" + accountNumber;
        } else if (temp.length() <= 10){
            temp += "    \t#" + accountNumber;
        } else if (temp.length() <= 11){
            temp += "   \t#" + accountNumber;
        } else {
            temp += "  \t#" + accountNumber;
        }
        return temp;
    }

    public int getLevel() {
        return level;
    }
}
