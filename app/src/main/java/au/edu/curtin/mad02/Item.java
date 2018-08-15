package au.edu.curtin.mad02;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class Item implements Parcelable
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

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(desc);
        parcel.writeInt(value);
    }
}