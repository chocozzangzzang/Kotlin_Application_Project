package com.example.myapplication.FishingContent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.FishingContent.model.FishContest
import com.example.myapplication.databinding.ActivityFishingContentBinding
import com.example.myapplication.FishingContent.model.Poster
import com.example.myapplication.FishingContent.recycler.PosterAdapter
import com.example.myapplication.weather_imgfind.net.APIApplication
import com.google.firebase.firestore.FirebaseFirestore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FishingContent : AppCompatActivity() {


    private lateinit var binding: ActivityFishingContentBinding
    private var posterlist= mutableListOf<Poster>()
    lateinit var psAdapter : PosterAdapter
    lateinit var fishingService  : FishService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFishingContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener{
            posterlist.clear()
            loadFirestoreData("포스터")
        }
//        val imageView: ImageView =findViewById(R.id.myImageView)
//
//        imageView.setOnClickListener {
//            val dialog = Dialog(this)
//            dialog.setContentView(R.layout.dialog_image_preview)

//            val previewImage: ImageView = dialog.findViewById(R.id.previewImage)
//            previewImage.setImageResource(R.drawable.azz) // 확대해서 보여줄 이미지 설정

//            dialog.show()
//        }
//        val imageUrl = "여기에_파이어베이스_이미지_URL_입력"
//
//        val smallImageView: ImageView = findViewById(R.id.ps)
//        smallImageView.setOnClickListener {
//            val dialog = ImagePreviewDialog(this, imageUrl)
//            dialog.show()
//        }


    }



    private fun loadFirestoreData(path: String) {
        val fishService = (applicationContext as APIApplication).fishService
        val contest = fishService.fishContestList()
        contest.enqueue(object : Callback<List<FishContest>> {
            override fun onResponse(call: Call<List<FishContest>>, response: Response<List<FishContest>>) {
                val contestdata = response.body()
                viewBindingFunc(contestdata!!)
            }
            override fun onFailure(call: Call<List<FishContest>>, t: Throwable) {
            }
        })
//        db.collection(path).get()
//            .addOnSuccessListener {  querySnapshot  ->
//                val dataToShow = StringBuilder()
//                for (documentSnapshot in querySnapshot) {
//                    if (documentSnapshot.exists()) {
//                        val data = documentSnapshot.data
//                        val title = data?.get("title") as String // 필드 이름을 적절히 변경하세요
////                        val date = data?.get("date") as String
////                        val location = data?.get("location") as String
////                        val fs =data?.get("fs") as String
//                        val ps = data?.get("ps") as String
//                        posterlist.add(Poster(title, ps ))
//                        //dataToShow.append(info).append("\n")
//                    }
//                }
//                viewBindingFunc(posterlist)
//            }
//            .addOnFailureListener { exception ->
//                //binding.textView.text = "데이터를 불러오는 중에 오류가 발생했습니다."
//                // 오류 처리 코드를 여기에 추가하세요
//            }

    }
    fun viewBindingFunc(fishes : List<FishContest>) {
        psAdapter = PosterAdapter(fishes)
        binding.recyclerView.adapter = psAdapter

    }

}