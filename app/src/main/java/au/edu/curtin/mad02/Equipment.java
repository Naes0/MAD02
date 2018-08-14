package au.edu.curtin.mad02;

public class Equipment extends Item
{
    private double mass;

    public Equipment(String desc, int value, int mass)
    {
        this.mass = mass;
        super.setDesc(desc);
        super.setValue(value);
    }
}
