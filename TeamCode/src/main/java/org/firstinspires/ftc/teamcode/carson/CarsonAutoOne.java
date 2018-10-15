package org.firstinspires.ftc.teamcode.carson;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.concurrent.TimeUnit;

import static org.firstinspires.ftc.teamcode.carson.StaticEncoder.*;

@Autonomous(name="Carson Auto One", group="Carson")
public class CarsonAutoOne extends LinearOpMode{

    private Robot robot;

    @Override
    public void runOpMode() {
        robot = new Robot(hardwareMap);
        setOp(this);
        setRobot(robot);

        robot.climb.setPower(10);
        ElapsedTime timer = new ElapsedTime();
        while(timer.time(TimeUnit.SECONDS) < 2){
            try {
                wait(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        robot.climb.setPower(0);
        encoderDrive(1,10);
        align();

    }

    private void align() {
        int bound = 1;
        if(Math.abs(aligment()) < bound){
            return;
        }
        if(aligment() > 0){
           setLeftMotorPower(1);
        }else if(aligment() < 0){
            setRightMotorPower(1);
        }
        while(Math.abs(aligment()) > bound){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        setBothMotorPower(0);
    }

    private double aligment(){
        return robot.left_distance.getDistance(DistanceUnit.INCH) - robot.right_distance.getDistance(DistanceUnit.INCH);
    }

}
