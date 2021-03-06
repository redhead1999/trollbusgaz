package androidx.media;

import android.media.AudioAttributes;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class AudioAttributesImplApi21 implements AudioAttributesImpl {
    private static final String TAG = "AudioAttributesCompat21";
    static Method sAudioAttributesToLegacyStreamType;
    AudioAttributes mAudioAttributes;
    int mLegacyStreamType;

    AudioAttributesImplApi21() {
        this.mLegacyStreamType = -1;
    }

    AudioAttributesImplApi21(AudioAttributes audioAttributes) {
        this(audioAttributes, -1);
    }

    AudioAttributesImplApi21(AudioAttributes audioAttributes, int explicitLegacyStream) {
        this.mLegacyStreamType = -1;
        this.mAudioAttributes = audioAttributes;
        this.mLegacyStreamType = explicitLegacyStream;
    }

    static Method getAudioAttributesToLegacyStreamTypeMethod() {
        try {
            if (sAudioAttributesToLegacyStreamType == null) {
                sAudioAttributesToLegacyStreamType = AudioAttributes.class.getMethod("toLegacyStreamType", new Class[]{AudioAttributes.class});
            }
            return sAudioAttributesToLegacyStreamType;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public Object getAudioAttributes() {
        return this.mAudioAttributes;
    }

    public int getVolumeControlStream() {
        if (VERSION.SDK_INT >= 26) {
            return this.mAudioAttributes.getVolumeControlStream();
        }
        return AudioAttributesCompat.toVolumeStreamType(true, getFlags(), getUsage());
    }

    public int getLegacyStreamType() {
        int i = this.mLegacyStreamType;
        if (i != -1) {
            return i;
        }
        Method frameworkMethod = getAudioAttributesToLegacyStreamTypeMethod();
        String str = TAG;
        if (frameworkMethod == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("No AudioAttributes#toLegacyStreamType() on API: ");
            sb.append(VERSION.SDK_INT);
            Log.w(str, sb.toString());
            return -1;
        }
        try {
            return ((Integer) frameworkMethod.invoke(null, new Object[]{this.mAudioAttributes})).intValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("getLegacyStreamType() failed on API: ");
            sb2.append(VERSION.SDK_INT);
            Log.w(str, sb2.toString(), e);
            return -1;
        }
    }

    public int getRawLegacyStreamType() {
        return this.mLegacyStreamType;
    }

    public int getContentType() {
        return this.mAudioAttributes.getContentType();
    }

    public int getUsage() {
        return this.mAudioAttributes.getUsage();
    }

    public int getFlags() {
        return this.mAudioAttributes.getFlags();
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("androidx.media.audio_attrs.FRAMEWORKS", this.mAudioAttributes);
        int i = this.mLegacyStreamType;
        if (i != -1) {
            bundle.putInt("androidx.media.audio_attrs.LEGACY_STREAM_TYPE", i);
        }
        return bundle;
    }

    public int hashCode() {
        return this.mAudioAttributes.hashCode();
    }

    public boolean equals(Object o) {
        if (!(o instanceof AudioAttributesImplApi21)) {
            return false;
        }
        return this.mAudioAttributes.equals(((AudioAttributesImplApi21) o).mAudioAttributes);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AudioAttributesCompat: audioattributes=");
        sb.append(this.mAudioAttributes);
        return sb.toString();
    }

    public static AudioAttributesImpl fromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        AudioAttributes frameworkAttrs = (AudioAttributes) bundle.getParcelable("androidx.media.audio_attrs.FRAMEWORKS");
        if (frameworkAttrs == null) {
            return null;
        }
        return new AudioAttributesImplApi21(frameworkAttrs, bundle.getInt("androidx.media.audio_attrs.LEGACY_STREAM_TYPE", -1));
    }
}
