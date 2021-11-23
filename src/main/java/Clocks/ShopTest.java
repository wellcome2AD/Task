package Clocks;

import VisualApp.ViewAllClocks;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class ShopTest {

    public static void main(String[] args){

        if(true) {
            ViewAllClocks a = new ViewAllClocks();
        }
        Shop clocksShop = new Shop();
        Scanner in = new Scanner(System.in);

        int h, m, s;

        System.out.println("We have many clocks in our shop:");
        ArrayList<IClock> listClocks = clocksShop.getClockList(); //Вывод списка часов.
        System.out.println(listClocks);

        System.out.println("\nSet time:");
        h = in.nextInt();
        m = in.nextInt();
        s = in.nextInt();

        try { //Устанавливать заданное время на всех часах.
            clocksShop.setTime(h, m, s);
        }
        catch (TimeError e) { return; }
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
