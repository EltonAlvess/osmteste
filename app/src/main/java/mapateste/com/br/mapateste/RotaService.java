package mapateste.com.br.mapateste;

import android.app.Activity;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;

import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

/**
 * Created by eltoncandidoalves on 29/12/14.
 */
public class RotaService extends IntentService {


    public RotaService() {
        super("ServiceRota");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        resultReceiver roadLocal = intent.getParcelableExtra("road");

        RoadManager roadManager = new OSRMRoadManager();


        GeoPoint startPoint = new GeoPoint(48.13, -1.63);
        ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
        waypoints.add(startPoint);
        GeoPoint endPoint = new GeoPoint(48.4, -1.9);
        waypoints.add(endPoint);

        Road road = roadManager.getRoad(waypoints);

        Bundle resultBundle = new Bundle();
        resultBundle.putParcelable("result", roadLocal);
        roadLocal.send(Activity.RESULT_OK, resultBundle);
    }
}
