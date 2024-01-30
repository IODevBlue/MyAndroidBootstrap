package com.blueiobase.app.android.shopper.map

/**
 * Interface to map one object to another.
 *
 * @author IO DevBlue
 * @since 1.0.0
 */
interface Map<T, R> { //TODO: Confirm if an inverse map is needed.

    /** Transforms type [T] to type [R]. */
    fun map(data: T): R

//    fun inverseMap(data: R): T
}