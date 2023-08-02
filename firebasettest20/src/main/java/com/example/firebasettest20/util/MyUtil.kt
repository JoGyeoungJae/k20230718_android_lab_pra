package com.example.firebasettest20.util

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.Date

/*해당, 코틀린  파일인테 한수를 리팩토링, 파일로 따로 분리해서
* 자주 사용하는 기능을 분리
* 매채변수에 타입이 액티비티로 사용되는 부분 확인*/
fun myCheckPermission(activity: AppCompatActivity) {
/*인텐트 후처리하는 함수
* 권한 여부를 확인하는 후처리 기능         ActivityResultContracts.RequestPermission()*/
    val requestPermissionLauncher = activity.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {//앱이 시작할때 미디어 이미지 저장소에 접근이 가능하면
            Toast.makeText(activity, "권한 승인", Toast.LENGTH_SHORT).show()
        } else {//33버전 이후 세분화된 미디어 권한 확인 부탁함
            Toast.makeText(activity, "권한 거부", Toast.LENGTH_SHORT).show()
        }
    }
/*권한 체크부분 변경 Manifest.permission.READ_MEDIA_IMAGES
* 이미지에 접근 권함 물어봄.*/
    if (ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.READ_MEDIA_IMAGES
        ) !== PackageManager.PERMISSION_GRANTED
    ) {
        requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
    }
}

fun dateToString(date: Date): String {
    val format = SimpleDateFormat("yyyy-MM-dd")
    return format.format(date)
}

/*이미지를 압축하는 기능이 없음
* 이미지를 압축하는 함수를 가져와서 여기서 선언해서 해당 액티비티에서 사용함*/