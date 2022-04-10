package edu.co.icesi.camgaltest

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import edu.co.icesi.camgaltest.databinding.ActivityMainBinding
import edu.co.icesi.camgaltest.modal.AddContactFragment
import edu.co.icesi.semana4kotlina.UtilDomi
import java.io.File
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private val binding:ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var file:File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        requestPermissions(arrayOf(
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA
        ), 11)

        val galLauncher= registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(), ::onGalleryResult)

        val camLauncher= registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(), ::onCameraResult)

        binding.galBtn.setOnClickListener{
            val i= Intent(Intent.ACTION_GET_CONTENT)
            i.type= "image/*"
            galLauncher.launch(i)
        }


        binding.camBtn.setOnClickListener{
            val i= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            file= File("${getExternalFilesDir(null)}/photo.png")
            val uri = FileProvider.getUriForFile(this, "edu.co.icesi.camgaltest", file!!)
            i.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            Log.e(">>", file?.path.toString())
            camLauncher.launch(i)
        }

        binding.addBtn.setOnClickListener {
            AddContactFragment.newInstance().show(supportFragmentManager, "addContact")
        }
    }

    private fun onCameraResult(activityResult: ActivityResult){
        //Thumbnail
        /*val bitmap=activityResult.data?.extras?.get("data") as Bitmap
        binding.imageView.setImageBitmap(bitmap)*/


        val bitmap= BitmapFactory.decodeFile(file?.path)


        val scaledBitmap=
            Bitmap.createScaledBitmap(
                bitmap,
                bitmap.width/2,
                bitmap.height/2,
                true
            )

        binding.imageView.setImageBitmap(scaledBitmap)

        Log.e("bitmap", bitmap.toString())
        Log.e("file", file.toString())

    }

    private fun onGalleryResult(activityResult: ActivityResult){
        val uri= activityResult.data?.data

        val path= UtilDomi.getPath(this, uri!!)

        if(activityResult.resultCode == RESULT_OK){
            val uriImage = activityResult.data?.data
            uriImage?.let {
                binding.imageView.setImageURI(uriImage)
            }
        }
        Log.e("file", file.toString())

        //val bitmap= BitmapFactory.decodeFile(file?.path)

       /* val bitmap= BitmapFactory.decodeFile(path!!)
       // binding.imageView.setImageBitmap(bitmap)*/

        Log.e(">>>>", uri.toString())
        Log.e(">>>>", path!!)
    }
}