@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

object systemPreferences {

    private val module: dynamic = js("require('electron').systemPreferences")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     */
    fun isDarkMode(): Boolean = 
        module.isDarkMode()

    /**
     */
    fun isSwipeTrackingFromScrollEventsEnabled(): Boolean = 
        module.isSwipeTrackingFromScrollEventsEnabled()

    /**
     * Posts event as native notifications of macOS. The userInfo is an Object that 
     * contains the user information dictionary sent along with the notification.
     */
    fun postNotification(event: String, userInfo: PostNotificationUserInfo.() -> Unit): Unit = 
        module.postNotification(event, userInfo.let { PostNotificationUserInfo().apply(it) })

    /**
     * Posts event as native notifications of macOS. The userInfo is an Object that 
     * contains the user information dictionary sent along with the notification.
     */
    fun postLocalNotification(event: String, userInfo: PostLocalNotificationUserInfo.() -> Unit): Unit = 
        module.postLocalNotification(event, userInfo.let { PostLocalNotificationUserInfo().apply(it) })

    /**
     * Subscribes to native notifications of macOS, callback will be called with 
     * callback(event, userInfo) when the corresponding event happens. The userInfo 
     * is an Object that contains the user information dictionary sent along with the 
     * notification.
     *
     * The id of the subscriber is returned, which can be used to unsubscribe the 
     * event.
     *
     * Under the hood this API subscribes to NSDistributedNotificationCenter, example 
     * values of event are:
     *
     *  . AppleInterfaceThemeChangedNotification
     *  . AppleAquaColorVariantChanged
     *  . AppleColorPreferencesChangedNotification
     *  . AppleShowScrollBarsSettingChanged
     *
     */
    fun subscribeNotification(event: String, callback: (event: String, userInfo: SubscribeNotificationUserInfo.() -> Unit) -> Unit): Unit = 
        module.subscribeNotification(event, callback)

    /**
     * Removes the subscriber with id.
     */
    fun unsubscribeNotification(id: Int): Unit = 
        module.unsubscribeNotification(id)

    /**
     * Same as subscribeNotification, but uses NSNotificationCenter for local 
     * defaults. This is necessary for events such as 
     * NSUserDefaultsDidChangeNotification
     */
    fun subscribeLocalNotification(event: String, callback: (event: String, userInfo: SubscribeLocalNotificationUserInfo.() -> Unit) -> Unit): Unit = 
        module.subscribeLocalNotification(event, callback)

    /**
     * Same as unsubscribeNotification, but removes the subscriber from 
     * NSNotificationCenter.
     */
    fun unsubscribeLocalNotification(id: Int): Unit = 
        module.unsubscribeLocalNotification(id)

    /**
     * Get the value of key in system preferences.
     *
     * This API uses NSUserDefaults on macOS. Some popular key and types are:
     *
     *  . AppleInterfaceStyle: string
     *  . AppleAquaColorVariant: integer
     *  . AppleHighlightColor: string
     *  . AppleShowScrollBars: string
     *  . NSNavRecentPlaces: array
     *  . NSPreferredWebServices: dictionary
     *  . NSUserDictionaryReplacementItems: array
     *
     */
    fun getUserDefault(key: String, type: String): Unit = 
        module.getUserDefault(key, type)

    /**
     * Set the value of key in system preferences.
     *
     * Note that type should match actual type of value. An exception is thrown if 
     * they don't.
     *
     * This API uses NSUserDefaults on macOS. Some popular key and types are:
     *
     *  . ApplePressAndHoldEnabled: boolean
     *
     */
    fun setUserDefault(key: String, type: String, value: String): Unit = 
        module.setUserDefault(key, type, value)

    /**
     * This method returns true if DWM composition (Aero Glass) is enabled, and false 
     * otherwise.
     *
     * An example of using it to determine if you should create a transparent window 
     * or not (transparent windows won't work correctly when DWM composition is 
     * disabled):
     *
     *  | 
     *  | const {BrowserWindow, systemPreferences} = require('electron')
     *  | let browserOptions = {width: 1000, height: 800}
     *  | 
     *  | // Make the window transparent only if the platform supports it.
     *  | if (process.platform !== 'win32' || systemPreferences.isAeroGlassEnabled()) {
     *  |   browserOptions.transparent = true
     *  |   browserOptions.frame = false
     *  | }
     *  | 
     *  | // Create the window.
     *  | let win = new BrowserWindow(browserOptions)
     *  | 
     *  | // Navigate.
     *  | if (browserOptions.transparent) {
     *  |   win.loadURL(`file://${__dirname}/index.html`)
     *  | } else {
     *  |   // No transparency, so we load a fallback that uses basic styles.
     *  |   win.loadURL(`file://${__dirname}/fallback.html`)
     *  | }
     *  | 
     */
    fun isAeroGlassEnabled(): Unit = 
        module.isAeroGlassEnabled()

    /**
     *  | 
     *  | const color = systemPreferences.getAccentColor() // `"aabbccdd"`
     *  | const red = color.substr(0, 2) // "aa"
     *  | const green = color.substr(2, 2) // "bb"
     *  | const blue = color.substr(4, 2) // "cc"
     *  | const alpha = color.substr(6, 2) // "dd"
     *  | 
     */
    fun getAccentColor(): String = 
        module.getAccentColor()

    /**
     */
    fun getColor(color: String): String = 
        module.getColor(color)

    /**
     */
    fun isInvertedColorScheme(): Boolean = 
        module.isInvertedColorScheme()

    // ~ Builders ------------------------------------------------------------------------------

    class PostNotificationUserInfo(
    )

    class PostLocalNotificationUserInfo(
    )

    class SubscribeNotificationUserInfo(
    )

    class SubscribeLocalNotificationUserInfo(
    )
}

