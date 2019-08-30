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

public class DataCenterDetails extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_center_details);
        textView = findViewById(R.id.datacenterdetail);
        Intent intent = getIntent();
        String id = intent.getStringExtra("datacenter");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("DataCenter")
                .whereEqualTo("datacenterid", id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String nr = "Data Center ID : " + document.get("datacenterid")+"\n\nTimestamp : " + document.get("timestamp")+"\n\nTimezone : " + document.get("timezone")+"\n\nCost Storage : " + document.get("coststorage")+"\n\nCost Memory : " + document.get("costmemory")+"\n\nCost Bandwidth : " + document.get("costbandwidth");
                                textView.setText(nr);
                            }
                        }
                    }
                });
    }
}
