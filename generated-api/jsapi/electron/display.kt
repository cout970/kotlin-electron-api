package jsapi.electron

class Display(
        var id: Float, 
        var rotation: Float, 
        var scaleFactor: Float, 
        var touchSupport: String, 
        var bounds: Rectangle, 
        var size: Size, 
        var workArea: Rectangle, 
        var workAreaSize: WorkAreaSize
) {

    val instance: dynamic = this

    // ~ Builders -------------------------------------------------------------------------------

    class Size(
        var height: Float, 
        var width: Float
    )

    class WorkAreaSize(
        var height: Float, 
        var width: Float
    )

}

