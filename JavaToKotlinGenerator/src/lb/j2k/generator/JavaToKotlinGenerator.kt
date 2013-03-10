package lb.j2k.generator

import java.util.jar.JarFile
import java.nio.file.Paths
import java.nio.file.Files
import java.util.TreeSet
import java.util.ArrayList
import java.util.regex.Pattern
import javafx.beans.Observable
import javafx.collections.ObservableList
import java.util.HashSet
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.lang.reflect.ParameterizedType
import java.lang.reflect.WildcardType
import java.lang.reflect.TypeVariable


public fun main(args: Array<String>)
{
    val jfxjar = obtainJavaFxJar()
    val codeBuffer = prepareCodeBuffer()
    processJar(jfxjar, codeBuffer)

    System.out.println(codeBuffer)
}


fun prepareCodeBuffer() : StringBuilder
{
    val buf = StringBuilder()
    return buf
       .append("// Kotlin-friendly JavaFX extensions \n")
       .append("package javafx;\n")
       .append("\n")
       .append("import javafx.collections.ObservableList\n")
       .append("\n\n\n")
}


fun obtainJavaFxJar() : JarFile
{
    // obtain java home
    val javaHomePath = System.getProperty("java.home")
    if (javaHomePath == null) {
        halt(1, "JRE home not determined.")
        throw RuntimeException()
    }
    System.out.println("Java Home: ${javaHomePath}")

    // find the jar
    val jfxjar = Paths.get(javaHomePath, "lib/jfxrt.jar")!!
    if (!Files.exists(jfxjar)) {
        halt(2, "The jfxrt.jar is not found.")
        throw RuntimeException()
    }

    // open the jar
    val jar = JarFile(jfxjar.toFile())
    return jar
}

val interestingEntryNamePattern =
        Pattern.compile("^javafx\\/[^$]+\\.class$")

fun processJar(jar: JarFile, codeBuffer: StringBuilder)
{
    val classes = retrieveJavaFxClasses(jar);
    classes.forEach { processClass(it, codeBuffer) }
}



fun retrieveJavaFxClasses(jar: JarFile): Collection<Class<*>>
{
    val classes = ArrayList<Class<*>>()
    for (entry in jar.entries()) {
        val entryName = entry.getName()
        if (entryName.matches(interestingEntryNamePattern)) {
            val clazzName = entryName.replace(".class","").replace("/",".")
            if (clazzName.startsWith("javafx.embed.swt."))
                continue

            try {
                val clazz = Class.forName(clazzName)
                classes.add(clazz)
            }
            catch (cnfe: NoClassDefFoundError) {
                System.err.println("Unable to get class ${clazzName}: class ${cnfe.getMessage()}")
                continue
            }
        }
    }
    System.out.println("Found ${classes.size} classes.")
    return classes
}


val ourObservableListClass = javaClass<ObservableList<*>>()

val ourGetObservableListMethodPattern = Pattern.compile("^get[A-Z].*")

fun processClass(klass: Class<*>, codeBuffer: StringBuilder)
{
    val methods = klass.getDeclaredMethods()
    for (method in methods) {
        val methodName = method.getName()
        if (methodName == null || !methodName.matches(ourGetObservableListMethodPattern))
            continue
        if (!method.getParameterTypes()!!.isEmpty())
            continue
        if (Modifier.isStatic(method.getModifiers()))
            continue

        val returnType = method.getReturnType()
        if (returnType?.getSimpleName() == "ObservableList") {
            processObservableListMethod(klass, method, codeBuffer)
        }
    }
}


fun processObservableListMethod(klass: Class<*>, method: Method, codeBuffer: StringBuilder)
{
    val klassName = klass.getName()!!
    val methodName = method.getName()!!
    val propertyName = propertyNameByGetterName(methodName)
    val returnType = method.getGenericReturnType()!! as ParameterizedType
    val propertyType = returnType.getActualTypeArguments()!![0]!!

    val propertyTypeName =
            when (propertyType) {
                is Class<*> -> propertyType.getName()
                is WildcardType -> propertyType.getUpperBounds()!![0]!!
                is TypeVariable<*> -> "***"
                else -> "*"
            }


    val definition =
            "public val ${klassName}.${propertyName}: ObservableList<${propertyTypeName}>  get() = this.${methodName}()!!;"

    codeBuffer.append(definition).append('\n')
}


fun propertyNameByGetterName(getterName: String): String
{
    val c1 = getterName[3]
    return "${Character.toLowerCase(c1)}${getterName.substring(4)}"
}


fun String.matches(pattern: Pattern): Boolean
{
    return pattern.matcher(this).matches()
}


inline fun halt(exitCode: Int, errMessage: String)
{
    System.out.flush()
    System.err.flush()
    System.err.println("FATAL ERROR: ${errMessage}")
    System.exit(exitCode)

    throw RuntimeException() // this line of code is not reachable
}


