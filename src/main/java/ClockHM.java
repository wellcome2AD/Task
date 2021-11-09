public class ClockHM implements IClock{

    protected int h = 0, m = 0, price;
    protected String brand;

    public ClockHM(String _brand, int _price){
        brand = _brand;
        price = _price;
    }

    @Override
    public String GetBrand() {
        return brand;
    }

    @Override
    public int GetPrice() {
        return price;
    }

    @Override
    public void SetTime(EArrow type, int val) throws TimeError, MissingError {
        if(type == EArrow.H){
            if (val >= 0 && val < 24) {
                h = val;
                return;
            }
            throw new TimeError("Incorrect input: " + type + ", " + val);
        }
        if(type == EArrow.M){
            if (val >= 0 && val < 60 ) {
                m = val;
                return;
            }
            throw new TimeError("Incorrect input: " + type + ", " + val);
        }
        throw new MissingError("Type " + TypeOfClock.HM + " doesn't have a S-arrow");
    }

    @Override
    public void ChangeTime(EArrow type, int val) throws TimeError, MissingError{
        if(type == EArrow.H){
            if (val >= 0 && val < 24) {
                h = (h + val) % 24;
                return;
            }
            throw new TimeError("Incorrect input: " + type + ", " + val);
        }
        if(type == EArrow.M){
            if (val >= 0 && val < 60 ) {
                h = (h + (m + val) / 60) % 24;
                m = (m + val) % 60;
                return;
            }
            throw new TimeError("Incorrect input: " + type + ", " + val);
        }
        throw new MissingError("Type " + TypeOfClock.HM + " doesn't have a S-arrow");
    }

    @Override
    public String toString(){
        return "\nBrand: " + brand + ", Price: " + price + ", Time: " + "h=" + h + ", m=" + m;
    }
}
