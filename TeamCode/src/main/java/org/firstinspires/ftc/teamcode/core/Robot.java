package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Robot {
    private final HardwareMap map;

    public final DcMotor rb_motor;
    public final DcMotor lb_motor;
    public final DcMotor rf_motor;
    public final DcMotor lf_motor;



    public final DcMotor climb;
    //do servos later
    public final Servo lb_servo;// Left Bar Servo
    public final Servo rb_servo;// Right Bar Servo
    public final ColorSensor left_sensey;
    public final ColorSensor right_sensey;
    public final DistanceSensor left_distance;
    public final DistanceSensor right_distance;

    
    public Robot(HardwareMap map) {
        this.map = map;
        rb_motor = map.dcMotor.get("Right Back Motor");
        lb_motor = map.dcMotor.get("Left Back Motor");
        rf_motor = map.dcMotor.get("Right Front Motor");
        lf_motor = map.dcMotor.get("Left Front Motor");

        climb = map.dcMotor.get("Climbing Motor");
        lb_servo = map.servo.get("Left Bar Motor");
        rb_servo = map.servo.get("Right Bar Motor");
        left_sensey = map.colorSensor.get("Left Color Sensor");
        right_sensey = map.colorSensor.get("Right Color Sensor");
        left_distance = (DistanceSensor) map.opticalDistanceSensor.get("Left Distance Sensor");
        right_distance = (DistanceSensor) map.opticalDistanceSensor.get("Right Distance Sensor");
    }

    public static final double     counts_per_inch;
    static{
        double     counts_per_motor_rev    = 100/6 ;    // eg: TETRIX Motor Encoder 560 1120
        double     drive_gear_reduction    = 1 ;     // This is < 1.0 if geared UP 20
        double     wheel_diameter_inches   = 4 ;     // For figuring circumference 3.75
        counts_per_inch         = (counts_per_motor_rev * drive_gear_reduction) / (wheel_diameter_inches * 3.1415); //circumference 12.57 tpr 1120
    }


}
