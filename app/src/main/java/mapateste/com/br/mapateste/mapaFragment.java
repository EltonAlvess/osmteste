package mapateste.com.br.mapateste;

import android.content.Context;

import android.os.Bundle;


import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.overlays.Polyline;
import org.osmdroid.tileprovider.IRegisterReceiver;
import org.osmdroid.tileprovider.MapTileProviderArray;
import org.osmdroid.tileprovider.modules.MapTileDownloader;

import org.osmdroid.tileprovider.modules.MapTileFilesystemProvider;
import org.osmdroid.tileprovider.modules.MapTileModuleProviderBase;
import org.osmdroid.tileprovider.modules.NetworkAvailabliltyCheck;
import org.osmdroid.tileprovider.modules.TileWriter;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.tileprovider.util.SimpleRegisterReceiver;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.ResourceProxyImpl;

import org.osmdroid.views.MapView;

/**
 * Created by FCAMARA164 on 24/12/2014.
 */

//-20.5076 -47.4749
public class mapaFragment  extends Fragment implements TaskFinish{

    MapView mMapView;
    ResourceProxy mResourceProxy;
    IMapController mMapController;
    Boolean ok ;

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
        mMapView.setMultiTouchControls(true);


        AsyncMap ae = new AsyncMap(this);


        ae.execute(getActivity());







        return mMapView;
    }

//-20.5142842,\"lng\":-47.4209103},{\"r\":1,\"d\":1,\"i\":2,\"a\":\"Rua Lizete Coelho Lourenço\",\"lat\":-20.5115397,\"lng\":-47.4229308},{\"r\":1,\"d\":1,\"i\":3,\"a\":\"Av. 1\",\"lat\":-20.508363,\"lng\":-47.4211582}
    @Override
    public void completed(Polyline p) {
        GeoPoint point1 = new GeoPoint(-20.5142842, -47.4209103);


        mMapController = mMapView.getController();

        mMapController.setCenter(point1);
        mMapController.setZoom(12);

        mMapView.getOverlays().add(p);
        mMapView.invalidate();

    }
}

