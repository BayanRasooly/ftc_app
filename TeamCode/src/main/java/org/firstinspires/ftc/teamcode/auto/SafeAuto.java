package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.Encoder;

@Autonomous(name="Auto SAFE", group="Robot")
public class SafeAuto extends LinearOpMode {

    public DcMotor r_motor;
    public DcMotor l_motor;

    public DcMotor climb;

    public static final int SPEED = 1;

    private void initMap() {
        r_motor = hardwareMap.dcMotor.get("Right Drive Motor");
        l_motor = hardwareMap.dcMotor.get("Left Drive Motor");
        climb = hardwareMap.dcMotor.get("Lifting Motor");
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initMap();
        waitForStart();
        new Encoder(this).startAuto(this,l_motor,r_motor,climb);
    }



}
