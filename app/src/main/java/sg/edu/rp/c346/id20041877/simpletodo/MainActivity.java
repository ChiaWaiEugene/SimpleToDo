package sg.edu.rp.c346.id20041877.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
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
    Button btnAdd, btnClear, btnDelete;
    ListView lvList;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDelete = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);
        lvList = findViewById(R.id.listViewTaskList);
        spinner = findViewById(R.id.spinner);

        ArrayList<String> alList = new ArrayList<String>();
        ArrayAdapter aaList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alList);
        lvList.setAdapter(aaList);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        etTask.setText("");
                        etTask.setInputType(InputType.TYPE_CLASS_TEXT);
                        etTask.setHint("Type in a new task here");

                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        etTask.setText("");
                        etTask.setInputType(InputType.TYPE_CLASS_NUMBER);
                        etTask.setHint("Type in the index of the task to be removed");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etTask.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(MainActivity.this, "Input is required!", Toast.LENGTH_SHORT).show();
                }
                else {
                    String todo = etTask.getText().toString();
                    alList.add(todo);
                    aaList.notifyDataSetChanged();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isTrue = false;
                if(alList.isEmpty()==true) {
                    Toast.makeText(MainActivity.this, "You don't have any task to removed!", Toast.LENGTH_SHORT).show();
                }
                else if(etTask.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(MainActivity.this, "Index is required!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(Integer.parseInt(etTask.getText().toString()) > alList.size()-1){
                        Toast.makeText(MainActivity.this, "Wrong index number!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        for(int i = 0; i < alList.size(); i++) {
                            if(i == Integer.parseInt(etTask.getText().toString())){
                                isTrue = true;
                                break;
                            }
                            else {
                                isTrue = false;
                            }
                        }

                        if(isTrue == true) {
                            alList.remove(Integer.parseInt(etTask.getText().toString()));
                            aaList.notifyDataSetChanged();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Index is not within range!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alList.isEmpty() == true) {
                    Toast.makeText(MainActivity.this, "You don't have any task to clear!", Toast.LENGTH_SHORT).show();
                }
                else {
                    alList.clear();
                    etTask.setText("");
                    aaList.notifyDataSetChanged();
                }
            }
        });
    }
}