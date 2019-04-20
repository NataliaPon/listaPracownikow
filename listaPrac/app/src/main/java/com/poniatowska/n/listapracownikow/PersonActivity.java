package com.poniatowska.n.listapracownikow;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PersonActivity extends AppCompatActivity {

    private Button button;
    private TextView id,name,salary,age,image;
    Person p =null;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        context =this;

        button = findViewById(R.id.button);
        id = findViewById(R.id.textView3);
        name = findViewById(R.id.textView4);
        salary = findViewById(R.id.textView5);
        age = findViewById(R.id.textView6);
        image = findViewById(R.id.textView7);

        if(getIntent().getExtras() !=null) {
            p = (Person) getIntent().getSerializableExtra("p");
        }

        if(p!=null) {
            id.setText("ID: "+p.getId());
            name.setText("Name: "+p.getEmployeeName());
            salary.setText("Salary: "+p.getEmployeeSalary());
            age.setText("Age: "+p.getEmployeeAge());
            image.setText("Image: "+p.getProfileImage());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}
