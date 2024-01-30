package com.blueiobase.app.android.shopper.map


/**
 * This class transforms a [List] of [Pair] objects to another.
 *
 *  - [M] is the mutual type both pairs have.
 *
 * - [T] is the type that is taken and converted.
 *
 * - [R] is the type that is returned and produced.
 * @author IO DevBlue
 * @since 1.0.0
 */
open class PairListMap<M, T, R>(private val map: Map<T, R>): Map<ArrayList<Pair<T, M>>, ArrayList<Pair<R, M>>> {

    override fun map(data: ArrayList<Pair<T, M>>): ArrayList<Pair<R, M>> { //TODO: Test this map.
        val array = arrayListOf<Pair<R, M>>()
        for (a in data) {
            val x = a.first
            val y = map.map(x)
            array.add(Pair(y, a.second))
        }
        return array
    }

}