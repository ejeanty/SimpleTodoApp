package CodePath.com.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import CodePath.com.Fragments.Edition;
import CodePath.com.R;

public class MainActivity extends AppCompatActivity {


    ListView listView;
    EditText input;
    Button validationButton;
    ArrayAdapter<String> mAdaper;
    ArrayList<String> mytasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listTask);
        input = (EditText) findViewById(R.id.input);
        validationButton = (Button) findViewById(R.id.validation_button);
        mytasks = new ArrayList<>();
        mAdaper = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mytasks);

        listView.setAdapter(mAdaper);

        validationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(input.getText())){
                    mytasks.add(input.getText().toString());
                    mAdaper.notifyDataSetChanged();
                    input.setText("");
                }else{
                    Toast.makeText(MainActivity.this, "Champs vide", Toast.LENGTH_SHORT).show();
                }
            }
        });




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Edition edition = Edition.newInstance(mytasks.get(position), position);
                edition.show(getSupportFragmentManager(), "EDITION");
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mytasks.remove(position);
                mAdaper.notifyDataSetChanged();
                return false;
            }
        });

    }


    public void onFinishEditing(String newText, Integer position){
        mytasks.set(position, newText);
        mAdaper.notifyDataSetChanged();
    }
}
