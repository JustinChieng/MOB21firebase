#include <jni.h>

// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.







extern "C"
JNIEXPORT jstring JNICALL
Java_com_justin_mob21justin_core_utils_NativeUtils_greet(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("Hello for Native (CPP) 23");
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_justin_mob21justin_core_utils_NativeUtils_sayHelloNumbers(JNIEnv *env, jobject thiz) {
    return 23;
}