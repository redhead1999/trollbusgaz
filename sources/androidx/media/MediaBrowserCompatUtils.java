package androidx.media;

import android.os.Bundle;
import android.support.p000v4.media.MediaBrowserCompat;

public class MediaBrowserCompatUtils {
    public static boolean areSameOptions(Bundle options1, Bundle options2) {
        boolean z = true;
        if (options1 == options2) {
            return true;
        }
        String str = MediaBrowserCompat.EXTRA_PAGE_SIZE;
        String str2 = MediaBrowserCompat.EXTRA_PAGE;
        if (options1 == null) {
            if (!(options2.getInt(str2, -1) == -1 && options2.getInt(str, -1) == -1)) {
                z = false;
            }
            return z;
        } else if (options2 == null) {
            if (!(options1.getInt(str2, -1) == -1 && options1.getInt(str, -1) == -1)) {
                z = false;
            }
            return z;
        } else {
            if (!(options1.getInt(str2, -1) == options2.getInt(str2, -1) && options1.getInt(str, -1) == options2.getInt(str, -1))) {
                z = false;
            }
            return z;
        }
    }

    public static boolean hasDuplicatedItems(Bundle options1, Bundle options2) {
        int pageSize1;
        int pageSize2;
        int endIndex1;
        int startIndex1;
        int endIndex2;
        int startIndex2;
        String str = MediaBrowserCompat.EXTRA_PAGE;
        int page1 = options1 == null ? -1 : options1.getInt(str, -1);
        int page2 = options2 == null ? -1 : options2.getInt(str, -1);
        String str2 = MediaBrowserCompat.EXTRA_PAGE_SIZE;
        if (options1 == null) {
            pageSize1 = -1;
        } else {
            pageSize1 = options1.getInt(str2, -1);
        }
        if (options2 == null) {
            pageSize2 = -1;
        } else {
            pageSize2 = options2.getInt(str2, -1);
        }
        if (page1 == -1 || pageSize1 == -1) {
            startIndex1 = 0;
            endIndex1 = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        } else {
            startIndex1 = pageSize1 * page1;
            endIndex1 = (startIndex1 + pageSize1) - 1;
        }
        if (page2 == -1 || pageSize2 == -1) {
            startIndex2 = 0;
            endIndex2 = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        } else {
            startIndex2 = pageSize2 * page2;
            endIndex2 = (startIndex2 + pageSize2) - 1;
        }
        if (endIndex1 < startIndex2 || endIndex2 < startIndex1) {
            return false;
        }
        return true;
    }

    private MediaBrowserCompatUtils() {
    }
}
