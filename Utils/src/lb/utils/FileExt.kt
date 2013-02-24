package lb.utils

import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.io.Writer
import java.nio.charset.Charset

val utf8 = Charset.forName("UTF-8")


public fun File.produceText(producer: (writer:Writer) -> Unit)
{
    val stream = FileOutputStream(this)
    try {
        val writer1 = OutputStreamWriter(stream, utf8)
        try {
            val bufWriter = BufferedWriter(writer1)
            try {

                producer(bufWriter)

            }
            finally {
                bufWriter.close()
            }
        }
        finally {
            writer1.close()
        }
    }
    finally {
        stream.close()
    }
}


