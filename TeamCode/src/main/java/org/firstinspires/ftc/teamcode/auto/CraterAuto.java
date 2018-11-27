package org.firstinspires.ftc.teamcode.auto;

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
;

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
//        en.lower(climb, SPEED);

        boolean[] minerals = new MineralReader(hardwareMap).read();

        if(!minerals[1]){
            int left = minerals[0]?5:0;
            int right = minerals[0]?0:5;
            en.encoderDrive(l_motor, r_motor, SPEED, left, right );
        }
        int dist = minerals[1] ? 24 : 30;
        en.encoderDrive(l_motor, r_motor, SPEED,dist);
        en.encoderDrive(l_motor, r_motor, SPEED,-(dist-2));
        //TURN 45*TODO
        en.align(l_motor, r_motor, left_distance, right_distance);
        en.encoderDrive(l_motor, r_motor, SPEED,5);//drive to wall
        en.encoderDrive(l_motor, r_motor, SPEED,-5,5);//turn to claim
        en.align(l_motor, r_motor, left_distance, right_distance);//may be needed, may waste time
        en.encoderDrive(l_motor, r_motor, SPEED,144);
        en.claim();
        en.encoderDrive(l_motor, r_motor, SPEED,-144);
    }
}