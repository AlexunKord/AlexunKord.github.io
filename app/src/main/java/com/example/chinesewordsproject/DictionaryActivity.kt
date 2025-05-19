package com.example.chinesewordsproject

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DictionaryActivity : AppCompatActivity() {
    private lateinit var adapter: DictionaryAdapter
    private val dictionaryItems = listOf(
        DictionaryItem("我", "wǒ", "я"),
    DictionaryItem("你", "nǐ", "ты"),
    DictionaryItem("他", "tā", "он"),
    DictionaryItem("她", "tā", "она"),
    DictionaryItem("吃", "chī", "кушать"),
    DictionaryItem("爸爸", "bàba", "папа"),
    DictionaryItem("妈妈", "māma", "мама"),
    DictionaryItem("姐姐", "jiějie", "старшая сестра"),
    DictionaryItem("妹妹", "mèimei", "младшая сестра"),
    DictionaryItem("有", "yǒu", "иметь"),
    DictionaryItem("年", "nián", "год"),
    DictionaryItem("女儿", "nǚ'ér", "дочь"),
    DictionaryItem("朋友", "péngyou", "друг"),
    DictionaryItem("漂亮", "piàoliang", "красивый"),
    DictionaryItem("苹果", "píngguǒ", "яблоко"),
    DictionaryItem("七", "qī", "семь"),
    DictionaryItem("钱", "qián", "деньги"),
    DictionaryItem("前面", "qiánmiàn", "перед"),
    DictionaryItem("请", "qǐng", "пожалуйста"),
    DictionaryItem("去", "qù", "идти"),
    DictionaryItem("热", "rè", "горячий"),
    DictionaryItem("人", "rén", "человек"),
    DictionaryItem("认识", "rènshi", "знать"),
    DictionaryItem("日", "rì", "день"),
    DictionaryItem("三", "sān", "три"),
    DictionaryItem("商店", "shāngdiàn", "магазин"),
    DictionaryItem("上", "shàng", "верх"),
    DictionaryItem("上午", "shàngwǔ", "утро"),
    DictionaryItem("少", "shǎo", "мало"),
    DictionaryItem("谁", "shéi", "кто"),
    DictionaryItem("什么", "shénme", "что"),
    DictionaryItem("十", "shí", "десять"),
    DictionaryItem("时候", "shíhou", "время"),
    DictionaryItem("是", "shì", "быть"),
    DictionaryItem("书", "shū", "книга"),
    DictionaryItem("水", "shuǐ", "вода"),
    DictionaryItem("水果", "shuǐguǒ", "фрукты"),
    DictionaryItem("睡觉", "shuìjiào", "спать"),
    DictionaryItem("说话", "shuōhuà", "говорить"),
    DictionaryItem("四", "sì", "четыре"),
    DictionaryItem("岁", "suì", "лет (возраст)"),
    DictionaryItem("太", "tài", "слишком"),
    DictionaryItem("天气", "tiānqì", "погода"),
    DictionaryItem("听", "tīng", "слушать"),
    DictionaryItem("同学", "tóngxué", "одноклассник"),
    DictionaryItem("喂", "wèi", "алло"),
    DictionaryItem("我们", "wǒmen", "мы"),
    DictionaryItem("五", "wǔ", "пять"),
    DictionaryItem("喜欢", "xǐhuan", "нравиться"),
    DictionaryItem("下", "xià", "низ"),
    DictionaryItem("下午", "xiàwǔ", "после полудня"),
    DictionaryItem("下雨", "xiàyǔ", "идти дождь"),
    DictionaryItem("先生", "xiānsheng", "господин"),
    DictionaryItem("现在", "xiànzài", "сейчас"),
    DictionaryItem("小", "xiǎo", "маленький"),
    DictionaryItem("小姐", "xiǎojie", "мисс"),
    DictionaryItem("些", "xiē", "некоторые"),
    DictionaryItem("写", "xiě", "писать"),
    DictionaryItem("谢谢", "xièxie", "спасибо"),
    DictionaryItem("星期", "xīngqī", "неделя"),
    DictionaryItem("学生", "xuésheng", "студент"),
    DictionaryItem("学习", "xuéxí", "учиться"),
    DictionaryItem("学校", "xuéxiào", "школа"),
    DictionaryItem("一", "yī", "один"),
    DictionaryItem("衣服", "yīfu", "одежда"),
    DictionaryItem("医生", "yīshēng", "врач"),
    DictionaryItem("医院", "yīyuàn", "больница"),
    DictionaryItem("椅子", "yǐzi", "стул"),
    DictionaryItem("月", "yuè", "месяц"),
    DictionaryItem("在", "zài", "в"),
    DictionaryItem("再见", "zàijiàn", "до свидания"),
    DictionaryItem("怎么", "zěnme", "как"),
    DictionaryItem("怎么样", "zěnmeyàng", "как дела"),
    DictionaryItem("这", "zhè", "этот"),
    DictionaryItem("中国", "Zhōngguó", "Китай"),
    DictionaryItem("中午", "zhōngwǔ", "полдень"),
    DictionaryItem("住", "zhù", "жить"),
    DictionaryItem("桌子", "zhuōzi", "стол"),
    DictionaryItem("字", "zì", "иероглиф"),
    DictionaryItem("昨天", "zuótiān", "вчера"),
    DictionaryItem("坐", "zuò", "сидеть"),
    DictionaryItem("做", "zuò", "делать"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)

        // Set up return button
        val returnButton = findViewById<Button>(R.id.returnButton)
        returnButton.setOnClickListener {
            finish()
        }

        // Set up RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.dictionaryRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = DictionaryAdapter(dictionaryItems) { item, button ->
            if (RepetitionManager.containsWord(item)) {
                RepetitionManager.removeWord(item)
                button.text = "Добавить в повтор"
            } else {
                RepetitionManager.addWord(item)
                button.text = "Удалить из повтора"
            }
        }
        recyclerView.adapter = adapter

        // Set up search
        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                val filteredItems = dictionaryItems.filter {
                    it.chineseCharacter.contains(query) ||
                    it.pinyin.contains(query, ignoreCase = true) ||
                    it.russianTranslation.contains(query, ignoreCase = true)
                }
                adapter.updateItems(filteredItems)
                
                // Scroll to first matching item
                if (filteredItems.isNotEmpty()) {
                    val position = dictionaryItems.indexOf(filteredItems[0])
                    recyclerView.scrollToPosition(position)
                }
            }
        })
    }
}

