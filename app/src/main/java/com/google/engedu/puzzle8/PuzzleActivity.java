
package com.google.engedu.puzzle8;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import static android.app.Activity.RESULT_OK;


public class PuzzleActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap imageBitmap = null;
    private PuzzleBoardView boardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        // This code programmatically adds the PuzzleBoardView to the UI.
        RelativeLayout container = (RelativeLayout) findViewById(R.id.puzzle_container);
        boardView = new PuzzleBoardView(this);
        // Some setup of the view.
        boardView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        container.addView(boardView);
    }

    public void dispatchTakePictureIntent(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            boardView.initialize(imageBitmap);
        }

    }

    public void shuffleImage(View view) {
        boardView.shuffle();
    }

    public void solve(View view) {
      boardView.solve();

    }
}
