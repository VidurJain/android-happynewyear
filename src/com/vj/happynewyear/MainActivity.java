package com.vj.happynewyear;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class MainActivity extends Activity
{

	SoundPool soundPool;

	int newYearSound;

	private Timer customHandler = new Timer();

	private AdView adView;

	private volatile boolean played = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// initializeSoundClips();
		customHandler.scheduleAtFixedRate(updateTimerThread, 0, 1000);
		// Create the adView
		adView = new AdView(this, AdSize.BANNER, "a152b283614a4c9");

		// Initiate a generic request to load it with an ad
		adView.loadAd(new AdRequest());

	}

	private TimerTask updateTimerThread = new TimerTask()
	{
		@Override
		public void run()
		{

			runOnUiThread(new Runnable()
			{
				@Override
				public void run()
				{
					TextView countdownText = (TextView) findViewById(R.id.countdownText);
					countdownText.setText("COUNTDOWN TO " + getNextYear());
					
					Date now = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
					Date newyearDate = null;
					try
					{
						newyearDate = sdf.parse(getCurrentNewYearDate());
					}
					catch (ParseException e)
					{
						e.printStackTrace();
					}

					long diff = (long) (newyearDate.getTime() - now.getTime());

					// diff = diff - ((long) 371 * 24 * 3600 * 1000 + (long) 13 * 3600 * 1000 + (long) 52 * 60 * 1000);

					int Hours = (int) (int) ((diff / (1000 * 60 * 60)) % 24);
					int Mins = (int) ((diff / (1000 * 60)) % 60);
					int days = (int) (diff / (1000 * 60 * 60 * 24));
					int seconds = (int) ((diff / (1000)) % 60);

					TextView dayTimer = (TextView) findViewById(R.id.daytimer);
					TextView hourtimer = (TextView) findViewById(R.id.hourtimer);
					TextView minutetimer = (TextView) findViewById(R.id.minutetimer);
					TextView secondtimer = (TextView) findViewById(R.id.secondtimer);

					TextView dayText = (TextView) findViewById(R.id.days);
					TextView hoursText = (TextView) findViewById(R.id.hours);
					TextView minutesText = (TextView) findViewById(R.id.minutes);
					TextView secondsText = (TextView) findViewById(R.id.seconds);

					final RelativeLayout parent = (RelativeLayout) findViewById(R.id.parent);

					if (diff <= 0)
					{
						secondtimer.setVisibility(View.INVISIBLE);
						secondsText.setVisibility(View.INVISIBLE);
						minutesText.setVisibility(View.INVISIBLE);
						hoursText.setVisibility(View.INVISIBLE);
						dayText.setVisibility(View.INVISIBLE);
						countdownText.setVisibility(View.INVISIBLE);
						countdownText.setText("COUNTDOWN TO " + getNextYear());
						parent.setBackgroundResource(R.drawable.hny2015);
						// if (!played)
						// playSound(newYearSound);
						System.out.println("Happy New Year");
						return;
					}

					if (days > 99)
					{
						RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) dayTimer.getLayoutParams();
						lp.leftMargin = convertDpToPixel(10, parent.getContext());
						dayTimer.setLayoutParams(lp);
					}
					else
					{
						RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) dayTimer.getLayoutParams();
						lp.leftMargin = convertDpToPixel(26, parent.getContext());
						dayTimer.setLayoutParams(lp);
					}

					if (days == 0 && dayTimer.getVisibility() != View.INVISIBLE)
					{
						dayTimer.setVisibility(View.INVISIBLE);
						dayText.setVisibility(View.INVISIBLE);

						RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) hoursText.getLayoutParams();
						lp.leftMargin = convertDpToPixel(66, parent.getContext());
						hoursText.setLayoutParams(lp);

						lp = (RelativeLayout.LayoutParams) hourtimer.getLayoutParams();
						lp.leftMargin = convertDpToPixel(76, parent.getContext());
						hourtimer.setLayoutParams(lp);

						lp = (RelativeLayout.LayoutParams) minutetimer.getLayoutParams();
						lp.leftMargin = convertDpToPixel(136, parent.getContext());
						minutetimer.setLayoutParams(lp);

						lp = (RelativeLayout.LayoutParams) minutesText.getLayoutParams();
						lp.leftMargin = convertDpToPixel(126, parent.getContext());
						minutesText.setLayoutParams(lp);

						lp = (RelativeLayout.LayoutParams) secondtimer.getLayoutParams();
						lp.leftMargin = convertDpToPixel(216, parent.getContext());
						secondtimer.setLayoutParams(lp);

						lp = (RelativeLayout.LayoutParams) secondsText.getLayoutParams();
						lp.leftMargin = convertDpToPixel(206, parent.getContext());
						secondsText.setLayoutParams(lp);

						// hourtimer.setX(hoursText.getX() - 40);
						// hoursText.setX(hoursText.getX() - 40);

						// minutetimer.setX(minutetimer.getX() - 40);
						// minutesText.setX(minutesText.getX() - 40);
						// secondtimer.setX(secondtimer.getX() - 40);
						// secondsText.setX(secondsText.getX() - 40);
					}

					if (days == 0 && Hours == 0 && hourtimer.getVisibility() != View.INVISIBLE)
					{
						hourtimer.setVisibility(View.INVISIBLE);
						hoursText.setVisibility(View.INVISIBLE);
						RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) minutetimer.getLayoutParams();
						lp.leftMargin = convertDpToPixel(86, parent.getContext());
						minutetimer.setLayoutParams(lp);

						lp = (RelativeLayout.LayoutParams) minutesText.getLayoutParams();
						lp.leftMargin = convertDpToPixel(76, parent.getContext());
						minutesText.setLayoutParams(lp);

						lp = (RelativeLayout.LayoutParams) secondtimer.getLayoutParams();
						lp.leftMargin = convertDpToPixel(176, parent.getContext());
						secondtimer.setLayoutParams(lp);

						lp = (RelativeLayout.LayoutParams) secondsText.getLayoutParams();
						lp.leftMargin = convertDpToPixel(166, parent.getContext());
						secondsText.setLayoutParams(lp);

						// minutetimer.setX(minutetimer.getX() - 60);
						// minutesText.setX(minutesText.getX() - 60);
						// secondtimer.setX(secondtimer.getX() - 60);
						// secondsText.setX(secondsText.getX() - 60);
					}

					if (days == 0 && Hours == 0 && Mins == 0 && minutetimer.getVisibility() != View.INVISIBLE)
					{
						minutetimer.setVisibility(View.INVISIBLE);
						minutesText.setVisibility(View.INVISIBLE);

						RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) secondtimer.getLayoutParams();
						lp.leftMargin = convertDpToPixel(126, parent.getContext());
						secondtimer.setLayoutParams(lp);

						lp = (RelativeLayout.LayoutParams) secondsText.getLayoutParams();
						lp.leftMargin = convertDpToPixel(116, parent.getContext());
						secondsText.setLayoutParams(lp);

						// secondtimer.setX(secondtimer.getX() - 80);
						// secondsText.setX(secondsText.getX() - 80);
					}

					dayTimer.setText(days + "");
					hourtimer.setText(Hours + "");
					minutetimer.setText(Mins + "");
					secondtimer.setText(seconds + "");

				}
			});
		}
	};
	
	public static String getCurrentNewYearDate() {
		String newYearPrepend = "01.01.";
		return newYearPrepend + getNextYear();
	}
	
	public static String getNextYear() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		return "" + (year + 1);
	}
	

	private static float convertPixelsToDp(float px, Context context)
	{
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float dp = px / (metrics.xdpi / DisplayMetrics.DENSITY_DEFAULT);
		return dp;
	}

	private static int convertDpToPixel(float dp, Context context)
	{
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = dp * (metrics.xdpi / DisplayMetrics.DENSITY_DEFAULT);
		return (int) px;
	}

	private void initializeSoundClips()
	{
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		// Load the sound
		soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener()
		{
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status)
			{
				System.out.println("Sound Loaded");
			}
		});
		newYearSound = soundPool.load(this, R.raw.winner, 1);
	}

	private void playSound(int sound)
	{
		AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		float actualVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		soundPool.play(sound, actualVolume, actualVolume, 1, 0, 1f);
		played = true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onDestroy()
	{
		if (adView != null)
		{
			adView.destroy();
		}
		super.onDestroy();
	}
}
