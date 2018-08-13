package au.edu.curtin.mad02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NavigateActivity extends AppCompatActivity
{
    private Button northButton;
    private Button southButton;
    private Button westButton;
    private Button eastButton;
    private TextView currPosView;
    private TextView descView;
    private Player player;
    private GameMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);

        northButton = (Button) findViewById(R.id.northButton);
        southButton = (Button) findViewById(R.id.southButton);
        westButton = (Button) findViewById(R.id.westButton);
        eastButton = (Button) findViewById(R.id.eastButton);
        currPosView = (TextView) findViewById(R.id.positionTextView);
        descView = (TextView) findViewById(R.id.descTextView);

        player = new Player();
        map = new GameMap();
        currPosView.setText(player.getPos());

        northButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(player.getRow() < 2)
                {
                    player.addRow(1);
                    double newHealth = Math.max(0.0, player.getHealth() - 5.0 - ((double)player.getEquipMass() / 2.0));
                    player.addHealth(newHealth);
                    currPosView.setText(player.getPos());
                    Area newArea = map.getArea(player.getRow(), player.getCol());
                    setDesc(newArea);
                }
                else
                {
                    currPosView.setText(player.getPos());
                }
            }
        });

        southButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(player.getRow() > 0)
                {
                    player.addRow(-1);
                    double newHealth = Math.max(0.0, player.getHealth() - 5.0 - ((double)player.getEquipMass() / 2.0));
                    player.addHealth(newHealth);
                    currPosView.setText(player.getPos());
                    Area newArea = map.getArea(player.getRow(), player.getCol());
                    setDesc(newArea);
                }
                else
                {
                    currPosView.setText(player.getPos());
                }
            }
        });

        westButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(player.getCol() > 0)
                {
                    player.addCol(-1);
                    double newHealth = Math.max(0.0, player.getHealth() - 5.0 - ((double)player.getEquipMass() / 2.0));
                    player.addHealth(newHealth);
                    currPosView.setText(player.getPos());
                    Area newArea = map.getArea(player.getRow(), player.getCol());
                    setDesc(newArea);
                }
                else
                {
                    currPosView.setText(player.getPos());
                }
            }
        });

        eastButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(player.getCol() < 2)
                {
                    player.addCol(1);
                    double newHealth = Math.max(0.0, player.getHealth() - 5.0 - ((double)player.getEquipMass() / 2.0));
                    player.addHealth(newHealth);
                    currPosView.setText(player.getPos());
                    Area newArea = map.getArea(player.getRow(), player.getCol());
                    setDesc(newArea);
                }
                else
                {
                    currPosView.setText(player.getPos());
                }
            }
        });
    }

    public void setDesc(Area area)
    {
        if(area.isTown())
        {
            descView.setText("Town");
        }
        else
        {
            descView.setText("Wilderness");
        }
    }
}
