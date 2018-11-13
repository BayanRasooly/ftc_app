package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.core.Encoder;

@Autonomous(name="EncoderTest", group="Robot")

public class EncoderTest extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();

    Encoder en;

    public static final int SPEED = 1;


    public DcMotor r_motor;
    public DcMotor l_motor;

    private void initMap(){
        r_motor = hardwareMap.dcMotor.get("R");
        l_motor = hardwareMap.dcMotor.get("L");
    }

    public void runOpMode() throws InterruptedException {
        initMap();
        en = new Encoder(this);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        en.encoderDrive(l_motor, r_motor, SPEED,120);
    }






}
