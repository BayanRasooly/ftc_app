package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import static android.R.attr.left;
import static android.R.attr.right;
import static android.R.transition.move;

//make sure to change!
@TeleOp(name="tele2", group="test")
//@Disabled
public class TeleOpTest2 extends LinearOpMode{


    private DcMotor lf_motor;
    private DcMotor lb_motor;
    private DcMotor rf_motor;
    private DcMotor rb_motor;
    private DcMotor lift;
    private Servo lservo;
    private Servo rservo;


    HardwareMap hwMap =  null;

    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;
        lf_motor   = hwMap.dcMotor.get("lf_drive");
        lb_motor  = hwMap.dcMotor.get("lb_drive");
        rf_motor   = hwMap.dcMotor.get("rf_drive");
        rb_motor  = hwMap.dcMotor.get("rb_drive");
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

            //float straight;
            //float sideways;
            float orientation;

            orientation = gamepad1.right_stick_y;

            //lift.setPower(0.0);

            //driveStraight(straight);
            //driveSideways(sideways);

            //turn(orientation);


            if(gamepad2.a)
                lift.setPower(1);
            else if(gamepad2.b)
                lift.setPower(-1);
            else
                lift.setPower(0);



            if(gamepad2.y)
                openServo(0);
            if (gamepad2.x)
                closeServo(.9);

            //float speed = (gamepad1.left_trigger != 0)? SPEED_MULTIPLIER : TURBO_MULTIPLIER;

            float speed = (gamepad1.y)? 1f : 0.5f;

            if(gamepad1.right_trigger != 0)
            {
                lf_motor.setPower(-speed);
                lb_motor.setPower(-speed);
                rf_motor.setPower(-speed);
                rb_motor.setPower(-speed);
            }
            else if(gamepad1.left_trigger != 0)
            {
                lf_motor.setPower(speed);
                lb_motor.setPower(speed);
                rf_motor.setPower(speed);
                rb_motor.setPower(speed);
            }
            else if(gamepad1.dpad_up)
            {
                lf_motor.setPower(-speed);
                lb_motor.setPower(-speed);
                rf_motor.setPower(speed);
                rb_motor.setPower(speed);
            }
            else if(gamepad1.dpad_down)
            {
                lf_motor.setPower(speed);
                lb_motor.setPower(speed);
                rf_motor.setPower(-speed);
                rb_motor.setPower(-speed);
            }
            else if(gamepad1.dpad_left)
            {
                lf_motor.setPower(speed);
                lb_motor.setPower(-speed);
                rf_motor.setPower(speed);
                rb_motor.setPower(-speed);
            }
            else if(gamepad1.dpad_right)
            {
                lf_motor.setPower(-speed);
                lb_motor.setPower(speed);
                rf_motor.setPower(-speed);
                rb_motor.setPower(speed);
            } else {
                lf_motor.setPower(0.0);
                lb_motor.setPower(0.0);
                rf_motor.setPower(0.0);
                rb_motor.setPower(0.0);
            }
            idle();
        }
        // Gwendyl Harter - have we tried putting an idle() at the end of
        // the while op-mode loop?  Documentation says that this is important
        // for giving the machinery time to catch up with the code.
        // May explain the jittery-ness
    }
    // public void driveStraight(float power) {
    // lf_motor.setPower(-power);
    // lb_motor.setPower(-power);
    // rf_motor.setPower(-power);
    // rb_motor.setPower(-power);
    // }
    //public void driveSideways(float power){
    //  lf_motor.setPower(-power);
    //  lb_motor.setPower(power);
    //  rf_motor.setPower(-power);
    //  rb_motor.setPower(power);
    //  }
    public void closeServo(double position) {
        lservo.setPosition(position);
        rservo.setPosition(1-position);
    }
    public void openServo(double position) {
        lservo.setPosition(position);
        rservo.setPosition(1-position);
    }


}






