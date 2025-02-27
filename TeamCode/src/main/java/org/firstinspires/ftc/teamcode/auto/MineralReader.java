package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.core.Robot;

import java.util.Arrays;

public class MineralReader {


    private final AnalogInput pixy;
    public MineralReader(HardwareMap map){
        pixy = map.analogInput.get("Pixy");
    }

    public MineralReader(AnalogInput pixy) {
        this.pixy = pixy;
    }

    private static final double firstBound = 0.3;
    private static final double secondBound = 1.4;
    public boolean[] read(){
        double voltage = pixy.getVoltage();
        boolean[] arr = new boolean[3];
        Arrays.fill(arr,false);
        if(voltage < firstBound)
            arr[0] = true;
        else if(voltage > secondBound)
            arr[2] = true;
        else
            arr[1] = true;
        return arr;

    }
}
