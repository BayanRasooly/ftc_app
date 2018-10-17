package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

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
        
    }
}
