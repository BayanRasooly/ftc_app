package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;

public class DistanceSensor {
    private I2cDeviceSynch rangeReader;
    private byte[] rangeCache;

    private DistanceSensor(int eightBit, String name, HardwareMap map){
        I2cDevice range = map.i2cDevice.get(name);
        this.rangeReader = new I2cDeviceSynchImpl(range, I2cAddr.create8bit(eightBit), false);
        rangeReader.engage();
    }

    static DistanceSensor getLeft(HardwareMap map){
        return new DistanceSensor(0x28,"L_Range",map);
    }

    static DistanceSensor getRight(HardwareMap map){
        return new DistanceSensor(0x2a,"L_Range",map);
    }

    public int getUltrasonic(){
        rangeCache = rangeReader.read(0x04, 2);
        return rangeCache[0] & 0xFF;  // & 0xFF creates a value between 0 and 255 instead of -127 to 128
    }

    public int getOptical(){
        rangeCache = rangeReader.read(0x24,2);
        return rangeCache[1] & 0xFF;
    }

}
