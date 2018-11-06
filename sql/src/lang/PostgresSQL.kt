package org.jetbrains.datamagus.sql.lang

object PostgresSQL : SQL()
{

    override val dialectAbb: String = "Pg"
    override val dialectName: String = "Postgres"

    override val supportedComments: Set<CommentKind> = setOf(CommentKind.Normal, CommentKind.C_like)

}