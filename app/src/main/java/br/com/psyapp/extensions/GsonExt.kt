package br.com.psyapp.extensions

import br.com.psyapp.utils.firebase.RemoteConfigUtils
import com.google.common.primitives.Primitives
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

@Throws(JsonSyntaxException::class)
fun <T> Gson.fromRemoteConfig(keyRemoteConfig: String, classOfT: Class<T>): T? {
    val json = RemoteConfigUtils.getFirebaseRemoteConfig().getString(keyRemoteConfig)
    val `object` = fromJson(json, classOfT)
    return Primitives.wrap(classOfT).cast(`object`)
}