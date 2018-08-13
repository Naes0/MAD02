package au.edu.curtin.mad02;

public class GameMap
{
    private Area grid[][];

    public GameMap()
    {
        grid = new Area[3][3];
        grid[0][0] = new Area(false);
        grid[0][1] = new Area(true);
        grid[0][2] = new Area(false);
        grid[1][0] = new Area(false);
        grid[1][1] = new Area(true);
        grid[1][2] = new Area(false);
        grid[2][0] = new Area(false);
        grid[2][1] = new Area(true);
        grid[2][2] = new Area(false);
    }

    public Area getArea(int x, int y)
    {
        return grid[x][y];
    }
}
