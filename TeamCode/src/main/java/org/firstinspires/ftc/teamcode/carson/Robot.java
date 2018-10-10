package org.firstinspires.ftc.teamcode.carson;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Robot {
    private final HardwareMap map;

    final DcMotor right_motor;
    final DcMotor left_motor;
    final DcMotor climb;
    //do servos later
    final Servo lb_servo;// Left Bar Servo
    final Servo rb_servo;// Right Bar Servo
    final ColorSensor left_sensey;
    final ColorSensor right_sensey;
    
    public Robot(HardwareMap map) {
        this.map = map;
        left_motor = map.dcMotor.get("Left Motor");
        right_motor = map.dcMotor.get("Right Motor");
        climb = map.dcMotor.get("Climbing Motor");
        lb_servo = map.servo.get("Left Bar Motor");
        rb_servo = map.servo.get("Right Bar Motor");
        left_sensey = map.colorSensor.get("Left Color Sensor");
        right_sensey = map.colorSensor.get("Right Color Sensor");
    }





    static final double     counts_per_inch;
    static{
        double     counts_per_motor_rev    = 100/6 ;    // eg: TETRIX Motor Encoder 560 1120
        double     drive_gear_reduction    = 1 ;     // This is < 1.0 if geared UP 20
        double     wheel_diameter_inches   = 4 ;     // For figuring circumference 3.75
        counts_per_inch         = (counts_per_motor_rev * drive_gear_reduction) / (wheel_diameter_inches * 3.1415); //circumference 12.57 tpr 1120
    }




}
