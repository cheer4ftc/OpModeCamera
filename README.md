# OpModeCamera
Basic FTC OpMode Functionality for using the front facing camera

To use (as of 9/20/15):

1. Add the code in AndroidManifestCameraExtras.xml to your AndroidManifest.xml right above the "<application" line.
2. Add the code in activity_ftc_controller_camera_extras.xml to your activity_ftc_controller.xml right before the last </RelativeLayout> line.
3. Add the code in FtcRobotControllerActivityCameraExtras to your FtcRobotControllerActivity.java right after the variables are defined in the class.
4. Copy CameraPreview.java to the com.qualcomm.ftcrobotcontroller folder.
5. Copy OpModeCamera.java, LinearOpModeCamera.java, DetectColor.java, and LinearDetectColor.java to the opmodes folder.
6. Add the code in FtcOpModeRegisterCameraExtras to your FtcOpModeRegister.java file in the register method.

The files DetectColor.java and LinearDetectColor.java show how to use/extend the OpModeCamera class to access the RGB image values from the camera.  These OpModes put a camera preview window on the RobotController screen and report back to the DriverStation telemetry whether red, green, or blue is the color most seen by the camera at a given time.

The regular OpModeCamera like the one shown in DetectColor.java seems to work well.

The linear LinearOpModeCamera is a little bit tricky.  A LinearOpMode that ends "organically" can shut down the camera functions at the end of runOpMode.  But a LinearOpMode that ends due to a stop-button press or a driver-station timeout ends abruptly without a clean way to shut down the camera functions.  To address this, LinearOpModeCamera starts a thread right after waitForStart happens that auto-runs stopCamera after a fixed amount of time.  This usually works well, but can cause odd behavior if you try to break it on purpose.  For example, if you press start, stop, start, stop, start, stop repeatedly every second or so, sometimes the camera will not have fully stopped from the previous run before the next run starts.  In typically operation, it should work.

Many thanks to FTC_Team5648 who provided much of the core code in this example in an FTC forum thread titled "How to use the camera as a sensor" on 9/17/15!
