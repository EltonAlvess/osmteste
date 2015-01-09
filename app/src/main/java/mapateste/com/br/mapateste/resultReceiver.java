package mapateste.com.br.mapateste;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by eltoncandidoalves on 29/12/14.
 */
public class resultReceiver extends ResultReceiver {
    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     *
     * @param handler
     */
    public resultReceiver(Handler handler) {
        super(handler);
    }


    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {


    }
}
