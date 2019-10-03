/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


//@Disabled
public class Auto_Pathing extends LinearOpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorLeft = null;
    private DcMotor motorRight = null;
    private DcMotor motorRearLeft = null;
    private DcMotor motorRearRight = null;

    public void runOpMode() {

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorRearLeft = hardwareMap.dcMotor.get("motorRearLeft");
        motorRearRight = hardwareMap.dcMotor.get("motorRearRight");

        /*motorLeft.setChannelMode(DcMotorController.RunMode.Run_To_Position)
        motorRight.setChannelMode(DcMotorController.RunMode.Run_To_Position)
        motorRearLeft.setChannelMode(DcMotorController.RunMode.Run_To_Position)
        motorRearRight.setChannelMode(DcMotorController.RunMode.Run_To_Position)

        motorLeft.setChannelMode(DcMotorController.RunMode.Run_To_Position)
        motorRight.setChannelMode(DcMotorController.RunMode.Run_To_Position)
        motorRearLeft.setChannelMode(DcMotorController.RunMode.Run_To_Position)
        motorRearRight.setChannelMode(DcMotorController.RunMode.Run_To_Position)

        motorLeft.setMotorDirection(DcMotor.Direction.REVERSE);
        motorRearLeft.setMotorDirection(DcMotor.Direction.REVERSE);*/

        waitForStart();
        AllForwardDistance(1, 500);
    }

    public void AllForward (double power) {

        motorLeft.setPower(power);
        motorRight.setPower(power);
        motorRearLeft.setPower(power);
        motorRearRight.setPower(power);
        
    }

    public void RightTurn (double power) {

        motorLeft.setPower(power);
        motorRight.setPower(-power);
        motorRearLeft.setPower(power);
        motorRearRight.setPower(-power);
        
    }

    public void LeftTurn (double power) {

        motorLeft.setPower(-power);
        motorRight.setPower(power);
        motorRearLeft.setPower(-power);
        motorRearRight.setPower(power);
        
    }

    public void Reverse (double power) {

        motorLeft.setPower(-power);
        motorRight.setPower(-power);
        motorRearLeft.setPower(-power);
        motorRearRight.setPower(-power);
    }

    public void AllForwardDistance (double power, int distance){

        /*motorLeft.setChannelMode(DcMotorController.RunMode.Reset_Encoders);
        motorRight.setChannelMode(DcMotorController.RunMode.Reset_Encoders);
        motorRearLeft.setChannelMode(DcMotorController.RunMode.Reset_Encoders);
        motorRearRight.setChannelMode(DcMotorController.RunMode.Reset_Encoders);*/

        motorRight.setTargetPosition(distance);
        motorLeft.setTargetPosition(distance);
        motorRearRight.setTargetPosition(distance);
        motorRearLeft.setTargetPosition(distance);

        AllForward(power);

        while (motorRearRight.isBusy() && (motorRight.isBusy() && (motorLeft.isBusy() && (motorRearLeft.isBusy())))){

        }

        /*stopDriving();
        motorLeft.setChannelMode(DcMotorController.RunMode.Run_Using_Encoders);
        motorRight.setChannelMode(DcMotorController.RunMode.Run_Using_Encoders);
        motorRearLeft.setChannelMode(DcMotorController.RunMode.Run_Using_Encoders);
        motorRearRight.setChannelMode(DcMotorController.RunMode.Run_Using_Encoders);*/
    }

    public void RightTurnDistance (double power, int distance){

        /*motorLeft.setChannelMode(DcMotorController.RunMode.Reset_Encoders);
        motorRight.setChannelMode(DcMotorController.RunMode.Reset_Encoders);
        motorRearLeft.setChannelMode(DcMotorController.RunMode.Reset_Encoders);
        motorRearRight.setChannelMode(DcMotorController.RunMode.Reset_Encoders);*/

        motorRight.setTargetPosition(distance);
        motorLeft.setTargetPosition(distance);
        motorRearRight.setTargetPosition(distance);
        motorRearLeft.setTargetPosition(distance);

        RightTurn(power);

        while (motorRearRight.isBusy() && (motorRight.isBusy() && (motorLeft.isBusy() && (motorRearLeft.isBusy())))){

        }

        /*stopDriving();
        motorLeft.setChannelMode(DcMotorController.RunMode.Run_Using_Encoders);
        motorRight.setChannelMode(DcMotorController.RunMode.Run_Using_Encoders);
        motorRearLeft.setChannelMode(DcMotorController.RunMode.Run_Using_Encoders);
        motorRearRight.setChannelMode(DcMotorController.RunMode.Run_Using_Encoders);*/
    }

    public void LeftTurnDistance (double power, int distance){

        /*motorLeft.setChannelMode(DcMotorController.RunMode.Reset_Encoders);
        motorRight.setChannelMode(DcMotorController.RunMode.Reset_Encoders);
        motorRearLeft.setChannelMode(DcMotorController.RunMode.Reset_Encoders);
        motorRearRight.setChannelMode(DcMotorController.RunMode.Reset_Encoders);*/

        motorRight.setTargetPosition(distance);
        motorLeft.setTargetPosition(distance);
        motorRearRight.setTargetPosition(distance);
        motorRearLeft.setTargetPosition(distance);

        LeftTurn(power);

        while (motorRearRight.isBusy() && (motorRight.isBusy() && (motorLeft.isBusy() && (motorRearLeft.isBusy())))){

        }

        /*stopDriving();
        motorLeft.setChannelMode(DcMotorController.RunMode.Run_Using_Encoders);
        motorRight.setChannelMode(DcMotorController.RunMode.Run_Using_Encoders);
        motorRearLeft.setChannelMode(DcMotorController.RunMode.Run_Using_Encoders);
        motorRearRight.setChannelMode(DcMotorController.RunMode.Run_Using_Encoders);*/
    }

    public void ReverseDistance (double power, int distance){

        /*motorLeft.setChannelMode(DcMotorController.RunMode.Reset_Encoders);
        motorRight.setChannelMode(DcMotorController.RunMode.Reset_Encoders);
        motorRearLeft.setChannelMode(DcMotorController.RunMode.Reset_Encoders);
        motorRearRight.setChannelMode(DcMotorController.RunMode.Reset_Encoders);*/

        motorRight.setTargetPosition(distance);
        motorLeft.setTargetPosition(distance);
        motorRearRight.setTargetPosition(distance);
        motorRearLeft.setTargetPosition(distance);

        Reverse(power);

        while (motorRearRight.isBusy() && (motorRight.isBusy() && (motorLeft.isBusy() && motorRearLeft.isBusy()))){

        }

        /*stopDriving();
        motorLeft.setChannelMode(DcMotorController.RunMode.Run_Using_Encoders);
        motorRight.setChannelMode(DcMotorController.RunMode.Run_Using_Encoders);
        motorRearLeft.setChannelMode(DcMotorController.RunMode.Run_Using_Encoders);
        motorRearRight.setChannelMode(DcMotorController.RunMode.Run_Using_Encoders);*/
    }



}