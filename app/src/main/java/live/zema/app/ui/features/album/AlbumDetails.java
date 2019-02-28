package live.zema.app.ui.features.album;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import live.zema.app.R;
import live.zema.app.ui.adapters.AlbumRecycleViewAdapter;
import live.zema.app.data.entity.Music;
import live.zema.app.signature.HeaderItem;
import live.zema.app.signature.Pair;
import live.zema.app.signature.SimpleItem;
import live.zema.app.utility.Utility;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class AlbumDetails extends AppCompatActivity {

      private boolean firstLaunch = true;
      private MediaPlayer mediaPlayer;
      private int currentIndex;
      private TextView song_title, tv_time, tv_time_duration;
      private ImageView playButton, nextButton, previousButton;
      private ProgressBar pb_loader;
      private long currentSongLength;
      private SeekBar seekBar;

      private RecyclerView recyclerView;
      private AlbumRecycleViewAdapter adapter;
      private ArrayList<SimpleItem> musics = new ArrayList<>();


      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_album_details);
            recyclerView = findViewById(R.id.recyclerViewSongs);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            Intent intent = getIntent();
            String artists = intent.getStringExtra("artists");
            String albumName = intent.getStringExtra("album_name");
            String released_date = intent.getStringExtra("released_date");
            String poster = intent.getStringExtra("poster");

            HeaderItem hi = new HeaderItem();
            hi.artist = artists;
            hi.albumName = albumName;
            hi.releasedDate = released_date;
            hi.poster = poster;

            musics.add(hi);
            HashMap<String, Pair> allSongs = (HashMap<String, Pair>) intent.getSerializableExtra("musics");
            int index = 1;
            for (HashMap.Entry<String, Pair> entry : allSongs.entrySet()) {
                  String song_index = String.valueOf(index);
                  musics.add(new Music(song_index, entry.getKey(), entry.getValue().getValOne(), entry.getValue().getValTwo()));

                  index++;
            }
//            Toast.makeText(this, allSongs.get(0), Toast.LENGTH_SHORT).show();
            adapter = new AlbumRecycleViewAdapter(musics, this, new AlbumRecycleViewAdapter.RecyclerItemClickListener() {
                  @Override
                  public void onClickListener(Music track, int position) {
                        firstLaunch = false;
                        changeSelectedSong(position);
                        prepareSong(track);
                  }
            });
            recyclerView.setAdapter(adapter);

            initializeViews();

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                  @Override
                  public void onPrepared(MediaPlayer mp) {
                        togglePlay(mp);
                  }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                  @Override
                  public void onCompletion(MediaPlayer mp) {

                        int nextIndex = currentIndex + 1;

                        if (nextIndex < musics.size()) {
                              playNextMusic(nextIndex);
                        } else {
                              playNextMusic(1);
                        }
                  }
            });

            seekbarHandler();
            pressPlayButton();
            pressPreviousButton();
            PressNextButton();
      }

      private void playNextMusic(int position) {
            Music next = (Music) musics.get(position);
            changeSelectedSong(position);
            prepareSong(next);
      }

      private void seekbarHandler() {
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                  @Override
                  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (mediaPlayer != null && fromUser) {
                              mediaPlayer.seekTo(progress * 1000);
                        }
                  }

                  @Override
                  public void onStartTrackingTouch(SeekBar seekBar) {

                  }

                  @Override
                  public void onStopTrackingTouch(SeekBar seekBar) {

                  }
            });
      }

      private void prepareSong(Music Music) {
            currentSongLength = Music.getSong_duration();
            pb_loader.setVisibility(View.VISIBLE);
            song_title.setVisibility(View.GONE);
            playButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.selector_play));
            song_title.setText(Music.getSongName());
            tv_time.setText(Utility.convertDuration(Music.getSong_duration()));
            String stream = Music.getSongLocation();
            mediaPlayer.reset();

            try {
                  mediaPlayer.setDataSource(stream);
                  mediaPlayer.prepareAsync();
            } catch (IOException e) {
                  e.printStackTrace();
            }
      }

      private void togglePlay(MediaPlayer mp) {

            if (mp.isPlaying()) {
                  mp.stop();
                  mp.reset();
            } else {
                  pb_loader.setVisibility(View.GONE);
                  song_title.setVisibility(View.VISIBLE);
                  mp.start();
                  playButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.selector_pause));
                  final Handler mHandler = new Handler();
                  this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                              seekBar.setMax((int) currentSongLength / 1000);
                              int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                              seekBar.setProgress(mCurrentPosition);
                              tv_time.setText(Utility.convertDuration((long) mediaPlayer.getCurrentPosition()));
                              tv_time_duration.setText(Utility.convertDuration(currentSongLength));
                              mHandler.postDelayed(this, 1000);
                        }
                  });
            }

      }

      private void initializeViews() {
            song_title = (TextView) findViewById(R.id.song_title);
            playButton = (ImageView) findViewById(R.id.iv_play);
            nextButton = (ImageView) findViewById(R.id.iv_next);
            previousButton = (ImageView) findViewById(R.id.iv_previous);
            pb_loader = (ProgressBar) findViewById(R.id.pb_loader);
            seekBar = (SeekBar) findViewById(R.id.seekbar);
            tv_time = (TextView) findViewById(R.id.tv_time);
            tv_time_duration = (TextView) findViewById(R.id.tv_time_duration);

      }

      private void changeSelectedSong(int index) {
            adapter.notifyItemChanged(adapter.getSelectedPosition());
            currentIndex = index;
            adapter.setSelectedPosition(currentIndex);
            adapter.notifyItemChanged(currentIndex);
      }

      private void pressPlayButton() {
            playButton.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                        if (mediaPlayer.isPlaying() && mediaPlayer != null) {
                              playButton.setImageDrawable(ContextCompat.getDrawable(AlbumDetails.this, R.drawable.selector_play));
                              mediaPlayer.pause();
                        } else {
//                              Iterator
                              if (firstLaunch) {
                                    Music music = (Music) musics.get(1);
                                    changeSelectedSong(1);
                                    prepareSong(music);
                              } else {
                                    mediaPlayer.start();
                                    firstLaunch = false;
                              }
                              playButton.setImageDrawable(ContextCompat.getDrawable(AlbumDetails.this, R.drawable.selector_pause));
                        }

                  }
            });
      }

      private void pressPreviousButton() {

            previousButton.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        firstLaunch = false;
                        if (mediaPlayer != null) {

                              if (currentIndex - 1 > 0) {
                                    playNextMusic(currentIndex - 1);
                              } else {
                                    changeSelectedSong(musics.size() - 1);
                                    prepareSong((Music) musics.get(musics.size() - 1));
                              }

                        }
                  }
            });

      }

      private void PressNextButton() {
            nextButton.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        firstLaunch = false;
                        if (mediaPlayer != null) {
                              if (currentIndex + 1 < musics.size()) {
                                    playNextMusic(currentIndex + 1);
                              } else {
                                    int index = 1;
                                    changeSelectedSong(index);
                                    prepareSong((Music) musics.get(index));
                              }

                        }
                  }
            });

      }

      @Override
      protected void onDestroy() {
            if (mediaPlayer != null) {
                  mediaPlayer.release();
            }
            super.onDestroy();
      }
}
