package au.edu.curtin.mad02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MarketActivity extends AppCompatActivity
{
    private TextView itemTypeBuyView;
    private TextView descBuyView;
    private TextView valueBuyView;
    private TextView masshealthBuyView;
    private Button prevBuyButton;
    private Button nextBuyButton;
    private Button buyButton;
    private TextView descSellView;
    private TextView valueSellView;
    private TextView massHealthSellView;
    private Button prevSellButton;
    private Button nextSellButton;
    private Button sellButton;
    private Button leaveButton;
    private TextView healthView;
    private TextView cashView;
    private TextView massView;
    private Player player;
    private Area area;
    private List<Item> itemList;
    private List<Equipment> equipmentList;
    private Item buyItem;
    private Equipment sellItem;
    private int currBuyIndex;
    private int currSellIndex;
    private boolean marketIsEmpty;
    private boolean playerIsEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        buttonInitialise();
        setup();
        initialise();

        nextBuyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(!marketIsEmpty)
                {
                    if (currBuyIndex == itemList.size() - 1)
                    {
                        currBuyIndex = -1;
                    }
                    currBuyIndex++;
                    buyItem = itemList.get(currBuyIndex);
                    updateBuyItem();
                }
            }
        });

        prevBuyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(!marketIsEmpty)
                {
                    if(currBuyIndex == 0)
                    {
                        currBuyIndex = itemList.size();
                    }
                    currBuyIndex--;
                    buyItem = itemList.get(currBuyIndex);
                    updateBuyItem();
                }
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if((!marketIsEmpty) && (player.getCash() >= buyItem.getValue()))
                {
                    if(buyItem instanceof Equipment)
                    {
                        player.addEquipment((Equipment) buyItem);
                    }
                    else
                    {
                        player.addHealth(buyItem.getMassOrHealth());
                    }
                    player.addCash(-buyItem.getValue());
                    if(itemList.indexOf(buyItem) == 0)
                    {
                        buyItem = itemList.get(currBuyIndex+1);

                    }
                    else
                    {
                        buyItem = itemList.get(currBuyIndex-1);
                    }
                    updateBuyItem();
                    buyItem = itemList.get(currBuyIndex);
                    itemList.remove(buyItem);
                    if(itemList.isEmpty())
                    {
                        marketIsEmpty = true;
                    }
                }

                updateStatusBar();
            }
        });

        nextSellButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(!playerIsEmpty)
                {
                    if (currSellIndex == equipmentList.size() - 1)
                    {
                        currSellIndex = -1;
                    }
                    currSellIndex++;
                    sellItem = equipmentList.get(currSellIndex);
                    updateSellItem();
                }
            }
        });

        prevSellButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(!playerIsEmpty)
                {
                    if(currSellIndex == 0)
                    {
                        currSellIndex = equipmentList.size();
                    }
                    currSellIndex--;
                    sellItem = equipmentList.get(currSellIndex);
                    updateSellItem();
                }
            }
        });

        sellButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(!playerIsEmpty)
                {
                    player.addCash(sellItem.getValue());
                    if(equipmentList.indexOf(sellItem) == 0)
                    {
                        currSellIndex++;
                    }
                    else
                    {
                        currSellIndex--;
                    }
                    sellItem = equipmentList.get(currSellIndex);
                    player.removeEquipment(sellItem);
                    equipmentList.remove(sellItem);
                    if(equipmentList.isEmpty())
                    {
                        playerIsEmpty = true;
                    }
                }
                updateSellItem();
                updateStatusBar();
            }
        });


    }

    public void updateBuyItem()
    {
        if (!marketIsEmpty)
        {
            itemTypeBuyView.setText(itemType(buyItem));
            descBuyView.setText(buyItem.getDesc());
            valueBuyView.setText("Value: " + buyItem.getValue());
            masshealthBuyView.setText(healthOrMass(buyItem) + Double.toString(buyItem.getMassOrHealth()));
        }
        else
        {
            itemTypeBuyView.setText("N/A");
            descBuyView.setText("EMPTY");
            valueBuyView.setText("N/A");
            masshealthBuyView.setText("N/A");
        }
    }

    public void updateSellItem()
    {
        if (!playerIsEmpty)
        {
            descSellView.setText(sellItem.getDesc());
            valueSellView.setText("Value: " + sellItem.getValue());
            massHealthSellView.setText(healthOrMass(sellItem) + Double.toString(sellItem.getMassOrHealth()));
        }
        else
        {
            descSellView.setText("EMPTY");
            valueSellView.setText("N/A");
            massHealthSellView.setText("N/A");
        }
    }

    public void buttonInitialise()
    {
        itemTypeBuyView = (TextView) findViewById(R.id.itemTitle);
        descBuyView = (TextView) findViewById(R.id.descBuyView);
        valueBuyView = (TextView) findViewById(R.id.valueBuyView);
        masshealthBuyView = (TextView) findViewById(R.id.massOrHealthBuyView);
        prevBuyButton = (Button) findViewById(R.id.prevBuyButton);
        nextBuyButton = (Button) findViewById(R.id.nextBuyButton);
        buyButton = (Button) findViewById(R.id.buyButton);
        descSellView = (TextView) findViewById(R.id.descSellView);
        valueSellView = (TextView) findViewById(R.id.valueSellView);
        massHealthSellView = (TextView) findViewById(R.id.massOrHealthSellView);
        prevSellButton = (Button) findViewById(R.id.prevSellButton);
        nextSellButton = (Button) findViewById(R.id.nextSellButton);
        sellButton = (Button) findViewById(R.id.sellButton);
        leaveButton = (Button) findViewById(R.id.leaveButton);
        healthView = (TextView) findViewById(R.id.healthView);
        cashView = (TextView) findViewById(R.id.cashView);
        massView = (TextView) findViewById(R.id.massView);
    }

    public void setup()
    {
        Intent intent = getIntent();
        player = intent.getParcelableExtra("player");
        area = intent.getParcelableExtra("currArea");
        itemList = area.getItemList();
        equipmentList = player.getEquipmentlist();
        buyItem = itemList.get(0);
        sellItem = equipmentList.get(0);
        currBuyIndex = 0;
        currSellIndex = 0;
        marketIsEmpty = itemList.isEmpty();
        playerIsEmpty = equipmentList.isEmpty();
    }
    public void initialise()
    {
        updateStatusBar();
        itemTypeBuyView.setText(itemType(buyItem));
        descBuyView.setText(buyItem.getDesc());
        valueBuyView.setText("Value: " + buyItem.getValue());
        masshealthBuyView.setText(healthOrMass(buyItem) + Double.toString(buyItem.getMassOrHealth()));
        descSellView.setText(sellItem.getDesc());
        valueSellView.setText("Value: " + sellItem.getValue());
        massHealthSellView.setText("Mass: " + sellItem.getMassOrHealth());
    }

    public void updateStatusBar()
    {
        healthView.setText("Health: " + Double.toString(player.getHealth()) + "/100.0");
        cashView.setText("Cash: $" + player.getCash());
        massView.setText("Mass: " + Double.toString(player.getEquipMass()) + " kg");
    }

    public String itemType(Item item)
    {
        if (item instanceof Equipment)
        {
            return "Equipment";
        }
        else
        {
            return "Food";
        }
    }

    public String healthOrMass(Item item)
    {
        if (item instanceof Equipment)
        {
            return "Mass: ";
        }
        else
        {
            return "Health: ";
        }
    }
}