data class DictionaryItem(
    val chineseCharacter: String,
    val pinyin: String,
    val russianTranslation: String
)

class DictionaryAdapter(
    private var items: List<DictionaryItem>,
    private val onRepetitionButtonClick: (DictionaryItem, Button) -> Unit
) : RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder>() {

    class DictionaryViewHolder(view: android.view.View) : RecyclerView.ViewHolder(view) {
        val chineseCharacter = view.findViewById<android.widget.TextView>(R.id.chineseCharacter)
        val pinyin = view.findViewById<android.widget.TextView>(R.id.pinyin)
        val russianTranslation = view.findViewById<android.widget.TextView>(R.id.russianTranslation)
        val addToRepetitionButton = view.findViewById<Button>(R.id.addToRepetitionButton)
    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): DictionaryViewHolder {
        val view = android.view.LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dictionary, parent, false)
        return DictionaryViewHolder(view)
    }

    override fun onBindViewHolder(holder: DictionaryViewHolder, position: Int) {
        val item = items[position]
        holder.chineseCharacter.text = item.chineseCharacter
        holder.pinyin.text = item.pinyin
        holder.russianTranslation.text = item.russianTranslation
        
        // Set initial button text based on whether the word is in repetition
        holder.addToRepetitionButton.text = if (RepetitionManager.containsWord(item)) {
            "Удалить из повтора"
        } else {
            "Добавить в повтор"
        }
        
        holder.addToRepetitionButton.setOnClickListener {
            onRepetitionButtonClick(item, holder.addToRepetitionButton)
        }
    }

    override fun getItemCount() = items.size

    fun updateItems(newItems: List<DictionaryItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}

object RepetitionManager {
    private val wordsToRepeat = mutableListOf<DictionaryItem>()

    fun addWord(item: DictionaryItem) {
        if (!wordsToRepeat.contains(item)) {
            wordsToRepeat.add(item)
        }
    }

    fun removeWord(item: DictionaryItem) {
        wordsToRepeat.remove(item)
    }

    fun containsWord(item: DictionaryItem): Boolean {
        return wordsToRepeat.contains(item)
    }

    fun getWords(): List<DictionaryItem> = wordsToRepeat.toList()
} 