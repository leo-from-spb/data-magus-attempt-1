package org.jetbrains.datamagus.sql.lang

object OracleSQL : SQL()
{

    override val dialectAbb: String = "Ora"
    override val dialectName: String = "Oracle"

    override val supportedComments: Set<CommentKind> = setOf(CommentKind.Normal, CommentKind.C_like)

}