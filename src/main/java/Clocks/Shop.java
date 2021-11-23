package Clocks;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Shop {

    private final ArrayList<IClock> listClocks = new ArrayList<>();

    public Shop(){
        clockDelivery(10);
    }

    private void clockDelivery(int n){ //поставка часов: при открытии магазина
        ClockFactory clockFactory = new ClockFactory();

        String[] brands = {"Clock", "Vlock", "Block", "Rlock", "Dlock"};
        TypeOfClock type = null;
        Random rand = new Random();

        for (int i=0; i<n; i++){
            int temp = rand.nextInt(2)+1;
            if (temp == 1)
                type = TypeOfClock.HM;
            if (temp == 2)
                type = TypeOfClock.HMS;

            listClocks.add(clockFactory.createClock(type, brands[rand.nextInt(5)], rand.nextInt(200)));
        }
    }

    public ArrayList<IClock> getClockList(){
        return listClocks;
    }

    public IClock mostExpClock(){ //Получать описание самых дорогих часов
        return Collections.max(listClocks, new Comparator<IClock>()
        {
            @Override
            public int compare(IClock clock1, IClock clock2) {
                return clock1.GetPrice() - clock2.GetPrice();
            }
        });
    }

    public void setTime(int _h, int _m, int _s) throws TimeError, MissingError //Устанавливать заданное время на всех часах.
    {
        int n = 0;

        for (IClock listClock : listClocks) {
            try {
                n++;
                listClock.SetTime(EArrow.H, _h);
                listClock.SetTime(EArrow.M, _m);
                listClock.SetTime(EArrow.S, _s);
            }
            catch (TimeError ex)
            {
                Logger.getLogger(ShopTest.class.getName()).log(Level.SEVERE, null, ex);
                throw new TimeError("Error");
            }

            catch (MissingError ignored){ }

        }
    }

    public String mostFreqBrand(){ //Получать название наиболее часто встречающейся марки часов в магазине.
        String res = null;
        int max = 0;

        TreeMap<String,Integer> map = new TreeMap<String,Integer>();
        for (IClock listClock : listClocks)
            if (!map.containsKey(listClock.GetBrand()))
            {
                map.put(listClock.GetBrand(), 1);
            }
            else
            {
                map.replace(listClock.GetBrand(), map.get(listClock.GetBrand()) + 1);
            }

        for(Map.Entry<String, Integer> m:map.entrySet())
        {
            if (m.getValue() > max)
            {
                max = m.getValue();
                res = m.getKey();
            }
        }

        return res;
    }

    public TreeSet<String> getBrandSet(){ //Выводить марки часов без повторения в упорядоченном виде.
        TreeSet<String> res = new TreeSet<String>();
        for(IClock listClock : listClocks)
            res.add(listClock.GetBrand());
        return res;
    }
}
