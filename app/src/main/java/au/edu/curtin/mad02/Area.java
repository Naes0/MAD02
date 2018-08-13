package au.edu.curtin.mad02;

import java.util.ArrayList;
import java.util.List;

public class Area
{
    private boolean town;
    private List<Item> itemlist;

    public Area(boolean town)
    {
        this.town = town;
        itemlist = new ArrayList<Item>();
    }

    public boolean isTown()
    {
        return town;
    }

    public void setTown(boolean town)
    {
        this.town = town;
    }
}
