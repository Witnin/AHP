package com.wsy.ahp.activity.message

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.wsy.ahp.R
import com.wsy.ahp.http.common.ArouterUrl
import kotlinx.android.synthetic.main.activity_message_test.inputText
import kotlinx.android.synthetic.main.activity_message_test.recyclerView_message
import kotlinx.android.synthetic.main.activity_message_test.send

@Route(path = ArouterUrl.RECYCLE_VIEW_MESSAGE)
class MessageTestActivity : AppCompatActivity(),View.OnClickListener {
    private val msgList = ArrayList<Msg>()
    private var adapter: MsgAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_test)
        initMsg()
        val layoutManager = LinearLayoutManager(this)
        recyclerView_message.layoutManager = layoutManager
        adapter = MsgAdapter(msgList)
        recyclerView_message.adapter = adapter
        send.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v) {
            send -> {
                val content = inputText.text.toString()
                if (content.isNotEmpty()) {
                    val msg = Msg(content, Msg.TYPE_SENT)
                    msgList.add(msg)
                    adapter?.notifyItemInserted(msgList.size - 1) // 当有新消息时，
                    recyclerView_message.scrollToPosition(msgList.size - 1) // 将RecyclerView

                    inputText.setText("") // 清空输入框中的内容
                }
            }
        }
    }
    private fun initMsg() {
        val msg1 = Msg("Hello guy.", Msg.TYPE_RECEIVED)
        msgList.add(msg1)
        val msg2 = Msg("Hello. Who is that?", Msg.TYPE_SENT)
        msgList.add(msg2)
        val msg3 = Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED)
        msgList.add(msg3)
        val msg4 = Msg("Hello guy.", Msg.TYPE_RECEIVED)
        msgList.add(msg4)
        val msg5 = Msg("Hello. Who is that?", Msg.TYPE_SENT)
        msgList.add(msg5)
        val msg6 = Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED)
        msgList.add(msg6)
        val msg7 = Msg("Hello guy.", Msg.TYPE_RECEIVED)
        msgList.add(msg7)
        val msg8 = Msg("Hello. Who is that?", Msg.TYPE_SENT)
        msgList.add(msg8)
        val msg9 = Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED)
        msgList.add(msg9)
    }
}