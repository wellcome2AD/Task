public class ClockHMS extends ClockHM{
    int s;

    public ClockHMS(String _brand, int _price) {
        super(_brand, _price);
    }

    public ClockHMS(String _brand, int _price, int _h, int _m, int _s) throws TimeError{
        super(_brand, _price, _h, _m);
        if (_s >= 0 && _s < 60){
            s = _s;
        }
        else{
            throw new TimeError("Incorrect input: S, " + _h);
        }
    }

    @Override
    public void SetTime(EArrow type, int val) throws TimeError {
        try{
            super.SetTime(type, val);
        }
        catch(TimeError ex)
        {
            if (val >= 0 && val < 60 ) {
                s = val;
                return;
            }
            throw new TimeError("Incorrect input: " + type + ", " + val);
        }
        catch(MissingError ignored) { }
    }

    @Override
    public String toString(){
        return super.toString() + ", s=" + s;
    }
}
