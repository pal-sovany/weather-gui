package com.palsovany;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     * @throws MqttException
     */
    public static void main(String[] args) throws MqttException {
        JFrame frame = new JFrame("Hello World Swing Example");
        JLabel lblText = new JLabel("Hello World!");
        lblText.setBounds(130,100,100, 40);

        frame.add(lblText);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);

        IMqttClient client = new MqttClient("tcp://127.0.0.1:1234", "Test Java client");
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        client.connect(options);

        client.subscribe("test", (topic, msg) -> {
            byte[] payload = msg.getPayload();
            String message = new String(payload);
            lblText.setText(message);
        });
    }
}
