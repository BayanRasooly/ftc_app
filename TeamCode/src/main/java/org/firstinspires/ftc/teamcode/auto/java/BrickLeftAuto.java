package org.firstinspires.ftc.teamcode.auto.java;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="BrickLeftAuto", group="Linear Opmode")
public class BrickLeftAuto extends LinearOpMode {

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

            //while (opModeIsActive()) {
            int bricks = 0;
            forwardDrive(1,1120/2);
            leftTankTurn(1,(int)(1120*1.25));
            while(/*filler*/true) {
                forwardDrive(1, 1000);/*one block*/
                bricks++;
                if(/*test test*/true){
                    break;
                }

            }
            rightTankTurn(1,(int)(1120*1.25));
            forwardDrive(1,50);
            /*grab*/
            rearDrive(1,100);
            rightTankTurn(1,(int)(1120*1.25));
            forwardDrive(1, 15000 - (1000 * bricks));
            rearDrive(1,3000);


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

