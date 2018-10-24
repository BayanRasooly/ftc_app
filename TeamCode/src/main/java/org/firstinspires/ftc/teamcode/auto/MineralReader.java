package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.core.Robot;

import java.util.Arrays;

public class MineralReader {
    public static boolean[] random(){
        boolean[] arr = new boolean[3];
        Arrays.fill(arr,false);
        arr[(int) (Math.random()*3)] = true;
        return arr;
    }



    private static final double firstBound = 1.2;
    private static final double secondBound = 2.4;
    public static boolean[] read(double voltage){
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
