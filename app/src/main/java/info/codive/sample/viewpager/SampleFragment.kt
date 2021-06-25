package info.codive.sample.viewpager

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

private const val BUNDLE_SAMPLE_DATA: String = "BUNDLE_SAMPLE_DATA"
private const val BUNDLE_IS_FIRST: String = "BUNDLE_IS_FIRST"
private const val BUNDLE_IS_LAST: String = "BUNDLE_IS_LAST"

class SampleFragment : Fragment() {
    private lateinit var onSampleFragmentActionListener: OnSampleFragmentActionListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        //Bundleから引数取得
        val sampleData: SampleData =
            requireArguments().getSerializable(BUNDLE_SAMPLE_DATA) as SampleData
        val isFirst: Boolean = requireArguments().getBoolean(BUNDLE_IS_FIRST)
        val isLast: Boolean = requireArguments().getBoolean(BUNDLE_IS_LAST)

        val view = inflater.inflate(R.layout.fragment_sample, container, false)
        view.findViewById<TextView>(R.id.page_title).text = sampleData.title

        //BACKボタンのイベント処理割り当て
        if (isFirst) {
            //最初のページの場合「BACKボタン」は非表示
            view.findViewById<TextView>(R.id.back_button).visibility = View.INVISIBLE
        } else {
            //前のページに戻る
            view.findViewById<TextView>(R.id.back_button).setOnClickListener {
                onSampleFragmentActionListener.onBackButtonClicked()
            }
        }

        //NEXTボタンのイベント処理割り当て
        if (isLast) {
            //最後のページの場合「NEXTボタン」を「ENDボタンに変更」
            val button = view.findViewById<TextView>(R.id.next_button)
            button.text = "END"
            button.setOnClickListener {
                onSampleFragmentActionListener.onEndButtonClicked()
            }
        } else {
            //次のページに進む
            view.findViewById<TextView>(R.id.next_button).setOnClickListener {
                onSampleFragmentActionListener.onNextButtonClicked()
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onSampleFragmentActionListener = if (context is OnSampleFragmentActionListener) {
            context
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement OnSampleFragmentActionListener"
            )
        }
    }

//    override fun onDetach() {
//        super.onDetach()
//        onSampleFragmentActionListener = null
//    }

    companion object {
        fun newInstance(sampleData: SampleData, isFirst: Boolean, isLast: Boolean): SampleFragment {
            val fragment = SampleFragment()
            //Bundleに詰めFragmentの引数として設定
            val bundle = Bundle()
            bundle.putSerializable(BUNDLE_SAMPLE_DATA, sampleData)
            bundle.putBoolean(BUNDLE_IS_FIRST, isFirst)
            bundle.putBoolean(BUNDLE_IS_LAST, isLast)
            fragment.arguments = bundle
            return fragment
        }
    }

    interface OnSampleFragmentActionListener {
        fun onBackButtonClicked()
        fun onNextButtonClicked()
        fun onEndButtonClicked()
    }
}