package com.example.chinesewordsproject

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlin.random.Random

class RepetitionActivity : AppCompatActivity() {
    private lateinit var currentWord: TextView
    private lateinit var currentPinyin: TextView
    private lateinit var option1: Button
    private lateinit var option2: Button
    private lateinit var option3: Button
    private var currentWordIndex = 0
    private var correctOptionIndex = 0
    private val wordsToRepeat = RepetitionManager.getWords()
    private val allWords = listOf(
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
        setContentView(R.layout.activity_repetition)

        // Initialize views
        currentWord = findViewById(R.id.currentWord)
        currentPinyin = findViewById(R.id.currentPinyin)
        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)

        // Set up return button
        val returnButton = findViewById<Button>(R.id.returnButton)
        returnButton.setOnClickListener {
            finish()
        }

        // Set up option buttons
        option1.setOnClickListener { checkAnswer(0) }
        option2.setOnClickListener { checkAnswer(1) }
        option3.setOnClickListener { checkAnswer(2) }

        // Show first word if there are words to repeat
        if (wordsToRepeat.isNotEmpty()) {
            showNextWord()
        } else {
            currentWord.text = "Добавьте слова в повтор"
            currentPinyin.text = ""
            option1.isEnabled = false
            option2.isEnabled = false
            option3.isEnabled = false
        }
    }

    private fun showNextWord() {
        if (currentWordIndex >= wordsToRepeat.size) {
            currentWordIndex = 0
        }

        val currentItem = wordsToRepeat[currentWordIndex]
        currentWord.text = currentItem.chineseCharacter
        currentPinyin.text = currentItem.pinyin

        // Generate options
        val options = mutableListOf(currentItem.russianTranslation)
        val otherWords = allWords.filter { it != currentItem }
        val randomWords = otherWords.shuffled().take(2)
        options.addAll(randomWords.map { it.russianTranslation })
        options.shuffle()

        // Set options text
        option1.text = options[0]
        option2.text = options[1]
        option3.text = options[2]

        // Store correct option index
        correctOptionIndex = options.indexOf(currentItem.russianTranslation)

        // Reset button colors
        option1.backgroundTintList = ContextCompat.getColorStateList(this, R.color.golden)
        option2.backgroundTintList = ContextCompat.getColorStateList(this, R.color.golden)
        option3.backgroundTintList = ContextCompat.getColorStateList(this, R.color.golden)
    }

    private fun checkAnswer(selectedIndex: Int) {
        if (selectedIndex == correctOptionIndex) {
            // Show correct answer in green
            when (selectedIndex) {
                0 -> option1.backgroundTintList = ContextCompat.getColorStateList(this, R.color.green)
                1 -> option2.backgroundTintList = ContextCompat.getColorStateList(this, R.color.green)
                2 -> option3.backgroundTintList = ContextCompat.getColorStateList(this, R.color.green)
            }

            // Move to next word after a delay
            currentWord.postDelayed({
                currentWordIndex++
                showNextWord()
            }, 1000)
        }
    }
} 