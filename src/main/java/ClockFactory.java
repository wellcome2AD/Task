public class ClockFactory {
    public IClock createClock(TypeOfClock type, String brand, int price){
        IClock clock = null;
        switch (type) {
            case HM:
                clock = new ClockHM(brand, price);
                break;
            case HMS:
                clock = new ClockHMS(brand, price);
                break;
        }
        return clock;
    }
}
