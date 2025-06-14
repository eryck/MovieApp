package br.com.movieapp.core.data.remote

import br.com.movieapp.BuildConfig
import br.com.movieapp.core.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class ParamsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter(
                name = Constants.LANGUAGE_PARAM,
                value = Constants.LANGUAGE_VALUE
            )
            .addQueryParameter(
                name = Constants.API_KEY_PARAM,
                value = BuildConfig.API_KEY
            )
            .build()

        val newRequest = request.newBuilder()
            .url(url = url)
            .build()

        return chain.proceed(newRequest)
    }
}