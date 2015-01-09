package mapateste.com.br.mapateste;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;


import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.overlays.Polyline;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.util.ArrayList;

/**
 * Created by eltoncandidoalves on 08/01/15.
 */
public class AsyncMap extends AsyncTask<Context, Void, Polyline >{
    MapView mMap;

    private TaskFinish callback;

    public AsyncMap(TaskFinish o){
            callback = o;
    }

    @Override
    protected Polyline doInBackground(Context... params) {

        RoadManager roadManager = new OSRMRoadManager();


       // GeoPoint startPoint = new GeoPoint(48.13, -1.63);
        GeoPoint point1 = new GeoPoint(-20.514284,-47.4209103);
        GeoPoint point2 = new GeoPoint(-20.511539,-47.4229308);
        GeoPoint point3 = new GeoPoint(-20.508363,-47.4211582);

        ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
        waypoints.add(point1);
        waypoints.add(point2);
       // waypoints.add(point3);
      //  GeoPoint endPoint = new GeoPoint(48.4, -1.9);
       // waypoints.add(endPoint);

        Road road = roadManager.getRoad(waypoints);

        Polyline roads = roadManager.buildRoadOverlay(road, params[0]);



        return roads;
    }


    @Override
    protected void onPostExecute(Polyline o){
        callback.completed(o);
    }
}
