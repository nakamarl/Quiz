package jp.ac.it_college.std.s17007.quiz

import org.apache.commons.csv.*
import java.io.Reader
import java.io.Writer


data class Quiz(
    val difficult: String,
    val question: String,
    val answer1: String,
    val answer2: String,
    val answer3: String,
    val answer4: String
)

enum class QuizColumn(override val header: String) : Column{
    DIFFICULT("難易度(1 or 2)"),
    QUESTION("問題文"),
    ANSWER1("選択肢A(正解)"),
    ANSWER2("選択肢B(不正解)"),
    ANSWER3("選択肢C(不正解)"),
    ANSWER4("選択肢D(不正解)"),
}

class QuizReader(reader: Reader) : CSVReader<Quiz>(reader){
    override fun CSVRecord.parse() = Quiz(
        difficult = column(QuizColumn.DIFFICULT),
        question = column(QuizColumn.QUESTION),
        answer1 = column(QuizColumn.ANSWER1),
        answer2 = column(QuizColumn.ANSWER2),
        answer3 = column(QuizColumn.ANSWER3),
        answer4 = column(QuizColumn.ANSWER4)
    )
}


class QuizWriter(writer: Writer) : CSVWriter<Quiz>(writer,HEADERS){
    companion object {
        private val HEADERS = QuizColumn.values().toList()
    }

    override fun Quiz.encode() = mapOf(
        QuizColumn.DIFFICULT to difficult,
        QuizColumn.QUESTION to question,
        QuizColumn.ANSWER1 to answer1,
        QuizColumn.ANSWER2 to answer2,
        QuizColumn.ANSWER3 to answer3,
        QuizColumn.ANSWER4 to answer4
    )
}


private val READER_FORMAT = CSVFormat.DEFAULT.withFirstRecordAsHeader()
abstract class CSVReader<out R>(reader: Reader,format: CSVFormat = READER_FORMAT,offset: Long = 0){
    private val parser = CSVParser(reader,format,0,offset)
    abstract fun CSVRecord.parse(): R

    fun lines(): Sequence<R>{
        return parser.asSequence().map{it.parse()}
    }

    open fun CSVRecord.column(c: Column): String{
        return this[c.header]
    }
}


private val WRITER_FORMAT = CSVFormat.DEFAULT.withRecordSeparator('\n').withQuoteMode(QuoteMode.NON_NUMERIC)
abstract class CSVWriter<in R>(writer: Writer,private val headers: List<Column>,format: CSVFormat = WRITER_FORMAT){

    private val format = format.withHeader(*(headers.map{it.header}.toTypedArray()))

    private val printer: CSVPrinter = this.format.print(writer)

    abstract fun R.encode(): Map<out Column,Any>

    fun write(value: R){
        val encoded = value.encode()
        printer.printRecord(headers.map{encoded[it]})
    }
}


interface Column{
    val header: String
    val ordinal: Int
}