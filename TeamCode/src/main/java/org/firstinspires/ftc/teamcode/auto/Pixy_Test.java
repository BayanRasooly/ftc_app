package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.AnalogInputController;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.SerialNumber;

import org.firstinspires.ftc.robotcore.external.Func;


@Autonomous(name="Pixy_Test", group="Robot")

public class Pixy_Test extends LinearOpMode {

    HardwareMap hwMap = null;
    private AnalogInputController controller = null;
    AnalogInput controller1;// = new AnalogInput(controller,0);

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        controller1 = hwMap.analogInput.get("ana");
    }
            /*
    //AnalogInputController ana;
*/
    public void runOpMode() throws InterruptedException {
        init(hardwareMap);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        //if connect
        telemetry.addData("connection info", controller1.getConnectionInfo());
        //check voltage
        telemetry.addData("Volts",new Func<String>(){
            @Override
            public String value() {
                return controller1.getVoltage() + "";
            }
        });
        //check iterations/runs
        telemetry.addData("Iteration",new Func<String>(){
            int index = 0;
            @Override
            public String value() {
                return index++ + "";
            }
        });

        telemetry.addData("Guess", new Func<String>(){
            @Override
            public String value() {
                boolean[] arr = new MineralReader(controller1).read();
                return "[" + arr[0] + "," + arr[1] + "," + arr[2] + "]";
            }
        });

        while(opModeIsActive()) {
            //push telemetry to phone
            telemetry.update();
            Thread.sleep(10);
        }
    }
}