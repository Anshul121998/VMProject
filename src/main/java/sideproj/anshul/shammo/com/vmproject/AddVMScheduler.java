package sideproj.anshul.shammo.com.vmproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddVMScheduler extends AppCompatActivity {
    EditText editText,editText3,editText4,editText5;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vmscheduler);
        Intent intent = getIntent();
        final String id = intent.getStringExtra("datacenter");
        button = findViewById(R.id.vmbutton);
        editText = findViewById(R.id.vmeditText);
        editText3 = findViewById(R.id.vmeditText2);
        editText4 = findViewById(R.id.vmeditText3);
        editText5 = findViewById(R.id.vmeditText4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long tsLong = System.currentTimeMillis()/1000;
                String ts = tsLong.toString();
                Map<String, Object> data = new HashMap<>();
                data.put("hostid", editText.getText().toString());
                data.put("timestamp", ts);
                data.put("bandwidth", editText3.getText().toString());
                data.put("totalstorage", editText4.getText().toString());
                data.put("totalram", editText5.getText().toString());
                data.put("datacenterid", id);

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("vmschedulehost")
                        .add(data)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getApplicationContext(),"VM Scheduler Added",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}
