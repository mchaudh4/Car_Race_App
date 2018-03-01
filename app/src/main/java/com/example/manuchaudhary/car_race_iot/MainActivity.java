package com.example.manuchaudhary.car_race_iot;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements OnTouchListener{
//public class MainActivity extends Activity implements OnTouchListener{

    LinearLayout playGround;
    ImageView image;
    private ImageView wheel;
    private double mCurrAngle = 0;
    private double mPrevAngle = 0;
    ImageView bask;
    public static final int CONTROL_RELEASED = 0;
    public static final int ACCELERATOR_PRESSED = 1;
    public static final int BREAKS_PRESSED = 2;
    public static int PLAYER_ACTION = 0;


    AccelerateDecelerateInterpolator accelerateDecelerateInterpolator;
    AccelerateInterpolator accelerateInterpolator;
    AnticipateInterpolator anticipateInterpolator;
    LinearInterpolator linearInterpolator;
    DecelerateInterpolator decelerateInterpolator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {    //This method will create everything
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        wheel=(ImageView)findViewById(R.id.imageView1);  //  Attaching the image of wheel using ID
        wheel.setOnTouchListener(this);   //   Setting the ontouch listener to Wheel



        playGround = (LinearLayout)findViewById(R.id.playground);//  providig ID to the playgorund
        image = (ImageView)findViewById(R.id.image);  // providing ID to the Image
        float x= image.getX();
        float y= image.getY();
        TextView t4= (TextView)findViewById(R.id.textViewXCoordinate);
        TextView t5= (TextView)findViewById(R.id.textViewYCoordinate);

        t4.setText(Float.toString(x));
        t5.setText(Float.toString(y));



        Button btnAccelerateDecelerateInterpolator     // Very Low Speed or Brake
                = (Button)findViewById(R.id.bDecelerateToVeryLowSpeed);
        btnAccelerateDecelerateInterpolator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareObjectAnimator1(linearInterpolator);
                            }

        });

        //  This MAKE ACCELARATOR BUTTON   "ON TOUCH"
        Button btnAccelerateInterpolator = (Button)findViewById(R.id.bAccelerateInterpolator);
        btnAccelerateInterpolator.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TextView t6= (TextView)findViewById(R.id.textViewmCurrAngle);
                int rotateAngle;
                rotateAngle= (int) mCurrAngle;
               // rotateAngle = 120;
                t6.setText(Integer.toString(rotateAngle));

                //    t6.setText(Integer.toString(rotateAngle));
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN: {
                       if (rotateAngle>= 0 && rotateAngle <= 90) {

                           // image.setRotation(image.getRotation());
                            prepareObjectAnimator7(accelerateInterpolator);

                           prepareInterpolator();
                            break;

                        }
                        if (rotateAngle > 90 && rotateAngle <= 180) {
                            //image.setRotation(image.getRotation());
                            prepareObjectAnimator8(linearInterpolator);

                            prepareInterpolator();
                            break;

                        }
                        if (rotateAngle > -179 && rotateAngle <= -90) {



                            prepareObjectAnimator9(accelerateInterpolator);

                            prepareInterpolator();
                            break;

                        }

                        if (rotateAngle > -90 && rotateAngle <= -20) {

                            prepareObjectAnimator10(linearInterpolator);

                            prepareInterpolator();
                            break;

                        }

                    }

                    case MotionEvent.ACTION_UP: {
                        prepareObjectAnimator1(decelerateInterpolator);  //to stop the car
                        prepareInterpolator();
                    //    prepareObjectAnimator2(linearInterpolator);  //to stop the car

                        break;
                    }



                }
                return true;
            }


                });



        Button btnLinearInterpolatorPosY = (Button)findViewById(R.id.bStart);// Start Button
        btnLinearInterpolatorPosY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareObjectAnimator4(linearInterpolator);
            }
        });





        prepareInterpolator();    // This command will help in preparing the interpolator
    }

    private void prepareInterpolator() {
        accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
        accelerateInterpolator = new AccelerateInterpolator();
        anticipateInterpolator = new AnticipateInterpolator();
        decelerateInterpolator = new DecelerateInterpolator();


        linearInterpolator = new LinearInterpolator();
    }


    private void animate(double fromDegrees, double toDegrees, long durationMillis) {
        final RotateAnimation rotate = new RotateAnimation((float) fromDegrees, (float) toDegrees,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(durationMillis);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        wheel.startAnimation(rotate);
        System.out.println(mCurrAngle);
    }
    private int prepareObjectAnimator1(TimeInterpolator timeInterpolator){  // TO stop the CAR// BRAKE
        //float w = (float)playGround.getWidth();
        TextView t1= (TextView)findViewById(R.id.textViewAcceleration);
        TextView t2= (TextView)findViewById(R.id.textViewVelocity);
        TextView t3= (TextView)findViewById(R.id.textViewRpm);
        int acc= 0;
        int vel= 0;
        int rpm= 150;

        t1.setText(Integer.toString(acc));
        t2.setText(Integer.toString(vel));
        t3.setText(Integer.toString(rpm));

        float x= image.getX();   //to find value of x
        float y= image.getY();  //to find value of y
        TextView t4= (TextView)findViewById(R.id.textViewXCoordinate);
        TextView t5= (TextView)findViewById(R.id.textViewYCoordinate);

        t4.setText(Float.toString(x));  ////to find value of x
        t5.setText(Float.toString(y));   // //to find value of x

        float h = (float)playGround.getHeight();
        //float propertyStart = 0f;
        float propertyEnd = -(h/100-(float)image.getHeight());
        String propertyName = "translationY";
        ObjectAnimator objectAnimator
                = ObjectAnimator.ofFloat(image, propertyName,  propertyEnd);
        objectAnimator.setDuration(2000000000);
        // objectAnimator.setRepeatCount(1);
        // objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.setInterpolator(timeInterpolator);
        objectAnimator.start();

        float w = (float)playGround.getWidth();
        //float propertyStart = 0f;
        float propertyEnd1 = -(w/100-(float)image.getHeight());
        String propertyName1 = "translationX";
        ObjectAnimator objectAnimator1
                = ObjectAnimator.ofFloat(image, propertyName1,  propertyEnd1);
        objectAnimator1.setDuration(2000000000);
        // objectAnimator.setRepeatCount(1);
        // objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator1.setInterpolator(timeInterpolator);

        objectAnimator1.start();
        return 0;

            }




    private int prepareObjectAnimator2(TimeInterpolator timeInterpolator){  // TO DECLELARATE THE CAR
        //float w = (float)playGround.getWidth();
        TextView t1= (TextView)findViewById(R.id.textViewAcceleration);
        TextView t2= (TextView)findViewById(R.id.textViewVelocity);
        TextView t3= (TextView)findViewById(R.id.textViewRpm);
        int acc= 0;
        int vel= 0;
        int rpm= 150;

        t1.setText(Integer.toString(acc));
        t2.setText(Integer.toString(vel));
        t3.setText(Integer.toString(rpm));

        float x= image.getX();   //to find value of x
        float y= image.getY();  //to find value of y
        TextView t4= (TextView)findViewById(R.id.textViewXCoordinate);
        TextView t5= (TextView)findViewById(R.id.textViewYCoordinate);

        t4.setText(Float.toString(x));  ////to find value of x
        t5.setText(Float.toString(y));   // //to find value of x

        float h = (float)playGround.getHeight();
        //float propertyStart = 0f;
        float propertyEnd = -(h/100-(float)image.getHeight());
        String propertyName = "translationY";
        ObjectAnimator objectAnimator
                = ObjectAnimator.ofFloat(image, propertyName,  propertyEnd);
        objectAnimator.setDuration(2000);
        // objectAnimator.setRepeatCount(1);
        // objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.setInterpolator(timeInterpolator);
        objectAnimator.start();

        float w = (float)playGround.getWidth();
        //float propertyStart = 0f;
        float propertyEnd1 = -(w/100-(float)image.getHeight());
        String propertyName1 = "translationX";
        ObjectAnimator objectAnimator1
                = ObjectAnimator.ofFloat(image, propertyName1,  propertyEnd1);
        objectAnimator1.setDuration(2000000000);
        // objectAnimator.setRepeatCount(1);
        // objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator1.setInterpolator(timeInterpolator);

        objectAnimator1.start();
        return 0;

    }


    private void prepareObjectAnimator4(TimeInterpolator timeInterpolator){   // Linear Interpolator// START Positiv Y Direction
        //float w = (float)playGround.getWidth();
        TextView t1= (TextView)findViewById(R.id.textViewAcceleration);
        TextView t2= (TextView)findViewById(R.id.textViewVelocity);
        TextView t3= (TextView)findViewById(R.id.textViewRpm);
        int acc= 0;
        int vel= 0;
        int rpm= 140;

        t1.setText(Integer.toString(acc));
        t2.setText(Integer.toString(vel));
        t3.setText(Integer.toString(rpm));

        float x= image.getX();   //to find value of x
        float y= image.getY();  //to find value of y
        TextView t4= (TextView)findViewById(R.id.textViewXCoordinate);
        TextView t5= (TextView)findViewById(R.id.textViewYCoordinate);

        t4.setText(Float.toString(x));  ////to find value of x
        t5.setText(Float.toString(y));   // //to find value of x

        float h = (float)playGround.getHeight();
        //float propertyStart = 0f;
        float propertyEnd = -(h - (float)image.getHeight());
        String propertyName = "translationY";
        ObjectAnimator objectAnimator
                = ObjectAnimator.ofFloat(image, propertyName, propertyEnd);
        objectAnimator.setDuration(200000000);
        // objectAnimator.setupEndValues(h);
        //  objectAnimator.setRepeatCount(1);
        //objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.setInterpolator(timeInterpolator);
        objectAnimator.start();
    }

    @Override
    public boolean onTouch(final View v, MotionEvent event) {
        final float xc = wheel.getWidth() / 2;
        final float yc = wheel.getHeight() / 2;


        final float x = event.getX();
        final float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {

             /*   imageView.setImageResources(R.drawable.steering_wheel);
                animatedcarImage.start();
                animatorSet.start(); */
                wheel.clearAnimation();
                mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                mPrevAngle = mCurrAngle;
                mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                animate(mPrevAngle, mCurrAngle, 0);
                System.out.println(mCurrAngle);

                TextView t7= (TextView)findViewById(R.id.textViewSteeringBlockAngle);
                int rotateAngle;
                rotateAngle= (int) mCurrAngle;

                    t7.setText(Integer.toString(rotateAngle));

                if (rotateAngle>=0 && rotateAngle <= 90) {
                    image.setRotation(image.getRotation()+3);
                  //  prepareObjectAnimator7(linearInterpolator);

                   // prepareInterpolator();
                    break;

                }
                if (rotateAngle> 90 && rotateAngle<=180) {
                    image.setRotation(image.getRotation()+3);
                   // prepareObjectAnimator8(linearInterpolator);

                  // prepareInterpolator();
                    break;

                }
                if (rotateAngle> -179 && rotateAngle<=-90) {
                    image.setRotation(image.getRotation()+3);
                  // prepareObjectAnimator9(linearInterpolator);

                  //  prepareInterpolator();
                    break;

                }

                if (rotateAngle> -90 && rotateAngle<=-20) {
                    image.setRotation(image.getRotation()+3);
                //   prepareObjectAnimator10(linearInterpolator);

                //    prepareInterpolator();
                    break;

                }

                break;
            }
            case MotionEvent.ACTION_UP: {
              // mPrevAngle = mCurrAngle ;

             prepareObjectAnimator1(linearInterpolator);
             //prepareObjectAnimator2(linearInterpolator);

                break;
            }
        }
        return true;
    }


    private int prepareObjectAnimator7(TimeInterpolator timeInterpolator){   // less than 30 Degree  //330 to 360
        //float w = (float)playGround.getWidth();                               //positive Y
        TextView t1= (TextView)findViewById(R.id.textViewAcceleration);
        TextView t2= (TextView)findViewById(R.id.textViewVelocity);
        TextView t3= (TextView)findViewById(R.id.textViewRpm);
        int acc= 30;
        int vel= 100;
        int rpm= 230;

        t1.setText(Integer.toString(acc));
        t2.setText(Integer.toString(vel));
        t3.setText(Integer.toString(rpm));

        float x= image.getX();   //to find value of x
        float y= image.getY();  //to find value of y
        TextView t4= (TextView)findViewById(R.id.textViewXCoordinate);
        TextView t5= (TextView)findViewById(R.id.textViewYCoordinate);

        t4.setText(Float.toString(x));  ////to find value of x
        t5.setText(Float.toString(y));   // //to find value of x


        float h = (float)playGround.getHeight();
        // float propertyStart = 0f;
        float propertyEnd = -(h - (float)image.getHeight());
        String propertyName = "translationY";
        ObjectAnimator objectAnimator
                = ObjectAnimator.ofFloat(image, propertyName,propertyEnd);
        objectAnimator.setDuration(5000);
        //objectAnimator.setupEndValues(h);
        //  objectAnimator.setRepeatCount(1);
        // objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.setInterpolator(timeInterpolator);
        objectAnimator.start();
        return 0;

    }
    private int prepareObjectAnimator8(TimeInterpolator timeInterpolator){   // 30 to 150 Degree
        TextView t1= (TextView)findViewById(R.id.textViewAcceleration);
        TextView t2= (TextView)findViewById(R.id.textViewVelocity);
        TextView t3= (TextView)findViewById(R.id.textViewRpm);
        int acc= 25;
        int vel= 70;
        int rpm= 220;

        t1.setText(Integer.toString(acc));
        t2.setText(Integer.toString(vel));
        t3.setText(Integer.toString(rpm));

        float x= image.getX();   //to find value of x
        float y= image.getY();  //to find value of y
        TextView t4= (TextView)findViewById(R.id.textViewXCoordinate);
        TextView t5= (TextView)findViewById(R.id.textViewYCoordinate);

        t4.setText(Float.toString(x));  ////to find value of x
        t5.setText(Float.toString(y));   // //to find value of x


        float w = (float)playGround.getWidth();                           // positive X
        //  float h = (float)playGround.getHeight();
        // float propertyStart = 0f;
        float propertyEnd = (w - (float)image.getWidth());
        String propertyName = "translationX";
        ObjectAnimator objectAnimator
                = ObjectAnimator.ofFloat(image, propertyName,propertyEnd);
        objectAnimator.setDuration(5000);
        //objectAnimator.setupEndValues(h);
        //  objectAnimator.setRepeatCount(1);
        // objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.setInterpolator(timeInterpolator);
        objectAnimator.start();
        return 0;
    }
    private int prepareObjectAnimator9(TimeInterpolator timeInterpolator){   // // 150 to 210
        // float w = (float)playGround.getWidth();                             //Negative Y
        TextView t1= (TextView)findViewById(R.id.textViewAcceleration);
        TextView t2= (TextView)findViewById(R.id.textViewVelocity);
        TextView t3= (TextView)findViewById(R.id.textViewRpm);
        int acc= 20;
        int vel= 80;
        int rpm= 210;

        t1.setText(Integer.toString(acc));
        t2.setText(Integer.toString(vel));
        t3.setText(Integer.toString(rpm));

        float x= image.getX();   //to find value of x
        float y= image.getY();  //to find value of y
        TextView t4= (TextView)findViewById(R.id.textViewXCoordinate);
        TextView t5= (TextView)findViewById(R.id.textViewYCoordinate);

        t4.setText(Float.toString(x));  ////to find value of x
        t5.setText(Float.toString(y));   // //to find value of x


        float h = (float)playGround.getHeight();
        // float propertyStart = 0f;
        float propertyEnd = (h- (float)image.getWidth());
        String propertyName = "translationY";
        ObjectAnimator objectAnimator
                = ObjectAnimator.ofFloat(image, propertyName, propertyEnd);
        objectAnimator.setDuration(10000);
        //objectAnimator.setupEndValues(h);
        //  objectAnimator.setRepeatCount(1);
        // objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.setInterpolator(timeInterpolator);
        objectAnimator.start();
        return 0;
    }
    private int prepareObjectAnimator10(TimeInterpolator timeInterpolator){   // 150 to 330
        TextView t1= (TextView)findViewById(R.id.textViewAcceleration);
        TextView t2= (TextView)findViewById(R.id.textViewVelocity);
        TextView t3= (TextView)findViewById(R.id.textViewRpm);
        int acc= 18;
        int vel= 75;
        int rpm= 200;

        t1.setText(Integer.toString(acc));
        t2.setText(Integer.toString(vel));
        t3.setText(Integer.toString(rpm));

        float x= image.getX();   //to find value of x
        float y= image.getY();  //to find value of y
        TextView t4= (TextView)findViewById(R.id.textViewXCoordinate);
        TextView t5= (TextView)findViewById(R.id.textViewYCoordinate);

        t4.setText(Float.toString(x));  ////to find value of x
        t5.setText(Float.toString(y));   // //to find value of x

        float w = (float)playGround.getWidth();                                 //Negative X
        //  float h = (float)playGround.getHeight();

     //  float propertyStart = 80f ;

          float propertyEnd =  -(w -(float)image.getWidth());
        String propertyName = "translationX";
        ObjectAnimator objectAnimator
                = ObjectAnimator.ofFloat(image, propertyName,propertyEnd);
        objectAnimator.setDuration(10000);
        //objectAnimator.setupEndValues(h);
        //  objectAnimator.setRepeatCount(1);
        //objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.setInterpolator(timeInterpolator);
        objectAnimator.start();
        return 0;
    }



    // These FUNCTIONS BELOW WILL PRODUCE ACCELERATION
    /*private void prepareObjectAnimator11(TimeInterpolator timeInterpolator){   // less than 30 Degree positive Y
        if (mCurrAngle<=30) {
            TextView t1= (TextView)findViewById(R.id.textViewAcceleration);
            TextView t2= (TextView)findViewById(R.id.textViewVelocity);
            TextView t3= (TextView)findViewById(R.id.textViewRpm);
            int acc= 18;
            int vel= 75;
            int rpm= 150;

            t1.setText(Integer.toString(acc));
            t2.setText(Integer.toString(vel));
            t3.setText(Integer.toString(rpm));

            float x= image.getX();   //to find value of x
            float y= image.getY();  //to find value of y
            TextView t4= (TextView)findViewById(R.id.textViewXCoordinate);
            TextView t5= (TextView)findViewById(R.id.textViewYCoordinate);

            t4.setText(Float.toString(x));  ////to find value of x
            t5.setText(Float.toString(y));   // //to find value of x

            float h = (float) playGround.getHeight();
            // float propertyStart = 0f;
            float propertyEnd = -(h - (float) image.getHeight());
            String propertyName = "translationY";
            ObjectAnimator objectAnimator
                    = ObjectAnimator.ofFloat(image, propertyName,propertyEnd);
            objectAnimator.setDuration(2000);
            //objectAnimator.setupEndValues(h);
            //  objectAnimator.setRepeatCount(1);
            //   objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
            objectAnimator.setInterpolator(timeInterpolator);
            objectAnimator.start();
        }
        if(mCurrAngle>=0 && mCurrAngle <= 90){
            TextView t1= (TextView)findViewById(R.id.textViewAcceleration);
            TextView t2= (TextView)findViewById(R.id.textViewVelocity);
            TextView t3= (TextView)findViewById(R.id.textViewRpm);
            int acc= 20;
            int vel= 85;
            int rpm= 220;

            t1.setText(Integer.toString(acc));
            t2.setText(Integer.toString(vel));
            t3.setText(Integer.toString(rpm));

            float x= image.getX();   //to find value of x
            float y= image.getY();  //to find value of y
            TextView t4= (TextView)findViewById(R.id.textViewXCoordinate);
            TextView t5= (TextView)findViewById(R.id.textViewYCoordinate);

            t4.setText(Float.toString(x));  ////to find value of x
            t5.setText(Float.toString(y));   // //to find value of x

            float w = (float)playGround.getWidth();                           // positive X
            //  float h = (float)playGround.getHeight();
            // float propertyStart = 0f;
            float propertyEnd = ((w/8+w/8+w/8+w/8+w/8+w/8+w/8) - (float)image.getWidth());
            String propertyName = "translationX";
            ObjectAnimator objectAnimator
                    = ObjectAnimator.ofFloat(image, propertyName,propertyEnd);
            //  objectAnimator.setDuration(2000);
            //objectAnimator.setupEndValues(h);
            //  objectAnimator.setRepeatCount(1);
            // objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
            objectAnimator.setInterpolator(timeInterpolator);
            objectAnimator.start();

        }

        if(mCurrAngle>90 && mCurrAngle <= 180){
            TextView t1= (TextView)findViewById(R.id.textViewAcceleration);
            TextView t2= (TextView)findViewById(R.id.textViewVelocity);
            TextView t3= (TextView)findViewById(R.id.textViewRpm);
            int acc= 18;
            int vel= 80;
            int rpm= 210;

            t1.setText(Integer.toString(acc));
            t2.setText(Integer.toString(vel));
            t3.setText(Integer.toString(rpm));

            float x= image.getX();   //to find value of x
            float y= image.getY();  //to find value of y
            TextView t4= (TextView)findViewById(R.id.textViewXCoordinate);
            TextView t5= (TextView)findViewById(R.id.textViewYCoordinate);

            t4.setText(Float.toString(x));  ////to find value of x
            t5.setText(Float.toString(y));   // //to find value of x

            float h = (float)playGround.getHeight();
            // float propertyStart = 0f;
            float propertyEnd = (h- (float)image.getWidth());
            String propertyName = "translationY";
            ObjectAnimator objectAnimator
                    = ObjectAnimator.ofFloat(image, propertyName,propertyEnd);
            // objectAnimator.setDuration(2000);
            //objectAnimator.setupEndValues(h);
            //  objectAnimator.setRepeatCount(1);
            // objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
            objectAnimator.setInterpolator(timeInterpolator);
            objectAnimator.start();

        }
        if(mCurrAngle> -179 && mCurrAngle<=-90){
            TextView t1= (TextView)findViewById(R.id.textViewAcceleration);
            TextView t2= (TextView)findViewById(R.id.textViewVelocity);
            TextView t3= (TextView)findViewById(R.id.textViewRpm);
            int acc= 18;
            int vel= 75;
            int rpm= 200;

            t1.setText(Integer.toString(acc));
            t2.setText(Integer.toString(vel));
            t3.setText(Integer.toString(rpm));

            float x= image.getX();   //to find value of x
            float y= image.getY();  //to find value of y
            TextView t4= (TextView)findViewById(R.id.textViewXCoordinate);
            TextView t5= (TextView)findViewById(R.id.textViewYCoordinate);

            t4.setText(Float.toString(x));  ////to find value of x
            t5.setText(Float.toString(y));   // //to find value of x


            float w = (float)playGround.getWidth();                                 //Negative X
            // float h = (float)playGround.getHeight();
            //  float propertyStart = 0f;

            float propertyEnd =  -(w-(float)image.getWidth());
            String propertyName = "translationX";
            ObjectAnimator objectAnimator
                    = ObjectAnimator.ofFloat(image, propertyName,propertyEnd);
            // objectAnimator.setDuration(2000);
            //objectAnimator.setupEndValues(h);
            //  objectAnimator.setRepeatCount(1);
            //objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
            objectAnimator.setInterpolator(timeInterpolator);
            objectAnimator.start();

        }

        if(mCurrAngle>-90 && mCurrAngle<=-20){
            float x= image.getX();   //to find value of x
            float y= image.getY();  //to find value of y
            TextView t4= (TextView)findViewById(R.id.textViewXCoordinate);
            TextView t5= (TextView)findViewById(R.id.textViewYCoordinate);

            t4.setText(Float.toString(x));  ////to find value of x
            t5.setText(Float.toString(y));   // //to find value of x

            float h = (float) playGround.getHeight();
            // float propertyStart = 0f;
            float propertyEnd = -(h - (float) image.getHeight());
            String propertyName = "translationY";
            ObjectAnimator objectAnimator
                    = ObjectAnimator.ofFloat(image, propertyName,propertyEnd);
            //  objectAnimator.setDuration(2000);
            //objectAnimator.setupEndValues(h);
            //objectAnimator.setRepeatCount(1);
            //objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
            objectAnimator.setInterpolator(timeInterpolator);
            objectAnimator.start();

        }


    }*/







}