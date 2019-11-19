package org.firstinspires.ftc.teamcode;

/*
Modern Robotics Range Sensors Example
Created 10/31/2016 by Colton Mehlhoff of Modern Robotics using FTC SDK 2.35
Reuse permitted with credit where credit is due

Configuration:
I2CDevice "range28" (MRI Range Sensor with default I2C address 0x28
I2CDevice "range2a" (MRI Range Sensor with I2C address 0x2a

To change range sensor I2C Addresses, go to http://modernroboticsedu.com/mod/lesson/view.php?id=96
Support is available by emailing support@modernroboticsinc.com.
*/

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;

@TeleOp(name = "Range Sensors", group = "MRI")
@Disabled
public class MRI_Range_Sensors extends OpMode {

    byte[] rangeAcache;
    byte[] rangeBcache;

    I2cDevice rangeA;
    I2cDevice rangeB;
    I2cDeviceSynch rangeAreader;
    I2cDeviceSynch rangeBreader;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        rangeA = hardwareMap.i2cDevice.get("L_Range");
        rangeB = hardwareMap.i2cDevice.get("R_Range");

        rangeAreader = new I2cDeviceSynchImpl(rangeA, I2cAddr.create8bit(0x28), false);
        rangeBreader = new I2cDeviceSynchImpl(rangeB, I2cAddr.create8bit(0x2a), false);

        rangeAreader.engage();
        rangeBreader.engage();
    }

    @Override
    public void loop() {
        rangeAcache = rangeAreader.read(0x04, 2);  //Read 2 bytes starting at 0x04
        rangeBcache = rangeBreader.read(0x04, 2);

        // Ultrasonic value is at index 0.
        int LUS = rangeAcache[0] & 0xFF;  // & 0xFF creates a value between 0 and 255 instead of -127 to 128
        int RUS = rangeBcache[0] & 0xFF;

        // Optical distance value is at index 1.
        int LODS = rangeAcache[1] & 0xFF;  // & 0xFF creates a value between 0 and 255 instead of -127 to 128
        int RODS = rangeBcache[1] & 0xFF;

        // Display values
        telemetry.addData("1 L US", LUS);
        telemetry.addData("2 L ODS", LODS);
        telemetry.addData("3 R US", RUS);
        telemetry.addData("4 R ODS", RODS);
    }
}

