package VisualApp;

import java.util.ArrayList;
import Clocks.*;

public class AllClocks {
    ArrayList<IClock> allClocks = new ArrayList<>();
    ArrayList<IObserver> o = new ArrayList<>();

    public void addO(IObserver obj)
    {
        o.add(obj);
    }

    public void add(IClock clock){
        allClocks.add(clock);
        event();
    }

    public void remove(IClock clock)
    {
        allClocks.remove(clock);
        event();
    }

    void event()
    {
        for (IObserver obj : o) {
            obj.event(this);
        }
    }

    ArrayList<IClock> getAllPair(){
        return allClocks;
    }
}
