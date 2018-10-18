package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.core.Robot;


@Autonomous(name="Claim Zone Auto", group="Robot")

public class Pixy_Test extends LinearOpMode {
    Robot robot;
    double volts;
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        while(opModeIsActive()) {
            double volts = robot.ana.getVoltage();
            telemetry.addData("Volts", volts);
        }
    }
}