package vn.htv.fresher.todoapp.presentation.main

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.threeten.bp.LocalDateTime
import vn.htv.fresher.todoapp.R
import vn.htv.fresher.todoapp.databinding.FragmentMainBinding
import vn.htv.fresher.todoapp.domain.model.CategoryModel
import vn.htv.fresher.todoapp.presentation.common.BaseFragment
import vn.htv.fresher.todoapp.presentation.common.decoration.DefaultItemDecoration

class MainFragment : BaseFragment<FragmentMainBinding>() {

  // MainFragment class variables
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

  override val layoutId: Int
    get() = R.layout.fragment_main

  private val viewModel by viewModel<MainViewModel>()

  private val categoryAdapter by lazy {
    CategoryAdapter()
  }

  override fun init() {
    super.init()

    binding.viewModel       = viewModel
    binding.eventListeners  = EventListeners()

    viewModel.loadData()
  }

  override fun initUi() {
    super.initUi()
    categoryRecyclerView.apply {
      adapter = categoryAdapter
      addItemDecoration(DefaultItemDecoration(
        resources.getDimensionPixelSize(R.dimen.recyclerview_item_horizontal_margin),
        resources.getDimensionPixelSize(R.dimen.recyclerview_item_vertical_margin)))
    }
  }

  override fun registerLiveDataListener() {
    super.registerLiveDataListener()

    viewModel.mainItemList.observe(this, Observer {
      categoryAdapter.setItems(it)
    })
  }

  inner class EventListeners() {
    fun onNewCategory() {
    }
  }

  /**
   * Static definition
   */
  companion object {

    /**
     * Create MainFragment instance pattern
     */
    fun newInstance() = MainFragment()
  }
}