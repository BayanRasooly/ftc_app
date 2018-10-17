package org.firstinspires.ftc.teamcode.carson;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.core.Encoder;
import org.firstinspires.ftc.teamcode.core.Robot;

@Autonomous(name="Carson Tele One", group="Carson")
public class CarsonTeleOne extends LinearOpMode {
    Robot robot;
    Encoder en;

    @Override
    public void runOpMode() {
        robot = new Robot(hardwareMap);
        en = new Encoder(robot,this);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()) {
            float left = 0;
            float right = 0;
            left+=gamepad1.right_stick_y;
            right+=gamepad1.right_stick_y;

            left+=gamepad1.right_stick_x;
            right-=gamepad1.right_stick_x;

            en.setLeftMotorPower(left * (gamepad1.y?2:1));
            en.setRightMotorPower(right * (gamepad1.y?2:1));
            idle();
        }
    }

}
