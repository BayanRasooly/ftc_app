package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

//@Autonomous(name="Distance", group="Auto")
public class TestDistance extends LinearOpMode {
    HardwareMap hwMap = null;

    public UltrasonicSensor SensePlease;
    public double distance;

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        SensePlease = hardwareMap.ultrasonicSensor.get("Sense");
    }
    @Override
    public void runOpMode() throws InterruptedException{
        init(hardwareMap);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()) {
            distance = SensePlease.getUltrasonicLevel();
            telemetry.addData("Distance", distance);
            telemetry.update();
        }
    }
}
