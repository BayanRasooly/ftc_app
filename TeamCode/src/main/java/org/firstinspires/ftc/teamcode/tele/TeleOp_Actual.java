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

    public DcMotor r_motor;
    public DcMotor l_motor;

    public DcMotor climb;

    public Servo lb_servo;// Left Bar Servo
    public Servo rb_servo;// Right Bar Servo

    private void initMap(){
        r_motor = hardwareMap.dcMotor.get("Right Back Motor");
        l_motor = hardwareMap.dcMotor.get("Left Motor");
        climb = hardwareMap.dcMotor.get("Climbing Motor");
        lb_servo = hardwareMap.servo.get("Left Bar Motor");
        rb_servo = hardwareMap.servo.get("Right Bar Motor");
    }
    @Override
    public void runOpMode() throws InterruptedException{
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()) {
            float left_speed = 0;
            float right_speed = 0;
            double speed = 0.5;

            /*if(gamepad1.right_trigger != 0)
            {
                l_motor.setPower(speed);
                r_motor.setPower(-speed);
            }
            else if(gamepad1.left_trigger != 0)
            {
                l_motor.setPower(-speed);
                r_motor.setPower(speed);
            }
            else if(gamepad1.dpad_up)
            {
                l_motor.setPower(speed);
                r_motor.setPower(speed);
            }
            else if(gamepad1.dpad_down)
            {
                l_motor.setPower(-speed);
                r_motor.setPower(-speed);
            } else {
                l_motor.setPower(0.0);
                r_motor.setPower(0.0);
            }
            idle();
            */
        }
    }
}






