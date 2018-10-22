package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.core.Encoder;
import org.firstinspires.ftc.teamcode.core.Robot;

import static android.R.attr.left;
import static android.R.attr.right;
import static android.R.transition.move;

//make sure to change!
@TeleOp(name="tele2", group="test")
//@Disabled
public class TeleOpTest2 extends LinearOpMode{
    public DcMotor r_motor;
    public DcMotor l_motor;

    public DcMotor climb;
    //do servos later
    public Servo lb_servo;// Left Bar Servo
    public Servo rb_servo;// Right Bar Servo

    public ColorSensor left_sensey;
    public ColorSensor right_sensey;
    public DistanceSensor left_distance;
    public  DistanceSensor right_distance;

    public boolean moveForwards(float speed){
        try{
            r_motor.setPower(speed);
            l_motor.setPower(speed);
        }
        catch(RuntimeException t){
            t.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean moveBackwards(float speed){
        return moveForwards(-speed);
    }
    public boolean turnCounterclockwise(float speed){
        try{
            r_motor.setPower(speed);
            l_motor.setPower(-speed);
        }
        catch(RuntimeException t){
            t.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean turnClockwise(float speed){
        return turnCounterclockwise(-speed);
    }
    public boolean quickTurn(float degree){
        throw new AssertionError("Hasn't been written yet - can't be called");
    }

    public boolean pause(){
        try {
            r_motor.setPower(0);
            l_motor.setPower(0);
        }catch (RuntimeException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Deprecated
    public boolean align(){
//        double margin = 0.50;
//        while(rDistanceSensor.getDistance(DistanceUnit.INCH))
        try {
//            en.align();//margin is set within the method
        }catch(RuntimeException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void initMap(){
        r_motor = hardwareMap.dcMotor.get("Right Back Motor");
        l_motor = hardwareMap.dcMotor.get("Left Motor");
        climb = hardwareMap.dcMotor.get("Climbing Motor");
        lb_servo = hardwareMap.servo.get("Left Bar Motor");
        rb_servo = hardwareMap.servo.get("Right Bar Motor");
        left_sensey = hardwareMap.colorSensor.get("Left Color Sensor");
        right_sensey = hardwareMap.colorSensor.get("Right Color Sensor");
        left_distance = (DistanceSensor) hardwareMap.opticalDistanceSensor.get("Left Distance Sensor");
        right_distance = (DistanceSensor) hardwareMap.opticalDistanceSensor.get("Right Distance Sensor");
    }


    @Override
    public void runOpMode() {
        initMap();
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()) {
            float speed = (gamepad1.y)? 1f : 0.5f;//fast mode?
            if(gamepad1.right_trigger != 0)
            {
                moveForwards(speed);
            }
            else if(gamepad1.left_trigger != 0)
            {
                moveBackwards(speed);
            }
            else if(gamepad1.left_trigger != 0 && gamepad1.right_trigger != 0){

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