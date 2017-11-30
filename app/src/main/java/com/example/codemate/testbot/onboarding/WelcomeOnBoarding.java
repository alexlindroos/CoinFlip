package com.example.codemate.testbot.onboarding;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.codemate.testbot.MainActivity;
import com.example.codemate.testbot.R;
import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;

import java.util.ArrayList;

/**
 * Created by Alex Lindroos on 29/11/2017.
 */

//OnBoarding class

public class WelcomeOnBoarding extends AppCompatActivity {

        private android.support.v4.app.FragmentManager fragmentManager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.welcome_fragments_layout);
            //Setup the Onboarding fragment
            fragmentManager = getSupportFragmentManager();
            final PaperOnboardingFragment onBoardingFragment = PaperOnboardingFragment.newInstance(getDataForOnboarding());

            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, onBoardingFragment);
            fragmentTransaction.commit();
            //Listener for the onboarding last page, when it is swiped to right go to main activity
            onBoardingFragment.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
                @Override
                public void onRightOut() {
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                    if(fragment != null)
                        fragmentManager.beginTransaction().remove(fragment).commit();
                    goToMainActivity();
                }
            });
        }
//Function to go to main activity
        private void goToMainActivity() {
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
//Function to prepare onboarding arraylist with PaperOnBoarding objects
        private ArrayList<PaperOnboardingPage> getDataForOnboarding() {
            // prepare data
            PaperOnboardingPage scr1 = new PaperOnboardingPage("Create an account.",
                    "Create an account if you are a jobseeker or employer looking for employees.",
                    Color.parseColor("#9B90BC"),
                    R.drawable.ic_person_add_black_24dp,
                    R.drawable.ic_person_add_black_24dp);
            PaperOnboardingPage scr2 = new PaperOnboardingPage("Build your CV.",
                    "Build your CV with the Chatbot.",
                    Color.parseColor("#9B90BC"),
                    R.drawable.ic_contact_mail_black_24dp,
                    R.drawable.ic_contact_mail_black_24dp);
            PaperOnboardingPage scr3 = new PaperOnboardingPage("Find the right people.",
                    "Find your new employers and employees.",
                    Color.parseColor("#9B90BC"),
                    R.drawable.ic_sentiment_satisfied_black_24dp,
                    R.drawable.ic_sentiment_satisfied_black_24dp);

            ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
            elements.add(scr1);
            elements.add(scr2);
            elements.add(scr3);
            return elements;
        }
    }
