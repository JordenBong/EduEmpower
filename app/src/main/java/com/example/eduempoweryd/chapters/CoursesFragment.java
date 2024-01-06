package com.example.eduempoweryd.chapters;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.eduempoweryd.R;

import java.util.ArrayList;
public class CoursesFragment extends Fragment {


    // View
    private SearchView searchView;


    //Discover Course Variables
    ArrayList<DiscoverCourse> discoverCoursesArray = new ArrayList<>();
    DiscoverCourse discoverCourse;
    int[] courseImages = {R.drawable.web, R.drawable.web, R.drawable.web, R.drawable.web};

    //Ongoing Course Variables
    ArrayList<OngoingCourse> ongoingCourseArrayList = new ArrayList<>();
    OngoingCourse ongoingCourse;
    int[] oc_courseImages = {R.drawable.oc_accounting, R.drawable.oc_biology, R.drawable.oc_accounting, R.drawable.oc_biology, R.drawable.oc_accounting};

    //Completed Course Variables
    ArrayList<CompletedCourse> completedCourseArrayList = new ArrayList<>();
    CompletedCourse completedCourse;
    int[] cc_courseImages = {R.drawable.chemistry};

    public CoursesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.chapter_fragment_courses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        // Recycle View for Discover Courses
        RecyclerView recyclerView = view.findViewById(R.id.discoverCourseRecycleView);
        setUpDiscoverCourseData();
        DiscoverCourseAdapter discoverCourseAdapter = new DiscoverCourseAdapter(this.getContext(), discoverCoursesArray);
        recyclerView.setAdapter(discoverCourseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Recycle View for Ongoing Courses
        RecyclerView recyclerView1 = view.findViewById(R.id.ongoingCourseRecycleView);
        setUpOngoingCourseData();
        OngoingCourseAdapter ongoingCourseAdapter = new OngoingCourseAdapter(this.getContext(), ongoingCourseArrayList);
        recyclerView1.setAdapter(ongoingCourseAdapter);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Recycle View for Completed Courses
        RecyclerView recyclerView2 = view.findViewById(R.id.CompltetedCourseRecycleView);
        setUpCompletedCourse();
        CompletedCourseAdapter completedCourseAdapter = new CompletedCourseAdapter(this.getContext(), completedCourseArrayList);
        recyclerView2.setAdapter(completedCourseAdapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    public void setUpDiscoverCourseData(){
        String[] courseName = getResources().getStringArray(R.array.courses);
        String[] courseDesc = getResources().getStringArray(R.array.course_desc);

        for (int i=0; i<courseDesc.length; i++){
            discoverCoursesArray.add(new DiscoverCourse(courseName[i], courseDesc[i], courseImages[i]));
        }
    }

    public void setUpOngoingCourseData(){
        String[] oc_courseNames = getResources().getStringArray(R.array.oc_courseName);
        String[] oc_courseQtys = getResources().getStringArray(R.array.oc_courseQty);
        for (int i=0; i<oc_courseNames.length; i++){
            ongoingCourseArrayList.add(new OngoingCourse(oc_courseNames[i], oc_courseQtys[i], oc_courseImages[i]));
        }
    }

    public void setUpCompletedCourse(){
        String[] cc_courseNames = getResources().getStringArray(R.array.cc_courseName);
        String[] cc_courseDesc = getResources().getStringArray(R.array.cc_courseDesc);
        for (int i=0; i<cc_courseDesc.length; i++){
            completedCourseArrayList.add(new CompletedCourse(cc_courseNames[i], cc_courseDesc[i], cc_courseImages[i]));
        }
    }


}