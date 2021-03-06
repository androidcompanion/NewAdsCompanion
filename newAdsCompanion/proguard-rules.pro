# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontwarn com.google.common.**

-keep class com.google.gson.Gson



-keep public class * extends com.google.gson.TypeAdapter

-keepattributes *Annotation*

-keepclassmembers enum * { *; }


-keepclassmembers class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
-keep public class com.google.android.gms.ads.** {
   public *;
}

-keepattributes JavascriptInterface
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

-keep public class com.newadscompanion.ModelsCompanion.AdsData {*;}
-keep public class com.newadscompanion.ModelsCompanion.AdsIdsList {*;}
-keep public class com.newadscompanion.ModelsCompanion.AdsPrefernce {*;}
-keep public class com.newadscompanion.ModelsCompanion.BannerAdIH {*;}
-keep public class com.newadscompanion.ModelsCompanion.BannerDetail {*;}
-keep public class com.newadscompanion.ModelsCompanion.InterDetail {*;}
-keep public class com.newadscompanion.ModelsCompanion.InterstitialAdIH {*;}
-keep public class com.newadscompanion.ModelsCompanion.NativeAdIH {*;}
-keep public class com.newadscompanion.ModelsCompanion.NativeDetail {*;}
-keep public class com.newadscompanion.ModelsCompanion.GsonUtils {*;}
