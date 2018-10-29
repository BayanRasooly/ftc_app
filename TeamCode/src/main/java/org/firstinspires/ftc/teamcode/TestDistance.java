package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

@Autonomous(name="Distance", group="Auto")
public class TestDistance extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        OpticalDistanceSensor dis = hardwareMap.opticalDistanceSensor.get("distance");
    }
}
