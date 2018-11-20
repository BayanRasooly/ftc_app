package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.teamcode.auto.DistanceSensor;

public class Encoder {
    private LinearOpMode op;

    public Encoder(LinearOpMode op){
        this.op = op;
    }


    public void setLeftMotorPower(DcMotor motor,double speed){
        motor.setPower(-speed);
    }

    public void setRightMotorPower(DcMotor motor,double speed){
        motor.setPower(speed);
    }

    public void setBothMotorPower(DcMotor a,DcMotor b,double speed){
        setLeftMotorPower(a,speed);
        setRightMotorPower(b,speed);
    }

    public void encoderDrive(DcMotor leftMotor, DcMotor rightMotor, double speed, double left, double right, int time, Func<Boolean> func){
        if(!op.opModeIsActive()){
            return;
        }
        int newLeftTarget = leftMotor.getCurrentPosition() + (int)(left * Robot.counts_per_inch);
        int newRightTarget = rightMotor.getCurrentPosition() + (int)(right * Robot.counts_per_inch);


        leftMotor.setTargetPosition(newLeftTarget);
        rightMotor.setTargetPosition(newRightTarget);

        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        ElapsedTime timer = new ElapsedTime();

        leftMotor.setPower(-Math.abs(speed));
        rightMotor.setPower(Math.abs(speed));


        while (op.opModeIsActive() &&
                (timer.seconds() < time) &&
                (leftMotor.isBusy() && rightMotor.isBusy()) &&
                func.value()) {
            unsafeWait(100);
        }

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void encoderDrive(DcMotor leftMotor, DcMotor rightMotor,double speed, double left,double right, int time){
        encoderDrive(leftMotor,rightMotor,speed,left,right,time,new Func<Boolean>(){
            @Override
            public Boolean value() {
                return true;
            }
        });
    }

    public void encoderDrive(DcMotor leftMotor, DcMotor rightMotor,double speed, double left, double right){
        encoderDrive(leftMotor,rightMotor,speed,left,right,Integer.MAX_VALUE/2);
    }

    public void encoderDrive(DcMotor leftMotor, DcMotor rightMotor,double speed, double distance){
        encoderDrive(leftMotor,rightMotor,speed,distance,distance);
    }

    public void claim() {
        throw new AssertionError("what are you doing, write this method before calling");
    }

    private static final double bound = 7;
    public boolean leftInBounds(ColorSensor sensor){
        return sensor.red() > bound || sensor.blue() > bound;
    }
    public boolean rightInBounds(ColorSensor sensor){
        return sensor.red() > bound || sensor.blue() > bound;
    }

    public void align(DcMotor leftMotor, DcMotor rightMotor, DistanceSensor leftSensy, DistanceSensor rightSensy) {
        int bound = 1;
        if(Math.abs(alignment(leftSensy,rightSensy)) < bound){
            return;
        }

        while(Math.abs(alignment(leftSensy,rightSensy)) > bound) {
            if (alignment(leftSensy, rightSensy) > 0) {
                setLeftMotorPower(leftMotor, -0.25);
            } else {
                setRightMotorPower(rightMotor, 0.25);
            }
            unsafeWait(30);
            setBothMotorPower(rightMotor,leftMotor,0);
        }

        setBothMotorPower(rightMotor,leftMotor,0);
    }

    public double alignment(DistanceSensor leftSensy,DistanceSensor rightSensy){
        return leftSensy.getUltrasonic() - rightSensy.getUltrasonic();
    }

    public void lower(DcMotor climb,float speed){
        climb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ElapsedTime eTime = new ElapsedTime();
        while (eTime.time() <= 3) {
            climb.setPower(-speed);
        }
        climb.setPower(0);
    }

    /**
     * Will call Thread.sleep(ms), but will catch any exceptions thrown due to thread interruptions
     * @param ms how long to sleep
     */
    public void unsafeWait(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
