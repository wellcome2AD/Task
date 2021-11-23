package Clocks;

public interface IClock{
    TypeOfClock GetType();
    String GetBrand();
    int GetPrice();
    void SetTime(EArrow type, int val) throws TimeError, MissingError;
    void ChangeTime(EArrow type, int val) throws TimeError, MissingError;

    String GetTimeString();

    public static TypeOfClock valueOf(String sType)
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
    public static String valueOf(TypeOfClock type)
    {
        switch (type) {
            case HM:
                return "Clock with HM-arrows";
            case HMS:
                return "Clock with HMS-arrows";
            default:
                return "Unknown type";
        }
    }
}
