package info.codive.sample.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * FragmentPagerAdapterは、ページの切り替えの際に一度生成したFragmentをそのままメモリに保持します。
 * それに対してFragmentStatePagerAdapterは、Fragmentを一旦破棄し、Fragmentの状態のみを保持します。
 * ページ数が多い場合はFragmentStatePagerAdapterを使用します。
 * ただし、状態を保存する必要があるので、対象のFragment内のsavedInstanceStateを使って前回の状態を復元します。
 */
class MainPagerAdapter constructor(
    fragmentManager: FragmentManager,
    private val list: List<SampleData>
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
//) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        val isFirst = position == 0
        val isLast = position == count - 1
        return SampleFragment.newInstance(list[position], isFirst, isLast)
    }

    override fun getCount(): Int = list.size //ページ数
}