package org.jetbrains.datamagus.sql.lang

import java.util.regex.Pattern

/**
 * SQL dialect definition.
 */
abstract class SQL
{

    /**
     * Kind of comment.
     * @property continuos  whether the comment is multiline
     * @property prefix     the prefix (opening sequence) of the comment
     * @property suffix     the suffix (closing sequence) of the comment
     * @property pattern    the pattern
     */
    enum class CommentKind
    (
            val continuos: Boolean,
            val prefix: String,
            val suffix: String?,
            val pattern: Pattern
    )
    {
        //@formatter:off
        Normal(false, "--", null, Pattern.compile("""--[^\n]*$""", Pattern.MULTILINE)),
        C_like(true,  "/*", "*/", Pattern.compile("""/*(.*?)*/""", Pattern.DOTALL)),
        Unix  (false, "#",  null, Pattern.compile("""#[^\n]*$""",  Pattern.MULTILINE))
        //@formatter:on
    }



    /// PROPERTIES \\\

    /**
     * Abbreviation (code) of the dialect.
     */
    abstract val dialectAbb: String

    /**
     * Human readable name of the dialect.
     */
    abstract val dialectName: String

    /**
     * Supported comments.
     */
    abstract val supportedComments: Set<CommentKind>

}