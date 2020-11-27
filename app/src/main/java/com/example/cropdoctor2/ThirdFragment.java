package com.example.cropdoctor2;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

import static com.example.cropdoctor2.loginact.skp;


public class ThirdFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {
        ImageView imageView;
        TextView t1,t2;
        Button bt;
        GoogleApiClient googleApiClient;
        GoogleSignInOptions gso;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                View RootView = inflater.inflate(R.layout.fragment_fifth, container, false);
                imageView = (ImageView) RootView.findViewById(R.id.imageView);
                t1=RootView.findViewById(R.id.textView);
                t2=RootView.findViewById(R.id.textView2);

                bt=RootView.findViewById(R.id.button);
                gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
                //googleApiClient=new GoogleApiClient.Builder(getContext()).enableAutoManage(getActivity(),FifthFragment.this)
                googleApiClient = new GoogleApiClient.Builder(getActivity())
                        .addConnectionCallbacks(this)   // problem!
                        .addOnConnectionFailedListener(this)
                        .addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();


                bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {


                                        @Override
                                        public void onResult(@NonNull Status status) {
                                                if(status.isSuccess())
                                                {

                                                        gotoMainActivity();
                                                }
                                                else
                                                {
                                                        Toast.makeText(getActivity(),"Logout Failed",Toast.LENGTH_SHORT).show();
                                                }
                                        }
                                });
                        }
                });

                return RootView;

        }
        private void gotoMainActivity() {
                //startActivity(new Intent(getActivity(),loginact.class));
                if(skp==1){
                AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
                builder.setMessage("You haven't sign in. Do you want to sign in?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                                skp=0;
                                startActivity(new Intent(getActivity(),loginact.class));
                        }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(getActivity(),MainActivity.class));
                        }
                });
                AlertDialog alert = builder.create();
                alert.show();}
                else{
                        skp=0;
                        startActivity(new Intent(getActivity(),loginact.class));
                        //Intent intent=new Intent(getActivity(),MainActivity.class);
                        //intent.putExtra("skp",skp=1);
                }
        }

        private void handleSigninResult(GoogleSignInResult result){
                if(result.isSuccess()){
                        GoogleSignInAccount account=result.getSignInAccount();
                        t1.setText(account.getDisplayName());
                        t2.setText(account.getEmail());
                        //t3.setText(account.getId());
                        Picasso.get().load(account.getPhotoUrl()).placeholder(R.mipmap.ic_launcher).into(imageView);
                }
                else {
                        gotoMainActivity();
                }

        }

        @Override
        public void onStart() {
                super.onStart();
                if(this.googleApiClient != null){
                        this.googleApiClient.connect();
                }
                OptionalPendingResult<GoogleSignInResult> opr=Auth.GoogleSignInApi.silentSignIn(googleApiClient);
                if(opr.isDone()){
                        GoogleSignInResult result=opr.get();
                        handleSigninResult(result);
                }else{
                        opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                                @Override
                                public void onResult(@NonNull GoogleSignInResult result) {
                                        handleSigninResult(result);
                                }
                        });

                }

        }


        @Override
        public void onStop() {
                super.onStop();
                googleApiClient.disconnect();
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        }


        @Override
        public void onConnected(@Nullable Bundle bundle) {

        }

        @Override
        public void onConnectionSuspended(int i) {

        }
}