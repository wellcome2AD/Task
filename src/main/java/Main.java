import java.util.ArrayList;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args){
        Shop clocksShop = new Shop();

        int h = 12, m = 30, s = 45;

        ArrayList<IClock> listClocks = clocksShop.getClockList(); //Вывод списка часов.
        System.out.println(listClocks);

        try { //Устанавливать заданное время на всех часах.
            clocksShop.setTime(h, m, s);
        }
        catch (TimeError e)
        {
            return;
        }
        catch (MissingError ignored){ }

        listClocks = clocksShop.getClockList(); //Вывод списка часов.
        System.out.println(listClocks);

        IClock expClock = clocksShop.mostExpClock(); //Получать описания самых дорогих часов.
        System.out.println("\nThe most expensive clock: " + expClock);

        String mostFreqBrand = clocksShop.mostFreqBrand(); //Получать название наиболее часто встречающейся марки часов в магазине.
        System.out.println("\nThe most frequent brand is " + mostFreqBrand);

        TreeSet<String> sortedBrands = clocksShop.getBrandSet(); //Выводить марки часов без повторения в упорядоченном виде.
        System.out.println("\nAll brands: sorted & without repeats: " + sortedBrands);
    }
}
