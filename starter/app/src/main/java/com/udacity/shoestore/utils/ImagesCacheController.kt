package com.udacity.shoestore.utils

import android.graphics.Bitmap
import android.util.LruCache

class ImagesCacheController {

        private lateinit var imagesCache: LruCache<String, Bitmap?>

        init{
            val maxMemory = Runtime.getRuntime().maxMemory() / 1024
            val cacheSize: Int = (maxMemory / 8).toInt()
            imagesCache = LruCache<String, Bitmap?>(cacheSize / 2)
        }

        fun getBitmap(key: String): Bitmap? {
            return if (::imagesCache.isInitialized) {
                imagesCache.get(key)
            } else {
                null
            }
        }

        fun saveBitmap(key: String, bitmap: Bitmap?) {
            if (::imagesCache.isInitialized) {
                imagesCache.put(key, bitmap)
            }
        }

    fun clean(){
        if(::imagesCache.isInitialized) {
            imagesCache.evictAll()
        }
    }
}