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

public class VMSchedulerDetails extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vmscheduler_details);
        textView = findViewById(R.id.vmdetail);
        Intent intent = getIntent();
        String id = intent.getStringExtra("hostid");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("vmschedulehost")
                .whereEqualTo("hostid", id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String nr = "Data Center ID : " + document.get("datacenterid")+"\n\nTimestamp : " + document.get("timestamp")+"\n\nHost ID : " + document.get("hostid")+"\n\nBandwidth : " + document.get("bandwidth")+"\n\nTotal RAM : " + document.get("totalram")+"\n\nTotal Storage : " + document.get("totalstorage");
                                textView.setText(nr);
                            }
                        }
                    }
                });
    }
}
