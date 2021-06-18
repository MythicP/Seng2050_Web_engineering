/*SaveData.java
   *Author: Michael Price
   *Student No: 3233787
   *Date last Modified: 07/05/2019
   *Description:
   maintains a list of all GameSave's 
   is a java bean
*/
package myPackage;

import java.util.List;
import java.util.ListIterator;
import java.util.LinkedList;
import java.io.Serializable;

public class SaveData implements Serializable
{
    private List<GameSave> data = new LinkedList<GameSave>();

    public SaveData()
    {

    }

    public void setData(List<GameSave> new_Data)
    {
    	data = new_Data;
    }

    public List<GameSave> getData()
    {
    	return data;
    }

    //pre-condition: save != null
    //post-condition: if user id of save dosen't have a save it adds the save to the list
    //otherwise it removes the old save then adds the new save
    public void setSave(GameSave save)
    {
        GameSave temp = getSave(save.getUserName());
        if(temp == null)
        {
            data.add(save);
        }
        else
        {
            ListIterator<GameSave> iter = data.listIterator();
            while(iter.hasNext())
            {
                temp = iter.next();
                if(save.getUserName().equals(temp.getUserName()))
                {
                    iter.remove();
                    iter.add(save);
                    break;
                }
            }
        }
    }

    //pre-condition: id != null
    //post-condition: iterates throught the save list and returns the save matching the user id
    //if any of the gameSaves are null or the users name tied to a save is null that save is removed to prevent errors
    public GameSave getSave(String id)
    {
        GameSave temp = null;
        ListIterator<GameSave> iter = data.listIterator();
        while(iter.hasNext())
        {
            temp = iter.next();
            if(temp == null)
            {
                iter.remove();
            }
            else if(temp.getUserName() == null)
            {
                iter.remove();
            }
            else if(temp.getUserName().equals(id))
            {
                break;
            }
            temp = null;
        }
        return temp;
    }

    public int getSize()
    {
        return data.size();
    }

    //pre-condition: none
    //post-condition: used to wipe saves(not used)
    public void setClear()
    {
        GameSave temp = null;
        ListIterator<GameSave> iter = data.listIterator();
        while(iter.hasNext())
        {
            temp = iter.next();
            iter.remove();
        }
    }

    //pre-condition: 
    //post-condition: follows the same method of get save but insted removes said users game
    public void setRemove(String id)
    {
        GameSave temp = null;
        ListIterator<GameSave> iter = data.listIterator();
        while(iter.hasNext())
        {
            temp = iter.next();
            if(temp == null)
            {
                iter.remove();
            }
            else if(temp.getUserName() == null)
            {
                iter.remove();
            }
            else if(temp.getUserName().equals(id))
            {
                iter.remove();
                break;
            }
            temp = null;
        }
    }
}