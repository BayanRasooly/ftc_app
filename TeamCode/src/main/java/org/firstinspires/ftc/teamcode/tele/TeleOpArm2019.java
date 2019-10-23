package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="TeleOpArm2019", group="Linear Opmode")

public class TeleOpArm2019 extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();;
    private DcMotor armMotor = null;

    //@Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        armMotor = hardwareMap.get(DcMotor.class, "armMotor");

        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();
        runtime.reset();
        int clawStage = 0;


        while (opModeIsActive()) {

            if (gamepad2.dpad_up && !armMotor.isBusy() && clawStage<7){
                clawStage++;
                int stagePosition = clawStage*30;
                armMotor.setTargetPosition(stagePosition);
                armMotor.setPower(.3);
                armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }

            if (gamepad2.dpad_down && !armMotor.isBusy() && clawStage>0){
                clawStage--;
                int stagePosition = clawStage*30;
                armMotor.setTargetPosition(stagePosition);
                armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }

        }
    }
}
