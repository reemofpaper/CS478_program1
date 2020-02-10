package cs478.sp20.program1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button enterName;
    Button verifyName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterName = findViewById(R.id.name);
        enterName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // sending the user to the enter address activity
                Intent i = new Intent(MainActivity.this, NameActivity.class);
                startActivityForResult(i, 1);
            }
        });

        // setup and listener for map button. No info should have been entered yet, so default to toast message.
        verifyName = findViewById(R.id.verify);
        verifyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "You did not enter a name. Try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        verifyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (requestCode == 1) {
                    if (resultCode == RESULT_CANCELED) {
                        String givenName = data.getExtras().getString("userName");
                        Toast.makeText(getApplicationContext(), givenName + " is not a valid name. Try again.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        String givenName = data.getExtras().getString("userName");
                        Intent editContact = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
                        editContact.putExtra(ContactsContract.Intents.Insert.NAME, givenName);
                        startActivity(editContact);
                    }
                }
            }
        });
    }
}