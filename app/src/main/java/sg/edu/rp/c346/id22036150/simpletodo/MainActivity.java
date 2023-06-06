package sg.edu.rp.c346.id22036150.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    Button btnAdd, btnDelete, btnClear;
    ListView tasks;
    Spinner spinner;
    ArrayList<String> taskArray;
    ArrayAdapter<String> aaTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.etDoTask);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnClear = findViewById(R.id.btnClear);
        tasks = findViewById(R.id.ltTasks);
        spinner = findViewById(R.id.spinner);

        taskArray = new ArrayList<>();

        aaTask = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,taskArray);

        tasks.setAdapter(aaTask);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("Add a new task")) {
                    etTask.setHint("Type in a new task here");
                    btnAdd.setEnabled(true);
                    btnClear.setEnabled(true);
                    btnDelete.setEnabled(false);
                } else if(selectedItem.equals("Remove a task")) {
                    etTask.setHint("Type in the index of the task to be removed");
                    btnAdd.setEnabled(false);
                    btnClear.setEnabled(true);
                    btnDelete.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etTask.getText().toString();
                taskArray.add(task);
                aaTask.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etTask.getText().toString();
                if (input.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter an index number", Toast.LENGTH_LONG).show();
                    return; // Exit the method if input is empty
                }

                int index = Integer.parseInt(input) - 1;
                if (!taskArray.isEmpty()) {
                    if (index >= 0 && index < taskArray.size()) {
                        taskArray.remove(index);
                        aaTask.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid index number", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "You don't have any tasks to remove", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!taskArray.isEmpty()) {
                    taskArray.clear();
                    aaTask.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "There are no tasks to clear", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

