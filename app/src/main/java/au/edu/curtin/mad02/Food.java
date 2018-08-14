package au.edu.curtin.mad02;

public class Food extends Item
{
    private double health;

    public Food(String desc, int value, int health)
    {
        this.health = health;
        super.setDesc(desc);
        super.setValue(value);
    }
}
