package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.core.Robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="ControllerCheck", group="Pushbot")
@Disabled
public class ControllerCheck extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            buttons();
            sticks();
            trigs();
            bumpers();
        }
    }
    public void buttons(){
        if(gamepad2.a != false || gamepad2.b != false || gamepad2.x != false || gamepad2.y != false) {
            telemetry.addData("A", gamepad2.a);
            telemetry.addData("B", gamepad2.b);
            telemetry.addData("X", gamepad2.x);
            telemetry.addData("Y", gamepad2.y);
            telemetry.update();
        }
    }
    public void sticks(){
        if(gamepad1.left_stick_x != 0 || gamepad1.left_stick_y != 0) {
            telemetry.addData("Left_Y", gamepad1.left_stick_y);
            telemetry.addData("Left_X", gamepad1.left_stick_x);
            telemetry.update();
        }
    }
    public void trigs(){
        if(gamepad2.right_trigger != 0 || gamepad2.left_trigger != 0) {
            telemetry.addData("Trig_L", gamepad2.left_trigger);
            telemetry.addData("Trig_R", gamepad2.right_trigger);
            telemetry.update();
        }
    }
    public void bumpers(){
        if(gamepad2.left_bumper != false || gamepad2.right_bumper != false) {
            telemetry.addData("Bump_L", gamepad2.left_bumper);
            telemetry.addData("Bump_R", gamepad2.right_bumper);
            telemetry.update();
        }
    }
}