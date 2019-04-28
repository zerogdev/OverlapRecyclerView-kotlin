package com.zerogdev.app

import android.graphics.Rect
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val imageArray : ArrayList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageArray.add(R.drawable.image1)
        imageArray.add(R.drawable.image2)
        imageArray.add(R.drawable.image3)
        imageArray.add(R.drawable.image4)
        imageArray.add(R.drawable.image5)
        imageArray.add(R.drawable.image6)
        imageArray.add(R.drawable.image7)
        imageArray.add(R.drawable.image8)
        imageArray.add(R.drawable.image9)
        imageArray.add(R.drawable.image10)


        overlapRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        overlapRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                val params: RecyclerView.LayoutParams = view.layoutParams as RecyclerView.LayoutParams
                val position = params.viewAdapterPosition
                if (position != 0) {
                    //move negative left margin (-25dp)
                    val v: Int= (25 * resources.displayMetrics.density).toInt()
                    outRect.set(-v, 0, 0, 0)
                }
            }
        })

        overlapRecyclerView.adapter = MyAdapter(imageArray) {
            position -> overlapRecyclerView.selectedPosition = position
        }

    }

    class MyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {

        val items: ArrayList<Int>
        val clickListener: (v:Int) -> Unit

        constructor(items: ArrayList<Int>, clickListener: (v:Int) -> Unit) : super() {
            this.items = items
            this.clickListener = clickListener
        }

        override fun onCreateViewHolder(view: ViewGroup, i: Int): RecyclerView.ViewHolder {
            return MyHolder(LayoutInflater.from(view.context).inflate(R.layout.item_image, view, false))
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, i: Int) {
            if (holder is MyHolder) {
                holder.imageView.setBackgroundResource(items[i])
                holder.itemView.setOnClickListener {
                    clickListener.invoke(i)
                }
            }
        }

    }

    class MyHolder : RecyclerView.ViewHolder {

        var imageView:ImageView

        constructor(itemView: View) : super(itemView) {
            imageView = itemView.findViewById(R.id.imageView)
        }
    }
}
