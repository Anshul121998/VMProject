package sideproj.anshul.shammo.com.vmproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddDaataCenter extends AppCompatActivity {
    EditText editText,editText3,editText4,editText5,editText6;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_daata_center);
        button = findViewById(R.id.button2);
        editText = findViewById(R.id.editText);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long tsLong = System.currentTimeMillis()/1000;
                String ts = tsLong.toString();
                Map<String, Object> data = new HashMap<>();
                data.put("datacenterid", editText.getText().toString());
                data.put("timestamp", ts);
                data.put("timezone", editText3.getText().toString());
                data.put("costbandwidth", editText4.getText().toString());
                data.put("coststorage", editText5.getText().toString());
                data.put("costmemory", editText6.getText().toString());

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("DataCenter")
                        .add(data)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getApplicationContext(),"DataCenter Added",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });



    }
}
