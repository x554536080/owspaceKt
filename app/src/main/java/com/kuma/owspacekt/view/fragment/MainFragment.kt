package com.kuma.owspacekt.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.kuma.owspacekt.R
import com.kuma.owspacekt.app.GlideApp
import com.kuma.owspacekt.model.entity.ItemJ
import com.kuma.owspacekt.view.activity.DetailActivity

class MainFragment : Fragment() {

    var title: String? = null

    @BindView(R.id.image_iv)
    lateinit var imageIv: ImageView

    @BindView(R.id.type_container)
    lateinit var typeContainer: LinearLayout

    @BindView(R.id.comment_tv)
    lateinit var commentTv: TextView

    @BindView(R.id.like_tv)
    lateinit var likeTv: TextView

    @BindView(R.id.read_count_tv)
    lateinit var readCountTv: TextView

    @BindView(R.id.title_tv)
    lateinit var titleTv: TextView

    @BindView(R.id.content_tv)
    lateinit var contentTv: TextView

    @BindView(R.id.author_tv)
    lateinit var authorTv: TextView

    @BindView(R.id.type_tv)
    lateinit var typeTv: TextView


    @BindView(R.id.home_advertise_iv)
    lateinit var homeAdvertiseIv: ImageView

    @BindView(R.id.pager_content)
    lateinit var pagerContent: RelativeLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.item_main_page, container, false)
        ButterKnife.bind(this, root)
        return root

    }


    override fun onResume() {
        super.onResume()
        val item = arguments?.getParcelable<ItemJ>("item")//泛型？ J？
        val model = Integer.valueOf(item?.model!!)

        if (model == 5) {//好像都是1 5应该是广告？
            pagerContent.visibility = View.GONE
            homeAdvertiseIv.visibility = View.VISIBLE
        } else {
            pagerContent.visibility = View.VISIBLE
            homeAdvertiseIv.visibility = View.GONE
            title = item.title
            GlideApp.with(requireContext()).load(item.thumbnail).centerCrop().into(imageIv)
            commentTv.text = item.comment
            likeTv.text = item.good
            readCountTv.text = item.view
            titleTv.text = title
            contentTv.text = item.excerpt
            authorTv.text = item.author
            typeTv.text = item.category
        }

        typeContainer.setOnClickListener {
            var intent: Intent? = null

            when (model) {
                5 -> startActivity(intent)
                3 -> {

                }
                2 -> {

                }
                1 -> {
                    intent = Intent(activity, DetailActivity::class.java)
                }
                else -> {

                }
            }


        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }

    companion object {
        fun instance(item: ItemJ): Fragment {
            val fragment = MainFragment()
            val bundle = Bundle()
            bundle.putParcelable("item", item)
            fragment.arguments = bundle
            return fragment
        }
    }
}