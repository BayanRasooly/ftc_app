package org.firstinspires.ftc.teamcode.auto.java;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto2019", group="Linear Opmode")
public class Auto2019 extends LinearOpMode {

        // Declare OpMode members.
        private ElapsedTime runtime = new ElapsedTime();
        private DcMotor leftDrive = null;
        private DcMotor rightDrive = null;
        private DcMotor rearLeftDrive = null;
        private DcMotor rearRightDrive = null;

        //@Override
        public void runOpMode() {
            telemetry.addData("Status", "Initialized");
            telemetry.update();

            leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
            rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
            rearLeftDrive  = hardwareMap.get(DcMotor.class, "rear_left_drive");
            rearRightDrive = hardwareMap.get(DcMotor.class, "rear_right_drive");

            leftDrive.setDirection(DcMotor.Direction.FORWARD);
            rightDrive.setDirection(DcMotor.Direction.REVERSE);
            rearLeftDrive.setDirection(DcMotor.Direction.FORWARD);
            rearRightDrive.setDirection(DcMotor.Direction.REVERSE);


            waitForStart();
            runtime.reset();

            // run until the end of the match (driver presses STOP)
            while (opModeIsActive()) {


            }
        }

        public void forwardDrive(double power){
            leftDrive.setPower(power);
            rightDrive.setPower(power);
            rearLeftDrive.setPower(power);
            rearRightDrive.setPower(power);
        }

        public void rightTankTurn (double power){
            leftDrive.setPower(power);
            rightDrive.setPower(-power);
            rearLeftDrive.setPower(power);
            rearRightDrive.setPower(-power);
        }

        public void LeftTankTurn(double power){
            leftDrive.setPower(-power);
            rightDrive.setPower(power);
            rearLeftDrive.setPower(-power);
            rearRightDrive.setPower(power);
        }

        public void rearDrive(double power){
            leftDrive.setPower(-power);
            rightDrive.setPower(-power);
            rearLeftDrive.setPower(-power);
            rearRightDrive.setPower(-power);
        }
    }

