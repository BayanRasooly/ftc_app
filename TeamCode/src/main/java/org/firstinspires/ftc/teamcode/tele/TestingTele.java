package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "Testing", group = "Tests")
public class TestingTele extends LinearOpMode
{
    private DcMotor motorLeft;

    @Override
    public void runOpMode()
    {
        motorLeft = hardwareMap.dcMotor.get("motorLeft");

        waitForStart();

        while (opModeIsActive())

            motorLeft.setPower(1);
    }
}
