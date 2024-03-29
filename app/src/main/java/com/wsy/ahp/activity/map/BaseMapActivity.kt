package com.wsy.ahp.activity.map



import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.amap.api.maps.AMap
import com.amap.api.maps.MapView
import com.amap.api.maps.model.MyLocationStyle
import com.wsy.ahp.R


class BaseMapActivity : AppCompatActivity() {
    var mapView: MapView? = null
    var aMap: AMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_map)

        mapView = findViewById<View>(R.id.basicMap) as MapView
        mapView!!.onCreate(savedInstanceState) // 此方法必须重写

        aMap = mapView!!.getMap()

        val myLocationStyle: MyLocationStyle
        myLocationStyle =
            MyLocationStyle() //初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。

        myLocationStyle.interval(2000) //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。




    }




    override fun onDestroy() {
        super.onDestroy()
        //在activity执行onDestroy时执行mapView.onDestroy()，销毁地图
        mapView!!.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        //在activity执行onResume时执行mapView.onResume ()，重新绘制加载地图
        mapView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        //在activity执行onPause时执行mapView.onPause ()，暂停地图的绘制
        mapView!!.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //在activity执行onSaveInstanceState时执行mapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView!!.onSaveInstanceState(outState)
    }

}