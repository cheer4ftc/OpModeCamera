package com.qualcomm.ftcrobotcontroller.opmodes;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * TeleOp Mode
 * <p/>
 * Enables control of the robot via the gamepad
 */
public class LinearDetectColor extends LinearOpMode {

    OpModeCamera cameraOps = new OpModeCamera();
    DcMotor motorRight;
    DcMotor motorLeft;

    int ds2 = 2;  // additional downsampling of the image
    // set to 1 to disable further downsampling

    @Override
    public void runOpMode() throws InterruptedException {
        String colorString = "NONE";

        // linear OpMode, so could do stuff like this too.
        /*
        motorLeft = hardwareMap.dcMotor.get("motor_1");
        motorRight = hardwareMap.dcMotor.get("motor_2");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        */

        cameraOps.setCameraDownsampling(8);
        // parameter determines how downsampled you want your images
        // 8, 4, 2, or 1.
        // higher number is more downsampled, so less resolution but faster
        // 1 is original resolution, which is detailed but slow
        // must be called before super.init sets up the camera


        cameraOps.startCamera();// can take a while.
        // best started before waitForStart
        // or in a separate thread.

        waitForStart();

        // LinearOpMode, so could do stuff like this too.
        /*
        motorLeft.setPower(1);  // drive forward
        motorRight.setPower(1);

        sleep(1000);            // for a second.

        motorLeft.setPower(0);  // stop drive motors.
        motorRight.setPower(0);

        sleep(1000);            // wait a second.
        */

        while (opModeIsActive()) {
            if (cameraOps.imageReady()) { // only do this if an image has been returned from the camera
                int redValue = 0;
                int blueValue = 0;
                int greenValue = 0;

                Bitmap rgbImage;
                rgbImage = cameraOps.convertYuvImageToRgb(cameraOps.yuvImage, cameraOps.width, cameraOps.height, ds2);
                for (int x = 0; x < cameraOps.width / ds2; x++) {
                    for (int y = 0; y < cameraOps.height / ds2; y++) {
                        int pixel = rgbImage.getPixel(x, y);
                        redValue += cameraOps.red(pixel);
                        blueValue += cameraOps.blue(pixel);
                        greenValue += cameraOps.green(pixel);
                    }
                }
                int color = cameraOps.highestColor(redValue, greenValue, blueValue);

                switch (color) {
                    case 0:
                        colorString = "RED";
                        break;
                    case 1:
                        colorString = "GREEN";
                        break;
                    case 2:
                        colorString = "BLUE";
                }

            }

            telemetry.addData("Color:", "Color detected is: " + colorString);
            waitOneHardwareCycle();
        }

        cameraOps.stopCamera();
    }
}
