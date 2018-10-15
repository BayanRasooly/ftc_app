package org.firstinspires.ftc.teamcode.carson;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Func;

import static org.firstinspires.ftc.teamcode.carson.StaticEncoder.*;

@Autonomous(name="Carson Auto One", group="Carson")
public class CarsonAutoOne extends LinearOpMode{

    private Robot robot;

    @Override
    public void runOpMode() {
        robot = new Robot(hardwareMap);
        setOp(this);
        setRobot(robot);


        //uses methods
        setLeftMotorPower(1);
        setRightMotorPower(1);
        setBothMotorPower(0);

        encoderDrive(1, 10, 10, 5, new Func<Boolean>() {
            @Override
            public Boolean value() {
                return robot.left_sensey.red() > 100;
            }
        });//drive 10 inches forward, or 5 seconds, or until red values are over 100

        encoderDrive(1,10,10,5);//drive 10 in forward, or 5 seconds

        encoderDrive(1,10,10);//drive 10 in forward, no timeout

        encoderDrive(1,10);//drive 10in forward, no timeout, both motors are the same
    }

}
