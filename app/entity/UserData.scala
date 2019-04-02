package entity

import play.api.libs.json._
import scalikejdbc._

case class UserData(
                userid: Int,
                username: String,
                latitude: String,
                longitude: String
              ){
  def save()(implicit session: DBSession = UserData.autoSession): UserData = UserData.save(this)(session)
}

object UserData extends SQLSyntaxSupport[UserData]{
  def apply(userid: Int, username: String, latitude: String, longitude: String ): UserData = new UserData(userid, username, latitude, longitude)

  def apply(c: SyntaxProvider[UserData])(rs: WrappedResultSet): UserData = apply(c.resultName)(rs)
  def apply(c: ResultName[UserData])(rs: WrappedResultSet): UserData = new UserData(
    userid = rs.get("userid"),
    username = rs.get("username"),
    latitude = rs.get("latitude"),
    longitude = rs.get("longitude")
  )

  override val tableName:String = "UserData"
  override val columns:Seq[String] = Seq("username", "userid", "latitude", "longitude")

  val ud = UserData.syntax("ud")
  def findAll(): List[UserData] = {
    val conn: java.sql.Connection = ConnectionPool.borrow()

    println("")
    DB(conn) readOnly { implicit session =>
      sql"select * from userdata".map(UserData(ud)).list.apply()
    }
  }


  def save(userData: UserData)(implicit session: DBSession = autoSession): UserData = {
    withSQL {
      update(UserData).set(
        column.username -> userData.username,
        column.latitude -> userData.latitude,
        column.longitude -> userData.longitude).where.eq(column.userid, userData.userid)
    }.update.apply()
    userData
  }

  implicit val residentWrites:Writes[UserData] = Json.writes[UserData]
}