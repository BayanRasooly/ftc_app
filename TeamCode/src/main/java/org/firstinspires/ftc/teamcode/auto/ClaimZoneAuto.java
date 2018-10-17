package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.core.Encoder;
import org.firstinspires.ftc.teamcode.core.Robot;

@Autonomous(name="Claim Zone Auto", group="Robot")

public class ClaimZoneAuto extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();

    Robot robot;
    Encoder en;
    
    public static final int SPEED = 1;
    

    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap);
        en = new Encoder(robot,this);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        en.lower(SPEED);
        robot.left_sensey.enableLed(true);
        robot.right_sensey.enableLed(true);
        if(!en.leftInBounds() || !en.rightInBounds()){
            if(!en.leftInBounds()){
                en.setLeftMotorPower(SPEED);
                while(!en.leftInBounds()){
                    en.wait(10);
                }
                en.setLeftMotorPower(0);
            }
            en.setRightMotorPower(SPEED);
            while(!en.rightInBounds()){
                en.wait(10);
            }
            en.setRightMotorPower(0);
        }

        boolean[] minerals = MineralReader.read(robot);

        robot.lb_servo.setPosition(0);
        robot.rb_servo.setPosition(0);

        if (!minerals[1]) {
            en.encoderDrive(SPEED, minerals[0] ? -5 : 5, minerals[2] ? -5 : 5);
            en.encoderDrive(SPEED, 5);
            en.align();
            //drive forward
            en.encoderDrive(SPEED,5);

            //turn
            int mult = minerals[0]?-SPEED:SPEED;//HARD
            en.encoderDrive(SPEED,mult*-5,mult*5);//HARD

            //drive to claim zone
            en.setBothMotorPower(SPEED);
            while(!en.leftInBounds()){//test for the left color sensor, shouldn't matter
                en.unsafeWait(10);
            }
            en.setBothMotorPower(0);
            en.claim();
            en.align();
        } else {
            en.encoderDrive(SPEED, 5);
            en.claim();
            en.encoderDrive(SPEED,1,-1);
            en.align();
        }
        en.encoderDrive(SPEED,-288);
    }






}
