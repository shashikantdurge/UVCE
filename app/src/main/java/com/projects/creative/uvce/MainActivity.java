package com.projects.creative.uvce;

import android.content.Context;
import android.support.design.widget.TabLayout;
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
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    public static Spinner branchSpn,semLabSpn,subjectSpn,syllabusSpn;
    public static int syllabusPos=0;
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

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



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
        ArrayAdapter<CharSequence> sem_labAdapter,subjectAdapter;
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

        public void initialize(View rootView){
            branchSpn=(Spinner)rootView.findViewById(R.id.branch_spinner);
            semLabSpn=(Spinner)rootView.findViewById(R.id.sem_lab_spinner);
            subjectSpn=(Spinner)rootView.findViewById(R.id.subjects_spinner);
            syllabusSpn=(Spinner)rootView.findViewById(R.id.syllabus_spinner);
            sem_labAdapter=ArrayAdapter.createFromResource(rootView.getContext(),R.array.semester,android.R.layout.simple_spinner_dropdown_item);
            subjectAdapter=ArrayAdapter.createFromResource(rootView.getContext(),R.array.all1,android.R.layout.simple_list_item_1);
            semLabSpn.setAdapter(sem_labAdapter);
            subjectSpn.setAdapter(subjectAdapter);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            initialize(rootView);
            Log.d("PlaceHolDer","I'm in onViewCreated");
            branchSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    setSubjects(rootView.getContext());
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            semLabSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        setSubjects(rootView.getContext());
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            syllabusSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    syllabusPos=i;
                    if(i==0){               //SYLLABUS
                        subjectSpn.setEnabled(true);
                        sem_labAdapter=ArrayAdapter.createFromResource(rootView.getContext(),R.array.semester,android.R.layout.simple_spinner_dropdown_item);
                    }
                    else if(i==1){          //SCHEME
                        subjectSpn.setEnabled(false);
                        sem_labAdapter=ArrayAdapter.createFromResource(rootView.getContext(),R.array.semester,android.R.layout.simple_spinner_dropdown_item);
                    }
                    else{                   //LAB MANUAL
                        subjectSpn.setEnabled(false);
                        sem_labAdapter=ArrayAdapter.createFromResource(rootView.getContext(),R.array.lab_manual,android.R.layout.simple_spinner_dropdown_item);
                    }
                    semLabSpn.setAdapter(sem_labAdapter);


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            return rootView;
        }

        void setSubjects(Context context){
            String resString;
            Log.d("PlaceHolDer","setSubjects");
            if(syllabusPos==0){
                int sem=Integer.parseInt(semLabSpn.getSelectedItem().toString().substring(0,1));
                if(sem==1){
                    Log.d("PlaceHolDer","sem 1 selected "+sem);
                    subjectAdapter=ArrayAdapter.createFromResource(context,getResourceId(context,"all1"),android.R.layout.simple_list_item_1);

                }
                else{
                    Log.d("PlaceHolDer","sem other than 1 "+sem);
                    resString=branchSpn.getSelectedItem().toString().toLowerCase().substring(0,2)+sem;
                    subjectAdapter=ArrayAdapter.createFromResource(context,getResourceId(context,resString),android.R.layout.simple_list_item_1);
                }
                subjectSpn.setAdapter(subjectAdapter);
            }

        }

        int getResourceId(Context context,String resString){
            int resId=context.getResources().getIdentifier(resString,"array",context.getPackageName());
            return resId;
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
            if(position==0)
                return MainActivity.PlaceholderFragment.newInstance(position + 1);
            else
                Log.d("MAIN ACTIVITY","Creating HomeFragment");
                return HomeFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "FAV";
                case 1:
                    return "HOME";

            }
            return null;
        }
    }
}
