package com.example.cropdoctor2;



import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Objects;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class SecondFragment extends Fragment {

    String str;
    final int STORAGE_PERMISSION_CODE =1000;
    private Button mDisplayData;
    private TextView tv;
    public Calendar cal;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    public String datetime, result;
    public String noofdays="1";
    public Date date;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View RootView = inflater.inflate(R.layout.fragment_second, container, false);
        final EditText ed1;
        ed1=RootView.findViewById(R.id.ed);
        final TextView tv1;
        Button bt;
        bt=RootView.findViewById(R.id.button);
        str=ed1.getText().toString();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                {
                    if(ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.READ_CALENDAR)== PackageManager.PERMISSION_DENIED ||
                            ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.WRITE_CALENDAR)==PackageManager.PERMISSION_DENIED
                    ){
                        String[] permission = {Manifest.permission.WRITE_CALENDAR,Manifest.permission.READ_CALENDAR};
                        requestPermissions(permission,STORAGE_PERMISSION_CODE);
                    }
                    else{
                        openCalendar(ed1.getText().toString());
                    }
                }
                else{
                    openCalendar(ed1.getText().toString());
                }
            }


        });

        mDisplayData=RootView.findViewById(R.id.b);
        tv=RootView.findViewById(R.id.textView);
        mDisplayData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal=Calendar.getInstance();
                int year =cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog =new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();}


        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month+=1;
                //Log.d(TAG,"date"+i+"/"+i1+"/"+i2);
                tv.append(" : "+day+"/"+month+"/"+year);
                mDisplayData.setEnabled(false);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm:ss a");
                datetime=simpleDateFormat.format(cal.getTime());
                tv.append("\nCurrent time is : "+datetime);
                try {
                    date=simpleDateFormat.parse(datetime);
                    cal.setTime(date);
                    cal.add(Calendar.MINUTE,parseInt(noofdays));
                    result=simpleDateFormat.format(cal.getTime());
                    tv.append("\nupdated time : "+result);
                    //tv.append("\nCurrent time is : "+datetime);
                    while(true)
                    {
                        if (datetime.compareTo(result)==0) {

                            Toast.makeText(getContext(),"time to fertilizer",Toast.LENGTH_SHORT).show();
                            break;
                        }
                        else{
                            break;
                        }

                    }
                } catch (ParseException e) {
                    tv.append("\nupdated time : "+R.string.app_name);
                }

            }
        };


        return RootView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void openCalendar(String s1) {
        if(!s1.isEmpty()){
            Intent intent=new Intent(Intent.ACTION_INSERT);
            intent.setData(CalendarContract.Events.CONTENT_URI);
            intent.putExtra(CalendarContract.Events.TITLE,s1);
            if (intent.resolveActivity(getActivity().getPackageManager())!= null) {
                startActivity(intent);
            }
            else{
                Toast.makeText(getContext(),"There is no calendar app installed here",Toast.LENGTH_SHORT).show();

            }
        }
        else if(s1.isEmpty()){
            Toast.makeText(getContext(),"Enter Event Name",Toast.LENGTH_SHORT).show();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case STORAGE_PERMISSION_CODE:{
                if(grantResults.length>0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    if(!str.isEmpty()){
                        openCalendar(str);}
                    else if(str.isEmpty()){
                        Toast.makeText(getContext(),"Enter Event Name",Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(getContext(),"Permission denied",Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}