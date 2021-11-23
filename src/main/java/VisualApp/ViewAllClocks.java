package VisualApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Clocks.*;

public class ViewAllClocks extends javax.swing.JPanel implements IObserver {

    AllClocks allClocks = new AllClocks();

    private JTextField TypeField;
    private JTextField BrandField;
    private JTextField PriceField;
    private JButton addClockButton;
    private JPanel MainPanel;
    private JPanel ContentPanel;
    private JPanel _contentPanel;
    private JScrollPane ScrollPane;
    private JTextField HourField;
    private JTextField MinuteField;
    private JTextField SecondField;
    private JButton setTimeButton;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public ViewAllClocks() {
        addClockButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                TypeOfClock type = null;
                String sType = "";
                String brand = "";
                int price = 0;

                try
                {
                    if (TypeField.getText().isEmpty() || BrandField.getText().isEmpty() || PriceField.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Not all fields are entered", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    sType = TypeField.getText();
                    brand = BrandField.getText();
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "No data entered", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try
                {
                    price = Integer.valueOf(PriceField.getText());
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Incorrect clock type", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ClockFactory clockF = new ClockFactory();

                type = IClock.valueOf(sType);
                if (type == null)
                {
                    JOptionPane.showMessageDialog(null, "Incorrect data type", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                IClock clock = clockF.createClock(type, brand, price);
                allClocks.add(clock);
            }
        });
        setTimeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int hour = 0, min = 0, sec = 0;
                try {
                    if (HourField.getText().isEmpty() || MinuteField.getText().isEmpty() || SecondField.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Not all fields are entered", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    hour = Integer.valueOf(HourField.getText());
                    min = Integer.valueOf(MinuteField.getText());
                    sec = Integer.valueOf(SecondField.getText());
                }
                catch(Exception er)
                {
                    JOptionPane.showMessageDialog(null, "Incorrect data type", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    allClocks.setTime(hour, min, sec);
                }
                catch(TimeError er)
                {
                    JOptionPane.showMessageDialog(null, "Incorrect time", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                catch (MissingError ignored){}
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Clocks.Shop of clock");
        ViewAllClocks viewAllClocks = new ViewAllClocks();
        frame.setContentPane(viewAllClocks.MainPanel);
        viewAllClocks.allClocks.addO(viewAllClocks);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setAlwaysOnTop(false);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void event(AllClocks allClocks) {
        ContentPanel.removeAll();
        ArrayList<IClock> listOfClocks = allClocks.getAllClocks();
        for (IClock object : listOfClocks) {
            ViewClock vc = new ViewClock();
            vc.SetClock(object, allClocks);
            ContentPanel.add(vc.panel1);
        }
        ScrollPane.revalidate();
    }

    private void createUIComponents() {
        ContentPanel = new JPanel(new GridLayout(0, 1));
        ContentPanel.setVisible(true);

        ScrollPane = new JScrollPane();
        ScrollPane.setViewportView(ContentPanel);
    }
}
