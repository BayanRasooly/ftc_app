package org.firstinspires.ftc.teamcode.auto.java;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

    @Autonomous(name="MotorTest", group="Testing")
    //@Disabled
    public class MotorTest extends LinearOpMode {

        private ElapsedTime runtime = new ElapsedTime();
        private DcMotor leftDrive = null;
        private DcMotor rightDrive = null;

        @Override
        public void runOpMode() {
            telemetry.addData("Status", "Initialized");
            telemetry.update();

            leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
            rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

            leftDrive.setDirection(DcMotor.Direction.FORWARD);
            rightDrive.setDirection(DcMotor.Direction.REVERSE);

            waitForStart();
            runtime.reset();

            while (opModeIsActive()) {

                leftDrive.setPower(1);
                rightDrive.setPower(1);

                sleep(4000);

                leftDrive.setPower(0);
                rightDrive.setPower(0);


            }


        }
    }