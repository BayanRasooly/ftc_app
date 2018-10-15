package org.firstinspires.ftc.teamcode.carson;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.core.Encoder;
import org.firstinspires.ftc.teamcode.core.Robot;

import java.util.concurrent.TimeUnit;

@Autonomous(name="Carson Auto One", group="Carson")
public class CarsonAutoOne extends LinearOpMode{

    private Robot robot;
    private Encoder en;

    @Override
    public void runOpMode() {
        robot = new Robot(hardwareMap);
        en = new Encoder(robot,this);

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
        en.encoderDrive(1,10);
        en.align();

    }

}
