import org.scalatest.{BeforeAndAfterAll, FlatSpecLike, Matchers}
import akka.actor.{Actor, ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActorRef, TestKit, TestProbe}

import scala.concurrent.duration._
import com.brauproject.iot.{Device, IotSupervisor}

class AkkaQuickstartSpec(_system: ActorSystem)
  extends TestKit(_system)
  with Matchers
  with FlatSpecLike
  with BeforeAndAfterAll{

  def this() = this(ActorSystem("AkkaQuickstartSpec"))

  override def afterAll: Unit = {
    shutdown(system)
  }

  "actor" should "reply with latest temperature reading" in {
    val probe = TestProbe()
    val deviceActor = system.actorOf(Device.props("group", "device"))

    deviceActor.tell(Device.RecordTemperature(requestId = 1, 24.0), probe.ref)
    probe.expectMsg(Device.TemperatureRecorded(requestId = 1))

    deviceActor.tell(Device.ReadTemperature(requestId = 2), probe.ref)
    val response1 = probe.expectMsgType[Device.RespondTemperature]
    response1.requestId should === (2)
    response1.value should === (Some(24.0))

    deviceActor.tell(Device.RecordTemperature(requestId = 3, 55.0), probe.ref)
    probe.expectMsg(Device.TemperatureRecorded(requestId = 3))

    deviceActor.tell(Device.ReadTemperature(requestId = 4), probe.ref)

    val response2 = probe.expectMsgType[Device.RespondTemperature]
    response2.requestId should === (4)
    response2.value should === (Some(55.0))
  }
}
