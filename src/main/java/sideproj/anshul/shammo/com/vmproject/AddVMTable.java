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

public class AddVMTable extends AppCompatActivity {
    EditText editText,editText3,editText4,editText5,editText2,editText6,editText7,editText8;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vmtable);
        Intent intent = getIntent();
        final String id = intent.getStringExtra("hostid");
        button = findViewById(R.id.vmtablebutton);
        editText = findViewById(R.id.vmtableeditText);
        editText2 = findViewById(R.id.vmtableeditText2);
        editText3 = findViewById(R.id.vmtableeditText3);
        editText4 = findViewById(R.id.vmtableeditText4);
        editText5 = findViewById(R.id.vmtableeditText5);
        editText6 = findViewById(R.id.vmtableeditText6);
        editText7 = findViewById(R.id.vmtableeditText7);
        editText8 = findViewById(R.id.vmtableeditText8);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long tsLong = System.currentTimeMillis()/1000;
                String ts = tsLong.toString();
                Map<String, Object> data = new HashMap<>();
                data.put("vmid", editText.getText().toString());
                data.put("timestamp", ts);
                data.put("memory", editText2.getText().toString());
                data.put("ram", editText3.getText().toString());
                data.put("mips", editText4.getText().toString());
                data.put("pessnumber", editText5.getText().toString());
                data.put("bandwidth", editText6.getText().toString());
                data.put("timezone", editText7.getText().toString());
                data.put("in_use", editText8.getText().toString());
                data.put("hostid", id);

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("vmtable")
                        .add(data)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getApplicationContext(),"VM Table Added",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


    }
}
