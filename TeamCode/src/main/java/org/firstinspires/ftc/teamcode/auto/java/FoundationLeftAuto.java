package org.firstinspires.ftc.teamcode.auto.java;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="FoundationLeftAuto", group="Linear Opmode")
public class FoundationLeftAuto extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor rearLeftDrive = null;
    private DcMotor rearRightDrive = null;
    private Servo grab1 = null;
    private Servo grab2 = null;

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

        grab1 = hardwareMap.get(Servo.class, "grab1");
        grab2 = hardwareMap.get(Servo.class, "grab2");

        waitForStart();
        runtime.reset();

        //while (opModeIsActive()) {
        rearDrive(.8,2000);
        grab1.setPosition(0.5);
        grab2.setPosition(-0.5);
        leftTankTurn(1,200);
        forwardDrive(1,1900);

        //}
    }

    public void forwardDrive(double power, int distance){
        driveEncoder(distance,"f");
        resetEncoders();
        runToPosition();
        leftDrive.setPower(power);
        rightDrive.setPower(power);
        rearLeftDrive.setPower(power);
        rearRightDrive.setPower(power);
        while (leftDrive.isBusy() || rightDrive.isBusy()){
            sleep(1);
        }

    }

    public void rightTankTurn (double power, int distance){
        driveEncoder(distance,"r");
        resetEncoders();
        runToPosition();
        leftDrive.setPower(power);
        rightDrive.setPower(-power);
        rearLeftDrive.setPower(power);
        rearRightDrive.setPower(-power);
        while (leftDrive.isBusy() || rightDrive.isBusy()){
            sleep(1);
        }
    }

    public void leftTankTurn(double power, int distance){
        driveEncoder(distance,"l");
        resetEncoders();
        runToPosition();
        leftDrive.setPower(-power);
        rightDrive.setPower(power);
        rearLeftDrive.setPower(-power);
        rearRightDrive.setPower(power);
        while (leftDrive.isBusy() || rightDrive.isBusy()){
            sleep(1);
        }
    }

    public void rearDrive(double power, int distance){
        driveEncoder(distance,"r");
        resetEncoders();
        runToPosition();
        leftDrive.setPower(-power);
        rightDrive.setPower(-power);
        rearLeftDrive.setPower(-power);
        rearRightDrive.setPower(-power);
        while (leftDrive.isBusy() || rightDrive.isBusy()){
            sleep(1);
        }
    }

    public void driveEncoder(int distance, String direction){
        if(direction.equals("f")) {
            leftDrive.setTargetPosition(distance);
            rightDrive.setTargetPosition(distance);
            rearLeftDrive.setTargetPosition(distance);
            rearRightDrive.setTargetPosition(distance);
        }else if(direction.equals("b")){
            leftDrive.setTargetPosition(-distance);
            rightDrive.setTargetPosition(-distance);
            rearLeftDrive.setTargetPosition(-distance);
            rearRightDrive.setTargetPosition(-distance);
        }else if(direction.equals("r")){
            leftDrive.setTargetPosition(distance);
            rightDrive.setTargetPosition(-distance);
            rearLeftDrive.setTargetPosition(distance);
            rearRightDrive.setTargetPosition(-distance);
        }else if(direction.equals("l")){
            leftDrive.setTargetPosition(-distance);
            rightDrive.setTargetPosition(distance);
            rearLeftDrive.setTargetPosition(-distance);
            rearRightDrive.setTargetPosition(distance);
        }

    }
    public void runToPosition(){
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void resetEncoders(){
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


}

