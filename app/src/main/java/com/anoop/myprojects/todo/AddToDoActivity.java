package com.anoop.myprojects.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.anoop.myprojects.todo.DataModels.ToDoItem;
import com.anoop.myprojects.todo.Database.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddToDoActivity extends AppCompatActivity {

    EditText title;

    TextView date,time;

    int mYear,mMonth,mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);

        title = findViewById(R.id.title);

        date = findViewById(R.id.date);
        time = findViewById(R.id.time);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDate();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTime();
            }
        });





    }

    public void showDate()
    {
        DatePickerDialog datePickerDialog = new DatePickerDialog(AddToDoActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker,
                                          int year,
                                          int Month,
                                          int day) {

                        date.setText(day + "-"+(Month+1) +"-"+year);

                    }
                },mYear,mMonth,mDay);


        datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
        datePickerDialog.show();
    }

    public  void showTime()
    {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(AddToDoActivity.this,

                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int Hour, int Min) {
                        try {
                            String _24Hr_time = Hour + ":"+Min;
                            SimpleDateFormat _24HrFormat = new SimpleDateFormat("hh:mm");
                            SimpleDateFormat _12HrFormat = new SimpleDateFormat("hh:mm a");
                            Date _24HrDate = _24HrFormat.parse(_24Hr_time);
                            time.setText(_12HrFormat.format(_24HrDate));
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                },hour,min,false);


        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();

    }

    public void AddTodo(View view) {

        String dateStr =  date.getText().toString();
        String timeStr =  time.getText().toString();
        String titleStr =  title.getText().toString();

        if(!dateStr.isEmpty() && !timeStr.isEmpty() && !titleStr.isEmpty())
        {
            ToDoItem toDoItem  = new ToDoItem();
            toDoItem.setDate(dateStr);
            toDoItem.setTime(timeStr);
            toDoItem.setTitle(titleStr);

            DatabaseHelper databaseHelper = new DatabaseHelper(AddToDoActivity.this);
            long id = databaseHelper.addToDoItem(toDoItem);

            if(id >0)
            {
                Toast.makeText(AddToDoActivity.this,
                        "ToDo item Added",
                        Toast.LENGTH_SHORT).show();

                super.onBackPressed();

            }
            else
            {
                Toast.makeText(AddToDoActivity.this,
                        "ToDo item Failed to Add",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(AddToDoActivity.this,
                    "Please give details",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
