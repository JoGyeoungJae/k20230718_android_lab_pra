package com.example.test13_16_17_18.test16

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain2Binding
import com.example.test13_16_17_18.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    //인텐트, 후처리 2가지,ActivityResultLauncher
    //연락처(외부액) 여기에 접근해서 권한을 획득하고 후처리
    lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>
    lateinit var requestContactsLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //후처리하는 코드 부분
        requestPermissionLauncher = registerForActivityResult(
            //RequestMultiplePermissions:이부분은 다른 작업도 들어감
            //권한 획득 외에 데이터를 가져오는 작업을 더 많이 했음
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            //후처리의 결과는 Map 형태의 컬렉션에 담아서 오게됨 ->it
            //Map 키: 값의 형태로 제공
            //아래의 런처 함수가 수행 되어서 작업이 성공하며
            //후처리는 여기서 받아서 작업을 진행
            for (entry in it.entries) {
                if(entry.key == "android.permission.READ_CONTACTS" && entry.value) {
                    Log.d("kkang", "granted ok...")
                    val intent =
                        Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
                    //인텐트 후처리하는 런처 함수를 실행 하면
                    requestContactsLauncher.launch(intent)
                }else {
                    Toast.makeText(this, "required permission..", Toast.LENGTH_SHORT).show()
                }
            }

        }
        //후처리 2번쨰 작업, 연락처 정보를 가져온느 쿼리
        requestContactsLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            if(it.resultCode == RESULT_OK){
                Log.d("kkang", "${it.data?.data}")
                //프로바이더로 제공된 데이터에 접근하는 함수중 하나
                //select name, email from user where email='' and name=''
                val cursor = contentResolver.query(
                    it!!.data!!.data!!,
                    arrayOf<String>(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    ),
                    null,
                    null,
                    null
                )
                Log.d("kkang", "cursor size....${cursor?.count}")
                if (cursor!!.moveToFirst()) {
                    val name = cursor?.getString(0)
                    val phone = cursor?.getString(1)
                    binding.resultContact.text = "name: $name, phone: $phone"
                }
            }
        }
        binding.contactButton.setOnClickListener {
            //권한이 있는 지 확인하는 함수
            if (ContextCompat.checkSelfPermission(
                    this,
                    //허용하는 앱의 문자열이 일치해야, 원하는 정보를 가져올수 있음
                    "android.permission.READ_CONTACTS"
                ) !== PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(arrayOf("android.permission.READ_CONTACTS"))
            } else {
                val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
                requestContactsLauncher.launch(intent)
            }
        }
    }
}