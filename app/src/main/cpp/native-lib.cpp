#include <jni.h>

extern "C"
void
Java_com_omar_packintent_MainActivity_nativeGCMRegisteredForRemoteNotifications(
        JNIEnv* jenv, jobject thiz, jstring jGCMToken ) {
    // TODO(omar) : do some super cool stuff with the registration token
}

extern "C"
void
Java_com_omar_packintent_MainActivity_nativeGCMFailedToRegisterForRemoteNotifications(
        JNIEnv* jenv, jobject thiz, jstring jErrorMessage ) {
    // TODO(omar) : table flip
}

extern "C"
void
Java_com_omar_packintent_MainActivity_nativeGCMReceivedRemoteNotification(
        JNIEnv* jenv, jobject thiz, jstring jMessage ) {
    // TODO(omar) : do something with the received message
}