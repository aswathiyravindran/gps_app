package com.example.gps_application;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements LocationListener{
Button loc;
Location location;
LocationManager location_manager;
boolean isGPSenable=false;
boolean isNWenable=false;
double latitude,longitude;
private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

// The minimum time between updates in milliseconds
private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
    @Override
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   
        loc=(Button)findViewById(R.id.b1);
       loc.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) 
		{
			LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			 isGPSenable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			 LocationListener locationListener = new LocationListener() {
				    public void onLocationChanged(Location location) {
				      // Called when a new location is found by the network location provider.
				      //makeUseOfNewLocation(location);
				    }

					@Override
					public void onProviderDisabled(String arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onProviderEnabled(String arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onStatusChanged(String arg0, int arg1,
							Bundle arg2) {
						// TODO Auto-generated method stub
						
					}
			 };
	         // getting network status
	         isNWenable = locationManager
	            .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
	         if (!isGPSenable && !isNWenable) {
	             // no network provider is enabled
	          } else {
	             
	             // First get location from Network Provider
	             if (isNWenable) {
	                locationManager.requestLocationUpdates(
	                   LocationManager.NETWORK_PROVIDER,
	                   MIN_TIME_BW_UPDATES,
	                   MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
	 						
	                
	                if (locationManager != null) {
	                   location = locationManager
	                      .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	 							
	                   if (location != null) {
	                      latitude = location.getLatitude();
	                      longitude = location.getLongitude();
	                	   String str;
	                	   str="Lattitude :"+latitude+" Longitude"+longitude;
	                      
	                	   Toast.makeText(getApplicationContext(),str, Toast.LENGTH_SHORT).show(); 
	                   }
	                }
	             }
	 				
	             // if GPS Enabled get lat/long using GPS Services
	             if (isGPSenable) {
	                if (location == null) {
	                   locationManager.requestLocationUpdates(
	                      LocationManager.GPS_PROVIDER,
	                      MIN_TIME_BW_UPDATES,
	                      MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
	 							
	                   
	                   if (locationManager != null) {
	                      location = locationManager
	                         .getLastKnownLocation(LocationManager.GPS_PROVIDER);
	 								
	                      if (location != null) {
	                    	latitude = location.getLatitude();
		                      longitude = location.getLongitude();
		                	   String str;
		                	   str="Lattitude :"+latitude+" Longitude"+longitude;
		                      
		                	   Toast.makeText(getApplicationContext(),str, Toast.LENGTH_SHORT).show();
	                      }
	                   }
	                }
	             }
	          }

	       }

		
    
        	
        });
   
    }
    
	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
    
}
