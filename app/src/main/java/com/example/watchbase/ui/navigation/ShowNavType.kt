package com.example.watchbase.ui.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.example.watchbase.data.model.Show
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ShowNavType : NavType<Show?>(false) {
    override fun get(bundle: Bundle, key: String): Show? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Show? {
        return Gson().fromJson(value, object : TypeToken<Show>() {}.type)
    }

    override fun put(bundle: Bundle, key: String, value: Show?) {
        bundle.putParcelable(key, value)
    }
}