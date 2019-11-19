package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="AirAndScare", group="Linear Opmode")
@Disabled
public class AirAndScare extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor rearLeftDrive = null;
    private DcMotor rearRightDrive = null;
    @Override
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


        while (opModeIsActive()) {

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
        leftDrive.setPower(-1);
        rightDrive.setPower(1);
        rearLeftDrive.setPower(1);
        rearRightDrive.setPower(-1);
    }

    public void strafeRight(){
        leftDrive.setPower(1);
        rightDrive.setPower(-1);
        rearLeftDrive.setPower(-1);
        rearRightDrive.setPower(1);
    }

}
