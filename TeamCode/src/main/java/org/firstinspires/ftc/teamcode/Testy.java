package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name = "testy", group = "test")
//@TeleOp (name = "testy", group = "test")

public class Testy extends LinearOpMode {

    public DcMotor motorTest;

    public void runOpMode() throws InterruptedException {

        waitForStart();
        motorTest = hardwareMap.dcMotor.get("motorTest");
        motorTest.setPower(.5);
        motorTest.setPower(0);


        while(opModeIsActive()){
            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
}
