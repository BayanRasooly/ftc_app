package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="TeleOp2019", group="Linear Opmode")

public class TeleOp2019 extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor rearLeftDrive = null;
    private DcMotor rearRightDrive = null;
    private DcMotor armMotor = null;
    private DcMotor sliderMotor = null;
    private Servo leftFunnelServo = null;
    private Servo rightFunnelServo = null;

    //@Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        rearLeftDrive  = hardwareMap.get(DcMotor.class, "rear_left_drive");
        rearRightDrive = hardwareMap.get(DcMotor.class, "rear_right_drive");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        sliderMotor = hardwareMap.get(DcMotor.class, "sliderMotor");
        leftFunnelServo = hardwareMap.get(Servo.class, "leftFunnelServo");
        rightFunnelServo = hardwareMap.get(Servo.class, "leftFunnelServo");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        rearLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        rearRightDrive.setDirection(DcMotor.Direction.REVERSE);

        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        sliderMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sliderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();
        runtime.reset();
        int clawStage = 0;


        while (opModeIsActive()) {

            if (gamepad2.left_stick_y>0.05 && sliderMotor.getCurrentPosition()>=0){
                sliderMotor.setPower(gamepad2.left_stick_y);
            }else if(gamepad2.left_stick_y<-0.05 && sliderMotor.getCurrentPosition()<=2000){
                sliderMotor.setPower(-gamepad2.left_stick_y);
            }else{
                sliderMotor.setPower(0);
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
        rightDrive.setPower(-1);
        rearLeftDrive.setPower(-1);
        rearRightDrive.setPower(1);
    }

    public void strafeRight(){
        leftDrive.setPower(-1);
        rightDrive.setPower(1);
        rearLeftDrive.setPower(1);
        rearRightDrive.setPower(-1);
    }

}
