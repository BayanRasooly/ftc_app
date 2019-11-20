package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="TeleOp2019", group="Linear Opmode")
//@Override
public class TeleOp2019 extends LinearOpMode {


    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor rearLeftDrive = null;
    private DcMotor rearRightDrive = null;
    private DcMotor armMotor = null;
    private DcMotor sliderMotor = null;

    private Servo leftDropServo = null;
    private Servo rightDropServo = null;

    private Servo wristServo = null;
    private Servo grabServo = null;

    public void runOpMode() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {

        }
        telemetry.addData("Status", "Hi");
        telemetry.update();

        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        rearLeftDrive = hardwareMap.get(DcMotor.class, "rear_left_drive");
        rearRightDrive = hardwareMap.get(DcMotor.class, "rear_right_drive");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        sliderMotor = hardwareMap.get(DcMotor.class, "sliderMotor");

        leftDropServo = hardwareMap.get(Servo.class, "leftDropServo");
        rightDropServo = hardwareMap.get(Servo.class, "rightDropServo");

        wristServo = hardwareMap.get(Servo.class, "wristServo");
        grabServo = hardwareMap.get(Servo.class, "grabServo");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        rearLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        rearRightDrive.setDirection(DcMotor.Direction.REVERSE);

        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //armMotor.setDirection(DcMotor.Direction.REVERSE);
        //somewhat working maybe possibly

        //sliderMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //sliderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        /*init();
        grabServo.setPosition(0.5);
        wristServo.setPosition(0.5);
        leftDropServo.setPosition(0.5);
        rightDropServo.setPosition(0.5);
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {

        }
        telemetry.addData("Potato", "Bob");
        telemetry.update();
        sleep(1000);*/

        waitForStart();
        //runtime.reset();
        int clawStage = 0;
        double armPower = 0.4;
        telemetry.addData("Whales", "Clouds");
        telemetry.update();

        while (opModeIsActive()) {

            if (gamepad2.dpad_left) {
                wristServo.setPosition(0.5);
                wristServo.setPosition(0.5);
            } else if (gamepad2.dpad_right) {
                wristServo.setPosition(0);
                wristServo.setPosition(0);
            }
            telemetry.addData("If Statement", "Reached");
            telemetry.update();
            if (gamepad2.right_stick_y < -0.05 /*&& sliderMotor.getCurrentPosition() >= 0*/) {
                telemetry.addData("If Statement", "Functioning");
                telemetry.update();
                sliderMotor.setPower(0.3);
                telemetry.addData("Slider", "Forward");
            } else if (gamepad2.right_stick_y > 0.05 /*&& sliderMotor.getCurrentPosition() <= 374*/) {
                telemetry.addData("Slider", "Back");
                sliderMotor.setPower(-0.3);
            } else {
                sliderMotor.setPower(0);
            }
            telemetry.update();
//hi chris, Ishaan was messing with your code just to spite you. HEHEHEHEHEHEHEHE
            /*if (Math.abs(-gamepad2.left_stick_x) > 0.05) {
                sliderMotor.setPower(-0.5);
            } else if (sliderMotor.getCurrentPosition() >= 1997 || sliderMotor.getCurrentPosition() <= 3) {
                sliderMotor.setPower(0);
            }else {
                sliderMotor.setPower(0);*/

            if (gamepad1.a) {
                leftDropServo.setPosition(0.5);
                rightDropServo.setPosition(0.5);
            } else if (gamepad1.b) {
                leftDropServo.setPosition(1);
                rightDropServo.setPosition(0);
            }

            if (gamepad2.a) {
                grabServo.setPosition(1);
                armPower = 0.7;
            } else if (gamepad2.b) {
                grabServo.setPosition(0.5);
                armPower = 0.4;
            }


            /*if (gamepad2.dpad_up && !armMotor.isBusy() && clawStage < 7) {
                clawStage++;
                telemetry.addData("Clawstage: ", clawStage);
                int stagePosition = clawStage * 60;
                armMotor.setTargetPosition(stagePosition);
                armMotor.setPower(1);
                armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                telemetry.update();


            }

            if (gamepad2.dpad_down && !armMotor.isBusy() && clawStage > 0) {
                clawStage--;
                telemetry.addData("Clawstage: ", clawStage);
                int stagePosition = clawStage * 60;
                armMotor.setTargetPosition(stagePosition);
                armMotor.setPower(-0.3);
                armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                telemetry.update();

            *///"OG" Claw code


            /*if (gamepad2.left_stick_y < -0.05 && armMotor.getCurrentPosition() <= 170) {
                armMotor.setTargetPosition(170);
                armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armMotor.setPower(0.6);
            } else if (gamepad2.left_stick_y > 0.05 && armMotor.getCurrentPosition() >= 0) {
                armMotor.setTargetPosition(0);
                armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armMotor.setPower(0.3);
            } else {
                armMotor.setPower(0);
            }*/


            telemetry.addData("Left Joystick: ", gamepad2.left_stick_y);
            if (gamepad2.left_stick_y < -0.05 /*&& armMotor.getCurrentPosition() <= 170*/) {
                armMotor.setPower(armPower);
                telemetry.addData("Moving", "Up");
            } else if (gamepad2.left_stick_y > 0.05 /*&& armMotor.getCurrentPosition() >= 0*/) {
                armMotor.setPower(-0.1);
                telemetry.addData("Moving", "Down");
            } else {
                armMotor.setPower(0.01);
                telemetry.addData("Moving", "Static");
            }
            telemetry.update();

            if (!(gamepad1.left_bumper || gamepad1.right_bumper)) {
                double x = gamepad1.left_stick_x;
                double y = -gamepad1.left_stick_y;
                drive(x, y);
            } else {
                if (gamepad1.left_bumper) {
                    strafeLeft();
                } else {
                    strafeRight();
                }
            }
        }
    }


    public void drive(double turn, double drive){
        if(Math.abs(turn)>0.05||Math.abs(drive)>0.05) {
            double left = drive + turn;
            double right = drive - turn;

            double max = Math.max(Math.abs(left), Math.abs(right));
            if (max > 1.0) {
                left /= max;
                right /= max;
            }

            leftDrive.setPower(left);
            rightDrive.setPower(right);
            rearLeftDrive.setPower(left);
            rearRightDrive.setPower(right);
        }else{
            leftDrive.setPower(0);
            rightDrive.setPower(0);
            rearLeftDrive.setPower(0);
            rearRightDrive.setPower(0);
        }
    }

    public void strafeLeft(){
        leftDrive.setPower(1);
        rightDrive.setPower(1);
        rearLeftDrive.setPower(-1);
        rearRightDrive.setPower(-1);
    }

    public void strafeRight(){
        leftDrive.setPower(-1);
        rightDrive.setPower(-1);
        rearLeftDrive.setPower(1);
        rearRightDrive.setPower(1);
    }
}