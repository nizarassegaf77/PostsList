package id.aruna.posts.mvvm

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.aruna.posts.R
import id.aruna.posts.mvvm.model.PostsModel
import id.aruna.posts.repository.PostsRepository
import kotlinx.android.synthetic.main.fragment_posts.*


class SetListFragment : Fragment() {
    private lateinit var vm: SetListViewModel
    private lateinit var adapter: SetListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_posts, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //BaseApplication.  instance.component.inject(this)

        adapter = SetListAdapter()
        rvSet.adapter = adapter

        val factory = SetListFactory(PostsRepository.instance)
        vm = ViewModelProviders.of(this, factory).get(SetListViewModel::class.java).apply {
            viewState.observe(this@SetListFragment, Observer(this@SetListFragment::handleState))
            srlSet.setOnRefreshListener {
                searchView.text.clear()
                getSets()
            }
            //searchView?.clearFocus()
            searchView?.setOnEditorActionListener { v, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    rvSet.scrollToPosition(0)
                    getSetsByTitle(v?.text.toString())
                    hideKeyboard(searchView)
                }
                true
            }

            searchView?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (count == 0) {
                        //getSets()
                        adapter.notifyDataSetChanged()
                    } else if (count > 2) {
                        getSetsByTitle(s.toString())
                        //adapter.notifyDataSetChanged()
                        /*searchPresenter?.page = 1
                        scrollListener.resetState()
                        searchPresenter?.getUserList(s.toString())*/
                    }
                }
            })
        }
    }

    private fun handleState(viewState: SetListViewState?) {
        viewState?.let {
            toggleLoading(it.loading)
            it.data?.let { data -> showData(data) }
            it.error?.let { error -> showError(error) }
        }
    }

    private fun showData(data: MutableList<PostsModel>) {
        adapter.updateData(data)
        tvSetError.visibility = View.GONE
        rvSet.visibility = View.VISIBLE
    }

    private fun showError(error: Exception) {
        tvSetError.text = error.message
        tvSetError.visibility = View.VISIBLE
        rvSet.visibility = View.GONE
    }

    private fun toggleLoading(loading: Boolean) {
        srlSet.isRefreshing = loading
    }

    private fun hideKeyboard(editText: EditText) {
        val imm =
            activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0)
    }

}
