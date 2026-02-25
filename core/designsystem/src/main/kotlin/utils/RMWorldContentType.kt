package utils
import androidx.window.core.layout.WindowWidthSizeClass
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowSizeClass

enum class RMWorldContentType {
    MOBILE_PORTRAIT,
    MOBILE_LANDSCAPE,
    TABLET_PORTRAIT,
    TABLET_LANDSCAPE,
}
object RMWorldWindowInfo{
    fun getContentType(windowSizeClass: WindowSizeClass): RMWorldContentType{
      val widthClass = windowSizeClass.windowWidthSizeClass
      val heightClass = windowSizeClass.windowHeightSizeClass
     return  when{
           widthClass== WindowWidthSizeClass.COMPACT && heightClass== WindowHeightSizeClass.MEDIUM
           ->{
               RMWorldContentType.MOBILE_PORTRAIT
           }
           widthClass == WindowWidthSizeClass.MEDIUM && heightClass == WindowHeightSizeClass.COMPACT
           ->{
               RMWorldContentType.MOBILE_LANDSCAPE
           }
           widthClass== WindowWidthSizeClass.MEDIUM && heightClass== WindowHeightSizeClass.EXPANDED->{
               RMWorldContentType.TABLET_PORTRAIT

           }
           widthClass== WindowWidthSizeClass.EXPANDED && heightClass== WindowHeightSizeClass.MEDIUM->{
               RMWorldContentType.TABLET_LANDSCAPE
           }
           else->{
               RMWorldContentType.MOBILE_LANDSCAPE
           }
       }
    }
}