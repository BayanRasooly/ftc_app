package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "Testing", group = "Tests")
public class TestingTele extends LinearOpMode
{
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor motorRearLeft;
    private DcMotor motorRearRight;

    @Override
    public void runOpMode()
    {
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorRearLeft = hardwareMap.dcMotor.get("motorRearLeft");
        motorRearRight = hardwareMap.dcMotor.get("motorRearRight");

        waitForStart();

        while (opModeIsActive())

            motorLeft.setPower(1);
            motorRight.setPower(1);
            motorRearLeft.setPower(1);
            motorRearRight.setPower(1);
    }
}
