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
            float left_speed = lefty();
            telemetry.addData("Left Track", left_speed);
            float right_speed =  righty();
            telemetry.addData("Right Track", right_speed);
            telemetry.update();
        }
    }
    public float lefty(){
        return floor(-gamepad1.left_stick_y - gamepad1.left_stick_x);
    }

    public float righty(){
        return floor(-gamepad1.left_stick_y + gamepad1.left_stick_x);
    }

    private float floor(float in){
        if(in >= 1)
            return 1;
        if(in <= -1)
            return -1;
        return in;
    }
}






