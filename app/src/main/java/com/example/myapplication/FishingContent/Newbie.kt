package com.example.myapplication.FishingContent

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.FishingContent.model.FishBait
import com.example.myapplication.FishingContent.model.FishRope
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityNewbieBinding
import com.example.myapplication.FishingContent.model.Guide
import com.example.myapplication.FishingContent.recycler.NewbieAdpater
import com.example.myapplication.FishingContent.recycler.NewbieAdpater2
import com.example.myapplication.weather_imgfind.net.APIApplication
import com.google.firebase.firestore.FirebaseFirestore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Newbie : AppCompatActivity() {
    private lateinit var binding: ActivityNewbieBinding
    private var newbielist = mutableListOf<Guide>()
    lateinit var nbAdapter : NewbieAdpater
    lateinit var nbAdapter2 : NewbieAdpater2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewbieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            newbielist.clear()
            Log.d("testtest1234", "asdfasdfasdfasdfasfasd")
            loadFirestoreData("매듭")  // 원하는 문서 ID를 적절히 변경하세요
        }

        binding.button1.setOnClickListener {
            newbielist.clear()
            Log.d("testtest1234", "werqwrweqteqwtqwtqwetwetqwet")
            loadFirestoreData("미끼")  // 원하는 문서 ID를 적절히 변경하세요
        }
    }

    private fun loadFirestoreData(path: String) {
        Log.d("testtest1234", "asdfasdfas111111111dfasdfasfasd")
        val fishService = (applicationContext as APIApplication).fishService
        if(path == "매듭") {
            Log.d("testtest1234", "asdf15135135135asdfasdfasdfasfasd")
            val ropeService = fishService.fishRopeList()
            ropeService.enqueue(object : Callback<List<FishRope>> {
                override fun onResponse(call: Call<List<FishRope>>, response: Response<List<FishRope>>
                ) {
                    val rope = response.body()
                    Log.d("testtest1234", "$rope")
                    viewBindingFunc(rope!!)
                }

                override fun onFailure(call: Call<List<FishRope>>, t: Throwable) {
                    Log.d("testtest1234", "11366")
                }
            })
        }

        if(path == "미끼") {
            val baitService = fishService.fishBaitList()
            baitService.enqueue(object : Callback<List<FishBait>> {
                override fun onResponse(call: Call<List<FishBait>>, response: Response<List<FishBait>>
                ) {
                    val rope = response.body()
                    Log.d("testtest1234", "$rope")
                    viewBindingFunc2(rope!!)
                }

                override fun onFailure(call: Call<List<FishBait>>, t: Throwable) {

                }
            })
        }
//        db?.collection(path)?.get()
//            ?.addOnSuccessListener {  querySnapshot  ->
//                val dataToShow = StringBuilder()
//                for (documentSnapshot in querySnapshot) {
//                    if (documentSnapshot.exists()) {
//                        val data = documentSnapshot.data
//                        val title = data?.get("title") as String // 필드 이름을 적절히 변경하세요
//                        val url = data?.get("url") as String
//                        val thumbnail = data?.get("thumbnail") as String
//                        Log.d("tt", "$title, $url, $thumbnail")
//                        newbielist.add(Guide(title, url, thumbnail))
//                        //dataToShow.append(info).append("\n")
//                    }
//                }

                /*
                if (dataToShow.isNotEmpty()) {
                    binding.textView.text = dataToShow.toString()
                } else {
                    binding.textView.text = "데이터가 없습니다."
                }*/
//            }
//            ?.addOnFailureListener { exception ->
//                //binding.textView.text = "데이터를 불러오는 중에 오류가 발생했습니다."
//                // 오류 처리 코드를 여기에 추가하세요
//            }

    }
    fun viewBindingFunc(newbies : List<FishRope>) {
        Log.d("tt", "=====$newbies=====")
        nbAdapter = NewbieAdpater(newbies)
        //Log.d("tt", "=====${nbAdapter.guides.toString()}=====")
        binding.recyclerView2.adapter = nbAdapter
//        val binding = ActivityNewbieBinding.inflate(layoutInflater)
//        val recycle  = binding.recyclerView2

    }

    fun viewBindingFunc2(newbies : List<FishBait>) {
        Log.d("tt", "=====$newbies=====")
        nbAdapter2 = NewbieAdpater2(newbies)
        //Log.d("tt", "=====${nbAdapter.guides.toString()}=====")
        binding.recyclerView2.adapter = nbAdapter2
//        val binding = ActivityNewbieBinding.inflate(layoutInflater)
//        val recycle  = binding.recyclerView2

    }
}
