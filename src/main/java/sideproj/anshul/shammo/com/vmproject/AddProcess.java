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

public class AddProcess extends AppCompatActivity {
    EditText editText,editText3,editText4,editText5,editText2;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_process);
        Intent intent = getIntent();
        final String id = intent.getStringExtra("vmid");
        button = findViewById(R.id.vmtablebutton);
        editText = findViewById(R.id.vmtableeditText);
        editText2 = findViewById(R.id.vmtableeditText2);
        editText3 = findViewById(R.id.vmtableeditText3);
        editText4 = findViewById(R.id.vmtableeditText4);
        editText5 = findViewById(R.id.vmtableeditText5);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long tsLong = System.currentTimeMillis()/1000;
                String ts = tsLong.toString();
                Map<String, Object> data = new HashMap<>();
                data.put("processid", editText.getText().toString());
                data.put("timestamp", ts);
                data.put("length", editText2.getText().toString());
                data.put("filesize", editText3.getText().toString());
                data.put("pen", editText4.getText().toString());
                data.put("outputsize", editText5.getText().toString());
                data.put("vmid", id);

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("process")
                        .add(data)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getApplicationContext(),"Process Added",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}
