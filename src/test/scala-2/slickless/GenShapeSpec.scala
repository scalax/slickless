package net.scalax.slickless

import shapeless.{Generic, HNil}
import slick.jdbc.H2Profile.api._
import compat._

import scala.concurrent.ExecutionContext.Implicits.global

class GenShapeSpec extends Spec {

  case class Address(id: Long, house: Int, street: String)

  class Addresss(tag: Tag) extends Table[Address](tag, "addresses") {
    def id     = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def house  = column[Int]("house")
    def street = column[String]("street")

    def * = (id :: house :: street :: HNil).mappedWith(Generic[Address])
  }

  lazy val addresses = TableQuery[Addresss]

  "slick tables with generic mappings" - {
    "should support inserts and selects" in {
      val db = Database.forConfig("h2")

      val address = Address(1L, 29, "Acacia Road")

      val action = for {
        _   <- addresses.schema.create
        _   <- addresses += address
        ans <- addresses.result.head
        _   <- addresses.schema.drop
      } yield ans

      whenReady(db.run(action)) { _ should equal(address) }
    }
  }
}
