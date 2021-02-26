package com.geraa1985.phrasebook.ca_d_frameworks.imgLoader

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.geraa1985.phrasebook.R
import com.geraa1985.phrasebook.ca_c_adapters.viewmodels.ILoadImage

class GlideImgLoader: ILoadImage<ImageView, RequestOptions> {

    override fun loadInto(
        url: String,
        container: ImageView,
        options: RequestOptions?,
        progressGone: () -> Unit
    ) {
        options?.let{
            Glide
                .with(container.context)
                .load(url)
                .apply(options)
                .error(R.drawable.ic_load_error_vector)
                .placeholder(R.drawable.ic_no_photo_vector)
                .listener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressGone()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressGone()
                        return false
                    }
                })
                .into(container)
        } ?: run {
            Glide
                .with(container.context)
                .load(url)
                .error(R.drawable.ic_load_error_vector)
                .placeholder(R.drawable.ic_no_photo_vector)
                .listener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressGone()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressGone()
                        return false
                    }
                })
                .into(container)
        }
    }

}