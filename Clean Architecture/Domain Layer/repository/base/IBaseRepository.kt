package com.blueiobase.app.android.shopper.domain.repository.base

/**
 * Interface contract for all repositories.
 *
 * @author IO DevBlue
 * @since 1.0.0
 */
interface IBaseRepository<T> {

    /** Stores the given object. */
    fun persist(data: T)

    /**
     * Retrieves object with the provided [name].
     * @param name Identifier for the given object.
     */
    fun retrieve(name: String): T
}