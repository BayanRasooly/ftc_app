package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="tele_actual", group="Pushbot")
//@Disabled
public class TeleOp_Actual extends LinearOpMode{


    private DcMotor left_motor;
    private DcMotor right_motor;
    private DcMotor lift;
    private Servo lservo;
    private Servo rservo;


    HardwareMap hwMap =  null;

    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;
        left_motor   = hwMap.dcMotor.get("lf_drive");
        right_motor  = hwMap.dcMotor.get("rb_drive");
        lift = hwMap.dcMotor.get("lift");
        lservo = hwMap.servo.get("lservo");
        rservo = hwMap.servo.get("rservo");
    }
    @Override
    public void runOpMode() throws InterruptedException{

        init(hardwareMap);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()) {

            float speed = 0.5f;

            if(gamepad1.right_trigger != 0)
            {
                left_motor.setPower(speed);
                right_motor.setPower(-speed);
            }
            else if(gamepad1.left_trigger != 0)
            {
                left_motor.setPower(-speed);
                right_motor.setPower(speed);
            }
            else if(gamepad1.dpad_up)
            {
                left_motor.setPower(speed);
                right_motor.setPower(speed);
            }
            else if(gamepad1.dpad_down)
            {
                left_motor.setPower(-speed);
                right_motor.setPower(-speed);
            } else {
                left_motor.setPower(0.0);
                right_motor.setPower(0.0);
            }
            idle();
        }
    }
    public void closeServo(double position) {
        lservo.setPosition(position);
        rservo.setPosition(1-position);
    }
    public void openServo(double position) {
        lservo.setPosition(position);
        rservo.setPosition(1-position);
    }


}






