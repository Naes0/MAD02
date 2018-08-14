package au.edu.curtin.mad02;

import java.util.ArrayList;
import java.util.List;

public class Player
{
    private int row;
    private int col;
    private int cash;
    private double health;
    private double equipMass;
    private List<Equipment> equipmentlist;
    private static final double MAX_HEALTH = 100.0;

    public Player()
    {
        this.row = 0;
        this.col = 0;
        this.cash = 0;
        this.health = MAX_HEALTH;
        this.equipMass = 0.0;
        this.equipmentlist = new ArrayList<Equipment>();
    }

    public int getRow()
    {
        return row;
    }

    public void addRow(int row)
    {
        this.row += row;
    }

    public int getCol()
    {
        return col;
    }

    public void addCol(int col)
    {
        this.col += col;
    }

    public int getCash()
    {
        return cash;
    }

    public void addCash(int cash)
    {
        this.cash += cash;
    }

    public double getHealth()
    {
        return health;
    }

    public void setHealth(double health)
    {
        this.health = health;
    }

    public double getEquipMass()
    {
        return equipMass;
    }

    public void addEquipMass(double equipMass)
    {
        this.equipMass += equipMass;
    }

    public String getPos()
    {
        return ("[" + row + "]" + "[" + col + "]");
    }

    public List<Equipment> getEquipmentlist()
    {
        return equipmentlist;
    }

    public void setEquipmentlist(List<Equipment> equipmentlist)
    {
        this.equipmentlist = equipmentlist;
    }

    public void addEquipment(Equipment equip)
    {
        equipmentlist.add(equip);
    }

    public void decreaseHealth()
    {
        setHealth();
    }
}
