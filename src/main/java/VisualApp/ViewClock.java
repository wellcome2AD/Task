package VisualApp;

import javax.swing.*;
import java.awt.*;
import Clocks.*;

public class ViewClock extends Component {

    IClock clock;
    AllClocks allClocks;

    public JPanel panel1;
    private JLabel TypeLabel;
    private JLabel BrandLabel;
    private JLabel PriceLabel;
    private JButton deleteThisClockButton;
    private JLabel Label1;
    private JLabel Label2;
    private JLabel Label3;

    public ViewClock() {
    }

    public void SetClock(IClock clock, AllClocks allClocks)
    {
        this.clock = clock;
        this.allClocks = allClocks;
        TypeLabel.setText(String.valueOf(clock.GetType()));
        BrandLabel.setText(clock.GetBrand());
        PriceLabel.setText(String.valueOf(clock.GetPrice()));
    }

}
