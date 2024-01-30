package com.blueiobase.app.android.shopper.domain.interactor.base

/**
 * Interface contract representing interactions (also known as use-cases) which are triggered
 * whenever actions on the user interface requires a response from a database.
 *
 * Every action that would trigger the retrieval and storage of data from a database
 * is considered an interaction and every interaction (with a database) must implement this contract.
 *
 * @author IO DevBlue
 * @since 1.0.0
 */
interface IBaseInteractor {

    /** Executes the current interactor. */
    suspend operator fun invoke()
}