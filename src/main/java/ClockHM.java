public class ClockHM implements IClock{

    private int h = 0;
    private int m = 0;
    public String brand;
    public int price;

    public ClockHM(String _brand, int _price){
        brand = _brand;
        price = _price;
    }

    public ClockHM(String _brand, int _price, int _h, int _m) throws TimeError {
        brand = _brand;
        price = _price;
        if (_h >= 0 && _h < 24){
            h = _h;
        }
        else{
            throw new TimeError("Incorrect input: H, " + _h);
        }
        if (_m >= 0 && _m < 60){
            m = _m;
        }
        else{
            throw new TimeError("Incorrect input: M, " + _m);
        }
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
    public String toString(){
        return "\nBrand: " + brand + ", Price: " + price + ", Time: " + "h=" + h + ", m=" + m;
    }
}
