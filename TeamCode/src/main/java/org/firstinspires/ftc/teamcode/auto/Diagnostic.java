package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.core.Encoder;

@Autonomous(name="Diagnostic", group="Robot")
public class Diagnostic extends LinearOpMode {

    public DcMotor r_motor;
    public DcMotor l_motor;


    public DistanceSensor left_distance;
    public DistanceSensor right_distance;

    public static final int SPEED = 1;

    private void initMap() {

        r_motor = hardwareMap.dcMotor.get("Right Drive Motor");
        l_motor = hardwareMap.dcMotor.get("Left Drive Motor");
        left_distance = DistanceSensor.getLeft(hardwareMap);
        right_distance = DistanceSensor.getRight(hardwareMap);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        //en = new Encoder(robot,this);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        initMap();
        waitForStart();
        while(true){
//            boolean[] minerals = new MineralReader(hardwareMap).read();
//            telemetry.addData("Pixy", minerals);
            telemetry.addData("Left Range Sensor", left_distance.getUltrasonic());
            telemetry.addData("Right Range Sensor", right_distance.getUltrasonic());
            telemetry.update();
        }
    }
}