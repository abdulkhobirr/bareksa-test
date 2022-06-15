package khobir.abdul.bareksa_test.utils

import android.graphics.drawable.Drawable
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.kennyc.view.MultiStateView
import khobir.abdul.bareksa_test.R

fun MultiStateView.showDefaultState() {
    this.viewState = MultiStateView.ViewState.CONTENT
}

fun MultiStateView.showEmptyState() {
    this.viewState = MultiStateView.ViewState.EMPTY
}

fun MultiStateView.showLoadingState() {
    this.viewState = MultiStateView.ViewState.LOADING
}

fun MultiStateView.showErrorState(
    errorMessage: String? = null,
    title: String? = null,
    drawable: Drawable? = null,
    errorAction: (() -> Unit)? = null
) {
    this.viewState = MultiStateView.ViewState.ERROR

    errorMessage?.let {
        val tvError =
            this.getView(MultiStateView.ViewState.ERROR)?.findViewById<TextView>(R.id.tv_error)
        tvError?.text = errorMessage
    }

    title?.let {
        val tvTitle =
            this.getView(MultiStateView.ViewState.ERROR)?.findViewById<TextView>(R.id.tv_title)
        tvTitle?.text = title
    }

    drawable?.let {
        val imgError =
            this.getView(MultiStateView.ViewState.ERROR)?.findViewById<ImageView>(R.id.img_error)
        imgError?.setImageDrawable(drawable)
    }

    val btnError =
        this.getView(MultiStateView.ViewState.ERROR)?.findViewById<Button>(R.id.btn_error)

    btnError?.setOnClickListener { errorAction?.invoke() }
}