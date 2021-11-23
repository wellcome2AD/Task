package VisualApp;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Clocks.*;

public class ViewShop extends javax.swing.JPanel implements IObserver {

    AllClocks allClocks = new AllClocks();

    private JTextField TypeField;
    private JTextField BrandField;
    private JTextField PriceField;
    private JButton addClockButton;
    private JPanel MainPanel;
    private JPanel ContentPanel;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public ViewShop() {
        addClockButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                ClockFactory clockF = new ClockFactory();

                String sType = TypeField.getText();
                TypeOfClock type = TypeOfClock.HM;
                String brand = "Clock";
                int price = 123;

                if (sType == "HM")
                    type = TypeOfClock.HM;
                else if (sType == "HMS")
                    type = TypeOfClock.HMS;

                IClock clock = clockF.createClock(type, brand, price);
                allClocks.add(clock);

                ViewClock aViewClock = new ViewClock();
                aViewClock.SetClock(clock, allClocks);
                aViewClock.setVisible(true);
                ContentPanel.removeAll();
                ContentPanel.add(aViewClock.panel1);
                ContentPanel.revalidate();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Clocks.Shop of clock");
        frame.setContentPane(new ViewShop().MainPanel);
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
        /*ContentPanel.removeAll();
        ArrayList<IClock> listOfClocks = allClocks.getAllPair();
        for (IClock object : listOfClocks) {
            ViewClock vc = new ViewClock();
            vc.SetClock(object, allClocks);
            ContentPanel.add(vc);
        }
        ScrollPane.revalidate();*/
    }
}
