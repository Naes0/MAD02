package au.edu.curtin.mad02;

public abstract class Item
{
    private String desc;
    private int value;

    public void setValue(int value)
    {
        this.value = value;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public abstract double getMassOrHealth();

    public String getDesc()
    {
        return desc;
    }

    public int getValue()
    {
        return value;
    }
}