package org.firstinspires.ftc.teamcode.carson;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Func;

public class CarsonAutoOne extends LinearOpMode{

    private Robot robot;

    @Override
    public void runOpMode() {
        robot = new Robot(hardwareMap);
    }














    private void setLeftMotorPower(double speed){
        robot.left_motor.setPower(speed);
    }

    private void setRightMotorPower(double speed){
        robot.right_motor.setPower(speed);
    }

    private void setBothMotorPower(double speed){
        setLeftMotorPower(speed);
        setRightMotorPower(speed);
    }

    private void encoderDrive(double speed, double left, double right, int time, Func<Boolean> func){
        if(!opModeIsActive()){
            return;
        }
        int newLeftTarget = robot.left_motor.getCurrentPosition() + (int)(left * Robot.counts_per_inch);
        int newRightTarget = robot.right_motor.getCurrentPosition() + (int)(right * Robot.counts_per_inch);


        robot.left_motor.setTargetPosition(newLeftTarget);
        robot.right_motor.setTargetPosition(newRightTarget);

        robot.left_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.right_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        ElapsedTime timer = new ElapsedTime();

        robot.left_motor.setPower(Math.abs(speed));
        robot.left_motor.setPower(Math.abs(speed));


        while (opModeIsActive() &&
                (timer.seconds() < time) &&
                (robot.left_motor.isBusy() && robot.right_motor.isBusy()) && 
                func.value()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        robot.left_motor.setPower(0);
        robot.right_motor.setPower(0);

        robot.left_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.right_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
    }

    private void encoderDrive(double speed, double left,double right, int time){
        encoderDrive(speed,left,right,time,new Func<Boolean>(){
            @Override
            public Boolean value() {
                return true;
            }
        });
    }

    private void encoderDrive(double speed, double left, double right){
        encoderDrive(speed,left,right,Integer.MAX_VALUE/2);
    }

    private void encoderDrive(double speed, double distance){
        encoderDrive(speed,distance,distance);
    }

}
