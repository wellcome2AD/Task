public interface IClock{
    void SetTime(EArrow type, int value) throws TimeError, MissingError;
    String GetBrand();
    int GetPrice();
}
