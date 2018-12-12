package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.Encoder;

@Autonomous(name="Claim", group="Robot")
public class Claim extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        waitForStart();
        new Encoder(this).claim(hardwareMap.dcMotor.get("Intake Motor"));
    }
}
