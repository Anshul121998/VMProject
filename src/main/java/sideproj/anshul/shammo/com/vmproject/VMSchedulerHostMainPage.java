package sideproj.anshul.shammo.com.vmproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class VMSchedulerHostMainPage extends AppCompatActivity {
    ImageButton imageButton;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vmscheduler_host_main_page);
        Intent intent = getIntent();
        final String id = intent.getStringExtra("datacenter");
        list = new ArrayList<>();
        imageButton = findViewById(R.id.vmscheduleimageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddVMScheduler.class);
                intent.putExtra("datacenter",id);
                startActivity(intent);
            }
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("vmschedulehost")
                .whereEqualTo("datacenterid", id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                list.add(document.get("hostid").toString());
                            }
                        }
                        RecyclerView recyclerView = findViewById(R.id.vmschedulerecyclerview);
                        recyclerView.setHasFixedSize(true);
                        layoutManager = new LinearLayoutManager(getBaseContext());
                        recyclerView.setLayoutManager(layoutManager);
                        RecyclerAdapter adapter = new RecyclerAdapter(list,getBaseContext(),"vmscheduler");
                        recyclerView.setAdapter(adapter);
                    }
                });
    }
}
