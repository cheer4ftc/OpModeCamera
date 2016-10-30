# OpModeCamera

**UPDATED on 10/30/16 to cleanly teardown the camera in LinearOpModes consistent with the newly improved LinearOpMode format which no longer throws exceptions.**

**UPDATED on 9/15/16 to cleanly teardown the camera in LinearOpModes, rotate the returned images so (0,0) corresponds to the bottom left of the preview window, and clean up some imports.**

**UPDATED on 9/5/16 to organize files into packages for easier installation and support.**

**UPDATED on 9/1/16 to support the 2016/17 FTC SDK code structure**

Basic FTC OpMode Functionality for using the front facing camera on the ZTE Speed phone for FTC.

DetectColor.java and LinearDetectColor.java show examples of OpModes using the camera functionality. 

To use this to make your own custom OpModes, just extend OpModeCamera or LinearOpModeCamera as shown in the examples and change the code that uses the RGB pixels to perform your own image processing functions. You can use all of the normal SDK functions as well (motors, servos, sensors, gamepads, etc.).

To install (as of 9/15/16):

* Put the for_camera_opmodes folder in the FtcRobotController java folder.
* Put the sample_camera_opmodes folder in the TeamCode java folder.
* Add the code in AndroidManifestCameraExtras.xml to your AndroidManifest.xml (in the FtcRobotController manifests folder) right above the "<application" line.
* Add the code in activity_ftc_controller_camera_extras.xml to your activity_ftc_controller.xml (in the FtcRobotController res/layout folder) right before the last < /RelativeLayout > line.
* Add the code in FtcRobotControllerActivityCameraExtras to your FtcRobotControllerActivity.java right after the variables are defined in the class. Import android.hardware.Camera in this class file also. You may need to import other class files too as suggested by Android Studio.

The files DetectColor.java and LinearDetectColor.java show how to use/extend the OpModeCamera and LinearOpModeCamera classes to access the RGB image values from the camera.  These OpModes put a camera preview window on the RobotController screen and report back to the DriverStation telemetry whether red, green, or blue is the color most seen by the camera at a given time.

Many thanks to FTC_Team5648 who provided much of the core code in this example in an FTC forum thread titled "How to use the camera as a sensor" on 9/17/15!
