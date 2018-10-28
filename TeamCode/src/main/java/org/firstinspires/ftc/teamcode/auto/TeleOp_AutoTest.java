package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


@Autonomous(name="tele_gamepadtest", group="Pushbot")

public class TeleOp_AutoTest extends LinearOpMode{

    HardwareMap hwMap = null;

    public DcMotor r_motor;
    public DcMotor l_motor;

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        r_motor = hardwareMap.dcMotor.get("R_Drive");
        l_motor = hardwareMap.dcMotor.get("L_Drive");
    }
    @Override
    public void runOpMode() throws InterruptedException{
        init(hardwareMap);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()) {
            r_motor.setPower(1);
            l_motor.setPower(0.5);
        }
    }
}






