package cs478.sp20.program1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

public class NameActivity extends AppCompatActivity {
    EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        nameEditText = findViewById(R.id.editName);
        nameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionID, KeyEvent event) {
                // nothing entered
                if (nameEditText.getText().toString().trim().length() <= 0){
                    setResult(RESULT_CANCELED);
                    finish();
                }
                else {
                    // split the name given to check individual names provided
                    String[] name = nameEditText.getText().toString().trim().split(" ");
                    String userName = nameEditText.getText().toString().trim();
                    System.out.println("UserName in Activity: " + userName);

                    // checking if there are at least a first and last name
                    if (name.length < 2){
                        Intent returnName = new Intent(NameActivity.this, MainActivity.class);
                        returnName.putExtra("userName", userName);
                        setResult(RESULT_CANCELED, returnName);
                        finish();
                    }

                    // check if letter contains non letters
                    for (String x : name){
                        char[] z = x.toCharArray();
                        for (char c : z) {
                            if(!Character.isLetter(c)) {
                                Intent returnName = new Intent(NameActivity.this, MainActivity.class);
                                returnName.putExtra("userName", userName);
                                setResult(RESULT_CANCELED, returnName);
                                finish();
                            }
                        }
                    }

                    // checking if first character is capitalized. turns out was unneeded for this project
                    /*for (String x : name) {
                        if (!Character.isUpperCase(x.charAt(0))){
                            Intent returnName = new Intent(NameActivity.this, MainActivity.class);
                            returnName.putExtra("userName", userName);
                            setResult(RESULT_CANCELED, returnName);
                            finish();
                        }

                        // checking if the rest of the characters are lower cased
                        String y = x.substring(1);
                        char[] z = y.toCharArray();

                        for (char w : z){
                            if (!Character.isLowerCase(w)){
                                Intent returnName = new Intent(NameActivity.this, MainActivity.class);
                                returnName.putExtra("userName", userName);
                                setResult(RESULT_CANCELED, returnName);
                                finish();
                            }
                        }
                    }*/
                    // name is fine, so we send it back to the main activity
                    Intent returnName = new Intent(NameActivity.this, MainActivity.class);
                    returnName.putExtra("userName", userName);
                    setResult(RESULT_OK, returnName);
                    finish();

                }
                return true;
            }
        });
    }
}