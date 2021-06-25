package info.codive.sample.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager

private const val PAGE_MAX: Int = 5

class MainActivity : AppCompatActivity(), SampleFragment.OnSampleFragmentActionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //テストデータ作成
        val listItem = createTestData()

        val viewPager = findViewById<ViewPager>(R.id.sample_view_pager)
        val pagerAdapter = MainPagerAdapter(supportFragmentManager, listItem)
        viewPager.adapter = pagerAdapter

//        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//            override fun onPageScrolled(
//                position: Int,
//                positionOffset: Float,
//                positionOffsetPixels: Int
//            ) {}
//            override fun onPageSelected(position: Int) {}
//            override fun onPageScrollStateChanged(state: Int) {}
//        })
    }

    //FragmentのBACKボタンのイベント処理
    override fun onBackButtonClicked() {
        val viewPager = findViewById<ViewPager>(R.id.sample_view_pager)
        viewPager.setCurrentItem(viewPager.currentItem - 1, true) //Pageを移動
    }

    //FragmentのNEXTボタンのイベント処理
    override fun onNextButtonClicked() {
        val viewPager = findViewById<ViewPager>(R.id.sample_view_pager)
        viewPager.setCurrentItem(viewPager.currentItem + 1, true) //Pageを移動
    }

    //FragmentのENDボタンのイベント処理
    override fun onEndButtonClicked() {
        Toast.makeText(this, "Last Fragment", Toast.LENGTH_SHORT).show()
    }

    // テストデータ作成
    private fun createTestData(): MutableList<SampleData> {
        val listItem = mutableListOf<SampleData>()
        for (i in 1..PAGE_MAX) {
            listItem.add(SampleData("Title $i"))
        }
        return listItem
    }
}