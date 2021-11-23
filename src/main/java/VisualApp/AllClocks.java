package VisualApp;

import java.util.ArrayList;
import Clocks.*;

public class AllClocks {
    private Shop shopOfClock = new Shop();
    //ArrayList<IClock> allClocks = new ArrayList<>();
    ArrayList<IObserver> o = new ArrayList<>();

    public void addO(IObserver obj)
    {
        o.add(obj);
    }

    public void add(IClock clock){
        shopOfClock.AddClock(clock);
        event();
    }

    public void remove(IClock clock)
    {
        shopOfClock.RemoveClock(clock);
        event();
    }

    public void event()
    {
        for (IObserver obj : o) {
            obj.event(this);
        }
    }

    public ArrayList<IClock> getAllClocks(){
        return shopOfClock.getClockList();
    }

    public Shop getShop(){
        return shopOfClock;
    }

    public void setTime(int h, int m, int s) throws MissingError, TimeError {
        shopOfClock.setTime(h, m, s);
        event();
    }
}
