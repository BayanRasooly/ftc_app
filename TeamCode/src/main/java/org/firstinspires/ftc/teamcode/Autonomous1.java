package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;


@Autonomous(name="auto1", group="Pushbot")
//@Disabled
public class Autonomous1 extends LinearOpMode {

    private DcMotor lf_motor;
    private DcMotor lb_motor;
    private DcMotor rf_motor;
    private DcMotor rb_motor;
    private Servo knocker;
    static final double forwardSpeed = .5;
    private ElapsedTime runtime = new ElapsedTime();
    ColorSensor sensey;
    HardwareMap hwMap = null;

    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;
        lf_motor = hwMap.dcMotor.get("lf_drive");
        lb_motor = hwMap.dcMotor.get("lb_drive");
        rf_motor = hwMap.dcMotor.get("rf_drive");
        rb_motor = hwMap.dcMotor.get("rb_drive");
        knocker = hwMap.servo.get("knocker");
        sensey = hwMap.colorSensor.get("sense");
    }

    public void runOpMode() throws InterruptedException {

        float hsvValues[] = {0F,0F,0F};
        final float values[] = hsvValues;
        init(hardwareMap);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        knocker.setPosition(1);
        telemetry.addData("RED", sensey.red());
        //lf_motor.setPower(forwardSpeed);
        //lb_motor.setPower(forwardSpeed);
        //rf_motor.setPower(forwardSpeed);
        //rb_motor.setPower(forwardSpeed);

        ElapsedTime eTime = new ElapsedTime();

        eTime.reset();

        while (eTime.time() < 2.0) {

        }
    }
}



