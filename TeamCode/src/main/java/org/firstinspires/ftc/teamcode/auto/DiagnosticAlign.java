package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.core.Encoder;

//@Autonomous(name="Align", group="Robot")
public class DiagnosticAlign extends LinearOpMode {

    public DcMotor r_motor;
    public DcMotor l_motor;



    public DcMotor climb;
    //do servos later
    public ColorSensor left_sensey;
    public ColorSensor right_sensey;
    public DistanceSensor left_distance;
    public DistanceSensor right_distance;

    public static final int SPEED = 1;

    public static Encoder en;

    private void initMap() {
        r_motor = hardwareMap.dcMotor.get("Right Drive Motor");
        l_motor = hardwareMap.dcMotor.get("Left Drive Motor");
        climb = hardwareMap.dcMotor.get("Lifting Motor");
        left_sensey = hardwareMap.colorSensor.get("Left Color Sensor");
        right_sensey = hardwareMap.colorSensor.get("Right Color Sensor");
        left_distance = DistanceSensor.getLeft(hardwareMap);
        right_distance = DistanceSensor.getRight(hardwareMap);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        en = new Encoder(this);
        initMap();
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        int count = 0;

        telemetry.addData("left",new Func<Double>(){
            @Override
            public Double value() {
                return l_motor.getPower();
            }
        });
        telemetry.addData("right",new Func<Double>(){
            @Override
            public Double value() {
                return r_motor.getPower();
            }
        });

        telemetry.addData("left distance", new Func<Integer>(){
            @Override
            public Integer value() {
                return left_distance.getUltrasonic();
            }
        });

        telemetry.addData("right distance", new Func<Integer>(){
            @Override
            public Integer value() {
                return right_distance.getUltrasonic();
            }
        });


        while(opModeIsActive()){
            Thread.sleep(1000);
            en.align(l_motor,r_motor,left_distance,right_distance);
            telemetry.addData("iter",count++);
            telemetry.update();
        }
    }
}