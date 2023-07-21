package com.example.test8_9_joj

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import com.example.test8_9_joj.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val TAG :String ="MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Long츨릭 이벤트 리스터 확인
        //바인딩 작업
        val binding = ActivityMainBinding.inflate(layoutInflater)
        //뷰 바인딩 적용
        setContentView(binding.root)

        binding.button.setOnClickListener {
            Log.d(TAG,"클릭이벤트")
        }
        binding.button.setOnLongClickListener{
           Log.d(TAG,"롱클릭이벤트")
            true
        }

    }
    //리스너 설정, 결과 확인
    // 공식 문서 경로
    //경로
    //https://developer.android.com/guide/topics/ui/controls/checkbox?hl=ko
    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.checkbox_meat -> {
                    if (checked) {
                        Toast.makeText(this@MainActivity,"고기선택",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity,"고기선택해제",Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.checkbox_cheese -> {
                    if (checked) {
                        Toast.makeText(this@MainActivity,"치즈선택",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity,"치즈선택해제",Toast.LENGTH_SHORT).show()
                    }
                }
                // TODO: Veggie sandwich
            }
        }
    }




    //이벤트 핸들러 처리하는  방법 3가지,, 그중에서 SAM기법, Single Abstract Method
    //추상 메서드가 하나 인 인터페이스 -> 바로 람다식으로 사용 가능한 기법
    //찰고 경로
    //https://github.com/lsy3709/AndroidLab/blob/master/test8/src/main/java/com/example/test8/MainActivity221.kt
    //사전 작업
    //새로 난든 모듈에는 빌드, 그레이들 파일에 뷰바인딩 설정이 없음.  설정
    //참고, 앞에 모듈에 설정코드, 복붙,
    //체크박스 뷰, 버튼 뷰 설정.
    //리스너 설정, 결과 확인




    //전체 원본 소스 코드
    //https://github.com/lsy3709/AndroidLab/blob/master/test8/src/main/java/com/example/test8/MainActivity.kt
    /*override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                //로깅 관련된 프로그램. d :debug,e:err
                Log.d(TAG,
                    "Touch down event x: ${event.x}, rawX: ${event.rawX} Touch down event y: ${event.y}, rawX: ${event.rawY}")
            }
            MotionEvent.ACTION_UP -> {
                Log.d(TAG, "Touch up event")
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(TAG, "Touch move event x: ${event.x}, rawX: ${event.rawX} Touch move event y: ${event.y}, rawX: ${event.rawY}")
            }
        }
        return super.onTouchEvent(event)
    }
    //백 키 이벤트 따로 처리하는 함수
    override fun onBackPressed() {
        Log.d(TAG,"back키 사용됨")
    }*/



    //키이벤트
    //경로
    //https://github.com/lsy3709/AndroidLab/blob/master/test8/src/main/java/com/example/test8/MainActivity218.kt
    /*override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> Log.d(TAG, "BACK Button을 눌렀네요")
            KeyEvent.KEYCODE_VOLUME_UP -> Log.d(TAG, "Volume Up 키를 눌렀네요")
            KeyEvent.KEYCODE_VOLUME_DOWN -> Log.d(TAG, "Volume Down 키를 눌렀네요")
            KeyEvent.KEYCODE_0 -> Log.d(TAG, "0 키를 눌렀네요")
            KeyEvent.KEYCODE_ENTER -> Log.d(TAG, "ENTER 키를 눌렀네요")
        }
        return super.onKeyDown(keyCode, event)
    }*/
}