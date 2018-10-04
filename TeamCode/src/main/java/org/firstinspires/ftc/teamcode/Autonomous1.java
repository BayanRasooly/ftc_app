package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.Func;

import java.util.concurrent.TimeUnit;


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
//        telemetry.addData("RED", sensey.red());//replace with
        telemetry.addData("RED",new Func<String>() {//lamba would have been used by java version is capped at 1.7 :(
            @Override
            public String value() {
                return sensey.red() + "";
            }
        });//sends red value everytime telemtry.update() is called TODO VERIFY THIS WORKS
        setAllDriveMoters(forwardSpeed);
        ElapsedTime eTime = new ElapsedTime();
        eTime.reset();
        while (eTime.time() < 2.0) {
             Thread.sleep(10);//poll time. don't make calls in instantly, waiting as little as 10 ms greatly deceases processing needed
        }
        setAllDriveMoters(0);//stop moving after 2 seconds, ~+10ms

        //maybe?
        telemetry.addData("Runtime",runtime.time(TimeUnit.MILLISECONDS));
        telemetry.update();
    }

    private void setAllDriveMoters(double speed){
        lf_motor.setPower(speed);
        lb_motor.setPower(speed);
        rf_motor.setPower(speed);
        rb_motor.setPower(speed);
    }

}



