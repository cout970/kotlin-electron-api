package jsapi.electron

object systemPreferences {

    private val module: dynamic = js("require('electron').systemPreferences")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun isDarkMode(): Boolean = 
        module.isDarkMode()

    fun isSwipeTrackingFromScrollEventsEnabled(): Boolean = 
        module.isSwipeTrackingFromScrollEventsEnabled()

    fun postNotification(event: String, userInfo: PostNotificationUserInfo.() -> Unit): Unit = 
        module.postNotification(event, userInfo.let { PostNotificationUserInfo().apply(it) })

    fun postLocalNotification(event: String, userInfo: PostLocalNotificationUserInfo.() -> Unit): Unit = 
        module.postLocalNotification(event, userInfo.let { PostLocalNotificationUserInfo().apply(it) })

    fun subscribeNotification(event: String, callback: (event: String, userInfo: SubscribeNotificationUserInfo.() -> Unit) -> Unit): Unit = 
        module.subscribeNotification(event, callback)

    fun unsubscribeNotification(id: Int): Unit = 
        module.unsubscribeNotification(id)

    fun subscribeLocalNotification(event: String, callback: (event: String, userInfo: SubscribeLocalNotificationUserInfo.() -> Unit) -> Unit): Unit = 
        module.subscribeLocalNotification(event, callback)

    fun unsubscribeLocalNotification(id: Int): Unit = 
        module.unsubscribeLocalNotification(id)

    fun getUserDefault(key: String, type: String): Unit = 
        module.getUserDefault(key, type)

    fun setUserDefault(key: String, type: String, value: String): Unit = 
        module.setUserDefault(key, type, value)

    fun isAeroGlassEnabled(): Unit = 
        module.isAeroGlassEnabled()

    fun getAccentColor(): String = 
        module.getAccentColor()

    fun getColor(color: String): String = 
        module.getColor(color)

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

