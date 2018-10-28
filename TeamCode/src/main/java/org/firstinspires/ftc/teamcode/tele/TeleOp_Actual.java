package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.core.Robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="tele_actual", group="Pushbot")

public class TeleOp_Actual extends LinearOpMode{
    HardwareMap hwMap = null;

    public DcMotor r_motor;
    public DcMotor l_motor;

    public DcMotor lift;

    public Servo lbar_servo;// Left Bar Servo
    public Servo rbar_servo;// Right Bar Servo

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        r_motor = hardwareMap.dcMotor.get("R_Drive");
        l_motor = hardwareMap.dcMotor.get("L_Drive");
        lift = hardwareMap.dcMotor.get("Climbing Motor");
        lbar_servo = hardwareMap.servo.get("Left Bar Motor");
        rbar_servo = hardwareMap.servo.get("Right Bar Motor");
    }
    @Override
    public void runOpMode() throws InterruptedException{
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()) {
            //left motor
            float left_speed = lefty();
            l_motor.setPower(left_speed);
            telemetry.addData("Left Track", left_speed);
            telemetry.update();
            //right motor
            float right_speed =  righty();
            r_motor.setPower(right_speed);
            telemetry.addData("Right Track", right_speed);
            telemetry.update();
            //Lift Motor
            elevator();

        }
    }

    public void elevator() {
        if (gamepad2.left_trigger > 0) {
            lift.setPower(-gamepad2.left_trigger);
        }else{
            lift.setPower(gamepad2.right_trigger);
        }
    }
    public float lefty(){
        if ((-gamepad1.left_stick_y + gamepad1.left_stick_x) >= 1){
            return 1;
        }else if((-gamepad1.left_stick_y + gamepad1.left_stick_x) <= -1){
            return -1;
        }else{
            return (-gamepad1.left_stick_y + gamepad1.left_stick_x);
        }
    }
    public float righty(){
        if ((-gamepad1.left_stick_y - gamepad1.left_stick_x) >= 1){
            return 1;
        }else if((-gamepad1.left_stick_y - gamepad1.left_stick_x) <= -1){
            return -1;
        }else{
            return (-gamepad1.left_stick_y - gamepad1.left_stick_x);
        }
    }
}






