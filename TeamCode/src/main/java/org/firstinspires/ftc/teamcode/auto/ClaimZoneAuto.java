package org.firstinspires.ftc.teamcode.auto;

import android.util.Pair;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.core.Encoder;

@Autonomous(name="Claim Zone Auto", group="Robot")

public class ClaimZoneAuto extends LinearOpMode{

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

        Pair<Integer,boolean[]> pair = en.startAuto(this,l_motor,r_motor,climb);

        boolean[] minerals = pair.second;

        if (!minerals[1]) {
            //turn
            int mult = minerals[0]?-SPEED:SPEED;//HARD
            en.encoderDrive(l_motor, r_motor, SPEED,mult*-5,mult*5);//HARD

            //drive to claim zone
            en.setBothMotorPower(r_motor,l_motor,SPEED);

            en.setBothMotorPower(r_motor,l_motor,0);
            en.claim();
            en.align(l_motor,r_motor,left_distance,right_distance);
        } else {
            en.encoderDrive(l_motor, r_motor, SPEED, 27);
            en.claim();
            en.encoderDrive(l_motor, r_motor, SPEED,10,-10);
            en.align(l_motor,r_motor,left_distance,right_distance);
        }
        en.encoderDrive(l_motor, r_motor, SPEED,-288);
    }
}
