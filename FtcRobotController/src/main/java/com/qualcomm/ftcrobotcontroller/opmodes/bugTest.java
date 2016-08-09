package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by katiejohnson on 7/30/16.
 */
public class bugTest extends OpMode {
    // Declaring motors and servos
    DcMotor leftDrive1, leftDrive2, rightDrive1, rightDrive2, liftMotor1, liftMotor2,
            leftBrushMotor, rightBrushMotor;
    Servo leftLiftServo, rightLiftServo, rightAutoServo, leftAutoServo, brakeServo,
            rightDebrisServo, leftDebrisServo, rightZipServo, leftZipServo, toggleServo,
            rightFenderServo, leftFenderServo;

    float xValue; // X Value of joystick
    float yValue; // Y value of joystick
    float liftDeadband = 0.1F; // Deadband for trigger which moves lift
    // Servo positions
    double rightLiftUp = 0.0;
    double rightLiftDown = 0.3;
    double leftLiftUp = 1.0;
    double leftLiftDown = 0.7;
    double leftAutoIn = 0.06;
    double leftAutoOut = 1.0;
    double rightAutoIn = 0.95;
    double rightAutoOut = 0.0;
    double leftDebrisOut = 0.51;
    double leftDebrisIn = 0.424;
    double rightDebrisOut = 0.914;
    double rightDebrisIn = 1.0;
    double releaseLift = 1.0;
    double brakeLift = 0.824;
    double rightZipIn = 0.8;
    double rightZipOut = 0.1;
    double leftZipIn = 0.2;
    double toggleClose = .45;
    double toggleOpen = .55;
    double leftFenderDown = 0.5;
    double rightFenderDown = 0.68;
    double leftFenderUp = 1.0;
    double rightFenderUp = 0.1;
    // Booleans to decide which action to perform
    boolean leftDebrisScore = false;
    boolean rightDebrisScore = false;
    boolean negateMotor = false;
    boolean lockBrake = false;
    boolean brushForward = false;
    boolean brushReverse = false;
    boolean zipOut = false;
    boolean autoOut = false;
    boolean fendersDown = true;
    boolean liftLowerLimit = true;
    // Previous value from gamepad buttons
    boolean lastX = false;
    boolean lastA = false;
    boolean lastB = false;
    boolean lastLeftDpad = false;
    boolean lastRightDpad = false;
    boolean lastGuide = false;
    boolean lastLeftTrigger = false;
    boolean lastLeftBumper = false;
    boolean lastLeftJoystick = false;
    boolean lastBack = false;

    boolean guide = false;
    boolean leftDpad = false;
    boolean rightDpad = false;
    boolean leftTrigger = false;
    boolean leftBumper = false;
    boolean aButton = false;
    boolean bButton = false;
    boolean leftJoystick = false;
    float rightTrigger = 0.0F;

    public void init() // Code to run during initialization
    {
        // Get references to hardware
        leftDrive1 = hardwareMap.dcMotor.get("leftDrive1");
        leftDrive2 = hardwareMap.dcMotor.get("leftDrive2");
        rightDrive1 = hardwareMap.dcMotor.get("rightDrive1");
        rightDrive2 = hardwareMap.dcMotor.get("rightDrive2");
        liftMotor1 = hardwareMap.dcMotor.get("liftMotor1");
        liftMotor2 = hardwareMap.dcMotor.get("liftMotor2");
        leftBrushMotor = hardwareMap.dcMotor.get("leftBrushMotor");
        rightBrushMotor = hardwareMap.dcMotor.get("rightBrushMotor");
        leftLiftServo = hardwareMap.servo.get("leftLiftServo");
        rightLiftServo = hardwareMap.servo.get("rightLiftServo");
        leftAutoServo = hardwareMap.servo.get("leftAutoServo");
        rightAutoServo = hardwareMap.servo.get("rightAutoServo");
        brakeServo = hardwareMap.servo.get("brakeServo");
        leftDebrisServo = hardwareMap.servo.get("leftDebrisServo");
        rightDebrisServo = hardwareMap.servo.get("rightDebrisServo");
        rightZipServo = hardwareMap.servo.get("rightZipServo");
        leftZipServo = hardwareMap.servo.get("leftZipServo");
        toggleServo = hardwareMap.servo.get("toggleServo");
        leftFenderServo = hardwareMap.servo.get("leftFenderServo");
        rightFenderServo = hardwareMap.servo.get("rightFenderServo");

        // Reverse right drive motors and left brush motor
        rightDrive1.setDirection(DcMotor.Direction.REVERSE);
        rightDrive2.setDirection(DcMotor.Direction.REVERSE);
        leftBrushMotor.setDirection(DcMotor.Direction.REVERSE);

        // Also use encoders on one brush motor (for accurate low speeds)
        rightBrushMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        // Initialize servos
        brakeServo.setPosition(releaseLift);
        rightZipServo.setPosition(rightZipIn);
        leftZipServo.setPosition(leftZipIn);
        leftDebrisServo.setPosition(leftDebrisIn);
        rightDebrisServo.setPosition(rightDebrisIn);
        leftLiftServo.setPosition(leftLiftUp);
        rightLiftServo.setPosition(rightLiftUp);
        rightAutoServo.setPosition(rightAutoIn);
        leftAutoServo.setPosition(leftAutoIn);
        toggleServo.setPosition(toggleClose);
        rightFenderServo.setPosition(rightFenderDown);
        leftFenderServo.setPosition(leftFenderDown);
    }


    public void loop() {
        // If right bumper pressed, raise lift
        if (gamepad1.right_bumper) {
            liftMotor1.setPower(0.5);
            liftMotor2.setPower(0.5);
        } else if (gamepad1.right_trigger >= liftDeadband) { // if right trigger pressed, lower lift
            liftMotor1.setPower(-0.5);
            liftMotor2.setPower(-0.5);
        } else {
            liftMotor1.setPower(0.0);
            liftMotor2.setPower(0.0);
        }

        leftLiftServo.setPosition(0.8);


        // If left bumper bumped, brush forward
        if(gamepad1.left_bumper) {
            leftBrushMotor.setPower(0.25);
            rightBrushMotor.setPower(0.25);
        } else if (gamepad1.left_trigger >= liftDeadband) { // If left trigger bumped, brush backward
            leftBrushMotor.setPower(-0.25);
            rightBrushMotor.setPower(-0.25);
        } else {
            leftBrushMotor.setPower(0.0);
            rightBrushMotor.setPower(0.0);
        }
    }
}
