package com.group4.paladar.Fragments.views;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.group4.paladar.FireBaseLogic.DiningEventHandler;
import com.group4.paladar.FireBaseLogic.UserHelper;
import com.group4.paladar.FireBaseStructures.event.DiningEvent;
import com.group4.paladar.FireBaseStructures.event.HomePrefs;
import com.group4.paladar.MainActivity;
import com.group4.paladar.R;
import com.group4.paladar.Utils.ImageHandler;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Surface on 2016-02-26.
 */
public class EventFragment extends Fragment implements View.OnClickListener, ImageHandler.onImageGatheredCallback, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {


    boolean isMainImagechangeing = false;
    ImageView eventImageView;
    ImageView eventHomeImageView;

    Calendar myCalendar = Calendar.getInstance();

    EditText dateView;
    EditText timeView;

    DiningEvent event;

    EditText eventTitle;
    EditText eventSummary;
    EditText eventPrice;
    EditText eventSeats;
    EditText eventLength;
    EditText eventHomeAddress;

    int eventYear = 0;
    int eventMonth = 0;
    int eventDay = 0;

    int eventHour = -1;
    int eventMinute = -1;



    String eventhomeImage;
    String eventImage;


    CheckBox isDogsAllowed;
    CheckBox isCatsAllowed;
    CheckBox isChildrenAllowed;
    CheckBox isAlocholAllowed;

    CheckBox isChildrenOnLocation;
    CheckBox isDogsOnLocation;
    CheckBox isCatsOnlocation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        event = new DiningEvent();

        View root = inflater.inflate(R.layout.fragment_event, container, false);

        initImageButtons(root);

        initDateAndTimePickers(root);

        initCreateEventButton(root);

        initViews(root);

        initCheckBoxes(root);

