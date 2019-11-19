package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.Func;

@Autonomous(name="ColorTest", group="Test")
@Disabled
public class ColorTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        final ColorSensor sensy = hardwareMap.colorSensor.get("Color");
        sensy.enableLed(true);
        telemetry.addData("RED:", new Func<Integer>() {
            @Override
            public Integer value() {
                return sensy.red();
            }
        });
        telemetry.addData("BLUE:", new Func<Integer>() {
            @Override
            public Integer value() {
                return sensy.blue();
            }
        });
        telemetry.addData("GREEN:", new Func<Integer>() {
            @Override
            public Integer value() {
                return sensy.green();
            }
        });
        while(true) {
            Thread.sleep(10);
            telemetry.update();
        }
    }
}
