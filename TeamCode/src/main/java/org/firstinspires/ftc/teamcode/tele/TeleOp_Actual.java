package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.core.Robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="tele_actual", group="Pushbot")

public class TeleOp_Actual extends LinearOpMode{

    Robot robot;

    @Override
    public void runOpMode() throws InterruptedException{
        robot = new Robot(hardwareMap);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()) {

            double speed = 0.5;

            if(gamepad1.right_trigger != 0)
            {
                robot.l_motor.setPower(speed);
                robot.r_motor.setPower(-speed);
            }
            else if(gamepad1.left_trigger != 0)
            {
                robot.l_motor.setPower(-speed);
                robot.r_motor.setPower(speed);
            }
            else if(gamepad1.dpad_up)
            {
                robot.l_motor.setPower(speed);
                robot.r_motor.setPower(speed);
            }
            else if(gamepad1.dpad_down)
            {
                robot.l_motor.setPower(-speed);
                robot.r_motor.setPower(-speed);
            } else {
                robot.l_motor.setPower(0.0);
                robot.r_motor.setPower(0.0);
            }
            idle();
        }
    }
}






