package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import entity.UserData
import java.net._

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def health() = Action { implicit request: Request[AnyContent] => {
    Ok("OK")
    }
  }

  def getData() = Action { implicit request: Request[AnyContent] => {
    val response = Json.toJson(UserData.findAll())
    val localhost: InetAddress = InetAddress.getLocalHost
    val localIpAddress: String = localhost.getHostAddress

    println(s"localIpAddress = $localIpAddress")
    Ok(response)
    }
  }

}
