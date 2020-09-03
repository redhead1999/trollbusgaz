package android.support.p000v4.media.session;

import android.net.Uri;
import android.os.Bundle;

/* renamed from: android.support.v4.media.session.MediaControllerCompatApi23 */
class MediaControllerCompatApi23 {

    /* renamed from: android.support.v4.media.session.MediaControllerCompatApi23$TransportControls */
    public static class TransportControls {
        public static void playFromUri(Object controlsObj, Uri uri, Bundle extras) {
            ((android.media.session.MediaController.TransportControls) controlsObj).playFromUri(uri, extras);
        }

        private TransportControls() {
        }
    }

    private MediaControllerCompatApi23() {
    }
}
