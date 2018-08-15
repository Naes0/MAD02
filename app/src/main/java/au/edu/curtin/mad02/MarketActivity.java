package au.edu.curtin.mad02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MarketActivity extends AbstractActivity
{
    private TextView itemTypeBuyView;
    private TextView descBuyView;
    private TextView valueBuyView;
    private TextView masshealthBuyView;
    private Button prevBuyButton;
    private Button nextBuyButton;
    private Button BuyButton;
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        buttonInitialise();
        setup();
        initialise();

    }

    public void buttonInitialise()
    {
        itemTypeBuyView = (TextView) findViewById(R.id.itemTitle);
        descBuyView = (TextView) findViewById(R.id.descBuyView);
        valueBuyView = (TextView) findViewById(R.id.valueBuyView);
        masshealthBuyView = (TextView) findViewById(R.id.massOrHealthBuyView);
        prevBuyButton = (Button) findViewById(R.id.prevBuyButton);
        nextBuyButton = (Button) findViewById(R.id.nextBuyButton);
        nextBuyButton = (Button) findViewById(R.id.buyButton);
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
    }
    public void initialise()
    {
        healthView.setText("Health: " + Double.toString(player.getHealth()) + "/100.0");
        cashView.setText("Cash: $" + player.getCash());
        massView.setText("Mass: :" + Double.toString(player.getEquipMass()) + " kg");
        itemTypeBuyView.setText(itemType(itemList.get(0)));
        descBuyView.setText(itemList.get(0).getDesc());
        valueBuyView.setText("Value: " + itemList.get(0).getValue());
        masshealthBuyView.setText(healthOrMass(itemList.get(0)) + Double.toString(itemList.get(0).getMassOrHealth()));
        descSellView.setText(player.getEquipmentlist().get(0).getDesc());
        valueSellView.setText("Value: " + player.getEquipmentlist().get(0).getValue());
        massHealthSellView.setText("Mass: " + player.getEquipmentlist().get(0).getMassOrHealth());
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
