package org.firstinspires.ftc.teamcode.carson;

import org.firstinspires.ftc.teamcode.core.Encoder;
import org.firstinspires.ftc.teamcode.core.Robot;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadedAligner implements Runnable {
    Robot robot;
    Encoder en;

    public ThreadedAligner(Robot robot,Encoder en) {
        this.robot =robot;
        this.en = en;
    }

    public void startThread(){
        Executors.newSingleThreadExecutor().submit(this);
    }

    private volatile boolean state;
    public void setState(boolean state){
        this.state = state;
    }

    private volatile boolean run = true;
    public void stopAll(){
        run = false;
    }

    @Override
    public void run() {
        while(!run){
            if(!state){
                try { Thread.sleep(10); } catch (InterruptedException e) {e.printStackTrace(); }//decrease wait
                continue;
            }
            int bound = 1;
            int increase = 1;
            int msWait = 100;
            if(Math.abs(en.aligment()) < bound){
                try { Thread.sleep(msWait); } catch (InterruptedException e) {e.printStackTrace(); }
                continue;
            }
            if(en.aligment() > 0){
                double power = robot.l_motor.getPower();
                robot.l_motor.setPower(power + increase);
                en.unsafeWait(msWait);
                robot.l_motor.setPower(power);
            }else{
                double power = robot.r_motor.getPower();
                robot.r_motor.setPower(power + increase);
                en.unsafeWait(msWait);
                robot.r_motor.setPower(power);
            }
        }
    }
}
