package org.jetbrains.datamagus.sql.lang

object H2SQL : SQL()
{

    override val dialectAbb: String = "H2"
    override val dialectName: String = "H2db"

    override val supportedComments: Set<CommentKind> = setOf(CommentKind.Normal, CommentKind.C_like)

}