package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.core.Encoder;

@Autonomous(name="Claim Zone Auto", group="Robot")

public class ClaimZoneAuto extends LinearOpMode{




    private ElapsedTime runtime = new ElapsedTime();

    Encoder en;
    
    public static final int SPEED = 1;


    public DcMotor r_motor;
    public DcMotor l_motor;



    public DcMotor climb;
    //do servos later
    public Servo lb_servo;// Left Bar Servo
    public Servo rb_servo;// Right Bar Servo

    public ColorSensor left_sensey;
    public ColorSensor right_sensey;
    public DistanceSensor left_distance;
    public DistanceSensor right_distance;

    
    private void initMap(){
        r_motor = hardwareMap.dcMotor.get("Right Back Motor");
        l_motor = hardwareMap.dcMotor.get("Left Motor");
        climb = hardwareMap.dcMotor.get("Climbing Motor");
        lb_servo = hardwareMap.servo.get("Left Bar Motor");
        rb_servo = hardwareMap.servo.get("Right Bar Motor");
        left_sensey = hardwareMap.colorSensor.get("Left Color Sensor");
        right_sensey = hardwareMap.colorSensor.get("Right Color Sensor");
        left_distance = (DistanceSensor) hardwareMap.opticalDistanceSensor.get("Left Distance Sensor");
        right_distance = (DistanceSensor) hardwareMap.opticalDistanceSensor.get("Right Distance Sensor");
    }

    public void runOpMode() throws InterruptedException {
        initMap();
        en = new Encoder(this);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        en.lower(climb,SPEED);
        left_sensey.enableLed(true);
        right_sensey.enableLed(true);
        //en.align(l_motor, r_motor, left_distance, right_distance);
        if(!en.leftInBounds(left_sensey) || !en.rightInBounds(right_sensey)){
            if(!en.leftInBounds(left_sensey)){
                en.setLeftMotorPower(l_motor,SPEED);
                while(!en.leftInBounds(left_sensey)){
                    en.wait(10);
                }
                en.setLeftMotorPower(l_motor,0);
            }
            en.setRightMotorPower(r_motor,SPEED);
            while(!en.rightInBounds(right_sensey)){
                en.wait(10);
            }
            en.setRightMotorPower(r_motor,0);
        }

        boolean[] minerals = new MineralReader(hardwareMap).read();

        lb_servo.setPosition(0);
        rb_servo.setPosition(0);

        if (!minerals[1]) {
            en.encoderDrive(l_motor, r_motor, SPEED, minerals[0] ? -5 : 5, minerals[2] ? -5 : 5);
            en.encoderDrive(l_motor, r_motor, SPEED, 5);
            //en.encoderDrive(l_motor, r_motor, SPEED, minerals[0] ? 5 : -5, minerals[2] ? 5 : -5);
            en.align(l_motor,r_motor,left_distance,right_distance);
            //drive forward
            en.encoderDrive(l_motor, r_motor, SPEED,5);

            //turn
            int mult = minerals[0]?-SPEED:SPEED;//HARD
            en.encoderDrive(l_motor, r_motor, SPEED,mult*-5,mult*5);//HARD

            //drive to claim zone
            en.setBothMotorPower(r_motor,l_motor,SPEED);
            while(!en.leftInBounds(left_sensey)){//test for the left color sensor, shouldn't matter
                en.unsafeWait(10);
            }
            en.setBothMotorPower(r_motor,l_motor,0);
            en.claim();
            en.align(l_motor,r_motor,left_distance,right_distance);
        } else {
            en.encoderDrive(l_motor, r_motor, SPEED, 5);
            en.claim();
            en.encoderDrive(l_motor, r_motor, SPEED,1,-1);
            en.align(l_motor,r_motor,left_distance,right_distance);
        }
        en.encoderDrive(l_motor, r_motor, SPEED,-288);
    }






}
