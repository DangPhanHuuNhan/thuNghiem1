package dongHo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WorldClock extends JFrame {
    private JLabel clockLabel;
    private JComboBox<String> timeZoneComboBox;
    private Timer timer;

    private String[] timeZones = {
            "UTC+0", "UTC+1", "UTC+2", "UTC+3", "UTC+4", "UTC+5", "UTC+6", "UTC+7", "UTC+8", "UTC+9", "UTC+10", "UTC+11", "UTC+12"
    };

    public WorldClock() {
        setTitle("World Clock");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        initComponents();
        addComponents();

        setVisible(true);
    }

    private void initComponents() {
        clockLabel = new JLabel("", SwingConstants.CENTER);
        clockLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        timeZoneComboBox = new JComboBox<>(timeZones);
        timeZoneComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
        timer.start();
    }

    private void addComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(clockLabel, BorderLayout.CENTER);
        mainPanel.add(timeZoneComboBox, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private void updateTime() {
        int selectedTimeZoneIndex = timeZoneComboBox.getSelectedIndex();
        long currentTimeMillis = System.currentTimeMillis();
        long timeZoneOffset = (selectedTimeZoneIndex - 12) * 3600000; // Convert to milliseconds

        long timeInSelectedTimeZone = currentTimeMillis + timeZoneOffset;
        clockLabel.setText(getTimeText(timeInSelectedTimeZone));
    }

    private String getTimeText(long timeInMillis) {
        long totalSeconds = timeInMillis / 1000;
        long currentSecond = totalSeconds % 60;
        long totalMinutes = totalSeconds / 60;
        long currentMinute = totalMinutes % 60;
        long totalHours = totalMinutes / 60;
        long currentHour = totalHours % 24;

        return String.format("%02d:%02d:%02d", currentHour, currentMinute, currentSecond);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WorldClock();
            }
        });
    }
}

