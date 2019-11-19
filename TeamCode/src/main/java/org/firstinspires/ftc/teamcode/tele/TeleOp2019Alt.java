package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="TeleOp2019Alt", group="Linear Opmode")

public class TeleOp2019Alt extends LinearOpMode {
    //@Override

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
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        rearLeftDrive  = hardwareMap.get(DcMotor.class, "rear_left_drive");
        rearRightDrive = hardwareMap.get(DcMotor.class, "rear_right_drive");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        //sliderMotor = hardwareMap.get(DcMotor.class, "sliderMotor");

        leftDropServo = hardwareMap.get(Servo.class, "leftDropServo");
        rightDropServo = hardwareMap.get(Servo.class, "rightDropServo");

        wristServo = hardwareMap.get(Servo.class, "wristServo");
        grabServo = hardwareMap.get(Servo.class, "grabServo");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        rearLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        rearRightDrive.setDirection(DcMotor.Direction.REVERSE);

        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //sliderMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //sliderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        init();
        grabServo.setPosition(0);
        wristServo.setPosition(0);
        leftDropServo.setPosition(0);
        rightDropServo.setPosition(0);

        waitForStart();
        runtime.reset();
        //int clawStage = 0;

        while (opModeIsActive()) {

            /*if (gamepad2.dpad_left) {
                wristServo.setPosition(-1);
                wristServo.setPosition(1);
            }

            if(gamepad1.a) {
                leftDropServo.setPosition(0.5);
                rightDropServo.setPosition(-0.5);
            }
            if(gamepad1.b) {
                leftDropServo.setPosition(0);
                rightDropServo.setPosition(0);
            }

            if (gamepad2.a) {
                grabServo.setPosition(1);
            }

            if (gamepad2.b) {
                grabServo.setPosition(0);
            }

            if (gamepad2.left_stick_x > 0.1){
                wristServo.setPosition(gamepad2.left_stick_x);
            }

            if (gamepad2.dpad_up && !armMotor.isBusy() && clawStage<7){
                clawStage++;
                int stagePosition = clawStage*60;
                armMotor.setTargetPosition(stagePosition);
                armMotor.setPower(.6);
                armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }

            if (gamepad2.dpad_down && !armMotor.isBusy() && clawStage>0){
                clawStage--;
                int stagePosition = clawStage*60;
                armMotor.setTargetPosition(stagePosition);
                armMotor.setPower(.6);
                armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }

            if(!(gamepad1.left_bumper||gamepad1.right_bumper)){
                double x = gamepad1.left_stick_x;
                double y = -gamepad1.left_stick_y;
                drive(x,y);
            }else{
                if (gamepad1.left_bumper){
                    strafeLeft();
                }else{
                    strafeRight();
                }

            }*/
            double magnitude = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            final double fld = magnitude * Math.cos(robotAngle) + rightX;
            final double frd = magnitude * Math.sin(robotAngle) - rightX;
            final double bld = magnitude * Math.sin(robotAngle) + rightX;
            final double brd = magnitude * Math.cos(robotAngle) - rightX;
            leftDrive.setPower(fld);
            rightDrive.setPower(frd);
            rearLeftDrive.setPower(bld);
            rearRightDrive.setPower(brd);
        }
    }


    }

    /*public void strafeLeft(){
        leftDrive.setPower(-1);
        rightDrive.setPower(1);
        rearLeftDrive.setPower(-1);
        rearRightDrive.setPower(1);
    }

    public void strafeRight(){
        leftDrive.setPower(1);
        rightDrive.setPower(-1);
        rearLeftDrive.setPower(1);
        rearRightDrive.setPower(-1);
    }*/
//.j}
