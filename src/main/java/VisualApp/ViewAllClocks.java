package VisualApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import Clocks.*;

public class ViewAllClocks extends javax.swing.JPanel implements IObserver {

    AllClocks allClocks = new AllClocks();

    private JTextField BrandField;
    private JTextField TypeField;
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
    private JButton readFromFileButton;
    private JButton writeButton;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(ViewAllClocks::createAndShowGUI);
    }

    public ViewAllClocks() {
        addClockButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                TypeOfClock type;
                String sType;
                String brand;
                int price;

                try
                {
                    if (BrandField.getText().isEmpty() || TypeField.getText().isEmpty() || PriceField.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Not all fields are entered", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    brand = BrandField.getText();
                    sType = TypeField.getText();
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "No data entered", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try
                {
                    price = Integer.parseInt(PriceField.getText());
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Incorrect data type", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ClockFactory clockF = new ClockFactory();

                type = IClock.valueOf(sType);
                if (type == null)
                {
                    JOptionPane.showMessageDialog(null, "Incorrect clock type", "Error", JOptionPane.ERROR_MESSAGE);
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
                int hour, min, sec;
                try {
                    if (HourField.getText().isEmpty() || MinuteField.getText().isEmpty() || SecondField.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Not all fields are entered", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    hour = Integer.parseInt(HourField.getText());
                    min = Integer.parseInt(MinuteField.getText());
                    sec = Integer.parseInt(SecondField.getText());
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
        writeButton.addMouseListener(new MouseAdapter() {
        });
        writeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FileDialog fd = new FileDialog((Frame) null, "Choose a file", FileDialog.LOAD);
                fd.setDirectory(".");
                fd.setFile(".txt");
                fd.setVisible(true);
                String filename = fd.getFile();
                if (filename == null)
                    return;
                else
                    allClocks.getShop().WriteToFile(filename);
            }
        });
        readFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FileDialog fd = new FileDialog((Frame) null, "Choose a file", FileDialog.LOAD);
                fd.setDirectory(".");
                fd.setFile(".txt");
                fd.setVisible(true);
                String filename = fd.getFile();
                if (filename == null)
                    return;
                else {
                    allClocks.removeAll();

                    allClocks.getShop().ReadFromFile(filename);
                    event(allClocks);
                }
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
