public interface IClock{
    void SetTime(EArrow type, int val) throws TimeError, MissingError;
    void ChangeTime(EArrow type, int val) throws TimeError, MissingError;
    String GetBrand();
    int GetPrice();
}
