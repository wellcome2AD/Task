package Clocks;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Shop {

    private final ArrayList<IClock> listClocks = new ArrayList<>();

    public Shop(){
    }

    public void AddClock(IClock clock){
        listClocks.add(clock);
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
        for (IClock listClock : listClocks) {
            try {
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

    public void RemoveClock(IClock clock) {
        listClocks.remove(clock);
    }

    public void RemoveAllClock() {
        listClocks.clear();
    }

    public void WriteToFile(String nameOfFile){
        File listOfClocks = new File(".", nameOfFile);
        String res = new String();

        for (IClock clock: listClocks) {
            res = res + clock.GetBrand() + ';' + clock.GetType() + ';' + clock.GetPrice()
                    + ';' + clock.GetTimeString() + ';' + "\r\n";
        }
        try {
            FileWriter writer = new FileWriter(listOfClocks, false);
            writer.write(res);
            writer.close();
            //writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ReadFromFile(String nameOfFile){
        File listOfClocks = new File(".", nameOfFile);
        String line = "";

        try
        {
            FileReader fr = new FileReader(listOfClocks);
            BufferedReader reader = new BufferedReader(fr);
            line = reader.readLine();
            while (line != null) {
                String[] clockInfo = line.split(";");
                String[] timeInfo = clockInfo[3].split(":");
                ClockFactory clockFactory = new ClockFactory();

                IClock clock = clockFactory.createClock(valueOf(clockInfo[1]), clockInfo[0], Integer.parseInt(clockInfo[2]));

                int size = timeInfo.length;
                clock.SetTime(EArrow.H, Integer.parseInt(timeInfo[0]));
                clock.SetTime(EArrow.M, Integer.parseInt(timeInfo[1]));
                if (size == 3)
                    clock.SetTime(EArrow.S, Integer.parseInt(timeInfo[2]));

                AddClock(clock);

                line = reader.readLine();
            }
            reader.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        catch (TimeError | MissingError ignored) {}
    }

    private TypeOfClock valueOf(String sType)
    {
        switch (sType) {
            case "HM":
                return TypeOfClock.HM;
            case "HMS":
                return TypeOfClock.HMS;
            default:
                return null;
        }
    }
}
