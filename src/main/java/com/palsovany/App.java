package com.palsovany;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Hello world!
 */
public final class App {

    private static final int FONT_SIZE = 60;
    private static final String DISPLAY_TEMPLATE = "%.1f °C";

    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     * @throws MqttException
     */
    public static void main(String[] args) throws MqttException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Temperature Monitor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(screenSize);
        frame.setExtendedState(frame.getExtendedState() | Frame.MAXIMIZED_BOTH);
        
        JLabel lblText = new JLabel(String.format(DISPLAY_TEMPLATE, 0.0f));
        lblText.setFont(new Font("Serif", Font.BOLD, FONT_SIZE));
        
        JLabel timeText = new JLabel();
        timeText.setFont(new Font("Serif", Font.BOLD, 30));
        
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 0.7;
        contentPane.add(lblText, gridBagConstraints);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_END; 
        contentPane.add(timeText, gridBagConstraints);

        IMqttClient client = new MqttClient("tcp://127.0.0.1:1234", "Weather GUI client");
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        client.connect(options);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        client.subscribe("test", (topic, msg) -> {
            byte[] payload = msg.getPayload();
            String message = new String(payload);
            float currentTemperature = Float.parseFloat(message);
            lblText.setText(String.format(DISPLAY_TEMPLATE, currentTemperature));
            timeText.setText(LocalDateTime.now().format(formatter));
        });
    }
}
