// Specifies that this file is part of this Jaca package
package tech.billshi.assignment1demo;

// Java imports (think about .h headers in C and C++)

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Public / Private / protected are Java keywords (called access levels)
 * Class: this is a class / object
 * MainActivity: name of the activity
 * extends Activity: parent class of this class is Activity ("inherits" Activity class)
 */
public class MainActivity extends Activity {
    // State variables
    private int clicks = 0;                 //Clicked "N" times
    private boolean isImageDisp = false;    //Is the image currently displayed

    // References to view elements
    private TextView textField;             //Reference to text field for "Clicked N times"
    private ImageView dogImageView;         //Reference to the image display widget/element

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Required code for the Activity, automatically generated by IDE
        super.onCreate(savedInstanceState);     // Calling parent onCreate method (super is a Java keyword)
        setContentView(R.layout.activity_main); // Link this Java class to the XML file (activity_main.xml under layouts)
        // This is how the Jaca code knows what it is controlling


        /**
         * Getting references to view elements
         * Get reference using the {@link #findViewById(int)} method
         * The argument is R.id.%ID_OF_THE_ELEMENT_DEFINED_IN_XML_FILE%; R.id.ELEMENT_ID is of type int
         * This method returns a View type object; all widgets (e.g. TextView, ImageView) extends (is a child of) View class
         * therefore cast the result to the appropriate class
         */
        textField = (TextView) findViewById(R.id.textField);            // Reference to text field
        dogImageView = (ImageView) findViewById(R.id.dog_image_view);   // Reference to image display


        /**
         * Retrive the saved variables to solve orientation change problems (technically called "Configuration Change", see slides)
         * This makes sure the textfield has the original message and image display state is maintained.
         */
        if (savedInstanceState != null) {
            clicks = savedInstanceState.getInt("clicks");
            isImageDisp = savedInstanceState.getBoolean("isImageDisp");
        }
        // After restoring the variable we need to set these states to the widget elements
        setTextField();
        setImage();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        /**
         * Save the two state variables so that they can be restored on Configuration Changes
         * in {@link #onCreate(Bundle)} {@see line}
         */
        outState.putInt("clicks", clicks);
        outState.putBoolean("isImageDisp", isImageDisp);
    }

    private void setTextField() {
        if (clicks > 0) {
            /**
             * {@link TextView#setText(CharSequence)}
             * sets what text is displayed in the element
             */
            textField.setText(String.format("Clicked %d times.", clicks));
        } else {
            resetTextField();
        }
    }

    private void resetTextField() {
        // R.string.no_clicks_yet is a String resource defined in main/res/values/strings
        // Think about it as a global string constant
        textField.setText(R.string.no_clicks_yet);
    }

    private void setImage() {
        if (isImageDisp) {
            /**
             * {@link View#setVisibility(int)} sets whether the element is:
             * VISIBILE, INVISIBLE (invisible but still takes up space, like transparent),
             * GONE (invisible and does not take up space, as if deleted from layout)
             */
            dogImageView.setVisibility(View.VISIBLE);
        } else {
            resetImage();
        }
    }

    private void resetImage() {
        dogImageView.setVisibility(View.INVISIBLE);
    }

    /**
     * The following methods are Callback methods from the buttons for the onClick action,
     * e.g. "on the event that the button is clicked the method is called"
     * which method correspond to which element is specified in the XML file under the onClick property/attribute
     * The method must have a function signature matching {@link android.view.View.OnClickListener#onClick(View)}
     * (public, return is void, and Takes exactly one argument of type android.view.View)
     */

    /**
     */
    public void onClickRegister(View view) {
        clicks++;
        setTextField();
    }

    public void onClickToggle(View view) {
        isImageDisp = !isImageDisp;
        setImage();
    }

    public void onClickReset(View view) {
        clicks = 0;
        isImageDisp = false;
        resetTextField();
        resetImage();
    }







}
