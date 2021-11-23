import javax.swing.*;
import java.awt.*;

public class ViewClock extends Component {
    private JPanel panel1;
    private JLabel TypeLabel;
    private JLabel BrandLabel;
    private JLabel PriceLabel;
    private JButton deleteThisClockButton;
    private JLabel typeLabel;
    private JLabel brandLabel;
    private JLabel priceLabel;

    public ViewClock() {
    }

    public void SetClock(IClock clock) {
        TypeLabel.setText(IClock.valueOf(clock.GetType()));
        BrandLabel.setText(clock.GetBrand());
        PriceLabel.setText(String.valueOf(clock.GetPrice()));
    }

}
