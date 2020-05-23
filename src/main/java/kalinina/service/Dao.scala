package kalinina.service

import java.sql.DriverManager

import org.telegram.telegrambots.meta.api.objects.User

object Dao {

  import scalikejdbc._

  Class.forName("org.postgresql.Driver")
  val urlJdbc = "jdbc:postgresql://localhost:8000/bot?user=postgres&password=postgres"

  def saveNewChart(chartId: Long, user: User): Unit = {
    val connection = DriverManager.getConnection(urlJdbc)
    using(DB(connection)) { db =>
      db localTx { implicit session =>
        SQL(
          s"insert into bot.chat_inf(chat_id, user_name, first_name) values($chartId, '${user.getUserName}', '${user.getFirstName}')"
        ).update
          .apply()
      }
    }
  }

  def saveSuggestAdvice(advice: String): Unit = {
    val connection = DriverManager.getConnection(urlJdbc)
    using(DB(connection)) { db =>
      db localTx { implicit session =>
        SQL(
          s"insert into bot.suggest_advice(advice) values($advice)"
        ).update.apply()
      }
    }
  }

  def deleteChart(chartId: Long): Unit = {
    val connection = DriverManager.getConnection(urlJdbc)
    using(DB(connection)) { db =>
      db localTx { implicit session =>
        SQL(
          s"delete from bot.chat_inf where chat_id = $chartId"
        ).update.apply()
      }
    }
  }

  def isExistsChart(chartId: java.lang.Long): Boolean = {
    val connection = DriverManager.getConnection(urlJdbc)
    using(DB(connection)) { db =>
      db.localTx { implicit session =>
        SQL(
          s"select exists(select 1 from bot.chat_inf where chat_id = $chartId) as is_exist")
          .map(_.boolean("is_exist"))
          .single()
          .apply()
          .getOrElse(false)
      }
    }
  }

  def getAdvice(): String = {
    val connection = DriverManager.getConnection(urlJdbc)
    using(DB(connection)) { db =>
      db.localTx { implicit session =>
        SQL(
          s"""select
            | advice
            | from bot.advice
            | where id = (
            |   select
            |     case
            |       when id = 0 then 1
            |       else id
            |     end
            | from
            | (SELECT floor(random()*last_value) as id from bot.advice_id_seq limit 1) t
            )""".stripMargin)
          .map(_.string("advice"))
          .single()
          .apply()
          .getOrElse("")
      }
    }
  }
}
