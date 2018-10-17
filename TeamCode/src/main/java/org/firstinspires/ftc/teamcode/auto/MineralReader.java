package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.core.Robot;

import java.util.Arrays;

public class MineralReader {
    public static boolean[] read(Robot r){
        boolean[] arr = new boolean[3];
        Arrays.fill(arr,false);
        arr[(int) (Math.random()*3)] = true;
        return arr;
    }
}
