package sideproj.anshul.shammo.com.vmproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class VMTableDetails extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vmtable_details);
        textView = findViewById(R.id.vmtabledetail);
        Intent intent = getIntent();
        String id = intent.getStringExtra("vmid");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("vmtable")
                .whereEqualTo("vmid", id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String nr = "Host ID : " + document.get("hostid")+"\n\nTimestamp : " + document.get("timestamp")+"\n\nVM ID : " + document.get("vmid")+"\n\nMemory : " + document.get("memory")+"\n\nRAM : " + document.get("ram") + "\n\nMIPS : " + document.get("mips") + "\n\nPess Number : " + document.get("pessnumber") + "\n\nBandwidth : " + document.get("bandwidth") + "\n\nTimezone : " + document.get("timezone") + "\n\nIn_use : " + document.get("in_use");
                                textView.setText(nr);
                            }
                        }
                    }
                });
    }
}
