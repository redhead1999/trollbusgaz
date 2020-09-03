package androidx.core.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import androidx.core.util.Preconditions;

public final class ResourcesCompat {
    private static final String TAG = "ResourcesCompat";

    public static abstract class FontCallback {
        public abstract void onFontRetrievalFailed(int i);

        public abstract void onFontRetrieved(Typeface typeface);

        public final void callbackSuccessAsync(final Typeface typeface, Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() {
                public void run() {
                    FontCallback.this.onFontRetrieved(typeface);
                }
            });
        }

        public final void callbackFailAsync(final int reason, Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() {
                public void run() {
                    FontCallback.this.onFontRetrievalFailed(reason);
                }
            });
        }
    }

    public static Drawable getDrawable(Resources res, int id, Theme theme) throws NotFoundException {
        if (VERSION.SDK_INT >= 21) {
            return res.getDrawable(id, theme);
        }
        return res.getDrawable(id);
    }

    public static Drawable getDrawableForDensity(Resources res, int id, int density, Theme theme) throws NotFoundException {
        if (VERSION.SDK_INT >= 21) {
            return res.getDrawableForDensity(id, density, theme);
        }
        if (VERSION.SDK_INT >= 15) {
            return res.getDrawableForDensity(id, density);
        }
        return res.getDrawable(id);
    }

    public static int getColor(Resources res, int id, Theme theme) throws NotFoundException {
        if (VERSION.SDK_INT >= 23) {
            return res.getColor(id, theme);
        }
        return res.getColor(id);
    }

    public static ColorStateList getColorStateList(Resources res, int id, Theme theme) throws NotFoundException {
        if (VERSION.SDK_INT >= 23) {
            return res.getColorStateList(id, theme);
        }
        return res.getColorStateList(id);
    }

    public static float getFloat(Resources res, int id) {
        TypedValue value = new TypedValue();
        res.getValue(id, value, true);
        if (value.type == 4) {
            return value.getFloat();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Resource ID #0x");
        sb.append(Integer.toHexString(id));
        sb.append(" type #0x");
        sb.append(Integer.toHexString(value.type));
        sb.append(" is not valid");
        throw new NotFoundException(sb.toString());
    }

    public static Typeface getFont(Context context, int id) throws NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, id, new TypedValue(), 0, null, null, false);
    }

    public static void getFont(Context context, int id, FontCallback fontCallback, Handler handler) throws NotFoundException {
        Preconditions.checkNotNull(fontCallback);
        if (context.isRestricted()) {
            fontCallback.callbackFailAsync(-4, handler);
            return;
        }
        loadFont(context, id, new TypedValue(), 0, fontCallback, handler, false);
    }

    public static Typeface getFont(Context context, int id, TypedValue value, int style, FontCallback fontCallback) throws NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, id, value, style, fontCallback, null, true);
    }

    private static Typeface loadFont(Context context, int id, TypedValue value, int style, FontCallback fontCallback, Handler handler, boolean isRequestFromLayoutInflator) {
        Resources resources = context.getResources();
        resources.getValue(id, value, true);
        Typeface typeface = loadFont(context, resources, value, id, style, fontCallback, handler, isRequestFromLayoutInflator);
        if (typeface != null || fontCallback != null) {
            return typeface;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Font resource ID #0x");
        sb.append(Integer.toHexString(id));
        sb.append(" could not be retrieved.");
        throw new NotFoundException(sb.toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:64:0x00f5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Typeface loadFont(android.content.Context r19, android.content.res.Resources r20, android.util.TypedValue r21, int r22, int r23, androidx.core.content.res.ResourcesCompat.FontCallback r24, android.os.Handler r25, boolean r26) {
        /*
            r9 = r20
            r10 = r21
            r11 = r22
            r12 = r23
            r13 = r24
            r14 = r25
            java.lang.String r15 = "ResourcesCompat"
            java.lang.CharSequence r0 = r10.string
            if (r0 == 0) goto L_0x00fa
            java.lang.CharSequence r0 = r10.string
            java.lang.String r8 = r0.toString()
            java.lang.String r0 = "res/"
            boolean r0 = r8.startsWith(r0)
            r16 = 0
            r7 = -3
            if (r0 != 0) goto L_0x0029
            if (r13 == 0) goto L_0x0028
            r13.callbackFailAsync(r7, r14)
        L_0x0028:
            return r16
        L_0x0029:
            android.graphics.Typeface r6 = androidx.core.graphics.TypefaceCompat.findFromCache(r9, r11, r12)
            if (r6 == 0) goto L_0x0035
            if (r13 == 0) goto L_0x0034
            r13.callbackSuccessAsync(r6, r14)
        L_0x0034:
            return r6
        L_0x0035:
            java.lang.String r0 = r8.toLowerCase()     // Catch:{ XmlPullParserException -> 0x00d8, IOException -> 0x00bd }
            java.lang.String r1 = ".xml"
            boolean r0 = r0.endsWith(r1)     // Catch:{ XmlPullParserException -> 0x00d8, IOException -> 0x00bd }
            if (r0 == 0) goto L_0x0099
            android.content.res.XmlResourceParser r0 = r9.getXml(r11)     // Catch:{ XmlPullParserException -> 0x0092, IOException -> 0x008b }
            androidx.core.content.res.FontResourcesParserCompat$FamilyResourceEntry r1 = androidx.core.content.res.FontResourcesParserCompat.parse(r0, r9)     // Catch:{ XmlPullParserException -> 0x0092, IOException -> 0x008b }
            r17 = r1
            if (r17 != 0) goto L_0x0069
            java.lang.String r1 = "Failed to find font-family tag"
            android.util.Log.e(r15, r1)     // Catch:{ XmlPullParserException -> 0x0061, IOException -> 0x0059 }
            if (r13 == 0) goto L_0x0058
            r13.callbackFailAsync(r7, r14)     // Catch:{ XmlPullParserException -> 0x0061, IOException -> 0x0059 }
        L_0x0058:
            return r16
        L_0x0059:
            r0 = move-exception
            r1 = r19
            r18 = r6
            r10 = r8
            goto L_0x00c3
        L_0x0061:
            r0 = move-exception
            r1 = r19
            r18 = r6
            r10 = r8
            goto L_0x00de
        L_0x0069:
            r1 = r19
            r2 = r17
            r3 = r20
            r4 = r22
            r5 = r23
            r18 = r6
            r6 = r24
            r10 = -3
            r7 = r25
            r10 = r8
            r8 = r26
            android.graphics.Typeface r1 = androidx.core.graphics.TypefaceCompat.createFromResourcesFamilyXml(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ XmlPullParserException -> 0x0086, IOException -> 0x0082 }
            return r1
        L_0x0082:
            r0 = move-exception
            r1 = r19
            goto L_0x00c3
        L_0x0086:
            r0 = move-exception
            r1 = r19
            goto L_0x00de
        L_0x008b:
            r0 = move-exception
            r18 = r6
            r10 = r8
            r1 = r19
            goto L_0x00c3
        L_0x0092:
            r0 = move-exception
            r18 = r6
            r10 = r8
            r1 = r19
            goto L_0x00de
        L_0x0099:
            r18 = r6
            r10 = r8
            r1 = r19
            android.graphics.Typeface r0 = androidx.core.graphics.TypefaceCompat.createFromResourcesFontFile(r1, r9, r11, r10, r12)     // Catch:{ XmlPullParserException -> 0x00bb, IOException -> 0x00b9 }
            r6 = r0
            if (r13 == 0) goto L_0x00b8
            if (r6 == 0) goto L_0x00ab
            r13.callbackSuccessAsync(r6, r14)     // Catch:{ XmlPullParserException -> 0x00b4, IOException -> 0x00b0 }
            goto L_0x00b8
        L_0x00ab:
            r2 = -3
            r13.callbackFailAsync(r2, r14)     // Catch:{ XmlPullParserException -> 0x00b4, IOException -> 0x00b0 }
            goto L_0x00b8
        L_0x00b0:
            r0 = move-exception
            r18 = r6
            goto L_0x00c3
        L_0x00b4:
            r0 = move-exception
            r18 = r6
            goto L_0x00de
        L_0x00b8:
            return r6
        L_0x00b9:
            r0 = move-exception
            goto L_0x00c3
        L_0x00bb:
            r0 = move-exception
            goto L_0x00de
        L_0x00bd:
            r0 = move-exception
            r1 = r19
            r18 = r6
            r10 = r8
        L_0x00c3:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to read xml resource "
            r2.append(r3)
            r2.append(r10)
            java.lang.String r2 = r2.toString()
            android.util.Log.e(r15, r2, r0)
            goto L_0x00f3
        L_0x00d8:
            r0 = move-exception
            r1 = r19
            r18 = r6
            r10 = r8
        L_0x00de:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to parse xml resource "
            r2.append(r3)
            r2.append(r10)
            java.lang.String r2 = r2.toString()
            android.util.Log.e(r15, r2, r0)
        L_0x00f3:
            if (r13 == 0) goto L_0x00f9
            r2 = -3
            r13.callbackFailAsync(r2, r14)
        L_0x00f9:
            return r16
        L_0x00fa:
            r1 = r19
            android.content.res.Resources$NotFoundException r0 = new android.content.res.Resources$NotFoundException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Resource \""
            r2.append(r3)
            java.lang.String r3 = r9.getResourceName(r11)
            r2.append(r3)
            java.lang.String r3 = "\" ("
            r2.append(r3)
            java.lang.String r3 = java.lang.Integer.toHexString(r22)
            r2.append(r3)
            java.lang.String r3 = ") is not a Font: "
            r2.append(r3)
            r3 = r21
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.res.ResourcesCompat.loadFont(android.content.Context, android.content.res.Resources, android.util.TypedValue, int, int, androidx.core.content.res.ResourcesCompat$FontCallback, android.os.Handler, boolean):android.graphics.Typeface");
    }

    private ResourcesCompat() {
    }
}
