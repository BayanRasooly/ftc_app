package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.core.Encoder;

@Autonomous(name="Claim Zone Auto SAFE", group="Robot")

public class ClaimZoneAutoSAFE extends LinearOpMode{

    public DcMotor r_motor;
    public DcMotor l_motor;



    public DcMotor climb;
    public DistanceSensor left_distance;
    public DistanceSensor right_distance;

    Encoder en;

    public static final int SPEED = 1;

    private void initMap() {
        r_motor = hardwareMap.dcMotor.get("Right Drive Motor");
        l_motor = hardwareMap.dcMotor.get("Left Drive Motor");
        climb = hardwareMap.dcMotor.get("Lifting Motor");
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
        en.lower(climb,SPEED);

        boolean[] minerals = new MineralReader(hardwareMap).read();

        if (!minerals[1]) {//if side mineral
            en.encoderDrive(l_motor, r_motor, SPEED, minerals[0] ? -5 : 5, minerals[2] ? -5 : 5);//turn to mineral
            en.encoderDrive(l_motor, r_motor, SPEED, 5);//drive forward
            en.align(l_motor,r_motor,left_distance,right_distance);
            //drive forward
            en.encoderDrive(l_motor, r_motor, SPEED,5);

            //turn
            int mult = minerals[0]?-SPEED:SPEED;
            en.encoderDrive(l_motor, r_motor, SPEED,mult*-5,mult*5);

            //drive to claim zone
            en.setBothMotorPower(r_motor,l_motor,SPEED);
            en.setBothMotorPower(r_motor,l_motor,0);
            en.claim();
            if(minerals[2])//SAFE CODE
                en.encoderDrive(l_motor,r_motor,SPEED,5,-5);//SAFE CODE turn to drive backwards the other way
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
