package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


//@TeleOp(name="DriveTest", group="Pushbot")

public class DriveTest extends LinearOpMode{

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
            //left motor
            float left_speed = -lefty();
            l_motor.setPower(left_speed);
            telemetry.addData("Left Track", left_speed);
            //right motor
            float right_speed = righty();
            r_motor.setPower(right_speed);
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






