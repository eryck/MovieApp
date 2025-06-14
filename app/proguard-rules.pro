-keepattributes Signatures
-keepattributes *Annotations*

-dontwarn sun.misc.**

-keep class com.google.gson.examples.android.model.** {<fields>;}
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

-keepclasseswithmembers,allowobfuscation class * {
@com.google.gson.annotations.SerializedName <fields>;
}

-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken