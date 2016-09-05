# OpModeCamera

**UPDATED on 9/1/16 to support the 2016/17 FTC SDK code structure!**

Basic FTC OpMode Functionality for using the front facing camera on the ZTE Speed phone for FTC.

DetectColor.java and LinearDetectColor.java show examples of OpModes using the camera functionality. 

To use this to make your own custom OpModes, just extend OpModeCamera or LinearOpModeCamera as shown in the examples and change the code that uses the RGB pixels to perform your own image processing functions. You can use all of the normal SDK functions as well (motors, servos, sensors, gamepads, etc.).

To install (as of 9/1/16):

* Put CameraPreview.java, OpModeCamera.java, and LinearOpModeCamera.java in the org.firstinspires.ftc.robotcontroller.internal folder.
* Create a new folder called "CameraOpModes" in the TeamCode java folder and put DetectColor.java and LinearDetectColor.java in the CameraOpModes folder.
* Add the code in AndroidManifestCameraExtras.xml to your AndroidManifest.xml (in the FtcRobotController manifests folder) right above the "<application" line.
* Add the code in activity_ftc_controller_camera_extras.xml to your activity_ftc_controller.xml (in the FtcRobotController res/layout folder) right before the last </RelativeLayout> line.
* Add the code in FtcRobotControllerActivityCameraExtras to your FtcRobotControllerActivity.java right after the variables are defined in the class. Import android.hardware.Camera in this class file also.

The files DetectColor.java and LinearDetectColor.java show how to use/extend the OpModeCamera and LinearOpModeCamera classes to access the RGB image values from the camera.  These OpModes put a camera preview window on the RobotController screen and report back to the DriverStation telemetry whether red, green, or blue is the color most seen by the camera at a given time.

The regular OpModeCamera like the one shown in DetectColor.java seems to work well.

The linear LinearOpModeCamera seems to work well but is a little bit tricky.  A LinearOpMode that ends "organically" can shut down the camera functions at the end of runOpMode.  But a LinearOpMode that ends due to a stop-button press or a driver-station timeout ends abruptly without a clean way to shut down the camera functions.  To address this, extensions of LinearOpModeCamera need to run the method stopCameraInSecs() right after waitForStart, which starts a thread that runs stopCamera after a fixed amount of time, even if the original LinearOpModeCamera is interrupted.  This usually works well, but can cause odd behavior if you try to break it on purpose.  For example, if you press start, stop, start, stop, start, stop repeatedly every second or so, sometimes the camera will not have fully stopped from the previous run before the next run starts.  In typical operation, it works.

Many thanks to FTC_Team5648 who provided much of the core code in this example in an FTC forum thread titled "How to use the camera as a sensor" on 9/17/15!
