

import akka.actor._
import akka.persistence._
import akka.pattern.ask
import scala.concurrent.duration._
import akka.util.Timeout


/**
 * @author ragb
 */
object main extends App {
  implicit val system = ActorSystem()
  import system.dispatcher
  val store = system.actorOf(Props[IntListStoreActor])
  val view = system.actorOf(Props[SumView])
  store ! Command(1, 1)
  store ! Command(1, 9)
  view ! Update(await=false)
  implicit val timeout = Timeout(5 seconds)
  (view ? State).mapTo[Int] foreach println
  readLine()
  system.shutdown()
}


case class Command(value : Int, count : Int)
case class Event(value : Int)
case object State


class IntListStoreActor extends PersistentActor {
  override def persistenceId = "int-value-counter"
  
  var state : Seq[Int] = Vector.empty[Int]
  
  override def receiveCommand : Receive = {
    case Command(value, count) => {
      for(_ <- 0 until count) {
        persist(Event(value))(addEvent)
      }
    }
    case State => sender ! state
  
  }
  
  override def receiveRecover : Receive = {
    case event : Event => addEvent(event)
  }
  def addEvent(event : Event) : Unit = {
    state = state ++ Seq(event.value)
  }
}

class SumView extends PersistentView {
  override def persistenceId = "int-value-counter"
  override def viewId = "sum-view"
  var state : Int = 0

  def receive = {
    case State => sender ! state
    case Event(value) => state = state +1
  }
}