package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.teamcode.core.Encoder;

@Autonomous(name="Diagnostic", group="Robot")
public class Diagnostic extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private HardwareMap map;

    public DcMotor r_motor;
    public DcMotor l_motor;



    public DcMotor climb;
    //do servos later
    public Servo lb_servo;// Left Bar Servo
    public Servo rb_servo;// Right Bar Servo

    public ColorSensor left_sensey;
    public ColorSensor right_sensey;
    public DistanceSensor left_distance;
    public DistanceSensor right_distance;

    public AnalogInput ana;

    Encoder en;

    public static final int SPEED = 1;

    private void initMap() {
        r_motor = map.dcMotor.get("Right Drive Motor");
        l_motor = map.dcMotor.get("Left Drive Motor");
        climb = map.dcMotor.get("Lifting Motor");
        lb_servo = map.servo.get("Left Bar Motor");
        rb_servo = map.servo.get("Right Bar Motor");
        left_sensey = map.colorSensor.get("Left Color Sensor");
        right_sensey = map.colorSensor.get("Right Color Sensor");
        left_distance = DistanceSensor.getLeft(hardwareMap);
        right_distance = DistanceSensor.getRight(hardwareMap);
        ana = map.analogInput.get("ana");
    }

    @Override
    public void runOpMode() throws InterruptedException {
        //en = new Encoder(robot,this);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        boolean[] minerals = new MineralReader(hardwareMap).read();
        telemetry.addData("Pixy", minerals);
        telemetry.addData("Left Range Sensor", left_distance);
        telemetry.addData("Right Range Sensor", right_distance);
        telemetry.addData("Left Color Sensor", left_sensey);
        telemetry.addData("Right Color Sensor", right_sensey);
    }
}