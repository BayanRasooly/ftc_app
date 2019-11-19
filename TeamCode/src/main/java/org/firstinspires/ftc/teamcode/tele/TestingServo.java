package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="TestingServo", group="Linear Opmode")

public class TestingServo extends LinearOpMode {
    //@Override

    private Servo leftDropServo = null;
    private Servo rightDropServo = null;

    private Servo wristServo = null;
    private Servo grabServo = null;

    public void runOpMode() {

        leftDropServo = hardwareMap.get(Servo.class, "leftDropServo");
        rightDropServo = hardwareMap.get(Servo.class, "rightDropServo");

        wristServo = hardwareMap.get(Servo.class, "wristServo");
        grabServo = hardwareMap.get(Servo.class, "grabServo");

        waitForStart();

        if (gamepad2.dpad_left) {
            wristServo.setPosition(1);
            wristServo.setPosition(1);
        }else if(gamepad2.dpad_right){
            wristServo.setPosition(0);
            wristServo.setPosition(0);
        }
        if(gamepad1.a) {
            leftDropServo.setPosition(0.5);
            rightDropServo.setPosition(0.5);
        }
        if(gamepad1.b) {
            leftDropServo.setPosition(0);
            rightDropServo.setPosition(0);
        }

        if (gamepad2.a) {
            grabServo.setPosition(1);
        }

        if (gamepad2.b) {
            grabServo.setPosition(0);
        }

        if (gamepad2.left_stick_x > 0.1){
            wristServo.setPosition(gamepad2.left_stick_x);
        }

    }
}