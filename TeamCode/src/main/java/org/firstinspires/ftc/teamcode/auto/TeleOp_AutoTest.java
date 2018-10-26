package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name="tele_gamepadtest", group="Pushbot")

public class TeleOp_AutoTest extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException{
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()) {
            float left_speed = lefty();
            telemetry.addData("Left Track", left_speed);
            float right_speed =  righty();
            telemetry.addData("Right Track", right_speed);
            telemetry.update();
        }
    }
    public float lefty(){
        if ((-gamepad1.left_stick_y - gamepad1.left_stick_x) >= 1){
            return 1;
        }else if((-gamepad1.left_stick_y - gamepad1.left_stick_x) <= -1){
            return -1;
        }else{
            return (-gamepad1.left_stick_y - gamepad1.left_stick_x);
        }
    }
    public float righty(){
        if ((-gamepad1.left_stick_y + gamepad1.left_stick_x) >= 1){
            return 1;
        }else if((-gamepad1.left_stick_y + gamepad1.left_stick_x) <= -1){
            return -1;
        }else{
            return (-gamepad1.left_stick_y + gamepad1.left_stick_x);
        }
    }
}






