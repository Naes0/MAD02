package au.edu.curtin.mad02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public abstract class AbstractActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abstract);




    }
}
