package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Encoder {
    private Robot robot;
    private LinearOpMode op;

    public Encoder(Robot robot,LinearOpMode op){
        this.robot = robot;
        this.op = op;
    }


    public void setLeftMotorPower(double speed){
        robot.l_motor.setPower(speed);
    }

    public void setRightMotorPower(double speed){
        robot.r_motor.setPower(speed);
    }

    public void setBothMotorPower(double speed){
        setLeftMotorPower(speed);
        setRightMotorPower(speed);
    }

    public void encoderDrive(double speed, double left, double right, int time, Func<Boolean> func){
        if(!op.opModeIsActive()){
            return;
        }
        int newLeftTarget = robot.l_motor.getCurrentPosition() + (int)(left * Robot.counts_per_inch);
        int newRightTarget = robot.r_motor.getCurrentPosition() + (int)(right * Robot.counts_per_inch);


        robot.l_motor.setTargetPosition(newLeftTarget);
        robot.r_motor.setTargetPosition(newRightTarget);

        robot.l_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.r_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        ElapsedTime timer = new ElapsedTime();

        robot.l_motor.setPower(Math.abs(speed));
        robot.l_motor.setPower(Math.abs(speed));


        while (op.opModeIsActive() &&
                (timer.seconds() < time) &&
                (robot.l_motor.isBusy() && robot.r_motor.isBusy()) &&
                func.value()) {
            unsafeWait(100);
        }

        robot.l_motor.setPower(0);
        robot.r_motor.setPower(0);

        robot.l_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.r_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void encoderDrive(double speed, double left,double right, int time){
        encoderDrive(speed,left,right,time,new Func<Boolean>(){
            @Override
            public Boolean value() {
                return true;
            }
        });
    }

    public void encoderDrive(double speed, double left, double right){
        encoderDrive(speed,left,right,Integer.MAX_VALUE/2);
    }

    public void encoderDrive(double speed, double distance){
        encoderDrive(speed,distance,distance);
    }

    public void claim() {
        throw new AssertionError("what are you doing, write this method before calling");
    }

    private static final double bound = 3;
    public boolean leftInBounds(){
        return Math.abs(robot.left_sensey.red() - /*red value*/1) < bound;
    }
    public boolean rightInBounds(){
        return Math.abs(robot.right_sensey.red() - /*red value*/1)<bound;
    }

    public void align() {
        int bound = 1;
        if(Math.abs(aligment()) < bound){
            return;
        }
        if(aligment() > 0){
            setLeftMotorPower(1);
        }else{
            setRightMotorPower(1);
        }

        while(Math.abs(aligment()) > bound)
            unsafeWait(10);

        setBothMotorPower(0);
    }

    public double aligment(){
        return robot.left_distance.getDistance(DistanceUnit.INCH) - robot.right_distance.getDistance(DistanceUnit.INCH);
    }

    public void lower(float speed){
        ElapsedTime eTime = new ElapsedTime();
        while (eTime.time() <= 3) {
            robot.climb.setPower(-speed);
        }
        robot.climb.setPower(0);
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
