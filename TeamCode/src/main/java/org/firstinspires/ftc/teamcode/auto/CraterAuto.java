package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.teamcode.core.Encoder;
import org.firstinspires.ftc.teamcode.core.Robot;

@Autonomous(name="Crater Auto", group="Robot")
public class CraterAuto extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    Robot robot;
    Encoder en;

    public static final int SPEED = 1;


    @Override
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
        }//COPIED

        boolean[] minerals = MineralReader.read(robot);
        if(!minerals[1]){
            int mult = minerals[0]?1:-1;
            en.encoderDrive(SPEED, mult * -5, mult * 5);
        }
        en.encoderDrive(SPEED,100);
        en.encoderDrive(SPEED,-100);
        //TURN 45*TODO
        en.align();
        en.encoderDrive(SPEED,5);//drive to wall
        en.encoderDrive(SPEED,-5,5);//turn to claim
        en.align();//may be needed, may waste time
        en.encoderDrive(SPEED, 100, 100, 9999, new Func<Boolean>() {
            @Override
            public Boolean value() {
                return en.rightInBounds() || en.leftInBounds();
            }
        });
        en.claim();
        en.encoderDrive(SPEED,288);
    }
}
