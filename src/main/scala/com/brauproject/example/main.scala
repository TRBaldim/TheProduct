package com.brauproject.example

import akka.actor.{ActorSystem, Props}

import scala.io.StdIn

object main extends App {
  val system = ActorSystem("testSystem")

  val firstRef = system.actorOf(Props[PrintMyActorRefActor], "first-actor")
  println(s"First $firstRef")
  firstRef ! "printit"

  // Start Stop Action
  val first = system.actorOf(Props[StartStopActor1], "first")
  first ! "stop"

  // Supervising with Fail
  val supervisingActor = system.actorOf(Props[SupervisingActor], "supervising-actor")
  supervisingActor ! "failChild"

  println(">>> Press ENTER to exit <<<")

  try StdIn.readLine()
  finally system.terminate()
}
