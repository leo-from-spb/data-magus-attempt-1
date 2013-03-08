package lb.utils

import java.io.Writer
import java.nio.charset.Charset
import java.nio.file.*

val utf8 = Charset.forName("UTF-8")


public fun Path.produceText(producer: (writer:Writer) -> Unit)
{
    val writer = Files.newBufferedWriter(this, utf8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
    try {
        producer(writer)
    }
    finally {
        writer.close()
    }
}


