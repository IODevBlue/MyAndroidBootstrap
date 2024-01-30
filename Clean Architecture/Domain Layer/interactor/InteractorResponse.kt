package com.blueiobase.app.android.shopper.domain.interactor

import com.blueiobase.app.android.shopper.domain.interactor.base.IBaseInteractorError
import com.blueiobase.app.android.shopper.domain.interactor.base.IBaseInteractor


/**
 * Generic class representing a response retrieved from a database after an [IBaseInteractor] has been executed.
 *
 * - T: The type of the object retrieved from the database.
 *
 * @author IO DevBlue
 * @since 1.0.0
 */
class InteractorResponse<T> private constructor(){

    companion object {
        /**
         * The default [IBaseInteractorError] for any [InteractorResponse] to signify that
         * no result has been found.
         */
        val NO_RESULT = object: IBaseInteractorError {
            override val message: String
                get() = "No data found."

        }
    }

    /** The error that occurred when retrieving the [response][retrievedObject]. */
    var error: IBaseInteractorError? = null
        private set

    /** The response object gotten from the database. */
    var retrievedObject: T? = null
        private set


    /**
     * Constructs an [InteractorResponse] with the given [object][retrievedObject].
     * @param retrievedObject The object gotten from a database query.
     */
    constructor(retrievedObject: T): this() {
        this.retrievedObject = retrievedObject
    }

    /**
     * Constructs an [InteractorResponse] with the given [error].
     * @param error The error that occurred while attempting to query the database.
     */
    constructor(error: IBaseInteractorError) :this() {
        this.error = error
    }

    /** Validates if this response encountered any [error][IBaseInteractorError] while attempting to interact with the database.*/
    val hasError = error != null
}