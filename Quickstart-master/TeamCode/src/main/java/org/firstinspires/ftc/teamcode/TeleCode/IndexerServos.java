package org.firstinspires.ftc.teamcode.TeleCode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IndexerServos {
    private Servo flipper_1;
    private Servo flipper_2;
    private Servo flipper_3;

    //init the Flippers
    public IndexerServos(HardwareMap hwMap) {
        flipper_1 = hwMap.get(Servo.class,"flipper_1");
        flipper_2 = hwMap.get(Servo.class,"flipper_2");
        flipper_3 = hwMap.get(Servo.class,"flipper_3");

        flipper_1.setDirection(Servo.Direction.REVERSE);
    }

    //Methods for the Flippers
    public void setFlipper_1(double angle1) {
        flipper_1.setPosition(angle1);
    }

    public void setFlipper_2(double angle2) {
        flipper_2.setPosition(angle2);
    }

    public void setFlipper_3(double angle3) {
        flipper_3.setPosition(angle3);
    }

    //Shoots all 3 flippers
    public void allFlippers(double angle1, double angle2, double angle3) throws InterruptedException {
        flipper_1.setPosition(angle1);
        flipper_2.setPosition(angle2);
        flipper_3.setPosition(angle3);
    }
}
