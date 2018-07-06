package ir.polimi.booking;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int FRAGMENT_COUNT = 9;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mViewPager.getCurrentItem() + 1 == FRAGMENT_COUNT) {
                    Snackbar.make(view, "Done", Snackbar.LENGTH_LONG)
                            .setAction("Finish", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    finish();
                                }
                            }).show();
                } else {
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1 ,true);
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            int pageNumber = getArguments().getInt(ARG_SECTION_NUMBER);
            View rootView;

            Random random = new Random();

            switch (pageNumber) {
                case 1:
                    rootView = inflater.inflate(R.layout.fragment_choose_type, container, false);

                break;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_how_many, container, false);
                    break;
                case 3:
                    rootView = inflater.inflate(R.layout.fragment_choose_scenery, container, false);
                    break;
                case 4:
                    rootView = inflater.inflate(R.layout.fragment_have_meal, container, false);
                    break;
                case 5:
                    rootView = inflater.inflate(R.layout.fragment_choose_transportation, container, false);
                    break;
                case 6:
                    rootView = inflater.inflate(R.layout.fragment_first_time, container, false);
                    break;
                case 7:
                    rootView = inflater.inflate(R.layout.fragment_vip_ticket, container, false);
                    break;
                case 8:
                    rootView = inflater.inflate(R.layout.fragment_out_calendar, container, false);
                    final CalendarView calendarView = rootView.findViewById(R.id.calendarView);
                    final Spinner spinner1 = rootView.findViewById(R.id.spinner);
                    final List<String> values1 = new ArrayList<>();
                    final int c1 = random.nextInt(4) + 3;
                    for (int i = 0; i < c1; i++) {
                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.add(Calendar.DAY_OF_MONTH, random.nextInt(10));
                        values1.add(new SimpleDateFormat("MM/dd/yyyy").format(calendar1.getTime()));
                    }
                    values1.add("choose other");
                    spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == values1.size()-1) {
                                calendarView.setVisibility(View.VISIBLE);
                            } else {
                                calendarView.setVisibility(View.INVISIBLE);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            calendarView.setVisibility(View.INVISIBLE);
                        }
                    });
                    SpinnerAdapter adapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, values1);
                    spinner1.setAdapter(adapter1);
                    break;
                case 9:
                    rootView = inflater.inflate(R.layout.fragment_out_time, container, false);
                    final TimePicker timePicker = rootView.findViewById(R.id.timePicker);
                    final Spinner spinner2 = rootView.findViewById(R.id.spinner);
                    final List<String> values2 = new ArrayList<>();
                    final int c2 = random.nextInt(4) + 3;
                    for (int i = 0; i < c2; i++) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.MINUTE, random.nextInt(400));
                        values2.add(new SimpleDateFormat("hh:mm").format(calendar.getTime()));
                    }
                    values2.add("choose other");
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == values2.size()-1) {
                                timePicker.setVisibility(View.VISIBLE);
                            } else {
                                timePicker.setVisibility(View.INVISIBLE);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            timePicker.setVisibility(View.INVISIBLE);
                        }
                    });
                    SpinnerAdapter adapter2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, values2);
                    spinner2.setAdapter(adapter2);
                    break;
                default:
                    rootView = inflater.inflate(R.layout.fragment_main, container, false);
                    TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                    textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            }

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 9 total pages.
            return FRAGMENT_COUNT;
        }
    }
}
