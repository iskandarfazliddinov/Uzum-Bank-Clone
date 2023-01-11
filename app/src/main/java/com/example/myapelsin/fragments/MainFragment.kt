package com.example.myapelsin.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapelsin.Adapters.AccessAdapter
import com.example.myapelsin.Adapters.HistoryAdapter
import com.example.myapelsin.Adapters.PaymentAdapter
import com.example.myapelsin.Adapters.SelectAdapter
import com.example.myapelsin.Models.AccessModel
import com.example.myapelsin.Models.HistoryModel
import com.example.myapelsin.Models.PaymentModel
import com.example.myapelsin.Models.SelectModel
import com.example.myapelsin.R
import com.example.myapelsin.activity.QRActivity
import com.example.myapelsin.databinding.FragmentMainBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentMainBinding
    private lateinit var accessAdapter: AccessAdapter
    private lateinit var paymentAdapter: PaymentAdapter
    private lateinit var selectAdapter: SelectAdapter
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var historyModel: ArrayList<HistoryModel>
    private lateinit var accessModel: ArrayList<AccessModel>
    private lateinit var paymentModel: ArrayList<PaymentModel>
    private lateinit var selectModel: ArrayList<SelectModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        setData()
        dataAdds()
        setAdapter()
        setRecyclerView()

        binding.apply {
            mainLayout.searchView.setQueryHint(
                Html.fromHtml(
                    "<font color = #ffffff>" + getResources().getString(
                        R.string.hintSearchMess
                    ) + "</font>"
                )
            )

            mainLayout.visibiltyControl.setOnClickListener {
                visibilty()
            }

            swipeRefreshLayout.setOnRefreshListener {
                refresh()
            }

            floatButton.setOnClickListener {
                swipeRefreshLayout.isRefreshing = true
                refresh()
            }

            btnQR.setOnClickListener {
                findNavController().navigate(R.id.QRActivity)
            }

            openContact.setOnClickListener{
                var intent = Intent(Intent.ACTION_DEFAULT, ContactsContract.Contacts.CONTENT_URI)
                startActivityForResult(intent, 1)
            }
        }

        return binding.root
    }

    private fun visibilty() {
        binding.mainLayout.apply {
            if (txtMoney.text != "*****") {
                visibiltyControl.setImageResource(R.drawable.ic_baseline_visibility_24)
                txtMoney.text = "*****"
                cardMoney.text = "*****"
            } else {
                visibiltyControl.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                txtMoney.text = "775 000 UZS"
                cardMoney.text = "775 000 UZS"
            }
        }
    }

    private fun dataAdds() {
        AccessModels()
        Payments()
        Selects()
        Historys()
    }

    private fun Historys() {
        historyModel.add(
            HistoryModel(
                R.drawable.uzcard,
                "ISKANDAR",
                140000,
                "22.12.2022 16:08:44",
                "O`tkazma"
            )
        )
        historyModel.add(
            HistoryModel(
                R.drawable.humos,
                "ISKANDAR",
                240000,
                "22.12.2022 09:05:15",
                "To`lov"
            )
        )
        historyModel.add(
            HistoryModel(
                R.drawable.beline,
                "Beeline",
                -10000,
                "21.12.2022 14:10:15",
                "To`lov"
            )
        )
        historyModel.add(
            HistoryModel(
                R.drawable.uzcard,
                "ISKANDAR",
                180000,
                "20.12.2022 22:05:44",
                "O`tkazma"
            )
        )
        historyModel.add(
            HistoryModel(
                R.drawable.ucell,
                "Ucell",
                -190000,
                "19.12.2022 19:18:44",
                "O`tkazma"
            )
        )
        historyModel.add(
            HistoryModel(
                R.drawable.humos,
                "ISKANDAR",
                150000,
                "18.12.2022 08:07:44",
                "O`tkazma"
            )
        )
        historyModel.add(
            HistoryModel(
                R.drawable.beline,
                "Beeline",
                -50000,
                "17.12.2022 13:24:47",
                "To`lov"
            )
        )
        historyModel.add(
            HistoryModel(
                R.drawable.visa,
                "Visa",
                50000,
                "16.12.2022 23:08:10",
                "O`tkazma"
            )
        )
    }

    private fun Selects() {
        selectModel.add(SelectModel(R.drawable.beline, "Beeline"))
        selectModel.add(SelectModel(R.drawable.humos, "Humo"))
        selectModel.add(SelectModel(R.drawable.uzcard, "UzCard"))
        selectModel.add(SelectModel(R.drawable.gaz, "Tabiy Gaz"))
        selectModel.add(SelectModel(R.drawable.visa, "Visa"))
    }

    private fun Payments() {
        paymentModel.add(PaymentModel(R.drawable.megaplanet, "Optimist\n(MegaPlanet)"))
        paymentModel.add(PaymentModel(R.drawable.shoxmed, "Shox-med\ncenter"))
        paymentModel.add(PaymentModel(R.drawable.kokoko, "Kokoko"))
        paymentModel.add(PaymentModel(R.drawable.kukmara, "KUKMARA"))
    }

    private fun AccessModels() {
        accessModel.add(AccessModel(R.drawable.beline, "Mening raqamim"))
        accessModel.add(AccessModel(R.drawable.ic_baseline_home_24, "Mening uyim"))
        accessModel.add(AccessModel(R.drawable.ic_baseline_time_to_leave_24, "Atto Pay"))
        accessModel.add(AccessModel(R.drawable.ic_baseline_qr_code_24, "QR-pass"))
        accessModel.add(AccessModel(R.drawable.h, "HUMO Pay"))
        accessModel.add(AccessModel(R.drawable.ic_baseline_cached_24, "Valyuta\nayiraboshlash"))
        accessModel.add(AccessModel(R.drawable.deposit, "omonatlar"))
        accessModel.add(AccessModel(R.drawable.ic_baseline_credit_card_24, "Kreditlar"))
        accessModel.add(AccessModel(R.drawable.ic_baseline_attach_money_24, "Valyuta kursi"))
        accessModel.add(AccessModel(R.drawable.global, "Xalqaro pul\no`tkazmalari"))
        accessModel.add(AccessModel(R.drawable.ic_baseline_account_balance_wallet_24, "Hamyonlar"))
    }

    private fun setRecyclerView() {
        binding.apply {
            RecyclerQuickAccess.adapter = accessAdapter
            RecyclerPaymentPlaces.adapter = paymentAdapter
            Recycler.adapter = selectAdapter
            RecyclerHistory.adapter = historyAdapter
        }
    }

    private fun setAdapter() {
        accessAdapter = AccessAdapter(accessModel)
        paymentAdapter = PaymentAdapter(paymentModel)
        selectAdapter = SelectAdapter(selectModel)
        historyAdapter = HistoryAdapter(requireContext(), historyModel)
    }

    private fun setData() {
        accessModel = ArrayList()
        paymentModel = ArrayList()
        selectModel = ArrayList()
        historyModel = ArrayList()
    }

    private fun refresh() {
        setData()
        dataAdds()
        setAdapter()
        setRecyclerView()

        Handler().postDelayed(Runnable {
            binding.swipeRefreshLayout.isRefreshing = false
        }, 2000)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}