/*GameSave.java
   *Author: Michael Price
   *Student No: 3233787
   *Date last Modified: 07/05/2019
   *Description:
   This class us used as a java bean to track the games state
*/
package myPackage;

import java.util.List;
import java.util.ListIterator;
import java.util.LinkedList;
import java.io.Serializable;
import java.util.Collections;

public class GameSave implements Serializable
{
    private String userName = null;
    private boolean[] openedCases = null;
    private Double[] caseValues = null;
    private int picked = -1;
    private int round = 0;
    private int count = 0;
    private Double offer = 0.0;
    private Double winnings = 0.0;
    private boolean dealOrNo = false;

    public GameSave()
    {

    }

    public void setUserName(String new_user)
    {
        userName = new_user;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setOpenedCases(boolean[] new_openedCases)
    {
        openedCases = new_openedCases;
    }

    public boolean[] getOpenedCases()
    {
        return openedCases;
    }

    //pre-condition: new_caseValues array is of length 12
    //post-condition: randimises pased array and sets caseValues
    public void setCaseValues(Double[] new_caseValues)
    {
        caseValues = new Double[12];
        List<Double> caseValueslist = new LinkedList<Double>();
        for(int i = 0; i < 12; i++)
        {
            caseValueslist.add(new_caseValues[i]);
        }
        Collections.shuffle(caseValueslist);
        caseValues = caseValueslist.toArray(caseValues);
    }

    public Double[] getCaseValues()
    {
        return caseValues;
    }

    public void setPicked(int new_Picked)
    {
        picked = new_Picked;
    }

    public int getPicked()
    {
        return picked;
    }

    public void setRound(int new_round)
    {
        round = new_round;
    }

    public int getRound()
    {
        return round;
    }

    public void setCount(int new_count)
    {
        count = new_count;
    }

    public int getCount()
    {
        return count;
    }

    public void setOffer(Double new_offer)
    {
        offer = new_offer;
    }

    //pre-condition: caseValues must be set i.e. not null
    //post-condition: calculates the banks offer and returns it (to s dp)
    public Double getOffer()
    {
        try
        {
            Double curCount = 0.0;
            Double totalRemaining = 0.0;
            for(int i = 0; i < 12; i++)
            {
                if(openedCases[i] == false)
                {
                    totalRemaining += caseValues[i];
                    curCount += 1.0;
                }
            }
            offer = totalRemaining/curCount;
            offer = (Math.round(offer * 100.00))/ 100.00;
            return offer;
        }
        catch(Exception e)
        {
            return -1.0;
        }
    }

    public void setWinnings(Double new_winnings)
    {
        winnings = new_winnings;
    }

    public Double getWinnings()
    {
        return winnings;
    }

    public void setDealOrNo(boolean new_deal)
    {
        dealOrNo = new_deal;
    }

    public boolean getDealOrNo()
    {
        return dealOrNo;
    }
        
}