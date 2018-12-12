package org.firstinspires.ftc.teamcode.core;

import android.util.Pair;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.teamcode.auto.DistanceSensor;
import org.firstinspires.ftc.teamcode.auto.MineralReader;

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
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!op.opModeIsActive())
                throw new RuntimeException("halp");
        }
        int newLeftTarget = leftMotor.getCurrentPosition() - (int)(left * Robot.counts_per_inch * 0.97);
        int newRightTarget = rightMotor.getCurrentPosition() + (int)(right * Robot.counts_per_inch);


        leftMotor.setTargetPosition(newLeftTarget);
        rightMotor.setTargetPosition(newRightTarget);

        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        ElapsedTime timer = new ElapsedTime();

        leftMotor.setPower(Math.abs(speed));
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

    public void claim(DcMotor claim) {
        claim.setPower(-1);
        unsafeWait(1000);
        claim.setPower(0);
    }

    private static final double bound = 7;
    public boolean leftInBounds(ColorSensor sensor){
        return sensor.red() > bound || sensor.blue() > bound;
    }
    public boolean rightInBounds(ColorSensor sensor){
        return sensor.red() > bound || sensor.blue() > bound;
    }

    public void align(DcMotor leftMotor, DcMotor rightMotor, DistanceSensor leftSensy, DistanceSensor rightSensy) {
        int bound = 7;
        int smallerBound = 2;
        if(Math.abs(alignment(leftSensy,rightSensy)) < bound){
            return;
        }

        while(Math.abs(alignment(leftSensy,rightSensy)) > bound) {
            double speed = Math.abs(alignment(leftSensy,rightSensy)) < smallerBound ? 0.25 : 0.5;
            if (alignment(leftSensy, rightSensy) > 0) {
                setLeftMotorPower(leftMotor, speed);
                setRightMotorPower(rightMotor,-speed);
            } else {
                setLeftMotorPower(leftMotor,-speed);
                setRightMotorPower(rightMotor, speed);
            }

//            unsafeWait(30);
//            setBothMotorPower(rightMotor,leftMotor,0);
        }

        setBothMotorPower(rightMotor,leftMotor,0);
    }

    public double alignment(DistanceSensor leftSensy,DistanceSensor rightSensy){
        return leftSensy.getUltrasonic() - rightSensy.getUltrasonic();
    }

    private static boolean CLIMB = false;
    public void lower(DcMotor climb,float speed){
        if(!CLIMB)return;
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



    public Pair<Integer,boolean[]> startAuto(DcMotor l_motor, DcMotor r_motor, DcMotor climb){
//        lower(climb,1);
        boolean[] minerals = new MineralReader(op.hardwareMap).read().clone();
        op.telemetry.addData("Guess", "[" + minerals[0] + "," + minerals[1] + "," + minerals[2] + "]");
//        encoderDrive(l_motor,r_motor,1,0.1,2);
        if(minerals[0] || minerals[2]) {
            double left = minerals[0] ? -4 : 4 ;
            double right = minerals[0] ? 4 : -4;
            op.telemetry.addData("motors",left + "," + right);
            encoderDrive(l_motor, r_motor, 1, left, right);
        }
        int dist;//move forward more if sideways
        if(minerals[0]) dist = 30;
        else if(minerals[1]) dist = 27;
        else dist = 30;
        op.telemetry.addData("dist",dist);
        op.telemetry.update();
        encoderDrive(l_motor, r_motor, 1, dist);
        return new Pair<>(dist, minerals);
    }

}
