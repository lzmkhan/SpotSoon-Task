package com.crystrom.spotsoontask;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Images/Song content can either be fetched from server or available locally
    //I am assuming it is available locally.
    ArrayList<Song> songsList = new ArrayList<Song>();


    ImageView position1, position2, position3, position4, position5;
    ViewPager mViewPager, mContentViewPager;
    TabLayout pagerTab;

    //Track title and subtitle
    TextView txtTitle, txtSubTitle;
    public static final int LEFT = 1;
    public static final int RIGHT = -1;

    //Holds the previous position of the view pager.
    private int mPreviousPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing the ImageView for updates.
        position1 = (ImageView) findViewById(R.id.first_image_indicator);
        position2 = (ImageView) findViewById(R.id.second_image_indicator);
        position3 = (ImageView) findViewById(R.id.third_image_indicator);
        position4 = (ImageView) findViewById(R.id.fourth_image_indicator);
        position5 = (ImageView) findViewById(R.id.fifth_image_indicator);


        //Initializing text views
        txtTitle = (TextView) findViewById(R.id.track_name);

        txtSubTitle = (TextView) findViewById(R.id.track_name_sub);


        //Getting the View Pager for Image slider
        mViewPager = (ViewPager) findViewById(R.id.track_view_pager);

        //Adding all the songs to arraylist
        addSongs();


        //Initially Position 0 will be displayed. Hence updating the Indicator.
        updateIndicator(0);
        //set the title and subtitle for the song.
        txtTitle.setText(songsList.get(0).getSongTitle());
        txtSubTitle.setText(songsList.get(0).getSongSubTitle());

        //Setting the custom PageAdapter
        mViewPager.setAdapter(new ImagePagerAdapter(this));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //Updating the corresponding  Circular indicators based on the position and direction
                updateIndicator(position);
                txtTitle.setText(songsList.get(position).getSongTitle());
                txtSubTitle.setText(songsList.get(position).getSongSubTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        //Getting the view Pager for content fragments
        mContentViewPager = (ViewPager) findViewById(R.id.content_view_pager);


        //Setting FragmentPageAdapter

        mContentViewPager.setAdapter(new FragmentPageAdapter(getSupportFragmentManager()));
        pagerTab = (TabLayout) findViewById(R.id.viewpager_tab);
        pagerTab.setupWithViewPager(mContentViewPager);
        LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(R.layout.custom_tab, null);
        TextView txt1 = (TextView) layout.findViewById(R.id.tabtext);
        ImageView image1 = (ImageView) layout.findViewById(R.id.tab_icon);

        txt1.setText("VIDEOS");
        txt1.setHighlightColor(Color.RED);
        image1.setBackgroundResource(R.drawable.videos_tab_icon);
        pagerTab.getTabAt(0).setCustomView(layout);


        LinearLayout layout1 = (LinearLayout) getLayoutInflater().inflate(R.layout.custom_tab, null);
        TextView txt2 = (TextView) layout1.findViewById(R.id.tabtext);
        ImageView image2 = (ImageView) layout1.findViewById(R.id.tab_icon);

        txt2.setText("IMAGES");
        image2.setBackgroundResource(R.drawable.images_tab_icon);
        pagerTab.getTabAt(1).setCustomView(layout1);

        LinearLayout layout2 = (LinearLayout) getLayoutInflater().inflate(R.layout.custom_tab, null);
        TextView txt3 = (TextView) layout2.findViewById(R.id.tabtext);
        ImageView image3 = (ImageView) layout2.findViewById(R.id.tab_icon);

        txt3.setText("MILESTONE");
        image3.setBackgroundResource(R.drawable.milestone_tab_icon);
        pagerTab.getTabAt(2).setCustomView(layout2);

    }

    /**
     * Used to store the previous positions and to calculate the direction of swipe.
     *
     * @param position is the current position of the page in view pager
     * @return
     */
    private int getDirection(int position) {
        int direction = position - mPreviousPosition;
        mPreviousPosition = position;
        return direction;
    }

    /**
     * used to update the circular indicators with respect to the current page.
     *
     * @param position is the current position of the viewpager's page
     */
    public void updateIndicator(int position) {
        int direction = getDirection(position);
        switch (position) {
            case 0:
                position1.setImageResource(R.drawable.circle_solid);
                if (direction == RIGHT)
                    position2.setImageResource(R.drawable.circle_hollow);
                break;
            case 1:
                position2.setImageResource(R.drawable.circle_solid);
                if (direction == LEFT) {
                    position1.setImageResource(R.drawable.circle_hollow);
                } else {
                    position3.setImageResource(R.drawable.circle_hollow);
                }
                break;
            case 2:
                position3.setImageResource(R.drawable.circle_solid);
                if (direction == LEFT) {
                    position2.setImageResource(R.drawable.circle_hollow);
                } else {
                    position4.setImageResource(R.drawable.circle_hollow);
                }
                break;
            case 3:
                position4.setImageResource(R.drawable.circle_solid);
                if (direction == LEFT) {
                    position3.setImageResource(R.drawable.circle_hollow);
                } else {
                    position5.setImageResource(R.drawable.circle_hollow);
                }
                break;
            case 4:
                position5.setImageResource(R.drawable.circle_solid);
                if (direction == LEFT) {
                    position4.setImageResource(R.drawable.circle_hollow);
                }
                break;

        }

    }

    /**
     * Add songs to the arraylist during onCreate()
     */

    private void addSongs() {
        songsList.add(new Song("Linkin Park - New divide", "Transformer: Revenge of the fallen", R.drawable.concert_placeholder, null));
        songsList.add(new Song("Linkin Park - Lost in the Echo", "Living Things", R.drawable.concert_placeholder_1, null));
        songsList.add(new Song("Linkin Park - Numb", "Meteora", R.drawable.concert_placeholder_2, null));
        songsList.add(new Song("Linkin Park - Heavy", "feat. Kiiara", R.drawable.concert_placeholder_3, null));
        songsList.add(new Song("Linkin Park - Battle Symphony", "Single", R.drawable.concert_placeholder_4, null));
    }


    private class ImagePagerAdapter extends PagerAdapter {
        private static final int NUM_OF_IMAGES = 5;
        private Context mContext;


        public ImagePagerAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return NUM_OF_IMAGES;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view == (RelativeLayout) object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ViewGroup rootView = (ViewGroup) ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.image_fragment, container, false);
            ImageView imageView = (ImageView) rootView.findViewById(R.id.viewpager_image);
            imageView.setBackgroundResource(songsList.get(position).getSongImageIds());

            //add the rootView to viewpager's container
            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            RelativeLayout retLayout = (RelativeLayout) object;

            //check if the relative layout has a parent, if there then you need to do removeView() on it before
            //you can proceeed to remove the child view.
            if (retLayout.getParent() == null) {
                container.removeView(retLayout);
            }
        }
    }


    private class FragmentPageAdapter extends FragmentStatePagerAdapter {

        FragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ContentFragments fragment = new ContentFragments();
            Bundle bundle = new Bundle();
            bundle.putByte("Page_Type", ContentFragments.IMAGES);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            CharSequence title;
            switch (position) {
                case 0:
                    title = "VIDEOS";
                    break;
                case 1:
                    title = "IMAGES";
                    break;
                case 2:
                    title = "MILESTONES";
                    break;
                default:
                    title = "VIDEOS";
                    break;
            }
            return title;
        }
    }


    public static class ContentFragments extends Fragment {

        RecyclerView recyclerView;
        ArrayList<Video> videoList = new ArrayList<Video>();
        public static final byte IMAGES = 0;
        public static final byte VIDEOS = 1;
        public static final byte MILESTONES = 2;


        public ContentFragments() {
            super();
        }


        /**
         * Add videos to the arrayList
         */
        private void addVideos() {
            videoList.add(new Video("Linkin Park - Iridescent", "18 Hours ago", getString(R.string.sample_text), R.drawable.concert_placeholder));
            videoList.add(new Video("Linkin Park - Castle of Glass", "24 Hours ago", getString(R.string.sample_text), R.drawable.concert_placeholder_1));
            videoList.add(new Video("Linkin Park - PaperCut", "14 Hours ago", getString(R.string.sample_text), R.drawable.concert_placeholder_2));
            videoList.add(new Video("Linkin Park - Somewhere I belong", "11 Hours ago", getString(R.string.sample_text), R.drawable.concert_placeholder_3));
            videoList.add(new Video("Linkin Park - Waiting for the end", "1 Hours ago", getString(R.string.sample_text), R.drawable.concert_placeholder_4));
            videoList.add(new Video("Linkin Park - Heavy", "30 mins ago", getString(R.string.sample_text), R.drawable.concert_placeholder));
        }


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.content_layout, container, false);

            Log.i("ContentFragment", "onCreateView");
            //add the videos to the list
            addVideos();

            recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager((getActivity())));


            //Get the type of page we want to display
            byte page_type = getArguments().getByte("Page_Type");

            //you can set different dataset for each page with different cardViews depending on the data set
            switch (page_type) {
                case IMAGES:
                    recyclerView.setAdapter(new RecyclerViewAdapter(videoList));
                    break;
                case VIDEOS:
                    recyclerView.setAdapter(new RecyclerViewAdapter(videoList));
                    break;
                case MILESTONES:
                    recyclerView.setAdapter(new RecyclerViewAdapter(videoList));
                    break;
                default:
                    recyclerView.setAdapter(new RecyclerViewAdapter(videoList));
                    break;
            }


            return rootView;
        }
    }


}
