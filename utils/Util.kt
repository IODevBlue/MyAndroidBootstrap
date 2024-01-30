package com.blueiobase.api.android.parallaxnavigationdrawer.util

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import android.view.WindowManager

/**
 * Utility class.
 * @author IO DevBlue
 * @since 1.0.0
 */
object Util {

    /**
     * Returns the width of the screen.
     * @param context The current [Context].
     * @return The width of the screen.
     */
    @Suppress("DEPRECATION")
    fun getScreenWidth(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = wm.currentWindowMetrics
            val insets = windowMetrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right
        } else {
            val metrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(metrics)
            metrics.widthPixels
        }
    }

    /**
     * Returns the height of the screen.
     * @param context The current [Context].
     * @return The height of the screen.
     */
    @Suppress("DEPRECATION")
    fun getScreenHeight(context: Context): Int { //TODO: Added this
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = wm.currentWindowMetrics
            val insets = windowMetrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.height() - insets.top - insets.bottom
        } else {
            val metrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(metrics)
            metrics.heightPixels
        }
    }

       /**
         * Converts Density-Independent Pixels to Pixels
         * @param dp Density-Independent Pixels in [Integer]
         * @return Pixels in [Integer]
         */
        fun dpToPx(dp: Int) = (dp * Resources.getSystem().displayMetrics.density).toInt()

        /**
         * Converts Converts Density-Independent Pixels to Pixels
         * @param dp Density-Independent Pixels in [Integer]
         * @return Pixels in [Integer]
         */
        fun dpToPxFloat(context: Context, dp: Float): Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()
        }

        /**
         * Converts Converts Scale-Independent Pixels to Pixels
         * @param sp Scale-Independent Pixels in [Float]
         * @return Pixels in [Integer]
         */
        fun spToPx(context: Context, sp: Float): Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.resources.displayMetrics).toInt()
        }

    /**
     * Tints the provided [drawable] to the specified [color].
     * @param context The [Context] instance to perform the tint operation
     * @param drawable The [Int] id for the [Drawable] object
     * @param drawableObj The [Drawable] object to tint
     * @param color The color to be applied as the tint
     * @return The tinted [Drawable]
     */
    fun tintDrawable(context: Context, @DrawableRes drawable: Int = 0,
                     drawableObj: Drawable? = null,
                     @ColorInt color: Int): Drawable {
        val d = DrawableCompat.wrap(if(drawable != 0) ContextCompat.getDrawable(context, drawable)!! else drawableObj!!)
        d.setTint(color)
        return d
    }

    /**
     * Converts device pixels to Density-Independent Pixels
     * @param px Number of Pixels
     * @return Density-Independent Pixels in [Float]
     */
    fun pxToDp(context: Context, px: Float) = px / context.resources.displayMetrics.density

    /**
     * Create a unique UUID [String] for the provided [uri].
     * @param uri The [Uri] to be converted to a UUID [String].
     * @return [String] representation of the [uri].
     */
    fun createUUIDKey(uri: Uri) = UUID.nameUUIDFromBytes(uri.toString().toByteArray()).toString().replace("-","")

    /**
     * Creates a [RippleDrawable].
     * @param normalColor The background/neutral color.
     * @param rippleColor The color of the dynamic ripple.
     * @return A valid [RippleDrawable] instance.
     */
    fun createRippleDrawable(@ColorInt normalColor: Int, @ColorInt rippleColor: Int): RippleDrawable { //TODO: Test this method
	val states = arrayOf(
            intArrayOf(android.R.attr.state_pressed),
            intArrayOf(android.R.attr.state_focused),
            intArrayOf(android.R.attr.state_activated),
            intArrayOf()
        )

        val colors = intArrayOf(rippleColor, rippleColor, rippleColor, normalColor)

        return RippleDrawable(ColorStateList(states, colors), ColorDrawable(normalColor), null)
    }

    /**
     * Changes the color of a scroll thumb drawable of a scrollable [View] eg: RecyclerView, ListView etc.
     *
     * **NOTE:** This method uses reflection on android versions less than Android Q (SDK 29).
     * @param scrollable The [View] with scrollable content.
     * @param color The color to change the scroll thumb drawable to.
     * @param isVertical `true` if [scrollable] scrolls vertically, `false` if [scrollable] scrolls horizontally.
     */
    @SuppressLint("DiscouragedPrivateApi")
    fun changeScrollBarColor(scrollable: View, @ColorInt color: Int, isVertical: Boolean) {
        if(isAndroid29()) {
            if(isVertical) {
                scrollable.verticalScrollbarThumbDrawable = ColorDrawable(color)
            } else {
                scrollable.horizontalScrollbarThumbDrawable = ColorDrawable(color)
            }
        } else {
            try {
                val mScrollCacheField = View::class.java.getDeclaredField("mScrollCache")
                mScrollCacheField.isAccessible = true
                val mScrollCache = mScrollCacheField.get(scrollable)
                val scrollBarField = mScrollCache.javaClass.getDeclaredField("scrollBar")
                scrollBarField.isAccessible = true
                val scrollBar = scrollBarField.get(mScrollCache)
                val method = scrollBar.javaClass.getDeclaredMethod(
                    if(isVertical) "setVerticalThumbDrawable" else "setHorizontalThumbDrawable" , Drawable::class.java)
                method.isAccessible = true
                method.invoke(scrollBar, ColorDrawable(color))
            } catch(_: Exception) {/* no-op */}
        }
    }


}