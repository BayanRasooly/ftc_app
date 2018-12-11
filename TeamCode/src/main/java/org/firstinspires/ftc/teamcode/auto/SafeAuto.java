package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.Encoder;

@Autonomous(name="Auto SAFE (if shit hits the fan)", group="Robot")
public class SafeAuto extends LinearOpMode {

    public DcMotor r_motor;
    public DcMotor l_motor;

    public DcMotor climb;
    public DcMotor claim;

    private void initMap() {
        r_motor = hardwareMap.dcMotor.get("Right Drive Motor");
        l_motor = hardwareMap.dcMotor.get("Left Drive Motor");
        climb = hardwareMap.dcMotor.get("Lifting Motor");
        claim = hardwareMap.dcMotor.get("Intake Motor");
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initMap();
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        try {
            Encoder en = new Encoder(this);
            en.startAuto(l_motor, r_motor, climb);
//            en.claim(claim);
        }catch(Throwable e){
            throw e;
        }
    }

}
