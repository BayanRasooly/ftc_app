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

@Autonomous(name="Crater Auto", group="Robot")
public class CraterAuto extends LinearOpMode {

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
        en.lower(climb, SPEED);

        left_sensey.enableLed(true);
        right_sensey.enableLed(true);
        if(!en.leftInBounds(left_sensey) || !en.rightInBounds(right_sensey)){
            if(!en.leftInBounds(left_sensey)){
                en.setLeftMotorPower(l_motor, SPEED);
                while(!en.leftInBounds(left_sensey)){
                    en.wait(10);
                }
                en.setLeftMotorPower(l_motor, SPEED);
            }
            en.setRightMotorPower(r_motor, SPEED);
            while(!en.rightInBounds(right_sensey)){
                en.wait(10);
            }
            en.setRightMotorPower(r_motor, SPEED);
        }//COPIED

        boolean[] minerals = new MineralReader(hardwareMap).read();

        if(!minerals[1]){
            int mult = minerals[0]?1:-1;
            en.encoderDrive(l_motor, r_motor, SPEED, mult * -5, mult * 5);
        }
        en.encoderDrive(l_motor, r_motor, SPEED,100);
        en.encoderDrive(l_motor, r_motor, SPEED,-100);
        //TURN 45*TODO
        en.align(l_motor, r_motor, left_distance, right_distance);
        en.encoderDrive(l_motor, r_motor, SPEED,5);//drive to wall
        en.encoderDrive(l_motor, r_motor, SPEED,-5,5);//turn to claim
        en.align(l_motor, r_motor, left_distance, right_distance);//may be needed, may waste time
        en.encoderDrive(l_motor, r_motor, SPEED,144); new Func<Boolean>(){
            @Override
            public Boolean value() {
                return en.rightInBounds(right_sensey) || en.leftInBounds(left_sensey);
            }
        };
        en.claim();
        en.encoderDrive(l_motor, r_motor, SPEED,144);
    }
}