package com.palsovany;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;

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
        frame.setSize(screenSize);
        frame.getContentPane().setLayout(new GridBagLayout());
        frame.setVisible(true);

        JLabel lblText = new JLabel(String.format(DISPLAY_TEMPLATE, 0.0f));
        lblText.setFont(new Font("Serif", Font.BOLD, FONT_SIZE));
        lblText.setSize(300, FONT_SIZE);

        JPanel panel = new JPanel();
        panel.add(lblText);
        frame.add(panel, new GridBagConstraints());

        IMqttClient client = new MqttClient("tcp://127.0.0.1:1234", "Test Java client");
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        client.connect(options);

        client.subscribe("test", (topic, msg) -> {
            byte[] payload = msg.getPayload();
            String message = new String(payload);
            float currentTemperature = Float.parseFloat(message);
            lblText.setText(String.format(DISPLAY_TEMPLATE, currentTemperature));
        });
    }
}
