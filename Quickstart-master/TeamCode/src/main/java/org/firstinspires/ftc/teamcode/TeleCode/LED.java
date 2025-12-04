package org.firstinspires.ftc.teamcode.TeleCode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LED;

public class LED {
    private  L redLED;
    private LED greenLED;

    public LED(HardwareMap hwMap) {
        redLED = hwMap.get(LED.class, "red_LED");
        greenLED = hwMap.get(LED.class, "green_LED");
    }


    public void setRedLED(boolean isOn) {
        if (isOn) {
            redLED.on();
        }
        else {
            redLED.off();
        }
    }

    public void setGreenLED(boolean isOn) {
        if (isOn) {
            greenLED.on();
        }
        else {
            greenLED.off();
        }
    }
}