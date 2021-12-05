package Clocks;

public class ClocksTest {

        public static void main(String[] args){

            ClockFactory clockFactory = new ClockFactory();

            int h = 12, m = 45, s = 20;

            IClock clock1 = clockFactory.createClock(TypeOfClock.HM, "Clocks.ClockHM", 100 );
            IClock clock2 = clockFactory.createClock(TypeOfClock.HMS, "Clocks.ClockHMS", 200 );

            System.out.println(clock1);
            System.out.println(clock2);

            System.out.println("Set 12:45:20");
            try { //Устанавливать заданное время на часах.
                clock1.SetTime(EArrow.H, h);
                clock1.SetTime(EArrow.M, m);
                clock1.SetTime(EArrow.S, s);
            }
            catch (TimeError e)
            {
                e.printStackTrace();
            }
            catch (MissingError ignored){ }
            System.out.println(clock1);

            try { //Устанавливать заданное время на часах.
                clock2.SetTime(EArrow.H, h);
                clock2.SetTime(EArrow.M, m);
                clock2.SetTime(EArrow.S, s);
            }
            catch (TimeError e)
            {
                e.printStackTrace();
            }
            catch (MissingError ignored){ }
            System.out.println(clock2);

            System.out.println("Change to 1:30:40");
            try { //Переводить часы.
                clock1.ChangeTime(EArrow.H, h);
                clock1.ChangeTime(EArrow.M, -m);
                clock1.ChangeTime(EArrow.S, -s);
            }
            catch (TimeError e)
            {
                e.printStackTrace();
            }
            catch (MissingError ignored){ }
            System.out.println(clock1);

            try { //Переводить часы.
                clock2.ChangeTime(EArrow.H, h);
                clock2.ChangeTime(EArrow.M, m);
                clock2.ChangeTime(EArrow.S, s);
            }
            catch (TimeError e)
            {
                e.printStackTrace();
                return;
            }
            catch (MissingError ignored){ }
            System.out.println(clock2);

            System.out.println("Get brand & price: " + clock1.GetBrand() + ' '+ clock1.GetPrice());
            System.out.println("Get brand & price: " + clock2.GetBrand() + ' '+ clock2.GetPrice());
        }
}
