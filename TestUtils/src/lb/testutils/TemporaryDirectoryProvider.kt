package lb.testutils

import java.io.IOException
import java.nio.file.*
import java.text.SimpleDateFormat

public class TemporaryDirectoryProvider
(
    public val directoryPrefix: String
)
{

    public val tempRoot: Path;

    private var deletedDirectories = 0;
    private var deletedFiles = 0;


    // INITIALIZATION
    {
        // thinking out our temp root name
        val timestamp = SimpleDateFormat("yyyyMMdd-HHmmss").format(java.util.Date())
        val directoryName = directoryPrefix + '-' + timestamp

        // determine the system temporary path
        val tmpPretendentStr =
            if (Files.isDirectory(Paths.get("/tmp")))
                "/tmp"
            else
                System.getProperty("java.io.tmpdir")
        if (tmpPretendentStr == null)
            throw IllegalStateException("Could not determine the system temporary path.")
        val systemTempPath = Paths.get(tmpPretendentStr)!!
        if (!Files.exists(systemTempPath))
            throw IllegalStateException("The system temporary path \"${tmpPretendentStr}\" doesn't exist.")

        // create the directory
        tempRoot = systemTempPath.resolve(directoryName)!!
        try {
            Files.createDirectory(tempRoot)
        }
        catch (ё: IOException) {
            throw IOException("Could not create the temporary directory \"${tempRoot}\": ${ё.getMessage()}", ё)
        }

        // ok
        System.out.println("Root temporary directory: ${tempRoot}")

        // we should delete it on exist
        Runtime.getRuntime().addShutdownHook(ShutDownHook())
    }


    inner class ShutDownHook : Thread()
    {
        override fun run()
        {
            cleanupTotally()
        }
    }


    public fun getDirectory(name: String): Path
    {
        val dir = tempRoot.resolve(name)!!;

        // create the directory
        if (!Files.isDirectory(dir)) {
            try {
                Files.createDirectory(dir)
            }
            catch (ё: IOException) {
                throw IOException("Could not create the subdirectory directory \"${dir}\": ${ё.toString()}", ё)
            }
        }

        return dir;
    }


    public fun cleanupTotally()
    {
        cleanupInnerContent()
        Files.delete(tempRoot)
    }


    public fun cleanupInnerContent()
    {
        deletedDirectories = 0
        deletedFiles = 0

        deleteDirectoryContent(tempRoot)

        if (deletedDirectories > 0 || deletedFiles > 0)
            System.out.println("Deleted ${deletedDirectories} directories and ${deletedFiles} files.")
    }

    public fun deleteDirectoryContent(dir: Path)
    {
        val nestedEntries = dir.listEntries()
        nestedEntries.forEach { deleteEntry(it) }
    }

    public fun deleteEntry(path: Path)
    {
        val isDir = Files.isDirectory(path);

        if (isDir)
            deleteDirectoryContent(path)

        try {
            Files.delete(path)
            if (isDir) deletedDirectories++; else deletedFiles++
        }
        catch (ё: IOException) {
            System.err.println("Could not delete ${if (isDir) "directory" else "file"} \"${path}\": ${ё.javaClass.getSimpleName()}: ${ё.getMessage()}")
        }
    }


    public fun Path.listEntries(): Collection<Path>
    {
        val dirStream = Files.newDirectoryStream(this)!!
        try {
            return dirStream.toList()
        }
        finally {
            dirStream.close()
        }
    }

}