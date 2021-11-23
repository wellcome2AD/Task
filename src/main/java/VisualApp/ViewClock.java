package VisualApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;

import Clocks.*;

public class ViewClock extends JPanel{

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
    private JLabel Label4;
    private JLabel TimeField;

    public ViewClock() {
        deleteThisClockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                panel1.setVisible(false);
                allClocks.remove(clock);
            }
        });
    }

    public void SetClock(IClock clock, AllClocks allClocks)
    {
        this.clock = clock;
        this.allClocks = allClocks;
        TypeLabel.setText(String.valueOf(clock.GetType()));
        BrandLabel.setText(clock.GetBrand());
        PriceLabel.setText(String.valueOf(clock.GetPrice()));
        TimeField.setText(clock.GetTimeString());
    }

    //public void SetTime(IClock clock, int h, )

}
