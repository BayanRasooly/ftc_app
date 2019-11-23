package org.firstinspires.ftc.teamcode.auto.java;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="autoFoundationSimple")
public class autoFoundationSimple extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor rearLeftDrive = null;
    private DcMotor rearRightDrive = null;
    private Servo grab1 = null;
    private Servo grab2 = null;
    private DcMotor armMotor = null;
    private Servo wristServo = null;




    public void strafeLeft(double power, int distance){
        driveEncoder(distance, "sl");
        resetEncoders();
        runToPosition();
        leftDrive.setPower(power);
        rightDrive.setPower(power);
        rearLeftDrive.setPower(power);
        rearRightDrive.setPower(power);
        while (leftDrive.isBusy() || rightDrive.isBusy()) {
            sleep(1);
        }
    }

    public void strafeRight(double power, int distance){
        driveEncoder(distance, "sr");
        resetEncoders();
        runToPosition();
        leftDrive.setPower(power);
        rightDrive.setPower(power);
        rearLeftDrive.setPower(power);
        rearRightDrive.setPower(power);
        while (leftDrive.isBusy() || rightDrive.isBusy()) {
            sleep(1);
        }
    }
    public void forwardDrive(double power, int distance){
        driveEncoder(distance,"f");
        resetEncoders();
        runToPosition();
        leftDrive.setPower(power);
        rightDrive.setPower(power);
        rearLeftDrive.setPower(power);
        rearRightDrive.setPower(power);
        while (leftDrive.isBusy() || rightDrive.isBusy())
            sleep(1);
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
        driveEncoder(distance,"b");
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

    public void armUp(double power, int distance){
        driveEncoder(distance,"a");
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setPower(power);
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
        }else if(direction.equals("l")) {
            leftDrive.setTargetPosition(-distance);
            rightDrive.setTargetPosition(distance);
            rearLeftDrive.setTargetPosition(-distance);
            rearRightDrive.setTargetPosition(distance);
        }else if(direction.equals("sr")){
            leftDrive.setTargetPosition(-distance);
            rightDrive.setTargetPosition(-distance);
            rearLeftDrive.setTargetPosition(distance);
            rearRightDrive.setTargetPosition(distance);
        }else if(direction.equals("sl")) {
            leftDrive.setTargetPosition(distance);
            rightDrive.setTargetPosition(distance);
            rearLeftDrive.setTargetPosition(-distance);
            rearRightDrive.setTargetPosition(-distance);
        }else if(direction.equals("a")) {
            armMotor.setTargetPosition(distance);
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

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        rearLeftDrive  = hardwareMap.get(DcMotor.class, "rear_left_drive");
        rearRightDrive = hardwareMap.get(DcMotor.class, "rear_right_drive");
        grab1 = hardwareMap.get(Servo.class, "grab1");
        grab2 = hardwareMap.get(Servo.class, "grab2");
        wristServo = hardwareMap.get(Servo.class, "wristServo");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");


        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        rearLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        rearRightDrive.setDirection(DcMotor.Direction.REVERSE);

        rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rearRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rearRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rearLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        resetEncoders();
        armUp(0.6, 260);

        waitForStart();

        runtime.reset();
        armUp(0.5,-100);
        wristServo.setPosition(1);
        grab1.setPosition(1);
        grab2.setPosition(0);
        resetEncoders();
        rearDrive(1,(int)(10.0/3*920));
        sleep(100);
        strafeRight(1,1120);
        sleep(100);
        rearDrive(1,350);
        grab1.setPosition(0.5);
        grab2.setPosition(0.5);
        sleep(150);
        forwardDrive(1,(int)(10.0/3*1000));
        sleep(100);
        leftTankTurn(1,500);
        sleep(100);
        grab1.setPosition(1);
        grab2.setPosition(0);
        strafeLeft(1,3*1600);
        rightTankTurn(1, 500);
    }
}