        return root;
    }

    private void initViews(View root) {

        eventTitle = (EditText) root.findViewById(R.id.event_title_edit_text);
        eventSummary  = (EditText) root.findViewById(R.id.event_summary_edit_text);
        eventPrice  = (EditText) root.findViewById(R.id.event_price_edit_text);
        eventSeats  = (EditText) root.findViewById(R.id.event_num_seats_edit_text);
        eventLength  = (EditText) root.findViewById(R.id.event_length_edit_text);
        eventHomeAddress  = (EditText) root.findViewById(R.id.event_home_address_edit_text);
    }

    private void initCheckBoxes(View root) {

        isDogsAllowed = ((CheckBox) root.findViewById(R.id.event_isDogAllowedCheckBox));
        isDogsOnLocation = ((CheckBox) root.findViewById(R.id.event_isDogOnLocationCheckBox));

        isCatsAllowed = ((CheckBox) root.findViewById(R.id.event_isCatsAllowedCheckBox));
        isCatsOnlocation = ((CheckBox) root.findViewById(R.id.event_isCatsOnLocationCheckBox));

        isChildrenOnLocation = ((CheckBox) root.findViewById(R.id.event_isChildrenOnLocationCheckBox));
        isChildrenAllowed = ((CheckBox) root.findViewById(R.id.event_isChidlrenAllowedCheckBox));

        isAlocholAllowed = ((CheckBox) root.findViewById(R.id.event_isAlcoholAllowedCheckBox));

    }

    private void initCreateEventButton(View root) {

        Button EventCreateButton = (Button) root.findViewById(R.id.event_create_button);
        EventCreateButton.setOnClickListener(this);

    }

    private void initDateAndTimePickers(View root) {

        //setup date view click handling!
        dateView = (EditText) root.findViewById(R.id.event_date_edit_text);
        timeView = (EditText) root.findViewById(R.id.event_time_edit_text);

        dateView.setOnClickListener(this);
        timeView.setOnClickListener(this);
    }

    private void initImageButtons(View root) {

        Button eventImageButton = (Button) root.findViewById(R.id.event_event_image_button);
        Button eventHomeImageButton = (Button) root.findViewById(R.id.event_home_image_button);

        eventImageView = (ImageView) root.findViewById(R.id.event_event_imageview);
        eventHomeImageView = (ImageView) root.findViewById(R.id.event_home_image_view);

        eventImageButton.setOnClickListener(this);
        eventHomeImageButton.setOnClickListener(this);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.event_event_image_button:
                ImageHandler.getImage(this, (MainActivity) getActivity());
                isMainImagechangeing = true;
                return;

            case R.id.event_home_image_button:
                ImageHandler.getImage(this, (MainActivity) getActivity());
                isMainImagechangeing = false;
                return;

            case R.id.event_date_edit_text:
                showDateAlertDialog();
                return;
            case R.id.event_time_edit_text:
                showTimeAlertDialog();
                return;

            case R.id.event_create_button:
                createEvent();
                return;
        }


    }


    private void showTimeAlertDialog() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);

        TimePickerDialog timedialog = new TimePickerDialog(getActivity(), this, hour, minute, true);
        timedialog.setTitle("Select Time");
        timedialog.setCancelable(false);
        timedialog.show();
    }

    private void showDateAlertDialog() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        DatePickerDialog datepicker = new DatePickerDialog(getActivity(), this, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        datepicker.setTitle("Select the date");
        datepicker.show();
    }


    //a bit ugly code should have better handlig of
    @Override
    public void onImageRecieved(Bitmap bmp) {
        Bitmap backup = bmp;
        Drawable d = new BitmapDrawable(getResources(), bmp);

        String image = ImageHandler.getImageAsString(backup);
        Toast.makeText(getActivity(), "amount of words: " + image.length() + " byte", Toast.LENGTH_SHORT).show();
        if (isMainImagechangeing) {
            eventImage = image;
            eventImageView.setImageDrawable(d);
            eventImageView.invalidate();
        } else {
            eventhomeImage = image;
            eventHomeImageView.setImageDrawable(d);
            eventHomeImageView.invalidate();
        }

    }


    //called when new time has been given!
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        dateView.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear) + "/" + String.valueOf(year));

        eventYear = year;
        eventMonth = monthOfYear;
        eventDay = dayOfMonth;
    }

    //on event time changed!
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        timeView.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));

        eventHour = hourOfDay;
        eventMinute = minute;
    }

    private void createEvent() {


        if (eventTitle.getText().length() == 0) {
            Toast.makeText(getActivity(), "Please write a title", Toast.LENGTH_SHORT).show();
            return;
        } else if (eventSummary.getText().length() == 0) {
            Toast.makeText(getActivity(), "Please write a summary", Toast.LENGTH_SHORT).show();

        } else if (eventPrice.getText().length() == 0) {
            Toast.makeText(getActivity(), "Please set a price", Toast.LENGTH_SHORT).show();

        } else if (eventLength.getText().length() == 0) {
            Toast.makeText(getActivity(), "Please set a event Length", Toast.LENGTH_SHORT).show();

        } else if (eventSeats.getText().length() == 0) {
            Toast.makeText(getActivity(), "Please set a event seats", Toast.LENGTH_SHORT).show();
        } else if (eventDay == 0 || eventMonth == 0 || eventYear == 0 || eventHour == -1 || eventMinute == -1) {
            Toast.makeText(getActivity(), "Please set a event date", Toast.LENGTH_SHORT).show();
        } else if (eventHomeAddress.getText().length() == 0) {
            Toast.makeText(getActivity(), "Please enter home address for event", Toast.LENGTH_SHORT).show();
        } else if (eventhomeImage.isEmpty()) {
            Toast.makeText(getActivity(), "Please set  home image for event", Toast.LENGTH_SHORT).show();
        } else if (eventImage.isEmpty()) {
            Toast.makeText(getActivity(), "Please set event image", Toast.LENGTH_SHORT).show();
        }

        event.setTitle(eventTitle.getText().toString());
        event.setSummary(eventSummary.getText().toString());
        event.setPrice(Integer.parseInt(eventPrice.getText().toString()));
        event.setLength(Integer.parseInt(eventPrice.getText().toString()));
        event.setDate(eventYear, eventMonth, eventDay, eventHour, eventMinute);
        event.setAddress(eventHomeAddress.getText().toString());
        event.setSeats(Integer.parseInt(eventSeats.getText().toString()));

        event.setFoodImage(eventImage);
        event.setHomeImage(eventhomeImage);

        HomePrefs homepref = new HomePrefs();

        homepref.setIsDogsAllowed(isDogsAllowed.isChecked());
        homepref.setIsDogsOnLocation(isDogsOnLocation.isChecked());

        homepref.setIsCatsAllowed(isCatsAllowed.isChecked());
        homepref.setIsCatsOnLocation(isCatsOnlocation.isChecked());

        homepref.setIsChildrenAllowed(isChildrenAllowed.isChecked());
        homepref.setIsChildrenOnLocation(isChildrenOnLocation.isChecked());

        homepref.setIsAlcholAllowed(isAlocholAllowed.isChecked());

        event.setHomePrefs(homepref);

        event.setHostId(((MainActivity) getActivity()).getAuth().getUid());
        //event ready too be sent to server!

        DiningEventHandler.pushEventToServer(event);

        //change view too main view!
        ((MainActivity) getActivity()).fHandler.ChangeView(new SearchFragment());
    }
}
