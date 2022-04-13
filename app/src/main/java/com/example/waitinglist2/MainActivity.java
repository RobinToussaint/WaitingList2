package com.example.waitinglist2;

import android.app.Dialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.waitinglist2.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputLayout;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    String[] course = {"Graduate", "4th Year", "3th Year","2th Year", "1st Year"};
    RecyclerView studentListRV;
    StudentAdapter studentAdapter;
    List<Student>studentList = newArrayList<>();
    FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });
    }

    public void showDialog(final boolean isInsert, Student student, final int position) {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.add_or_edit);
        if(inInsert) dialog.setTitle("Add Candidate");
        else dialog.setTitle("Edit Candidate");
        final TextInputLayout name = dialog.findViewById(R.id.name);
        final TextInputLayout rollNumber = dialog.findViewById(R.id.roll_no);
        final Spinner courseSpinner = dialog.findViewById(R.id.cource);
        Button submit = dialog.findViewById(R.id.submit);
    }

    @Override
    public void onClick(View v) {
        if (validateUI(name, rollNumber)) {
            Student student = new Student(name.getEditText().getText().toString(), rollNumber.getEditText().getText().toString(), courseSpinner.getSelectedItem().toString(), courseSpinner.getSelectedItemPosition());
            if (isInsert) studentList.add(student);
            else {
                studentList.get(position).name = student.name;
                studentList.get(position).rollNumber = student.rollNumber;
                studentList.get(position).course = student.course;
                studentList.get(position).priority = student.priority;
            }
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}