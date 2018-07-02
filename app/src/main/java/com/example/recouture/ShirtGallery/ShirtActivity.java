
package com.example.recouture.ShirtGallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.recouture.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ShirtActivity extends AppCompatActivity {


    /*
    get the images from firebase.
    firebase storage URL -> Photos/shirts
    store a shirt class. {
                            ArrayList<String> tags;
                            ImageUri uri;
                            }
                           edittext can be separated with commas and store them into array.






     */

    private EmptyRecyclerView mRecyclerViewShirt;

    private DatabaseReference mDatabaseReference;

    private FirebaseUser firebaseUser;

    private List<Shirt> mShirtList;

    private StorageReference mStorageReference;

    private ValueEventListener mDatabaseListener;

    private ShirtAdapter shirtAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shirts);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        Toolbar toolbar = (Toolbar) findViewById(R.id.categoryToolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShirtActivity.this,"Pressed back",Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerViewShirt = findViewById(R.id.recyclerViewShirt);

        mRecyclerViewShirt.setHasFixedSize(true);
        mRecyclerViewShirt.setLayoutManager(new GridLayoutManager(ShirtActivity.this,3));
        mRecyclerViewShirt.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.HORIZONTAL));
        mRecyclerViewShirt.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        // need to set adapter


        mShirtList = new ArrayList<>();


        shirtAdapter = new ShirtAdapter(this,mShirtList);

        mRecyclerViewShirt.setEmptyView(findViewById(R.id.empty_view));

        mRecyclerViewShirt.setAdapter(shirtAdapter);

        mStorageReference = FirebaseStorage.getInstance().getReference(firebaseUser.getUid());

        mDatabaseReference = FirebaseDatabase.getInstance().getReference(firebaseUser.getUid()).child("Shirts");


        StorageReference shirtStorageReference = mStorageReference.child("Shirts");

        mDatabaseListener = mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mShirtList.clear();

                for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {
                    Shirt shirt = postSnapShot.getValue(Shirt.class);
                    shirt.setMkey(shirt.getKey());
                    mShirtList.add(shirt);
                }
                shirtAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseReference.removeEventListener(mDatabaseListener);
    }


}
