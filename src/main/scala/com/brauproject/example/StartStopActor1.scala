package com.brauproject.example

import akka.actor.{Actor, Props}

class StartStopActor1 extends Actor {
  override def preStart(): Unit = {
    println("First Started")
    context.actorOf(Props[StartStopActor2], "second")
  }

  override def postStop(): Unit = println("first stopped")

  override def receive: Receive = {
    case "stop" => context.stop(self)
  }
}
