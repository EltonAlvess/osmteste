package mapateste.com.br.mapateste;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.api.Polyline;
import org.osmdroid.tileprovider.IRegisterReceiver;
import org.osmdroid.tileprovider.MapTileProviderArray;
import org.osmdroid.tileprovider.modules.GEMFFileArchive;
import org.osmdroid.tileprovider.modules.IArchiveFile;
import org.osmdroid.tileprovider.modules.INetworkAvailablityCheck;
import org.osmdroid.tileprovider.modules.MapTileDownloader;
import org.osmdroid.tileprovider.modules.MapTileFileArchiveProvider;
import org.osmdroid.tileprovider.modules.MapTileFilesystemProvider;
import org.osmdroid.tileprovider.modules.MapTileModuleProviderBase;
import org.osmdroid.tileprovider.modules.NetworkAvailabliltyCheck;
import org.osmdroid.tileprovider.modules.TileWriter;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.tileprovider.util.SimpleRegisterReceiver;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.ResourceProxyImpl;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.PathOverlay;

/**
 * Created by FCAMARA164 on 24/12/2014.
 */

//-20.5076 -47.4749
public class mapaFragment  extends Fragment{

    MapView mMapView;
    ResourceProxy mResourceProxy;
    MapController mMapController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mResourceProxy = new ResourceProxyImpl(inflater.getContext().getApplicationContext());
        mMapView = new MapView(inflater.getContext(), 256, mResourceProxy);

        final Context context = getActivity();
        final Context applicationContext = context.getApplicationContext();
        final IRegisterReceiver registerReceiver = new SimpleRegisterReceiver(applicationContext);

// Create a custom tile source
        final ITileSource tileSource = new XYTileSource("Mapnik", ResourceProxy.string.mapnik, 1, 18, 256, ".png", new String[]{"http://tile.openstreetmap.org/"});

// Create a file cache modular provider
        final TileWriter tileWriter = new TileWriter();
        final MapTileFilesystemProvider fileSystemProvider = new MapTileFilesystemProvider(
                registerReceiver, tileSource);

/*// Create an archive file modular tile provider
        GEMFFileArchive gemfFileArchive = GEMFFileArchive.getGEMFFileArchive(mGemfArchiveFilename); // Requires try/catch
        MapTileFileArchiveProvider fileArchiveProvider = new MapTileFileArchiveProvider(
                registerReceiver, tileSource, new IArchiveFile[] { gemfFileArchive });*/

// Create a download modular tile provider
        final NetworkAvailabliltyCheck networkAvailabliltyCheck = new NetworkAvailabliltyCheck(context);
        final MapTileDownloader downloaderProvider = new MapTileDownloader(
                tileSource, tileWriter, networkAvailabliltyCheck);

// Create a custom tile provider array with the custom tile source and the custom tile providers
        final MapTileProviderArray tileProviderArray = new MapTileProviderArray(
                tileSource, registerReceiver, new MapTileModuleProviderBase[] {
                fileSystemProvider,  downloaderProvider });

// Create the mapview with the custom tile provider array
        mMapView = new MapView(context, 256, new DefaultResourceProxyImpl(context), tileProviderArray);


        mMapView.setBuiltInZoomControls(true);

        mMapController = (MapController) mMapView.getController();
        mMapController.setZoom(21);



    //    GeoPoint gPt0 = new GeoPoint(-20.5381768,-47.4009795);
     //   GeoPoint gPt1 = new GeoPoint(gPt0.getLatitudeE6()+ mIncr, gPt0.getLongitudeE6());
     //   GeoPoint gPt2 = new GeoPoint(gPt0.getLatitudeE6()+ mIncr, gPt0.getLongitudeE6() + mIncr);
     //   GeoPoint gPt3 = new GeoPoint(gPt0.getLatitudeE6(), gPt0.getLongitudeE6() + mIncr);
                //-20.5655887
                //-20.5654887
                //-47.4036395
                //-47.4035395
        String poligonos = "M 13.3975116 -52.517290500000001 L 13.3978115 -52.517310299999998 13.398121 -52.5173311 13.398157700000001 -52.517120300000002 13.397992199999999 -52.517109300000001 13.397989300000001 -52.517124799999998 13.3979623 -52.517123300000002 13.3979655 -52.517106900000002 13.397741699999999 -52.517092400000003 13.3977392 -52.5171086 13.397712200000001 -52.517106400000003 13.397715 -52.517090600000003 13.397549 -52.517079799999998 Z";


     //   PathOverlay teste =  new PathOverlay(Color.RED,mResourceProxy.)



     //   mMapView.getOverlays().add(myOverlay);
      //  mMapController.setCenter(pt1);


        return mMapView;
    }
}
