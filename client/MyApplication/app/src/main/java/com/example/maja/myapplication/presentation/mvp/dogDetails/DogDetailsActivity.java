package com.example.maja.myapplication.presentation.mvp.dogDetails;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Dog;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DogDetailsActivity extends AppCompatActivity implements DogDetailsContact.View {

    private static final String TAG = DogDetailsActivity.class.getSimpleName();
    private AlertDialog.Builder builder;
    private DogDetailsPresenter presenter;
    private TextView dogName;
    private TextView dogBread;
    private TextView dogGender;
    private TextView dogAge;
    private Dog dog;
    private Button btnUpdate;
    private Button btnDelete;
    private Button btnShareFb;

    private CallbackManager callbackManager;
    private LoginManager manager;
    private Uri selectedImage;

    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_details);
        Log.d(TAG, "onCreate: " + getIntent().getSerializableExtra("dog"));
        dog = (Dog) getIntent().getSerializableExtra("dog");
        Log.d(TAG, "onCreate: " + dog);
        presenter = new DogDetailsPresenter(this);
        initUIComponents();
        initListener();
    }

    private void loginAndSharePost() {
        FacebookSdk.setApplicationId("354018245051548");
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        List<String> permissionNeeds = Arrays.asList("publish_actions");
        manager = LoginManager.getInstance();
        manager.logInWithPublishPermissions(this, permissionNeeds);
        manager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                sharePhotoToFacebook();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException exception) {
                Log.e(TAG, "onError: " + exception.getMessage());
            }
        });
    }

    private void sharePhotoToFacebook() {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(bitmap)
                    .setCaption("This is " + dog.getName() + " from shelter " + presenter.getShelterByShelterId(dog.getIdShelter()).getName() + ". Please adopt me !!!")
                    .build();

            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();
            ShareDialog.show(DogDetailsActivity.this,content);
            //ShareApi.share(content, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
        presenter.start();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
        presenter.stop();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
        presenter.pause();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
        presenter.resume();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
        presenter.destroy();
    }

    private void initListener() {
        btnShareFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: " + requestCode);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                selectedImage = data.getData();
                if (selectedImage != null) {
//                    try {
                    loginAndSharePost();
//                        //shareOnFb(selectedImage);
//                    } catch (IOException e) {
//                        Toast.makeText(this,"Facebook connection is not possible", Toast.LENGTH_LONG).show();
//                        e.printStackTrace();
//                    }
                }
            }
        }
        if (requestCode == 64207 || requestCode == 64206) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

//    private void shareOnFb(Uri selectedImage) throws IOException {
//        Log.d(TAG, "shareOnFb: 1");
//        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
//        Log.d(TAG, "shareOnFb: 2");
//        SharePhoto photo = new SharePhoto.Builder()
//                .setBitmap(bitmap)
//                .setCaption("This is " + dog.getName() + " from shelter " + presenter.getShelterByShelterId(dog.getIdShelter()).getName() + ". Please adopt me !!!")
//                .build();
//        SharePhotoContent content = new SharePhotoContent.Builder()
//                .addPhoto(photo)
//                .build();
//        Log.d(TAG, "shareOnFb: 3");
//        ShareDialog.show(DogDetailsActivity.this,content);
//        Log.d(TAG, "shareOnFb: 4");
//    }

    private void initUIComponents() {
        dogName = (TextView) findViewById(R.id.dogName);
        dogBread = (TextView) findViewById(R.id.dogBread);
        dogGender = (TextView) findViewById(R.id.dogGender);
        dogAge = (TextView) findViewById(R.id.dogAge);
        btnShareFb = (Button) findViewById(R.id.btnShareFb);


        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);

        dogName.setText(dog.getName());
        dogBread.setText(dog.getBread());
        int gender = dog.getGender();
        if (gender == 0) {
            dogGender.setText("Female");
        } else if (gender == 1) {
            dogGender.setText("Male");
        } else {
            dogGender.setText("Not valid");
        }
        dogAge.setText(String.valueOf(dog.getAge()));
    }
}
