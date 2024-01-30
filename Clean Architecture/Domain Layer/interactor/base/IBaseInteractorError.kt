package com.blueiobase.app.android.shopper.domain.interactor.base

/**
 * Interface contract representing errors that might occur when executing an [IBaseInteractor].
 *
 * @author IO DevBlue
 * @since 1.0.0
 */
interface IBaseInteractorError {
    val message: String
}