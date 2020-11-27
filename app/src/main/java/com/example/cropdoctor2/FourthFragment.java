package com.example.cropdoctor2;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FourthFragment extends Fragment {
    private List<buisness> buisnessList = new ArrayList<>();
    private RecyclerView recyclerView;
    DatabaseReference databaseArtists;
    private buisnessadapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_fourth, container, false);
        //Toast.makeText(getContext(),"here1",Toast.LENGTH_LONG).show();
        databaseArtists = FirebaseDatabase.getInstance().getReference().child("Side Buisness");
        recyclerView = (RecyclerView)RootView. findViewById(R.id.rv);
       // Toast.makeText(getContext(),"here2",Toast.LENGTH_LONG).show();
        mAdapter = new buisnessadapter(buisnessList);

        recyclerView.setHasFixedSize(true);

        // vertical RecyclerView
        // keep movie_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        // horizontal RecyclerView
        // keep movie_list_row.xml width to `wrap_content`
        // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(mLayoutManager);
        Toast.makeText(getContext(),"here3",Toast.LENGTH_LONG).show();
        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 16));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Toast.makeText(getContext(),"here4",Toast.LENGTH_LONG).show();
        recyclerView.setAdapter(mAdapter);
        Toast.makeText(getContext(),"here5",Toast.LENGTH_LONG).show();
       // adddata();
        prepareMovieData();

        return inflater.inflate(R.layout.fragment_fourth, container, false);

    }
    public void adddata(){
        Toast.makeText(getContext(),"here6",Toast.LENGTH_LONG).show();
        buisness movie = new buisness("Milk Centre", "\"Rearing cows and buffalos are very common in villages. There are so many dairy farms which demand milk in huge quantity. They take the milk from the milk centres. These milk centres collect milk from the villagers. To start the milking centre you would have to contact a dairy farm and do a tie-up with them. You need to have a proper place where you can keep the weighing machine and the machine to measure the quality of milk fat and other things. You need some basic qualifications as you would have to maintain accounts register and do some basic calculation. You can, however, use billing software as well. You would have to maintain cleanliness with the milk carrier so that the milk doesn’t get spoiled. Maintain good relations" +
                " with your customers, pay them on time so that everyone comes to your centre.\"", "https://mahasilk.maharashtra.gov.in/en/state_go...");
        String id = databaseArtists.push().getKey();
        databaseArtists.child(id).setValue(movie);

         movie = new buisness("Organic Farm Green House", "The increased demand for organically grown farm products has led to the growth of this agricultural business. As there are many health risks in the foods grown with chemicals " +
                 "and fertilizers, people are growing organic food.", "https://mahasilk.maharashtra.gov.in/en/state_govt_schemes");
         id = databaseArtists.push().getKey();
        databaseArtists.child(id).setValue(movie);

        movie = new buisness("Threshing machine", "If you have enough capital then you can invest it in getting a tractor. Along with it, you can buy a threshing machine, seed drill machine to start with. Not everyone keeps it with themselves. They usually rent it.  It will be a" +
                " very profitable business as this is always required in agriculture. In every season before and after harvest, these are required. " +
                " If you have these you can even keep the machine which is used to water the fields(pumping set, boring). In this way, you would have all the required things with you and people" +
                " would not have to go to others for different requirements.", "https://mahasilk.maharashtra.gov.in/en/state_govt_schemes");
        id = databaseArtists.push().getKey();
        databaseArtists.child(id).setValue(movie);

        movie = new buisness("Poultry farm", "Opening a poultry farm doesn’t require much land. You on your own or with the help of some others can start this business very easily. You would have to rear small chicken up to certain wait and then you can sell then off.  To begin with, you can start this business on a contract basis with a vendor. In this you would be provided with the chicken, their food and everything, you would just have to rear the chickens up to certain months or the weight according to their requirement. You will be paid either by the number of chickens or by weight. Mostly the payment is made according to chickens weight. It will not be advisable to do everything on your own in the beginning. As that would require more capital and you might even face loss as you do not have contacts and you should not take a risk in the very beginning " +
                "of your business.it would be better if you start it on a contract basis", "https://mahasilk.maharashtra.gov.in/en/state_govt_schemes");
        id = databaseArtists.push().getKey();
        databaseArtists.child(id).setValue(movie);


        movie = new buisness("Dried Flower Business ", "Are you aware that flowers are among the most profitable plants, which are producing one of the highest returns of any speciality crop? Well yes, its true! Flower production is one of the fastest-growing crop trends in today’s agriculture. It requires all types of flowers especially unique and hard to grow varieties. Growing, processing and selling dried flowers could put you on the path of making money with a sustainable business from the very first year. Therefore, it’s one of" +
                " the most profitable agriculture business idea that you can opt.", "https://mahasilk.maharashtra.gov.in/en/state_govt_schemes");
        id = databaseArtists.push().getKey();
        databaseArtists.child(id).setValue(movie);

    }
    private void prepareMovieData() {

        databaseArtists.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        buisness l=npsnapshot.getValue(buisness.class);
                        Toast.makeText(getContext(),l.getTitle(),Toast.LENGTH_LONG).show();
                        buisnessList.add(l);
                    }
                    buisnessadapter adapter=new buisnessadapter(buisnessList);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }
}