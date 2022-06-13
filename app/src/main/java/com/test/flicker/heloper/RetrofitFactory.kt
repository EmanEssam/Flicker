package com.test.flicker.heloper

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class RetrofitFactory @Inject constructor() {

    val BASE_URL = "https://www.flickr.com/services/rest/"

    var httpClient: OkHttpClient = OkHttpClient().newBuilder()
        .addNetworkInterceptor(Interceptor { chain ->
            var request: Request = chain.request()
            request = request.newBuilder().build()
            chain.proceed(request)
        }) //here we adding Interceptor for full level logging
        .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    //Creating HttpLogging Interceptor to log the contents of request and response bodies.
    private val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)


    //OkhttpClient for building http request url
    val dataClient = OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(90, TimeUnit.SECONDS)
        .readTimeout(90, TimeUnit.SECONDS)
        .build()

    var gson = GsonBuilder()
        .setLenient()
        .create()

    inline fun <reified T> getService(): T {
        return Retrofit.Builder()
            .client(dataClient)
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(T::class.java)
    }
}