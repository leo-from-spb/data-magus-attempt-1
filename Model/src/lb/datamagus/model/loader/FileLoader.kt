package lb.datamagus.model.loader

import java.io.File
import java.io.Writer
import lb.datamagus.model.core.Delta
import lb.datamagus.model.core.ModelManupulator
import lb.datamagus.model.core.Modification
import lb.datamagus.model.core.WorkModel
import lb.utils.produceText

public class FileLoader
{
    public fun saveModelToFile(model: WorkModel, file: File)
    {
        // export the fiven model
        val mm = ModelManupulator()
        val memo = model.read { model ->
            val root = model.getProjectRoot()
            mm.exportWholeModelAsModification(model, root.name)
        }

        // save to the file
        saveModificationToFile(memo, file)
    }


    fun saveModificationToFile(memo: Modification, file: File)
    {
        file.produceText { writer ->

            // write the header
            writer.write("DataMagus version 0.1\n")
            writer.write("Model ${memo.name}\n\n\n")

            // write all nodes
            for (d in memo.deltas) {
                saveOneDelta(d, writer)
            }
        }
    }


    private fun saveOneDelta(d: Delta, writer: Writer)
    {
        writer.write("\n")
        writer.write("@${d.id}: ${d.className}  -- ${d.nodeDisplayName}\n")

        for (p in d.props) {
            if (p.neo == null) continue
            writer.write("\t${p.propertyName} = ${p.neo}\n")
        }

        writer.write("^${d.id}\n")
        writer.write("\n")
    }

}