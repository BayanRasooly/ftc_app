package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.concurrent.TimeUnit;

@Autonomous(name="auto2", group="Pushbot")
public class Autonomous2 extends LinearOpMode {

    private DcMotor lf_motor;
    private DcMotor lb_motor;
    private DcMotor rf_motor;
    private DcMotor rb_motor;
    private Servo knocker;
    static final double FORWARD_SPEED = .5;
    private ElapsedTime runtime = new ElapsedTime();
    ColorSensor sensey;

    DistanceSensor r_distance;
    DistanceSensor l_distance;


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
        r_distance = hwMap.get(DistanceSensor.class,"r_distance_sensor");//hmmm. kinda a hack
        l_distance = hwMap.get(DistanceSensor.class,"l_distance_sensor");

    }
    public void runOpMode() throws InterruptedException {
        init(hardwareMap);

        telemetry.addData("Right Distance Sensor", new Func<String>() {
            @Override
            public String value() {
                return r_distance.getDistance(DistanceUnit.CM) + "";
            }
        });
        telemetry.addData("Left Distance Sensor", new Func<String>() {
            @Override
            public String value() {
                return l_distance.getDistance(DistanceUnit.CM) + "";
            }
        });
        telemetry.update();

        rb_motor.setPower(FORWARD_SPEED);//slightly move the robot
        ElapsedTime eTime = new ElapsedTime();
        waitForETime(eTime,500);

        align();//align
        telemetry.update();
    }

    private void align(){
        double allowance = 2;
        while(true){
            double difference = getDifference();
            if(Math.abs(difference) < allowance)
                return;

            int mult;
            if(difference > 0)
                mult = 1;
            else
                mult = -1;
            lf_motor.setPower( mult * FORWARD_SPEED/4);
            lb_motor.setPower( mult * FORWARD_SPEED/4);
            rf_motor.setPower(-mult * FORWARD_SPEED/4);
            rb_motor.setPower(-mult * FORWARD_SPEED/4);
            while(Math.abs(getDifference()) >= allowance){
                telemetry.addLine("difference:" + getDifference());
                telemetry.update();
            }
            setAllDriveMoters(0);
        }
    }

    /**
     * Returns positive if right is longer, negative if left is longer
     */
    private double getDifference(){
        return r_distance.getDistance(DistanceUnit.CM) - l_distance.getDistance(DistanceUnit.CM);
    }


    private void setAllDriveMoters(double speed){
        lf_motor.setPower(speed);
        lb_motor.setPower(speed);
        rf_motor.setPower(speed);
        rb_motor.setPower(speed);
    }


    private void waitForETime(ElapsedTime e,double ms,int msPollWait) throws InterruptedException {
        while(e.time(TimeUnit.MILLISECONDS) < ms){
            Thread.sleep(msPollWait);
        }
    }

    private void waitForETime(ElapsedTime e,double ms) throws InterruptedException {
        waitForETime(e,ms,10);
    }


}
