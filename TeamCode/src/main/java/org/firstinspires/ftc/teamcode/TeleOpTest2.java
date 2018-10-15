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
    
    HardwareMap hwMap =  null;
    //test
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
    public boolean moveForwards(float speed){
        try{
            lf_motor.setPower(speed);
            lb_motor.setPower(speed);
            rf_motor.setPower(speed);
            rb_motor.setPower(speed);
        }
        catch(Throwable t){
            return false;
        }
        return true;
    }
    public boolean moveBackwards(float speed){
        try{
            lf_motor.setPower(-speed);
            lb_motor.setPower(-speed);
            rf_motor.setPower(-speed);
            rb_motor.setPower(-speed);
        }
        catch(Throwable t){
            return false;
        }
        return true:
    }
    public boolean turnCounterclockwise(float speed){
        try{
            lf_motor.setPower(-speed);
            lb_motor.setPower(speed);
            rf_motor.setPower(-speed);
            rb_motor.setPower(speed);
        }
        catch(Throwable t){
            return false;
        }
        return true;
    }
    public boolean turnClockwise(float speed){
        try{
            lf_motor.setPower(speed);
            lb_motor.setPower(-speed);
            rf_motor.setPower(speed);
            rb_motor.setPower(-speed);
        }
        catch(Throwable t){
            return false;
        }
        return true;
    }
    public boolean pause(){
        lf_motor.setPower(0.0);
        lb_motor.setPower(0.0);
        rf_motor.setPower(0.0);
        rb_motor.setPower(0.0);
        return true;
    }
    @Override
    public void runOpMode() throws InterruptedException{

        init(hardwareMap);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()) {
            float speed = (gamepad1.y)? 1f : 0.5f;

            if(gamepad1.right_trigger != 0)
            {
                moveForwards(speed);
            }
            else if(gamepad1.left_trigger != 0)
            {
                moveBackwards(speed);
            }
            else if(gamepad1.dpad_left)
            {
                turnCounterclockwise(speed);
            }
            else if(gamepad1.dpad_right)
            {
                turnClockwise(speed);
            }
            else {
                pause();
            }
            idle();
        }
    }
}