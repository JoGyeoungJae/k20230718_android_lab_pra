package com.example.test13_16_17_18.test16

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityImageBinding
import com.example.test13_16_17_18.databinding.ActivityMainBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class ImageActivity : AppCompatActivity() {
    lateinit var binding: ActivityImageBinding
    lateinit var filePath: String
    //갤러리와 카메라 연동해서 인텐트의 후처리 작업을 이용해서
    //비트맵 또는 drawabel 타입으로 이미지 처리하는 부분 봅니다.
    //주의 사항 미디어 서버에 접근하는 허가 부분이 조금 변경이 되어서 소개 후 사용하고
    //콘텐츠 프로바이더 부분의 authoritites 부분 주의 해서 작업 따라 하시면 됩니다.
    //내용은 그대로 재사용 코드 리뷰 할때 설명 잘 보시면 됩니다.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //gallery request launcher..................
        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            try {
                val calRatio = calculateInSampleSize(
                    it.data!!.data!!,
                    resources.getDimensionPixelSize(R.dimen.imgSize),
                    resources.getDimensionPixelSize(R.dimen.imgSize)
                )
                val option = BitmapFactory.Options()
                option.inSampleSize = calRatio

                var inputStream = contentResolver.openInputStream(it.data!!.data!!)
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                inputStream!!.close()
                inputStream = null

                bitmap?.let {
                    Log.d("kkang", "결과 뷰에 적용 전")
                    binding.userImageView.setImageBitmap(bitmap)
                    Log.d("kkang", "결과 뷰에 적용 후")
                } ?: let{
                    Log.d("kkang", "bitmap null")
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
        //지도 맵 버튼
        binding.mapButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("geo : 37.5662952,126.9779451")
            )
            startActivity(intent)
        }
        //갤러리에서 작업
        binding.galleryButton.setOnClickListener {
            //gallery app........................
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }

        //camera request launcher.................후처리를 하는 함수
        val requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            //calRatio 임의로 만든 변수 이미지 처리시 크기 문제는 메모리 부족현상이 생겨
            //크기를 조정하는 함수
            //calculateInSampleSize 함수 : 저자가 임의로 만든 원본의 크기를 줄이는 비율을 구하는식
            //로직은 원본의 크기를 반으로 줄여서 비율 조사를 해나가는 방식
            //결론, 크기를 줄여나가는 함수
            //정수값
            val calRatio = calculateInSampleSize(
                //원본 데이터
                Uri.fromFile(File(filePath)),
                //출력할 이미지 크기를 임의 지정
                //현재 리소스 폴더에 150DP로 지정
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
            //BitmapFactory 비트맵 타입으로 이미지를 그대로 처리시 문제가 됨 oom 메모리 누수
            //옵션을 정해서 이미지를 처리해야함
            val option = BitmapFactory.Options()
            //calRatio 원본의 사진을 특정 비율에 맞게 줄인 결과 값
            //EX)  option.inSampleSize=4
            //12mb->3mb사이즈로 줄여나감(크기가 조정됨)
            option.inSampleSize = calRatio
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                binding.userImageView.setImageBitmap(bitmap)
            }
        }

        //카메라 이용
        binding.cameraButton.setOnClickListener {
            //camera app......................
            //파일 준비...............
            Log.d("kkang", "11111111111111111111111111")
            val timeStamp: String =
                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            //안드로이드 시스템에서 정하는 Environment.DIRECTORY_PICTURES 정해져 있음
            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
            )
            Log.d("kkang", "111111111111111111111111112222222")
            //물리 파일의 실제 경로
            filePath = file.absolutePath
            Log.d("kkang", filePath)
            //카메라에서 찍은 사진에 접근 하기위해서 콘텐츠 프로바이더에 요청
            //요청시, 매니페스트에서 정한 같은 문자열을 아용합니다.
            //com.example.test13_16_17_18.test16.fileprovider
            Log.d("kkang", "111111111111111111111111112222222333333333")
            val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "com.example.test13_16_17_18.fileprovider",
                file
            )
            //현재 앱-> 외부 앱으로 가기 위해서, 스스템에게 인텐트로 전달
            //인텐트의 메세지 내용은 액션의 문자열 카메라 앱
            //데이터의 내용은 사진의 출력(카메라로 찍은 사진), photoURi에 담기
            Log.d("kkang", "111111111111111111111111113333333333333")
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            //후처리 함수를 호출하면, 위에 정의된 후처리 작업하는 함수로 이동
            //카메라 촬영 후 체크 한 다음, 되돌렸을때 작업은 위
            Log.d("kkang", "11111111111111111111111111444444444444")
            requestCameraFileLauncher.launch(intent)
            Log.d("kkang", "1111111111111111111111111155555555555555555")

        }
    }
    //이미지의 크기를 줄이는 로직
    //첫 매개변수 : 사진 원본 데이터
    // 두번째, 세번째 매개변수:원하는 가로세로 크기(출력 원하는 사진의 크기)
    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        //비트맵 객체 그대로 사용하면, 사진 원본을 그대로 사용해서 메모리 부족 현상 생김
        //그래서 옵션이라는 속성을 사용
        val options = BitmapFactory.Options()
        //실제 비트맵 색체를 생성하는 것이 아니고 옵션만 설정하겠다라는 의미
        options.inJustDecodeBounds = true
        try {
            //실제 원본 사진의 물리 경로에 접근해서, 바이트로 읽음
            //사진을 읽은 바이트 단위
            var inputStream = contentResolver.openInputStream(fileUri)

            //inJustDecodeBounds 값을 true 로 설정한 상태에서 decodeXXX() 를 호출.
            //로딩 하고자 하는 이미지의 각종 정보가 options 에 설정 된다.
            BitmapFactory.decodeStream(inputStream, null, options)
            //읽었던 원본의 사진의 메모리 사용은 반납.
            inputStream!!.close()
            //객체를 null 초기화
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //비율 계산........................
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        //inSampleSize 비율 계산
        //height,width 원본의 가로 세로 크기
        //reqHeight,reqWidth원하는 크기 사이즈
        //이것 보다 크면 우너본 사이즈를 반으로 줄이는 작업을 진행
        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

}