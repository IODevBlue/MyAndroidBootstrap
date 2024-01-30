package com.blueiobase.app.android.shopper.map

/**
 * This class transforms one [List] to another.
 *
 * @author IO DevBlue
 * @since 1.0.0
 */
open class ListMap<T, R>(private val map: Map<T, R>): Map<ArrayList<T>, ArrayList<R>> {

    override fun map(data: ArrayList<T>): ArrayList<R> {
        val array: ArrayList<R> = ArrayList()
        if (data.size > 0) {
            for (model in data) {
                array.add(map.map(model))
            }
        }
        return array
    }


}