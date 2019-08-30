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

public class ProcessDetail extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_detail);
        textView = findViewById(R.id.processdetail);
        Intent intent = getIntent();
        String id = intent.getStringExtra("processid");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("process")
                .whereEqualTo("processid", id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String nr = "VM ID : " + document.get("vmid")+"\n\nTimestamp : " + document.get("timestamp")+"\n\nProcess ID : " + document.get("processid")+"\n\nLength : " + document.get("length")+"\n\nFile Size : " + document.get("filesize") + "\n\nProcessor Element Number : " + document.get("pen") + "\n\nOutput Size : " + document.get("outputsize");
                                textView.setText(nr);
                            }
                        }
                    }
                });
    }
}
