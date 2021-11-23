public class ClockHMS extends ClockHM{
    protected int s = 0;

    public ClockHMS(String _brand, int _price) { super(_brand, _price); }

    @Override
    public TypeOfClock GetType() {
        return TypeOfClock.HMS;
    }

    @Override
    public void SetTime(EArrow type, int val) throws TimeError{
        try{
            super.SetTime(type, val);
        }
        catch(MissingError ex)
        {
            if (val >= 0 && val < 60 ) {
                s = val;
                return;
            }
            throw new TimeError("Incorrect input: " + type + ", " + val);
        }
    }

    @Override
    public void ChangeTime(EArrow type, int val) throws TimeError{
        try{
            super.ChangeTime(type, val);
        }
        catch(MissingError ex)
        {
            if (val >= 0 && val < 60 ) {
                h = (h + (m + (s + val) / 60) / 60) % 24;
                m = (m + (s + val) / 60) % 60;
                s = (s + val) % 60;
                return;
            }
            throw new TimeError("Incorrect input: " + type + ", " + val);
        }
    }

    @Override
    public String toString(){
        return super.toString() + ", s=" + s;
    }
}
