package com.blueiobase.app.android.shopper.domain.repository.cache

import com.blueiobase.app.android.shopper.domain.repository.base.IBaseRepository

/**
 * Interface contract for repositories that cache their data locally on the device.
 *
 * @author IO DevBlue
 * @since 1.0.0
 */
interface ICacheRepository<T>: IBaseRepository<T> {

    /**
     * Validates if the data identified by the [name] parameter exists in the cache.
     *
     * @param name Parameter query to identify the data stored in the cache.
     * @return `true` if the data is cached, `false` if otherwise.
     */
    fun isCached(name: String): Boolean

    /**
     * Validates if the [ICacheRepository] is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    fun isExpired(): Boolean

    /** Removes all data stored in the [ICacheRepository]. */
    fun clean()
}