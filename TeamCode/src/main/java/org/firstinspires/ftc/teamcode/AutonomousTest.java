package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Autonomous(name="Auto Test", group="Robot")

public class AutonomousTest extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor right_motor;
    private DcMotor left_motor;
    private DcMotor climb;
    //do servos later
    private Servo lb_servo;// Left Bar Servo
    private Servo rb_servo;// Right Bar Servo
    private ColorSensor left_sensey;
    private ColorSensor right_sensey;

    static final double     counts_per_motor_rev    = 100/6 ;    // eg: TETRIX Motor Encoder 560 1120
    static final double     drive_gear_reduction    = 1 ;     // This is < 1.0 if geared UP 20
    static final double     wheel_diameter_inches   = 4 ;     // For figuring circumference 3.75
    static final double     counts_per_inch         = (counts_per_motor_rev * drive_gear_reduction) /
            (wheel_diameter_inches * 3.1415); //circumference 12.57 tpr 1120

    HardwareMap hwMap = null;

    public void init(HardwareMap hwMap) {
        left_motor = hwMap.dcMotor.get("Left Motor");
        right_motor = hwMap.dcMotor.get("Right Motor");
        climb = hwMap.dcMotor.get("Climbing Motor");
        lb_servo = hwMap.servo.get("Left Bar Motor");
        rb_servo = hwMap.servo.get("Right Bar Motor");
        left_sensey = hwMap.colorSensor.get("Left Color Sensor");
        right_sensey = hwMap.colorSensor.get("Right Color Sensor");
    }

    public void runOpMode() throws InterruptedException {
        init(hardwareMap);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        ElapsedTime eTime = new ElapsedTime();
        while (eTime.time() <= 3) {
            climb.setPower(-1);
        }
        climb.setPower(0);
        left_sensey.enableLed(true);
        right_sensey.enableLed(true);

        if(!leftInBounds() || !rightInBounds()){
            if(!leftInBounds()){
                left_motor.setPower(1);
                while(!leftInBounds()){}
                left_motor.setPower(0);
            }
            right_motor.setPower(1);
            while(!rightInBounds()){}
            right_motor.setPower(0);
        }

        boolean[] minerals = new boolean[]{true,false,false};

        lb_servo.setPosition(0);
        rb_servo.setPosition(0);

        if (!minerals[1]) {
            encoderDrive(1, minerals[0] ? -5 : 5, minerals[2] ? -5 : 5, 1);
            encoderDrive(1, 5, 5 ,  1);
        } else {
            encoderDrive(1, 5, 5 ,  1);
        }

    }
    private static final double bound = 3;
    private boolean leftInBounds(){
        return Math.abs(left_sensey.red() - /*red value*/1) < bound;
    }
    private boolean rightInBounds(){
        return Math.abs(right_sensey.red() - /*red value*/1)<bound;
    }



    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = left_motor.getCurrentPosition() + (int)(leftInches * counts_per_inch);
            newRightTarget = right_motor.getCurrentPosition() + (int)(rightInches * counts_per_inch);
            left_motor.setTargetPosition(newLeftTarget);
            right_motor.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            left_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            right_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            left_motor.setPower(Math.abs(speed));
            left_motor.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (left_motor.isBusy() && right_motor.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        left_motor.getCurrentPosition(),
                        right_motor.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            left_motor.setPower(0);
            right_motor.setPower(0);

            // Turn off RUN_TO_POSITION
            left_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            right_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }
}
