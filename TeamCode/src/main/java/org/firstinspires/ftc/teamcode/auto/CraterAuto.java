package org.firstinspires.ftc.teamcode.auto;

import android.util.Pair;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.teamcode.core.Encoder;

@Autonomous(name="Crater Auto", group="Robot")
public class CraterAuto extends LinearOpMode {

    public DcMotor r_motor;
    public DcMotor l_motor;

    public DcMotor claim;


    public DcMotor climb;
    public DistanceSensor left_distance;
    public DistanceSensor right_distance;

    Encoder en;

    public static final int SPEED = 1;

    private void initMap() {
        r_motor = hardwareMap.dcMotor.get("Right Drive Motor");
        l_motor = hardwareMap.dcMotor.get("Left Drive Motor");
        climb = hardwareMap.dcMotor.get("Lifting Motor");
        claim = hardwareMap.dcMotor.get("Intake Motor");
        left_distance = DistanceSensor.getLeft(hardwareMap);
        right_distance = DistanceSensor.getRight(hardwareMap);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        try {
            en = new Encoder(this);
            initMap();
            telemetry.addData("Status", "Ready to run");
            telemetry.update();
            waitForStart();
//        en.lower(climb, SPEED);



            Pair<Integer, boolean[]> pair = en.startAuto(l_motor, r_motor, climb);
        boolean[] minerals = pair.second;
        en.encoderDrive(l_motor, r_motor, 1,-(pair.first-2));
//
        int turn;
        if(minerals[0]) turn = 2;
        else if(minerals[1]) turn = 10;
        else turn = 15;

        en.encoderDrive(l_motor,r_motor,SPEED,-turn,turn);
        en.align(l_motor, r_motor, left_distance, right_distance);
        en.encoderDrive(l_motor, r_motor, SPEED,36);//drive to wall
        en.encoderDrive(l_motor, r_motor, SPEED,-20,20);//turn to claim
        en.align(l_motor,r_motor,left_distance,right_distance);
        en.encoderDrive(l_motor, r_motor, SPEED,56);
        en.claim(claim);
//        en.encoderDrive(l_motor, r_motor, SPEED,-144);
        }catch(Throwable a){
        }
    }
}
