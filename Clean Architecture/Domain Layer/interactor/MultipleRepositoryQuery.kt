package com.blueiobase.app.android.shopper.domain.interactor

import  com.blueiobase.app.android.shopper.domain.repository.base.IBaseRepository
import  com.blueiobase.app.android.shopper.domain.interactor.base.IBaseInteractor

/**
 * This class queries multiple [IBaseRepository] implementations to retrieve an [InteractorResponse] triggered by a [IBaseInteractor].
 *
 * This class is useful in scenarios where there are two or more repositories storing the same type of
 * data (for example: a local cache and a remote repository) and this data must be retrieved from either source.
 * - USAGE
 *
 *
 * @author IO DevBlue
 * @since 1.0.0
 */
class MultipleRepositoryQuery<T> (
    /** The [List] of [IBaseRepository] to query. */
    private val databases: List<IBaseRepository<T>>,
    /** The [Validator] used to authenticate usefulness of the data returned by the [MultipleRepositoryQuery]. */
    private var validator: Validator<T>
    ) {

    /**
     * Queries the [databases] to provide a [response][InteractorResponse].
     * @param parameter Identifier of the data stored in the [databases].
     * @return A valid [InteractorResponse].
     */
    fun queryDatabases(parameter: String): InteractorResponse<T> { //TODO: Test this method.

        return queryDatabase(parameter, databases.size)
    }

    /**
     * Internal delegate method for the [queryDatabases] to query each [IBaseRepository] in the [databases].
     * @param parameter Identifier of the data stored in the [databases].
     * @param numberOfDatabases The size of the [databases] list.
     * @return A valid [InteractorResponse].
     */
    private tailrec fun queryDatabase(parameter: String, numberOfDatabases: Int): InteractorResponse<T>  {

        return if(numberOfDatabases < 0) return InteractorResponse(InteractorResponse.NO_RESULT)

        else {
            val database = databases[numberOfDatabases]
            val data = database.retrieve(parameter)
            val valid = validator.isValid(data)
            if (valid) return InteractorResponse(data)
            queryDatabase(parameter, numberOfDatabases-1)

        }
    }

    /** Interface to determine if the data provided after a query by the [MultipleRepositoryQuery] is valid. */
    interface Validator<T> {

        /**
         * Validates if the provided [data] is still acceptable using the provided logic.
         * @param data The data to validate.
         * @return true if [data] is still useful, false if otherwise.
         */
        fun isValid(data: T): Boolean
    }

}