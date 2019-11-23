package org.firstinspires.ftc.teamcode.auto.java;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="EncoderTest", group="Testing")
@Disabled
public class EncoderTest extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Line","18");
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        //leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        //leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();
        runtime.reset();

        telemetry.addData("Line","38");
        telemetry.update();

        while (opModeIsActive()) {
            int distance = rightDrive.getTargetPosition() + 1120;

            telemetry.addData("Line","42");
            telemetry.update();
            //leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            telemetry.addData("Line","46");
            telemetry.update();
            //leftDrive.setTargetPosition(distance);

            rightDrive.setTargetPosition(distance);
            telemetry.addData("Line","50");
            telemetry.update();
            //leftDrive.setPower(1);
            rightDrive.setPower(1);
            telemetry.addData("Line","54");
            telemetry.update();
            //leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            telemetry.addData("Line","58");
            telemetry.update();
            boolean x = true;
            while (x){
                telemetry.addData("Current pos: ",rightDrive.getCurrentPosition());
                telemetry.addData("Target pos: ",rightDrive.getTargetPosition());
                telemetry.update();
            }

            telemetry.addData("Line","65");
            telemetry.update();
            //leftDrive.setPower(0);
            rightDrive.setPower(0);
            telemetry.addData("Line","69");
            telemetry.update();

        }
        telemetry.addData("Line","73");
        telemetry.update();
     }
  }