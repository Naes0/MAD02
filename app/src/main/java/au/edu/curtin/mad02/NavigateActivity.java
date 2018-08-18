package au.edu.curtin.mad02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NavigateActivity extends AppCompatActivity
{
    private ConstraintLayout layout;
    private Button northButton;
    private Button southButton;
    private Button westButton;
    private Button eastButton;
    private Button restartButton;
    private Button optionButton;
    private TextView currPosView;
    private TextView descView;
    private TextView healthView;
    private TextView cashView;
    private TextView massView;
    private Player player;
    private GameMap map;
    private Area currArea;
    private static final int REQUEST_CODE_MARKET = 0;
    private static final int REQUEST_CODE_WILDERNESS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);

        player = new Player();
        map = new GameMap();

        initialiseButtons();
        playerSetup();
        updateStatusBar();

        northButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(player.getRow() < 2)
                {
                    player.addRow(1);
                    setNewConditions();
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
                    setNewConditions();
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
                    setNewConditions();
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
                    setNewConditions();
                }
                else
                {
                    currPosView.setText(player.getPos());
                }
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(NavigateActivity.this, MainActivity.class));
            }
        });

        optionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent;
                if (descView.getText().equals("Town"))
                {
                    intent = new Intent(NavigateActivity.this, MarketActivity.class);
                }
                else
                {
                    intent = new Intent(NavigateActivity.this, WildernessActivity.class);
                }
                intent.putExtra("player", player);
                intent.putExtra("currArea", currArea);
                if (descView.getText().equals("Town"))
                {
                    startActivityForResult(intent, REQUEST_CODE_MARKET);
                }
                else
                {
                    startActivityForResult(intent, REQUEST_CODE_WILDERNESS);
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent returnData)
    {
        if ((requestCode == REQUEST_CODE_MARKET || requestCode == REQUEST_CODE_WILDERNESS) && resultCode == RESULT_OK)
        {
            player = returnData.getParcelableExtra("player");
            currArea = returnData.getParcelableExtra("area");
            map.setArea(player.getRow(), player.getCol(), currArea);
        }
        updateStatusBar();
    }

    public void setNewConditions()
    {
        player.decreaseHealth();
        currPosView.setText(player.getPos());
        currArea = map.getArea(player.getRow(), player.getCol());
        setDesc(currArea);

        updateStatusBar();
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

    public void initialiseButtons()
    {
        northButton = (Button) findViewById(R.id.northButton);
        southButton = (Button) findViewById(R.id.southButton);
        westButton = (Button) findViewById(R.id.westButton);
        eastButton = (Button) findViewById(R.id.eastButton);
        restartButton = (Button) findViewById(R.id.resetButton);
        optionButton = (Button) findViewById(R.id.optionButton);
        currPosView = (TextView) findViewById(R.id.positionTextView);
        descView = (TextView) findViewById(R.id.descTextView);
        healthView = (TextView) findViewById(R.id.healthView);
        cashView = (TextView) findViewById(R.id.cashView);
        massView = (TextView) findViewById(R.id.massView);
        layout = (ConstraintLayout) findViewById(R.id.)
    }

    public void updateStatusBar()
    {
        healthView.setText("Health: " + Double.toString(player.getHealth()) + "/100.0");
        cashView.setText("Cash: $" + player.getCash());
        massView.setText("Mass: " + Double.toString(player.getEquipMass()) + " kg");
    }

    public void playerSetup()
    {
        player.addEquipment(new Equipment("Sword", 10, 5));
        player.addEquipment(new Equipment("Shield", 5, 3));
        player.addCash(50);
        currPosView.setText(player.getPos());
        currArea = map.getArea(player.getRow(), player.getCol());
        setDesc(currArea);
    }
}
